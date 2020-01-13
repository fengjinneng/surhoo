package com.surhoo.sh.order.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.bean.order.response.OrderDetailReturnBean;
import com.surhoo.sh.bean.order.response.PayOrderSuccessBean;
import com.surhoo.sh.common.dialog.MyDialogFragment;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.order.CheckStandActivity;
import com.surhoo.sh.order.ExpressActivity;
import com.surhoo.sh.order.OrderDetailActivity;
import com.surhoo.sh.order.OrderEvaluationActivity;
import com.surhoo.sh.order.adapter.MyOrderListAdapter;
import com.surhoo.sh.order.present.MyOrderListPresent;
import com.surhoo.sh.order.present.impl.MyOrderListPresentImpl;
import com.surhoo.sh.order.view.MyOrderListView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class OrderListFragment extends BaseFragment implements MyOrderListView {


    private static final String GETORDERLIST = "getOrderList";
    private static final String CANCELORDER = "cancelOrder";
    private static final String DELETEORDER = "deleteOrder";

    private static final String ORDERSTATUS = "orderStatus";

    private static final String PAYUSEORDERNO = "payUseOrderNo";
    private static final String CONFIRMORDER = "confirmOrder";

    @BindView(R.id.fragment_order_list_recyclerView)
    RecyclerView fragmentOrderListRecyclerView;

    private int orderStatus;

    private MyOrderListPresent myOrderListPresent;
    private MyOrderListAdapter myOrderListAdapter;

    private int pageIndex;

    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;

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

        isCreate = true;
    }

    @Override
    public View getView(ViewGroup container) {

        return getLayoutInflater().inflate(R.layout.fragment_order_list, container, false);
    }


    private void initLoadingView() {
        loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.load_data_loading_view, null);
        loadingEmptyView = LayoutInflater.from(getActivity()).inflate(R.layout.load_data_empty_view, null);
        ImageView emptyImg = (ImageView) loadingEmptyView.findViewById(R.id.load_data_empty_view_img);
        emptyImg.setImageDrawable(getResources().getDrawable(R.mipmap.invoice_empty_img));
        TextView emptyText = (TextView) loadingEmptyView.findViewById(R.id.load_data_empty_view_content);
        emptyText.setText("暂无相关订单");

        loadingErrorView = LayoutInflater.from(getActivity()).inflate(R.layout.load_data_error_view, null);

        TextView errorText = (TextView) loadingErrorView.findViewById(R.id.load_data_error_view_retry);
        errorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    myOrderListAdapter.setEmptyView(loadingView);
                    requestData();
                }
            }
        });
    }

    @Override
    public void init() {

        pageIndex = 1;
        initLoadingView();
        myOrderListPresent = new MyOrderListPresentImpl();
        myOrderListPresent.bindView(getActivity(), this);
        initRecyclerViewData();

    }

    private boolean isCreate;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isCreate) {
            LogUtils.v("asadsadsadsadas", "isCreate");
        }
    }

    @Override
    public void onResume() {

        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {

            if (!this.isHidden() && this.getUserVisibleHint()) {
                LogUtils.v("asadsadsadsadas", "onResume");
            }
        }

    }

    private void initRecyclerViewData() {
        myOrderListAdapter = new MyOrderListAdapter(R.layout.item_my_orde_ist, null);

        myOrderListAdapter.setEmptyView(loadingView);

        fragmentOrderListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentOrderListRecyclerView.setAdapter(myOrderListAdapter);

        myOrderListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();
            }
        }, fragmentOrderListRecyclerView);


        myOrderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    OrderDetailReturnBean orderListBean = (OrderDetailReturnBean) adapter.getData().get(position);
                    Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                    intent.putExtra("id", orderListBean.getId());
                    ActivityUtils.startActivity(intent);
                }
            }
        });


        myOrderListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (ClickUtil.isFastClick()) {

                    checkedPosition = position;

                    OrderDetailReturnBean orderListBean = (OrderDetailReturnBean) adapter.getData().get(position);
                    orderTotalPrice = orderListBean.getOrderAmount();
                    switch (view.getId()) {
                        case R.id.item_my_order_list_button1:
                            switch (orderListBean.getOrderStatus()) {
                                //待支付
                                case 1:
                                    MyDialogFragment cancelDialogFragment = MyDialogFragment.newInstance("确定取消订单吗？");
                                    cancelDialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                                        @Override
                                        public void onConfirmClick() {
                                            cancelDialogFragment.dismiss();
                                            myOrderListPresent.cancelOrder(CANCELORDER, orderListBean.getId());
                                        }
                                    });
                                    cancelDialogFragment.setOnCancelClickListener(new MyDialogFragment.onCancelClickListener() {
                                        @Override
                                        public void onCancelClick() {
                                            cancelDialogFragment.dismiss();
                                        }
                                    });
                                    cancelDialogFragment.show(getFragmentManager(), "cancelDialogFragment");

                                    break;
                                //已发货(待收货)
                                case 4:
                                case 6:
                                    Intent expressIntent = new Intent(getActivity(), ExpressActivity.class);
                                    expressIntent.putExtra("expressCode", orderListBean.getExpressCode());
                                    expressIntent.putExtra("expressNumber", orderListBean.getExpressNumber());
                                    ActivityUtils.startActivity(expressIntent);

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
                                            myOrderListPresent.deleteOrder(DELETEORDER, orderListBean.getId());
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
                            }
                            break;

                        case R.id.item_my_order_list_button2:
                            switch (orderListBean.getOrderStatus()) {
                                //待支付
                                case 1:
                                    payOrderTime = orderListBean.getOrderTime();
                                    myOrderListPresent.payUseOrderNo(PAYUSEORDERNO, orderListBean.getOrderNo());
                                    break;
                                //已发货(待收货)
                                case 4:
                                    MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("确认您已经收货?");
                                    myDialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                                        @Override
                                        public void onConfirmClick() {
                                            myDialogFragment.dismiss();
                                            myOrderListPresent.confirmOrder(CONFIRMORDER, orderListBean.getId());
                                        }
                                    });
                                    myDialogFragment.setOnCancelClickListener(new MyDialogFragment.onCancelClickListener() {
                                        @Override
                                        public void onCancelClick() {
                                            myDialogFragment.dismiss();
                                        }
                                    });

                                    myDialogFragment.show(getFragmentManager(), "confirmOrder");

                                    break;

                                //已收货(待评价)
                                case 6:
                                    Intent orderEvaluationIntent = new Intent(getActivity(), OrderEvaluationActivity.class);
                                    orderEvaluationIntent.putExtra("orderListBean", orderListBean);
                                    ActivityUtils.startActivity(orderEvaluationIntent);
                                    break;
                            }
                            break;
                        case R.id.item_my_order_list_recyclerView:
                            Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                            intent.putExtra("id", orderListBean.getId());
                            ActivityUtils.startActivity(intent);
                            break;
                    }
                }
            }
        });

    }

    private String orderTotalPrice;
    //下单时间
    private String payOrderTime;

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void requestData() {
        myOrderListPresent.getOrderInfo(GETORDERLIST, 20, pageIndex, orderStatus);
    }

    @Override
    public void setHavePageEmptyView() {
        myOrderListAdapter.setNewData(null);

        myOrderListAdapter.setEmptyView(loadingEmptyView);
    }

    @Override
    public void setHavePageErrorView() {
        myOrderListAdapter.setNewData(null);

        myOrderListAdapter.setEmptyView(loadingErrorView);
    }

    @Override
    public void loadDataEnd() {
        myOrderListAdapter.loadMoreEnd();
    }

    @Override
    public void firstLoadData(List list) {

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
            case EventBusMessageBean.Order.payOrderSuccess:

                //待付款fragment

                if (orderStatus == 1 || orderStatus == 0) {
                    requestData();
                }
//                if (orderStatus == 1) {
//                    myOrderListAdapter.remove(checkedPosition);
//                    if (ObjectUtils.isEmpty(myOrderListAdapter.getData())) {
//                        myOrderListAdapter.setEmptyView(loadingEmptyView);
//                    }
//                } else if (orderStatus == 0) {
//                    myOrderListAdapter.getData().get(checkedPosition).setOrderStatus(3);
////                    myOrderListAdapter.notifyItemChanged(checkedPosition);
//                    myOrderListAdapter.notifyDataSetChanged();
//                }

                break;

            case EventBusMessageBean.Order.confirmOrder:
                //待付款fragment

                if (orderStatus == 4 || orderStatus == 0) {
                    requestData();
                }

//                if (orderStatus == 4) {
//                    myOrderListAdapter.remove(checkedPosition);
//                    if (ObjectUtils.isEmpty(myOrderListAdapter.getData())) {
//                        myOrderListAdapter.setEmptyView(loadingEmptyView);
//                    }
//                } else if (orderStatus == 0) {
//                    myOrderListAdapter.getData().get(checkedPosition).setOrderStatus(6);
////                    myOrderListAdapter.notifyItemChanged(checkedPosition);
//                    myOrderListAdapter.notifyDataSetChanged();
//                } else {
//                    myOrderListAdapter.notifyDataSetChanged();
//                }

                break;

            case EventBusMessageBean.Order.deleteOrder:

                requestData();

                break;

        }
    }

    private int checkedPosition = -1;

    @Override
    public void showStringData(String requestTag, String s) {

        if (StringUtils.equals(CANCELORDER, requestTag)) {
            if (checkedPosition != -1) {
                EventBus.getDefault().post(new EventBusMessageBean(
                        EventBusMessageBean.Order.cancleOrderSuccess,
                        checkedPosition));

            }
        }

        if (StringUtils.equals(CONFIRMORDER, requestTag)) {
            if (checkedPosition != -1) {
                EventBus.getDefault().post(new EventBusMessageBean(
                        EventBusMessageBean.Order.confirmOrder,
                        checkedPosition));
            }
        }


        if (StringUtils.equals(DELETEORDER, requestTag)) {
            if (checkedPosition != -1) {
                myOrderListAdapter.getData().remove(checkedPosition);
                myOrderListAdapter.notifyItemRemoved(checkedPosition);
            }
            if (myOrderListAdapter.getData().size() == 0) {
                myOrderListAdapter.setEmptyView(loadingEmptyView);
            }
        }


        if (StringUtils.equals(PAYUSEORDERNO, requestTag)) {
            try {
                PayOrderSuccessBean payOrderSuccessBean = JSONObject.parseObject(s, PayOrderSuccessBean.class);
                Intent intent = new Intent(getActivity(), CheckStandActivity.class);
                intent.putExtra("payOrderSuccessBean", payOrderSuccessBean);
                intent.putExtra("orderTotalPrice", orderTotalPrice);
//                long l = TimeUtils.string2Millis(payOrderTime);
                intent.putExtra("payOrderTime", payOrderTime);
                ActivityUtils.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
