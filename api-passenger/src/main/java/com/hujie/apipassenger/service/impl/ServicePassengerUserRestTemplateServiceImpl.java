package com.hujie.apipassenger.service.impl;

import com.hujie.apipassenger.service.ServicePassengerUserRestTemplateService;
import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.servicepassengeruser.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: online-taxi-three
 * @ClassName ServicePassengerUserRestTemplateServiceImpl
 * @description:
 * @author: huJie
 * @create: 2021-08-08 18:08
 **/
@Service
public class ServicePassengerUserRestTemplateServiceImpl implements ServicePassengerUserRestTemplateService {

    private final String baseUrl = "http://SERVICE-PASSENGER-USER";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult login(String passengerPhone) {
        String url = baseUrl + "/auth/login";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassengerPhone(passengerPhone);

        return restTemplate.postForObject(url, loginRequest, ResponseResult.class);
    }
}
