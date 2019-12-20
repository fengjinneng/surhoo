package com.surhoo.sh.shop.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.common.util.NetworkImageHolderView;
import com.surhoo.sh.goods.fragment.GoodsListFragment;
import com.surhoo.sh.shop.ShopDetailBean;
import com.surhoo.sh.shop.presenter.ShopPresenter;
import com.surhoo.sh.shop.presenter.impl.ShopPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopActivity extends BaseActivity implements ShopView{

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_shop_logo)
    ImageView activityShopLogo;
    @BindView(R.id.activity_shop_name)
    TextView activityShopName;
    @BindView(R.id.activity_shop_banner)
    ConvenientBanner activityShopBanner;
    @BindView(R.id.activity_shop_category_tab)
    SlidingTabLayout activityShopCategoryTab;
    @BindView(R.id.activity_shop_sort_tab)
    SlidingTabLayout activityShopSortTab;
    @BindView(R.id.activity_shop_viewPager)
    ViewPager activityShopViewPager;


    ShopPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public int getContentView() {
        return R.layout.activity_shop;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        presenter = new ShopPresenterImpl();

        presenter.bindView(this,this);

        toolbarLayoutTitle.setText("店铺详情");
    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {
        presenter.requestData(false,1);
    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void showData(ShopDetailBean shopDetailBean) {
        activityShopName.setText(shopDetailBean.getName());

        Glide.with(this).load(shopDetailBean.getShopLogo()).into(activityShopLogo);

        List<String> bannerData = new ArrayList<>();

        for (int i = 0; i < shopDetailBean.getBannerList().size(); i++) {
            bannerData.add(shopDetailBean.getBannerList().get(i));
        }

        activityShopBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, bannerData);

//        convenientBanner.setPageIndicator(new int[]{R.mipmap.banner_unchoiced, R.mipmap.banner_choiced});
//        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置如果只有一组数据时不能滑动
//        convenientBanner.setPointViewVisible(bannerData.size() == 1 ? false : true); // 指示器
        activityShopBanner.setManualPageable(bannerData.size() == 1 ? false : true);//设置false,手动影响（设置了该项无法手动切换）
        activityShopBanner.setCanLoop(bannerData.size() == 1 ? false : true); // 是否循环

        activityShopBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        List<Fragment> fragments = new ArrayList<>();
        String[] arr = new String[shopDetailBean.getClassifyList().size()];

        for (int i = 0; i < shopDetailBean.getClassifyList().size(); i++) {
            fragments.add(GoodsListFragment.newInstance(2,shopDetailBean.getClassifyList().get(i).getClassifyId(),1));
            arr[i] = shopDetailBean.getClassifyList().get(i).getName();
        }


        activityShopViewPager.setAdapter(new BaseViewpageAdapter(getSupportFragmentManager(), fragments));
        activityShopCategoryTab.setViewPager(activityShopViewPager, arr);


    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
