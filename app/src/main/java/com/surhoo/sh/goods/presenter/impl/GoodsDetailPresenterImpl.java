package com.surhoo.sh.goods.presenter.impl;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.goods.bean.GoodDetailBean;
import com.surhoo.sh.goods.presenter.GoodsDetailPresenter;
import com.surhoo.sh.goods.view.GoodsDetailView;

public class GoodsDetailPresenterImpl implements GoodsDetailPresenter {

    GoodsDetailView goodsDetailView;
    GoodDetailBean goodsBean;
    Context context;

    private static final String TAG = "GoodsDetailPresenterImpl";



    @Override
    public void bindView(Context ctx, GoodsDetailView view) {
        this.context = ctx;
        this.goodsDetailView = view;
    }

    @Override
    public void unBindView() {
        goodsDetailView = null;
    }


    @Override
    public void requestData(int id) {
        String url = Api.GOODSDETAIL;
        GetRequest<String> request = OkGo.<String>get(url)
                .tag(context)
                .headers("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJuaWNrbmFtZSI6IlJIRVRUIiwiaGVhZGltZ3VybCI6Imh0dHBzOi8vd3gucWxvZ28uY24vbW1vcGVuL3ZpXzMyL3R3MUxPU0piT1h6aFhPOERTOWljajZBdXhRRVNEakhsWU1uOHZJaGxDNGlhc0Qza055U0UyYlM2VUY5OU9hTWx0Qjl4dEJmdmtJUEFHRmNBV28yeW9taWNBLzEzMiIsImlkIjoxMDAxMDA4NSwiZXhwIjoxNTY5MjA0MDMzLCJvcGVuaWQiOiJvbDR5bDVQNHl3MmozTmJqa1UzMHkyVnJhMEh3IiwiYXBwaWQiOiJ3eDQxMDU2ODQ5OGYzYzljYmEifQ.UAmnuKj__D2XHWIIQDkOv7omnZUSyEcCTmRb-xhaAA8NdewES3YLl6df2JUQODIzvvImJzQ9XiDf-bwELIUIWQ")
                .params("id", id);


        StringCallback stringCallback = new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = JSONObject.parseObject(response.body());
                        if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                            ToastUtils.showShort(jsonObject.getString("msg"));
                            return;
                        }
                        goodsBean = jsonObject.toJavaObject(GoodDetailBean.class);
                        goodsDetailView.show(goodsBean);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                goodsDetailView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);


    }

    @Override
    public void showSpec() {
        if(ObjectUtils.isEmpty(goodsBean)){
            return;
        }
        goodsDetailView.showSpec(goodsBean);
    }
}
