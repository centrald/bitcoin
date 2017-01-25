package com.centrald.bitcoin.web.controller;

import com.centrald.bitcoin.common.domain.constant.BitcoinConstant;
import com.centrald.bitcoin.common.domain.entity.Ticker;
import com.centrald.bitcoin.common.util.Result;
import com.centrald.bitcoin.component.CHBTCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by central on 2017/1/5.
 */
@Controller
@RequestMapping(path = "/marketReports", produces = "application/json; charset=utf-8")
public class MarketReportController {

    @Autowired
    private CHBTCService chbtcService;

    /**
     *CHBTC 实时买卖价格
     */
    @RequestMapping(path = "/chbtc/getBtcTicker", method = RequestMethod.GET)
    @ResponseBody
    public String getBtcTicker() {

        Ticker btcTicker = chbtcService.getTicker(BitcoinConstant.CHBTC_BTC);

        return Result.success(btcTicker);
    }

    @RequestMapping(path = "/chbtc/getLtcTicker", method = RequestMethod.GET)
    @ResponseBody
    public String getLtcTicker() {

        Ticker ltcTicker = chbtcService.getTicker(BitcoinConstant.CHBTC_LTC);

        return Result.success(ltcTicker);
    }

    @RequestMapping(path = "/chbtc/getEthTicker", method = RequestMethod.GET)
    @ResponseBody
    public String getEthTicker() {

        Ticker ethTicker = chbtcService.getTicker(BitcoinConstant.CHBTC_ETH);

        return Result.success(ethTicker);
    }

    @RequestMapping(path = "/chbtc/getEtcTicker", method = RequestMethod.GET)
    @ResponseBody
    public String getEtcTicker() {

        Ticker EtcTicker = chbtcService.getTicker(BitcoinConstant.CHBTC_ETC);

        return Result.success(EtcTicker);
    }

    /**
     * 实时行情
     */
    @RequestMapping(path = "test", method = RequestMethod.POST)
    @ResponseBody
    public String getTest() {


    }
}
