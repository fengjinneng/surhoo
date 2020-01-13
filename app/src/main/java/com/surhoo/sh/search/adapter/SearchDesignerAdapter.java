package com.surhoo.sh.search.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goyourfly.multi_picture.MultiPictureView;
import com.surhoo.sh.ImagePagerActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.matisse.MatisseImageUtil;
import com.surhoo.sh.common.util.EmptyUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.designer.bean.DesignerListBean;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchDesignerAdapter extends BaseQuickAdapter<DesignerListBean
        , BaseViewHolder> {


    public SearchDesignerAdapter(int layoutResId, @Nullable List<DesignerListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DesignerListBean item) {


        helper.setText(R.id.item_designer_list_name, EmptyUtil.isStringEmpty(item.getDesignerName()));

        helper.setText(R.id.item_designer_list_level, "lv" + EmptyUtil.isInterEmpty(item.getLevel()));

        helper.setText(R.id.item_designer_list_content, EmptyUtil.isStringEmpty(item.getDetail()));

        TagFlowLayout flowLayout = (TagFlowLayout) helper.getView(R.id.item_designer_list_tag);

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

        ImageView goodsImg = (ImageView) helper.getView(R.id.item_designer_list_avator);
        GlideUtil.loadCircleImage(mContext, item.getHeadimgurl() + "?x-oss-process=image/resize,w_320", goodsImg);


        MultiPictureView pictureView = (MultiPictureView) helper.getView(R.id.item_designer_multiPictureView);

        if (ObjectUtils.isEmpty(item.getMaterialList())) {
            pictureView.setVisibility(View.GONE);
            helper.getView(R.id.item_designer_list_hot_material).setVisibility(View.GONE);

        } else {

            pictureView.setVisibility(View.VISIBLE);
            helper.getView(R.id.item_designer_list_hot_material).setVisibility(View.VISIBLE);
            List<Uri> temp = new ArrayList<>();
            for (int i = 0; i < item.getMaterialList().size(); i++) {
                temp.add(Uri.parse(item.getMaterialList().get(i)));
            }

            pictureView.addItem(temp);

//            ArrayList<String> temp = new ArrayList<>();
//
//            for (int i = 0; i < item.getMaterialList().size(); i++) {
//
//                if(i==6){
//                    break;
//                }
//                temp.add(item.getMaterialList().get(i));
//            }

        pictureView.setItemClickCallback(new MultiPictureView.ItemClickCallback() {
            @Override
            public void onItemClicked(@NotNull View view, int i, @NotNull ArrayList<Uri> arrayList) {

                ImagePagerActivity.startImagePagerActivity(mContext,item.getMaterialList(),i,null);

            }
        });

        }
    }
}
