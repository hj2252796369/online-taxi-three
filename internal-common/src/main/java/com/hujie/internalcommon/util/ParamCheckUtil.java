package com.hujie.internalcommon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: online-taxi-three
 * @ClassName ParamCheckUtil
 * @description:
 * @author: huJie
 * @create: 2021-07-29 21:49
 **/
public class ParamCheckUtil {
    private static String phoneReg = "^[1][3,4,5,7,8][0-9]{9}$";

    public static boolean checkParamIsPhone(String phoneStr){
        Pattern pattern = Pattern.compile(phoneReg);
        Matcher matcher = pattern.matcher(phoneStr);
        boolean isMatch = matcher.matches();
        return isMatch;
    }
}
