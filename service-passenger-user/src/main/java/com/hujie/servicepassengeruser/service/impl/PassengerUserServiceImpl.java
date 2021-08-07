package com.hujie.servicepassengeruser.service.impl;

import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.servicepassengeruser.service.PassengerUserService;
import org.springframework.stereotype.Service;

/**
 * @program: online-taxi-three
 * @ClassName PassengerUserServiceImpl
 * @description:
 * @author: huJie
 * @create: 2021-08-07 20:45
 **/
@Service
public class PassengerUserServiceImpl implements PassengerUserService {
    @Override
    public ResponseResult login(String passengerPhone) {

        //  如果数据库没有此用户，插库。可以用分布锁，也可以用 唯一索引。


        return null;
    }
}
