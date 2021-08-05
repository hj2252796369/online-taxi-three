package com.hujie.apipassenger.controller;

import com.hujie.apipassenger.request.ShortMsgRequest;
import com.hujie.apipassenger.service.VerificationCodeService;
import com.hujie.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: online-taxi-three
 * @ClassName VerificationCodeController
 * @description:
 * @author: huJie
 * @create: 2021-08-05 22:02
 **/
@RestController
@RequestMapping("/verify-code")
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping("/send")
    public ResponseResult sendCode(@RequestBody @Validated ShortMsgRequest shortMsgRequest){
        return verificationCodeService.sendCode(shortMsgRequest.getPhoneNumber());
    }

}
