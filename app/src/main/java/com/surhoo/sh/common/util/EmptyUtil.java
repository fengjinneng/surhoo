package com.surhoo.sh.common.util;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;

public class EmptyUtil {



    public static String isStringEmpty(String s){

        return StringUtils.isEmpty(s) ? "":s;
    }

    public static Integer isInterEmpty(Integer i){

        return ObjectUtils.isEmpty(i) ? 0:i;
    }

}
