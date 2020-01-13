package com.surhoo.sh.shoppingcart.shoppingcart2;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.shoppingcart.ShoppingCartBean;
import com.surhoo.sh.shoppingcart.ShoppingCartPresent;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ShoppingCart2ItemAdapter extends BaseQuickAdapter<ShoppingCartBean.CarGoodsListBean, BaseViewHolder> {


    private ShoppingCartPresent shoppingCartPresent;

    public ShoppingCartPresent getShoppingCartPresent() {
        return shoppingCartPresent;
    }

    public void setShoppingCartPresent(ShoppingCartPresent shoppingCartPresent) {
        this.shoppingCartPresent = shoppingCartPresent;
    }

    public ShoppingCart2ItemAdapter(int layoutResId, @Nullable List<ShoppingCartBean.CarGoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShoppingCartBean.CarGoodsListBean item) {

        helper.setText(R.id.item_shopping_cart2_goodsName, item.getGoodsName());
        helper.setText(R.id.item_shopping_cart2_price, "¥"+item.getGoodsPrice());
        helper.setText(R.id.item_shopping_cart2_skuName, item.getSkuName());
        helper.setText(R.id.item_shopping_cart2_num, item.getGoodsNum()+"");
        ImageView imageView = (ImageView) helper.getView(R.id.item_shopping_cart2_img);
        GlideUtil.loadDefaultImg(mContext,item.getGoodsImg(),imageView);

        helper.addOnClickListener(R.id.item_shopping_cart2_addNum);
        helper.addOnClickListener(R.id.item_shopping_cart2_reduceNum);
        helper.addOnClickListener(R.id.item_shopping_cart2_checkbox);

        ConstraintLayout addNum = (ConstraintLayout) helper.getView(R.id.item_shopping_cart2_addNum);
        ConstraintLayout reduceNum = (ConstraintLayout) helper.getView(R.id.item_shopping_cart2_reduceNum);
        TextView num = (TextView) helper.getView(R.id.item_shopping_cart2_num);
        CheckBox checkbox = (CheckBox) helper.getView(R.id.item_shopping_cart2_checkbox);


        addNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        reduceNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkbox.setChecked(isChecked);
            }
        });
    }
}
