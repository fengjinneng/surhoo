package com.surhoo.sh.shoppingcart;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.surhoo.sh.bean.shoppingcar.request.ChangeCarNumberBean;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;

public class ShoppingCartPresentImpl implements ShoppingCartPresent {


    private Activity activity;

    private ShoppingCartView shoppingCartView;

    @Override
    public void bindView(Activity activity, ShoppingCartView view) {
        this.activity = activity;
        this.shoppingCartView = view;
    }

    @Override
    public void unBindView() {
        shoppingCartView = null;
    }


    @Override
    public void requestData() {

        NetworkReturnUtil.requestNoPageList("", shoppingCartView, activity, Api.SHOPPINGCART, null, ShoppingCartBean.class);

    }

    @Override
    public void changeShoppingCarNum(String requestTag, ChangeCarNumberBean bean) {

        String s = JSONObject.toJSONString(bean);

        NetworkReturnUtil.requestStringResultUsePut(requestTag, shoppingCartView, activity, Api.SHOPPINGCART, s);

    }

    @Override
    public void deleteShoppingCart(String requestTag, String s) {


        NetworkReturnUtil.requestStringResultUsePut(requestTag, shoppingCartView, activity, Api.DELETESHOPPINGCARTNUMBER, s);


    }

}
