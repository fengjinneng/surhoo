package com.surhoo.sh.goods.view.impl;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.goods.adapter.AllCommentsAdapter;
import com.surhoo.sh.goods.bean.CommentBean;
import com.surhoo.sh.goods.presenter.AllCommentsPresenter;
import com.surhoo.sh.goods.presenter.impl.AllCommentsPresenterImpl;
import com.surhoo.sh.goods.view.AllCommentsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllCommentsActivity extends BaseActivity implements AllCommentsView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_all_comments_recyclerview)
    RecyclerView activityAllCommentsRecyclerview;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comments);
        ButterKnife.bind(this);


        id = getIntent().getIntExtra("id",0);

        initView();
        requestData();
    }

    @Override
    public void initView() {
        datas = new ArrayList<>();

        toolbarLayoutTitle.setText("全部评价");
        allCommentsPresenter = new AllCommentsPresenterImpl();
        allCommentsPresenter.bindView(this,this);

        allCommentsPresenter.requestData(pageSize,pageIndex,id);

        allCommentsAdapter = new AllCommentsAdapter(R.layout.item_goods_comments,datas);

        activityAllCommentsRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        activityAllCommentsRecyclerview.setAdapter(allCommentsAdapter);

        allCommentsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();
            }
        },activityAllCommentsRecyclerview);
    }

    private int pageSize = 20;
    private int pageIndex = 1;

    @Override
    public void requestData() {

        allCommentsPresenter.requestData(pageSize,pageIndex,id);

    }

    AllCommentsPresenter allCommentsPresenter;

    AllCommentsAdapter allCommentsAdapter;

    List<CommentBean.ListBean> datas;

    @Override
    public void firstInEmpty() {
        datas.clear();
        allCommentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadEnd() {
        allCommentsAdapter.loadMoreEnd();
    }

    @Override
    public void refresh(List<CommentBean.ListBean> list) {
        datas.addAll(list);
        allCommentsAdapter.setNewData(list);
        allCommentsAdapter.loadMoreComplete();
    }

    @Override
    public void loadData(List<CommentBean.ListBean> list) {
        datas.addAll(list);
        allCommentsAdapter.setNewData(list);
        allCommentsAdapter.loadMoreComplete();
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }
}
