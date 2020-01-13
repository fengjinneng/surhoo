package com.surhoo.sh.goods.presenter.impl;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.presenter.GoodsListPresenter;
import com.surhoo.sh.goods.view.GoodsListView;

public class GoodsPresenterImpl implements GoodsListPresenter {

    private static final String TAG = "GoodsPresenterImpl";

    GoodsListView goodsView;
    Activity activity;

    @Override
    public void bindView(Activity activity, GoodsListView view) {
        this.activity = activity;
        this.goodsView = view;
    }

    @Override
    public void unBindView() {
        goodsView = null;
    }

    /**@param from 1来自搜索，增加searchName  2为普通列表
     * @param classifyld 一级分类或者二级分类id
     * @param pageSize
     * @param pageIndex
     * @param sortType   1 综合 2销量 3 价格从高到低 4从低到高
     */
    @Override
    public void requestData(int from,int classifyld, int pageSize, int pageIndex, int sortType,String searchName) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize", pageSize);
        httpParams.put("pageIndex", pageIndex);
        httpParams.put("classifyId", classifyld);
        httpParams.put("sortType", sortType);

        switch (from){
            case 1:
                httpParams.put("type", 1);
                httpParams.put("searchName", searchName);
                NetworkReturnUtil.requestHavePageList(goodsView, activity, Api.SEARCHCATEGORY, httpParams, GoodsBean.class, pageIndex);
                break;
            case 2:
                NetworkReturnUtil.requestHavePageList(goodsView, activity, Api.GOODSLIST, httpParams, GoodsBean.class, pageIndex);
                break;
        }

    }

    @Override
    public void getCollect(int pageSize, int pageIndex, int type) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize", pageSize);
        httpParams.put("pageIndex", pageIndex);
        httpParams.put("type", type);

        NetworkReturnUtil.requestHavePageList(goodsView, activity, Api.getCollectList, httpParams, GoodsBean.class, pageIndex);
    }
}
