package com.surhoo.sh.order.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.bean.order.request.GetPayInfoBean;
import com.surhoo.sh.bean.order.request.GetPostageBean;
import com.surhoo.sh.bean.order.request.PayOrderBean;
import com.surhoo.sh.order.view.OrderConfirmView;

import java.util.List;

public interface IOrderConfirmPresent extends BasePresenter<OrderConfirmView> {



    void getOrderInfo(List<GetPayInfoBean> bean);

    void getPostage (String requestTag,GetPostageBean getPostageBean);


    void payOrder(String requestTag,PayOrderBean payOrderBean);

}
