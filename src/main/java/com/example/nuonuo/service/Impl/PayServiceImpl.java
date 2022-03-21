package com.example.nuonuo.service.Impl;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.nuonuo.mapper.AudioMapper;
import com.example.nuonuo.mapper.PayMapper;
import com.example.nuonuo.pojo.entity.Audio;
import com.example.nuonuo.pojo.entity.Pay;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.PayVO;
import com.example.nuonuo.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    private final AudioMapper audioMapper;
    private final PayMapper payMapper;
    String APP_ID="2021000119636816";
    String APP_PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDZ0j4c+TMMGBSUDMPupl8HWZsljC0Hb/2ErZ3DGyObCEa8gjP4oR1wmX07bSb3JNIQhHerih3T2K1klIJwV4DmjTtSLXAYATY4oaKUEtmPssw2YcIKj1yzsr3q9KxBbcVEBOnXx7Z1DkfOcmFLv2TImvPoCN5h1snqYDLw6q3HMnmJnWwXDlD6G6I1IaYDcliQaYQtxwEf+2H+/eJ9X57qkPkmYKZIhS8GM8pUr/wMlnPOzmNTW1xUXkVJ+66I+re/tED6MwpX738z3Ikhj1U/49tww3n0qEZPjt3KfMGiuOEFtj/kUmuKJBa9js/IHsQAKxYcR67yAiksKGVAA2sjAgMBAAECggEBAJeTEwAMdXZUB3Xi0DFw+q3dr/XX7rm/N7atLhxH9WX7w2WIYWwBicACP0AsO7I9oCQ4WiI3sXvqyvYz1fUhHyTQYNhVoCxEI5wS9lR+LYEulaa+Lk405vhxSHGIo5nALkaf+K3cXt5j+pnxxDz2ycZC9bmg+kcxNT7whGJbnGQBNiO7W8YaU3zJ3b6tN8OC4xIpebOYpMLWc8Qk3wGcJWMEtvV+o3fPl85DpQ7wL6aE3pb1un5dD/I09E3ctHvmUzYG2ZQNSnpSKHYPyGgdwD5awfffOHPrMQ8gNZDxloFDIWBjs1PnewoiedZGdNGIm//AMIyYmASJZPaC55IF9okCgYEA7dCjX4m1gBHNlwKQKKeGmWGTWKu/xtBcoS93P5b7WxJmmzRngeTCi1X448Qpq+q1qWKfWYgZiCMy/kPAbDESDh6RJVF/7U2pHIt8JfVJUhIIMZGZfJo7NojWZGYgl+U5g5dMytVkjPdE09e40G43lsKIVdX31KNz51qwv9jlre0CgYEA6no3RwH4hSdY4Viwc2niJ+PJqI1Te+xvd8smRRjWrvEgt7a+H3P11gCVakwIsU7SYtVgn+nDUUet7rntn6VxRSoCQUIamuaajHFaNzKu4VuRcOz401SULoIIhfWpmm+ixVUwvxiOqRjJ39Vkf8wpau4jLzbAL2CjywFPsHed208CgYEAvWHvGH/z1/9Vd7KE1rtCRxcbXfu4Bz2a5omEL1Gu7YGj0GlVHG3uN0PJpqhqfwu0iyWMvKHTo0RUsYOG0yHovPG+qLdUZsf3qlGyFhBVyVOuzrqiPkYcbXtfMCjcP9o9iPuOHeD95/xqBUG/XczH2/ki1ShF6kIYZN8l7kFt9qkCgYBl3s1PS1rmts6VsQuQSQtZm0yMr0H7ECDpodgxovX6sYYuavW1ApNm83/226vXJNCg4eWF9HDISmUV16+WcbGBhBvQsGCQtemlZMX2P03dWQQaVMpSPmfWwOXlFXMnRqMoKrfv/kJU0xE57yMnptLDw0yEAbyDmO/wjdRKbd2mXwKBgEWIeLp074QB0oXLTgAsY5NDRt3WOSotx74MHJ2ceDnTP9APJFOt0p9jkNojuxkciyukTtAywgxe4Z1pqczgb3WaiDM2TijVYSVa1zi6et6yg+C/BbUKI+aMw2OhuXg1GSx3KclUDo4w2cZnlF13U9zVCiguCghd4Db2rLMzcSkc";
    String FORMAT="json";
    String CHARSET="utf-8";
    String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlaUfjR6A8bcYNjjND4413iOf3TM7Sg0sMnexcg7Jbr7cU3klbBtMA1wKHhgIbbs3jnjnhFVhyOyAqhhO/QnDZB5hs9FjmXl/VfV2ZBgRKR5g/2uMOM3YueQaLGZVN0AT1pMBCK34Yr9iVHng3prWdP4ALub7b4wBXBXQsRa3ic0wPyvbyu9TlbFd2EifmWceR5n8M2HsYTAiV2FtPjubXIas3p8vR5cFcKIQzTueTlL5yg5ziO/R583zBqc2RwYpfM+/Z1EmEaJ6rWJk2sJeriu7/eDvTwue63UnVTHIGWAG90yTb3j9DKqx0ZuoEnGlrhxGf4YbW30+9+ySeWzWzQIDAQAB";
    String SIGN_TYPE="RSA2";

    public PayServiceImpl(AudioMapper audioMapper, PayMapper payMapper) {
        this.audioMapper = audioMapper;
        this.payMapper = payMapper;
    }

    @Override
    public String pay(Integer audioId, Integer uid) {
        AlipayClient alipayClient =  new DefaultAlipayClient( "\t\n" +
                "https://openapi.alipaydev.com/gateway.do" , APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest alipayRequest =  new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl( "http://localhost:9528/order?audio_id="+audioId );
        alipayRequest.setNotifyUrl( "http://localhost:8081/notify" );
        Audio audio=audioMapper.selectByPrimaryKey(audioId);
        String outTradeNo=getTradeNo();
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\""+outTradeNo+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":\""+audio.getPrice()+"\","  +
                "    \"subject\":\""+audio.getAudioName()+"\","  +
                "    \"body\":\""+audio.getAudioName()+"\""  +
//                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
//                "    \"extend_params\":{"  +
//                "    \"sys_service_provider_id\":\"2088511833207846\""  +
//                "    }"
//                +
                "  }" );
        String form="";
        try {
            form=alipayClient.pageExecute(alipayRequest).getBody();
        }catch (AlipayApiException e){
            e.printStackTrace();
        }
        Pay pay=new Pay();
//        pay.setUid(user.getUid());
        pay.setAudioId(audioId);
        Date date=new Date();
        Long dateTime=date.getTime();
        pay.setCreateTime(dateTime);
        pay.setUid(uid);
        pay.setOutTradeNo(outTradeNo);
        payMapper.insertSelective(pay);
        return form;

    }

    @Override
    public List<PayVO> getPayByuid(Integer uid) {
        List<PayVO> payVOList=new ArrayList<>();
        List<Pay> payList=payMapper.selectByuid(uid);
        for (int i=0;i<payList.size();i++)
        {
            PayVO payVO=new PayVO();
            BeanUtils.copyProperties(payList.get(i),payVO);
            Audio audio=audioMapper.selectByPrimaryKey(payVO.getAudioId());
            payVO.setAudioName(audio.getAudioName());
            payVOList.add(payVO);
        }
        return payVOList;
    }

    @Override
    public List<PayVO> getPayList() {
        List<PayVO> payVOList=new ArrayList<>();
        List<Pay> payList=payMapper.selectAll();
        for (int i=0;i<payList.size();i++)
        {
            PayVO payVO=new PayVO();
            BeanUtils.copyProperties(payList.get(i),payVO);
            Audio audio=audioMapper.selectByPrimaryKey(payVO.getAudioId());
            payVO.setAudioName(audio.getAudioName());
            payVOList.add(payVO);
        }
        return payVOList;
    }


    public String getTradeNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }
}
