package com.hujie.servicesms.service;

import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.servicesms.request.SmsSendRequest;

public interface SmsService {
    /**
     * 发送短信
     * @param request
     * @return
     */
    ResponseResult sendSms(SmsSendRequest smsSendRequest);
}
