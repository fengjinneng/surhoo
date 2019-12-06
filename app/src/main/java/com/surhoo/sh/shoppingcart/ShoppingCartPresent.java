package com.surhoo.sh.shoppingcart;

import android.widget.TextView;

import com.surhoo.sh.base.BasePresenter;

public interface ShoppingCartPresent extends BasePresenter<ShoppingCartView> {


    void requestData();


    void changeShoppingCarNum(boolean isAdd, int id, int goodsNum, ShoppingCartBean.CarGoodsListBean bean, TextView num);


    void deleteShoppingCart(String idList);

}
