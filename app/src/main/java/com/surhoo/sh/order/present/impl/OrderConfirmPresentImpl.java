package com.surhoo.sh.order.present.impl;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.surhoo.sh.bean.order.request.GetPayInfoBean;
import com.surhoo.sh.bean.order.request.GetPostageBean;
import com.surhoo.sh.bean.order.request.PayOrderBean;
import com.surhoo.sh.bean.order.response.OrderInfoBean;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.order.present.IOrderConfirmPresent;
import com.surhoo.sh.order.view.OrderConfirmView;

import java.util.List;

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
    public void getOrderInfo(List<GetPayInfoBean> bean) {

        String s = JSONObject.toJSONString(bean);
        NetworkReturnUtil.requestBeanResultUsePut(orderConfirmView,activity,Api.getPayInfo,s, OrderInfoBean.class);

    }

    @Override
    public void getPostage(String requestTag,GetPostageBean getPostageBean) {
        String s = JSONObject.toJSONString(getPostageBean);
        NetworkReturnUtil.requestStringResultUsePut(requestTag,orderConfirmView,activity,Api.GETORDERPOSTAGE,s);
    }

    @Override
    public void payOrder(String requestTag,PayOrderBean payOrderBean) {
        String s = JSONObject.toJSONString(payOrderBean);
        NetworkReturnUtil.requestStringResultUsePost(requestTag,orderConfirmView,activity,Api.payOrder,s);
    }

}
