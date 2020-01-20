package com.surhoo.sh.shop.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.common.recyclerview.GridDivider;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.goods.adapter.GoodsListAdapter;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.shop.presenter.ShopFragmentPresent;
import com.surhoo.sh.shop.presenter.ShopPresenter;
import com.surhoo.sh.shop.presenter.impl.ShopFragmentPresentImpl;
import com.surhoo.sh.shop.presenter.impl.ShopPresenterImpl;
import com.surhoo.sh.shop.view.impl.ShopFragmentView;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class ShopFragment extends BaseFragment implements ShopFragmentView {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.fragment_shop_sort_normal)
    TextView fragmentShopSortNormal;
    @BindView(R.id.fragment_shop_sort_saleCount)
    TextView fragmentShopSortSaleCount;
    @BindView(R.id.fragment_shop_sort_price)
    TextView fragmentShopSortPrice;
    @BindView(R.id.fragment_shop_sort_price_img)
    ImageView fragmentShopSortPriceImg;
    @BindView(R.id.fragment_shop_sort_price_layout)
    ConstraintLayout fragmentShopSortPriceLayout;
    @BindView(R.id.fragment_shop_recyclerView)
    RecyclerView fragmentShopRecyclerView;

    private int mParam1;

    ShopFragmentPresent shopPresenter;

    GoodsListAdapter goodsListAdapter;

    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance(int classifyId) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, classifyId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void init() {

        shopPresenter = new ShopFragmentPresentImpl();

        shopPresenter.bindView(getActivity(),this);

        goodsListAdapter= new GoodsListAdapter(R.layout.item_goods_list,null);

        fragmentShopRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        fragmentShopRecyclerView.setAdapter(goodsListAdapter);

        fragmentShopRecyclerView.addItemDecoration(new GridDivider(45));

        initLoadingView();

        goodsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(ClickUtil.isFastClick()){
                    GoodsBean goodsBean = (GoodsBean) adapter.getData().get(position);
                    Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                    intent.putExtra("id",goodsBean.getGoodsId());
                    ActivityUtils.startActivity(intent);
                }
            }
        });

        goodsListAdapter.setEmptyView(loadingView);

        goodsListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();
            }
        },fragmentShopRecyclerView);
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    private int sortType = 1;
    private int pageIndex =1;
    private int pageSize =20;

    @Override
    public void requestData() {
        shopPresenter.requestData(mParam1,sortType,pageIndex,pageSize);
    }

    @OnClick({R.id.fragment_shop_sort_normal, R.id.fragment_shop_sort_saleCount, R.id.fragment_shop_sort_price_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_shop_sort_normal:
                if(sortType==1){
                    return;
                }
                pageIndex=1;
                sortType=1;
                fragmentShopSortNormal.setTextColor(getResources().getColor(R.color.themeColor));
                fragmentShopSortSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                fragmentShopSortPrice.setTextColor(getResources().getColor(R.color.saleColor));
                fragmentShopSortPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_default));
                requestData();
                break;
            case R.id.fragment_shop_sort_saleCount:
                if(sortType==2){
                    return;
                }
                pageIndex=1;
                sortType=2;
                fragmentShopSortNormal.setTextColor(getResources().getColor(R.color.saleColor));
                fragmentShopSortSaleCount.setTextColor(getResources().getColor(R.color.themeColor));
                fragmentShopSortPrice.setTextColor(getResources().getColor(R.color.saleColor));
                fragmentShopSortPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_default));
                requestData();
                break;
            case R.id.fragment_shop_sort_price_layout:
                pageIndex=1;
                if(sortType==1||sortType==2){
                    sortType=3;
                    fragmentShopSortPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_down));
                }else {
                    if(sortType==3){
                        sortType=4;
                        fragmentShopSortPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_up));
                    }else if(sortType==4){
                        sortType=3;
                        fragmentShopSortPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_down));
                    }
                }
                fragmentShopSortNormal.setTextColor(getResources().getColor(R.color.saleColor));
                fragmentShopSortSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                fragmentShopSortPrice.setTextColor(getResources().getColor(R.color.themeColor));

                requestData();
                break;
        }
    }


    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;

    private void initLoadingView() {
        loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.load_data_loading_view, null);
        loadingEmptyView = LayoutInflater.from(getActivity()).inflate(R.layout.load_data_empty_view, null);
        ImageView emptyImg = (ImageView) loadingEmptyView.findViewById(R.id.load_data_empty_view_img);
        emptyImg.setImageDrawable(getResources().getDrawable(R.mipmap.search_empty_img));
        TextView emptyText = (TextView) loadingEmptyView.findViewById(R.id.load_data_empty_view_content);
        emptyText.setText("抱歉,没有找到相关内容");

        loadingErrorView = LayoutInflater.from(getActivity()).inflate(R.layout.load_data_error_view, null);

        TextView errorText = (TextView) loadingErrorView.findViewById(R.id.load_data_error_view_retry);
        errorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    goodsListAdapter.setEmptyView(loadingView);
                    requestData();
                }
            }
        });
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void setHavePageEmptyView() {
        goodsListAdapter.setNewData(null);
        goodsListAdapter.setEmptyView(loadingEmptyView);
    }

    @Override
    public void setHavePageErrorView() {
        goodsListAdapter.setNewData(null);
        goodsListAdapter.setEmptyView(loadingErrorView);
    }

    @Override
    public void loadDataEnd() {
        goodsListAdapter.loadMoreEnd();
    }

    @Override
    public void firstLoadData(List list) {
        goodsListAdapter.setNewData(list);
        goodsListAdapter.loadMoreComplete();
    }

    @Override
    public void loadData(List list) {
        goodsListAdapter.addData(list);
        goodsListAdapter.loadMoreComplete();
    }
}
