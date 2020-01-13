package com.surhoo.sh.order.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.order.view.MyOrderListView;

public interface MyOrderListPresent extends BasePresenter<MyOrderListView> {

    void getOrderInfo(String requestTag,int pageSize,int pageIndex,int orderStatus);

    void deleteOrder(String requestTag,int id);

    void cancelOrder(String requestTag,int id);

    void payUseOrderNo(String requestTag,String orderId);

    void confirmOrder(String requestTag,int id);

}
