package com.hujie.apipassenger.service.impl;

import com.hujie.apipassenger.service.ServiceSmsRestTemplateService;
import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.servicesms.SmsTemplateDto;
import com.hujie.internalcommon.dto.servicesms.request.SmsSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: online-taxi-three
 * @ClassName ServiceSmsRestTemplateServiceImpl
 * @description:
 * @author: huJie
 * @create: 2021-08-06 20:46
 **/
@Service
public class ServiceSmsRestTemplateServiceImpl implements ServiceSmsRestTemplateService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public ResponseResult sendSms(String phoneNumber, String code) {
        String url = "http://service-sms/send/sms-template";
        SmsSendRequest smsSendRequest = new SmsSendRequest();
        String[] phoneNumbers = new String[] {phoneNumber};
        smsSendRequest.setReceivers(phoneNumbers);

        List<SmsTemplateDto> data = new ArrayList<SmsTemplateDto>();
        SmsTemplateDto dto = new SmsTemplateDto();
        dto.setTemplateId("SMS_153055065");
        int templateSize = 1;
        HashMap<String, Object> templateMap = new HashMap<String, Object>(templateSize);
        templateMap.put("code", code);
        dto.setTemplateMap(templateMap);
        data.add(dto);

        smsSendRequest.setData(data);

        return restTemplate.postForObject(url, smsSendRequest, ResponseResult.class);
    }
}
