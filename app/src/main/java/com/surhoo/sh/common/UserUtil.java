package com.surhoo.sh.common;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;

public class UserUtil {

    public static boolean isLogin(){

        if(StringUtils.isEmpty(SPUtils.getInstance().getString("token"))){
            return false;
        }
        return true;

    }

}
