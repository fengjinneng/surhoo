package com.surhoo.sh.order.present.impl;

import android.app.Activity;

import com.surhoo.sh.bean.order.response.OrderDetailReturnBean;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.order.present.OrderDetailPresent;
import com.surhoo.sh.order.view.OrderDetailView;

public class OrderDetailPresentImpl implements OrderDetailPresent {


    private Activity activity;
    private OrderDetailView orderDetailView;


    @Override
    public void bindView(Activity activity, OrderDetailView view) {
        this.activity =activity;
        this.orderDetailView = view;
    }

    @Override
    public void unBindView() {
    this.orderDetailView = null;
    }

    @Override
    public void getOrderDetail(int id) {


        NetworkReturnUtil.requestBeanResultUseGet(orderDetailView,activity, Api.getOrderDetail+"/"+id,null, OrderDetailReturnBean.class);

    }

    @Override
    public void cancelOrder(String requestTag, int id) {
        String s = "{\"id\":"+id+"}";
        NetworkReturnUtil.requestStringResultUsePut(requestTag,orderDetailView,activity,Api.cancelOrder,s);
    }

    @Override
    public void deleteOrder(String requestTag, int id) {
        String s = "{\"id\":"+id+"}";
        NetworkReturnUtil.requestStringResultUsePut(requestTag,orderDetailView,activity,Api.deleteOrder,s);
    }

    @Override
    public void payUseOrderNo(String requestTag, String orderNo) {
        String s = "{\"orderNo\":"+orderNo+",\"orderSource\":2}";
        NetworkReturnUtil.requestStringResultUsePost(requestTag,orderDetailView,activity,Api.payUseOrderNo,s);
    }

    @Override
    public void confirmOrder(String requestTag, int id) {
        String s = "{\"id\":"+id+"}";
        NetworkReturnUtil.requestStringResultUsePut(requestTag,orderDetailView,activity,Api.confirmOrder,s);
    }

}
