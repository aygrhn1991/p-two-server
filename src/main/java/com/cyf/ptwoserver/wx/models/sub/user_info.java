package com.cyf.ptwoserver.wx.models.sub;

import com.cyf.ptwoserver.wx.models.WxBaseModel;

import java.util.List;

public class user_info extends WxBaseModel {
    public int subscribe;
    public String openid;
    public String nickname;
    public int sex;
    public String language;
    public String city;
    public String province;
    public String country;
    public String headimgurl;
    public long subscribe_time;
    public String unionid;
    public String remark;
    public int groupid;
    public List<Integer> tagid_list;
    public String subscribe_scene;
    public int qr_scene;
    public String qr_scene_str;
}
