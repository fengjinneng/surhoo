package com.surhoo.sh.scenario.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.scenario.NextLevelScenarioAdapter;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.scenario.fragment.ScenarioGoodsFragment;
import com.surhoo.sh.scenario.fragment.ScenarioMaterialFragment;
import com.surhoo.sh.scenario.presenter.ScenarioPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
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
    SlidingTabLayout activityScenarioCategoryTab;
    @BindView(R.id.activity_scenario_viewPager)
    ViewPager activityScenarioViewPager;


    ScenarioPresenterImpl presenter;

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

    @BindView(R.id.activity_scenario_nextlevel_decoration)
    View nextLevelDecoraation;
    @BindView(R.id.activity_scenario_sort_goods_price_img)
    ImageView activityScenarioSortGoodsPriceImg;
    @BindView(R.id.activity_scenario_sort_goods_price_layout)
    ConstraintLayout activityScenarioSortGoodsPriceLayout;
    @BindView(R.id.activity_scenario_sort_material_price_img)
    ImageView activityScenarioSortMaterialPriceImg;
    @BindView(R.id.activity_scenario_sort_material_price_layout)
    ConstraintLayout activityScenarioSortMaterialPriceLayout;


    //    1 商品 2 素材
    private int type = 1;
    //排序方式 1综合 2销量 3价格
    private int goodSortType = 1;
    private int materialSortType = 1;

    private Integer id;
    private String title;

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
        title =getIntent().getStringExtra("title");

        presenter = new ScenarioPresenterImpl();
        presenter.bindView(this, this);

        toolbarLayoutTitle.setText(title);

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
        httpParams.put("sortType", materialSortType);
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
                        switch (i) {
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
                activityScenarioSortGoodsTab.setVisibility(View.VISIBLE);
                activityScenarioSortMaterialTab.setVisibility(View.GONE);
                return;
            }
            if (hasMaterial) {
                List<Fragment> fragments = new ArrayList<>();
                fragments.add(ScenarioMaterialFragment.newInstance(id));
                activityScenarioViewPager.setAdapter(new BaseViewpageAdapter(getSupportFragmentManager(), fragments));
                activityScenarioSortGoodsTab.setVisibility(View.GONE);
                activityScenarioSortMaterialTab.setVisibility(View.VISIBLE);
            }
        }
    }

    private void requestGoods() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize", 5);
        httpParams.put("pageIndex", 1);
        httpParams.put("type", 1);
        httpParams.put("sortType", goodSortType);
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
    public void showBeanData(ScenarioBean scenarioBean) {

        GlideUtil.loadDefaultImg(this, scenarioBean.getLogo(), activityScenarioCategoryImg);

        if (!ObjectUtils.isEmpty(scenarioBean.getInfoList())) {
            nextLevelDecoraation.setVisibility(View.VISIBLE);
            activityScenarioNextlevelRecyclerview.setVisibility(View.VISIBLE);
            nextLevelScenarioDatas = new ArrayList<>();
            nextLevelScenarioDatas.addAll(scenarioBean.getInfoList());
            nextLevelScenarioAdapter = new NextLevelScenarioAdapter(R.layout.item_scenario_level, nextLevelScenarioDatas);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            activityScenarioNextlevelRecyclerview.setLayoutManager(linearLayoutManager);
            activityScenarioNextlevelRecyclerview.setAdapter(nextLevelScenarioAdapter);
            nextLevelScenarioAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (ClickUtil.isFastClick()) {
                        ScenarioBean.InfoListBean infoListBean = (ScenarioBean.InfoListBean) adapter.getData().get(position);
                        Integer sceneId = infoListBean.getSceneId();
                        Intent intent = new Intent(ScenarioActivity.this, ScenarioActivity.class);
                        intent.putExtra("id", sceneId);
                        intent.putExtra("title", infoListBean.getName());
                        ActivityUtils.startActivity(intent);
                    }
                }
            });

        }

    }


    @OnClick({R.id.toolbar_layout_back, R.id.activity_scenario_sort_goods_normal, R.id.activity_scenario_sort_goods_sale_count
            , R.id.activity_scenario_sort_goods_price_layout, R.id.activity_scenario_sort_material_normal
            , R.id.activity_scenario_sort_material_sale_count,
            R.id.activity_scenario_sort_material_price_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;
            case R.id.activity_scenario_sort_goods_normal:
                if (ObjectUtils.isEmpty(onGoodsSortTypeClickListener)) return;
                goodSortType = 1;
                activityScenarioSortGoodsPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_default));
                onGoodsSortTypeClickListener.onGoodsSortTypeClick(goodSortType);
                activityScenarioSortGoodsNormal.setTextColor(getResources().getColor(R.color.themeColor));
                activityScenarioSortGoodsSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortGoodsPrice.setTextColor(getResources().getColor(R.color.saleColor));
                break;
            case R.id.activity_scenario_sort_goods_sale_count:
                if (ObjectUtils.isEmpty(onGoodsSortTypeClickListener)) return;
                goodSortType = 2;
                activityScenarioSortGoodsPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_default));
                onGoodsSortTypeClickListener.onGoodsSortTypeClick(goodSortType);
                activityScenarioSortGoodsNormal.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortGoodsSaleCount.setTextColor(getResources().getColor(R.color.themeColor));
                activityScenarioSortGoodsPrice.setTextColor(getResources().getColor(R.color.saleColor));
                break;
            case R.id.activity_scenario_sort_goods_price_layout:
                if (ObjectUtils.isEmpty(onGoodsSortTypeClickListener)) return;
                if (goodSortType == 3) {
                    goodSortType = 4;
                    activityScenarioSortGoodsPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_up));
                } else if (goodSortType == 4) {
                    goodSortType = 3;
                    activityScenarioSortGoodsPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_down));
                } else {
                    goodSortType = 3;
                    activityScenarioSortGoodsPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_down));
                }
                onGoodsSortTypeClickListener.onGoodsSortTypeClick(goodSortType);
                activityScenarioSortGoodsNormal.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortGoodsSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortGoodsPrice.setTextColor(getResources().getColor(R.color.themeColor));
                break;
            case R.id.activity_scenario_sort_material_normal:
                if (ObjectUtils.isEmpty(onMaterialSortTypeClickListener)) return;
                materialSortType = 1;
                activityScenarioSortMaterialPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_default));
                onMaterialSortTypeClickListener.onMaterialSortTypeClick(materialSortType);
                activityScenarioSortMaterialNormal.setTextColor(getResources().getColor(R.color.themeColor));
                activityScenarioSortMaterialSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortMaterialPrice.setTextColor(getResources().getColor(R.color.saleColor));
                break;
            case R.id.activity_scenario_sort_material_sale_count:
                if (ObjectUtils.isEmpty(onMaterialSortTypeClickListener)) return;
                materialSortType = 2;
                activityScenarioSortMaterialPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_default));
                onMaterialSortTypeClickListener.onMaterialSortTypeClick(materialSortType);
                activityScenarioSortMaterialNormal.setTextColor(getResources().getColor(R.color.saleColor));
                activityScenarioSortMaterialSaleCount.setTextColor(getResources().getColor(R.color.themeColor));
                activityScenarioSortMaterialPrice.setTextColor(getResources().getColor(R.color.saleColor));
                break;
            case R.id.activity_scenario_sort_material_price_layout:
                if (ObjectUtils.isEmpty(onMaterialSortTypeClickListener)) return;
                if (materialSortType == 3) {
                    materialSortType = 4;
                    activityScenarioSortMaterialPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_up));
                } else if (materialSortType == 4) {
                    materialSortType = 3;
                    activityScenarioSortMaterialPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_down));
                } else {
                    materialSortType = 3;
                    activityScenarioSortMaterialPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_down));
                }
                onMaterialSortTypeClickListener.onMaterialSortTypeClick(materialSortType);
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
