package com.surhoo.sh.scenario;


import com.surhoo.sh.R;
import com.surhoo.sh.scenario.bean.NextLevelScenarioBean;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.scenario.presenter.ScenarioPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ScenarioModule {

    @Provides
    List getScenarioDatas(){
        return new ArrayList<>();
    }

    @Provides
    ScenarioPresenterImpl getScenarioPresenterImpl() {
        return new ScenarioPresenterImpl();
    }

    @Provides
    ScenarioAdapter getScenarioAdapter(List datas){
        return new ScenarioAdapter(R.layout.item_scenario,datas);
    }


    @Provides
    List<NextLevelScenarioBean> getNextLevelScenarioDatas(){
        return new ArrayList<>();
    }

    @Provides
    NextLevelScenarioAdapter getNextLevelScenarioAdapter(ArrayList<NextLevelScenarioBean> datas){
        return new NextLevelScenarioAdapter(R.layout.item_scenario,datas);
    }

}
