package com.surhoo.sh.address.present;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PutRequest;
import com.surhoo.sh.App;
import com.surhoo.sh.R;
import com.surhoo.sh.address.bean.AddressBean;
import com.surhoo.sh.address.view.AddressView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.DialogStringCallback;
import com.surhoo.sh.common.util.NetworkReturnUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddressPresenterImpl implements AddressPresenter {


    private Activity activity;

    private AddressView addressView;

    @Override
    public void requestAddressList(String requestTag) {
        NetworkReturnUtil.requestNoPageList(requestTag,addressView,activity,Api.ADDRESSLIST,null, AddressBean.class);
    }

    @Override
    public void deleteAddress(String requestTag,int id) {

        String s = "{\"id\":"+id+"}";
        NetworkReturnUtil.requestStringResultUsePut(requestTag,addressView,activity, Api.deleteAddress,s);

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
