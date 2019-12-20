package com.surhoo.sh.designer.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.base.PagerBaseView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.designer.adapter.DesignerDynamicAdapter;
import com.surhoo.sh.designer.bean.DesignerDynamicBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DynamicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DynamicFragment extends BaseFragment implements PagerBaseView {
    private static final String DESIGNID = "designerId";
    @BindView(R.id.fragment_dynamic_recyclerview)
    RecyclerView recyclerView;

    private Integer designerId;
    private DesignerDynamicAdapter adapter;
    private List<DesignerDynamicBean> datas;


    public DynamicFragment() {
        // Required empty public constructor
    }

    public static DynamicFragment newInstance(Integer designerId) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt(DESIGNID, designerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            designerId = getArguments().getInt(DESIGNID);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return getLayoutInflater().inflate(R.layout.fragment_dynamic, container, false);
    }

    @Override
    public void init() {

        datas = new ArrayList<>();
        adapter = new DesignerDynamicAdapter(R.layout.item_desinger_dynamic,datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();
            }
        },recyclerView);

    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }


    private HttpParams httpParams;
    private int pageIndex = 1;

    @Override
    public void requestData() {

        if (pageIndex == 1) {
            httpParams = new HttpParams();
            httpParams.put("isSelf", false);
            httpParams.put("designerId", designerId);
            httpParams.put("pageSize", 10);
            httpParams.put("pageIndex", pageIndex);
        } else {
            httpParams.put("pageIndex", pageIndex);
        }

        NetworkReturnUtil.requestPage(this, getActivity(), Api.DYNAMICOFDESIGNER, httpParams, DesignerDynamicBean.class, pageIndex);

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
