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
    public void requestData() {
        NetworkReturnUtil.requestList(addressView,activity,Api.ADDRESSLIST,null, AddressBean.class);
    }

    @Override
    public void deleteAddress(int id,int position) {


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String s = "{\"id\":"+id+"}";

        RequestBody requestBody = RequestBody.create(JSON,s );

       PutRequest<String> request = OkGo.<String>put(Api.deleteAddress)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("deleteAddress", response.body());
                try {
                    if (response.code() == 200) {

                        addressView.getDeleteResult(position);


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
                addressView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);

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
