package com.hujie.serviceverificationcode.controller;

import com.alibaba.fastjson.JSON;
import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.serviceverificationcode.request.VerifyCodeRequest;
import com.hujie.serviceverificationcode.service.VerifyCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: online-taxi-three
 * @ClassName VerifyCodeController
 * @description:
 * @author: huJie
 * @create: 2021-07-28 21:43
 **/
@Slf4j
@RestController
@RequestMapping("/verify-code")
public class VerifyCodeController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    private static final String identityReg = "";

    /**
     * 根据身份，手机号生成验证码
     *
     * @param identity
     * @param phoneNumber
     * @return
     */
    @GetMapping("/generate/{identity}/{phoneNumber}")
    public ResponseResult generate(@PathVariable("identity") int identity, @PathVariable("phoneNumber") String phoneNumber) {
        log.info("/generate/{identity}/{phoneNumber}:身份类型" + identity + ", 手机号：" + phoneNumber);
        return verifyCodeService.generate(identity, phoneNumber);
    }

    /**
     * 校验验证码是否正确
     * @param verifyCodeRequest
     * @return
     */
    @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
    public ResponseResult verifyCode(@RequestBody VerifyCodeRequest verifyCodeRequest) {
        log.info("/verifyCode :参数{}", JSON.toJSONString(verifyCodeRequest));

        // 组装参数
        int identity = verifyCodeRequest.getIdentity();
        String code = verifyCodeRequest.getCode();
        String phoneNumber = verifyCodeRequest.getPhoneNumber();
        return verifyCodeService.verify(identity, phoneNumber, code);
    }
}
