package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Center;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import javax.persistence.Table;

@Table(name = "center")
public interface CenterMapper extends BaseMapper<Center> {


}
