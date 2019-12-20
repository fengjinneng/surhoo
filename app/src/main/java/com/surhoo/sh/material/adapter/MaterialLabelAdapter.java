package com.surhoo.sh.material.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.material.bean.MaterialLabelBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MaterialLabelAdapter extends BaseQuickAdapter<MaterialLabelBean
        , BaseViewHolder> {


    public MaterialLabelAdapter(int layoutResId, @Nullable List<MaterialLabelBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MaterialLabelBean item) {

        TextView textView = (TextView) helper.getView(R.id.item_label_name);

        textView.setText(item.getName());

        if (item.isChecked()) {
            textView.setTextColor(mContext.getResources().getColor(R.color.themeColor));
            textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_goods_spec_item_check));
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.a4a4a4));
            textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_goods_spec_item_uncheck));
        }
    }
}
