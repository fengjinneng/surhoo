package com.surhoo.sh.order.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.address.EditAddresActivity;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.common.dialog.MyDialogFragment;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.order.OrderEvaluationActivity;
import com.surhoo.sh.order.adapter.MyOrderListAdapter;
import com.surhoo.sh.order.bean.OrderListBean;
import com.surhoo.sh.order.present.MyOrderListPresent;
import com.surhoo.sh.order.present.MyOrderListPresentImpl;
import com.surhoo.sh.order.view.MyOrderListView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderListFragment extends BaseFragment implements MyOrderListView {


    private static final String ORDERSTATUS = "orderStatus";
    @BindView(R.id.fragment_order_list_recyclerView)
    RecyclerView fragmentOrderListRecyclerView;

    private int orderStatus;

    private MyOrderListPresent myOrderListPresent;
    private MyOrderListAdapter myOrderListAdapter;

    private int pageIndex = 1;


    public OrderListFragment() {
    }


    public static OrderListFragment newInstance(int orderStatus) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle args = new Bundle();
        args.putInt("orderStatus", orderStatus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderStatus = getArguments().getInt(ORDERSTATUS);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return getLayoutInflater().inflate(R.layout.fragment_order_list, container, false);
    }

    @Override
    public void init() {
        myOrderListPresent = new MyOrderListPresentImpl();
        myOrderListPresent.bindView(getActivity(), this);

        myOrderListAdapter = new MyOrderListAdapter(R.layout.item_my_orde_ist, null);

        fragmentOrderListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentOrderListRecyclerView.setAdapter(myOrderListAdapter);

        myOrderListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();
            }
        }, fragmentOrderListRecyclerView);


        myOrderListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderListBean orderListBean = (OrderListBean) adapter.getData().get(position);

                switch (view.getId()) {
                    case R.id.item_my_order_list_button1:
                        switch (orderListBean.getOrderStatus()) {
                            //待支付
                            case 1:
                                myOrderListPresent.cancelOrder(orderListBean.getId(), position);
                                break;
                            //已发货(待收货)
                            case 4:
                                ToastUtils.showShort("查看物流");
                                break;
                        }
                        break;

                    case R.id.item_my_order_list_button2:
                        switch (orderListBean.getOrderStatus()) {
                            //待支付
                            case 1:
                                ToastUtils.showShort("立即付款");
                                break;
                            //已取消
                            case 3:
                                //已完成
                            case 7:
                                //8 :订单关闭
                            case 8:
                                MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("确定要删除此订单吗?");
                                myDialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                                    @Override
                                    public void onConfirmClick() {
                                        myDialogFragment.dismiss();
                                        myOrderListPresent.deleteOrder(orderListBean.getId(), position);
                                    }
                                });
                                myDialogFragment.setOnCancelClickListener(new MyDialogFragment.onCancelClickListener() {
                                    @Override
                                    public void onCancelClick() {
                                        myDialogFragment.dismiss();
                                    }
                                });

                                myDialogFragment.show(getFragmentManager(), "dialog");

                                break;
                            //已发货(待收货)
                            case 4:
                                ToastUtils.showShort("确认收货");
                                break;
                            //已收货(待评价)
                            case 6:
                                Intent orderEvaluationIntent  = new Intent(getActivity(),OrderEvaluationActivity.class);
                                orderEvaluationIntent.putExtra("orderListBean",orderListBean);
                                ActivityUtils.startActivity(orderEvaluationIntent);
                                break;
                        }
                        break;
                }
            }
        });

    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void requestData() {
        myOrderListPresent.getOrderInfo(10, pageIndex, orderStatus);
    }

    @Override
    public void firstInEmpty() {

    }

    @Override
    public void loadEnd() {
        myOrderListAdapter.loadMoreEnd();
    }

    @Override
    public void refresh(List list) {

        myOrderListAdapter.setNewData(list);
        myOrderListAdapter.loadMoreComplete();

    }

    @Override
    public void loadData(List list) {

        myOrderListAdapter.addData(list);
        myOrderListAdapter.loadMoreComplete();

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {

        switch (bean.getCode()) {
            case EventBusMessageBean.Order.cancleOrderSuccess:
                int param = bean.getParam();
                OrderListBean obj = (OrderListBean) bean.getObj();
                if (orderStatus == 1) {
                    myOrderListAdapter.getData().remove(param);
                } else {
                    obj.setOrderStatus(3);
                }
                myOrderListAdapter.notifyDataSetChanged();

                break;
        }
    }

    @Override
    public void getDeleteOrderResult(int position) {
        myOrderListAdapter.getData().remove(position);
        myOrderListAdapter.notifyDataSetChanged();

    }

    @Override
    public void getCancelOrderResult(int position) {
        EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Order.cancleOrderSuccess,position,myOrderListAdapter.getData().get(position)));

    }
}
