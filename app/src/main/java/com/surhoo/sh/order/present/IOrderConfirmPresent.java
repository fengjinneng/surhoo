package com.surhoo.sh.order.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.order.bean.RequestPostageBean;
import com.surhoo.sh.order.view.OrderConfirmView;

public interface IOrderConfirmPresent extends BasePresenter<OrderConfirmView> {


    void getAddressInfo();

    void getPostage(RequestPostageBean postageBean);

}
