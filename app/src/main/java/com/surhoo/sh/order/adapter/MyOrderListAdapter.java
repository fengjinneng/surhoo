package com.surhoo.sh.order.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.bean.order.response.OrderDetailReturnBean;
import com.surhoo.sh.order.OrderDetailActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderListAdapter extends BaseQuickAdapter<OrderDetailReturnBean, BaseViewHolder> {


    public MyOrderListAdapter(int layoutResId, @Nullable List<OrderDetailReturnBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderDetailReturnBean item) {

        helper.setText(R.id.item_my_order_list_shopName, item.getShopName());
        helper.setText(R.id.item_my_order_list_time, item.getOrderTime());

        helper.setText(R.id.item_my_order_list_totalPrice,"¥ "+item.getOrderAmount());

        //订单状态:1-待支付,2-已支付(待发货),3-已取消,4-已发货(待收货),6-已收货(待评价),7-已完成
        //8 :订单关闭

        TextView status = (TextView) helper.getView(R.id.item_my_order_list_status);

        TextView button1 = helper.getView(R.id.item_my_order_list_button1);
        TextView button2 = helper.getView(R.id.item_my_order_list_button2);

        helper.addOnClickListener(R.id.item_my_order_list_button1,R.id.item_my_order_list_button2,R.id.item_my_order_list_recyclerView);

        switch (item.getOrderStatus()) {
            //待支付
            case 1:
                status.setText("待付款");
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
                button1.setVisibility(View.VISIBLE);
                button1.setText("删除订单");
                button2.setVisibility(View.GONE);
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
                button1.setText("查看物流");
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button2.setText("立即评价");
                break;
                //已完成
            case 7:
                status.setText("已完成");
                button1.setVisibility(View.VISIBLE);
                button1.setText("删除订单");
                button2.setVisibility(View.GONE);
                break;

            case 8:
                //8 :订单关闭
                status.setText("已关闭");
                button1.setVisibility(View.VISIBLE);
                button1.setText("删除订单");
                button2.setVisibility(View.GONE);
                break;
        }

        RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.item_my_order_list_recyclerView);

        if(ObjectUtils.isEmpty(item.getAdapter())){

//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//
//            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//            recyclerView.setLayoutManager(linearLayoutManager);
            MyOrderListItemAdapter myOrderListItemAdapter = new MyOrderListItemAdapter(R.layout.item_order_confirm, item.getOrderDataList());
            recyclerView.setAdapter(myOrderListItemAdapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext) {
                @Override
                public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                    return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                }
            };
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);


            item.setLinearLayoutManager(linearLayoutManager);
            item.setAdapter(myOrderListItemAdapter);

            myOrderListItemAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    OrderDetailReturnBean.OrderDataListBean orderListBean = (OrderDetailReturnBean.OrderDataListBean) adapter.getData().get(position);
                    Intent intent = new Intent(mContext, OrderDetailActivity.class);
                    intent.putExtra("id", orderListBean.getOrderId());
                    ActivityUtils.startActivity(intent);
                }
            });
        }else {
            recyclerView.setAdapter(item.getAdapter());
        }

    }
}
