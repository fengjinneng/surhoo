package com.surhoo.sh.designer.adapter;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.ImagePagerActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.bean.PhotoInfo;
import com.surhoo.sh.common.util.MultiImageView;
import com.surhoo.sh.designer.bean.DesignerDynamicBean;
import com.surhoo.sh.material.MaterialDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class DesignerDynamicAdapter extends BaseQuickAdapter<DesignerDynamicBean
        , BaseViewHolder> {


    public DesignerDynamicAdapter(int layoutResId, @Nullable List<DesignerDynamicBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DesignerDynamicBean item) {
        helper.setText(R.id.item_desinger_dynamic_time, item.getStrDate());

        MultiImageView multiImageView = (MultiImageView) helper.getView(R.id.item_desinger_dynamic_multiImageView);

        List<DesignerDynamicBean.TrendsListBean> trendsList = item.getTrendsList();

        if (!ObjectUtils.isEmpty(trendsList)) {
            multiImageView.setVisibility(View.VISIBLE);

            ArrayList<PhotoInfo> imgs = new ArrayList<>();
            for (int i = 0; i < trendsList.size(); i++) {
                imgs.add(new PhotoInfo(trendsList.get(i).getLogo()));
            }

            multiImageView.setList(imgs);
            multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    DesignerDynamicBean.TrendsListBean trendsListBean = item.getTrendsList().get(position);
                    if (trendsListBean.isIsMaterial()) {
                        Intent materialIntent  = new Intent(mContext,MaterialDetailActivity.class);
                        materialIntent.putExtra("id",trendsListBean.getMaterialId());
                        ActivityUtils.startActivity(materialIntent);
                    }else {
                        List<String> photoUrls = new ArrayList<String>();
                        for (int i = 0; i < trendsList.size(); i++) {
                            photoUrls.add(trendsList.get(i).getLogo());
                        }
                        ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());
                        ImagePagerActivity.startImagePagerActivity(mContext, photoUrls, position, imageSize);
                    }
                }
            });

        } else {
            multiImageView.setVisibility(View.GONE);
        }

    }
}
