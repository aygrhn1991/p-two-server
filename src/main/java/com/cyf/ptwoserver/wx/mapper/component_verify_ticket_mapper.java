package com.cyf.ptwoserver.wx.mapper;

import com.cyf.ptwoserver.wx.models.main.component_verify_ticket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface component_verify_ticket_mapper {

    @Insert("insert into wx_component_verify_ticket(appId,createTime,infoType,componentVerifyTicket,systime) values(#{appId},#{createTime},#{infoType},#{componentVerifyTicket},#{systime})")
    int insert(component_verify_ticket ticket);

    @Select("select * from wx_component_verify_ticket order by createTime desc limit 1")
    component_verify_ticket select();

}
