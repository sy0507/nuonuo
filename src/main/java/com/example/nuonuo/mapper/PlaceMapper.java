package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Place;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface PlaceMapper extends BaseMapper<Place> {
    @Select("select * from place where center_id=#{center_id}")
    List<Place> selectByCenterId(Integer centerId);
}
