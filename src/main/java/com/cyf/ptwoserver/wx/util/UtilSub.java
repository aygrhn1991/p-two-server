package com.cyf.ptwoserver.wx.util;

import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.sub.source_type;
import com.cyf.ptwoserver.wx.models.sub.user_info;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class UtilSub {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WxConfig wxConfig;

    @Autowired
    private UtilMain utilMain;

    public String get_auth_code_url(String appid, String redirect_uri, String scope, String state) {
        return String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&component_appid=%s#wechat_redirect", appid, redirect_uri, scope, state, this.wxConfig.appId);
    }

    public user_info get_user_info(String appid, String openid) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String user_info_str = restTemplate.getForObject(String.format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN", this.utilMain.get_authorizer_access_token(appid), openid), String.class);
        logger.info(String.format("user_info请求结果：%s", user_info_str));
        user_info ui = new Gson().fromJson(user_info_str, user_info.class);
        return ui;
    }

    public void send_custom_service_message_text(String appid, String touser, String content) {
        Map m = new HashMap();
        m.put("content", content);
        Map map = new HashMap();
        map.put("touser", touser);
        map.put("msgtype", "text");
        map.put("text", m);
        String result = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s", this.utilMain.get_authorizer_access_token(appid)), map, String.class);
        logger.info(String.format("客服消息(text)发送结果：%s", result));
    }

    public void send_custom_service_message_image(String appid, String touser, String mediaId) {
        Map m = new HashMap();
        m.put("media_id", mediaId);
        Map map = new HashMap();
        map.put("touser", touser);
        map.put("msgtype", "image");
        map.put("image", m);
        String result = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s", this.utilMain.get_authorizer_access_token(appid)), map, String.class);
        logger.info(String.format("客服消息(image)发送结果：%s", result));
    }

    public void upload_temp_source(String appid) {
        String result = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s", this.utilMain.get_authorizer_access_token(appid)), source_type.image, String.class);
        logger.info(String.format("临时素材(图片)上传结果：%s", result));
    }

}
