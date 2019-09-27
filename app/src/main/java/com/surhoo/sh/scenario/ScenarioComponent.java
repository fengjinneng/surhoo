package com.surhoo.sh.scenario;


import com.surhoo.sh.scenario.view.ScenarioActivity;

import dagger.Component;

@Component(modules = ScenarioModule.class)
public interface ScenarioComponent {


    void inject(ScenarioActivity scenarioActivity);

}