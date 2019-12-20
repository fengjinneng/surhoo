package com.surhoo.sh.order.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.order.view.MyOrderListView;

public interface MyOrderListPresent extends BasePresenter<MyOrderListView> {

    void getOrderInfo(int pageSize,int pageIndex,int orderStatus);



    void deleteOrder(int id,int position);

    void cancelOrder(int id,int position);

}
