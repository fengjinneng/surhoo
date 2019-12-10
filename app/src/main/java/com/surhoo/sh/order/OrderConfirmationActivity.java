package com.surhoo.sh.order;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.address.AddressActivity;
import com.surhoo.sh.address.AddressBean;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.invoice.InvoiceActivity;
import com.surhoo.sh.order.adapter.ConfirmOrderAdapter;
import com.surhoo.sh.order.bean.RequestPostageBean;
import com.surhoo.sh.order.present.IOrderConfirmPresent;
import com.surhoo.sh.order.present.OrderConfirmPresentImpl;
import com.surhoo.sh.order.view.OrderConfirmView;
import com.surhoo.sh.shoppingcart.ShoppingCartBean;

import java.util.ArrayList;
import java.util.List;

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

    private ArrayList<ShoppingCartBean.CarGoodsListBean> goodsListBeans;
    private String shopName;
    private String goodsTotalPrice;

    private IOrderConfirmPresent orderConfirmPresent;


    private ConfirmOrderAdapter adapter;

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

        goodsListBeans = getIntent().getParcelableArrayListExtra("data");
        shopName = getIntent().getStringExtra("shopName");
        goodsTotalPrice = getIntent().getStringExtra("goodsTotalPrice");

        if (!ObjectUtils.isEmpty(goodsListBeans)) {
            adapter = new ConfirmOrderAdapter(R.layout.item_order_confirm, goodsListBeans);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }

        if (!StringUtils.isEmpty(shopName)) {
            activityOrderConfirmShopName.setText(shopName);
        }

        if (!StringUtils.isEmpty(goodsTotalPrice)) {
            activityOrderConfirmTotalPrice.setText(goodsTotalPrice);
        }


    }

    @Override
    public void requestData() {
        orderConfirmPresent.getAddressInfo();
    }

    @OnClick({R.id.toolbar_layout_back, R.id.activity_order_confirm_address_layout, R.id.activity_order_confirm_invoice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;
            case R.id.activity_order_confirm_address_layout:
                ActivityUtils.startActivity(AddressActivity.class);
                break;
            case R.id.activity_order_confirm_invoice:

                ActivityUtils.startActivity(InvoiceActivity.class);

                break;
        }
    }


    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    private ArrayList<RequestPostageBean.GoodsListBean> upLoadGoodsBean;

    @Override
    public void showList(List<AddressBean> list) {

        if (ObjectUtils.isEmpty(list)) {
            return;
        }

        activityOrderConfirmAddressName.setText(list.get(0).getName());
        activityOrderConfirmAddressPhone.setText(list.get(0).getPhone());
        activityOrderConfirmAddressDetail.setText(
                list.get(0).getProvinceName() + " " + list.get(0).
                        getCityName() + " " + list.get(0).getDistrictName()
                        + " " + list.get(0).getAddress());


        upLoadGoodsBean = new ArrayList<>();

        for (int i = 0; i < goodsListBeans.size(); i++) {
            RequestPostageBean.GoodsListBean goodsListBean = new RequestPostageBean.GoodsListBean();
            goodsListBean.setId(goodsListBeans.get(i).getId());
            goodsListBean.setGoodsNum(goodsListBeans.get(i).getGoodsNum());
            upLoadGoodsBean.add(goodsListBean);
        }

        RequestPostageBean postageBean  = new RequestPostageBean();
        postageBean.setGoodsList(upLoadGoodsBean);
        postageBean.setShipId(list.get(0).getId());

        orderConfirmPresent.getPostage(postageBean);

    }

    @Override
    public void showPostage(String postage) {
        activityOrderConfirmPostage.setText("¥"+postage);

    }
}
