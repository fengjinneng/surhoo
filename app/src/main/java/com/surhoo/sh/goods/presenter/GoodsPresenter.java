package com.surhoo.sh.goods.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.goods.view.GoodsView;

public interface GoodsPresenter extends BasePresenter<GoodsView> {

    void requestData(int from,int classifyld,int pageSize,int pageIndex,int sortType);

}
