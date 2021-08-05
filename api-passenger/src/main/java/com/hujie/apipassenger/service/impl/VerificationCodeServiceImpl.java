package com.hujie.apipassenger.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hujie.apipassenger.service.ServiceVerificationCodeRestTemplateService;
import com.hujie.apipassenger.service.VerificationCodeService;
import com.hujie.internalcommon.constant.CommonStatusEnum;
import com.hujie.internalcommon.constant.IdentityConstant;
import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: online-taxi-three
 * @ClassName VerificationCodeServiceImpl
 * @description:
 * @author: huJie
 * @create: 2021-08-05 22:09
 **/
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private ServiceVerificationCodeRestTemplateService serviceVerificationCodeRestTemplateService;


    @Override
    public ResponseResult sendCode(String phoneNumber) {

        // 获取验证码，从service-verification-code中获取
        ResponseResult generateCodeResult = serviceVerificationCodeRestTemplateService.generateCode(IdentityConstant.PASSENGER, phoneNumber);
        VerifyCodeResponse verifyCodeResponse = null;
        if(generateCodeResult.getCode() == CommonStatusEnum.SUCCESS.getCode()){
            verifyCodeResponse = JSONObject.parseObject(generateCodeResult.getData().toString(), VerifyCodeResponse.class);
        }else{
            return ResponseResult.fail("获取验证码失败");
        }

        String code = verifyCodeResponse.getCode();



        return null;
    }
}
