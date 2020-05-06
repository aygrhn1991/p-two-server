package com.cyf.ptwoserver.wx.models.sub;

import com.cyf.ptwoserver.wx.models.WxBaseModel;

import java.util.List;

public class auth_user_info extends WxBaseModel {
    public String openid;
    public String nickname;
    public int sex;
    public String province;
    public String city;
    public String country;
    public String headimgurl;
    public List<String> privilege;
    public String unionid;
}
