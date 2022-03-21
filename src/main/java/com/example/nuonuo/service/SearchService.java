package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.PageDTO;
import com.example.nuonuo.pojo.dto.SearchDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.AudioVO;
import com.example.nuonuo.pojo.vo.AuthorVO;
import com.example.nuonuo.pojo.vo.LAudioVO;

import java.util.List;

public interface SearchService {
    List<AuthorVO> list(String condition, PageDTO pageDTO, Integer uid);

    List<LAudioVO> listAudio(String condition, PageDTO pageDTO);


    List<AudioVO> listAdminAudio(Integer audioId, String audioName, Integer uid,Integer audioTypeId, PageDTO pageDTO);
}
