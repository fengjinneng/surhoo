package com.surhoo.sh.goods.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.goods.bean.CategoryBean;

import java.util.List;

public class LevelTwoCategoryAdapter extends BaseQuickAdapter<CategoryBean
        , BaseViewHolder> {


    public LevelTwoCategoryAdapter(int layoutResId, @Nullable List<CategoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CategoryBean item) {
        helper.setText(R.id.item_level_two_category_name,item.getName());
    }
}
