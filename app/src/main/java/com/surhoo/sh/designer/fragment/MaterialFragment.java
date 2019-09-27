package com.surhoo.sh.designer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.base.PagerBaseView;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.material.MaterialBean;
import com.surhoo.sh.search.adapter.SearchMaterialAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MaterialFragment extends BaseFragment implements PagerBaseView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MaterialFragment";
    @BindView(R.id.fragment_material_recyclerview)
    RecyclerView fragmentMaterialRecyclerview;
    Unbinder unbinder;

    private String mParam1;
    private String mParam2;


    public MaterialFragment() {
    }


    SearchMaterialAdapter adapter;
    List datas;


    public static MaterialFragment newInstance(String param1, String param2) {
        MaterialFragment fragment = new MaterialFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_material, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentMaterialRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new SearchMaterialAdapter(R.layout.item_material_list, datas);

        fragmentMaterialRecyclerview.setAdapter(adapter);

        addData();

    }

    private int pageIndex = 1;

    private void addData() {


        HttpParams httpParams = new HttpParams();
        httpParams.put("isSelf",false);
        httpParams.put("designerId",1);
        httpParams.put("pageSize",10);
        httpParams.put("pageIndex",pageIndex);

        NetworkReturnUtil.requestPage(this,getContext(),Api.GOODSLIST,httpParams,MaterialBean.class,pageIndex);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        datas.add(list);
        adapter.setNewData(list);
        adapter.loadMoreComplete();
    }

    @Override
    public void loadData(List list) {
        datas.add(list);
        adapter.addData(list);
        adapter.loadMoreComplete();
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
