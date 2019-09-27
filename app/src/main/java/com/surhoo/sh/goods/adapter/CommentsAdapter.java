package com.surhoo.sh.goods.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.goods.bean.GoodDetailBean;

import java.util.List;

public class CommentsAdapter extends BaseQuickAdapter<GoodDetailBean.EvaluateListBean
        , BaseViewHolder> {



    public CommentsAdapter(int layoutResId, @Nullable List<GoodDetailBean.EvaluateListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodDetailBean.EvaluateListBean item) {


        helper.setText(R.id.item_goods_comments_nickname,item.getEvaluateName());


        ImageView avatar = (ImageView) helper.getView(R.id.item_goods_comments_avatar);
        Glide.with(mContext).load(item.getHeadimgurl()).into(avatar);
    }
}
