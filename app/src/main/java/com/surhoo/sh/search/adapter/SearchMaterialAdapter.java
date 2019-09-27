package com.surhoo.sh.search.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.material.MaterialBean;
import com.surhoo.sh.scenario.bean.ScenarioBean;
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


        helper.setText(R.id.item_material_name,item.getName());

        helper.setText(R.id.item_material_price,"¥"+item.getPrice());

        helper.setText(R.id.item_material_content,item.getDetail());

        ImageView img = (ImageView) helper.getView(R.id.item_material_img);

        Glide.with(mContext).load(item.getLogo()).into(img);


        TagFlowLayout flowLayout = (TagFlowLayout) helper.getView(R.id.item_material_tag);


        List<String> strings = new ArrayList<>();

        for (int i = 0; i < item.getLabelInfoList().size(); i++) {
            strings.add(item.getLabelInfoList().get(i).getName());
        }

        flowLayout.setAdapter(new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mLayoutInflater.inflate(R.layout.item_material_list_flow, null);
                tv.setText(s);
                return tv;
            }
        });

    }
}
