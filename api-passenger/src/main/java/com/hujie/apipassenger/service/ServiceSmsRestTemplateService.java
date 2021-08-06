package com.hujie.apipassenger.service;

import com.hujie.internalcommon.dto.ResponseResult;

public interface ServiceSmsRestTemplateService {
    public ResponseResult sendSms(String phoneNumber , String code);
}
