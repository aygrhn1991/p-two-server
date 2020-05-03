package com.cyf.ptwoserver.wx.mapper;

import com.cyf.ptwoserver.wx.models.main.authorization_info;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface authorization_info_mapper {

    @Insert("insert into " +
            "wx_authorization_info(authorizer_appid,authorizer_access_token,expires_in,authorizer_refresh_token,systime) " +
            "values(#{authorizer_appid},#{authorizer_access_token},#{expires_in},#{authorizer_refresh_token},#{systime})")
    int insert(authorization_info info);

    @Select("select * from wx_authorization_info where authorizer_appid=#{appid}")
    authorization_info select(String appid);

}
