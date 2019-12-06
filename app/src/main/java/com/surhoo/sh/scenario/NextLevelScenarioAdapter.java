package com.surhoo.sh.scenario;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.scenario.bean.NextLevelScenarioBean;
import com.surhoo.sh.scenario.bean.ScenarioBean;

import java.util.List;

public class NextLevelScenarioAdapter extends BaseQuickAdapter<ScenarioBean.InfoListBean, BaseViewHolder> {



    public NextLevelScenarioAdapter(int layoutResId, @Nullable List<ScenarioBean.InfoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ScenarioBean.InfoListBean item) {


        ImageView imageView = (ImageView) helper.getView(R.id.item_scenario_level_icon);
        GlideUtil.loadCircleImage(mContext,item.getIcon(),imageView);

        helper.setText(R.id.item_scenario_level_name,item.getName());

    }
}
