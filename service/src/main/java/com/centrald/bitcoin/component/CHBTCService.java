package com.centrald.bitcoin.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centrald.bitcoin.common.domain.entity.Ticker;
import com.centrald.bitcoin.common.exception.CHBTCServiceException;
import com.centrald.bitcoin.common.util.DateUtils;
import com.centrald.bitcoin.common.util.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jiayan on 2017/1/4.
 */
@Service
public class CHBTCService extends HttpBaseService{
    private final static Logger logger = LoggerFactory.getLogger(CHBTCService.class);

    public CHBTCService() {
        super(PropertyUtils.getString("chbtc.url"));
    }
    /**
     *kline
     **/
    public Map<String,Object> getKline(String currency) {
        String uri = "/data/v1/kline";

        Map<String,String> queryMap = new HashMap<>();

        queryMap.put("currency",currency);

        String response = chbtcGet(uri,queryMap);

        System.out.println(response);
        //return new HashMap<>();
        Map<String,List<Object>> kLineMap = new HashMap<>();

        JSONArray data = JSON.parseObject(response).getJSONArray("data");

        List<Date> date = new ArrayList<>();
        for(int i=0;i<data.size();i++) {
            JSONArray jsonArray = data.getJSONArray(i);
            date.add(DateUtils.timesParseDate(jsonArray.getLong(0)));

        }
        return new HashMap<>();
    }

    /**
     *ticker
     * @return
     */
    public Ticker getTicker(String currency) {
        String uri = "/data/v1/ticker";

        Map<String,String> queryMap = new HashMap<>();

        queryMap.put("currency",currency);

        String response = chbtcGet(uri,queryMap);

        JSONObject jsonObject = JSON.parseObject(response);

        Long times = jsonObject.getLong("date");
        JSONObject ticker = jsonObject.getJSONObject("ticker");

        Ticker tickerObj = new Ticker();

        tickerObj.setDate(DateUtils.timesParseDate(times));
        tickerObj.setBuy(ticker.getDouble("buy"));
        tickerObj.setHigh(ticker.getDouble("high"));
        tickerObj.setLast(ticker.getDouble("last"));
        tickerObj.setLow(ticker.getDouble("low"));
        tickerObj.setSell(ticker.getDouble("sell"));
        tickerObj.setVol(ticker.getDouble("vol"));

        return tickerObj;
    }

    /**
     *chbtcGet
     */
    private String chbtcGet(String uri) {

        String response = httpGet(uri);

        if (StringUtils.isEmpty(response)) {
            throw new CHBTCServiceException("CHBTC接口访问失败,返回值为空值");
        }
        return response;
    }
    /**
     *chbtcGet
     */
    private String chbtcGet(String uri,Map queryMap) {

        String response = httpGet(uri,queryMap);

        if (StringUtils.isEmpty(response)) {
            throw new CHBTCServiceException("CHBTC接口访问失败,返回值为空值");
        }
        return response;
    }
}
