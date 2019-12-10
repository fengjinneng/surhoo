package com.surhoo.sh.order.present;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PutRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.address.AddressBean;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.DialogStringCallback;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.order.bean.RequestPostageBean;
import com.surhoo.sh.order.view.OrderConfirmView;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OrderConfirmPresentImpl implements IOrderConfirmPresent {


    private Activity activity;
    private OrderConfirmView orderConfirmView;


    @Override
    public void bindView(Activity activity, OrderConfirmView view) {
        this.activity = activity;
        this.orderConfirmView = view;
    }

    @Override
    public void unBindView() {
        orderConfirmView = null;
    }

    @Override
    public void getAddressInfo() {

        NetworkReturnUtil.requestList(orderConfirmView, activity, Api.ADDRESSLIST, new HttpParams(), AddressBean.class);

    }

    @Override
    public void getPostage(RequestPostageBean postageBean) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON,postageBean.toString());

        PutRequest<String> request = OkGo.<String>put(Api.GETORDERPOSTAGE)
                .tag(activity)
                .upRequestBody(requestBody)
                .headers("Authorization", activity.getResources().getString(R.string.Auth));


        StringCallback stringCallback = new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("GETORDERPOSTAGE", response.body());
                try {
                    if (response.code() == 200) {

                        orderConfirmView.showPostage(response.body());

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
                orderConfirmView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);

    }
}
