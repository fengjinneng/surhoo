package com.surhoo.sh.goods.view.impl;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.recyclerview.LinnearLayoutItemDecoration;
import com.surhoo.sh.goods.adapter.AllCommentsAdapter;
import com.surhoo.sh.goods.bean.CommentBean;
import com.surhoo.sh.goods.presenter.AllCommentsPresenter;
import com.surhoo.sh.goods.presenter.impl.AllCommentsPresenterImpl;
import com.surhoo.sh.goods.view.AllCommentsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    public int getContentView() {
        return R.layout.activity_all_comments;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        id = getIntent().getIntExtra("id",0);

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


        activityAllCommentsRecyclerview.addItemDecoration(
                new LinnearLayoutItemDecoration(this,R.drawable.item_decoration_linearlayout_10dp));
    }

    @Override
    public void initData() {

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
        allCommentsAdapter.addData(list);
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
