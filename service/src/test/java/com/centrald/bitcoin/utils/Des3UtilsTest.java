package com.centrald.bitcoin.utils;

import com.centrald.bitcoin.common.util.DES3Utils;
import com.centrald.bitcoin.common.util.DESUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by central on 2017/1/11.
 */

@Test(enabled = true)
@ContextConfiguration(locations = {"classpath:bitcoin-service-context.xml"})
public class Des3UtilsTest extends AbstractTestNGSpringContextTests{

   @Test(priority = 0)
    private void testDes () throws Exception {

       byte[] key = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7".getBytes();
       byte[] keyiv = { 1, 2, 3, 4, 5, 6, 7, 8 };
       byte[] data = "centrald".getBytes("UTF-8");

       byte[] str5 = DES3Utils.des3EncodeCBC(key, keyiv, data);
       System.out.println(str5);
       //System.out.println(new sun.misc.BASE64Encoder().encode(str5));

       byte[] str6 = DES3Utils.des3DecodeCBC(key, keyiv, str5);
       System.out.println(new String(str6, "UTF-8"));
   }
}
