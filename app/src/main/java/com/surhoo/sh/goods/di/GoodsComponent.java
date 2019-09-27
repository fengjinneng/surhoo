package com.surhoo.sh.goods.di;


import com.surhoo.sh.goods.fragment.GoodsListFragment;
import com.surhoo.sh.goods.view.GoodsListActivity;

import dagger.Component;
import dagger.Module;

@Component(modules = GoodsModule.class)
public interface GoodsComponent {


    void inject(GoodsListFragment goodsListFragment);

}
