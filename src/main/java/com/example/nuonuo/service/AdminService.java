package com.example.nuonuo.service;

import com.example.nuonuo.pojo.dto.AdminDTO;
import com.example.nuonuo.pojo.dto.AuditDTO;
import com.example.nuonuo.pojo.dto.SuspendDTO;
import com.example.nuonuo.pojo.vo.*;

import java.util.List;

public interface AdminService {
    AdminVO login(AdminDTO dto);

    AdminVO getUserInfoVO(Integer id);

    void resetPwd(Integer uid);

    void resetName(Integer uid);

    void suspend(SuspendDTO dto);

    void unsuspend(Integer uid);

    List<SuspendVO> getSuspendList(Integer uid);

    List<UserAdminVO> getUserList();

    List<AudioAdminVO> getAudioList();

    void audit(AuditDTO dto);
}
