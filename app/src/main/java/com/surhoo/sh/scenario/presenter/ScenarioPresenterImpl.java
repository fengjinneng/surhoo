package com.surhoo.sh.scenario.presenter;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.scenario.view.IScenarioView;


public class ScenarioPresenterImpl implements IScenarioPresenter {


    public ScenarioPresenterImpl() {
    }

    IScenarioView scenarioView;

    private Activity activity;

    @Override
    public void bindView(Activity activity, IScenarioView view) {

        this.activity = activity;
        this.scenarioView = view;
    }

    @Override
    public void unBindView() {
        scenarioView = null;
    }

    @Override
    public void requestData(int sceneId) {
        HttpParams httpParams = new HttpParams();

        NetworkReturnUtil.requestBeanResultUseGet(scenarioView, activity, Api.SCENARIODETAIL + "/" + sceneId, httpParams, ScenarioBean.class);

    }

}

