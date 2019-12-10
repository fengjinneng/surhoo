package com.surhoo.sh.scenario.presenter;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.scenario.view.IScenarioMaterialView;

public class ScenarioMaterialPresent implements BasePresenter<IScenarioMaterialView> {

    IScenarioMaterialView iScenarioMaterialView;
    Activity activity;

    @Override
    public void bindView(Activity activity, IScenarioMaterialView view) {
        this.activity =activity;
        this.iScenarioMaterialView = view;
    }

    @Override
    public void unBindView() {
        iScenarioMaterialView = null;
    }

    public void requestData(int pageSize,int pageIndex,int sortType,int sceneId ){
        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize", pageSize);
        httpParams.put("pageIndex", pageIndex);
        httpParams.put("type", 2);
        httpParams.put("sortType", sortType);
        httpParams.put("sceneId", sceneId);

        NetworkReturnUtil.requestPage(iScenarioMaterialView,activity,Api.SCENARIOCATEGORYLIST,httpParams,MaterialBean.class,pageIndex);

    }

}
