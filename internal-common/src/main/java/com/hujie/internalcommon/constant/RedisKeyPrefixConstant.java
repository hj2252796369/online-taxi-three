package com.hujie.internalcommon.constant;

/**
 * @program: online-taxi-three
 * @ClassName RedisKeyPrefixConstant
 * @description:
 * @author: huJie
 * @create: 2021-08-04 22:22
 **/
public class RedisKeyPrefixConstant {
    /**
     * 乘客登录验证码 key前缀
     */
    public static final String PASSENGER_LOGIN_CODE_KEY_PRE = "passenger_login_code_";

    public static final String PASSENGER_LOGIN_TOKEN_APP_KEY_PRE = "passenger_login_token_app_";

    public static final String PASSENGER_LOGIN_TOKEN_WEIXIN_KEY_PRE = "passenger_login_token_weixin_";

    /**
     * 司机登录验证码 key前缀
     */
    public static final String DRIVER_LOGIN_CODE_KEY_PRE = "driver_login_code_";

}
