package com.cyf.ptwoserver.wx.util;

import com.cyf.ptwoserver.wx.mapper.component_verify_ticket_mapper;
import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.component_access_token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class UtilMain {

    @Autowired
    WxConfig wxConfig;

    @Autowired
    private component_verify_ticket_mapper component_verify_ticket_mapper;

    public String get_component_access_token() {
        Map map = new HashMap<>();
        map.put("component_appid", wxConfig.appId);
        map.put("component_appsecret", wxConfig.appSecret);
        map.put("component_verify_ticket", component_verify_ticket_mapper.select().componentVerifyTicket);
        RestTemplate restTemplate = new RestTemplate();
        component_access_token result = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/component/api_component_token", map, component_access_token.class);
        return result.component_access_token;
    }

}
