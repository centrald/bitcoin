package com.centrald.bitcoin.utils;

import com.centrald.bitcoin.common.util.RSAUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/**
 * Created by central on 2017/1/11.
 */
@Test(enabled = true)
@ContextConfiguration(locations = {"classpath:bitcoin-service-context.xml"})
public class RSAUtilsTest extends AbstractTestNGSpringContextTests {

    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";
    @Test(priority = 0)
    private void generate() {
        String password = "centrald";

        Map<String,byte[]> keyMap = RSAUtils.generateKeyBytes();

        // 加密
        PublicKey publicKey = RSAUtils.restorePublicKey(keyMap.get(PUBLIC_KEY));

        byte[] encodedText = RSAUtils.RSAEncode(publicKey, password.getBytes());
        System.out.println("RSA encoded: " + Base64.encodeBase64String(encodedText));

        // 解密
        PrivateKey privateKey = RSAUtils.restorePrivateKey(keyMap.get(PRIVATE_KEY));

        System.out.println("RSA decoded: "
                + RSAUtils.RSADecode(privateKey, encodedText));

    }
}
