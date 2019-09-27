package com.surhoo.sh.shoppingcart;

import android.content.Context;

import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;

public class ShoppingCartPresentImpl implements ShoppingCartPresent {


    private Context context;

    private ShoppingCartView shoppingCartView;

    @Override
    public void bindView(Context ctx, ShoppingCartView view) {
        this.context = ctx;
        this.shoppingCartView = view;
    }

    @Override
    public void unBindView() {
        shoppingCartView =  null;
    }


    @Override
    public void requestData() {

        NetworkReturnUtil.requestList(shoppingCartView,context, Api.SHOPPINGCART,null,SBBBB.class);

    }
}
