package com.surhoo.sh.scenario.presenter;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.base.PagerBaseView;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.scenario.view.IScenarioView;

import java.util.List;


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

        NetworkReturnUtil.requestOne(scenarioView, activity, Api.SCENARIODETAIL + "/" + sceneId, httpParams, ScenarioBean.class);

    }

}

