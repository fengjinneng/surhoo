package com.surhoo.sh.goods.di;


import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;

import dagger.Component;

@Component(modules = GoodsDetailModule.class)
public interface GoodsDetailComponent {

    void inject(GoodsDetailActivity goodsDetailActivity);

}
