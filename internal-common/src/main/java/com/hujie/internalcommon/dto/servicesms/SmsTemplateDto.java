package com.hujie.internalcommon.dto.servicesms;

import lombok.Data;

import java.util.Map;

/**
 * @program: online-taxi-three
 * @ClassName SmsTemplateDto
 * @description:
 * @author: huJie
 * @create: 2021-08-06 20:53
 **/
@Data
public class SmsTemplateDto {
    // 模板ID
    private String templateId;

    // 对应的填充参数和值
    private Map<String, Object> templateMap;

    @Override
    public String toString() {
        return "SmsTemplateDto{" +
                "templateId='" + templateId + '\'' +
                ", data=" + templateMap +
                '}';
    }
}
