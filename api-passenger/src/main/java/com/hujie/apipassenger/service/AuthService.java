package com.hujie.apipassenger.service;

import com.hujie.internalcommon.dto.ResponseResult;

public interface AuthService {
    ResponseResult auth(String passengerPhone, String code);
}
