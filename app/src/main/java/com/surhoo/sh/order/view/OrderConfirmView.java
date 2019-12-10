package com.surhoo.sh.order.view;

import com.surhoo.sh.address.AddressBean;
import com.surhoo.sh.base.NoPageListBaseView;

public interface OrderConfirmView extends NoPageListBaseView<AddressBean> {


    void showPostage(String postage);

}
