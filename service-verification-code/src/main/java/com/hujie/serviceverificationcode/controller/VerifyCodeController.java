package com.hujie.serviceverificationcode.controller;

import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.serviceverificationcode.service.VerifyCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param identity
     * @param phoneNumber
     * @return
     */
    @GetMapping("/generate/{identity}/{phoneNumber}")
    public ResponseResult generate(@PathVariable("identity") int identity, @PathVariable("phoneNumber") String phoneNumber) {
        log.info("/generate/{identity}/{phoneNumber}:身份类型" + identity + ", 手机号：" + phoneNumber);
        return verifyCodeService.generate(identity, phoneNumber);
    }
}
