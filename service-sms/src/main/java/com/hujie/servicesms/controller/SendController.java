package com.hujie.servicesms.controller;

import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.servicesms.request.SmsSendRequest;
import com.hujie.servicesms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: online-taxi-three
 * @ClassName SendController
 * @description:
 * @author: huJie
 * @create: 2021-08-06 20:50
 **/
@RestController
@RequestMapping("/send")
@Slf4j
public class SendController {

    @Autowired
    private SmsService smsService;

    @RequestMapping(value = "/sms-template", method = RequestMethod.POST)
    public ResponseResult send(@RequestBody SmsSendRequest smsSendRequest){
        log.info("/send/sms-template,request:" + smsSendRequest.toString());
        return smsService.sendSms(smsSendRequest);
    }
}
