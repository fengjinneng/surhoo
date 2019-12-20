package com.surhoo.sh.search.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.material.bean.MaterialBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchMaterialAdapter extends BaseQuickAdapter<MaterialBean
        , BaseViewHolder> {


    public SearchMaterialAdapter(int layoutResId, @Nullable List<MaterialBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MaterialBean item) {


        helper.setText(R.id.item_material_name, item.getName());

        helper.setText(R.id.item_material_price, "¥" + item.getPrice());

        helper.setText(R.id.item_material_content, item.getDetail());

        ImageView img = (ImageView) helper.getView(R.id.item_material_img);

        GlideUtil.loadDefaultImg(mContext, item.getLogo() + "?x-oss-process=image/resize,w_320", img);

        TagFlowLayout flowLayout = (TagFlowLayout) helper.getView(R.id.item_material_tag);

        List<String> strings = new ArrayList<>();

        if (!ObjectUtils.isEmpty(item.getLabelInfoList())) {
            flowLayout.setVisibility(View.VISIBLE);
            for (int i = 0; i < item.getLabelInfoList().size(); i++) {
                strings.add(item.getLabelInfoList().get(i).getName());
            }
            if (item.isHasTagAdapter()) {
                TagAdapter tagAdapter = item.getTagAdapter();
                flowLayout.setAdapter(tagAdapter);
            } else {
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

        } else {
            flowLayout.setVisibility(View.GONE);
        }



    }
}
