package com.cyf.ptwoserver.wx.models.sub;

import com.cyf.ptwoserver.wx.models.WxBaseModel;

public class auth_access_token extends WxBaseModel {
    public String access_token;
    public int expires_in;
    public String refresh_token;
    public String openid;
    public String scope;
}
