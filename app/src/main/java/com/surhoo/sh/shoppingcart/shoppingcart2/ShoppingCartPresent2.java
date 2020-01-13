package com.surhoo.sh.shoppingcart.shoppingcart2;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.bean.shoppingcar.request.ChangeCarNumberBean;

public interface ShoppingCartPresent2 extends BasePresenter<ShoppingCart2View> {


    void requestData();


    void changeShoppingCarNum(String requestTag, ChangeCarNumberBean bean);

    void deleteShoppingCart(String requestTag, String idList);

}
