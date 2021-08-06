package com.hujie.apipassenger.service;

import com.hujie.internalcommon.dto.ResponseResult;

public interface ServiceVerificationCodeRestTemplateService {
    ResponseResult generateCode(int identity, String phoneNumber);

    ResponseResult verifyCde(int passenger, String passengerPhone, String code);
}
