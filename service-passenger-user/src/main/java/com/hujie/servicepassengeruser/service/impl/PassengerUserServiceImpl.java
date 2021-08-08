package com.hujie.servicepassengeruser.service.impl;

import com.hujie.internalcommon.constant.RedisKeyPrefixConstant;
import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.util.JwtUtil;
import com.hujie.servicepassengeruser.dao.PassengerUserInfoCustomDao;
import com.hujie.servicepassengeruser.entity.ServicePassengerUserInfo;
import com.hujie.servicepassengeruser.service.PassengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: online-taxi-three
 * @ClassName PassengerUserServiceImpl
 * @description:
 * @author: huJie
 * @create: 2021-08-07 20:45
 **/
@Service
public class PassengerUserServiceImpl implements PassengerUserService {

    @Autowired
    private PassengerUserInfoCustomDao passengerUserInfoCustomDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ResponseResult login(String passengerPhone) {

        //  如果数据库没有此用户，插库。可以用分布锁，也可以用 唯一索引。
        ServicePassengerUserInfo passengerUserInfo = passengerUserInfoCustomDao.selectPassengerUserInfoByPhone(passengerPhone);
        if(passengerUserInfo == null){
            passengerUserInfo = new ServicePassengerUserInfo();
            passengerUserInfo.setCreateTime(new Date());
            passengerUserInfo.setPassengerGender((byte)1);
            passengerUserInfo.setRegisterDate(new Date());
            passengerUserInfo.setPassengerName(passengerPhone);
            passengerUserInfo.setPassengerPhone(passengerPhone);
            passengerUserInfo.setUserState((byte)1);

            passengerUserInfoCustomDao.insertSelective(passengerUserInfo);
        }

        Long passengerUserId = passengerUserInfo.getId();
        // 生成token，如果是服务器控制，则把token存放在redis当中
        String token = JwtUtil.createToken(passengerUserId+"", new Date());
        BoundValueOperations<String, String> stringStringBoundValueOperations = redisTemplate.boundValueOps(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + passengerUserId);
        stringStringBoundValueOperations.set(token, 30, TimeUnit.DAYS);

        return ResponseResult.success(token);
    }
}
