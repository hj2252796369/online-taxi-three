package com.hujie.servicepassengeruser.controller;

import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.servicepassengeruser.request.LoginRequest;
import com.hujie.servicepassengeruser.service.PassengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: online-taxi-three
 * @ClassName AuthController
 * @description:
 * @author: huJie
 * @create: 2021-08-07 20:41
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private PassengerUserService passengerUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody LoginRequest loginRequest){
        String passengerPhone = loginRequest.getPassengerPhone();
        return passengerUserService.login(passengerPhone);

    }
}
