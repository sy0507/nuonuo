package com.example.nuonuo.service.Impl;

import com.example.nuonuo.mapper.AudioMapper;
import com.example.nuonuo.mapper.FollowMapper;
import com.example.nuonuo.mapper.TypeMapper;
import com.example.nuonuo.mapper.UserMapper;
import com.example.nuonuo.pojo.dto.PageDTO;
import com.example.nuonuo.pojo.dto.SearchDTO;
import com.example.nuonuo.pojo.entity.Audio;
import com.example.nuonuo.pojo.entity.Follow;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.AudioVO;
import com.example.nuonuo.pojo.vo.AuthorVO;
import com.example.nuonuo.pojo.vo.LAudioVO;
import com.example.nuonuo.service.AudioService;
import com.example.nuonuo.service.FileService;
import com.example.nuonuo.service.SearchService;
import com.example.nuonuo.util.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    private final PageHelper pageHelper;
    private final UserMapper userMapper;
    private final FileService fileService;
    private final FollowMapper followMapper;
    private final AudioMapper audioMapper;
    private final TypeMapper typeMapper;
    private final AudioService audioService;

    public SearchServiceImpl(PageHelper pageHelper, UserMapper userMapper, FileService fileService, FollowMapper followMapper, AudioMapper audioMapper, TypeMapper typeMapper, AudioService audioService) {
        this.pageHelper = pageHelper;
        this.userMapper = userMapper;
        this.fileService = fileService;
        this.followMapper = followMapper;
        this.audioMapper = audioMapper;
        this.typeMapper = typeMapper;
        this.audioService = audioService;
    }

    @Override
    public List<AuthorVO> list(String condition, PageDTO pageDTO,Integer uid) {
        pageDTO.setSortBy("fans");
        pageHelper.startPage(pageDTO,true);
        List<AuthorVO> authorVOList=new ArrayList<>();
        List<User> userList=userMapper.getAuthor(condition);

        for (int i=0;i<userList.size();i++){
            AuthorVO authorVO=new AuthorVO();
            BeanUtils.copyProperties(userList.get(i),authorVO);
            User user=userMapper.selectByPrimaryKey(uid);
            authorVO.setHeadPicUrl(fileService.getUrlById(authorVO.getHeadPicId()));
            if (user!=null){
                Follow follow=new Follow();
                follow.setFollowId(authorVO.getUid());
                follow.setUid(user.getUid());
                if (followMapper.selectOne(follow)!=null){
                    authorVO.setFollow(true);
                }

            }
            authorVOList.add(authorVO);
        }


        return authorVOList;
    }

    @Override
    public List<LAudioVO> listAudio(String condition, PageDTO pageDTO) {
        pageDTO.setSortBy("good_number");
        pageHelper.startPage(pageDTO,true);
        List<LAudioVO> lAudioVOList=new ArrayList<>();
        List<Audio> audioList=audioMapper.getLAudio(condition);

        for (int i=0;i<audioList.size();i++){
            LAudioVO lAudioVO=new LAudioVO();
            BeanUtils.copyProperties(audioList.get(i),lAudioVO);
            lAudioVO.setAudioType(audioService.getAudioType(lAudioVO.getAudioTypeId()));
            lAudioVO.setCoverUrl(fileService.getUrlById(lAudioVO.getCoverId()));
            lAudioVOList.add(lAudioVO);
        }
        return lAudioVOList;
    }

    @Override
    public List<AudioVO> listAdminAudio(Integer audioId, String audioName, Integer uid, Integer audioTypeId,PageDTO pageDTO) {
        pageDTO.setSortBy(pageDTO.getSortBy());
        pageHelper.startPage(pageDTO,true);
        List<AudioVO> audioVOList=new ArrayList<>();
        String audioIdStr;
        String uidStr;
        String audioTypeStr;
        if (audioId==null)
        {
            audioIdStr="";
        }
        else {
            audioIdStr=String.valueOf(audioId);
        }
        if (uid==null)
        {
            uidStr="";
        }
        else {
            uidStr=String.valueOf(uid);
        }
        if (audioTypeId==null){
            audioTypeStr="";
        }
        else {
            audioTypeStr=String.valueOf(audioTypeId);
        }
        if (audioName==null)
            audioName="";


        List<Audio> audioList=audioMapper.getAdminAudio(audioIdStr,audioName,uidStr,audioTypeStr);


        for (int i=0;i<audioList.size();i++)
        {
            AudioVO audioVO=new AudioVO();
            System.out.println(audioList.get(i).getCommentNumber());
            BeanUtils.copyProperties(audioList.get(i),audioVO);
            audioVO.setAudioType(audioService.getAudioType(audioVO.getAudioTypeId()));
            audioVO.setCoverUrl(fileService.getUrlById(audioVO.getCoverId()));
            audioVO.setContentUrl(fileService.getUrlById(audioVO.getContentId()));
            audioVOList.add(audioVO);
        }
        return audioVOList;

    }


}
