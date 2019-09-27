package com.surhoo.sh.goods.di;


import com.surhoo.sh.R;
import com.surhoo.sh.goods.adapter.CommentsAdapter;
import com.surhoo.sh.goods.bean.GoodDetailBean;
import com.surhoo.sh.goods.presenter.GoodsDetailPresenter;
import com.surhoo.sh.goods.presenter.impl.GoodsDetailPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class GoodsDetailModule {


    @Provides
    GoodsDetailPresenter provideGoodsDetailPresenter() {
        return new GoodsDetailPresenterImpl();
    }


    @Provides
    List<GoodDetailBean.EvaluateListBean> provideData() {
        return new ArrayList<>();

    }

    @Provides
    CommentsAdapter provideCommentsAdapter(List<GoodDetailBean.EvaluateListBean> datas) {

        return new CommentsAdapter(R.layout.item_goods_comments, datas);

    }
}
