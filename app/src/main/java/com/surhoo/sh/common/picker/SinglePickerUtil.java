package com.surhoo.sh.common.picker;

import android.app.Activity;

import com.surhoo.sh.R;

import java.util.ArrayList;

import cn.qqtheme.framework.picker.SinglePicker;

public class SinglePickerUtil {


    public static SinglePicker getSinglePicker(Activity activity, ArrayList<String> list) {
        SinglePicker<String> picker = new SinglePicker<String>(activity, list);

        picker.setCancelTextColor(activity.getResources().getColor(R.color.themeColor));
        picker.setSubmitTextColor(activity.getResources().getColor(R.color.themeColor));
        /**  标题与列表的分割线 **/
        picker.setTopLineColor(activity.getResources().getColor(R.color.themeColor));
        picker.setTextColor(activity.getResources().getColor(R.color.themeColor));//设置省市县字体滚动颜色
        picker.setDividerColor(activity.getResources().getColor(R.color.themeColor));//设置分割线的颜色

        return picker;
    }
}
