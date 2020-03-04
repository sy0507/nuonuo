package com.example.nuonuo.service.Impl;

import com.example.nuonuo.mapper.PlaceMapper;
import com.example.nuonuo.pojo.dto.PlaceDTO;
import com.example.nuonuo.pojo.entity.Center;
import com.example.nuonuo.pojo.entity.Place;
import com.example.nuonuo.pojo.vo.CenterVO;
import com.example.nuonuo.pojo.vo.PlaceVO;
import com.example.nuonuo.service.PlaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceMapper placeMapper;

    public PlaceServiceImpl(PlaceMapper placeMapper) {
        this.placeMapper = placeMapper;
    }

    @Override
    public void add(PlaceDTO placeDTO) {
        Place place=new Place();
        BeanUtils.copyProperties(placeDTO,place);
        placeMapper.insertSelective(place);

    }

    @Override
    public List<PlaceVO> getplaceInfoVo() {
        List<PlaceVO> placeVOList=new ArrayList<>();
        List<Place> placeList=placeMapper.selectAll();
        for (int i=0;i<placeList.size();i++){
            PlaceVO placeVO=new PlaceVO();
            BeanUtils.copyProperties(placeList.get(i),placeVO);
            placeVOList.add(placeVO);
        }
        return placeVOList;
    }
}
