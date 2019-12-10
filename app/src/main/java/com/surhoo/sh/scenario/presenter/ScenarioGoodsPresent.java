package com.surhoo.sh.scenario.presenter;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.scenario.view.IScenarioGoodsView;

public class ScenarioGoodsPresent implements BasePresenter<IScenarioGoodsView> {

    IScenarioGoodsView iScenarioGoodsView;
    Activity activity;

    @Override
    public void bindView(Activity activity, IScenarioGoodsView view) {
        this.activity =activity;
        this.iScenarioGoodsView = view;
    }

    @Override
    public void unBindView() {
        iScenarioGoodsView = null;
    }

    public void requestData(int pageSize,int pageIndex,int sortType,int sceneId ){
        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize", pageSize);
        httpParams.put("pageIndex", pageIndex);
        httpParams.put("type", 1);
        httpParams.put("sortType", sortType);
        httpParams.put("sceneId", sceneId);

        NetworkReturnUtil.requestPage(iScenarioGoodsView,activity,Api.SCENARIOCATEGORYLIST,httpParams,GoodsBean.class,pageIndex);

    }

}
