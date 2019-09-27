package com.surhoo.sh.designer.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.designer.bean.DesignerLabelBean;

import java.util.List;

public class DesignerLabelAdapter extends BaseQuickAdapter<DesignerLabelBean
        , BaseViewHolder> {



    public DesignerLabelAdapter(int layoutResId, @Nullable List<DesignerLabelBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DesignerLabelBean item) {
        helper.setText(R.id.item_designer_label_name,item.getName());
    }
}
