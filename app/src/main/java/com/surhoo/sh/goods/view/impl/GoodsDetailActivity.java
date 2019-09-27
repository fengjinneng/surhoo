package com.surhoo.sh.goods.view.impl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.NetworkImageHolderView;
import com.surhoo.sh.goods.adapter.CommentsAdapter;
import com.surhoo.sh.goods.adapter.GoodsSpecPopAdapter;
import com.surhoo.sh.goods.bean.GoodDetailBean;
import com.surhoo.sh.goods.di.DaggerGoodsDetailComponent;
import com.surhoo.sh.goods.presenter.GoodsDetailPresenter;
import com.surhoo.sh.goods.view.GoodsDetailView;
import com.zyyoona7.popup.EasyPopup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.samlss.broccoli.Broccoli;

public class GoodsDetailActivity extends BaseActivity implements GoodsDetailView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_goods_detail_goodsname)
    TextView activityGoodsDetailGoodsname;
    @BindView(R.id.activity_goods_detail_price)
    TextView activityGoodsDetailPrice;
    @BindView(R.id.activity_goods_detail_old_price)
    TextView activityGoodsDetailOldPrice;
    @BindView(R.id.activity_goods_detail_salescount)
    TextView activityGoodsDetailSalescount;
    @BindView(R.id.activity_goods_detail_choicespec)
    TextView activityGoodsDetailChoicespec;
    @BindView(R.id.activity_goods_detail_allcomments)
    TextView activityGoodsDetailAllcomments;
    @BindView(R.id.activity_goods_detail_comments_recyclerView)
    RecyclerView activityGoodsDetailCommentsRecyclerView;
    @BindView(R.id.activity_goods_detail_shop_img)
    ImageView activityGoodsDetailShopImg;
    @BindView(R.id.activity_goods_detail_shop_name)
    TextView activityGoodsDetailShopName;
    @BindView(R.id.activity_goods_detail_shop_hot)
    TextView activityGoodsDetailShopHot;
    @BindView(R.id.activity_goods_detail_shop_enter)
    TextView activityGoodsDetailShopEnter;
    @BindView(R.id.activity_goods_detail_webview)
    WebView activityGoodsDetailWebview;

    @BindView(R.id.activity_goods_detail_comments)
    TextView activityGoodsDetailComments;


    @Inject
    GoodsDetailPresenter goodsDetailPresenter;

    @Inject
    CommentsAdapter commentsAdapter;

    Broccoli broccoli;
    @BindView(R.id.activity_goods_detail_convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.activity_goods_detail_banner_page)
    TextView activityGoodsDetailBannerPage;
    @BindView(R.id.activity_goods_detail_nestedScrollView)
    NestedScrollView activityGoodsDetailNestedScrollView;
    @BindView(R.id.activity_goods_detail_servicer)
    ImageButton activityGoodsDetailServicer;
    @BindView(R.id.activity_goods_detail_totop)
    ImageButton activityGoodsDetailTotop;

    //商品ID
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        DaggerGoodsDetailComponent.create().inject(this);

        id = getIntent().getIntExtra("id",0);
        initView();

        goodsDetailPresenter.bindView(this,this);
        requestData();

        broccoli = new Broccoli();

        broccoli.addPlaceholders(this, R.id.activity_goods_detail_goodsname,
                R.id.activity_goods_detail_price, R.id.activity_goods_detail_old_price
                , R.id.activity_goods_detail_choicespec, R.id.activity_goods_detail_allcomments,
                R.id.activity_goods_detail_shop_enter, R.id.imageView5, R.id.view2, R.id.textView12, R.id.activity_goods_detail_comments
                , R.id.textView14, R.id.activity_goods_detail_comments_recyclerView, R.id.view3, R.id.textView16, R.id.activity_goods_detail_shop_img,
                R.id.activity_goods_detail_shop_name, R.id.imageView7, R.id.textView18, R.id.activity_goods_detail_shop_hot,
                R.id.activity_goods_detail_shop_enter, R.id.view4, R.id.textView21, R.id.activity_goods_detail_webview, R.id.textView10);
        broccoli.show();

    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("商品详情");

    }


    //    自动滑动到底部:

//    NestedScrollView.fullScroll(NestedScrollView.FOCUS_DOWN)
//    NestedScrollView.fullScroll(NestedScrollView.FOCUS_UP)
//    scrollTo() //跳转到指定位置，无滑动效果
//    scrollBy() //从当前位置再偏移到指定位置，无滑动效果
//    smoothScrollTo() //与scrollTo效果相同,但添加了滑动动画效果
//    smoothScrollBy() //同理
//    滑动到指定view的上方:
//    NestedScrollView.scrollTo(0,view.getTop())


    @Override
    public void requestData() {
        goodsDetailPresenter.requestData(id);
    }

    @OnClick({R.id.toolbar_layout_back, R.id.activity_goods_detail_choicespec, R.id.activity_goods_detail_allcomments, R.id.activity_goods_detail_shop_enter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;
            case R.id.activity_goods_detail_choicespec:
                goodsDetailPresenter.showSpec();
                break;
            case R.id.activity_goods_detail_allcomments:
                Intent allCommentIntent = new Intent(this,AllCommentsActivity.class);
                allCommentIntent.putExtra("id",1);
                ActivityUtils.startActivity(allCommentIntent);
                break;
            case R.id.activity_goods_detail_shop_enter:
                break;
        }
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void show(GoodDetailBean goodDetailBean) {
        if (ObjectUtils.isEmpty(goodDetailBean)) {
            return;
        }

        broccoli.clearAllPlaceholders();

        activityGoodsDetailGoodsname.setText(goodDetailBean.getGoodsName());
        activityGoodsDetailPrice.setText("¥" + goodDetailBean.getGoodsPrice());
        activityGoodsDetailOldPrice.setText("¥" + goodDetailBean.getGoodsMarketPrice());

        activityGoodsDetailSalescount.setText(String.valueOf(goodDetailBean.getSaleCount()));
        activityGoodsDetailComments.setText(String.valueOf(goodDetailBean.getEvaluateCount()));

        if (goodDetailBean.getEvaluateCount() > 0) {
            activityGoodsDetailCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            activityGoodsDetailCommentsRecyclerView.setAdapter(commentsAdapter);
            commentsAdapter.setNewData(goodDetailBean.getEvaluateList());
        }

        activityGoodsDetailWebview.loadData(goodDetailBean.getGoodsDetail(), "text/html;charset=UTF-8", null);


        List<String> bannerData = new ArrayList<>();
        bannerData.add(goodDetailBean.getBanner());
        setBannerData(bannerData);


    }


    GoodsSpecPopAdapter goodsSpecPopAdapter;

    @Override
    public void showSpec(GoodDetailBean goodDetailBean) {
        EasyPopup mCirclePop;
        mCirclePop = EasyPopup.create()
                .setContentView(this, R.layout.pop_goods_spec)
//                .setAnimationStyle(R.style.RightPopAnim)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)
//                变暗的背景颜色
                .setDimColor(Color.BLACK)
                //指定任意 ViewGroup 背景变暗
//                .setDimView(viewGroup)

                .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)

                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .apply();

        RecyclerView recyclerView = (RecyclerView) mCirclePop.getContentView().findViewById(R.id.pop_goods_spec_recyclerview);

        goodsSpecPopAdapter  =new GoodsSpecPopAdapter(R.layout.item_goods_spec,goodDetailBean.getSpecList());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(goodsSpecPopAdapter);


        mCirclePop.showAtLocation(toolbarLayoutTitle, Gravity.BOTTOM, 0, 0);
    }

    private void setBannerData(List<String> bannerData) {


        activityGoodsDetailBannerPage.setText("1/" + bannerData.size());

        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, bannerData);

//        convenientBanner.setPageIndicator(new int[]{R.mipmap.banner_unchoiced, R.mipmap.banner_choiced});
//        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置如果只有一组数据时不能滑动
//        convenientBanner.setPointViewVisible(bannerData.size() == 1 ? false : true); // 指示器
        convenientBanner.setManualPageable(bannerData.size() == 1 ? false : true);//设置false,手动影响（设置了该项无法手动切换）
        convenientBanner.setCanLoop(bannerData.size() == 1 ? false : true); // 是否循环

        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        convenientBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                activityGoodsDetailBannerPage.setText((i + 1) + "/" + bannerData.size());
            }

            @Override
            public void onPageSelected(int i) {
                activityGoodsDetailBannerPage.setText((i + 1) + "/" + bannerData.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                activityGoodsDetailBannerPage.setText((i + 1) + "/" + bannerData.size());
            }
        });
    }

}
