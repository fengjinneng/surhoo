package com.surhoo.sh.order.present;

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
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.DialogStringCallback;
import com.surhoo.sh.common.util.MyJsonUtil;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.order.bean.OrderListBean;
import com.surhoo.sh.order.view.MyOrderListView;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MyOrderListPresentImpl implements MyOrderListPresent {


    private Activity activity;
    private MyOrderListView myOrderListView;


    @Override
    public void bindView(Activity activity, MyOrderListView view) {

        this.activity = activity;
        this.myOrderListView = view;
    }

    @Override
    public void unBindView() {
            myOrderListView = null;
    }


    @Override
    public void getOrderInfo(int pageSize, int pageIndex, int orderStatus) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize",pageSize);
        httpParams.put("pageIndex",pageIndex);
        httpParams.put("orderStatus",orderStatus);

        NetworkReturnUtil.requestPage(myOrderListView,activity, Api.getOrderList,httpParams, OrderListBean.class,pageIndex);

    }

    @Override
    public void deleteOrder(int id,int position) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String s = "{\"id\":"+id+"}";
        RequestBody requestBody = RequestBody.create(JSON,s );
        PutRequest<String> request = OkGo.<String>put(Api.deleteOrder)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("deleteOrder", response.body());
                try {
                    if (response.code() == 200) {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            if(StringUtils.isEmpty(jsonObject.getString("msg"))){
                            }else {
                                myOrderListView.showToastMsg(jsonObject.getString("msg"));
                                return;
                            }
                        }
                        myOrderListView.getDeleteOrderResult(position);
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
                myOrderListView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);
    }

    @Override
    public void cancelOrder(int id, int position) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String s = "{\"id\":"+id+"}";
        RequestBody requestBody = RequestBody.create(JSON,s );
        PutRequest<String> request = OkGo.<String>put(Api.cancelOrder)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("cancelOrder", response.body());
                try {
                    if (response.code() == 200) {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            if(StringUtils.isEmpty(jsonObject.getString("msg"))){
                            }else {
                                myOrderListView.showToastMsg(jsonObject.getString("msg"));
                                return;
                            }
                        }
                        myOrderListView.getCancelOrderResult(position);
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
                myOrderListView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);

    }
}
