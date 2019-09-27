package com.surhoo.sh.goods.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.goods.view.AllCommentsView;

public interface AllCommentsPresenter extends BasePresenter<AllCommentsView> {

    void requestData(int pageSize,int pageIndex,int goodsId);

}
