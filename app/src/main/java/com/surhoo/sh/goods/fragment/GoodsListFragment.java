package com.surhoo.sh.goods.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.goods.adapter.GoodsListAdapter;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.presenter.GoodsPresenter;
import com.surhoo.sh.goods.presenter.impl.GoodsPresenterImpl;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.goods.view.GoodsView;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoodsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoodsListFragment extends BaseFragment implements GoodsView {
    private static final String FROM = "from";
    private static final String CATEGORYID = "categoryId";
    private static final String SORTTYPE = "sortType";
    @BindView(R.id.fragment_goods_list_recyclerView)
    RecyclerView fragmentGoodsListRecyclerview;


    //1为查看商品分类  2为查看店铺下的产品  3为场景下的商品
    private int from;
    private int categoryId;
    private int sortType;

    GoodsPresenter goodsPresenter;

    GoodsListAdapter goodsListAdapter;

    List<GoodsBean> datas;
    private int pageSize = 20;
    private int pageIndex = 1;



    //1为查看商品分类  2为查看店铺下的产品  3为场景下的商品  4为收藏下的商品
    //他们的借口有区别
    public static GoodsListFragment newInstance(int from,int categoryId, int sortType) {
        GoodsListFragment fragment = new GoodsListFragment();
        Bundle args = new Bundle();
        args.putInt(FROM, from);
        args.putInt(CATEGORYID, categoryId);
        args.putInt(SORTTYPE, sortType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            from = getArguments().getInt(FROM);
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
        goodsPresenter = new GoodsPresenterImpl();
        goodsPresenter.bindView(getActivity(),this);

        goodsListAdapter = new GoodsListAdapter(R.layout.item_goods_list,new ArrayList<>());
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        datas = goodsListAdapter.getData();

        fragmentGoodsListRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));

        fragmentGoodsListRecyclerview.setAdapter(goodsListAdapter);


        goodsPresenter.bindView(getActivity(),this);
        requestData();


        goodsListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        },fragmentGoodsListRecyclerview);

        goodsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityUtils.startActivity(GoodsDetailActivity.class);
            }
        });
    }

    @Override
    public void requestData() {
        goodsPresenter.requestData(from,categoryId,pageSize,pageIndex,sortType);
    }



    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void firstInEmpty() {
        datas.clear();
        goodsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadEnd() {
        goodsListAdapter.loadMoreEnd();
    }

    @Override
    public void refresh(List list) {
        datas.addAll(list);
        goodsListAdapter.setNewData(list);
        goodsListAdapter.loadMoreComplete();
    }

    @Override
    public void loadData(List list) {
        datas.addAll(list);
        goodsListAdapter.setNewData(list);
        goodsListAdapter.loadMoreComplete();
    }

}
