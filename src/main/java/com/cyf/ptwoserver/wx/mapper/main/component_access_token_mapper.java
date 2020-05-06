package com.cyf.ptwoserver.wx.mapper.main;

import com.cyf.ptwoserver.wx.models.main.component_access_token;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface component_access_token_mapper {

    @Insert("insert into " +
            "wx_component_access_token(component_access_token,expires_in,systime) " +
            "values(#{component_access_token},#{expires_in},#{systime})")
    int insert(component_access_token token);

    @Select("select * from wx_component_access_token order by systime desc limit 1")
    component_access_token select();

}
