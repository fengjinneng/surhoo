package com.surhoo.sh.order.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.order.bean.OrderListBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyOrderListItemAdapter extends BaseQuickAdapter<OrderListBean.OrderDataListBean, BaseViewHolder> {


    public MyOrderListItemAdapter(int layoutResId, @Nullable List<OrderListBean.OrderDataListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderListBean.OrderDataListBean item) {


        helper.setText(R.id.item_order_confirm_goodsName, item.getGoodsName());
        helper.setText(R.id.item_order_confirm_price, "¥"+item.getGoodsPrice());
        helper.setText(R.id.item_order_confirm_skuName, item.getSkuName());
        helper.setText(R.id.item_order_confirm_num, "x "+item.getGoodsNum());
        helper.setText(R.id.item_order_confirm_market_price, "¥"+item.getGoodsMarketPrice());

        ImageView imageView = (ImageView) helper.getView(R.id.item_order_confirm_img);
        GlideUtil.loadDefaultImg(mContext,item.getGoodsImg(),imageView);


    }
}
