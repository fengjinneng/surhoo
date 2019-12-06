package com.surhoo.sh.designer.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.designer.bean.FinishProductBean;

import java.util.List;

public class FinishProductsAdapter extends BaseQuickAdapter<FinishProductBean
        , BaseViewHolder> {



    public FinishProductsAdapter(int layoutResId, @Nullable List<FinishProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FinishProductBean item) {

    }
}
