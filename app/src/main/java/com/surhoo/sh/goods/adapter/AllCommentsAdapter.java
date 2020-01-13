package com.surhoo.sh.goods.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goyourfly.multi_picture.MultiPictureView;
import com.surhoo.sh.ImagePagerActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.goods.bean.CommentBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AllCommentsAdapter extends BaseQuickAdapter<CommentBean.ListBean
        , BaseViewHolder> {



    public AllCommentsAdapter(int layoutResId, @Nullable List<CommentBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommentBean.ListBean item) {

        helper.setText(R.id.item_goods_comments_nickname, item.getUserName());
        helper.setText(R.id.item_goods_comments_content, item.getEvaluateName());

        helper.setText(R.id.item_goods_comments_time, item.getGmtCreate());
        helper.setText(R.id.item_goods_comments_skuName, item.getSkuName());

        ImageView avatar = (ImageView) helper.getView(R.id.item_goods_comments_avatar);
        GlideUtil.loadCenterCropImg(mContext, item.getHeadimgurl(), avatar);

        MultiPictureView multiPictureView = (MultiPictureView) helper.getView(R.id.item_goods_comments_multiPictureView);

        if(item.isLoad()){

        }else {
            ArrayList<Uri> imgs =new ArrayList<>();
            String[] s = item.getImg().split("_");
            for (int i = 0; i < s.length; i++) {
                imgs.add(Uri.parse(s[i]));
            }

            multiPictureView.addItem(imgs);
            multiPictureView.setItemClickCallback(new MultiPictureView.ItemClickCallback() {
                @Override
                public void onItemClicked(@NotNull View view, int i, @NotNull ArrayList<Uri> arrayList) {
                    ArrayList<String> list = new ArrayList<>();
                    for (int j = 0; j < arrayList.size(); j++) {

                        list.add(String.valueOf(arrayList.get(j)));

                    }
                    ImagePagerActivity.startImagePagerActivity(mContext,list,i,null);
                }
            });

            item.setLoad(true);
        }

        if(StringUtils.isEmpty(item.getImg())){
            multiPictureView.setVisibility(View.GONE);
        }else {
            multiPictureView.setVisibility(View.VISIBLE);
        }
    }
}
