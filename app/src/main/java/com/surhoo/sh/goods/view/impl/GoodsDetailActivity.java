package com.surhoo.sh.goods.view.impl;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.goyourfly.multi_picture.ImageLoader;
import com.goyourfly.multi_picture.MultiPictureView;
import com.surhoo.sh.ImagePagerActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.UserUtil;
import com.surhoo.sh.common.dialog.MyDialogFragment;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.CommonUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.common.util.NetworkImageHolderView;
import com.surhoo.sh.common.util.ShareUtil;
import com.surhoo.sh.goods.adapter.CommentsAdapter;
import com.surhoo.sh.goods.adapter.GoodsSpecPopAdapter;
import com.surhoo.sh.goods.bean.GoodDetailBean;
import com.surhoo.sh.goods.bean.RequestAddToCarBean;
import com.surhoo.sh.goods.presenter.impl.GoodsDetailPresenterImpl;
import com.surhoo.sh.goods.view.GoodsDetailView;
import com.surhoo.sh.login.view.LoginActivity;
import com.surhoo.sh.order.OrderConfirmationActivity;
import com.surhoo.sh.shoppingcart.ShoppingCartActivity;
import com.surhoo.sh.shoppingcart.ShoppingCartBean;
import com.zyyoona7.popup.EasyPopup;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.samlss.broccoli.Broccoli;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

@Route(path = "product/productDetails")
public class GoodsDetailActivity extends BaseActivity implements GoodsDetailView, OnDDD {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.toolbar_layout_text)
    TextView toolbarLayoutText;
    @BindView(R.id.activity_goods_detail_goodsname)
    TextView activityGoodsDetailGoodsname;
    @BindView(R.id.activity_goods_detail_price)
    TextView activityGoodsDetailPrice;
    @BindView(R.id.activity_goods_detail_old_price)
    TextView activityGoodsDetailOldPrice;
    @BindView(R.id.activity_goods_detail_salescount)
    TextView activityGoodsDetailSalescount;
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

    GoodsDetailPresenterImpl goodsDetailPresenter;
    CommentsAdapter commentsAdapter;

    Broccoli broccoli;
    @BindView(R.id.activity_goods_detail_convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.activity_goods_detail_banner_page)
    TextView activityGoodsDetailBannerPage;
    @BindView(R.id.activity_goods_detail_nestedScrollView)
    NestedScrollView activityGoodsDetailNestedScrollView;
    @BindView(R.id.activity_goods_detail_totop)
    ImageButton activityGoodsDetailTotop;
    @BindView(R.id.activity_goods_detail_spec_img)
    ImageView activityGoodsDetailSpecImg;
    @BindView(R.id.activity_goods_detail_spec)
    TextView activityGoodsDetailSpec;
    @BindView(R.id.activity_goods_detail_spec_layout)
    ConstraintLayout activityGoodsDetailSpecLayout;
    @BindView(R.id.activity_goods_detail_server)
    ConstraintLayout activityGoodsDetailShare;
    @BindView(R.id.activity_goods_detail_shoppingCart)
    ConstraintLayout activityGoodsDetailShoppingCart;
    @BindView(R.id.activity_goods_detail_collect)
    ConstraintLayout activityGoodsDetailCollect;
    @BindView(R.id.activity_goods_detail_add_to_car)
    TextView activityGoodsDetailAddToCar;
    @BindView(R.id.activity_goods_detail_buy_rightNow)
    TextView activityGoodsDetailBuyRightNow;
    @BindView(R.id.activity_goods_detail_collect_img)
    ImageView activityGoodsDetailCollectImg;
    @BindView(R.id.activity_goods_detail_collect_text)
    TextView activityGoodsDetailCollectText;

    //商品ID
    private Integer id;

    @Override
    public int getContentView() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        MultiPictureView.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(@NotNull ImageView imageView, @NotNull Uri uri) {
                //使用glideutil会报错
                Glide.with(GoodsDetailActivity.this).load(uri).into(imageView);
            }
        });

        toolbarLayoutTitle.setText("商品详情");
        toolbarLayoutText.setVisibility(View.VISIBLE);
        toolbarLayoutText.setText("分享");


        commentsAdapter = new CommentsAdapter(R.layout.item_goods_comments, new ArrayList<>());

        goodsDetailPresenter = new GoodsDetailPresenterImpl();
        goodsDetailPresenter.bindView(this, this);

        broccoli = new Broccoli();

        broccoli.addPlaceholders(this, R.id.activity_goods_detail_goodsname,
                R.id.activity_goods_detail_price, R.id.activity_goods_detail_old_price
                , R.id.activity_goods_detail_spec_layout, R.id.activity_goods_detail_allcomments,
                R.id.activity_goods_detail_shop_enter,  R.id.textView12, R.id.activity_goods_detail_comments
                , R.id.textView14, R.id.activity_goods_detail_comments_recyclerView,
                R.id.activity_goods_detail_spec, R.id.view3, R.id.textView16, R.id.activity_goods_detail_shop_img,
                R.id.activity_goods_detail_shop_name, R.id.imageView7, R.id.textView18, R.id.activity_goods_detail_shop_hot,
                R.id.activity_goods_detail_shop_enter,  R.id.textView21, R.id.activity_goods_detail_webview, R.id.textView10);
        broccoli.show();

        shoppingCartNumber = new QBadgeView(this).bindTarget(activityGoodsDetailShoppingCart)
                .setBadgeGravity(Gravity.END | Gravity.TOP).setBadgeTextSize(10, true).setExactMode(false);


        activityGoodsDetailNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 1000) {
                    activityGoodsDetailTotop.setVisibility(View.VISIBLE);
                } else {
                    activityGoodsDetailTotop.setVisibility(View.GONE);
                }
            }
        });
    }

    private Badge shoppingCartNumber;

    @Override
    public void initData() {

        Uri data = getIntent().getData();
        //不为空说明是外部网页传过来的
        if (!ObjectUtils.isEmpty(data)) {
            id = Integer.valueOf(data.getQueryParameter("productDetailsId"));
        } else id = getIntent().getIntExtra("id",0);

    }

    @Override
    protected void onResume() {
        super.onResume();

        goodsDetailPresenter.getShoopingCartNumber(GETCARNUMBER);
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

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    private GoodsSpecPopAdapter goodsSpecPopAdapter;

    private EasyPopup goodsSpecPop;

    private ImageView popImg;

    private TextView skuPrice;

    private TextView inventory;


    private Button addToCar = null;


    public void showSpec(int buyType) {

        if (ObjectUtils.isEmpty(goodDetailBean)) {
            return;
        }


        if (ObjectUtils.isEmpty(goodsSpecPop)) {
            goodsSpecPop = EasyPopup.create()
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

            RecyclerView recyclerView = (RecyclerView) goodsSpecPop.getContentView().findViewById(R.id.pop_goods_spec_recyclerview);

            goodsSpecPopAdapter = new GoodsSpecPopAdapter(R.layout.item_goods_spec, goodDetailBean.getSpecList());

            goodsSpecPopAdapter.setSkuListBeans(goodDetailBean.getSkuList());

            for (int i = 0; i < goodDetailBean.getSpecList().size(); i++) {
                specList.add(goodDetailBean.getSpecList().get(i).getGoodsSkuSpecVals().get(0).getGoodsSkuSpecValName());
            }

            goodsSpecPopAdapter.setOnDDD(this);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            recyclerView.setAdapter(goodsSpecPopAdapter);

            popImg = (ImageView) goodsSpecPop.getContentView().findViewById(R.id.pop_goods_spec_img);

            checkSkuImg = goodDetailBean.getSkuList().get(0).getGoodsSkuImg();

            popImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> photoUrls = new ArrayList<>();
                    photoUrls.add(checkSkuImg);
                    ImagePagerActivity.startImagePagerActivity(GoodsDetailActivity.this, photoUrls, 0, null);
                }
            });

            GlideUtil.loadDefaultImg(this, checkSkuImg, popImg);

            ImageView close = (ImageView) goodsSpecPop.getContentView().findViewById(R.id.pop_goods_spec_close);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ClickUtil.isFastClick()) {
                        goodsSpecPop.dismiss();
                    }
                }
            });

            skuPrice = (TextView) goodsSpecPop.getContentView().findViewById(R.id.pop_goods_spec_price);
            skuPrice.setText(goodDetailBean.getGoodsPrice());


            ConstraintLayout addNUm = (ConstraintLayout) goodsSpecPop.getContentView().findViewById(R.id.pop_goods_spec_addNum);
            ConstraintLayout reduceNum = (ConstraintLayout) goodsSpecPop.getContentView().findViewById(R.id.pop_goods_spec_reduceNum);
            TextView num = (TextView) goodsSpecPop.getContentView().findViewById(R.id.pop_goods_spec_num);
            inventory = (TextView) goodsSpecPop.getContentView().findViewById(R.id.pop_goods_spec_inventory);
            inventory.setText(goodDetailBean.getSkuList().get(0).getGoodsSkuStock() + "");

            addNUm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.setText(Integer.valueOf(num.getText().toString()) + 1 + "");
                }
            });

            reduceNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.valueOf(num.getText().toString()) <= 1) {
                        ToastUtils.showShort("最少购买一件哦!");
                        return;
                    }
                    num.setText(Integer.valueOf(num.getText().toString()) - 1 + "");
                }
            });


            addToCar = (Button) goodsSpecPop.getContentView().findViewById(R.id.pop_goods_spec_addtocar);
            Button BuyRightNow = (Button) goodsSpecPop.getContentView().findViewById(R.id.pop_goods_spec_buy_rightNow);


            addToCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ClickUtil.isFastClick()) {
                        if (UserUtil.isLogin()) {
                            RequestAddToCarBean bean = new RequestAddToCarBean();
                            bean.setGoodsId(id);
                            bean.setGoodsNum(Integer.parseInt(num.getText().toString()));
                            bean.setSkuId(skuId);
                            goodsDetailPresenter.addToCar(ADDTOCAR, bean);
                        } else {
                            ActivityUtils.startActivity(LoginActivity.class);
                        }
                    }
                }
            });
            BuyRightNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ClickUtil.isFastClick()) {
                        if (UserUtil.isLogin()) {
                            ArrayList<ShoppingCartBean.CarGoodsListBean> buyGoods = new ArrayList<>();
                            ShoppingCartBean.CarGoodsListBean bean = new ShoppingCartBean.CarGoodsListBean();
                            bean.setGoodsId(id);
                            bean.setGoodsNum(Integer.parseInt(num.getText().toString()));
                            bean.setSkuId(skuId);
                            buyGoods.add(bean);
                            Intent intent = new Intent(GoodsDetailActivity.this, OrderConfirmationActivity.class);
                            intent.putExtra("data", buyGoods);
                            ActivityUtils.startActivity(intent);
                        } else {
                            ActivityUtils.startActivity(LoginActivity.class);
                        }
                    }
                }
            });
        }

        if (!ObjectUtils.isEmpty(addToCar)) {
            if (buyType == 1) {
                addToCar.setVisibility(View.VISIBLE);
            } else {
                addToCar.setVisibility(View.GONE);
            }
        }

        goodsSpecPop.showAtLocation(toolbarLayoutTitle, Gravity.BOTTOM, 0, 0);

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
                ImagePagerActivity.startImagePagerActivity(GoodsDetailActivity.this, bannerData, position, null);
            }
        });

        convenientBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                activityGoodsDetailBannerPage.setText(i1 + "/" + bannerData.size());
            }

            @Override
            public void onPageSelected(int i) {
                activityGoodsDetailBannerPage.setText(++i + "/" + bannerData.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                activityGoodsDetailBannerPage.setText(i + "/" + bannerData.size());
            }
        });
    }


    private GoodDetailBean goodDetailBean;

    private boolean isCollect;

    @Override
    public void showBeanData(GoodDetailBean goodDetailBean) {
        if (ObjectUtils.isEmpty(goodDetailBean)) {
            return;
        }

        if (goodDetailBean.isIsCollect()) {
            activityGoodsDetailCollectImg.setImageDrawable(getResources().getDrawable(R.mipmap.collect));
            activityGoodsDetailCollectText.setText("已收藏");
            isCollect = true;
        }

        skuId = goodDetailBean.getSkuList().get(0).getId();
        GlideUtil.loadCircleImage(this, goodDetailBean.getSkuList().get(0).getGoodsSkuImg(), activityGoodsDetailSpecImg);
        activityGoodsDetailSpec.setText(goodDetailBean.getSkuList().get(0).getGoodsSkuName());

        this.goodDetailBean = goodDetailBean;

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
        String[] split = goodDetailBean.getBanner().split(",");

        for (int i = 0; i < split.length; i++) {

            bannerData.add(split[i]);

        }
        setBannerData(bannerData);
    }

    private Integer skuId;

    private ArrayList<String> specList = new ArrayList<>();

    private String checkSkuImg;

    @Override
    public void onddd(int position, String name) {
        StringBuffer stringBuffer = new StringBuffer();

        specList.set(position, name);

        for (int i = 0; i < specList.size(); i++) {

            if (i == goodsSpecPopAdapter.getData().size() - 1) {
                stringBuffer.append(specList.get(i));
            } else {
                stringBuffer.append(specList.get(i) + "_");
            }
        }

        GoodDetailBean.SkuListBean sku = CommonUtil.getSku(goodDetailBean.getSkuList(), stringBuffer.toString().split("_"));
        skuId = sku.getId();

        activityGoodsDetailSpec.setText(sku.getGoodsSkuName());

        checkSkuImg = sku.getGoodsSkuImg();

        GlideUtil.loadDefaultImg(this, sku.getGoodsSkuImg(), popImg);
        skuPrice.setText(sku.getGoodsSkuRetailPrice());
        inventory.setText(sku.getGoodsSkuStock() + "");

        GlideUtil.loadCircleImage(this, sku.getGoodsSkuImg(), activityGoodsDetailSpecImg);

    }


    @OnClick({R.id.toolbar_layout_back, R.id.activity_goods_detail_spec_layout,R.id.toolbar_layout_text,
            R.id.activity_goods_detail_allcomments, R.id.activity_goods_detail_shop_enter,
            R.id.activity_goods_detail_server, R.id.activity_goods_detail_shoppingCart,
            R.id.activity_goods_detail_collect, R.id.activity_goods_detail_add_to_car,
            R.id.activity_goods_detail_buy_rightNow, R.id.activity_goods_detail_totop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;

            case R.id.toolbar_layout_text:

                if(ObjectUtils.isEmpty(goodDetailBean)){
                    return;
                }

                ShareUtil.shareGoods(this,goodDetailBean.getGoodsName(),goodDetailBean.getGoodsDetail(),goodDetailBean.getImg(),id);

                break;
            case R.id.activity_goods_detail_spec_layout:
                showSpec(1);
                break;
            case R.id.activity_goods_detail_add_to_car:
                if (UserUtil.isLogin()) {
                    showSpec(1);
                } else {
                    ActivityUtils.startActivity(this, LoginActivity.class);
                }

                break;
            case R.id.activity_goods_detail_allcomments:
                Intent allCommentIntent = new Intent(this, AllCommentsActivity.class);
                allCommentIntent.putExtra("id", id);
                ActivityUtils.startActivity(allCommentIntent);
                break;
            case R.id.activity_goods_detail_shop_enter:
                break;
            case R.id.activity_goods_detail_server:
                ToastUtils.showShort("暂不支持联系客服!");
                break;
            case R.id.activity_goods_detail_shoppingCart:
                if (UserUtil.isLogin()) {
                    ActivityUtils.startActivity(this, ShoppingCartActivity.class);
                } else {
                    ActivityUtils.startActivity(this, LoginActivity.class);
                }
                break;
            case R.id.activity_goods_detail_collect:
                if (UserUtil.isLogin()) {
                    if (isCollect) {
                        MyDialogFragment myDialogFragment =  MyDialogFragment.newInstance("您确定要取消收藏吗？");
                        myDialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                            @Override
                            public void onConfirmClick() {
                                myDialogFragment.dismiss();
                                goodsDetailPresenter.cancelCollect(CANCELCOLLECT, 1, id);
                            }
                        });

                        myDialogFragment.setOnCancelClickListener(new MyDialogFragment.onCancelClickListener() {
                            @Override
                            public void onCancelClick() {
                                myDialogFragment.dismiss();
                            }
                        });

                        myDialogFragment.show(getSupportFragmentManager(),"cancelCollect");
                    } else {
                        goodsDetailPresenter.addCollect(ADDCOLLECT, 1, id);
                    }
                } else {
                    ActivityUtils.startActivity(LoginActivity.class);
                }
                break;
            case R.id.activity_goods_detail_buy_rightNow:
                if (UserUtil.isLogin()) {
                    showSpec(2);
                } else {
                    ActivityUtils.startActivity(this, LoginActivity.class);
                }
                break;
            case R.id.activity_goods_detail_totop:
                activityGoodsDetailNestedScrollView.smoothScrollTo(0, 0);
                break;
        }
    }

    private static final String ADDTOCAR = "addToCar";
    private static final String GETCARNUMBER = "getCarNumber";
    private static final String ADDCOLLECT = "addCollect";
    private static final String CANCELCOLLECT = "cancelCollect";

    @Override
    public void showStringData(String requestTag, String s) {
        if (StringUtils.equals(ADDTOCAR, requestTag)) {
            if (StringUtils.equals("1", s)) {
                goodsSpecPop.dismiss();
                shoppingCartNumber.setBadgeNumber(shoppingCartNumber.getBadgeNumber() + 1);
                ToastUtils.showShort("加入购物车成功，我在购物车等你哦！");
            } else {
                ToastUtils.showShort("添加失败");
            }
        }

        if (StringUtils.equals(GETCARNUMBER, requestTag)) {
            shoppingCartNumber.setBadgeNumber(Integer.parseInt(s));
        }

        if (StringUtils.equals(ADDCOLLECT, requestTag)) {
            isCollect = true;
            activityGoodsDetailCollectImg.setImageDrawable(getResources().getDrawable(R.mipmap.collect));
            activityGoodsDetailCollectText.setText("已收藏");
        }

        if (StringUtils.equals(CANCELCOLLECT, requestTag)) {
            isCollect = false;
            activityGoodsDetailCollectImg.setImageDrawable(getResources().getDrawable(R.mipmap.un_collect));
            activityGoodsDetailCollectText.setText("收藏");
            EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Collect.cancelGoodsCollect));
        }
    }
}
