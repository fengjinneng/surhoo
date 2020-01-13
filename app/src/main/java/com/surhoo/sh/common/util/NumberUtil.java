package com.surhoo.sh.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtil {


    public static String getTwoPointString(double d){
         DecimalFormat df = new DecimalFormat("0.00");

//        NumberFormat nf = NumberFormat.getNumberInstance();
//        nf.setMinimumFractionDigits(2);
        String str = df.format(d);
        return str;
    }
}
