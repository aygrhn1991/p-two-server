package com.cyf.ptwoserver.wx.models.main;

import com.cyf.ptwoserver.wx.models.BaseModel;

import java.util.Date;

public class component_verify_ticket extends BaseModel {
    public component_verify_ticket(String appId, Date createTime, String infoType, String componentVerifyTicket) {
        this.appId = appId;
        this.createTime = createTime;
        this.infoType = infoType;
        this.componentVerifyTicket = componentVerifyTicket;
    }

    public String appId;
    public Date createTime;
    public String infoType;
    public String componentVerifyTicket;
}
