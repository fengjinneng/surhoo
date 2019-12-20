package com.surhoo.sh.goods.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.recyclerview.GridDivider;
import com.surhoo.sh.goods.adapter.GoodsListAdapter;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.presenter.GoodsListPresenter;
import com.surhoo.sh.goods.presenter.impl.GoodsPresenterImpl;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class GoodsListActivity extends BaseActivity implements GoodsListView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_goods_list_recyclerView)
    RecyclerView activityGoodsListRecyclerView;
    @BindView(R.id.activity_goods_list_comprehensive)
    TextView activityGoodsListComprehensive;
    @BindView(R.id.activity_goods_list_saleCount)
    TextView activityGoodsListSaleCount;
    @BindView(R.id.activity_goods_list_price)
    TextView activityGoodsListPrice;
    @BindView(R.id.activity_goods_list_price_img)
    ImageView activityGoodsListPriceImg;
    @BindView(R.id.activity_goods_list_price_layout)
    ConstraintLayout activityGoodsListPriceLayout;

    private int id;

    private GoodsListPresenter presenter;
    private GoodsListAdapter goodsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_goods_list;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    private int from;
    private String searchName;

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", 0);
        toolbarLayoutTitle.setText("商品列表");

        from = getIntent().getIntExtra("from",0);

        searchName = getIntent().getStringExtra("searchName");

    }

    @Override
    public void initData() {

        presenter =new GoodsPresenterImpl();
        presenter.bindView(this,this);

        goodsListAdapter = new GoodsListAdapter(R.layout.item_goods_list,null);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        activityGoodsListRecyclerView.setLayoutManager(gridLayoutManager);

        activityGoodsListRecyclerView.addItemDecoration(new GridDivider());

        activityGoodsListRecyclerView.setAdapter(goodsListAdapter);

        goodsListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                presenter.requestData(from,id,pageSize,pageIndex,sortType,searchName);
            }
        },activityGoodsListRecyclerView);


        goodsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsBean goodsBean = (GoodsBean) adapter.getData().get(position);
                Intent i = new Intent(GoodsListActivity.this, GoodsDetailActivity.class);
                i.putExtra("id",goodsBean.getGoodsId());
                ActivityUtils.startActivity(i);
            }
        });

    }

    private int sortType = 1;

    @Override
    public void requestData() {
        presenter.requestData(from,id,pageSize,pageIndex,sortType,searchName);
    }

    private int pageSize = 20;

    private int pageIndex =1;

    private int checkPosition = 1;


    //  1为从高到低 2从低到高
    private int priceOrder =1;

    @OnClick({R.id.toolbar_layout_back,R.id.activity_goods_list_comprehensive, R.id.activity_goods_list_saleCount, R.id.activity_goods_list_price_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;
            case R.id.activity_goods_list_comprehensive:
                if(checkPosition!=1){
                    checkPosition = 1;
                    activityGoodsListComprehensive.setTextColor(getResources().getColor(R.color.themeColor));
                    activityGoodsListSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                    activityGoodsListPrice.setTextColor(getResources().getColor(R.color.saleColor));
                    activityGoodsListPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_default));
                    pageIndex=1;
                    sortType =1;
                    priceOrder =1;
                    presenter.requestData(from,id,pageSize,pageIndex,sortType,searchName);
                }
                break;
            case R.id.activity_goods_list_saleCount:
                if(checkPosition!=2){
                    checkPosition = 2;
                    activityGoodsListComprehensive.setTextColor(getResources().getColor(R.color.saleColor));
                    activityGoodsListSaleCount.setTextColor(getResources().getColor(R.color.themeColor));
                    activityGoodsListPrice.setTextColor(getResources().getColor(R.color.saleColor));
                    activityGoodsListPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_default));
                    pageIndex=1;
                    sortType =2;
                    priceOrder =1;
                    presenter.requestData(from,id,pageSize,pageIndex,sortType,searchName);
                }
                break;
            case R.id.activity_goods_list_price_layout:
                pageIndex=1;
                checkPosition =3;
                activityGoodsListComprehensive.setTextColor(getResources().getColor(R.color.saleColor));
                activityGoodsListSaleCount.setTextColor(getResources().getColor(R.color.saleColor));
                activityGoodsListPrice.setTextColor(getResources().getColor(R.color.themeColor));
                if(priceOrder==1){
                    priceOrder=2;
                    sortType = 3;
                    activityGoodsListPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_down));
                }else {
                    priceOrder=1;
                    sortType = 4;
                    activityGoodsListPriceImg.setImageDrawable(getResources().getDrawable(R.mipmap.good_order_up));
                }
                presenter.requestData(from,id,pageSize,pageIndex,sortType,searchName);
                break;
        }
    }

    @Override
    public void firstInEmpty() {
        goodsListAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.search_empty_view,null));

    }

    @Override
    public void loadEnd() {
        goodsListAdapter.loadMoreEnd();
    }

    @Override
    public void refresh(List list) {
        goodsListAdapter.setNewData(list);
        goodsListAdapter.loadMoreComplete();
    }

    @Override
    public void loadData(List list) {
        goodsListAdapter.addData(list);
        goodsListAdapter.loadMoreComplete();
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
