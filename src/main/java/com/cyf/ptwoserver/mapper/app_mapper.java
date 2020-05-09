package com.cyf.ptwoserver.mapper;

import com.cyf.ptwoserver.models.app_activity;
import com.cyf.ptwoserver.models.app_user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface app_mapper {

    @Insert("insert into " +
            "app_user(appid,openid,unionid,systime) " +
            "values(#{appid},#{openid},#{unionid},#{systime})")
    int insert_app_user(app_user app_user);

    @Select("select * from app_user where appid=#{appid} and openid=#{openid}")
    app_user select_app_user_appid_openid(String appid, String openid);

    @Select("select * from app_user where appid=#{appid} and unionid=#{unionid}")
    app_user select_app_user_appid_unionid(String appid, String unionid);

    @Select("select * from app_activity where appid=#{appid}")
    app_activity select_app_activity(String appid);

}
