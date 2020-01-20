package com.surhoo.sh.scenario.fragment;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.common.recyclerview.GridDivider;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.goods.adapter.GoodsListAdapter;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.scenario.presenter.ScenarioGoodsPresent;
import com.surhoo.sh.scenario.view.IScenarioGoodsView;
import com.surhoo.sh.scenario.view.ScenarioActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScenarioGoodsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScenarioGoodsFragment extends BaseFragment implements IScenarioGoodsView ,ScenarioActivity.onGoodsSortTypeClickListener {
    private static final String SCENARIOID = "scenarioId";
    @BindView(R.id.fragment_scenario_goods_recyclerview)
    RecyclerView fragmentScenarioGoodsRecyclerview;
    GoodsListAdapter adapter;
    List<GoodsBean> datas;

    private Integer scenarioId;
    private ScenarioGoodsPresent scenarioGoodsPresent;
    private Integer pageIndex = 1;

    //排序方式 1综合 2销量 3价格倒序 4 正序
    private Integer sortType = 1;

    public ScenarioGoodsFragment() {
    }

    public static ScenarioGoodsFragment newInstance(Integer scenarioId) {
        ScenarioGoodsFragment fragment = new ScenarioGoodsFragment();
        Bundle args = new Bundle();
        args.putInt(SCENARIOID, scenarioId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            scenarioId = getArguments().getInt(SCENARIOID);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return getLayoutInflater().inflate(R.layout.fragment_scenario_goods, container, false);
    }

    @Override
    public void init() {
        scenarioGoodsPresent = new ScenarioGoodsPresent();
        scenarioGoodsPresent.bindView(getActivity(), this);
        datas = new ArrayList<>();
        adapter= new GoodsListAdapter(R.layout.item_goods_list,datas);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        fragmentScenarioGoodsRecyclerview.setLayoutManager(gridLayoutManager);

        fragmentScenarioGoodsRecyclerview.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();
            }
        },fragmentScenarioGoodsRecyclerview);

        ((ScenarioActivity) getActivity()).setOnGoodsSortTypeClickListener(this);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    GoodsBean goodsBean = (GoodsBean) adapter.getData().get(position);
                    Intent i = new Intent(getActivity(), GoodsDetailActivity.class);
                    i.putExtra("id", goodsBean.getGoodsId());
                    ActivityUtils.startActivity(i);
                }
            }
        });

        fragmentScenarioGoodsRecyclerview.addItemDecoration(new GridDivider(45));

    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void requestData() {
        scenarioGoodsPresent.requestData(10, pageIndex, sortType, scenarioId);
    }

    @Override
    public void setHavePageEmptyView() {

    }

    @Override
    public void setHavePageErrorView() {

    }

    @Override
    public void loadDataEnd() {
        adapter.loadMoreEnd();
    }

    @Override
    public void firstLoadData(List list) {
        if(!ObjectUtils.isEmpty(list)){

            adapter.setNewData(list);
            adapter.loadMoreComplete();

        }
    }

    @Override
    public void loadData(List list) {
        if(!ObjectUtils.isEmpty(list)){

            adapter.addData(list);
            adapter.loadMoreComplete();

        }
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onGoodsSortTypeClick(int sortType) {
        if(this.sortType==sortType&&this.sortType < 3){
            return;
        }

        this.sortType = sortType;
        pageIndex=1;
        requestData();
    }
}


