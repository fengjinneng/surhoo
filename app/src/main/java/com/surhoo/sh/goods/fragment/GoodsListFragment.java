package com.surhoo.sh.goods.fragment;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.common.recyclerview.GridDivider;
import com.surhoo.sh.goods.adapter.GoodsListAdapter;
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


    //1为查看商品分类  2为查看店铺下的产品  3为场景下的商品
    private int from;
    private int categoryId;
    private int sortType;

    GoodsListPresenter goodsPresenter;

    GoodsListAdapter goodsListAdapter;

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

        goodsListAdapter = new GoodsListAdapter(R.layout.item_goods_list,null);
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);


        fragmentGoodsListRecyclerview.setLayoutManager(gridLayoutManager);

        fragmentGoodsListRecyclerview.setAdapter(goodsListAdapter);


        fragmentGoodsListRecyclerview.addItemDecoration(new GridDivider());

        goodsPresenter.bindView(getActivity(),this);
        requestData();


        goodsListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
//                goodsPresenter.requestData(from,categoryId,pageSize,pageIndex,sortType);
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
//        goodsPresenter.requestData(from,categoryId,pageSize,pageIndex,sortType);
    }



    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void firstInEmpty() {
        goodsListAdapter.notifyDataSetChanged();
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

}
