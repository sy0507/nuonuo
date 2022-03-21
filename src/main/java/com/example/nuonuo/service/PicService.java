package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.PicDTO;
import com.example.nuonuo.pojo.vo.PicVO;

import java.util.List;

public interface PicService {
    void  addPic(PicDTO picDTO);

    List<PicVO> getList();

    Object getPic(Integer id);
}
