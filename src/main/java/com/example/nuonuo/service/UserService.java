package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.PutUserProfileDTO;
import com.example.nuonuo.pojo.dto.UserLoginDTO;
import com.example.nuonuo.pojo.dto.UserRegisterDTO;
import com.example.nuonuo.pojo.dto.UserResetPasswordDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.ActivityVO;
import com.example.nuonuo.pojo.vo.UserInfoVO;

import java.util.List;

public interface UserService {
  void register(UserRegisterDTO dto);

  UserInfoVO login(UserLoginDTO dto);


  void modifyPassword(String oldPassword, String newPassword, User user);



  void resetPassword(UserResetPasswordDTO dto);



  Object updateProfile(Integer uid, PutUserProfileDTO userProfileDTO);

  UserInfoVO getUserInfoVO(User user);

  void collectActivity(Integer activityId, User user);

  List<ActivityVO> acGetList(User user);

  User queryUserByToken(String token);

}
