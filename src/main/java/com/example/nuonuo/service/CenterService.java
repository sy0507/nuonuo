package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.CenterCarDTO;
import com.example.nuonuo.pojo.dto.CenterDTO;
import com.example.nuonuo.pojo.dto.DistanceDTO;
import com.example.nuonuo.pojo.vo.CarCenterVO;
import com.example.nuonuo.pojo.vo.CenterVO;

import java.io.IOException;
import java.util.List;

public interface CenterService {
    void add(CenterDTO centerdto);


    List<CenterVO> getCenterInfoVo() throws IOException;


    void own(Integer centerId ,CenterCarDTO centerCarDTO);


    List<CarCenterVO> getOwnCarVo(Integer centerId);


    void distance(Integer centerId);


    Object execute(Integer centerId) throws IOException, InterruptedException;


    Object modify(Integer id, CenterDTO centerDTO);


    Object getInfo(Integer id);


    Object modifycar(Integer carId,Integer centerId, CenterCarDTO centerCarDTO);


    void delete(Integer carId,Integer centerId);


    void deletecenter(Integer id);
}
