package com.surhoo.sh.home.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.goods.view.impl.CategoryActivity;
import com.surhoo.sh.home.bean.HomePageBean;
import com.surhoo.sh.home.presenter.HomePresenter;
import com.surhoo.sh.home.presenter.HomePresenterImpl;
import com.surhoo.sh.home.view.HomeView;
import com.surhoo.sh.home.vlayout.BannerLayoutAdapter;
import com.surhoo.sh.home.vlayout.CutPriceLayoutAdapter;
import com.surhoo.sh.home.vlayout.DesignerLayoutAdapter;
import com.surhoo.sh.home.vlayout.GoodsLayoutAdapter;
import com.surhoo.sh.home.vlayout.LevelOneScenarioLayoutAdapter;
import com.surhoo.sh.home.vlayout.ScenarioLayoutAdapter;
import com.surhoo.sh.home.vlayout.TitleLayoutAdapter;
import com.surhoo.sh.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.fragment_home_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_home_category)
    ImageView fragmentHomeCategory;
    @BindView(R.id.fragment_home_search)
    TextView fragmentHomeSearch;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return getLayoutInflater().inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void init() {
        layoutManager = new VirtualLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        delegateAdapter = new DelegateAdapter(layoutManager);
        recyclerView.setAdapter(delegateAdapter);

        homePresenter = new HomePresenterImpl();

        homePresenter.bindView(getActivity(), this);
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void requestData() {
        homePresenter.requestData();
    }

    VirtualLayoutManager layoutManager;
    DelegateAdapter delegateAdapter;
    HomePresenter homePresenter;


    @Override
    public void showData(HomePageBean homePageBean) {

        if (!ObjectUtils.isEmpty(homePageBean.getBANNER())) {
            BannerLayoutAdapter bannerLayoutAdapter = new BannerLayoutAdapter(getContext(), homePageBean.getBANNER());
            delegateAdapter.addAdapter(bannerLayoutAdapter);
        }

        if (!ObjectUtils.isEmpty(homePageBean.getFIRSTSCENE())) {
            LevelOneScenarioLayoutAdapter levelOneScenarioLayoutAdapter = new LevelOneScenarioLayoutAdapter(getContext(), homePageBean.getFIRSTSCENE());
            delegateAdapter.addAdapter(levelOneScenarioLayoutAdapter);
        }

        if (!ObjectUtils.isEmpty(homePageBean.getSCENE())) {
            TitleLayoutAdapter titleLayoutAdapter = new TitleLayoutAdapter(getContext(), "热门场景");
            delegateAdapter.addAdapter(titleLayoutAdapter);
            ScenarioLayoutAdapter scenarioLayoutAdapter = new ScenarioLayoutAdapter(getContext(), homePageBean.getSCENE());
            delegateAdapter.addAdapter(scenarioLayoutAdapter);
        }

        if (!ObjectUtils.isEmpty(homePageBean.getDESIGNER())) {
            TitleLayoutAdapter titleLayoutAdapter = new TitleLayoutAdapter(getContext(), "设计师榜");
            delegateAdapter.addAdapter(titleLayoutAdapter);
            DesignerLayoutAdapter designerLayoutAdapter = new DesignerLayoutAdapter(getContext(), homePageBean.getDESIGNER());
            delegateAdapter.addAdapter(designerLayoutAdapter);
        }

        if (!ObjectUtils.isEmpty(homePageBean.getGOODS())) {
            TitleLayoutAdapter titleLayoutAdapter = new TitleLayoutAdapter(getContext(), "砍价活动");
            delegateAdapter.addAdapter(titleLayoutAdapter);
            CutPriceLayoutAdapter cutPriceLayoutAdapter = new CutPriceLayoutAdapter(getContext(), homePageBean.getBARGAINGOODS());
            delegateAdapter.addAdapter(cutPriceLayoutAdapter);
        }

        if (!ObjectUtils.isEmpty(homePageBean.getGOODS())) {
            TitleLayoutAdapter titleLayoutAdapter = new TitleLayoutAdapter(getContext(), "热门商品");
            delegateAdapter.addAdapter(titleLayoutAdapter);
            GoodsLayoutAdapter goodsLayoutAdapter = new GoodsLayoutAdapter(getContext(), homePageBean.getGOODS());
            delegateAdapter.addAdapter(goodsLayoutAdapter);

        }

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @OnClick(R.id.fragment_home_category)
    public void onViewClicked() {
        ActivityUtils.startActivity(CategoryActivity.class);
    }

    @OnClick(R.id.fragment_home_search)
    public void onSearchClicked() {
        ActivityUtils.startActivity(SearchActivity.class);
    }
}
