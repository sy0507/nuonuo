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
    public List<PlaceVO> getplaceInfoVo(Integer centerId) {
        List<PlaceVO> placeVOList=new ArrayList<>();
        List<Place> placeList=placeMapper.selectByCenterId(centerId);
        for (int i=0;i<placeList.size();i++){
            PlaceVO placeVO=new PlaceVO();
            BeanUtils.copyProperties(placeList.get(i),placeVO);
            placeVOList.add(placeVO);
        }
        return placeVOList;
    }

    @Override
    public Object getPresentInfoVo(Integer placeId) {
        Place place=placeMapper.selectByPrimaryKey(placeId);
        PlaceVO placeVO=new PlaceVO();
        BeanUtils.copyProperties(place,placeVO);
        return placeVO;
    }

    @Override
    public Object modify(Integer placeId, PlaceDTO placeDTO) {
        Place place=placeMapper.selectByPrimaryKey(placeId);
        BeanUtils.copyProperties(placeDTO,place);
        placeMapper.updateByPrimaryKeySelective(place);
        return null;
    }

    @Override
    public void deleteplace(Integer placeId) {
        placeMapper.deleteByPrimaryKey(placeId);
    }
}
