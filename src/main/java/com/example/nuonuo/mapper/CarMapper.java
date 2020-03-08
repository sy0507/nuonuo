package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Car;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface CarMapper extends BaseMapper<Car> {

    @Select("select * from center_car where center_id = #{centerId}")
    List<Car> selectByCenterId(@Param("centerId") Integer CenterId);
}
