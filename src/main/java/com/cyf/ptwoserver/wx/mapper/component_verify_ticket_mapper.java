package com.cyf.ptwoserver.wx.mapper;

import com.cyf.ptwoserver.wx.models.main.component_verify_ticket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface component_verify_ticket_mapper {
    @Insert("insert into component_verify_ticket(appId,createTime,infoType,componentVerifyTicket) values(#{appId},#{createTime},#{infoType},#{componentVerifyTicket})")
    int insert(component_verify_ticket platform);
}
