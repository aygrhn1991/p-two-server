package com.cyf.ptwoserver.wx.mapper.sub;

import com.cyf.ptwoserver.wx.models.sub.auth_user_info;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface auth_user_info_mapper {

    @Insert("insert into " +
            "wx_auth_user_info(unionid,openid,nickname,sex,province,city,country,headimgurl,systime) " +
            "values(#{unionid},#{openid},#{nickname},#{sex},#{province},#{city},#{country},#{headimgurl},#{systime})")
    int insert(auth_user_info info);

    @Select("select * from wx_auth_user_info where unionid=#{unionid}")
    auth_user_info select_union(String unionid);

    @Select("select * from wx_auth_user_info where openid=#{openid}")
    auth_user_info select_openid(String openid);

}
