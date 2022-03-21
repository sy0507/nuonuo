package com.example.nuonuo.service;

import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.PayVO;

import java.util.List;

public interface PayService {
    String pay(Integer audioId, Integer uid);

    List<PayVO> getPayByuid(Integer uid);

    List<PayVO> getPayList();
}
