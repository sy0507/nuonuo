package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.CommentDTO;
import com.example.nuonuo.pojo.entity.Comment;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.CommentVO;

import java.util.List;

public interface CommentService {
    Integer insert(CommentDTO commentDTO, User user);

    List<CommentVO> getCommentList(Integer audioId);

    Integer on(Integer id);

    Integer out(Integer id);
}
