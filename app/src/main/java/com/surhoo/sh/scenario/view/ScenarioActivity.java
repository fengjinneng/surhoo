package com.surhoo.sh.scenario.view;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.scenario.NextLevelScenarioAdapter;
import com.surhoo.sh.scenario.ScenarioAdapter;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.scenario.fragment.ScenarioGoodsFragment;
import com.surhoo.sh.scenario.fragment.ScenarioMaterialFragment;
import com.surhoo.sh.scenario.presenter.ScenarioPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    SlidingTabLayout activityScenarioCategoryTab;
    @BindView(R.id.activity_scenario_viewPager)
    ViewPager activityScenarioViewPager;


    ScenarioPresenterImpl presenter;

    ScenarioAdapter scenarioAdapter;

    List<ScenarioBean.InfoListBean> nextLevelScenarioDatas;
    @BindView(R.id.activity_scenario_category_img)
    ImageView activityScenarioCategoryImg;
    @BindView(R.id.activity_scenario_sort_goods_normal)
    TextView activityScenarioSortGoodsNormal;
    @BindView(R.id.activity_scenario_sort_goods_sale_count)
    TextView activityScenarioSortGoodsSaleCount;
    @BindView(R.id.activity_scenario_sort_goods_price)
    TextView activityScenarioSortGoodsPrice;
    @BindView(R.id.activity_scenario_sort_goods_tab)
    ConstraintLayout activityScenarioSortGoodsTab;
    @BindView(R.id.activity_scenario_sort_material_normal)
    TextView activityScenarioSortMaterialNormal;
    @BindView(R.id.activity_scenario_sort_material_sale_count)
    TextView activityScenarioSortMaterialSaleCount;
    @BindView(R.id.activity_scenario_sort_material_price)
    TextView activityScenarioSortMaterialPrice;
    @BindView(R.id.activity_scenario_sort_material_tab)
    ConstraintLayout activityScenarioSortMaterialTab;


    //    1 商品 2 素材
    private int type = 1;
    //排序方式 1综合 2销量 3价格
    private int sortType = 1;

    private Integer id;

    private onGoodsSortTypeClickListener onGoodsSortTypeClickListener;
    private onMaterialSortTypeClickListener onMaterialSortTypeClickListener;


    @Override
    public int getContentView() {
        return R.layout.activity_scenario;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        id = getIntent().getIntExtra("id", 0);

        presenter = new ScenarioPresenterImpl();
        presenter.bindView(this, this);

        toolbarLayoutTitle.setText("场景");

    }

    public void setOnGoodsSortTypeClickListener(ScenarioActivity.onGoodsSortTypeClickListener onGoodsSortTypeClickListener) {
        this.onGoodsSortTypeClickListener = onGoodsSortTypeClickListener;
    }

    public void setOnMaterialSortTypeClickListener(ScenarioActivity.onMaterialSortTypeClickListener onMaterialSortTypeClickListener) {
        this.onMaterialSortTypeClickListener = onMaterialSortTypeClickListener;
    }

    @Override
    public void initData() {


    }


    @Override
    public void requestData() {
        presenter.requestData(id);
        requestGoods();
        requestMaterial();
    }

    private boolean hasGoods;
    private boolean hasMaterial;

    private boolean finishRequestMaterial;
    private boolean finishRequestGoods;

    private void requestMaterial() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize", 5);
        httpParams.put("pageIndex", 1);
        httpParams.put("type", 2);
        httpParams.put("sortType", sortType);
        httpParams.put("sceneId", id);

        GetRequest<String> request = OkGo.<String>get(Api.SCENARIOCATEGORYLIST)
                .tag(this)
                .headers("Authorization", getResources().getString(R.string.Auth))
                .params(httpParams);

        StringCallback stringCallback = new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("SCENARIOCATEGORYLIST", response.body());
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = JSONObject.parseObject(response.body());
                        finishRequestMaterial = true;

                        if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                            ToastUtils.showShort(jsonObject.getString("msg"));
                            return;
                        }
                        if (jsonObject.getIntValue("total") == 0) {
                            dowork();
                            return;
                        }
                        hasMaterial = true;
                        if (hasGoods) {
                            activityScenarioCategoryTab.setVisibility(View.VISIBLE);
                        } else {
                            activityScenarioCategoryTab.setVisibility(View.GONE);
                        }

                        dowork();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        };

        request.execute(stringCallback);
    }


    private synchronized void dowork() {
        if (finishRequestGoods && finishRequestMaterial) {
            if (hasGoods && hasMaterial) {
                List<Fragment> fragments = new ArrayList<>();
                fragments.add(ScenarioGoodsFragment.newInstance(id));
                fragments.add(ScenarioMaterialFragment.newInstance(id));
                activityScenarioViewPager.setAdapter(new BaseViewpageAdapter(getSupportFragmentManager(), fragments));
                activityScenarioCategoryTab.setViewPager(activityScenarioViewPager, new String[]{"商品", "素材"});
                activityScenarioViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }
                    @Override
                    public void onPageSelected(int i) {
                        type = i + 1;
                        switch (i){
                            case 0:
                                activityScenarioSortGoodsTab.setVisibility(View.VISIBLE);
                                activityScenarioSortMaterialTab.setVisibility(View.GONE);

                                break;
                            case 1:
                                activityScenarioSortGoodsTab.setVisibility(View.GONE);
                                activityScenarioSortMaterialTab.setVisibility(View.VISIBLE);
                                break;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });

                return;
            }
            if (hasGoods) {
                List<Fragment> fragments = new ArrayList<>();
                fragments.add(ScenarioGoodsFragment.newInstance(id));
                activityScenarioViewPager.setAdapter(new BaseViewpageAdapter(getSupportFragmentManager(), fragments));
                return;
            }
            if (hasMaterial) {
                List<Fragment> fragments = new ArrayList<>();
                fragments.add(ScenarioMaterialFragment.newInstance(id));
                activityScenarioViewPager.setAdapter(new BaseViewpageAdapter(getSupportFragmentManager(), fragments));
                type = 2;
            }
        }
    }

    private void requestGoods() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize", 5);
        httpParams.put("pageIndex", 1);
        httpParams.put("type", 1);
        httpParams.put("sortType", sortType);
        httpParams.put("sceneId", id);

        GetRequest<String> request = OkGo.<String>get(Api.SCENARIOCATEGORYLIST)
                .tag(this)
                .headers("Authorization", getResources().getString(R.string.Auth))
                .params(httpParams);

        StringCallback stringCallback = new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("SCENARIOCATEGORYLIST", response.body());
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = JSONObject.parseObject(response.body());
                        finishRequestGoods = true;
                        if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                            ToastUtils.showShort(jsonObject.getString("msg"));
                            return;
                        }
                        if (jsonObject.getIntValue("total") == 0) {
                            dowork();
                            return;
                        }
                        hasGoods = true;
                        if (hasMaterial) {
                            activityScenarioCategoryTab.setVisibility(View.VISIBLE);
                        } else {
                            activityScenarioCategoryTab.setVisibility(View.GONE);
                        }

                        dowork();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        };

        request.execute(stringCallback);
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    public void showData(ScenarioBean scenarioBean) {

        GlideUtil.loadDefaultImg(this, scenarioBean.getLogo(), activityScenarioCategoryImg);

        if (!ObjectUtils.isEmpty(scenarioBean.getInfoList())) {
            activityScenarioNextlevelRecyclerview.setVisibility(View.VISIBLE);
            nextLevelScenarioDatas = new ArrayList<>();
            nextLevelScenarioDatas.addAll(scenarioBean.getInfoList());
            nextLevelScenarioAdapter = new NextLevelScenarioAdapter(R.layout.item_scenario_level, nextLevelScenarioDatas);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            activityScenarioNextlevelRecyclerview.setLayoutManager(linearLayoutManager);
            activityScenarioNextlevelRecyclerview.setAdapter(nextLevelScenarioAdapter);
        }

    }

    @OnClick({R.id.toolbar_layout_back,R.id.activity_scenario_sort_goods_normal, R.id.activity_scenario_sort_goods_sale_count
            , R.id.activity_scenario_sort_goods_price, R.id.activity_scenario_sort_material_normal
            , R.id.activity_scenario_sort_material_sale_count,
            R.id.activity_scenario_sort_material_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;
            case R.id.activity_scenario_sort_goods_normal:
                if(ObjectUtils.isEmpty(onGoodsSortTypeClickListener)) return;
                onGoodsSortTypeClickListener.onGoodsSortTypeClick(1);
                activityScenarioSortGoodsNormal.setTextColor(getResources().getColor(R.color.themeColor));
                activityScenarioSortGoodsSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortGoodsPrice.setTextColor(getResources().getColor(R.color.saleColor));
                break;
            case R.id.activity_scenario_sort_goods_sale_count:
                if(ObjectUtils.isEmpty(onGoodsSortTypeClickListener)) return;
                onGoodsSortTypeClickListener.onGoodsSortTypeClick(2);
                activityScenarioSortGoodsNormal.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortGoodsSaleCount.setTextColor(getResources().getColor(R.color.themeColor));
                activityScenarioSortGoodsPrice.setTextColor(getResources().getColor(R.color.saleColor));
                break;
            case R.id.activity_scenario_sort_goods_price:
                if(ObjectUtils.isEmpty(onGoodsSortTypeClickListener)) return;
                onGoodsSortTypeClickListener.onGoodsSortTypeClick(3);
                activityScenarioSortGoodsNormal.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortGoodsSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortGoodsPrice.setTextColor(getResources().getColor(R.color.themeColor));
                break;
            case R.id.activity_scenario_sort_material_normal:
                if(ObjectUtils.isEmpty(onMaterialSortTypeClickListener)) return;
                onMaterialSortTypeClickListener.onMaterialSortTypeClick(1);
                activityScenarioSortMaterialNormal.setTextColor(getResources().getColor(R.color.themeColor));
                activityScenarioSortMaterialSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortMaterialPrice.setTextColor(getResources().getColor(R.color.saleColor));
                break;
            case R.id.activity_scenario_sort_material_sale_count:
                if(ObjectUtils.isEmpty(onMaterialSortTypeClickListener)) return;
                onMaterialSortTypeClickListener.onMaterialSortTypeClick(2);
                activityScenarioSortMaterialNormal.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortMaterialSaleCount.setTextColor(getResources().getColor(R.color.themeColor));
                activityScenarioSortMaterialPrice.setTextColor(getResources().getColor(R.color.saleColor));
                break;
            case R.id.activity_scenario_sort_material_price:
                if(ObjectUtils.isEmpty(onMaterialSortTypeClickListener)) return;
                onMaterialSortTypeClickListener.onMaterialSortTypeClick(3);
                activityScenarioSortMaterialNormal.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortMaterialSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortMaterialPrice.setTextColor(getResources().getColor(R.color.themeColor));

                break;
        }
    }

    public interface onGoodsSortTypeClickListener {
        void onGoodsSortTypeClick(int sortType);
    }

    public interface onMaterialSortTypeClickListener {
        void onMaterialSortTypeClick(int sortType);
    }
}
