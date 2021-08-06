package com.hujie.servicesms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hujie.internalcommon.dto.ResponseResult;
import com.hujie.internalcommon.dto.servicesms.SmsTemplateDto;
import com.hujie.internalcommon.dto.servicesms.request.SmsSendRequest;
import com.hujie.servicesms.constant.SmsStatusEnum;
import com.hujie.servicesms.dao.ServiceSmsRecordDao;
import com.hujie.servicesms.dao.ServiceSmsTemplateCustomDao;
import com.hujie.servicesms.entity.ServiceSmsRecord;
import com.hujie.servicesms.entity.ServiceSmsTemplate;
import com.hujie.servicesms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: online-taxi-three
 * @ClassName SmsServiceImpl
 * @description:
 * @author: huJie
 * @create: 2021-08-06 21:01
 **/
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    /**
     *   缓存用于替换内容的模板
     */
    private Map<String, String> templateMaps = new HashMap<String, String>();

    @Autowired
    ServiceSmsTemplateCustomDao serviceSmsTemplateCustomDao;


    @Autowired
    private ServiceSmsRecordDao serviceSmsRecordDao;

    @Override
    public ResponseResult sendSms(SmsSendRequest smsSendRequest) {
        log.info("SmsServiceImpl -> sendSms start....");
        // 需要发送的手机号
        for (String phoneNumber : smsSendRequest.getReceivers()) {
            // 发送模板即内容
            List<SmsTemplateDto> templateDtos = smsSendRequest.getData();

            ServiceSmsRecord sms = new ServiceSmsRecord();
            sms.setPhoneNumber(phoneNumber);

            // 缓存的大小是按照存储模板的字段长度计算的
            for (SmsTemplateDto smsTemplate : templateDtos) {
                // 从DB中加载模板到内存中缓存
                if(!templateMaps.containsKey(smsTemplate.getTemplateId())){
                    //此处注释掉的内容为，将db模板加载到内存
                    ServiceSmsTemplate t = serviceSmsTemplateCustomDao.selectTemplateById(smsTemplate.getTemplateId());
                    templateMaps.put(smsTemplate.getTemplateId(), t.getTemplateContent());
                }
                // 组装内容
                String templateContent = templateMaps.get(smsTemplate.getTemplateId());
                for (Map.Entry<String, Object> entry : smsTemplate.getTemplateMap().entrySet()) {
                    templateContent = StringUtils.replace(templateContent, "${" + entry.getKey() + "}", "" + entry.getValue());
                }

                // 当其中一个发送错误，内部消化，不影响其他短信的 发送
                try {
                    int result = send(phoneNumber, smsTemplate.getTemplateId(), smsTemplate.getTemplateMap());

                    // 组装SMS对象
                    sms.setSendTime(new Date());
                    sms.setSmsContent(templateContent);
                    sms.setOperatorName("");
                    sms.setSendFlag(1);
                    sms.setSendNumber(0);
                    if(result != SmsStatusEnum.SEND_SUCCESS.getCode()){
                        throw new Exception("短信发送失败");
                    }
                } catch (Exception e) {
                    sms.setSendFlag(0);
                    sms.setSendNumber(1);
                    log.error("发送短信（" + smsTemplate.getTemplateId() + "）失败：" + phoneNumber, e);
                }finally {
                    sms.setCreateTime(new Date());
                    serviceSmsRecordDao.insert(sms);
                }
            }
        }
        log.info("SmsServiceImpl -> sendSms end....");
        return ResponseResult.success("");
    }

    private int send(String phoneNumber, String templateId, Map<String, Object> templateMap) throws Exception{
        JSONObject param = new JSONObject();
        param.putAll(templateMap);

        return sendMsg(phoneNumber, templateId, param.toString());
    }

    private int sendMsg(String phoneNumber, String templateId, String toString)  throws Exception{
        /**
         *  供应商 发 短信 这里直接返回成功
         */
        log.info("供应商发送信息：电话号：{},模板号:{}, 内容:{}",phoneNumber, templateId, toString );
        return SmsStatusEnum.SEND_SUCCESS.getCode();
    }
}
