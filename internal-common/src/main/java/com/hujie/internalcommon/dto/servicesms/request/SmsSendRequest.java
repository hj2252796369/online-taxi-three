package com.hujie.internalcommon.dto.servicesms.request;

import com.hujie.internalcommon.dto.servicesms.SmsTemplateDto;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @program: online-taxi-three
 * @ClassName SmsSendRequest
 * @description:
 * @author: huJie
 * @create: 2021-08-06 20:53
 **/
@Data
public class SmsSendRequest {
    private String[] receivers;
    private List<SmsTemplateDto> data;

    @Override
    public String toString() {
        return "SmsSendRequest{" +
                "receivers=" + Arrays.toString(receivers) +
                ", data=" + data +
                '}';
    }
}
