package com.surhoo.sh.order.view;

import com.surhoo.sh.base.BaseView;
import com.surhoo.sh.base.PagerBaseView;

public interface MyOrderListView extends PagerBaseView {


    void getDeleteOrderResult(int position);

    void getCancelOrderResult(int position);

}
