package com.cyf.ptwoserver.wx.util;

import com.cyf.ptwoserver.util.UtilDate;
import com.cyf.ptwoserver.wx.mapper.component_access_token_mapper;
import com.cyf.ptwoserver.wx.mapper.component_verify_ticket_mapper;
import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.component_access_token;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class UtilMain {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WxConfig wxConfig;

    @Autowired
    private component_verify_ticket_mapper component_verify_ticket_mapper;

    @Autowired
    private component_access_token_mapper component_access_token_mapper;

    public String get_component_access_token() {
        component_access_token token = this.component_access_token_mapper.select();
        if (token == null || (new Date().getTime() - token.systime.getTime()) > (token.expires_in * 1000) / 2) {
            Map map = new HashMap<>();
            map.put("component_appid", wxConfig.appId);
            map.put("component_appsecret", wxConfig.appSecret);
            map.put("component_verify_ticket", component_verify_ticket_mapper.select().componentVerifyTicket);
            RestTemplate restTemplate = new RestTemplate();
            component_access_token cat = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/component/api_component_token", map, component_access_token.class);
            if (cat.errcode != null) {
                logger.info(String.format("component_access_token请求结果（失败）：%s", cat.errmsg));
            } else {
                logger.info(String.format("component_access_token请求结果（成功）：%s", cat.component_access_token));
                cat.systime = new Date();
                int count = this.component_access_token_mapper.insert(cat);
                logger.info(String.format("component_access_token存储结果：%s", count));
            }
            return cat.component_access_token;
        } else {
            return token.component_access_token;
        }
    }


}
