-- auto-generated definition
create table service_sms_record
(
    id            int auto_increment
        primary key,
    phone_number  varchar(255) null,
    sms_content   varchar(255) null,
    send_time     timestamp    null,
    operator_name varchar(255) null,
    send_flag     int          null,
    send_number   int          null,
    create_time   timestamp    null,
    update_time   timestamp    null
);

/
-- auto-generated definition
create table service_sms_template
(
    id               int          not null
        primary key,
    template_id      varchar(255) null,
    template_name    varchar(255) null,
    template_content varchar(255) null,
    template_type    int          null,
    create_time      timestamp    null,
    update_time      timestamp    null
);

