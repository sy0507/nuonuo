package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
  @Select("select * from user where phone = #{phone}")
  User findUserByPhone(String phone);

  @Select("SELECT * FROM user where email = #{email}")
  List<User> findUserByEmail(String email);

  @Select("select * from user where name like '%' #{condition} '%' ")
  List<User> getAuthor(String condition);

  @Select("select * from user where uname =#{uname}")
  User selectByUname(String uname);



}
