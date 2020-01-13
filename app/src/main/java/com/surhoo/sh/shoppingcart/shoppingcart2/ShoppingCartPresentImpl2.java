package com.surhoo.sh.shoppingcart.shoppingcart2;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.surhoo.sh.bean.shoppingcar.request.ChangeCarNumberBean;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
public class ShoppingCartPresentImpl2 implements ShoppingCartPresent2 {


    private Activity activity;

    private ShoppingCart2View shoppingCart2View;

    @Override
    public void bindView(Activity activity, ShoppingCart2View view) {
        this.activity = activity;
        this.shoppingCart2View = view;
    }

    @Override
    public void unBindView() {
        shoppingCart2View = null;
    }


    @Override
    public void requestData() {

        NetworkReturnUtil.requestNoPageList("", shoppingCart2View, activity, Api.SHOPPINGCART, null, ShoppingCartBean2.class);

    }

    @Override
    public void changeShoppingCarNum(String requestTag, ChangeCarNumberBean bean) {

        String s = JSONObject.toJSONString(bean);

        NetworkReturnUtil.requestStringResultUsePut(requestTag, shoppingCart2View, activity, Api.SHOPPINGCART, s);

    }

    @Override
    public void deleteShoppingCart(String requestTag, String s) {


        NetworkReturnUtil.requestStringResultUsePut(requestTag, shoppingCart2View, activity, Api.DELETESHOPPINGCARTNUMBER, s);


    }

}
