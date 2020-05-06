package com.cyf.ptwoserver.wx.models.main;

import com.cyf.ptwoserver.wx.models.WxBaseModel;

import java.util.List;

public class authorization_info extends WxBaseModel {
    public String authorizer_appid;
    public String authorizer_access_token;
    public int expires_in;
    public String authorizer_refresh_token;
    public List<Object> func_info;
}
