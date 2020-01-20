package com.surhoo.sh.shop.view.impl;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.base.OneResultBaseView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.common.util.CommonUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.shop.bean.ClassifyListBean;
import com.surhoo.sh.shop.bean.ShopDetailBean;
import com.surhoo.sh.shop.fragment.ArtistFragment;
import com.surhoo.sh.shop.fragment.DerivativesFragment;
import com.surhoo.sh.shop.fragment.ProductFragment;
import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

public class ArtistShopDetailActivity extends BaseActivity implements OneResultBaseView<ShopDetailBean> {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_artist_shop_logo)
    ImageView activityArtistShopLogo;
    @BindView(R.id.activity_artist_shop_name)
    TextView activityArtistShopName;
    @BindView(R.id.activity_artist_shop_banner)
    ConvenientBanner activityArtistShopBanner;
    @BindView(R.id.activity_artist_shop_tab)
    SlidingTabLayout activityArtistShopTab;
    @BindView(R.id.activity_shop_viewPager)
    ViewPager activityShopViewPager;

    private int id;

    @Override
    public int getContentView() {
        return R.layout.activity_artist_shop;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id",0);
        toolbarLayoutTitle.setText("艺术家店铺");

    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {

        HttpParams httpParams = new HttpParams();
        httpParams.put("shopId",id);

        NetworkReturnUtil.requestBeanResultUseGet(this,activity, Api.SHOPDETAIL,httpParams, ShopDetailBean.class);

    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showBeanData(ShopDetailBean shopDetailBean) {

        activityArtistShopName.setText(shopDetailBean.getName());

        GlideUtil.loadCircleImage(this,shopDetailBean.getShopLogo(),activityArtistShopLogo);


        List<String> bannerData = new ArrayList<>();

        for (int i = 0; i < shopDetailBean.getBannerList().size(); i++) {
            bannerData.add(shopDetailBean.getBannerList().get(i));
        }

        CommonUtil.setBannerInfo(activityArtistShopBanner,bannerData);

        activityArtistShopBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });


        List<Fragment> fragments = new ArrayList<>();

        List<String> data  = new ArrayList<>();

        if(shopDetailBean.getIsSynopsis()==1){
            fragments.add(ArtistFragment.newInstance(shopDetailBean.getSynopsisId()));
            data.add("艺术家");
        }

        if(shopDetailBean.getIsOriginal()==1){
            fragments.add(ProductFragment.newInstance(shopDetailBean.getShopId()));
            data.add("作品");
        }

        if(shopDetailBean.getIsGoods()==1){
            ArrayList<ClassifyListBean> listBeans = new ArrayList<>();
            for (int i = 0; i < shopDetailBean.getClassifyList().size(); i++) {
                ClassifyListBean bean = new ClassifyListBean();
                bean.setName(shopDetailBean.getClassifyList().get(i).getName());
                bean.setClassifyId(shopDetailBean.getClassifyList().get(i).getClassifyId());
                listBeans.add(bean);
            }

            fragments.add(DerivativesFragment.newInstance(listBeans));
            data.add("衍生品");
        }

        String[] strs = new String[data.size()];
        for(int i=0;i<strs.length;i++){
            strs[i] = data.get(i);
        }

        activityShopViewPager.setAdapter(new BaseViewpageAdapter(getSupportFragmentManager(), fragments));
        activityArtistShopTab.setViewPager(activityShopViewPager, strs);

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
