package com.cyf.ptwoserver.wx.util;

import com.cyf.ptwoserver.wx.mapper.main.authorization_info_mapper;
import com.cyf.ptwoserver.wx.mapper.main.component_access_token_mapper;
import com.cyf.ptwoserver.wx.mapper.main.component_verify_ticket_mapper;
import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private authorization_info_mapper authorization_info_mapper;

    public String get_component_access_token() {
        component_access_token token = this.component_access_token_mapper.select();
        if (token == null || (new Date().getTime() - token.systime.getTime()) > (token.expires_in * 1000) / 2) {
            Map map = new HashMap<>();
            map.put("component_appid", wxConfig.appId);
            map.put("component_appsecret", wxConfig.appSecret);
            map.put("component_verify_ticket", component_verify_ticket_mapper.select().componentVerifyTicket);
            component_access_token cat = new RestTemplate().postForObject("https://api.weixin.qq.com/cgi-bin/component/api_component_token", map, component_access_token.class);
            logger.info(String.format("component_access_token请求结果：%s", new Gson().toJson(cat)));
            cat.systime = new Date();
            int count = this.component_access_token_mapper.insert(cat);
            logger.info(String.format("component_access_token存储结果：%s", count));
            return cat.component_access_token;
        } else {
            return token.component_access_token;
        }
    }

    public String get_pre_auth_code() {
        Map map = new HashMap<>();
        map.put("component_appid", wxConfig.appId);
        pre_auth_code pac = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=%s", this.get_component_access_token()), map, pre_auth_code.class);
        logger.info(String.format("pre_auth_code请求结果：%s", new Gson().toJson(pac)));
        return pac.pre_auth_code;
    }

    public String get_auth_code_url_pc(String redirect_uri) {
        return String.format("https://mp.weixin.qq.com/cgi-bin/componentloginpage?auth_type=3&component_appid=%s&pre_auth_code=%s&redirect_uri=%s", wxConfig.appId, this.get_pre_auth_code(), redirect_uri);
    }

    public String get_auth_code_url_mobile(String redirect_uri) {
        return String.format("https://mp.weixin.qq.com/safe/bindcomponent?action=bindcomponent&auth_type=3&no_scan=1&component_appid=%s&pre_auth_code=%s&redirect_uri=%s#wechat_redirect", wxConfig.appId, this.get_pre_auth_code(), redirect_uri);
    }

    public auth get_authorizer_info(String authorizer_appid) {
        Map map = new HashMap();
        map.put("component_appid", this.wxConfig.appId);
        map.put("authorizer_appid", authorizer_appid);
        auth auth = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=%s", this.get_component_access_token()), map, auth.class);
        logger.info(String.format("authorizer_info请求结果：%s", new Gson().toJson(auth)));
        auth.authorizer_info.authorizer_appid = auth.authorization_info.authorizer_appid;
        return auth;
    }

    public String get_authorizer_access_token(String authorizer_appid) {
        authorization_info ai = this.authorization_info_mapper.select(authorizer_appid);
        if (new Date().getTime() - ai.systime.getTime() > (ai.expires_in * 1000) / 2) {
            Map map = new HashMap<>();
            map.put("component_appid", wxConfig.appId);
            map.put("authorizer_appid", authorizer_appid);
            map.put("authorizer_refresh_token", ai.authorizer_refresh_token);
            authorization_info ai2 = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=%s", this.get_component_access_token()), map, authorization_info.class);
            logger.info(String.format("authorizer_access_token刷新结果：%s", new Gson().toJson(ai2)));
            ai2.authorizer_appid = authorizer_appid;
            ai2.systime = new Date();
            int count = this.authorization_info_mapper.update(ai2);
            logger.info(String.format("authorizer_access_token存储结果：%s", count));
            return ai2.authorizer_access_token;
        } else {
            return ai.authorizer_access_token;
        }
    }

    public List<auth_info> get_authorizer_list(int offset, int count) {
        Map map = new HashMap();
        map.put("component_appid", this.wxConfig.appId);
        map.put("offset", offset);
        map.put("count", count);
        auth_info_list ail = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_list?component_access_token=%s", this.get_component_access_token()), map, auth_info_list.class);
        logger.info(String.format("authorizer_list请求结果：%s", new Gson().toJson(ail)));
        return ail.list;
    }
}
