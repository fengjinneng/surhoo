package com.surhoo.sh.designer.fragment;


import android.content.Intent;
import android.os.Bundle;
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
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.custom.MyLoadMoreView;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.designer.bean.DesignerListBean;
import com.surhoo.sh.designer.view.DesignerActivity;
import com.surhoo.sh.material.MaterialDetailActivity;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.search.adapter.SearchDesignerAdapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DesignerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DesignerListFragment extends BaseFragment implements HavePageListBaseView {
    private static final String FROM = "from";
    private static final String PARAM1 = "param1";
    @BindView(R.id.fragment_designer_list_recyclerView)
    RecyclerView recyclerView;

    private String from;
    private String mParam1;


    public DesignerListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DesignerListFragment newInstance(String from, String param1) {
        DesignerListFragment fragment = new DesignerListFragment();
        Bundle args = new Bundle();
        args.putString(FROM, from);
        args.putString(PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            from = getArguments().getString(FROM);
            mParam1 = getArguments().getString(PARAM1);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_designer_list, container, false);
    }

    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;
    private SearchDesignerAdapter adapter;
    private int pageIndex = 1;
    @Override
    public void init() {


        initLoadingView();

        adapter = new SearchDesignerAdapter(R.layout.item_designer_list, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter.setEmptyView(loadingView);
        recyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();

            }
        }, recyclerView);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    DesignerListBean designerBean = (DesignerListBean) adapter.getData().get(position);
                    Intent designerIntent = new Intent(getActivity(), DesignerActivity.class);
                    designerIntent.putExtra("id", designerBean.getDesignerId());
                    ActivityUtils.startActivity(designerIntent);
                }
            }
        });

        adapter.setLoadMoreView(new MyLoadMoreView());

    }




    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {
        super.onRecevieMessage(bean);

        switch (bean.getCode()){
            case EventBusMessageBean.Collect.cancelDesignerCollect:
                pageIndex=1;
                requestData();
                break;
        }

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

    @Override
    public void requestData() {

        if (pageIndex == 1) {
            httpParams = new HttpParams();
            httpParams.put("type", 3);
            httpParams.put("pageSize", 10);
            httpParams.put("pageIndex", pageIndex);
        } else {
            httpParams.put("pageIndex", pageIndex);
        }

        NetworkReturnUtil.requestHavePageList(this, getActivity(), Api.getCollectList, httpParams, DesignerListBean.class, pageIndex);

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
