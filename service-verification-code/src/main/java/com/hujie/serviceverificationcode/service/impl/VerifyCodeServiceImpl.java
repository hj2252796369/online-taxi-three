package com.hujie.serviceverificationcode.service.impl;

import com.hujie.internalcommon.constant.CommonStatusEnum;
import com.hujie.internalcommon.constant.IdentityConstant;
import com.hujie.internalcommon.constant.RedisKeyPrefixConstant;
import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import com.hujie.internalcommon.util.ParamCheckUtil;
import com.hujie.serviceverificationcode.service.VerifyCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
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
    RedisTemplate<String, String> redisTemplate;

    @Override
    public ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber) {
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));

        //参数校验
        ParamCheckUtil.checkParamIsPhone(phoneNumber);

        //生成redis code
        String preKey = generateKeyPreByIdentity(identity);
        String redisKey = preKey + phoneNumber;

        // 存redis，2分钟过期
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(redisKey);
        boundValueOperations.set(code, 10, TimeUnit.MINUTES);

        // 返回
        VerifyCodeResponse verifyCodeResponse = new VerifyCodeResponse();
        verifyCodeResponse.setCode(code);

        return ResponseResult.success(verifyCodeResponse);
    }

    /**
     * 根据身份前缀生成redisKey的前缀
     *
     * @param identity identity
     * @return
     */
    private String generateKeyPreByIdentity(int identity) {
        String preKey = "";
        if (identity == IdentityConstant.PASSENGER) {
            preKey = RedisKeyPrefixConstant.DRIVER_LOGIN_CODE_KEY_PRE;
        } else if (identity == IdentityConstant.DRIVER) {
            preKey = RedisKeyPrefixConstant.DRIVER_LOGIN_CODE_KEY_PRE;
        }
        return preKey;
    }

    @Override
    public ResponseResult verify(int identity, String phoneNumber, String code) {
        //三档验证
        // 一段时间内验证码错误的次数
        // 一天失败的次数


        //生成redis code
        String preKey = generateKeyPreByIdentity(identity);
        String redisKey = preKey + phoneNumber;

        BoundValueOperations<String, String> boundValueOperations = redisTemplate.boundValueOps(redisKey);
        String redisCode = boundValueOperations.get();

        if(StringUtils.isNotBlank(code)
                && StringUtils.isNotBlank(redisCode)
                && code.trim().equals(redisCode.trim())) {

            return ResponseResult.success("");
        }else {
            return ResponseResult.fail(CommonStatusEnum.VERIFY_CODE_ERROR.getCode(), CommonStatusEnum.VERIFY_CODE_ERROR.getValue());
        }
    }

    public static void main(String[] args) {
        //数字运算会比字符拼接的效率高上很多

        int sum = 10000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < sum; i++) {
            String codeOne = (Math.random() + "").substring(2, 8); //字符拼接
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);


        start = System.currentTimeMillis();
        for (int i = 0; i < sum; i++) {
            String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));//数字运算
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);


    }
}
