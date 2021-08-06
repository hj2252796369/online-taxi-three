-- auto-generated definition
create table service_passenger_user_info
(
    id               int          not null
        primary key,
    register_date    timestamp    null comment '注册日期',
    passenger_phone  varchar(255) null comment '乘客手机号',
    passenger_name   varchar(255) null comment '乘客姓名',
    passenger_gender tinyint      null comment '性别。1：男，0：女',
    user_state       tinyint      null comment '用户状态：1：有效，0：失效',
    create_time      timestamp    null,
    update_time      timestamp    null
);

