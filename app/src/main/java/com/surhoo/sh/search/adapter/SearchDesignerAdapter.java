package com.surhoo.sh.search.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.designer.bean.DesignerListBean;

import java.util.List;

public class SearchDesignerAdapter extends BaseQuickAdapter<DesignerListBean
        , BaseViewHolder> {


    public SearchDesignerAdapter(int layoutResId, @Nullable List<DesignerListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DesignerListBean item) {

        helper.setText(R.id.item_designer_list_name, item.getDesignerName());

        helper.setText(R.id.item_designer_list_level, "lv" + item.getLevel());

        helper.setText(R.id.item_designer_list_content,item.getDetail());

//        TagFlowLayout flowLayout = (TagFlowLayout) helper.getView(R.id.item_designer_list_tag);
//
//
//        List<String> strings = new ArrayList<>();
//
//        for (int i = 0; i < item.getMaterialList().size(); i++) {
//            strings.add(item.getMaterialList().get(i));
//        }
//
//        flowLayout.setAdapter(new TagAdapter<String>(strings) {
//            @Override
//            public View getView(FlowLayout parent, int position, String s) {
//                TextView tv = (TextView) mLayoutInflater.inflate(R.layout.item_material_list_flow, null);
//                tv.setText(s);
//                return tv;
//            }
//        });


        ImageView goodsImg = (ImageView) helper.getView(R.id.item_designer_list_avator);
        Glide.with(mContext).load(item.getHeadimgurl()).into(goodsImg);

        ImageView img1 = (ImageView) helper.getView(R.id.item_designer_list_img1);
        ImageView img2 = (ImageView) helper.getView(R.id.item_designer_list_img2);
        ImageView img3 = (ImageView) helper.getView(R.id.item_designer_list_img3);

        if (!ObjectUtils.isEmpty(item.getMaterialList())) {
            if (!StringUtils.isEmpty(item.getMaterialList().get(0))) {
                Glide.with(mContext).load(item.getMaterialList().get(0)).into(img1);
            }

            if (item.getMaterialList().size() > 1) {
                if (!StringUtils.isEmpty(item.getMaterialList().get(1))) {
                    Glide.with(mContext).load(item.getMaterialList().get(1)).into(img2);
                }

            }
            if (item.getMaterialList().size() > 2) {
                if (!StringUtils.isEmpty(item.getMaterialList().get(2))) {
                    Glide.with(mContext).load(item.getMaterialList().get(2)).into(img3);
                }
            }
        }

    }
}
