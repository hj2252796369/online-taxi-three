package com.hujie.apipassenger.controller;

import com.hujie.apipassenger.request.UserAuthRequest;
import com.hujie.apipassenger.service.AuthService;
import com.hujie.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: online-taxi-three
 * @ClassName AuthController
 * @description:
 * @author: huJie
 * @create: 2021-08-06 22:24
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 用户登录
     * @param userAuthRequest
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody @Validated UserAuthRequest userAuthRequest){
        String passengerPhone = userAuthRequest.getPassengerPhone();
        String code = userAuthRequest.getCode();

        return authService.auth(passengerPhone, code);
    }
}
