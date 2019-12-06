package com.surhoo.sh.designer.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.base.NoPageListBaseView;
import com.surhoo.sh.common.custom.MyLoadMoreView;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.designer.adapter.FinishProductsAdapter;
import com.surhoo.sh.designer.bean.FinishProductBean;
import com.surhoo.sh.material.bean.MaterialBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FinishProducsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishProducsFragment extends BaseFragment implements NoPageListBaseView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MATERIALID = "materialId";
    @BindView(R.id.fragment_finish_products_recyclerview)
    RecyclerView recyclerView;


    private Integer materialId;

    FinishProductsAdapter adapter;
    List<FinishProductBean> datas;


    public static FinishProducsFragment newInstance(Integer materialId) {
        FinishProducsFragment fragment = new FinishProducsFragment();
        Bundle args = new Bundle();
        args.putInt(MATERIALID, materialId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            materialId = getArguments().getInt(MATERIALID);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return getLayoutInflater().inflate(R.layout.fragment_finish_producs, container, false);
    }

    @Override
    public void init() {


        datas = new ArrayList();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new FinishProductsAdapter(R.layout.item_designer_finish_product, datas);

        recyclerView.setAdapter(adapter);

        adapter.setEmptyView(getLayoutInflater().inflate(R.layout.empty_view_layout, null));
        adapter.setLoadMoreView(new MyLoadMoreView());
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void requestData() {

        HttpParams httpParams = new HttpParams();
        httpParams.put("isSelf", false);
        httpParams.put("designerId", materialId);

        NetworkReturnUtil.requestList(this, getActivity(), Api.FINISHPRODUCTOFDESIGNER, httpParams, MaterialBean.class);

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showList(List list) {
        datas.addAll(list);
        adapter.setNewData(list);
        adapter.loadMoreEnd();
    }
}
