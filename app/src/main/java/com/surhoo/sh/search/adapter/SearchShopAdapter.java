package com.surhoo.sh.search.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.shop.bean.ShopListBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchShopAdapter extends BaseQuickAdapter<ShopListBean
        , BaseViewHolder> {




    public SearchShopAdapter(int layoutResId, @Nullable List<ShopListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShopListBean item) {


        helper.setText(R.id.item_shop_list_name,item.getName());

        helper.setText(R.id.item_shop_list_hot,item.getViewNum()+"");

        ImageView img = (ImageView) helper.getView(R.id.item_shop_list_img);

        GlideUtil.loadDefaultImg(mContext,item.getLogo(),img);

        TagFlowLayout flowLayout = (TagFlowLayout) helper.getView(R.id.item_shop_list_tag);

        List<String> strings = new ArrayList<>();

        if (!StringUtils.isEmpty(item.getLabelNames())) {

            flowLayout.setVisibility(View.VISIBLE);

            String[] split = item.getLabelNames().split(",");

            for (int i = 0; i < split.length; i++) {
                strings.add(split[i]);
            }

            if(item.isHasTagAdapter()){

                TagAdapter tagAdapter = item.getTagAdapter();
                flowLayout.setAdapter(tagAdapter);

            }else {

                TagAdapter<String> tagAdapter = new TagAdapter<String>(strings) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) mLayoutInflater.inflate(R.layout.item_designer_tag, flowLayout, false);
                        tv.setText(s);
                        return tv;
                    }
                };

                flowLayout.setAdapter(tagAdapter);
                item.setHasTagAdapter(true);
                item.setTagAdapter(tagAdapter);

            }

        }else {

            flowLayout.setVisibility(View.GONE);
        }
    }
}
