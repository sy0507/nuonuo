package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.dto.SearchDTO;
import com.example.nuonuo.pojo.entity.Audio;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface AudioMapper extends BaseMapper<Audio> {
    @Select("select * from audio_info where audio_name like '%' #{condition} '%' ")
    List<Audio> getLAudio(String condition);

    @Select("select * from audio_info where uid=#{id}")
    List<Audio> selectByUid(Integer id);

//    @Select("select * from audio_info where audio_id like ('%' #{audioId} '%') and audio_name like ('%' #{audioName} '%' ) and uid like ('%' #{uid} '%')")
//    @Select("select *  from audio_info where concat('audio_id','audio_name','uid')  like '%' #{audioId,audioName,uid} '%'")
//    @Select("select * from audio_info where instr(audio_info,'audioId')>0 and instr(audio_name,'audioName')>0 and instr(uid,'uid')>0")
     @Select("select * from audio_info where audio_id like concat ('%', #{audioIdStr},'%') and audio_name like concat ('%',#{audioName},'%') and uid like concat ('%',#{uidStr},'%') and audio_type_id like concat ('%',#{audioTypeStr},'%')")
    List<Audio> getAdminAudio(String audioIdStr, String audioName, String uidStr,String audioTypeStr);
}
