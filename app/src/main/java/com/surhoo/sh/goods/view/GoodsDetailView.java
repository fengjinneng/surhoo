package com.surhoo.sh.goods.view;

import com.surhoo.sh.base.BaseView;
import com.surhoo.sh.goods.bean.GoodDetailBean;

public interface GoodsDetailView extends BaseView {


    void show(GoodDetailBean goodDetailBean);


    void showSpec(GoodDetailBean goodDetailBean);

}
