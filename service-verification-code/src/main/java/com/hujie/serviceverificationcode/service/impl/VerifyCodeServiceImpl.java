package com.hujie.serviceverificationcode.service.impl;

import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import com.hujie.serviceverificationcode.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program: online-taxi-three
 * @ClassName VerifyCodeServiceImpl
 * @description:
 * @author: huJie
 * @create: 2021-07-28 22:11
 **/
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
@Autowired
    RedisTemplate redisTemplate;

    @Override
    public ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber) {
        String code = String.valueOf((int)((Math.random()*9+1)*Math.pow(10,5)));

        redisTemplate.opsForValue().set(phoneNumber + code, 1, 10, TimeUnit.MILLISECONDS);

        VerifyCodeResponse verifyCodeResponse = new VerifyCodeResponse();
        verifyCodeResponse.setCode(code);

        return ResponseResult.success(verifyCodeResponse);
    }

    public static void main(String[] args) {
        //数字运算会比字符拼接的效率高上很多

        int sum = 10000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < sum; i++) {
            String codeOne = (Math.random()+"").substring(2,8); //字符拼接
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);


        start = System.currentTimeMillis();
        for (int i = 0; i < sum; i++) {
            String.valueOf((int)((Math.random()*9+1)*Math.pow(10,5)));//数字运算
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);


    }
}
