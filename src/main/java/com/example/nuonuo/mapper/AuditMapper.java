package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Audit;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

public interface AuditMapper extends BaseMapper<Audit> {
    @Select("select * from audit_info where audio_id=#{audioId} order by audit_time desc limit 1")
    Audit selectByAId(Integer audioId);
}
