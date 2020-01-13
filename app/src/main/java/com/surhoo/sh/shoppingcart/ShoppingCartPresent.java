package com.surhoo.sh.shoppingcart;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.bean.shoppingcar.request.ChangeCarNumberBean;
import com.surhoo.sh.shoppingcart.shoppingcart2.ShoppingCart2View;

public interface ShoppingCartPresent extends BasePresenter<ShoppingCartView> {


    void requestData();


    void changeShoppingCarNum(String requestTag,ChangeCarNumberBean bean);

    void deleteShoppingCart(String requestTag,String idList);

}
