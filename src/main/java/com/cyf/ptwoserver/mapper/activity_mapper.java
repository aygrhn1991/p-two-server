package com.cyf.ptwoserver.mapper;

import com.cyf.ptwoserver.models.activity_user;
import org.apache.ibatis.annotations.*;

@Mapper
public interface activity_mapper {

    @Insert("insert into " +
            "activity_user(appid,activity_id,organizer_unionid,member_unionid,subscribe,systime) " +
            "values(#{appid},#{activity_id},#{organizer_unionid},#{member_unionid},#{subscribe},#{systime})")
    int insert_activity_user(activity_user activity_user);

    @Select("select * from activity_user where appid=#{appid} and activity_id=#{activity_id} and organizer_unionid=#{organizer_unionid}")
    activity_user select_activity_user_organizer(String appid, int activity_id, String organizer_unionid);

    @Select("select * from activity_user where appid=#{appid} and activity_id=#{activity_id} and member_unionid=#{member_unionid}")
    activity_user select_activity_user_member(String appid, int activity_id, String member_unionid);

    @Update("update activity_user set subscribe=#{subscribe} where appid=#{appid} and activity_id=#{activity_id} and member_unionid=#{member_unionid}")
    int update_activity_user_member(int subscribe, String appid, int activity_id, String member_unionid);

    @Delete("delete from activity_user where appid=#{appid} and activity_id=#{activity_id} and member_unionid=#{member_unionid}")
    int delete_activity_user_member(String appid, int activity_id, String member_unionid);

    @Select("select count(*) from activity_user where appid=#{appid} and activity_id=#{activity_id} and organizer_unionid=#{organizer_unionid} and subscribe=#{subscribe}")
    int count(String appid, int activity_id, String organizer_unionid, int subscribe);
}
