package com.surhoo.sh.goods.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.goods.fragment.GoodsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsListActivity extends BaseActivity {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_goods_list_slidingTabLayout)
    SlidingTabLayout activityGoodsListSlidingTabLayout;
    @BindView(R.id.activity_goods_list_viewpager)
    ViewPager activityGoodsListViewpager;

    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_goods_list;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }


    @Override
    public void initView() {
        id = getIntent().getIntExtra("id",0);
        toolbarLayoutTitle.setText("商品列表");

        List<Fragment> datas = new ArrayList<>();
        datas.add(GoodsListFragment.newInstance(1,id , 1));
        datas.add(GoodsListFragment.newInstance(1,id , 2));
        datas.add(GoodsListFragment.newInstance(1,id , 3));

        String[] arr = new String[3];
        arr[0] = "综合";
        arr[1] = "销量";
        arr[2] = "价格";
        activityGoodsListViewpager.setAdapter(new BaseViewpageAdapter(getSupportFragmentManager(), datas));
        activityGoodsListSlidingTabLayout.setViewPager(activityGoodsListViewpager, arr);
    }

    @Override
    public void initData() {

    }


    @Override
    public void requestData() {

    }

    @OnClick({R.id.toolbar_layout_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;

        }
    }
}
