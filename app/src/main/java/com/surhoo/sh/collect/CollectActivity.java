package com.surhoo.sh.collect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.goods.fragment.GoodsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectActivity extends BaseActivity implements CollectView{

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_collect_tab)
    SlidingTabLayout activityCollectTab;
    @BindView(R.id.activity_collect_viewpager)
    ViewPager activityCollectViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("我的收藏");

        List<Fragment> datas = new ArrayList<>();
        datas.add(GoodsListFragment.newInstance(4,0 , 0));
        datas.add(GoodsListFragment.newInstance(4,0 , 0));
        datas.add(GoodsListFragment.newInstance(4,0 , 0));
        datas.add(GoodsListFragment.newInstance(4,0 , 0));

        String[] arr = new String[4];
        arr[0] = "商品";
        arr[1] = "素材";
        arr[2] = "设计师";
        arr[3] = " 店铺";
        activityCollectViewpager.setAdapter(new BaseViewpageAdapter(getSupportFragmentManager(), datas));
        activityCollectTab.setViewPager(activityCollectViewpager, arr);

    }

    @Override
    public void requestData() {

    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}