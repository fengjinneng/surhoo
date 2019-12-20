package com.surhoo.sh.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.StringUtils;

public class MyJsonUtil {


    public static boolean isJson(String s) {

        if(StringUtils.isEmpty(s)){
            return false;
        }

        try {
            JSONObject.parseObject(s);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public static boolean isJsonArray(String s) {

        if(StringUtils.isEmpty(s)){
            return false;
        }
        try {
            JSONArray.parseArray(s);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
