package com.cyf.ptwoserver.wx.mapper;

import com.cyf.ptwoserver.wx.models.main.authorizer_info;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface authorizer_info_mapper {

    @Insert("insert into " +
            "wx_authorizer_info(authorizer_appid,nick_name,head_img,user_name,principal_name,alias,qrcode_url,systime) " +
            "values(#{authorizer_appid},#{nick_name},#{head_img},#{user_name},#{principal_name},#{alias},#{qrcode_url},#{systime})")
    int insert(authorizer_info info);

    @Select("select * from wx_authorizer_info where authorizer_appid=#{appid}")
    authorizer_info select(String appid);

}
