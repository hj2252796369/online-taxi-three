package com.hujie.servicepassengeruser.dao;

import com.hujie.servicepassengeruser.entity.ServicePassengerUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PassengerUserInfoCustomDao {
    ServicePassengerUserInfo selectPassengerUserInfoByPhone(@Param("passengerPhone") String passengerPhone);

    void insertSelective(ServicePassengerUserInfo passengerUserInfo);
}
