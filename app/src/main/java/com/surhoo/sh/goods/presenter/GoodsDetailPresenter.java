package com.surhoo.sh.goods.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.goods.bean.RequestAddToCarBean;
import com.surhoo.sh.goods.view.GoodsDetailView;

public interface GoodsDetailPresenter extends BasePresenter<GoodsDetailView> {

    void requestData(int id);

    void addToCar(String requestTag,RequestAddToCarBean bean);

    void getShoopingCartNumber(String requestTag);


    //1 商品 2 素材 3设计师 4店铺
    void addCollect(String requestTag,int type,int typeId);


    void cancelCollect(String requestTag,int type,int typeId);


}
