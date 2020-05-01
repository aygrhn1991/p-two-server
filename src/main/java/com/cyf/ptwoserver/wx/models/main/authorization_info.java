package com.cyf.ptwoserver.wx.models.main;

import com.cyf.ptwoserver.wx.models.BaseModel;

import java.util.List;

public class authorization_info extends BaseModel {
    public String authorizer_appid;
    public String authorizer_access_token;
    public int expires_in;
    public String authorizer_refresh_token;
    public List<Object> func_info;
}
