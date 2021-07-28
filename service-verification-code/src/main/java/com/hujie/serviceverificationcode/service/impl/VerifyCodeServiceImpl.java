package com.hujie.serviceverificationcode.service.impl;

import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import com.hujie.serviceverificationcode.service.VerifyCodeService;

/**
 * @program: online-taxi-three
 * @ClassName VerifyCodeServiceImpl
 * @description:
 * @author: huJie
 * @create: 2021-07-28 22:11
 **/
public class VerifyCodeServiceImpl implements VerifyCodeService {
    @Override
    public ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber) {
        return null;
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
            String codeTwo = String.valueOf((int)((Math.random()*9+1)*Math.pow(10,5)));//数字运算
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);


    }
}
