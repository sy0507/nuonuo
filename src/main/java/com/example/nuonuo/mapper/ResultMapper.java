package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Result;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

public interface ResultMapper extends BaseMapper<Result> {
    @Select("delete  from result where 1=1")
    void deleteAll();
}
