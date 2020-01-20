package com.surhoo.sh.shop.presenter.impl;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.designer.bean.SearchLabelBean;
import com.surhoo.sh.shop.bean.ShopListBean;
import com.surhoo.sh.shop.presenter.ArtistShopListPresent;
import com.surhoo.sh.shop.view.ArtistShopListView;

public class ArtistShopListPresentImpl implements ArtistShopListPresent {

    private Activity activity;
    private ArtistShopListView artistShopListView;


    @Override
    public void bindView(Activity activity, ArtistShopListView view) {

        this.activity = activity;
        this.artistShopListView = view;

    }

    @Override
    public void unBindView() {
        this.artistShopListView = null;
    }



    @Override
    public void requestData(String searchName, int pageSize, int pageIndex, String idList) {
        HttpParams httpParams = new HttpParams();

        httpParams.put("type", 3);
        httpParams.put("searchName", searchName);
        httpParams.put("pageSize", pageSize);
        httpParams.put("pageIndex", pageIndex);
        httpParams.put("idList", idList);
        httpParams.put("shopType", 1);
        NetworkReturnUtil.requestHavePageList(artistShopListView, activity, Api.SEARCHCATEGORY, httpParams, ShopListBean.class, pageIndex);
    }

    @Override
    public void requestShopLabel(String requestTag) {
        NetworkReturnUtil.requestNoPageList(requestTag,artistShopListView, activity, Api.shopLabel, null, SearchLabelBean.class);
    }
}
