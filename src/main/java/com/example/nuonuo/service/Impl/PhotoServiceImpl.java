package com.example.nuonuo.service.Impl;

import com.example.nuonuo.pojo.dto.PhotoDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.PhotoVO;
import com.example.nuonuo.service.PhotoService;

public class PhotoServiceImpl implements PhotoService {
  @Override
  public Object uploadPhoto(PhotoDTO photoDTO, User user) {
    return null;
  }

  @Override
  public void Update(Integer id, User user, PhotoDTO photoDTO) {

  }

  @Override
  public PhotoVO getDetail(String photoId, User user) {
    return null;
  }

  @Override
  public Integer remove(User user) {
    return null;
  }

}
