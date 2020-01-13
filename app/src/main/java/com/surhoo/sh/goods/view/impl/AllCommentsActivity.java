package com.surhoo.sh.goods.view.impl;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goyourfly.multi_picture.ImageLoader;
import com.goyourfly.multi_picture.MultiPictureView;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.recyclerview.LinnearLayoutItemDecoration;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.goods.adapter.AllCommentsAdapter;
import com.surhoo.sh.goods.bean.CommentBean;
import com.surhoo.sh.goods.presenter.AllCommentsPresenter;
import com.surhoo.sh.goods.presenter.impl.AllCommentsPresenterImpl;
import com.surhoo.sh.goods.view.AllCommentsView;
import com.surhoo.sh.order.OrderEvaluationActivity;

import org.jetbrains.annotations.NotNull;

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

    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;


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


        MultiPictureView.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(@NotNull ImageView imageView, @NotNull Uri uri) {
                //使用glideutil会报错
                Glide.with(AllCommentsActivity.this).load(uri).into(imageView);
            }
        });

        id = getIntent().getIntExtra("id",0);


        toolbarLayoutTitle.setText("全部评价");
        allCommentsPresenter = new AllCommentsPresenterImpl();
        allCommentsPresenter.bindView(this,this);

        initLoadingView();
        initRecyclerViewData();

    }

    private void initLoadingView() {
        loadingView = LayoutInflater.from(this).inflate(R.layout.load_data_loading_view, null);
        loadingEmptyView = LayoutInflater.from(this).inflate(R.layout.load_data_empty_view, null);
        ImageView emptyImg = (ImageView) loadingEmptyView.findViewById(R.id.load_data_empty_view_img);
        emptyImg.setImageDrawable(getResources().getDrawable(R.mipmap.search_empty_img));
        TextView emptyText = (TextView) loadingEmptyView.findViewById(R.id.load_data_empty_view_content);
        emptyText.setText("抱歉,没有找到相关内容");

        loadingErrorView = LayoutInflater.from(this).inflate(R.layout.load_data_error_view, null);

        TextView errorText = (TextView) loadingErrorView.findViewById(R.id.load_data_error_view_retry);
        errorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    allCommentsAdapter.setEmptyView(loadingView);
                    requestData();
                }
            }
        });
    }

    private void initRecyclerViewData() {

        allCommentsAdapter = new AllCommentsAdapter(R.layout.item_goods_comments,null);

        activityAllCommentsRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        activityAllCommentsRecyclerview.setAdapter(allCommentsAdapter);

        allCommentsAdapter.setEmptyView(loadingView);

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


    @Override
    public void setHavePageEmptyView() {
        allCommentsAdapter.setNewData(null);
        allCommentsAdapter.setEmptyView(loadingEmptyView);
    }

    @Override
    public void setHavePageErrorView() {
        allCommentsAdapter.setNewData(null);
        allCommentsAdapter.setEmptyView(loadingErrorView);
    }

    @Override
    public void loadDataEnd() {
        allCommentsAdapter.loadMoreEnd();
    }

    @Override
    public void firstLoadData(List list) {
        allCommentsAdapter.setNewData(list);
        allCommentsAdapter.loadMoreComplete();
    }

    @Override
    public void loadData(List list) {
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
