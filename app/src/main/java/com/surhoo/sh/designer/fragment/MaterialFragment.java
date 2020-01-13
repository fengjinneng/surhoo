package com.surhoo.sh.designer.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.base.HavePageListBaseView;
import com.surhoo.sh.common.custom.MyLoadMoreView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.material.MaterialDetailActivity;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.search.adapter.SearchMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MaterialFragment extends BaseFragment implements HavePageListBaseView {
    private static final String PARAM1 = "param1";
    private static final String FROM = "from";

    @BindView(R.id.fragment_material_recyclerview)
    RecyclerView fragmentMaterialRecyclerview;

    private Integer designerId;
    private String from;

    private SearchMaterialAdapter adapter;

    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;


    public static MaterialFragment newInstance(String from, Integer param1) {
        MaterialFragment fragment = new MaterialFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM1, param1);
        args.putString(FROM, from);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            designerId = getArguments().getInt(PARAM1);
            from = getArguments().getString(FROM);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return getLayoutInflater().inflate(R.layout.fragment_material, container, false);
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
                    adapter.setEmptyView(loadingView);
                    requestData();
                }
            }
        });
    }

    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {
        super.onRecevieMessage(bean);

        switch (bean.getCode()){
            case EventBusMessageBean.Collect.cancelMaterialCollect:
                pageIndex=1;
                requestData();
                break;
        }
    }

    @Override
    public void init() {

        initLoadingView();

        fragmentMaterialRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new SearchMaterialAdapter(R.layout.item_material_list, null);

        adapter.setEmptyView(loadingView);

        fragmentMaterialRecyclerview.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();

            }
        }, fragmentMaterialRecyclerview);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    MaterialBean materialBean = (MaterialBean) adapter.getData().get(position);
                    Intent i = new Intent(getActivity(), MaterialDetailActivity.class);
                    i.putExtra("id", materialBean.getMaterialId());
                    ActivityUtils.startActivity(i);
                }
            }
        });

        adapter.setLoadMoreView(new MyLoadMoreView());

    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }


    private HttpParams httpParams;
    private int pageIndex = 1;


    @Override
    public void requestData() {

        if (StringUtils.equals("designer", from)) {
            if (pageIndex == 1) {
                httpParams = new HttpParams();
                httpParams.put("isSelf", false);
                httpParams.put("designerId", designerId);
                httpParams.put("pageSize", 10);
                httpParams.put("pageIndex", pageIndex);
            } else {
                httpParams.put("pageIndex", pageIndex);
            }

            NetworkReturnUtil.requestHavePageList(this, getActivity(), Api.MATERIALOFDESIGNER, httpParams, MaterialBean.class, pageIndex);
        }

        if (StringUtils.equals("collect", from)) {

            if (pageIndex == 1) {
                httpParams = new HttpParams();
                httpParams.put("type", 2);
                httpParams.put("pageSize", 10);
                httpParams.put("pageIndex", pageIndex);
            } else {
                httpParams.put("pageIndex", pageIndex);
            }

            NetworkReturnUtil.requestHavePageList(this, getActivity(), Api.getCollectList, httpParams, MaterialBean.class, pageIndex);
        }
    }

    @Override
    public void setHavePageEmptyView() {
        adapter.setNewData(null);
        adapter.setEmptyView(loadingEmptyView);
    }

    @Override
    public void setHavePageErrorView() {
        adapter.setNewData(null);
        adapter.setEmptyView(loadingErrorView);
    }

    @Override
    public void loadDataEnd() {
        adapter.loadMoreEnd();
    }

    @Override
    public void firstLoadData(List list) {
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
