-- auto-generated definition
create table service_sms_record
(
    id            int auto_increment
        primary key,
    phone_number  varchar(255) null comment '乘客手机号',
    sms_content   varchar(255) null comment '短信内容',
    send_time     timestamp    null comment '发送时间',
    operator_name varchar(255) null comment '操作人',
    send_flag     int          null comment '发送状态 0:失败  1: 成功',
    send_number   int          null comment '发送失败次数',
    create_time   timestamp    null,
    update_time   timestamp    null
);

/
-- auto-generated definition
create table service_sms_template
(
    id               int          not null
        primary key,
    template_id      varchar(255) null comment '短信模板ID',
    template_name    varchar(255) null comment '模板名称',
    template_content varchar(255) null comment '模板内容',
    template_type    int          null comment '模板类型（1：营销；2：通知；3：订单）',
    create_time      timestamp    null,
    update_time      timestamp    null
);

