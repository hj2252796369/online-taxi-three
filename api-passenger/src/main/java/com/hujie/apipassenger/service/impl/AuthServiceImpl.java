package com.hujie.apipassenger.service.impl;

import com.hujie.apipassenger.service.AuthService;
import com.hujie.apipassenger.service.ServicePassengerUserRestTemplateService;
import com.hujie.apipassenger.service.ServiceVerificationCodeRestTemplateService;
import com.hujie.internalcommon.constant.CommonStatusEnum;
import com.hujie.internalcommon.constant.IdentityConstant;
import com.hujie.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: online-taxi-three
 * @ClassName AuthServiceImpl
 * @description:
 * @author: huJie
 * @create: 2021-08-06 22:27
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ServiceVerificationCodeRestTemplateService verificationCodeRestTemplateService;

    @Autowired
    private ServicePassengerUserRestTemplateService passengerUserRestTemplateService;

    @Override
    public ResponseResult auth(String passengerPhone, String code) {
        // 验证验证码
        ResponseResult verifyCdeResult = verificationCodeRestTemplateService.verifyCde(IdentityConstant.PASSENGER, passengerPhone, code);
        if(verifyCdeResult.getCode() != CommonStatusEnum.SUCCESS.getCode()){
            return ResponseResult.fail("验证码不正确");
        }

        // 用户登录
        ResponseResult loginResult = passengerUserRestTemplateService.login(passengerPhone);
        if(loginResult.getCode() != CommonStatusEnum.SUCCESS.getCode()){
            return ResponseResult.fail("登录失败：请重新登录");
        }
        return loginResult;
    }
}
