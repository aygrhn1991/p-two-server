package com.cyf.ptwoserver.wx.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxConfig {

    @Value("${wx.appId}")
    public String appId;

    @Value("${wx.appSecret}")
    public String appSecret;

    @Value("${wx.token}")
    public String token;

    @Value("${wx.encodingAesKey}")
    public String encodingAesKey;
}
