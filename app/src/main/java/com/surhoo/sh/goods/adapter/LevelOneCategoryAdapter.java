package com.surhoo.sh.goods.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.goods.bean.CategoryBean;

import java.util.List;

public class LevelOneCategoryAdapter  extends BaseQuickAdapter<CategoryBean
        , BaseViewHolder> {


    public LevelOneCategoryAdapter(int layoutResId, @Nullable List<CategoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CategoryBean item) {

        TextView name = (TextView) helper.getView(R.id.item_level_one_category_name);

        ConstraintLayout layout = (ConstraintLayout) helper.getView(R.id.item_level_one_category_layout);

        name.setText(item.getName());

        if(item.isChecked()){
            helper.getView(R.id.item_level_one_category_red_line).setVisibility(View.VISIBLE);
            name.setTextColor(mContext.getResources().getColor(R.color.pageTitle));

            layout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }else {
            helper.getView(R.id.item_level_one_category_red_line).setVisibility(View.GONE);
            name.setTextColor(mContext.getResources().getColor(R.color.saleColor));

            layout.setBackgroundColor(mContext.getResources().getColor(R.color.bgColor));
        }



    }
}
