package com.cyf.ptwoserver.models;

public class app_user extends BaseModel {
    public app_user(String appid, String openid, String unionid) {
        this.appid = appid;
        this.openid = openid;
        this.unionid = unionid;
    }

    public String appid;
    public String openid;
    public String unionid;
}
