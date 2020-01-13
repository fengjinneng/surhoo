package com.surhoo.sh.address.present;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.PutRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.address.bean.AddressBean;
import com.surhoo.sh.address.view.EditAddressView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.DialogStringCallback;
import com.surhoo.sh.common.util.MyJsonUtil;
import com.surhoo.sh.common.util.NetworkReturnUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EditAddressPresentImpl implements EditAddressPresent {


    private Activity activity;
    private EditAddressView editAddressView;

    @Override
    public void bindView(Activity activity, EditAddressView view) {

        this.activity = activity;
        this.editAddressView = view;

    }

    @Override
    public void unBindView() {
        editAddressView = null;
    }


    @Override
    public void addAddress(String requestTag,AddressBean addressBean) {
        String s = JSONObject.toJSONString(addressBean);
        NetworkReturnUtil.requestStringResultUsePost(requestTag,editAddressView,activity,Api.ADDADDRESS,s);
    }

    @Override
    public void updateAddress(String requestTag,AddressBean addressBean) {
        String s = JSONObject.toJSONString(addressBean);
        NetworkReturnUtil.requestStringResultUsePut(requestTag,editAddressView,activity,Api.updateAddress,s);


    }
}
