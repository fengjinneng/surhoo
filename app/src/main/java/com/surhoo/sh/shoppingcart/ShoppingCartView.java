package com.surhoo.sh.shoppingcart;

import android.widget.TextView;

import com.surhoo.sh.base.NoPageListBaseView;

public interface ShoppingCartView extends NoPageListBaseView<ShoppingCartBean> {


   void changShoppingCarNum(boolean isAdd, int id, int goodsNum, ShoppingCartBean.CarGoodsListBean bean, TextView num);


   void deleteShoppingCart();

}
