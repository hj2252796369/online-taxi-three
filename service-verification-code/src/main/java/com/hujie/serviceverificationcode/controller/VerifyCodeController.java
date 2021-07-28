package com.hujie.serviceverificationcode.controller;

import com.hujie.internalcommon.dto.ResponseResult;
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
@RestController
@RequestMapping("/verify-code")
public class VerifyCodeController {

    @GetMapping("/generate/{identity}/{phoneNumber}")
    public ResponseResult generate(@PathVariable("identity") int identity, @PathVariable("phoneNumber") String phoneNumber) {
        return null;
    }
}
