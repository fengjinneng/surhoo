package com.surhoo.sh.designer.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.surhoo.sh.ImagePagerActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.base.HavePageListBaseView;
import com.surhoo.sh.base.NoPageListBaseView;
import com.surhoo.sh.common.bean.PhotoInfo;
import com.surhoo.sh.common.custom.MyLoadMoreView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.recyclerview.GridDivider;
import com.surhoo.sh.common.util.ClickUtil;
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
public class FinishProducsFragment extends BaseFragment implements HavePageListBaseView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MATERIALID = "materialId";
    @BindView(R.id.fragment_finish_products_recyclerview)
    RecyclerView recyclerView;

    private Integer materialId;
    private FinishProductsAdapter adapter;
    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;


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

        initLoadingView();

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        adapter = new FinishProductsAdapter(R.layout.item_designer_finish_product, null);

        recyclerView.setAdapter(adapter);

        adapter.setEmptyView(loadingView);


        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();
            }
        }, recyclerView);

        recyclerView.addItemDecoration(new GridDivider());

        adapter.setLoadMoreView(new MyLoadMoreView());


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());

                    List<String> photoUrls = new ArrayList<String>();
                    photoUrls.add(((FinishProductBean) adapter.getData().get(position)).getWorks());

                    ImagePagerActivity.startImagePagerActivity(getActivity(), photoUrls, position, imageSize);
                }
            }
        });

    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    private int pageIndex = 1;

    HttpParams httpParams;

    @Override
    public void requestData() {

        if (pageIndex == 1) {
            httpParams = new HttpParams();
            httpParams.put("isSelf", false);
            httpParams.put("designerId", materialId);
            httpParams.put("pageSize", 20);
            httpParams.put("pageIndex", pageIndex);
        } else {
            httpParams.put("pageIndex", pageIndex);
        }
        NetworkReturnUtil.requestHavePageList(this, getActivity(),
                Api.FINISHPRODUCTOFDESIGNER, httpParams, FinishProductBean.class, pageIndex);

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
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
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
}
