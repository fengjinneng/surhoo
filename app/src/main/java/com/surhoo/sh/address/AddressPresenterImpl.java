package com.surhoo.sh.address;

import android.app.Activity;
import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;

public class AddressPresenterImpl implements AddressPresenter {


    private Activity activity;

    private AddressView addressView;

    @Override
    public void requestData() {
        NetworkReturnUtil.requestList(addressView,activity,Api.ADDRESSLIST,null,AddressBean.class);
    }

    @Override
    public void deleteAddress(int id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id",id);
        NetworkReturnUtil.requestOne(addressView,activity,Api.ADDRESSDELETE,httpParams,AddressBean.class);
    }

    @Override
    public void bindView(Activity activity, AddressView view) {
            this.activity = activity;
            this.addressView = view;
    }

    @Override
    public void unBindView() {
        addressView = null;
    }
}
