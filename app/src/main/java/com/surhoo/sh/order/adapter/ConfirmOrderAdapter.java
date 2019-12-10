package com.surhoo.sh.order.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.shoppingcart.ShoppingCartBean;

import java.util.List;

public class ConfirmOrderAdapter extends BaseQuickAdapter<ShoppingCartBean.CarGoodsListBean,BaseViewHolder> {



    public ConfirmOrderAdapter(int layoutResId, @Nullable List<ShoppingCartBean.CarGoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShoppingCartBean.CarGoodsListBean item) {


        helper.setText(R.id.item_order_confirm_goodsName, item.getGoodsName());
        helper.setText(R.id.item_order_confirm_price, "¥"+item.getGoodsPrice());
        helper.setText(R.id.item_order_confirm_skuName, item.getSkuName());
        helper.setText(R.id.item_order_confirm_num, "x "+item.getGoodsNum());
        helper.setText(R.id.item_order_confirm_market_price, "¥"+item.getGoodsMarketPrice());

        ImageView imageView = (ImageView) helper.getView(R.id.item_order_confirm_img);
        GlideUtil.loadDefaultImg(mContext,item.getGoodsImg(),imageView);


    }
}
