package com.example.nuonuo.mapper;


import com.example.nuonuo.pojo.entity.Pay;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface PayMapper extends BaseMapper<Pay> {

    @Select("select * from  pay where uid=#{uid}")
    List<Pay> selectByuid(Integer uid);
}
