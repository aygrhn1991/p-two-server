package com.cyf.ptwoserver.models;

public class activity_user extends BaseModel {
    public activity_user(String appid, int activity_id, String organizer_unionid, String member_unionid, int subscribe) {
        this.appid = appid;
        this.activity_id = activity_id;
        this.organizer_unionid = organizer_unionid;
        this.member_unionid = member_unionid;
        this.subscribe = subscribe;
    }

    public String appid;
    public int activity_id;
    public String organizer_unionid;
    public String member_unionid;
    public int subscribe;
}
