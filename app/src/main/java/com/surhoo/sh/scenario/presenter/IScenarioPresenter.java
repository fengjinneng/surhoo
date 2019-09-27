package com.surhoo.sh.scenario.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.scenario.ScenarioAdapter;
import com.surhoo.sh.scenario.view.IScenarioView;

public interface IScenarioPresenter extends BasePresenter<IScenarioView> {


    void requestData(int sceneId);

}
