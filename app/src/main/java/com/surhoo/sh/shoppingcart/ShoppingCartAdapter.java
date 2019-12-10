package com.surhoo.sh.shoppingcart;

import android.support.annotation.NonNull;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.shoppingcart.ShoppingCartBean.CarGoodsListBean;

import java.util.List;

public class ShoppingCartAdapter extends BaseMultiItemQuickAdapter<CarGoodsListBean, BaseViewHolder> {


    public ShoppingCartAdapter(List data) {
        super(data);
        addItemType(CarGoodsListBean.head, R.layout.item_shopping_cart1);
        addItemType(CarGoodsListBean.body, R.layout.item_shopping_cart2);
        addItemType(CarGoodsListBean.foot, R.layout.item_shopping_cart3);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, CarGoodsListBean item) {
        switch (helper.getItemViewType()) {
            case CarGoodsListBean.head:
                CheckBox checkBox1 = (CheckBox) helper.getView(R.id.item_shopping_cart1_checkbox);
                checkBox1.setChecked(item.isChecked());
                helper.setText(R.id.item_shopping_cart1_shopname, item.getShopName());
                helper.addOnClickListener(R.id.item_shopping_cart1_checkbox);
                break;
            case CarGoodsListBean.body:
                CheckBox checkBox2 = (CheckBox) helper.getView(R.id.item_shopping_cart2_checkbox);
                checkBox2.setChecked(item.isChecked());
                helper.setText(R.id.item_shopping_cart2_goodsName, item.getGoodsName());
                helper.setText(R.id.item_shopping_cart2_price, "¥"+item.getGoodsPrice());
                helper.setText(R.id.item_shopping_cart2_skuName, item.getSkuName());
                helper.setText(R.id.item_shopping_cart2_num, item.getGoodsNum()+"");
                helper.addOnClickListener(R.id.item_shopping_cart2_addNum);
                helper.addOnClickListener(R.id.item_shopping_cart2_reduceNum);
                helper.addOnClickListener(R.id.item_shopping_cart2_checkbox);
                ImageView imageView = (ImageView) helper.getView(R.id.item_shopping_cart2_img);
                GlideUtil.loadDefaultImg(mContext,item.getGoodsImg(),imageView);
                break;
            case CarGoodsListBean.foot:
                break;
            default:
                break;
        }
    }
}
