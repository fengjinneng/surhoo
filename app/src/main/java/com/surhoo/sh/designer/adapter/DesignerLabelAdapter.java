package com.surhoo.sh.designer.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
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
