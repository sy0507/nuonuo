package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.CollectInfo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface CollectInfoMapper extends BaseMapper<CollectInfo> {
    @Select("select * from collect_info where uid=#{id}")
    List<CollectInfo> getCollectInfoByUid(Integer id);
}
