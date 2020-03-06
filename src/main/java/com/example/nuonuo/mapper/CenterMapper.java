package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Center;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import javax.persistence.Table;

@Table(name = "center")
public interface CenterMapper extends BaseMapper<Center> {

    @Select("delete  from center where 1=1")
    void deleteAll();

    @Select("select * from center LIMIT 1")
    Center findTop();

    @Select("select * from center where sname=#{sname}")
    Center fingBySname(String sname);
}
