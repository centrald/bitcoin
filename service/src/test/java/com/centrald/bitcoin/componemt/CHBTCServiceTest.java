package com.centrald.bitcoin.componemt;

import com.centrald.bitcoin.common.domain.constant.BitcoinConstant;
import com.centrald.bitcoin.common.domain.entity.Ticker;
import com.centrald.bitcoin.component.CHBTCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by jiayan on 2017/1/4.
 */
@Test(enabled = true)
@ContextConfiguration(locations = {"classpath:bitcoin-service-context.xml"})
public class CHBTCServiceTest extends AbstractTestNGSpringContextTests{

    @Autowired
    private CHBTCService chbtcService;
    @Test(priority = 1)
    private void getTicker() {

        Ticker ticker = chbtcService.getTicker(BitcoinConstant.CHBTC_BTC);
        System.out.println(ticker);

    }

    @Test(priority = 2)
    private void getKline() {

        chbtcService.getKline(BitcoinConstant.CHBTC_BTC);

    }
}
