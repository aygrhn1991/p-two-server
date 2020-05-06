package com.cyf.ptwoserver.wx.models.main;

import com.cyf.ptwoserver.wx.models.WxBaseModel;

import java.util.Date;

public class auth_event extends WxBaseModel {

    public auth_event(String appId, Date createTime, String infoType, String authorizerAppid, String authorizationCode, Date authorizationCodeExpiredTime, String preAuthCode) {
        this.appId = appId;
        this.createTime = createTime;
        this.infoType = infoType;
        this.authorizerAppid = authorizerAppid;
        this.authorizationCode = authorizationCode;
        this.authorizationCodeExpiredTime = authorizationCodeExpiredTime;
        this.preAuthCode = preAuthCode;
    }

    public String appId;
    public Date createTime;
    public String infoType;
    public String authorizerAppid;
    public String authorizationCode;
    public Date authorizationCodeExpiredTime;
    public String preAuthCode;
}
