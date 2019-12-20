package com.surhoo.sh.order.adapter;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.order.bean.OrderListBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderListAdapter extends BaseQuickAdapter<OrderListBean, BaseViewHolder> {


    public MyOrderListAdapter(int layoutResId, @Nullable List<OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderListBean item) {

        helper.setText(R.id.item_my_order_list_shopName, item.getShopName());
        helper.setText(R.id.item_my_order_list_time, item.getOrderTime());

        //订单状态:1-待支付,2-已支付(待发货),3-已取消,4-已发货(待收货),6-已收货(待评价),7-已完成
        //8 :订单关闭

        TextView status = (TextView) helper.getView(R.id.item_my_order_list_status);

        TextView button1 = helper.getView(R.id.item_my_order_list_button1);
        TextView button2 = helper.getView(R.id.item_my_order_list_button2);

        helper.addOnClickListener(R.id.item_my_order_list_button1,R.id.item_my_order_list_button2);

        switch (item.getOrderStatus()) {
            //待支付
            case 1:
                status.setText("待支付");
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button1.setText("取消订单");
                button2.setText("立即付款");
                break;
            //已支付(待发货)
            case 2:
                status.setText("待发货");
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                break;
            //已取消
            case 3:
                status.setText("已取消");
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
                button2.setText("删除订单");
                break;
            //已发货(待收货)
            case 4:
                status.setText("待收货");
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button1.setText("查看物流");
                button2.setText("确认收货");
                break;
            //已收货(待评价)
            case 6:
                status.setText("待评价");
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
                button2.setText("立即评价");
                break;
                //已完成
            case 7:
                status.setText("已完成");
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
                button2.setText("删除订单");
                break;

            case 8:
                //8 :订单关闭
                status.setText("已关闭");
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
                button2.setText("删除订单");
                break;
        }


        RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.item_my_order_list_recyclerView);

        if(ObjectUtils.isEmpty(item.getAdapter())){

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            recyclerView.setLayoutManager(linearLayoutManager);
            MyOrderListItemAdapter myOrderListItemAdapter = new MyOrderListItemAdapter(R.layout.item_order_confirm, item.getOrderDataList());
            recyclerView.setAdapter(myOrderListItemAdapter);

            item.setLinearLayoutManager(linearLayoutManager);
            item.setAdapter(myOrderListItemAdapter);
        }else {
            recyclerView.setAdapter(item.getAdapter());
        }

    }
}
