package com.surhoo.sh.order.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.order.view.ExpressView;

public interface IExpressPresent extends BasePresenter<ExpressView> {

    void getExpressInfo(String expressCode,String expressNumber);

}
