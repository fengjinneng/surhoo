package com.surhoo.sh.designer.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.designer.bean.FinishProductBean;

import java.util.List;

public class FinishProductsAdapter extends BaseQuickAdapter<FinishProductBean
        , BaseViewHolder> {



    public FinishProductsAdapter(int layoutResId, @Nullable List<FinishProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FinishProductBean item) {

        ImageView view = (ImageView) helper.getView(R.id.item_designer_finish_product_img);


        GlideUtil.loadDefaultImg(mContext,item.getWorks(),view);

    }
}
