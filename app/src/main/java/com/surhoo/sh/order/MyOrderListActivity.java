package com.surhoo.sh.order;

import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.order.fragment.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

public class MyOrderListActivity extends BaseActivity {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_my_order_list_slidingTablayout)
    SlidingTabLayout activityMyOrderListSlidingTablayout;
    @BindView(R.id.activity_my_order_viewpager)
    ViewPager activityMyOrderViewpager;


    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_my_order_list;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {

        toolbarLayoutTitle.setText("订单详情");

    }

    @Override
    public void initData() {

        //订单状态:1-待支付,2-已支付(待发货),3-已取消,4-已发货(待收货),5-待使用，6-已收货(待评价),7-已完成
        //8 :订单关闭

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(OrderListFragment.newInstance(0));
        fragments.add(OrderListFragment.newInstance(1));
        fragments.add(OrderListFragment.newInstance(2));
        fragments.add(OrderListFragment.newInstance(4));
        fragments.add(OrderListFragment.newInstance(6));

        String[] arr = new String[fragments.size()];
        arr[0] = "全部";
        arr[1] = "待付款";
        arr[2] = "待发货";
        arr[3] = "待收货";
        arr[4] = "待评价";

        activityMyOrderViewpager.setAdapter(new BaseViewpageAdapter(getSupportFragmentManager(), fragments));

        activityMyOrderListSlidingTablayout.setViewPager(activityMyOrderViewpager, arr);

    }

    @Override
    public void requestData() {

    }
}
