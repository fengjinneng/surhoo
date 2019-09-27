package com.surhoo.sh.goods.di;


import com.surhoo.sh.R;
import com.surhoo.sh.goods.adapter.GoodsListAdapter;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.presenter.GoodsPresenter;
import com.surhoo.sh.goods.presenter.impl.GoodsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class GoodsModule {


    @Provides
    GoodsPresenter provideGoodsPresenter() {

        return new GoodsPresenterImpl();
    }


    @Provides
    List<GoodsBean> provideGoodsList() {
        return new ArrayList<>();
    }

    @Provides
    GoodsListAdapter provideGoodsListAdapter(List<GoodsBean> datas) {

        return new GoodsListAdapter(R.layout.item_goods_list, datas);

    }
}
