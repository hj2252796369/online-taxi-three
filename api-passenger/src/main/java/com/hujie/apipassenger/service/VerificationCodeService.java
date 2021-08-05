package com.hujie.apipassenger.service;

import com.hujie.internalcommon.dto.ResponseResult;

/**
 * @program: online-taxi-three
 * @ClassName VerificationCodeService
 * @description:
 * @author: huJie
 * @create: 2021-08-05 22:08
 **/
public interface VerificationCodeService {
    ResponseResult sendCode(String phoneNumber);
}
