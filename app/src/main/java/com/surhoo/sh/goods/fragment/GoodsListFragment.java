package com.surhoo.sh.goods.fragment;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.common.custom.MyLoadMoreView;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.recyclerview.GridDivider;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.goods.adapter.GoodsListAdapter;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.presenter.GoodsListPresenter;
import com.surhoo.sh.goods.presenter.impl.GoodsPresenterImpl;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.goods.view.GoodsListView;

import java.util.List;


import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoodsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class GoodsListFragment extends BaseFragment implements GoodsListView {
    private static final String FROM = "from";
    private static final String CATEGORYID = "categoryId";
    private static final String SORTTYPE = "sortType";
    @BindView(R.id.fragment_goods_list_recyclerView)
    RecyclerView fragmentGoodsListRecyclerview;

    private String from;
    private int categoryId;
    private int sortType;

    GoodsListPresenter goodsPresenter;

    GoodsListAdapter goodsListAdapter;

    private int pageSize = 10;
    private int pageIndex = 1;

    //1为查看商品分类  2为查看店铺下的产品  3为场景下的商品  4为收藏下的商品
    //他们的借口有区别
    public static GoodsListFragment newInstance(String from,int categoryId, int sortType) {
        GoodsListFragment fragment = new GoodsListFragment();
        Bundle args = new Bundle();
        args.putString(FROM, from);
        args.putInt(CATEGORYID, categoryId);
        args.putInt(SORTTYPE, sortType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            from = getArguments().getString(FROM);
            categoryId = getArguments().getInt(CATEGORYID);
            sortType = getArguments().getInt(SORTTYPE);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return getLayoutInflater().inflate(R.layout.fragment_goods_list, container, false);
    }

    @Override
    public void init() {

        initLoadingView();

        goodsPresenter = new GoodsPresenterImpl();
        goodsPresenter.bindView(getActivity(),this);

        goodsListAdapter = new GoodsListAdapter(R.layout.item_goods_list,null);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        goodsListAdapter.setEmptyView(loadingView);

        fragmentGoodsListRecyclerview.setLayoutManager(gridLayoutManager);

        fragmentGoodsListRecyclerview.setAdapter(goodsListAdapter);


        fragmentGoodsListRecyclerview.addItemDecoration(new GridDivider());

        goodsListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();
            }
        },fragmentGoodsListRecyclerview);

        goodsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(ClickUtil.isFastClick()){
                    GoodsBean goodsBean = (GoodsBean) adapter.getData().get(position);
                    Intent i = new Intent(getActivity(), GoodsDetailActivity.class);
                    i.putExtra("id", goodsBean.getGoodsId());
                    ActivityUtils.startActivity(i);
                }
            }
        });

        goodsListAdapter.setLoadMoreView(new MyLoadMoreView());

    }


    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {
        super.onRecevieMessage(bean);

        switch (bean.getCode()){
            case EventBusMessageBean.Collect.cancelGoodsCollect:
                pageIndex=1;
                requestData();
                break;
        }
    }

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
    public boolean isFirstInLoadData() {
        return true;
    }


    @Override
    public void requestData() {
        if(StringUtils.equals("collect",from)){
            goodsPresenter.getCollect(pageSize,pageIndex,1);

        }else {
//            goodsPresenter.requestData(from,categoryId,pageSize,pageIndex,sortType);
        }
    }

    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;

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
