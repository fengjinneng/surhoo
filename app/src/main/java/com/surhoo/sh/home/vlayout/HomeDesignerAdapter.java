package com.surhoo.sh.home.vlayout;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.home.bean.HomePageBean;

import java.util.List;

public class HomeDesignerAdapter extends BaseQuickAdapter<HomePageBean.DESIGNERBean
        , BaseViewHolder> {


    public HomeDesignerAdapter(int layoutResId, @Nullable List<HomePageBean.DESIGNERBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomePageBean.DESIGNERBean item) {
        helper.setText(R.id.item_home_designer_name,item.getDesignerName());

        helper.setText(R.id.item_home_designer_level,"lv"+item.getLevel());

        ImageView avatar = (ImageView) helper.getView(R.id.item_home_designer_avatar);
        Glide.with(mContext).load(item.getHeadimgurl()).into(avatar);
    }
}
