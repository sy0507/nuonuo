package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Distance;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import javax.persistence.Table;

@Table(name = "distance")
public interface DistanceMapper extends BaseMapper<Distance> {

    @Select("delete  from distance where 1=1")
    void deleteAll();
}
