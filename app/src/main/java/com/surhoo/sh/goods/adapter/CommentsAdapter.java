package com.surhoo.sh.goods.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.goods.bean.GoodDetailBean;

import java.util.List;

public class CommentsAdapter extends BaseQuickAdapter<GoodDetailBean.EvaluateListBean
        , BaseViewHolder> {


    public CommentsAdapter(int layoutResId, @Nullable List<GoodDetailBean.EvaluateListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodDetailBean.EvaluateListBean item) {


        helper.setText(R.id.item_goods_comments_nickname, item.getUserName());
        helper.setText(R.id.item_goods_comments_content, item.getEvaluateName());

        helper.setText(R.id.item_goods_comments_time, item.getGmtCreate());
        helper.setText(R.id.item_goods_comments_skuName, item.getSkuName());


        ImageView avatar = (ImageView) helper.getView(R.id.item_goods_comments_avatar);
        GlideUtil.loadCenterCropImg(mContext, item.getHeadimgurl(), avatar);


        ConstraintLayout commentsLayout = (ConstraintLayout) helper.getView(R.id.item_goods_comments_layout);

        String img = item.getImg();

        ImageView img1 = (ImageView) helper.getView(R.id.item_goods_comments_img1);
        ImageView img2 = (ImageView) helper.getView(R.id.item_goods_comments_img2);
        ImageView img3 = (ImageView) helper.getView(R.id.item_goods_comments_img3);
        ImageView img4 = (ImageView) helper.getView(R.id.item_goods_comments_img4);
        ImageView img5 = (ImageView) helper.getView(R.id.item_goods_comments_img5);


        if (StringUtils.isEmpty(img)) {
            commentsLayout.setVisibility(View.GONE);
        } else {
            commentsLayout.setVisibility(View.VISIBLE);

            String[] split = img.split(",");
            switch (split.length) {
                case 1:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.INVISIBLE);
                    img3.setVisibility(View.INVISIBLE);
                    img4.setVisibility(View.INVISIBLE);
                    img5.setVisibility(View.INVISIBLE);
                    GlideUtil.loadCenterCropImg(mContext, split[0], img1);
                    break;

                case 2:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.INVISIBLE);
                    img4.setVisibility(View.INVISIBLE);
                    img5.setVisibility(View.INVISIBLE);
                    GlideUtil.loadCenterCropImg(mContext, split[0], img1);
                    GlideUtil.loadCenterCropImg(mContext, split[1], img2);
                    break;

                case 3:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.INVISIBLE);
                    img5.setVisibility(View.INVISIBLE);
                    GlideUtil.loadCenterCropImg(mContext, split[0], img1);
                    GlideUtil.loadCenterCropImg(mContext, split[1], img2);
                    GlideUtil.loadCenterCropImg(mContext, split[2], img3);
                    break;

                case 4:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.VISIBLE);
                    img5.setVisibility(View.INVISIBLE);
                    GlideUtil.loadCenterCropImg(mContext, split[0], img1);
                    GlideUtil.loadCenterCropImg(mContext, split[1], img2);
                    GlideUtil.loadCenterCropImg(mContext, split[2], img3);
                    GlideUtil.loadCenterCropImg(mContext, split[3], img4);
                    break;

                case 5:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.VISIBLE);
                    img5.setVisibility(View.VISIBLE);
                    GlideUtil.loadCenterCropImg(mContext, split[0], img1);
                    GlideUtil.loadCenterCropImg(mContext, split[1], img2);
                    GlideUtil.loadCenterCropImg(mContext, split[2], img3);
                    GlideUtil.loadCenterCropImg(mContext, split[3], img4);
                    GlideUtil.loadCenterCropImg(mContext, split[4], img5);

                    break;
                default:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.VISIBLE);
                    img5.setVisibility(View.VISIBLE);
                    GlideUtil.loadCenterCropImg(mContext, split[0], img1);
                    GlideUtil.loadCenterCropImg(mContext, split[1], img2);
                    GlideUtil.loadCenterCropImg(mContext, split[2], img3);
                    GlideUtil.loadCenterCropImg(mContext, split[3], img4);
                    GlideUtil.loadCenterCropImg(mContext, split[4], img5);
                    break;
            }

        }

    }
}
