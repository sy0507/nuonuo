package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.AudioDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.AudioVO;
import com.example.nuonuo.pojo.vo.CardVO;
import com.example.nuonuo.pojo.vo.TypeVO;

import java.util.List;

public interface AudioService {
    List<TypeVO> getTypeList();

    Integer insert(AudioDTO audioDTO, Integer uid);

    AudioVO getDetail(Integer audioId,Integer uid);

    String getAudioType(Integer audioTypeId);

    List<CardVO> getCarouselList();

    List<CardVO> getHotMusicList();

    List<CardVO> getCardList();
}
