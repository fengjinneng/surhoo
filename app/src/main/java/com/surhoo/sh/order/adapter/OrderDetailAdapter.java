package com.surhoo.sh.order.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.bean.order.response.OrderDetailReturnBean;
import com.surhoo.sh.common.util.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderDetailAdapter extends BaseQuickAdapter<OrderDetailReturnBean.OrderDataListBean, BaseViewHolder> {


    public OrderDetailAdapter(int layoutResId, @Nullable List<OrderDetailReturnBean.OrderDataListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderDetailReturnBean.OrderDataListBean item) {

        helper.setText(R.id.item_order_confirm_goodsName, item.getGoodsName());
        helper.setText(R.id.item_order_confirm_price, "¥"+item.getGoodsPrice());
        helper.setText(R.id.item_order_confirm_skuName, item.getSkuName());
        helper.setText(R.id.item_order_confirm_num, "x "+item.getGoodsNum());
        helper.setText(R.id.item_order_confirm_market_price, "¥"+item.getGoodsMarketPrice());

        ImageView imageView = (ImageView) helper.getView(R.id.item_order_confirm_img);

        GlideUtil.loadDefaultImg(mContext,item.getGoodsImg(),imageView);

    }
}
