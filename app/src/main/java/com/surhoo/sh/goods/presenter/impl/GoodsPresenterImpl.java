package com.surhoo.sh.goods.presenter.impl;

import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.presenter.GoodsPresenter;
import com.surhoo.sh.goods.view.GoodsView;

public class GoodsPresenterImpl implements GoodsPresenter {

    private static final String TAG = "GoodsPresenterImpl";

    GoodsView goodsView;
    Context context;

    @Override
    public void bindView(Context ctx, GoodsView view) {
        this.context = ctx;
        this.goodsView = view;
    }

    @Override
    public void unBindView() {
        goodsView = null;
    }

    /**
     * @param classifyld 一级分类或者二级分类id
     * @param pageSize
     * @param pageIndex
     * @param sortType   1 综合 2销量 3 价格
     */
    @Override
    public void requestData(int from, int classifyld, int pageSize, int pageIndex, int sortType) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize", pageSize);
        httpParams.put("pageIndex", pageIndex);

        switch (from) {
            case 1:
                httpParams.put("classifyId", classifyld);
                httpParams.put("sortType", sortType);
                NetworkReturnUtil.requestPage(goodsView, context, Api.GOODSLIST, httpParams, GoodsBean.class, pageIndex);
                break;
            case 2:
                httpParams.put("classifyId", classifyld);
                httpParams.put("sortType", sortType);
                NetworkReturnUtil.requestPage(goodsView, context, Api.SHOPGOODS, httpParams, GoodsBean.class, pageIndex);
                break;
            case 3:
                break;

            case 4:
                httpParams.put("type", 1);
                NetworkReturnUtil.requestPage(goodsView, context, Api.COLLECT, httpParams, GoodsBean.class, pageIndex);
                break;
        }
    }
}
