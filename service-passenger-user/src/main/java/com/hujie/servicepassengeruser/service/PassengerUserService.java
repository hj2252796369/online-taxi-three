package com.hujie.servicepassengeruser.service;

import com.hujie.internalcommon.dto.ResponseResult;

/**
 * @program: online-taxi-three
 * @ClassName PassengerUserService
 * @description:
 * @author: huJie
 * @create: 2021-08-07 20:43
 **/
public interface PassengerUserService {
    /**
     * 登录
     * @param passengerPhone
     * @return
     */
    ResponseResult login(String passengerPhone);
}
