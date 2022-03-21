package com.example.nuonuo.service.Impl;

import com.example.nuonuo.mapper.AudioMapper;
import com.example.nuonuo.mapper.CommentMapper;
import com.example.nuonuo.mapper.UserMapper;
import com.example.nuonuo.pojo.dto.CommentDTO;
import com.example.nuonuo.pojo.entity.Audio;
import com.example.nuonuo.pojo.entity.Comment;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.CommentVO;
import com.example.nuonuo.service.CommentService;
import com.example.nuonuo.service.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;
    private final FileService fileService;
    private AudioMapper audioMapper;

    public CommentServiceImpl(CommentMapper commentMapper, UserMapper userMapper, FileService fileService, AudioMapper audioMapper) {
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
        this.fileService = fileService;
        this.audioMapper = audioMapper;
    }

    @Override
    public Integer insert(CommentDTO commentDTO, User user) {
        Comment comment=new Comment();
        System.out.println(commentDTO);
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setUid(user.getUid());
        Date date=new Date();
        Long dateTime=date.getTime();
        comment.setTime(dateTime);
        commentMapper.insertSelective(comment);
        Audio audio=audioMapper.selectByPrimaryKey(commentDTO.getAudioId());
        audio.setCommentNumber(audio.getCommentNumber()+1);
        audioMapper.updateByPrimaryKeySelective(audio);
        int commentId=comment.getId();
        return commentId;
    }

    @Override
    public List<CommentVO> getCommentList(Integer audioId) {
        List<Comment> commentList=commentMapper.getCommentListByAudioId(audioId);
        List<CommentVO> commentVOList=new ArrayList<>();
        for (int i=0;i<commentList.size();i++)
        {
            CommentVO commentVO=new CommentVO();
            BeanUtils.copyProperties(commentList.get(i),commentVO);
            User user=userMapper.selectByPrimaryKey(commentVO.getUid());
            commentVO.setName(user.getName());
            commentVO.setHeadPicUrl(fileService.getUrlById(user.getHeadPicId()));
            commentVOList.add(commentVO);
        }
        return commentVOList;
    }

    @Override
    public Integer on(Integer id) {
        Comment comment=commentMapper.selectByPrimaryKey(id);
        comment.setPointOnNum(comment.getPointOnNum()+1);
        commentMapper.updateByPrimaryKeySelective(comment);
        return comment.getPointOnNum();
    }

    @Override
    public Integer out(Integer id) {
        Comment comment=commentMapper.selectByPrimaryKey(id);
        comment.setPointOutNum(comment.getPointOutNum()+1);
        commentMapper.updateByPrimaryKeySelective(comment);
        return comment.getPointOutNum();
    }
}
