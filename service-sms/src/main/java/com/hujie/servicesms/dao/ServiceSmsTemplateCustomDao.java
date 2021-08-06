package com.hujie.servicesms.dao;

import com.hujie.servicesms.entity.ServiceSmsTemplate;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: online-taxi-three
 * @ClassName ServiceSmsTemplateCustomDao
 * @description:
 * @author: huJie
 * @create: 2021-08-06 21:11
 **/
@Mapper
public interface ServiceSmsTemplateCustomDao {
    ServiceSmsTemplate selectTemplateById(String templateId);
}
