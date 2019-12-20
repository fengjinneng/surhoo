package com.surhoo.sh.order;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.address.AddressActivity;
import com.surhoo.sh.address.bean.AddressBean;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.invoice.InvoiceListActivity;
import com.surhoo.sh.order.adapter.ConfirmOrderAdapter;
import com.surhoo.sh.order.bean.RequestOrderBean;
import com.surhoo.sh.order.bean.RequestPostageBean;
import com.surhoo.sh.order.present.IOrderConfirmPresent;
import com.surhoo.sh.order.present.OrderConfirmPresentImpl;
import com.surhoo.sh.order.view.OrderConfirmView;
import com.surhoo.sh.shoppingcart.ShoppingCartBean;
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

    private ArrayList<ShoppingCartBean.CarGoodsListBean> goodsListBeans;
    private String shopName;
    private double goodsTotalPrice;

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
        goodsTotalPrice = getIntent().getDoubleExtra("goodsTotalPrice",0);

        if (!ObjectUtils.isEmpty(goodsListBeans)) {
            adapter = new ConfirmOrderAdapter(R.layout.item_order_confirm, goodsListBeans);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }

        if (!StringUtils.isEmpty(shopName)) {
            activityOrderConfirmShopName.setText(shopName);
        }

        if (goodsTotalPrice!=0) {
            activityOrderConfirmTotalPrice.setText(String.valueOf(goodsTotalPrice));
        }


    }

    @Override
    public void requestData() {
        orderConfirmPresent.getAddressInfo();
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

                RequestOrderBean bean = new RequestOrderBean();

                ArrayList<RequestOrderBean.OrderDataListBean> orderDataListBeans = new ArrayList<>();

                for (int i = 0; i < goodsListBeans.size(); i++) {

                    RequestOrderBean.OrderDataListBean orderDataListBean = new RequestOrderBean.OrderDataListBean();
                    orderDataListBean.setId(goodsListBeans.get(i).getGoodsId());
                    orderDataListBean.setGoodsNum(goodsListBeans.get(i).getGoodsNum());
                    orderDataListBean.setSkuId(goodsListBeans.get(i).getSkuId());
                    orderDataListBeans.add(orderDataListBean);
                }

                bean.setOrderDataList(orderDataListBeans);

                bean.setOrderRemarks(activityOrderConfirmRemarks.getText().toString());
                if(ObjectUtils.isEmpty(upLoadAddressId)){
                    ToastUtils.showShort("请添加地址信息!");
                    return;
                }else {
                    bean.setShipId(upLoadAddressId);
                }

                if(ObjectUtils.isEmpty(upLoadInvocieId)){
                    bean.setIsInvoice(0);
                }else {
                    bean.setIsInvoice(1);
                    bean.setInvoiceId(upLoadInvocieId);
                }

                bean.setCarPay(true);
                bean.setOrderSource(2);

                if(ObjectUtils.isEmpty(upPostage)){
                    return;
                }else {
                    bean.setOrderFreight(upPostage);
                    if(goodsTotalPrice==0){
                        return;
                    }else {
                        bean.setOrderAmount(String.valueOf(goodsTotalPrice+Double.valueOf(upPostage)));
                    }
                }

                orderConfirmPresent.payOrder(bean);


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


        upLoadAddressId =  list.get(0).getId();

        upLoadGoodsBean = new ArrayList<>();

        for (int i = 0; i < goodsListBeans.size(); i++) {
            RequestPostageBean.GoodsListBean goodsListBean = new RequestPostageBean.GoodsListBean();
            goodsListBean.setId(goodsListBeans.get(i).getId());
            goodsListBean.setGoodsNum(goodsListBeans.get(i).getGoodsNum());
            upLoadGoodsBean.add(goodsListBean);
        }

        RequestPostageBean postageBean = new RequestPostageBean();
        postageBean.setGoodsList(upLoadGoodsBean);
        postageBean.setShipId(list.get(0).getId());

        orderConfirmPresent.getPostage(postageBean);

    }

    private String upPostage;

    @Override
    public void showPostage(String postage) {
        upPostage = postage;
        activityOrderConfirmPostage.setText("¥" + postage);

    }

    @Override
    public void getPayOrderResult() {
        ToastUtils.showShort("---------");
    }

}
