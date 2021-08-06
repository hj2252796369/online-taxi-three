package com.hujie.apipassenger.service.impl;

import com.hujie.apipassenger.service.ServiceVerificationCodeRestTemplateService;
import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.serviceverificationcode.request.VerifyCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: online-taxi-three
 * @ClassName ServiceVerificationCodeRestTemplateServiceImpl
 * @description:
 * @author: huJie
 * @create: 2021-08-05 22:12
 **/
@Service
public class ServiceVerificationCodeRestTemplateServiceImpl implements ServiceVerificationCodeRestTemplateService {

    String baseUrl = "http://service-verfication-code";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult generateCode(int identity, String phoneNumber) {
        String url = baseUrl + "/verify-code/generate/{identity}/{phoneNumber}";
        ResponseResult result = restTemplate.getForObject(url, ResponseResult.class, identity, phoneNumber);
        return result;
    }

    @Override
    public ResponseResult verifyCde(int passenger, String passengerPhone, String code) {
        String url = baseUrl + "/verify-code/verifyCode";
        VerifyCodeRequest verifyCodeRequest = new VerifyCodeRequest();
        verifyCodeRequest.setCode(code);
        verifyCodeRequest.setIdentity(passenger);
        verifyCodeRequest.setPhoneNumber(passengerPhone);

        return  restTemplate.postForObject(url, verifyCodeRequest, ResponseResult.class);
    }
}
