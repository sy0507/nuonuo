package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.CenterCar;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface CenterCarMapper extends BaseMapper<CenterCar> {

    @Select("select * from center_car where id = #{centerId}")
    List<CenterCar> getById(@Param("center_id") Integer centerId);
}
