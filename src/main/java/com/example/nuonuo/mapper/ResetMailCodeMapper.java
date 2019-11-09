package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.ResetMailCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface ResetMailCodeMapper extends BaseMapper<ResetMailCode> {


  @Select("SELECT * FROM reset_mail_code where email=#{email}")
  ResetMailCode findByEmail(String email);

  @Select("DELETE FROM reset_mail_code WHERE TIMESTAMPDIFF(MINUTE,time,now())>5")
  void deleteOutOfTime();
}
