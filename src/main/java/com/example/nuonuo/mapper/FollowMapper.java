package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Follow;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface FollowMapper extends BaseMapper<Follow> {
    @Select("select * from follow where uid=#{id}")
    List<Follow> getFollowByUid(Integer id);

    @Select("select * from follow where follow_id=#{id}")
    List<Follow> getFanByUid(Integer id);
}