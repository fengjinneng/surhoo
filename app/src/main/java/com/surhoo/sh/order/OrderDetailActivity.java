package com.surhoo.sh.order;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.bean.order.response.OrderDetailReturnBean;
import com.surhoo.sh.bean.order.response.PayOrderSuccessBean;
import com.surhoo.sh.common.dialog.MyDialogFragment;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.view.GoodsListActivity;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.invoice.InvoiceDetailActivity;
import com.surhoo.sh.order.adapter.OrderDetailAdapter;
import com.surhoo.sh.order.present.OrderDetailPresent;
import com.surhoo.sh.order.present.impl.OrderDetailPresentImpl;
import com.surhoo.sh.order.view.OrderDetailView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity implements OrderDetailView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_order_detail_status)
    TextView activityOrderDetailStatus;
    @BindView(R.id.activity_order_detail_status_describe)
    TextView activityOrderDetailStatusDescribe;
    @BindView(R.id.activity_order_confirm_address_name)
    TextView activityOrderConfirmAddressName;
    @BindView(R.id.activity_order_detail_address_detail)
    TextView activityOrderDetailAddressDetail;
    @BindView(R.id.activity_order_detail_address_phone)
    TextView activityOrderDetailAddressPhone;
    @BindView(R.id.activity_order_detail_address_name)
    TextView activityOrderDetailAddressName;
    @BindView(R.id.activity_order_detail_address_layout)
    ConstraintLayout activityOrderDetailAddressLayout;
    @BindView(R.id.activity_order_detail_recyclerView)
    RecyclerView activityOrderDetailRecyclerView;
    @BindView(R.id.activity_order_detail_goods_payStatus)
    TextView activityOrderDetailGoodsPayStatus;
    @BindView(R.id.activity_order_detail_goods_goodsTotalPrice)
    TextView activityOrderDetailGoodsGoodsTotalPrice;
    @BindView(R.id.activity_order_detail_goods_postage)
    TextView activityOrderDetailGoodsPostage;
    @BindView(R.id.activity_order_detail_goods_totalPrice)
    TextView activityOrderDetailGoodsTotalPrice;
    @BindView(R.id.activity_order_detail_goods_layout)
    ConstraintLayout activityOrderDetailGoodsLayout;
    @BindView(R.id.activity_order_detail_invoice_type)
    TextView activityOrderDetailInvoiceType;
    @BindView(R.id.activity_order_detail_invoice_title)
    TextView activityOrderDetailInvoiceTitle;
    @BindView(R.id.activity_order_detail_invoice_content)
    TextView activityOrderDetailInvoiceContent;
    @BindView(R.id.activity_order_detail_invoice_check)
    TextView activityOrderDetailInvoiceCheck;
    @BindView(R.id.activity_order_detail_invoice_layout)
    ConstraintLayout activityOrderDetailInvoiceLayout;
    @BindView(R.id.activity_order_detail_postage_number)
    TextView activityOrderDetailPostageNumber;
    @BindView(R.id.activity_order_detail_postage_check)
    TextView activityOrderDetailPostageCheck;
    @BindView(R.id.activity_order_detail_postage_layout)
    ConstraintLayout activityOrderDetailPostageLayout;
    @BindView(R.id.activity_order_detail_order_number)
    TextView activityOrderDetailOrderNumber;
    @BindView(R.id.activity_order_detail_order_number_copy)
    TextView activityOrderDetailOrderNumberCopy;
    @BindView(R.id.activity_order_detail_order_payOrderTime)
    TextView activityOrderDetailOrderPayOrderTime;
    @BindView(R.id.activity_order_detail_order_sendGoodsTime)
    TextView activityOrderDetailOrderSendGoodsTime;
    @BindView(R.id.activity_order_detail_order_receiveGoodsTime)
    TextView activityOrderDetailOrderReceiveGoodsTime;
    @BindView(R.id.activity_order_detail_order_evaluationTime)
    TextView activityOrderDetailOrderEvaluationTime;
    @BindView(R.id.activity_order_detail_order_layout)
    ConstraintLayout activityOrderDetailOrderLayout;
    @BindView(R.id.activity_order_detail_button2)
    TextView activityOrderDetailButton2;
    @BindView(R.id.activity_order_detail_button1)
    TextView activityOrderDetailButton1;
    @BindView(R.id.activity_order_detail_order_payOrderTime_layout)
    ConstraintLayout activityOrderDetailOrderPayOrderTimeLayout;
    @BindView(R.id.activity_order_detail_order_sendGoodsTime_layout)
    ConstraintLayout activityOrderDetailOrderSendGoodsTimeLayout;
    @BindView(R.id.activity_order_detail_order_receiveGoodsTime_layout)
    ConstraintLayout activityOrderDetailOrderReceiveGoodsTimeLayout;
    @BindView(R.id.activity_order_detail_order_evaluationTime_layout)
    ConstraintLayout activityOrderDetailOrderEvaluationTimeLayout;
    @BindView(R.id.activity_order_detail_order_cancelTime)
    TextView activityOrderDetailOrderCancelTime;
    @BindView(R.id.activity_order_detail_order_cancelTime_layout)
    ConstraintLayout activityOrderDetailOrderCancelTimeLayout;


    private OrderDetailPresent orderDetailPresent;

    private OrderDetailAdapter orderDetailAdapter;

    private int id;

    @Override
    public int getContentView() {
        return R.layout.activity_order_detail;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("订单详情");
    }

    @Override
    public void initData() {

        id = getIntent().getIntExtra("id", id);
        orderDetailPresent = new OrderDetailPresentImpl();
        orderDetailPresent.bindView(this, this);

        orderDetailAdapter = new OrderDetailAdapter(R.layout.item_order_confirm, null);

        activityOrderDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        activityOrderDetailRecyclerView.setAdapter(orderDetailAdapter);

        orderDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    OrderDetailReturnBean.OrderDataListBean orderDataListBean = (OrderDetailReturnBean.OrderDataListBean) adapter.getData().get(position);
                    Intent i = new Intent(OrderDetailActivity.this, GoodsDetailActivity.class);
                    i.putExtra("id", orderDataListBean.getGoodsId());
                    ActivityUtils.startActivity(i);
                }
            }
        });
    }

    @Override
    public void requestData() {
        orderDetailPresent.getOrderDetail(id);
    }

    @OnClick({R.id.toolbar_layout_back, R.id.activity_order_detail_postage_check,
            R.id.activity_order_detail_order_number_copy, R.id.activity_order_detail_button1
            , R.id.activity_order_detail_button2,R.id.activity_order_detail_invoice_check})
    public void onViewClicked(View view) {
        if (ClickUtil.isFastClick()) {

            switch (view.getId()) {
                case R.id.toolbar_layout_back:
                    finish();
                    break;
                case R.id.activity_order_detail_postage_check:
                    Intent expressIntent = new Intent(this, ExpressActivity.class);
                    expressIntent.putExtra("expressCode", orderDetailReturnBean.getExpressCode());
                    expressIntent.putExtra("expressNumber", orderDetailReturnBean.getExpressNumber());
                    ActivityUtils.startActivity(expressIntent);
                    break;
                case R.id.activity_order_detail_invoice_check:

                    Intent invoiceIntent = new Intent(this, InvoiceDetailActivity.class);
                    invoiceIntent.putExtra("orderDetailReturnBean",orderDetailReturnBean);
                    ActivityUtils.startActivity(invoiceIntent);

                    break;
                case R.id.activity_order_detail_order_number_copy:
                    //获取剪贴板管理器：
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    // 创建普通字符型ClipData
                    ClipData mClipData = ClipData.newPlainText("Label", activityOrderDetailOrderNumber.getText().toString());
                    // 将ClipData内容放到系统剪贴板里。
                    cm.setPrimaryClip(mClipData);
                    ToastUtils.showShort("复制成功！");
                    break;
                case R.id.activity_order_detail_button1:

                    if (ObjectUtils.isEmpty(orderDetailReturnBean)) {
                        return;
                    }

                    switch (orderDetailReturnBean.getOrderStatus()) {
                        //取消订单
                        case 1:
                            MyDialogFragment cancelDialogFragment = MyDialogFragment.newInstance("确定取消订单吗？");
                            cancelDialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                                @Override
                                public void onConfirmClick() {
                                    cancelDialogFragment.dismiss();
                                    orderDetailPresent.cancelOrder(CANCELORDER, id);
                                }
                            });
                            cancelDialogFragment.setOnCancelClickListener(new MyDialogFragment.onCancelClickListener() {
                                @Override
                                public void onCancelClick() {
                                    cancelDialogFragment.dismiss();
                                }
                            });
                            cancelDialogFragment.show(getSupportFragmentManager(), "cancelDialogFragment");
                            break;

                        case 3:
                        case 7:
                        case 8:
                            MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("确定要删除此订单吗?");
                            myDialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                                @Override
                                public void onConfirmClick() {
                                    myDialogFragment.dismiss();
                                    orderDetailPresent.deleteOrder(DELETEORDER, id);
                                }
                            });
                            myDialogFragment.setOnCancelClickListener(new MyDialogFragment.onCancelClickListener() {
                                @Override
                                public void onCancelClick() {
                                    myDialogFragment.dismiss();
                                }
                            });

                            myDialogFragment.show(getSupportFragmentManager(), "dialog");
                            break;
                    }
                    break;

                case R.id.activity_order_detail_button2:

                    if (ObjectUtils.isEmpty(orderDetailReturnBean)) {
                        return;
                    }
                    orderTotalPrice = orderDetailReturnBean.getOrderAmount();

                    switch (orderDetailReturnBean.getOrderStatus()) {
                        //待支付
                        case 1:
                            payOrderTime = orderDetailReturnBean.getOrderTime();
                            orderDetailPresent.payUseOrderNo(PAYUSEORDERNO, orderDetailReturnBean.getOrderNo());
                            break;
                        //已发货(待收货)
                        case 4:
                            MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("确认您已经收货?");
                            myDialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                                @Override
                                public void onConfirmClick() {
                                    myDialogFragment.dismiss();
                                    orderDetailPresent.confirmOrder(CONFIRMORDER, id);
                                }
                            });
                            myDialogFragment.setOnCancelClickListener(new MyDialogFragment.onCancelClickListener() {
                                @Override
                                public void onCancelClick() {
                                    myDialogFragment.dismiss();
                                }
                            });

                            myDialogFragment.show(getSupportFragmentManager(), "confirmOrder");
                            break;

                        //已收货(待评价)
                        case 6:
                            Intent orderEvaluationIntent = new Intent(this, OrderEvaluationActivity.class);
                            orderEvaluationIntent.putExtra("orderListBean", orderDetailReturnBean);
                            ActivityUtils.startActivity(orderEvaluationIntent);
                            break;
                    }

                    break;
            }
        }
    }


    private static final String CANCELORDER = "cancelOrder";
    private static final String DELETEORDER = "deleteOrder";
    private static final String PAYUSEORDERNO = "payUseOrderNo";
    private static final String CONFIRMORDER = "confirmOrder";


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!ObjectUtils.isEmpty(timer)) {
            timer.cancel();
        }
    }

    CountDownTimer timer;

    private OrderDetailReturnBean orderDetailReturnBean;

    @Override
    public void showBeanData(OrderDetailReturnBean orderDetailReturnBean) {

        this.orderDetailReturnBean = orderDetailReturnBean;

        orderDetailAdapter.setNewData(orderDetailReturnBean.getOrderDataList());

        Integer orderStatus = orderDetailReturnBean.getOrderStatus();

        switch (orderStatus) {
            //待支付
            case 1:
                activityOrderDetailStatus.setText("待付款");
//              long orderStopTime = orderDetailReturnBean.getOrderStopTime();
                activityOrderDetailButton1.setText("取消订单");
                activityOrderDetailButton2.setText("立即付款");
                activityOrderDetailButton1.setVisibility(View.VISIBLE);
                activityOrderDetailButton2.setVisibility(View.VISIBLE);
                activityOrderDetailStatusDescribe.setVisibility(View.VISIBLE);

                timer = new CountDownTimer(orderDetailReturnBean.getOrderStopTime() - System.currentTimeMillis(), 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (!ObjectUtils.isEmpty(activityOrderDetailStatusDescribe)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                            activityOrderDetailStatusDescribe.setText("剩余" + sdf.format(new Date(millisUntilFinished)) + "后自动关闭");
                        }
                    }
                    @Override
                    public void onFinish() {
                        requestData();
                    }
                }.start();

                break;
            //已支付(待发货)
            case 2:
                activityOrderDetailStatusDescribe.setVisibility(View.GONE);
                activityOrderDetailStatus.setText("待发货");
                activityOrderDetailButton1.setVisibility(View.GONE);
                activityOrderDetailButton2.setVisibility(View.GONE);
                break;
            //已取消
            case 3:
                activityOrderDetailStatusDescribe.setVisibility(View.GONE);
                activityOrderDetailStatus.setText("已取消");
                activityOrderDetailButton1.setVisibility(View.VISIBLE);
                activityOrderDetailButton1.setText("删除订单");
                activityOrderDetailButton2.setVisibility(View.GONE);
                activityOrderDetailOrderCancelTimeLayout.setVisibility(View.VISIBLE);
                activityOrderDetailOrderCancelTime.setText(orderDetailReturnBean.getCancelAt());
                break;
            //已发货(待收货)
            case 4:
                activityOrderDetailStatus.setText("待收货");
                activityOrderDetailButton2.setVisibility(View.VISIBLE);
                activityOrderDetailButton2.setText("确认收货");
                activityOrderDetailButton1.setVisibility(View.GONE);
                break;
            //已收货(待评价)
            case 6:
                activityOrderDetailStatus.setText("待评价");
                activityOrderDetailButton2.setVisibility(View.VISIBLE);
                activityOrderDetailButton2.setText("立即评价");
                activityOrderDetailButton1.setVisibility(View.GONE);
                break;
            //已完成
            case 7:
                activityOrderDetailStatus.setText("已完成");
                activityOrderDetailButton1.setVisibility(View.VISIBLE);
                activityOrderDetailButton1.setText("删除订单");
                activityOrderDetailButton2.setVisibility(View.GONE);
                break;
            case 8:
                //8 :订单关闭
                activityOrderDetailStatus.setText("已关闭");
                activityOrderDetailButton1.setVisibility(View.VISIBLE);
                activityOrderDetailButton1.setText("删除订单");
                activityOrderDetailButton2.setVisibility(View.GONE);
                break;
        }

        //地址信息
        activityOrderDetailAddressName.setText(orderDetailReturnBean.getReceiptPerson());
        activityOrderDetailAddressPhone.setText(orderDetailReturnBean.getReceiptPhone());
        activityOrderDetailAddressDetail.setText(orderDetailReturnBean.getReceiptAddr());

        //商品信息
        activityOrderDetailGoodsGoodsTotalPrice.setText(orderDetailReturnBean.getGoodsAmount());
        activityOrderDetailGoodsTotalPrice.setText(orderDetailReturnBean.getOrderAmount());
        activityOrderDetailGoodsPostage.setText(orderDetailReturnBean.getOrderFreight());


        //发票信息
        if (orderDetailReturnBean.getIsInvoice() == 1) {
            activityOrderDetailInvoiceLayout.setVisibility(View.VISIBLE);

            if (orderDetailReturnBean.getInvoiceType() == 1) {
                //普通发票
                if (orderDetailReturnBean.getNormalType() == 1) {
                    //个人
                    activityOrderDetailInvoiceType.setText("个人普通发票");
                }
                if (orderDetailReturnBean.getNormalType() == 2) {
                    //企业
                    activityOrderDetailInvoiceType.setText("企业普通发票");
                }
            }

            if (orderDetailReturnBean.getInvoiceType() == 2) {
                //增值税
                activityOrderDetailInvoiceType.setText("增值税专用发票");
            }
            activityOrderDetailInvoiceTitle.setText(orderDetailReturnBean.getInvoiceTitle());
            activityOrderDetailInvoiceContent.setText(orderDetailReturnBean.getInvoiceContent());
        }


        //快递信息

        if (!StringUtils.isEmpty(orderDetailReturnBean.getExpressName())
                && !StringUtils.isEmpty(orderDetailReturnBean.getExpressNumber())) {
            activityOrderDetailPostageLayout.setVisibility(View.VISIBLE);
            activityOrderDetailPostageNumber.setText(orderDetailReturnBean.getExpressName() + ":" + orderDetailReturnBean.getExpressNumber());
        }


        //订单信息

        activityOrderDetailOrderNumber.setText(orderDetailReturnBean.getOrderNo());
        activityOrderDetailOrderPayOrderTime.setText(orderDetailReturnBean.getOrderTime());
//        activityOrderDetailOrderSendGoodsTime.setText(orderDetailReturnBean.get);
//        activityOrderDetailOrderReceiveGoodsTime.setText("");
//        activityOrderDetailOrderEvaluationTime.setText("");


    }


    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {
        super.onRecevieMessage(bean);

        switch (bean.getCode()){
            case EventBusMessageBean.Order.payOrderSuccess:
                requestData();
                break;
        }
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    private String orderTotalPrice;
    //下单时间
    private String payOrderTime;

    @Override
    public void showStringData(String requestTag, String s) {
        if (StringUtils.equals(CANCELORDER, requestTag)) {
            requestData();
            EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Order.cancleOrderSuccess));
        }

        if (StringUtils.equals(DELETEORDER, requestTag)) {
            EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Order.deleteOrder));
            finish();
        }

        if (StringUtils.equals(CONFIRMORDER, requestTag)) {
            EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Order.confirmOrder));
            requestData();
        }

        if (StringUtils.equals(PAYUSEORDERNO, requestTag)) {
            try {
                PayOrderSuccessBean payOrderSuccessBean = JSONObject.parseObject(s, PayOrderSuccessBean.class);
                Intent intent = new Intent(this, CheckStandActivity.class);
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
