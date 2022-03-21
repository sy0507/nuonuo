package com.example.nuonuo.service.Impl;

import com.example.nuonuo.mapper.*;
import com.example.nuonuo.pojo.dto.AudioDTO;
import com.example.nuonuo.pojo.entity.*;
import com.example.nuonuo.pojo.vo.AudioVO;
import com.example.nuonuo.pojo.vo.CardVO;
import com.example.nuonuo.pojo.vo.TypeVO;
import com.example.nuonuo.service.AudioService;
import com.example.nuonuo.service.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AudioServiceImpl implements AudioService {
    private final TypeMapper typeMapper;
    private final AudioMapper audioMapper;
    private final FileService fileService;
    private final CardMapper cardMapper;
    private final GoodInfoMapper goodInfoMapper;
    private final CollectInfoMapper collectInfoMapper;
    private final FollowMapper followMapper;
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;
    private final PayMapper payMapper;
    public AudioServiceImpl(TypeMapper typeMapper, AudioMapper audioMapper, FileService fileService, CardMapper cardMapper, GoodInfoMapper goodInfoMapper, CollectInfoMapper collectInfoMapper, FollowMapper followMapper, CommentMapper commentMapper, UserMapper userMapper, PayMapper payMapper) {
        this.typeMapper = typeMapper;
        this.audioMapper = audioMapper;
        this.fileService = fileService;
        this.cardMapper = cardMapper;
        this.goodInfoMapper = goodInfoMapper;
        this.collectInfoMapper = collectInfoMapper;
        this.followMapper = followMapper;
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
        this.payMapper = payMapper;
    }

    @Override
    public List<TypeVO> getTypeList() {
        List<TypeVO> typeVOList=new ArrayList<>();
        List<Type> typeList=typeMapper.selectAll();
        for (int i=0;i<typeList.size();i++)
        {
            TypeVO typeVO=new TypeVO();
            BeanUtils.copyProperties(typeList.get(i),typeVO);
            typeVOList.add(typeVO);
        }
        return typeVOList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(AudioDTO audioDTO, Integer uid) {
        Audio audio=new Audio();
        BeanUtils.copyProperties(audioDTO,audio);
        audio.setUid(uid);
        User user=userMapper.selectByPrimaryKey(uid);
        Date date=new Date();
        Long dateTime=date.getTime();
        audio.setCreateTime(dateTime);
        audio.setStatus(0);
        audioMapper.insertSelective(audio);
        user.setAudioNum(user.getAudioNum()+1);
        userMapper.updateByPrimaryKeySelective(user);
        int audioId=audio.getAudioId();
        System.out.println(audio);
        return audioId;
//        return null;
    }

    @Override
    public AudioVO getDetail(Integer audioId,Integer uid) {
        AudioVO audioVO=new AudioVO();
        Audio audio= audioMapper.selectByPrimaryKey(audioId);
        User user=userMapper.selectByPrimaryKey(uid);
        if (user!=null){
            GoodInfo goodInfo=new GoodInfo();
            goodInfo.setAudioId(audioId);
            goodInfo.setUid(user.getUid());
            if (goodInfoMapper.selectOne(goodInfo)!=null)
            {
                audioVO.setGood(true);
            }
            CollectInfo collectInfo=new CollectInfo();
            collectInfo.setAudioId(audioId);
            collectInfo.setUid(user.getUid());
            if (collectInfoMapper.selectOne(collectInfo)!=null){
                audioVO.setCollect(true);
            }
            Follow follow=new Follow();
            follow.setUid(user.getUid());
            follow.setFollowId(audio.getUid());
            if (followMapper.selectOne(follow)!=null){
                audioVO.setFollow(true);
            }
            if (!audio.getPrice().equals("0")){
                Pay pay=new Pay();
                pay.setAudioId(audioId);
                pay.setUid(user.getUid());
                if (payMapper.selectOne(pay)!=null){
                    audioVO.setBelong(true);
                }

            }

        }
        BeanUtils.copyProperties(audio,audioVO);
        audioVO.setAudioType(getAudioType(audio.getAudioTypeId()));
        audioVO.setCoverUrl(fileService.getUrlById(audio.getCoverId()));
        audioVO.setContentUrl(fileService.getUrlById(audio.getContentId()));
        return audioVO;
    }

    @Override
    public String getAudioType(Integer audioTypeId) {
        Type type=typeMapper.selectByPrimaryKey(audioTypeId);
        String audioType=type.getAudioType();
        return audioType;
    }

    @Override
    public List<CardVO> getCarouselList() {
        List<CardVO> cardVOList=new ArrayList<>();
        List<Audio> cardList=cardMapper.getCarouselList();
        for (int i=0;i<cardList.size();i++){
            CardVO cardVO=new CardVO();
            BeanUtils.copyProperties(cardList.get(i),cardVO);
            cardVO.setCoverUrl(fileService.getUrlById(cardList.get(i).getCoverId()));
            cardVOList.add(cardVO);
        }
        return cardVOList;
    }

    @Override
    public List<CardVO> getHotMusicList() {
        List<CardVO> cardVOList=new ArrayList<>();
        List<Audio> cardList=cardMapper.getHotMusicList();

        for (int i=0;i<cardList.size();i++){

            CardVO cardVO=new CardVO();
            BeanUtils.copyProperties(cardList.get(i),cardVO);
            cardVO.setCoverUrl(fileService.getUrlById(cardList.get(i).getCoverId()));
            cardVOList.add(cardVO);
        }
        return cardVOList;
    }

    @Override
    public List<CardVO> getCardList() {
        List<CardVO> cardVOList=new ArrayList<>();
        List<Audio> cardList=cardMapper.getCardList();

        for (int i=0;i<cardList.size();i++){

            CardVO cardVO=new CardVO();
            BeanUtils.copyProperties(cardList.get(i),cardVO);
            cardVO.setCoverUrl(fileService.getUrlById(cardList.get(i).getCoverId()));
            cardVOList.add(cardVO);
        }
        return cardVOList;
    }


}
