package com.surhoo.sh.shoppingcart;

import android.app.Activity;
import android.app.Dialog;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PutRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.DialogStringCallback;
import com.surhoo.sh.common.util.NetworkReturnUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;

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

        NetworkReturnUtil.requestList(shoppingCartView, activity, Api.SHOPPINGCART, null, ShoppingCartBean.class);

    }

    @Override
    public void changeShoppingCarNum(boolean isAdd, int id, int goodsNum, ShoppingCartBean.CarGoodsListBean bean, TextView num) {

        PutRequest<String> request = OkGo.<String>put(Api.CHANGESHOPPINGCARTNUMBER+"?id="+id+"&goodsNum="+goodsNum)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth));

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("CHANGESHOPPINGCARTNUMBER", response.body());
                try {
                    if (response.code() == 200) {

                        shoppingCartView.changShoppingCarNum(isAdd, id, goodsNum, bean, num);

                    } else {
                        ToastUtils.showShort("啊哦，出现错误了！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                shoppingCartView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);

    }

    @Override
    public void deleteShoppingCart(String s) {

        PutRequest<String> request = OkGo.<String>put(Api.DELETESHOPPINGCARTNUMBER+"?idList="+s)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth));


        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("CHANGESHOPPINGCARTNUMBER", response.body());
                try {
                    if (response.code() == 200) {
//                        JSONObject jsonObject = JSONObject.parseObject(response.body());
//                        if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
//                            ToastUtils.showShort(jsonObject.getString("msg"));
//                            return;
//                        }

                        shoppingCartView.deleteShoppingCart();

                    } else {
                        ToastUtils.showShort("啊哦，出现错误了！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                shoppingCartView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);


    }

}
