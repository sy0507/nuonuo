package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.PhotoDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.PhotoVO;

public interface PhotoService {
  Object uploadPhoto(PhotoDTO photoDTO, User user);

  void Update(Integer id, User user, PhotoDTO photoDTO);

  PhotoVO getDetail(String photoId, User user);


  Integer remove(User user);

}
