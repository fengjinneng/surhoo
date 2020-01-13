package com.surhoo.sh.order.present.impl;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.bean.order.response.OrderDetailReturnBean;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.order.present.MyOrderListPresent;
import com.surhoo.sh.order.view.MyOrderListView;

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
    public void getOrderInfo(String requestTag,int pageSize, int pageIndex, int orderStatus) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize",pageSize);
        httpParams.put("pageIndex",pageIndex);
        httpParams.put("orderStatus",orderStatus);

        NetworkReturnUtil.requestHavePageList(myOrderListView,activity, Api.getOrderList,httpParams, OrderDetailReturnBean.class,pageIndex);

    }

    @Override
    public void deleteOrder(String requestTag,int id) {
        String s = "{\"id\":"+id+"}";
        NetworkReturnUtil.requestStringResultUsePut(requestTag,myOrderListView,activity,Api.deleteOrder,s);
    }

    @Override
    public void cancelOrder(String requestTag,int id) {
        String s = "{\"id\":"+id+"}";
        NetworkReturnUtil.requestStringResultUsePut(requestTag,myOrderListView,activity,Api.cancelOrder,s);
    }

    @Override
    public void payUseOrderNo(String requestTag, String orderNo) {
        String s = "{\"orderNo\":"+orderNo+",\"orderSource\":2}";
        NetworkReturnUtil.requestStringResultUsePost(requestTag,myOrderListView,activity,Api.payUseOrderNo,s);
    }

    @Override
    public void confirmOrder(String requestTag, int id) {
        String s = "{\"id\":"+id+"}";
        NetworkReturnUtil.requestStringResultUsePut(requestTag,myOrderListView,activity,Api.confirmOrder,s);
    }
}
