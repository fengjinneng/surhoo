package com.surhoo.sh.order.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.order.view.OrderDetailView;

public interface OrderDetailPresent extends BasePresenter<OrderDetailView> {



    void getOrderDetail(int id);

    void cancelOrder(String requestTag,int id);

    void deleteOrder(String requestTag,int id);

    void payUseOrderNo(String requestTag, String orderNo);

    void confirmOrder(String requestTag, int id) ;

}
