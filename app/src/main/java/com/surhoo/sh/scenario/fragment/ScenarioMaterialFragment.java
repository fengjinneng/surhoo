package com.surhoo.sh.scenario.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.material.MaterialDetailActivity;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.scenario.presenter.ScenarioMaterialPresent;
import com.surhoo.sh.scenario.view.IScenarioMaterialView;
import com.surhoo.sh.scenario.view.ScenarioActivity;
import com.surhoo.sh.search.adapter.SearchMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScenarioMaterialFragment extends BaseFragment implements IScenarioMaterialView ,ScenarioActivity.onMaterialSortTypeClickListener {
    private static final String SCENARIOID = "scenarioId";
    @BindView(R.id.fragment_scenario_material_recyclerview)
    RecyclerView fragmentScenarioMaterialRecyclerview;


    SearchMaterialAdapter adapter;
    private List<MaterialBean> datas;

    private Integer scenarioId;

    private ScenarioMaterialPresent present;

    private Integer pageIndex = 1;
    private Integer sortType = 1;


    public ScenarioMaterialFragment() {
        // Required empty public constructor
    }


    public static ScenarioMaterialFragment newInstance(Integer scenarioId) {
        ScenarioMaterialFragment fragment = new ScenarioMaterialFragment();
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
        return getLayoutInflater().inflate(R.layout.fragment_scenario_material, container, false);
    }

    @Override
    public void init() {

        present = new ScenarioMaterialPresent();
        present.bindView(getActivity(),this);

        datas = new ArrayList<>();

        adapter = new SearchMaterialAdapter(R.layout.item_material_list,datas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        fragmentScenarioMaterialRecyclerview.setLayoutManager(linearLayoutManager);
        fragmentScenarioMaterialRecyclerview.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();
            }
        },fragmentScenarioMaterialRecyclerview);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MaterialBean materialBean = (MaterialBean)adapter.getData().get(position);
                Intent i = new Intent(getActivity(), MaterialDetailActivity.class);
                i.putExtra("id", materialBean.getMaterialId());
                ActivityUtils.startActivity(i);
            }
        });

        ((ScenarioActivity) getActivity()).setOnMaterialSortTypeClickListener(this);

    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void requestData() {
        present.requestData(20,pageIndex,sortType,scenarioId);
    }

    @Override
    public void firstInEmpty() {

    }

    @Override
    public void loadEnd() {
        adapter.loadMoreEnd();
    }

    @Override
    public void refresh(List list) {
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
    public void onMaterialSortTypeClick(int sortType) {
        if(this.sortType==sortType){
            return;
        }
        this.sortType = sortType;
        pageIndex=1;
        requestData();
    }
}
