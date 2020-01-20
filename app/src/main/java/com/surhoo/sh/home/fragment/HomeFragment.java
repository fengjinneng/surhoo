package com.surhoo.sh.home.fragment;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.goods.view.impl.CategoryActivity;
import com.surhoo.sh.home.bean.HomePageBean;
import com.surhoo.sh.home.presenter.HomePresenter;
import com.surhoo.sh.home.presenter.HomePresenterImpl;
import com.surhoo.sh.home.view.HomeView;
import com.surhoo.sh.home.vlayout.ArtistLayoutAdapter;
import com.surhoo.sh.home.vlayout.BannerLayoutAdapter;
import com.surhoo.sh.home.vlayout.DesignerLayoutAdapter;
import com.surhoo.sh.home.vlayout.FootLayoutAdapter;
import com.surhoo.sh.home.vlayout.GoodsLayoutAdapter;
import com.surhoo.sh.home.vlayout.LevelOneScenarioLayoutAdapter;
import com.surhoo.sh.home.vlayout.ScenarioExpandLayoutAdapter;
import com.surhoo.sh.home.vlayout.ScenarioLayoutAdapter;
import com.surhoo.sh.home.vlayout.TitleLayoutAdapter;
import com.surhoo.sh.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.fragment_home_swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

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

    private SwipeRefreshLayout.OnRefreshListener refreshListener;

    @Override
    public void init() {
        layoutManager = new VirtualLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        delegateAdapter = new DelegateAdapter(layoutManager);
        recyclerView.setAdapter(delegateAdapter);

        homePresenter = new HomePresenterImpl();

        homePresenter.bindView(getActivity(), this);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);


        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉业务
                swipeRefreshLayout.setRefreshing(true);
                requestData();
            }
        };
        swipeRefreshLayout.setOnRefreshListener(refreshListener);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshListener.onRefresh();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_green_light, android.R.color.holo_blue_light);



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


    private BannerLayoutAdapter bannerLayoutAdapter;
    private List<HomePageBean.BANNERBean> allBanner = new ArrayList<>();

    private LevelOneScenarioLayoutAdapter levelOneScenarioLayoutAdapter;
    private List<HomePageBean.FIRSTSCENEBean> allFirstScenario = new ArrayList<>();

    private ScenarioLayoutAdapter scenarioLayoutAdapter;
    private List<HomePageBean.SCENEBean> allScenario = new ArrayList<>();

    private ArtistLayoutAdapter artistLayoutAdapter;
    private List<HomePageBean.ArtistShopListBean> allArtist = new ArrayList<>();

    private GoodsLayoutAdapter goodsLayoutAdapter;
    private List<HomePageBean.GOODSBean> allGoods = new ArrayList<>();

    private boolean loadDataSuccess;

    private boolean isShowAllFirstScenario;

    @Override
    public void showBeanData(HomePageBean homePageBean) {

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        if (!loadDataSuccess) {
            bannerLayoutAdapter = new BannerLayoutAdapter(getActivity(), allBanner);
            delegateAdapter.addAdapter(bannerLayoutAdapter);

            levelOneScenarioLayoutAdapter = new LevelOneScenarioLayoutAdapter(getActivity(), allFirstScenario);
            delegateAdapter.addAdapter(levelOneScenarioLayoutAdapter);

            if (!ObjectUtils.isEmpty(homePageBean.getFIRSTSCENE()) && homePageBean.getFIRSTSCENE().size() > 8) {
                ScenarioExpandLayoutAdapter adapter = new ScenarioExpandLayoutAdapter(getContext());
                adapter.setOnScenarioExpandClickListener(new ScenarioExpandLayoutAdapter.OnScenarioExpandClickListener() {
                    @Override
                    public void onScenarioExpandClick() {
                        allFirstScenario.clear();
                        if(isShowAllFirstScenario){
                            List<HomePageBean.FIRSTSCENEBean> temp = new ArrayList<>();
                            for (int i = 0; i < homePageBean.getFIRSTSCENE().size(); i++) {
                                if(i>7){
                                    break;
                                }
                                temp.add(homePageBean.getFIRSTSCENE().get(i));
                            }
                            allFirstScenario.addAll(temp);
                            levelOneScenarioLayoutAdapter.notifyDataSetChanged();
                            isShowAllFirstScenario =false;
                        }else {
                            allFirstScenario.addAll(homePageBean.getFIRSTSCENE());
                            levelOneScenarioLayoutAdapter.notifyDataSetChanged();
                            isShowAllFirstScenario =true;
                        }
                    }
                });
                delegateAdapter.addAdapter(adapter);
            }

            TitleLayoutAdapter titleLayoutAdapter = new TitleLayoutAdapter(getActivity(), "热门场景");
            delegateAdapter.addAdapter(titleLayoutAdapter);

            scenarioLayoutAdapter = new ScenarioLayoutAdapter(getActivity(), allScenario);
            delegateAdapter.addAdapter(scenarioLayoutAdapter);

            TitleLayoutAdapter titleLayoutAdapter3 = new TitleLayoutAdapter(getActivity(), "艺术时尚工作室");
            delegateAdapter.addAdapter(titleLayoutAdapter3);

            artistLayoutAdapter = new ArtistLayoutAdapter(getActivity(),homePageBean.getArtistShopList());
            delegateAdapter.addAdapter(artistLayoutAdapter);


            TitleLayoutAdapter titleLayoutAdapter2 = new TitleLayoutAdapter(getActivity(), "热门商品");
            delegateAdapter.addAdapter(titleLayoutAdapter2);

            goodsLayoutAdapter = new GoodsLayoutAdapter(getActivity(), allGoods);
            delegateAdapter.addAdapter(goodsLayoutAdapter);


            delegateAdapter.addAdapter(new FootLayoutAdapter(getActivity()));

        }

        loadDataSuccess = true;


        if (!ObjectUtils.isEmpty(homePageBean.getBANNER())) {
            allBanner.clear();
            allBanner.addAll(homePageBean.getBANNER());
            bannerLayoutAdapter.notifyDataSetChanged();
        }

        if (!ObjectUtils.isEmpty(homePageBean.getFIRSTSCENE())) {
            allFirstScenario.clear();
            List<HomePageBean.FIRSTSCENEBean> temp = new ArrayList<>();

            for (int i = 0; i < homePageBean.getFIRSTSCENE().size(); i++) {
                if(i>7){
                    break;
                }
                temp.add(homePageBean.getFIRSTSCENE().get(i));
            }
            allFirstScenario.addAll(temp);
            levelOneScenarioLayoutAdapter.notifyDataSetChanged();
        }

        if (!ObjectUtils.isEmpty(homePageBean.getSCENE())) {
            allScenario.clear();
            allScenario.addAll(homePageBean.getSCENE());
            scenarioLayoutAdapter.notifyDataSetChanged();
        }


//        if (!ObjectUtils.isEmpty(homePageBean.getDESIGNER())) {
//            TitleLayoutAdapter titleLayoutAdapter = new TitleLayoutAdapter(getContext(), "设计师榜");
//            delegateAdapter.addAdapter(titleLayoutAdapter);
//            DesignerLayoutAdapter designerLayoutAdapter = new DesignerLayoutAdapter(getContext(), homePageBean.getDESIGNER());
//            delegateAdapter.addAdapter(designerLayoutAdapter);
//        }

        if (!ObjectUtils.isEmpty(homePageBean.getArtistShopList())) {

        }

//        if (!ObjectUtils.isEmpty(homePageBean.getGOODS())) {
//            TitleLayoutAdapter titleLayoutAdapter = new TitleLayoutAdapter(getContext(), "砍价活动");
//            delegateAdapter.addAdapter(titleLayoutAdapter);
//            CutPriceLayoutAdapter cutPriceLayoutAdapter = new CutPriceLayoutAdapter(getContext(), homePageBean.getBARGAINGOODS());
//            delegateAdapter.addAdapter(cutPriceLayoutAdapter);
//        }

        if (!ObjectUtils.isEmpty(homePageBean.getGOODS())) {
            allGoods.clear();
            allGoods.addAll(homePageBean.getGOODS());
            goodsLayoutAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @OnClick(R.id.fragment_home_category)
    public void onViewClicked() {
        if (ClickUtil.isFastClick()) {
            ActivityUtils.startActivity(CategoryActivity.class);
        }
    }

    @OnClick(R.id.fragment_home_search)
    public void onSearchClicked() {
        if (ClickUtil.isFastClick()) {
            ActivityUtils.startActivity(SearchActivity.class);
        }
    }
}
