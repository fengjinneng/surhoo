package com.surhoo.sh.search.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
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

        RoundedImageView goodsImg = (RoundedImageView) helper.getView(R.id.item_goods_list_img);

        goodsImg.setLayoutParams(new ConstraintLayout.LayoutParams((int) (ScreenUtils.getScreenWidth() * 0.44),(int) (ScreenUtils.getScreenWidth() * 0.44)));

        goodsImg.setPadding(ConvertUtils.dp2px(1),0,ConvertUtils.dp2px(1),0);

        GlideUtil.loadDefaultImg(mContext,item.getLogo() ,goodsImg);
    }
}
