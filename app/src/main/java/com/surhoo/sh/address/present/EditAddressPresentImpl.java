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
    public void addAddress(AddressBean addressBean) {


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String s = JSONObject.toJSONString(addressBean);
        RequestBody requestBody = RequestBody.create(JSON, s);

        PostRequest<String> request = OkGo.<String>post(Api.ADDADDRESS)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("ADDADDRESS", response.body());
                try {
                    if (response.code() == 200) {

                        editAddressView.getAddResult();

                    } else {
                        ToastUtils.showShort("啊哦，出现错误了！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        };

        request.execute(stringCallback);

    }

    @Override
    public void updateAddress(AddressBean addressBean) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String s = JSONObject.toJSONString(addressBean);
        RequestBody requestBody = RequestBody.create(JSON, s);

        PutRequest<String> request = OkGo.<String>put(Api.updateAddress)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("updateAddress", response.body());
                try {
                    if (response.code() == 200) {

                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            editAddressView.showToastMsg(jsonObject.getString("msg"));
                            return;
                        }

                        editAddressView.getUpdateResult();

                    } else {
                        ToastUtils.showShort("啊哦，出现错误了！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        };

        request.execute(stringCallback);
    }
}
