package com.centrald.bitcoin.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class HttpBaseService {

    private static final Logger logger = LoggerFactory.getLogger(HttpBaseService.class);

    private PoolingHttpClientConnectionManager connectionManager;
    private HttpClientContext httpClientContext;
    private RequestConfig requestConfig;
    private String urlPrefix;
    private Integer httpTimeout = 60000;

    public HttpBaseService(String urlPrefix) {
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(10);
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(httpTimeout)
                .setConnectTimeout(httpTimeout)
                .setConnectionRequestTimeout(httpTimeout)
                .build();
        this.urlPrefix = urlPrefix;
    }

    public HttpBaseService(String urlPrefix, String username, String password) {
        this(urlPrefix);
        URI uri = URI.create(urlPrefix);
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        AuthCache authCache = new BasicAuthCache();
        authCache.put(new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme()), new BasicScheme());
        httpClientContext = HttpClientContext.create();
        httpClientContext.setCredentialsProvider(credentialsProvider);
        httpClientContext.setAuthCache(authCache);
    }

    private CloseableHttpClient getHttpClient() {
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(connectionManager);
        return httpClientBuilder.build();
    }

    private HttpEntity getHttpEntity(Map<String, String> queryMap) {
        logger.info("Set Data: {}", queryMap.toString());
        List<NameValuePair> parameterList = new ArrayList<>();
        for (String key : queryMap.keySet()) {
            parameterList.add(new BasicNameValuePair(key, queryMap.get(key)));
        }
        return new UrlEncodedFormEntity(parameterList, StandardCharsets.UTF_8);
    }

    private HttpEntity getHttpEntity(String content) {
        logger.info("Set Data: {}", content);
        return new StringEntity(content, StandardCharsets.UTF_8);
    }

    private String execute(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        request.setConfig(requestConfig);
        String response = null;
        try {
            HttpResponse httpResponse = httpClientContext == null ? httpClient.execute(request) : httpClient.execute(request, httpClientContext);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String body = "";
            if (httpResponse.getEntity() != null) {
                body = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);

            }
            String simpleBody = body.length() > 100 ? StringUtils.substring(body, 0, 100) + " ..." : body;
            logger.info("Get http status {}, body:\n{}", statusCode, simpleBody);
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED || statusCode == HttpStatus.SC_ACCEPTED) {
                response = body;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.reset();
        }
        return response;
    }

    private String buildUrl(String uri, Map<String, String> queryMap) {
        String url;
        try {
            URIBuilder uriBuilder = new URIBuilder(urlPrefix + uri);
            for (String key : queryMap.keySet()) {
                uriBuilder.addParameter(key, queryMap.get(key));
            }
            url = uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return url;
    }

    private void setHeaders(HttpRequestBase request, Map<String, String> headerMap) {
        logger.info("Set Headers: {}", headerMap.toString());
        for (String name : headerMap.keySet()) {
            request.setHeader(name, headerMap.get(name));
        }
    }

    protected String httpGet(String uri) {
        return httpGet(uri, null, null);
    }

    protected String httpGet(String uri, Map<String, String> queryMap) {
        return httpGet(uri, queryMap, null);
    }

    protected String httpGet(String uri, Map<String, String> queryMap, Map<String, String> headerMap) {
        String url = queryMap == null ? urlPrefix + uri : buildUrl(uri, queryMap);
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        logger.info("HttpClient GET {}", httpGet.getURI());
        if (headerMap != null) {
            setHeaders(httpGet, headerMap);
        }
        return execute(httpGet);
    }

    protected String httpPost(String uri, Map<String, String> queryMap) {
        return httpPost(uri, queryMap, null, null);
    }

    protected String httpPost(String uri, String content) {
        return httpPost(uri, null, content, null);
    }

    protected String httpPost(String uri, Map<String, String> queryMap, String content, Map<String, String> headerMap) {
        System.out.println(urlPrefix +uri);
        HttpPost httpPost = new HttpPost(urlPrefix + uri);
        logger.info("HttpClient POST {}", httpPost.getURI());
        if (queryMap != null) {
            httpPost.setEntity(getHttpEntity(queryMap));
        } else if (content != null) {
            httpPost.setEntity(getHttpEntity(content));
        }
        if (headerMap != null) {
            setHeaders(httpPost, headerMap);
        }
        return execute(httpPost);
    }

    protected String httpPut(String uri, Map<String, String> queryMap) {
        return httpPut(uri, queryMap, null, null);
    }

    protected String httpPut(String uri, String content) {
        return httpPut(uri, null, content, null);
    }

    protected String httpPut(String uri, Map<String, String> queryMap, String content, Map<String, String> headerMap) {
        HttpPut httpPut = new HttpPut(urlPrefix + uri);
        logger.info("HttpClient PUT {}", httpPut.getURI());
        if (queryMap != null) {
            httpPut.setEntity(getHttpEntity(queryMap));
        } else if (content != null) {
            httpPut.setEntity(getHttpEntity(content));
        }
        if (headerMap != null) {
            setHeaders(httpPut, headerMap);
        }
        return execute(httpPut);
    }

    protected String httpDelete(String uri,Map<String, String> queryMap ) {

        return httpDelete(uri, queryMap, null);
    }

    protected String httpDelete(String uri, Map<String, String> queryMap, Map<String, String> headerMap) {
        String url = queryMap == null ? urlPrefix + uri : buildUrl(uri, queryMap);

        HttpDelete httpDelete = new HttpDelete(url);

        logger.info("HttpClient Delete {}",httpDelete.getURI());

        if (headerMap != null) {
            setHeaders(httpDelete, headerMap);
        }

        return execute(httpDelete);
    }

}
