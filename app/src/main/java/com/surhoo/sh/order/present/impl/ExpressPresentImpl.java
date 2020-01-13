package com.surhoo.sh.order.present.impl;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.bean.order.response.ExpressBean;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.order.present.IExpressPresent;
import com.surhoo.sh.order.view.ExpressView;

public class ExpressPresentImpl implements IExpressPresent {

    private Activity activity;
    private ExpressView expressView;


    @Override
    public void bindView(Activity activity, ExpressView view) {

        this.activity =activity;
        this.expressView =view;
    }

    @Override
    public void unBindView() {
        this.expressView = null;
    }


    @Override
    public void getExpressInfo(String expressCode, String expressNumber) {



        HttpParams httpParams = new HttpParams();
        httpParams.put("expressCode",expressCode);
        httpParams.put("expressNumber",expressNumber);

        NetworkReturnUtil.requestBeanResultUseGet(expressView,activity, Api.checkExpress,httpParams, ExpressBean.class);

    }
}
