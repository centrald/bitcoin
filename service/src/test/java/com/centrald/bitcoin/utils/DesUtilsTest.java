package com.centrald.bitcoin.utils;

import com.centrald.bitcoin.common.util.DESUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by central on 2017/1/11.
 */

@Test(enabled = true)
@ContextConfiguration(locations = {"classpath:bitcoin-service-context.xml"})
public class DesUtilsTest extends AbstractTestNGSpringContextTests{

   @Test(priority = 0)
    private void testDes() {
       String str = "centrald";
       //密码，长度要是8的倍数
       String password = "9588021";

       byte[] result = DESUtils.encrypt(str.getBytes(),password);
       System.out.println("加密后："+new String(result));
       //直接将如上内容解密
       try {
           byte[] decryResult = DESUtils.decrypt(result, password);
           System.out.println("解密后："+new String(decryResult));
       } catch (Exception e1) {
           e1.printStackTrace();
       }
   }
}
