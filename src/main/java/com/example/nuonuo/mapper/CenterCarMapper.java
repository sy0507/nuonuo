package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.CenterCar;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface CenterCarMapper extends BaseMapper<CenterCar> {

    @Select("select * from center_car where center_id = #{center_id}")
    List<CenterCar> getById(@Param("center_id") Integer centerId);


}
