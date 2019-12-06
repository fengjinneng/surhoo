package com.surhoo.sh.search.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.goods.bean.GoodsBean;

import java.util.List;

public class SearchGoodsAdapter extends BaseQuickAdapter<GoodsBean
        , BaseViewHolder> {




    public SearchGoodsAdapter(int layoutResId, @Nullable List<GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsBean item) {


        if (!StringUtils.isEmpty(item.getGoodsName())) {
            helper.setText(R.id.item_goods_list_goodsname, item.getGoodsName());
        }
        if (!StringUtils.isEmpty(item.getGoodsPrice())) {
            helper.setText(R.id.item_goods_list_price, item.getGoodsPrice());
        }
        if (item.getSaleCount() >= 0) {
            helper.setText(R.id.item_goods_list_sales, String.valueOf(item.getSaleCount()));
        }

        ImageView goodsImg = (ImageView) helper.getView(R.id.item_goods_list_img);
        GlideUtil.loadGoodsImage(mContext,item.getLogo() ,goodsImg);
    }
}
