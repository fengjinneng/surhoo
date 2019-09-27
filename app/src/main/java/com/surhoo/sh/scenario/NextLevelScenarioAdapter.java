package com.surhoo.sh.scenario;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.scenario.bean.NextLevelScenarioBean;
import com.surhoo.sh.scenario.bean.ScenarioBean;

import java.util.List;

public class NextLevelScenarioAdapter extends BaseQuickAdapter<NextLevelScenarioBean, BaseViewHolder> {



    public NextLevelScenarioAdapter(int layoutResId, @Nullable List<NextLevelScenarioBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NextLevelScenarioBean item) {

    }
}
