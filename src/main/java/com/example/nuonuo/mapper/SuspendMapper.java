package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Suspend;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface SuspendMapper extends BaseMapper<Suspend> {
    @Select("select * from user_suspend where uid=#{uid} order by suspend_start_time desc limit 1 ")
    Suspend getInfo(Integer uid);

    @Select("select * from user_suspend where uid=#{uid}")
    List<Suspend> selectByUid(Integer uid);
}
