package com.hujie.apipassenger.service;

import com.hujie.internalcommon.dto.ResponseResult;

public interface ServicePassengerUserRestTemplateService {
    ResponseResult login(String passengerPhone);
}
