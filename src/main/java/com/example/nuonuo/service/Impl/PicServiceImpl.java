package com.example.nuonuo.service.Impl;

import com.example.nuonuo.mapper.PicMapper;
import com.example.nuonuo.pojo.dto.PicDTO;
import com.example.nuonuo.pojo.entity.Pic;
import com.example.nuonuo.pojo.vo.PicVO;
import com.example.nuonuo.service.PicService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PicServiceImpl implements PicService {
    private final PicMapper picMapper;

    public PicServiceImpl(PicMapper picMapper) {
        this.picMapper = picMapper;
    }

    @Override
    public void addPic(PicDTO picDTO) {
        Pic pic=new Pic();
        BeanUtils.copyProperties(picDTO,pic);
        picMapper.insert(pic);

    }

    @Override
    public List<PicVO> getList() {
        List<PicVO> picVOList=new ArrayList<>();
        List<Pic> picList=picMapper.selectAll();


        for (int i=0;i<picList.size();i++){
            PicVO picVO=new PicVO();
            BeanUtils.copyProperties(picList.get(i),picVO);
            picVOList.add(picVO);

        }
        return picVOList;
    }

    @Override
    public Object getPic(Integer id) {
        Pic pic=picMapper.selectByPrimaryKey(id);
        PicVO picVO=new PicVO();
        BeanUtils.copyProperties(pic,picVO);
        return picVO;
    }
}
