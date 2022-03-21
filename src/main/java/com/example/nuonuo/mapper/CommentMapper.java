package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Comment;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select * from comment where audio_id=#{audioId}")
    List<Comment> getCommentListByAudioId(Integer audioId);

//    @Select("select count (0) from comment where audio_id=#{audioId}")
//    Integer getCount(Integer audioId);
}
