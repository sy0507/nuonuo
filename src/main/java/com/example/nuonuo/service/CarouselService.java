package com.example.nuonuo.service;

import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.AudioVO;
import com.example.nuonuo.pojo.vo.CardVO;
import com.example.nuonuo.pojo.vo.CarouselVO;

import java.util.List;

public interface CarouselService {

    List<CarouselVO> getCarouselList();

}
