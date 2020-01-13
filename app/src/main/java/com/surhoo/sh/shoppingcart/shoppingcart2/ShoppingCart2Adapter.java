package com.surhoo.sh.shoppingcart.shoppingcart2;


import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ShoppingCart2Adapter extends BaseQuickAdapter<ShoppingCartBean2.CarGoodsListBean, BaseViewHolder> {


    public ShoppingCart2Adapter(int layoutResId, @Nullable List<ShoppingCartBean2.CarGoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShoppingCartBean2.CarGoodsListBean item) {


        helper.setText(R.id.item_shopping_cart_goodsName, item.getGoodsName());
        helper.setText(R.id.item_shopping_cart_price, "¥"+item.getGoodsPrice());
        helper.setText(R.id.item_shopping_cart_skuName, item.getSkuName());
        helper.setText(R.id.item_shopping_cart_num, item.getGoodsNum()+"");
        helper.addOnClickListener(R.id.item_shopping_cart_addNum);
        helper.addOnClickListener(R.id.item_shopping_cart_reduceNum);

        CheckBox checkBox = (CheckBox) helper.getView(R.id.item_shopping_cart_checkBox);
        CheckBox shopCheckBox = (CheckBox) helper.getView(R.id.item_shopping_cart_shop_checkBox);

        helper.addOnClickListener(R.id.item_shopping_cart_checkBox);
        helper.addOnClickListener(R.id.item_shopping_cart_shop_checkBox);

        ImageView imageView = (ImageView) helper.getView(R.id.item_shopping_cart_img);
        GlideUtil.loadDefaultImg(mContext,item.getGoodsImg(),imageView);

        ConstraintLayout headLayout = (ConstraintLayout) helper.getView(R.id.item_shopping_cart_head);

        TextView footLayout = (TextView) helper.getView(R.id.item_shopping_cart_foot);

        if(item.isHead()){
            headLayout.setVisibility(View.VISIBLE);
        }else {
            headLayout.setVisibility(View.GONE);
        }

        if(item.isFoot()){
            footLayout.setVisibility(View.VISIBLE);
        }else {
            footLayout.setVisibility(View.GONE);
        }


        checkBox.setChecked(item.isCheck());

        if(item.isHeadCheck()){
            shopCheckBox.setChecked(true);
        }else {
            shopCheckBox.setChecked(false);
        }

        if(item.isDelete()){
            helper.itemView.setVisibility(View.GONE);
        }else {
            helper.itemView.setVisibility(View.VISIBLE);
        }

        item.setPosition(helper.getLayoutPosition());
//        shopCheckBox.setChecked(item.isHeadCheck());

//        if(item.isCheck()){
//            checkBox.setChecked(true);
//        }else {
//            checkBox.setChecked(false);
//        }
//
//        if(item.isHeadCheck()){
//            shopCheckBox.setChecked(true);
//        }else {
//            shopCheckBox.setChecked(false);
//        }
    }
}
