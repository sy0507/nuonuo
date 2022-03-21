package com.example.nuonuo.mapper;

import com.example.nuonuo.pojo.entity.Audio;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface CardMapper extends BaseMapper<Audio> {
    @Select("select * from audio_info order by create_time desc  limit 5")
    List<Audio> getCarouselList();

    @Select("select * from audio_info where status=2 order by good_number desc  limit 4")
    List<Audio> getHotMusicList();


    @Select("select * from audio_info where status = 2 order by watch_number  desc  limit 6")
    List<Audio> getCardList();


}
