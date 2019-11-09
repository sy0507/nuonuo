package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.PostActivityDTO;
import com.example.nuonuo.pojo.entity.Activity;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.ActivityVO;


import java.util.List;


public interface ActivityService {
  Integer insert(PostActivityDTO postActivityDTO, User user);

  List<Activity> getAllList();

  ActivityVO getDetail(Integer activityId, User user);

   int removeActivity(Integer activityId);


  void update(Integer id, PostActivityDTO dto, User user);
}
