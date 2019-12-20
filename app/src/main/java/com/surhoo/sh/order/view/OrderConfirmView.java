package com.surhoo.sh.order.view;

import com.surhoo.sh.address.bean.AddressBean;
import com.surhoo.sh.base.NoPageListBaseView;

public interface OrderConfirmView extends NoPageListBaseView<AddressBean> {


    void showPostage(String postage);


    void getPayOrderResult();

}
