package com.example.nuonuo.service.Impl;

import com.example.nuonuo.mapper.CarouselMapper;
import com.example.nuonuo.mapper.CollectInfoMapper;
import com.example.nuonuo.mapper.FollowMapper;
import com.example.nuonuo.mapper.GoodInfoMapper;
import com.example.nuonuo.pojo.entity.*;
import com.example.nuonuo.pojo.vo.AudioVO;
import com.example.nuonuo.pojo.vo.CardVO;
import com.example.nuonuo.pojo.vo.CarouselVO;
import com.example.nuonuo.service.AudioService;
import com.example.nuonuo.service.CarouselService;
import com.example.nuonuo.service.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    private final CarouselMapper carouselMapper;
    private final GoodInfoMapper goodInfoMapper;
    private final CollectInfoMapper collectInfoMapper;
    private final FollowMapper followMapper;
    private final FileService fileService;
    private final AudioService audioService;

    public CarouselServiceImpl(CarouselMapper carouselMapper, GoodInfoMapper goodInfoMapper, CollectInfoMapper collectInfoMapper, FollowMapper followMapper, FileService fileService, AudioService audioService) {
        this.carouselMapper = carouselMapper;
        this.goodInfoMapper = goodInfoMapper;
        this.collectInfoMapper = collectInfoMapper;
        this.followMapper = followMapper;
        this.fileService = fileService;
        this.audioService = audioService;
    }


    @Override
    public List<CarouselVO> getCarouselList() {
        List<CarouselVO> cardVOList=new ArrayList<>();
        List<Carousel> cardList=carouselMapper.selectAll();
        for (int i=0;i<cardList.size();i++){
            CarouselVO carouselVO=new CarouselVO();
            BeanUtils.copyProperties(cardList.get(i),carouselVO);
            carouselVO.setCoverUrl(fileService.getUrlById(cardList.get(i).getCoverId()));
            cardVOList.add(carouselVO);
        }
        return cardVOList;
    }
}
