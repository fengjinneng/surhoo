package com.surhoo.sh.shoppingcart;

import android.support.annotation.NonNull;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;

import java.util.List;

public class ShoppingCartAdapter extends BaseMultiItemQuickAdapter<ShoppingCartBean, BaseViewHolder> {


    public ShoppingCartAdapter(List data) {
        super(data);
        addItemType(ShoppingCartBean.head, R.layout.item_shopping_cart1);
        addItemType(ShoppingCartBean.body, R.layout.item_shopping_cart2);
        addItemType(ShoppingCartBean.foot, R.layout.item_shopping_cart3);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShoppingCartBean item) {
        switch (helper.getItemViewType()) {
            case ShoppingCartBean.head:
                CheckBox checkBox1 = (CheckBox) helper.getView(R.id.item_shopping_cart1_checkbox);
                checkBox1.setChecked(item.isChecked());
                helper.setText(R.id.item_shopping_cart1_shopname, item.getShopName());
                helper.addOnClickListener(R.id.item_shopping_cart1_shopname);
                helper.addOnClickListener(R.id.item_shopping_cart1_checkbox);
                break;
            case ShoppingCartBean.body:

                CheckBox checkBox2 = (CheckBox) helper.getView(R.id.item_shopping_cart2_checkbox);
                checkBox2.setChecked(item.isChecked());
                helper.setText(R.id.item_shopping_cart2_shopname, item.getCarGoodsList().getGoodsName());
                helper.setText(R.id.item_shopping_cart2_price, item.getCarGoodsList().getGoodsPrice());
                helper.addOnClickListener(R.id.item_shopping_cart2_shopname);
                helper.addOnClickListener(R.id.item_shopping_cart2_checkbox);

                ImageView imageView = (ImageView) helper.getView(R.id.item_shopping_cart2_img);
                Glide.with(mContext).load(item.getCarGoodsList().getGoodsImg()).into(imageView);

                break;
            case ShoppingCartBean.foot:
                break;
            default:
                break;
        }
    }
}
