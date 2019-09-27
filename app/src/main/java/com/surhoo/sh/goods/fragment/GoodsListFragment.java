package com.surhoo.sh.goods.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.PagerBaseView;
import com.surhoo.sh.goods.adapter.GoodsListAdapter;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.di.DaggerGoodsComponent;
import com.surhoo.sh.goods.presenter.GoodsPresenter;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.goods.view.GoodsView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoodsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoodsListFragment extends Fragment implements GoodsView {
    private static final String FROM = "from";
    private static final String CATEGORYID = "categoryId";
    private static final String SORTTYPE = "sortType";
    @BindView(R.id.fragment_goods_list_recyclerView)
    RecyclerView fragmentGoodsListRecyclerview;
    Unbinder unbinder;


    //1为查看商品分类  2为查看店铺下的产品  3为场景下的商品
    private int from;
    private int categoryId;
    private int sortType;

    @Inject
    GoodsPresenter goodsPresenter;

    @Inject
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goods_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerGoodsComponent.create().inject(this);

        datas = goodsListAdapter.getData();

        fragmentGoodsListRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));

        fragmentGoodsListRecyclerview.setAdapter(goodsListAdapter);


        goodsPresenter.bindView(getContext(),this);
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

    private void requestData() {
        goodsPresenter.requestData(from,categoryId,pageSize,pageIndex,sortType);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
