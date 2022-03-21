package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.*;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.*;

import java.util.List;

public interface UserService {
  void register(UserRegisterDTO dto);

  UserInfoVO login(UserLoginDTO dto);


  void modifyPassword(String oldPassword, String newPassword, User user);



  void resetPassword(UserResetPasswordDTO dto);



  Object updateProfile(Integer uid, PutUserProfileDTO userProfileDTO);

  UserInfoVO getUserInfoVO(Integer id);

  User queryUserByToken(String token);

    void collectAudio(Integer audioId, Integer uid);

  void goodAudio(Integer audioId, Integer uid);

  void watchAudio(Integer audioId);

  void follow(Integer id, Integer uid);

  List<CardVO> getCollectList(Integer id);

  List<FUserVO> getFollowList(Integer id);

  List<FUserVO> getFanList(Integer id);

  List<UAudioVO> getUploadList(Integer id);

  AudioAdminVO getUpload(Integer audioId);

  Object updateAudio(Integer audioId, AudioDTO audioDTO);

  Integer getLoginNumber();
}
