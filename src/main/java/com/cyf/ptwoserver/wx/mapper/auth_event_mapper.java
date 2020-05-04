package com.cyf.ptwoserver.wx.mapper;

import com.cyf.ptwoserver.wx.models.main.auth_event;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface auth_event_mapper {
    @Insert("insert into " +
            "wx_auth_event(appId,createTime,infoType,authorizerAppid,authorizationCode,authorizationCodeExpiredTime,preAuthCode,systime) " +
            "values(#{appId},#{createTime},#{infoType},#{authorizerAppid},#{authorizationCode},#{authorizationCodeExpiredTime},#{preAuthCode},#{systime})")
    int insert(auth_event ticket);
}
