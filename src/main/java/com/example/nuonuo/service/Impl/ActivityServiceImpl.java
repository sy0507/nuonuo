package com.example.nuonuo.service.Impl;

import com.example.nuonuo.pojo.dto.PostActivityDTO;
import com.example.nuonuo.pojo.entity.Activity;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.ActivityVO;
import com.example.nuonuo.service.ActivityService;

import java.util.List;

public class ActivityServiceImpl implements ActivityService {
  @Override
  public Integer insert(PostActivityDTO postActivityDTO, User user) {
    return null;
  }

  @Override
  public List<Activity> getAllList() {
    return null;
  }

  @Override
  public ActivityVO getDetail(Integer activityId, User user) {
    return null;
  }

  @Override
  public int removeActivity(Integer activityId) {
    return 0;
  }

  @Override
  public void update(Integer id, PostActivityDTO dto, User user) {

  }
}
