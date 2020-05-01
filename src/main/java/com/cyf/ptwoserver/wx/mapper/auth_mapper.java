package com.cyf.ptwoserver.wx.mapper;

import com.cyf.ptwoserver.wx.models.main.authorization_info;
import com.cyf.ptwoserver.wx.models.main.authorizer_info;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface auth_mapper {

    @Insert("insert into wx_auth(authorizer_appid,authorizer_access_token,expires_in,authorizer_refresh_token,systime) values(#{authorizer_appid},#{authorizer_access_token},#{expires_in},#{authorizer_refresh_token},#{systime})")
    int insert_authorization_info(authorization_info info);

    @Select("select * from wx_auth where authorizer_appid=#{appid}")
    authorization_info select_authorization_info(String appid);

    @Update("update wx_auth set nick_name=#{nick_name},head_img=#{head_img},user_name=#{user_name},principal_name=#{principal_name},alias=#{alias},qrcode_url=#{qrcode_url}")
    int update_authorizer_info(authorizer_info info);

    @Select("select * from wx_auth where authorizer_appid=#{appid}")
    authorizer_info select_authorizer_info(String appid);

}
