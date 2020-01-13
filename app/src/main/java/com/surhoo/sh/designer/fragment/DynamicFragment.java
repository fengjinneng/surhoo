package com.surhoo.sh.designer.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.base.HavePageListBaseView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.custom.MyLoadMoreView;
import com.surhoo.sh.common.util.ClickUtil;
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
public class DynamicFragment extends BaseFragment implements HavePageListBaseView {
    private static final String DESIGNID = "designerId";
    @BindView(R.id.fragment_dynamic_recyclerview)
    RecyclerView recyclerView;

    private Integer designerId;
    private DesignerDynamicAdapter adapter;
    private List<DesignerDynamicBean> datas;


    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;


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

        initLoadingView();

        datas = new ArrayList<>();
        adapter = new DesignerDynamicAdapter(R.layout.item_desinger_dynamic,datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setEmptyView(loadingView);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();
            }
        },recyclerView);


        adapter.setLoadMoreView(new MyLoadMoreView());
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

        NetworkReturnUtil.requestHavePageList(this, getActivity(), Api.DYNAMICOFDESIGNER, httpParams, DesignerDynamicBean.class, pageIndex);

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
