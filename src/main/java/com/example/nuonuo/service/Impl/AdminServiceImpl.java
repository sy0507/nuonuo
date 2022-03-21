package com.example.nuonuo.service.Impl;


import com.example.nuonuo.exception.CommonException;
import com.example.nuonuo.mapper.*;
import com.example.nuonuo.pojo.dto.AdminDTO;
import com.example.nuonuo.pojo.dto.AuditDTO;
import com.example.nuonuo.pojo.dto.SuspendDTO;
import com.example.nuonuo.pojo.entity.*;
import com.example.nuonuo.pojo.vo.AdminVO;
import com.example.nuonuo.pojo.vo.AudioAdminVO;
import com.example.nuonuo.pojo.vo.SuspendVO;
import com.example.nuonuo.pojo.vo.UserAdminVO;
import com.example.nuonuo.service.AdminService;
import com.example.nuonuo.service.AudioService;
import com.example.nuonuo.service.FileService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final FileService fileService;
    private final UserMapper userMapper;
    private final SuspendMapper suspendMapper;
    private final AudioMapper audioMapper;
    private final AudioService audioService;
    private final AuditMapper auditMapper;

    public AdminServiceImpl(AdminMapper adminMapper, FileService fileService, UserMapper userMapper, SuspendMapper suspendMapper, AudioMapper audioMapper, AudioService audioService, AuditMapper auditMapper) {
        this.adminMapper = adminMapper;
        this.fileService = fileService;
        this.userMapper = userMapper;
        this.suspendMapper = suspendMapper;
        this.audioMapper = audioMapper;
        this.audioService = audioService;
        this.auditMapper = auditMapper;
    }

    @Override
    public AdminVO login(AdminDTO dto) {
        Admin exampleUser = new Admin();
        exampleUser.setAdminName(dto.getAdminName());
        Admin admin = adminMapper.selectOne(exampleUser);

        if(admin == null){
            throw new CommonException("用户未注册");
        }


        //比对手机登录密码
        if(!Objects.equals(admin.getPassword(),encryptPassword(dto.getPassword()))){
            throw new CommonException("账号密码不正确");
        }

        //登陆成功则返回token
        updateToken(admin);

        //返回用户信息
        return getUserInfoVO(admin.getAdminId());

    }

    @Override
    public AdminVO getUserInfoVO(Integer id) {
        AdminVO adminVO=new AdminVO();
        Admin admin=new Admin();
        admin.setAdminId(id);
        Admin admin1=adminMapper.selectOne(admin);
        BeanUtils.copyProperties(admin1, adminVO);
        adminVO.setAvatarUrl(fileService.getUrlById(adminVO.getAvatarId()));
        return adminVO;
    }

    @Override
    public void resetPwd(Integer uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        user.setPassword(encryptPassword("audio123"));
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void resetName(Integer uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        user.setName("非法昵称"+uid);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void suspend(SuspendDTO dto) {
        Suspend suspend=new Suspend();
        BeanUtils.copyProperties(dto,suspend);
        suspendMapper.insertSelective(suspend);
        User user=userMapper.selectByPrimaryKey(dto.getUid());
        user.setSuspend(true);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void unsuspend(Integer uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        user.setSuspend(false);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<SuspendVO> getSuspendList(Integer uid) {
        List<SuspendVO> suspendVOList=new ArrayList<>();
        List<Suspend> suspendList=suspendMapper.selectByUid(uid);
        for (int i=0;i<suspendList.size();i++)
        {
            SuspendVO suspendVO=new SuspendVO();
            BeanUtils.copyProperties(suspendList.get(i),suspendVO);
            suspendVOList.add(suspendVO);
        }
        return suspendVOList;

    }

    @Override
    public List<UserAdminVO> getUserList() {
        List<User> userList=userMapper.selectAll();
        List<UserAdminVO> userAdminVOList=new ArrayList<>();
        for (int i=0;i< userList.size();i++)
        {
            UserAdminVO userAdminVO=new UserAdminVO();
            BeanUtils.copyProperties(userList.get(i),userAdminVO);
            if (userAdminVO.isSuspend()){
                Suspend suspend=suspendMapper.getInfo(userAdminVO.getUid());
                userAdminVO.setSuspendStartTime(suspend.getSuspendStartTime());
                userAdminVO.setSuspendEndTime(suspend.getSuspendEndTime());
                userAdminVO.setSuspendReason(suspend.getSuspendReason());
            }
            userAdminVOList.add(userAdminVO);
        }
        return userAdminVOList;

    }

    @Override
    public List<AudioAdminVO> getAudioList() {
        List<Audio> audioList=audioMapper.selectAll();
        List<AudioAdminVO> audioAdminVOList=new ArrayList<>();
        for (int i=0;i<audioList.size();i++)
        {
            AudioAdminVO audioAdminVO =new AudioAdminVO();
            BeanUtils.copyProperties(audioList.get(i),audioAdminVO);
            Audit audit=auditMapper.selectByAId(audioAdminVO.getAudioId());
            audioAdminVO.setAudioType(audioService.getAudioType(audioAdminVO.getAudioTypeId()));
            audioAdminVO.setCoverUrl(fileService.getUrlById(audioAdminVO.getCoverId()));
            audioAdminVO.setContentUrl(fileService.getUrlById(audioAdminVO.getContentId()));
            if (audit!=null){
            audioAdminVO.setReason(audit.getReason());
            audioAdminVO.setAuditTime(audit.getAuditTime());}
            audioAdminVOList.add(audioAdminVO);
        }
        return audioAdminVOList;
    }

    @Override
    public void audit(AuditDTO dto) {
        Audit audit=new Audit();
        BeanUtils.copyProperties(dto,audit);
        Date date =new Date();
        Long dateTime =date.getTime();
        audit.setAuditTime(dateTime);
        Audio audio=audioMapper.selectByPrimaryKey(dto.getAudioId());
        audio.setStatus(dto.getStatus());
        audioMapper.updateByPrimaryKeySelective(audio);
        auditMapper.insertSelective(audit);
    }

    private String encryptPassword(String source) {
        return DigestUtils.sha256Hex(source);
    }

    public void updateToken(Admin admin) {

        Admin exampleUser = new Admin();

        String accessToken;
        // 必须保证token的唯一性
        do {
            accessToken = generateToken(admin.getAdminId(), System.currentTimeMillis());
            exampleUser.setAccessToken(accessToken);
        } while (adminMapper.selectOne(exampleUser) != null);

        exampleUser.setAdminId(admin.getAdminId());
        adminMapper.updateByPrimaryKeySelective(exampleUser);
        admin.setAccessToken(accessToken);
    }

    private String generateToken(Integer uid,long time){
        return DigestUtils.md5Hex(uid+"-"+time);
    }
}
