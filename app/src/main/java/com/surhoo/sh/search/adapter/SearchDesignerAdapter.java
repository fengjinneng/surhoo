package com.surhoo.sh.search.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.EmptyUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.designer.bean.DesignerListBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

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


        if (ObjectUtils.isEmpty(item.getMaterialList())) {
            helper.getView(R.id.item_designer_list_material).setVisibility(View.GONE);
            helper.getView(R.id.item_designer_list_hot_material).setVisibility(View.GONE);

        } else {

            helper.getView(R.id.item_designer_list_material).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_designer_list_hot_material).setVisibility(View.VISIBLE);

            ArrayList<String> temp = new ArrayList<>();

            for (int i = 0; i < item.getMaterialList().size(); i++) {

                if(i==6){
                    break;
                }
                temp.add(item.getMaterialList().get(i));
            }

            ImageView img1 = (ImageView) helper.getView(R.id.item_designer_list_material_1);
            ImageView img2 = (ImageView) helper.getView(R.id.item_designer_list_material_2);
            ImageView img3 = (ImageView) helper.getView(R.id.item_designer_list_material_3);
            ImageView img4 = (ImageView) helper.getView(R.id.item_designer_list_material_4);
            ImageView img5 = (ImageView) helper.getView(R.id.item_designer_list_material_5);
            ImageView img6 = (ImageView) helper.getView(R.id.item_designer_list_material_6);
            helper.addOnClickListener(R.id.item_designer_list_material_1,R.id.item_designer_list_material_2
            ,R.id.item_designer_list_material_3,R.id.item_designer_list_material_4,R.id.item_designer_list_material_5
            ,R.id.item_designer_list_material_6);

            switch (temp.size()) {
                case 1:

                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(0) + "?x-oss-process=image/resize,w_320",img1);

                    break;
                case 2:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(0) + "?x-oss-process=image/resize,w_320",img1);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(1) + "?x-oss-process=image/resize,w_320",img2);

                    break;
                case 3:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(0) + "?x-oss-process=image/resize,w_320",img1);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(1) + "?x-oss-process=image/resize,w_320",img2);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(2) + "?x-oss-process=image/resize,w_320",img3);

                    break;
                case 4:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.VISIBLE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(0) + "?x-oss-process=image/resize,w_320",img1);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(1) + "?x-oss-process=image/resize,w_320",img2);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(2) + "?x-oss-process=image/resize,w_320",img3);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(3) + "?x-oss-process=image/resize,w_320",img4);
                    break;
                case 5:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.VISIBLE);
                    img5.setVisibility(View.VISIBLE);
                    img6.setVisibility(View.GONE);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(0) + "?x-oss-process=image/resize,w_320",img1);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(1) + "?x-oss-process=image/resize,w_320",img2);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(2) + "?x-oss-process=image/resize,w_320",img3);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(3) + "?x-oss-process=image/resize,w_320",img4);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(4) + "?x-oss-process=image/resize,w_320",img5);

                    break;
                case 6:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.VISIBLE);
                    img5.setVisibility(View.VISIBLE);
                    img6.setVisibility(View.VISIBLE);

                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(0) + "?x-oss-process=image/resize,w_320",img1);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(1) + "?x-oss-process=image/resize,w_320",img2);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(2) + "?x-oss-process=image/resize,w_320",img3);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(3) + "?x-oss-process=image/resize,w_320",img4);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(4) + "?x-oss-process=image/resize,w_320",img5);
                    GlideUtil.loadDefaultImg(mContext,item.getMaterialList().get(5) + "?x-oss-process=image/resize,w_320",img6);
                    break;

            }

        }
    }
}
