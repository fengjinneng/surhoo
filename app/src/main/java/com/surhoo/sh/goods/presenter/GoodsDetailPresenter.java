package com.surhoo.sh.goods.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.goods.bean.RequestAddToCarBean;
import com.surhoo.sh.goods.view.GoodsDetailView;

public interface GoodsDetailPresenter extends BasePresenter<GoodsDetailView> {

    void requestData(int id);

    void addToCar(RequestAddToCarBean bean);

    void getShoopingCartNumber();


}
