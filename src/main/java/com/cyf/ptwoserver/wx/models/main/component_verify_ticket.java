package com.cyf.ptwoserver.wx.models.main;

import com.cyf.ptwoserver.wx.models.BaseModel;

public class component_verify_ticket extends BaseModel {

    public component_verify_ticket(String appId, String createTime, String infoType, String componentVerifyTicket) {
        this.appId = appId;
        this.createTime = createTime;
        this.infoType = infoType;
        this.componentVerifyTicket = componentVerifyTicket;
    }

    public String appId;
    public String createTime;
    public String infoType;
    public String componentVerifyTicket;

}
