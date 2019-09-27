package com.surhoo.sh.goods.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.goods.adapter.LevelOneCategoryAdapter;
import com.surhoo.sh.goods.adapter.LevelTwoCategoryAdapter;
import com.surhoo.sh.goods.bean.CategoryBean;
import com.surhoo.sh.goods.presenter.CategoryPresenter;
import com.surhoo.sh.goods.presenter.impl.CategoryPresenterImpl;
import com.surhoo.sh.goods.view.CategoryListView;
import com.surhoo.sh.goods.view.GoodsListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends BaseActivity implements CategoryListView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_category_level_one_recyclerView)
    RecyclerView leverOneRecyclerview;
    @BindView(R.id.activity_category_level_two_recyclerView)
    RecyclerView leverTwoRecyclerview;

    CategoryPresenter categoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        initView();
        requestData();
    }

    @Override
    public void initView() {
        categoryPresenter = new CategoryPresenterImpl();

        categoryPresenter.bindView(this, this);
        toolbarLayoutTitle.setText("全部分类");

        leverOneRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        leverTwoRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void requestData() {

        categoryPresenter.requestLevelOneCategory();

    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    public void showLevelTwoCategory(List<CategoryBean> levelOneCategorys) {

    }


    private boolean firstIn =true;

    @Override
    public void showList(List<CategoryBean> list) {

        if (!firstIn) {
            LevelTwoCategoryAdapter levelTwoCategoryAdapter = new LevelTwoCategoryAdapter(R.layout.item_level_two_category, list);
            leverTwoRecyclerview.setAdapter(levelTwoCategoryAdapter);

            levelTwoCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    Intent intent = new Intent(CategoryActivity.this, GoodsListActivity.class);

                    intent.putExtra("id", ((CategoryBean) adapter.getData().get(position)).getId());

                    ActivityUtils.startActivity(intent);

                }
            });
        } else {
            firstIn =false;
            LevelOneCategoryAdapter levelOneCategoryAdapter = new LevelOneCategoryAdapter(R.layout.item_level_one_category, list);
            leverOneRecyclerview.setAdapter(levelOneCategoryAdapter);
            levelOneCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    CategoryBean categoryBean = (CategoryBean) adapter.getData().get(position);
                    categoryPresenter.requestLevelTwoCategory(categoryBean.getId());
                }
            });

        }
    }
}
