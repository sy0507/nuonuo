package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.MessageDTO;
import com.example.nuonuo.pojo.entity.User;

public interface MessageService {
  Object insert(MessageDTO messageDTO, User user);

  Object getAll(User user);

}
