package com.cyf.ptwoserver.wx.util;

import com.cyf.ptwoserver.wx.mapper.component_access_token_mapper;
import com.cyf.ptwoserver.wx.mapper.component_verify_ticket_mapper;
import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.component_access_token;
import com.cyf.ptwoserver.wx.models.main.pre_auth_code;
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

    public String get_pre_auth_code() {
        Map map = new HashMap<>();
        map.put("component_appid", wxConfig.appId);
        RestTemplate restTemplate = new RestTemplate();
        pre_auth_code pac = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=" + this.get_component_access_token(), map, pre_auth_code.class);
        if (pac.errcode != null) {
            logger.info(String.format("pre_auth_code请求结果（失败）：%s", pac.errmsg));
        } else {
            logger.info(String.format("pre_auth_code请求结果（成功）：%s", pac.pre_auth_code));
        }
        return pac.pre_auth_code;
    }

    public String get_auth_code_url_pc(String redirect_uri) {
        return String.format("https://mp.weixin.qq.com/cgi-bin/componentloginpage?auth_type=3&component_appid=%s&pre_auth_code=%s&redirect_uri=%s", wxConfig.appId, this.get_pre_auth_code(), redirect_uri);
    }

    public String get_auth_code_url_mobile(String redirect_uri) {
        return String.format("https://mp.weixin.qq.com/safe/bindcomponent?action=bindcomponent&auth_type=3&no_scan=1&component_appid=%s&pre_auth_code=%s&redirect_uri=%s#wechat_redirect", wxConfig.appId, this.get_pre_auth_code(), redirect_uri);
    }

}
