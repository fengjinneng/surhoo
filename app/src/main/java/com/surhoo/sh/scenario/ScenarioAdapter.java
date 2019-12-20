package com.surhoo.sh.scenario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.scenario.bean.ScenarioBean;

import java.util.List;

public class ScenarioAdapter extends BaseQuickAdapter<ScenarioBean, BaseViewHolder> {

    public ScenarioAdapter(int layoutResId, @Nullable List<ScenarioBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, ScenarioBean item) {

        helper.setText(R.id.item_scenario_content,item.getDetail());

        ImageView img = (ImageView) helper.getView(R.id.item_scenario_img);
        Glide.with(mContext).load(item.getLogo()).into(img);

    }
}
