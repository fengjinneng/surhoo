package com.surhoo.sh.goods.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.goods.adapter.LevelOneCategoryAdapter;
import com.surhoo.sh.goods.adapter.LevelTwoCategoryAdapter;
import com.surhoo.sh.goods.bean.CategoryBean;
import com.surhoo.sh.goods.presenter.CategoryPresenter;
import com.surhoo.sh.goods.presenter.impl.CategoryPresenterImpl;
import com.surhoo.sh.goods.view.CategoryListView;
import com.surhoo.sh.goods.view.GoodsListActivity;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
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
    @BindView(R.id.activity_category_level_one_name)
    TextView activityCategoryLevelOneName;

    private LevelTwoCategoryAdapter levelTwoCategoryAdapter;


    @Override
    public int getContentView() {
        return R.layout.activity_category;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {
        categoryPresenter = new CategoryPresenterImpl();

        categoryPresenter.bindView(this, this);
        toolbarLayoutTitle.setText("全部分类");

        leverOneRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        leverTwoRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));

        levelTwoCategoryAdapter = new LevelTwoCategoryAdapter(R.layout.item_level_two_category, null);

        leverTwoRecyclerview.setAdapter(levelTwoCategoryAdapter);

        levelTwoCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CategoryBean categoryBean = (CategoryBean) adapter.getData().get(position);

                Intent intent = new Intent(CategoryActivity.this, GoodsListActivity.class);
                intent.putExtra("from",2);
                intent.putExtra("id", categoryBean.getId());
                ActivityUtils.startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

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


    LevelOneCategoryAdapter levelOneCategoryAdapter;

    @Override
    public void showList(List<CategoryBean> list) {

        list.get(0).setChecked(true);
        activityCategoryLevelOneName.setText(list.get(0).getName());
        levelOneCategoryAdapter = new LevelOneCategoryAdapter(R.layout.item_level_one_category, list);
        leverOneRecyclerview.setAdapter(levelOneCategoryAdapter);
        levelOneCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CategoryBean categoryBean = (CategoryBean) adapter.getData().get(position);

                activityCategoryLevelOneName.setText(categoryBean.getName());

                for (int i = 0; i < adapter.getData().size(); i++) {
                    ((CategoryBean) adapter.getData().get(i)).setChecked(false);
                }

                categoryBean.setChecked(true);

                levelOneCategoryAdapter.notifyDataSetChanged();

                if (ObjectUtils.isEmpty(categoryBean.getCategoryBeans())) {
                    categoryPresenter.requestLevelTwoCategory(categoryBean.getId(), position);
                } else {
                    levelTwoCategoryAdapter.setNewData(categoryBean.getCategoryBeans());
                }
            }
        });

        categoryPresenter.requestLevelTwoCategory(list.get(0).getId(), 0);

    }

    @Override
    public void showLevelTwoCategory(List<CategoryBean> list, int positon) {

        ((CategoryBean) levelOneCategoryAdapter.getData().get(positon)).setCategoryBeans(list);
        levelTwoCategoryAdapter.setNewData(list);
    }
}
