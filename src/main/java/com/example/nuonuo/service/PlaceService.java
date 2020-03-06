package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.PlaceDTO;
import com.example.nuonuo.pojo.vo.PlaceVO;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface PlaceService {

    void add(PlaceDTO placeDTO);


    List<PlaceVO> getplaceInfoVo(Integer centerId);


    Object getPresentInfoVo(Integer placeId);


    Object modify(Integer placeId, PlaceDTO placeDTO);



    void deleteplace(Integer placeId);
}
