package com.surhoo.sh.address;

import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;

public class AddressPresenterImpl implements AddressPresenter {


    private Context context;

    private AddressView addressView;

    @Override
    public void requestData() {
        NetworkReturnUtil.requestList(addressView,context,Api.ADDRESSLIST,null,AddressBean.class);
    }

    @Override
    public void deleteAddress(int id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id",id);
        NetworkReturnUtil.requestOne(addressView,context,Api.ADDRESSDELETE,httpParams,AddressBean.class);
    }

    @Override
    public void bindView(Context ctx, AddressView view) {
            this.context = ctx;
            this.addressView = view;
    }

    @Override
    public void unBindView() {
        addressView = null;
    }
}
