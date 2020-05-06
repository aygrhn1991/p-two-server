package com.cyf.ptwoserver.mapper;

import com.cyf.ptwoserver.models.activity_user;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface activity_mapper {

    @Insert("insert into " +
            "activity_user(activity_id,organizer_unionid,member_unionid,subscribe,systime) " +
            "values(#{activity_id},#{organizer_unionid},#{member_unionid},#{subscribe},#{systime})")
    int insert_activity_user(activity_user activity_user);

    @Select("select * from activity_user where activity_id=#{activity_id} and organizer_unionid=#{organizer_unionid}")
    activity_user select_activity_user_organizer(int activity_id, String organizer_unionid);

    @Select("select * from activity_user where activity_id=#{activity_id} and member_unionid=#{member_unionid}")
    activity_user select_activity_user_member(int activity_id, String member_unionid);

    @Delete("delete from activity_user where activity_id=#{activity_id} and member_unionid=#{member_unionid}")
    int delete_activity_user_member(int activity_id, String member_unionid);

}
