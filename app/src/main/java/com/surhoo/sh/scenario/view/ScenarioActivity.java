package com.surhoo.sh.scenario.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.scenario.NextLevelScenarioAdapter;
import com.surhoo.sh.scenario.ScenarioAdapter;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.scenario.presenter.ScenarioPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScenarioActivity extends BaseActivity implements IScenarioView {



    NextLevelScenarioAdapter nextLevelScenarioAdapter;
    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_scenario_nextlevel_recyclerview)
    RecyclerView activityScenarioNextlevelRecyclerview;
    @BindView(R.id.activity_scenario_category_tab)
    TabLayout activityScenarioCategoryTab;
    @BindView(R.id.activity_scenario_sort_tab)
    TabLayout activityScenarioSortTab;
    @BindView(R.id.activity_scenario_viewPager)
    ViewPager activityScenarioViewPager;


    ScenarioPresenterImpl presenter;

    ScenarioAdapter scenarioAdapter;

    List nextLevelScenarioDatas;

    private int pageSize = 10;
    private int pageIndex = 1;

    //    1 商品 2 素材
    private int type = 1;
    //排序方式 1综合 2销量 3价格
    private int sortType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario);
        ButterKnife.bind(this);


        initView();


        presenter =  new ScenarioPresenterImpl();
        presenter.bindView(this, this);

        nextLevelScenarioAdapter  = new NextLevelScenarioAdapter(R.layout.item_goods_list,nextLevelScenarioDatas);
        activityScenarioNextlevelRecyclerview.setAdapter(nextLevelScenarioAdapter);

        requestData();


    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("场景");

        activityScenarioNextlevelRecyclerview.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL,false));
    }

    @Override
    public void requestData() {

        presenter.requestData(29);
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }



    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showData(ScenarioBean scenarioBean) {
            ToastUtils.showShort(scenarioBean.getName());
    }
}
