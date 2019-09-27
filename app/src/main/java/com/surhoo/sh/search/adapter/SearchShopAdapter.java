package com.surhoo.sh.search.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.shop.ShopListBean;

import java.util.List;

public class SearchShopAdapter extends BaseQuickAdapter<ShopListBean
        , BaseViewHolder> {




    public SearchShopAdapter(int layoutResId, @Nullable List<ShopListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShopListBean item) {


        helper.setText(R.id.item_shop_list_name,item.getName());

        helper.setText(R.id.item_shop_list_hot,item.getViewNum()+"");

        ImageView img = (ImageView) helper.getView(R.id.item_shop_list_img);

        Glide.with(mContext).load(item.getLogo()).into(img);


    }
}