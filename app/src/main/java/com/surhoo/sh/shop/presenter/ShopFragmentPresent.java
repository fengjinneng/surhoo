package com.surhoo.sh.shop.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.shop.view.impl.ShopFragmentView;

public interface ShopFragmentPresent extends BasePresenter<ShopFragmentView> {


    //排序方式 1综合 2销量 3价格
     void requestData(int classifyId, int sortType,int pageIndex,int pageSize);

}
