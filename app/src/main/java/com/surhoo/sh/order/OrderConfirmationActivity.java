package com.surhoo.sh.order;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.address.AddressActivity;
import com.surhoo.sh.address.bean.AddressBean;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.bean.order.request.GetPayInfoBean;
import com.surhoo.sh.bean.order.request.GetPostageBean;
import com.surhoo.sh.bean.order.response.OrderInfoBean;
import com.surhoo.sh.bean.order.response.PayOrderSuccessBean;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.NumberUtil;
import com.surhoo.sh.invoice.InvoiceListActivity;
import com.surhoo.sh.order.adapter.ConfirmOrderAdapter;
import com.surhoo.sh.bean.order.request.PayOrderBean;
import com.surhoo.sh.order.present.IOrderConfirmPresent;
import com.surhoo.sh.order.present.impl.OrderConfirmPresentImpl;
import com.surhoo.sh.order.view.OrderConfirmView;
import com.surhoo.sh.shoppingcart.ShoppingCartBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class OrderConfirmationActivity extends BaseActivity implements OrderConfirmView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_order_confirm_address_name)
    TextView activityOrderConfirmAddressName;
    @BindView(R.id.activity_order_confirm_address_detail)
    TextView activityOrderConfirmAddressDetail;
    @BindView(R.id.activity_order_confirm_address_phone)
    TextView activityOrderConfirmAddressPhone;
    @BindView(R.id.activity_order_confirm_address_layout)
    ConstraintLayout activityOrderConfirmAddressLayout;
    @BindView(R.id.activity_order_confirm_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.activity_order_confirm_shopName)
    TextView activityOrderConfirmShopName;
    @BindView(R.id.activity_order_confirm_total_price)
    TextView activityOrderConfirmTotalPrice;
    @BindView(R.id.activity_order_confirm_postage)
    TextView activityOrderConfirmPostage;
    @BindView(R.id.activity_order_confirm_invoice)
    TextView activityOrderConfirmInvoice;
    @BindView(R.id.activity_order_confirm_remarks)
    EditText activityOrderConfirmRemarks;
    @BindView(R.id.activity_order_confirm_pay_switch)
    Switch activityOrderConfirmPaySwitch;
    @BindView(R.id.activity_order_confirm_order_price)
    TextView activityOrderConfirmOrderPrice;
    @BindView(R.id.activity_order_confirm_order_pay)
    TextView activityOrderConfirmOrderPay;

    private IOrderConfirmPresent orderConfirmPresent;

    private ConfirmOrderAdapter adapter;

    private List<GetPayInfoBean> getPayInfoBeans = new ArrayList<>();

    private PayOrderBean payOrderBean = new PayOrderBean();


    @Override
    public int getContentView() {
        return R.layout.activity_order_confirmation;
    }
    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("确认订单");
        orderConfirmPresent = new OrderConfirmPresentImpl();
        orderConfirmPresent.bindView(this, this);
    }

    @Override
    public void initData() {

        ArrayList<ShoppingCartBean.CarGoodsListBean> goodsListBeans  = getIntent().getParcelableArrayListExtra("data");

        for (int i = 0; i < goodsListBeans.size(); i++) {
            GetPayInfoBean getPayInfoBean = new GetPayInfoBean();
            getPayInfoBean.setGoodsNum(goodsListBeans.get(i).getGoodsNum());
            getPayInfoBean.setId(goodsListBeans.get(i).getGoodsId());
            getPayInfoBean.setSkuId(goodsListBeans.get(i).getSkuId());
            getPayInfoBeans.add(getPayInfoBean);
        }

            adapter = new ConfirmOrderAdapter(R.layout.item_order_confirm, null);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

    }

    @Override
    public void requestData() {
        orderConfirmPresent.getOrderInfo(getPayInfoBeans);
    }



    @OnClick({R.id.toolbar_layout_back, R.id.activity_order_confirm_address_layout,
            R.id.activity_order_confirm_invoice,R.id.activity_order_confirm_order_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;
            case R.id.activity_order_confirm_address_layout:
                Intent addressIntent = new Intent(this, AddressActivity.class);
                addressIntent.putExtra("from", "order");
                ActivityUtils.startActivity(addressIntent);
                break;
            case R.id.activity_order_confirm_invoice:

                Intent intent = new Intent(this, InvoiceListActivity.class);
                intent.putExtra("from", "order");
                ActivityUtils.startActivity(intent);

                break;
            case R.id.activity_order_confirm_order_pay:

                ArrayList<PayOrderBean.OrderDataListBean> orderDataListBeans = new ArrayList<>();

                for (int i = 0; i < getPayInfoBeans.size(); i++) {
                    PayOrderBean.OrderDataListBean orderDataListBean = new PayOrderBean.OrderDataListBean();
                    orderDataListBean.setId(getPayInfoBeans.get(i).getId());
                    orderDataListBean.setGoodsNum(getPayInfoBeans.get(i).getGoodsNum());
                    orderDataListBean.setSkuId(getPayInfoBeans.get(i).getSkuId());
                    orderDataListBeans.add(orderDataListBean);
                }

                payOrderBean.setOrderDataList(orderDataListBeans);

                payOrderBean.setOrderAmount(activityOrderConfirmOrderPrice.getText().toString());

                payOrderBean.setOrderRemarks(activityOrderConfirmRemarks.getText().toString());

                if(ObjectUtils.isEmpty(upLoadAddressId)){
                    ToastUtils.showShort("请添加地址信息!");
                    return;
                }else {
                    payOrderBean.setShipId(upLoadAddressId);
                }

                if(ObjectUtils.isEmpty(upLoadInvocieId)){
                    payOrderBean.setIsInvoice(0);
                }else {
                    payOrderBean.setIsInvoice(1);
                    payOrderBean.setInvoiceId(upLoadInvocieId);
                }

                payOrderBean.setCarPay(true);
                payOrderBean.setOrderSource(2);

                payOrderBean.setOrderFreight(activityOrderConfirmPostage.getText().toString());

                orderConfirmPresent.payOrder(PAYORDER,payOrderBean);

                break;
        }
    }



    private Integer upLoadInvocieId;
    private Integer upLoadAddressId;

    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {
        switch (bean.getCode()) {
            case EventBusMessageBean.Invoice.choiceInvoice:
                upLoadInvocieId = bean.getParam();
                activityOrderConfirmInvoice.setText(bean.getMessage());
                break;

            case EventBusMessageBean.Address.choiceAddress:
                AddressBean addressBean = (AddressBean) bean.getObj();
                activityOrderConfirmAddressName.setText(addressBean.getName());
                activityOrderConfirmAddressPhone.setText(addressBean.getPhone());
                activityOrderConfirmAddressDetail.setText(
                        addressBean.getProvinceName() + " " + addressBean.
                                getCityName() + " " + addressBean.getDistrictName()
                                + " " + addressBean.getAddress());
                upLoadAddressId = addressBean.getId();

                queryPostage(addressBean.getId());
                break;
        }
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    private double goodsTatalPrice;
    private OrderInfoBean orderInfoBean;

    @Override
    public void showBeanData(OrderInfoBean orderInfoBean) {
        this.orderInfoBean = orderInfoBean;

        activityOrderConfirmShopName.setText(orderInfoBean.getGoodsList().get(0).getShopName());
        adapter.setNewData(orderInfoBean.getGoodsList().get(0).getGoodsList());

        for (int i = 0; i < orderInfoBean.getGoodsList().size(); i++) {

            for (int j = 0; j < orderInfoBean.getGoodsList().get(i).getGoodsList().size(); j++) {
                goodsTatalPrice += orderInfoBean.getGoodsList().get(i).getGoodsList().get(j).getGoodsPrice()
                        *orderInfoBean.getGoodsList().get(i).getGoodsList().get(j).getGoodsNum();
            }
        }

        activityOrderConfirmTotalPrice.setText(NumberUtil.getTwoPointString(goodsTatalPrice));


        OrderInfoBean.AddressBean address = orderInfoBean.getAddress();

        upLoadAddressId = address.getId();

        activityOrderConfirmAddressName.setText(address.getName());
        activityOrderConfirmAddressPhone.setText(address.getPhone());
        activityOrderConfirmAddressDetail.setText(
                address.getProvinceName() + " " + address.
                        getCityName() + " " + address.getDistrictName()
                        + " " + address.getAddress());
        queryPostage(orderInfoBean.getAddress().getId());
    }

    private void queryPostage(int addressId){
        GetPostageBean getPostageBean = new GetPostageBean();
        getPostageBean.setShipId(addressId);

        ArrayList<GetPostageBean.GoodsListBean> list = new ArrayList();
        for (int i = 0; i < orderInfoBean.getGoodsList().get(0).getGoodsList().size(); i++) {
            GetPostageBean.GoodsListBean goodsListBean = new GetPostageBean.GoodsListBean();
            goodsListBean.setId(orderInfoBean.getGoodsList().get(0).getGoodsList().get(i).getId());
            goodsListBean.setGoodsNum(orderInfoBean.getGoodsList().get(0).getGoodsList().get(i).getGoodsNum());
            list.add(goodsListBean);
        }

        getPostageBean.setGoodsList(list);

        orderConfirmPresent.getPostage(GETPOSTAGE,getPostageBean);
    }


    private static final String GETPOSTAGE = "getPostage";
    private static final String PAYORDER = "payOrder";

    @Override
    public void showStringData(String requestTag, String s) {
        if(StringUtils.equals(GETPOSTAGE,requestTag)){
            activityOrderConfirmPostage.setText(s);
            activityOrderConfirmOrderPrice.setText(NumberUtil.getTwoPointString(goodsTatalPrice+Double.valueOf(s)));

        }

        if(StringUtils.equals(PAYORDER,requestTag)){
            EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Order.payOrderSuccess));
            PayOrderSuccessBean payOrderSuccessBean = JSONObject.parseObject(s, PayOrderSuccessBean.class);
            Intent intent = new Intent(this,CheckStandActivity.class);
            intent.putExtra("payOrderSuccessBean",payOrderSuccessBean);
            intent.putExtra("orderTotalPrice",activityOrderConfirmOrderPrice.getText().toString());
            ActivityUtils.startActivity(intent);
            finish();
        }
    }
}
