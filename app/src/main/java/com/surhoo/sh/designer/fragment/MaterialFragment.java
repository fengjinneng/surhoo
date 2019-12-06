package com.surhoo.sh.designer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.base.PagerBaseView;
import com.surhoo.sh.common.custom.MyLoadMoreView;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.material.MaterialDetailActivity;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.search.SearchCategoryActivity;
import com.surhoo.sh.search.adapter.SearchMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MaterialFragment extends BaseFragment implements PagerBaseView {
    private static final String DESIGNERID = "designerId";

    @BindView(R.id.fragment_material_recyclerview)
    RecyclerView fragmentMaterialRecyclerview;

    private Integer designerId;

    SearchMaterialAdapter adapter;
    List datas;


    public static MaterialFragment newInstance(Integer designerId) {
        MaterialFragment fragment = new MaterialFragment();
        Bundle args = new Bundle();
        args.putInt(DESIGNERID, designerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            designerId = getArguments().getInt(DESIGNERID);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return getLayoutInflater().inflate(R.layout.fragment_material, container, false);
    }

    @Override
    public void init() {

        datas = new ArrayList();
        fragmentMaterialRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new SearchMaterialAdapter(R.layout.item_material_list, datas);

        fragmentMaterialRecyclerview.setAdapter(adapter);


        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();

            }
        },fragmentMaterialRecyclerview);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MaterialBean materialBean = (MaterialBean) adapter.getData().get(position);
                Intent i  = new Intent(getActivity(),MaterialDetailActivity.class);
                i.putExtra("id",materialBean.getMaterialId());
                ActivityUtils.startActivity(i);
            }
        });


        adapter.setEmptyView(getLayoutInflater().inflate(R.layout.empty_view_layout, null));
        adapter.setLoadMoreView(new MyLoadMoreView());

    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }


    private HttpParams httpParams;

    @Override
    public void requestData() {
        if(pageIndex==1){
            httpParams = new HttpParams();
            httpParams.put("isSelf",false);
            httpParams.put("designerId",designerId);
            httpParams.put("pageSize",10);
            httpParams.put("pageIndex",pageIndex);
        }else {
            httpParams.put("pageIndex",pageIndex);
        }

        NetworkReturnUtil.requestPage(this,getActivity(),Api.MATERIALOFDESIGNER,httpParams,MaterialBean.class,pageIndex);
    }





    private int pageIndex = 1;


    @Override
    public void firstInEmpty() {

    }

    @Override
    public void loadEnd() {
        adapter.loadMoreEnd();
    }

    @Override
    public void refresh(List list) {
        adapter.setNewData(list);
        adapter.loadMoreComplete();
    }

    @Override
    public void loadData(List list) {
        adapter.addData(list);
        adapter.loadMoreComplete();
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
