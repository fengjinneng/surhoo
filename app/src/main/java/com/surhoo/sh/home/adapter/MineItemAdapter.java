package com.surhoo.sh.home.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.home.bean.MineItemBean;

import java.util.List;

public class MineItemAdapter extends BaseQuickAdapter<MineItemBean
        , BaseViewHolder> {


    public MineItemAdapter(int layoutResId, @Nullable List<MineItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MineItemBean item) {

        helper.setText(R.id.template_mine_item_name,item.getName());

        ImageView imageView = helper.getView(R.id.template_mine_item_img);

        imageView.setImageDrawable(item.getDrawable());

    }
}
