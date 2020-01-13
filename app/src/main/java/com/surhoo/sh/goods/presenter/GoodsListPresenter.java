package com.surhoo.sh.goods.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.goods.view.GoodsListView;

public interface GoodsListPresenter extends BasePresenter<GoodsListView> {

    void requestData(int from,int classifyld,int pageSize,int pageIndex,int sortType,String searchName);


    void getCollect(int pageSize,int pageIndex,int type);

}
