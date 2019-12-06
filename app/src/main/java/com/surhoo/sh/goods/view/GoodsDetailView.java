package com.surhoo.sh.goods.view;

import com.surhoo.sh.base.NoPageBaseView;
import com.surhoo.sh.goods.bean.GoodDetailBean;

public interface GoodsDetailView extends NoPageBaseView<GoodDetailBean> {


    void addToCarResult(Integer result);


    void showCarNumber(Integer number);

}
