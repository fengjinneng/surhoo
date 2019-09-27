package com.surhoo.sh.shop.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.shop.view.ShopView;

public interface ShopPresenter extends BasePresenter<ShopView> {

    void requestData(boolean isSelf,int shopId);

}
