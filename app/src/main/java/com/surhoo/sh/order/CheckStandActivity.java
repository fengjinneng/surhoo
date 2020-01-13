package com.surhoo.sh.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.bean.order.response.PayOrderSuccessBean;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class CheckStandActivity extends BaseActivity {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_check_stand_total_price)
    TextView activityCheckStandTotalPrice;
    @BindView(R.id.activity_check_stand_time)
    TextView activityCheckStandTime;
    @BindView(R.id.activity_check_stand_weChat)
    CheckBox activityCheckStandWeChat;
    @BindView(R.id.activity_check_stand_confirm)
    Button activityCheckStandConfirm;

    private PayOrderSuccessBean payOrderSuccessBean;

    private IWXAPI api;

    private String orderTotalPrice;
    private CountDownTimer timer;

    private String payOrderTime;

    private int payType = 1;//选择的支付方式 1位微信 2位支付宝

    @Override
    public int getContentView() {
        return R.layout.activity_check_stand;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {

        toolbarLayoutTitle.setText("支付订单");
        payOrderSuccessBean = getIntent().getParcelableExtra("payOrderSuccessBean");

        api = WXAPIFactory.createWXAPI(this, null);

        api.registerApp("wxa9974a0f587be201");
    }

    @Override
    public void initData() {

        orderTotalPrice = getIntent().getStringExtra("orderTotalPrice");
        payOrderTime = getIntent().getStringExtra("payOrderTime");

        activityCheckStandWeChat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    payType = 1;
                } else {
                    payType = 0;
                }
            }
        });

        activityCheckStandTotalPrice.setText(orderTotalPrice);

        if (!StringUtils.isEmpty(payOrderTime)) {
            long l = TimeUtils.string2Millis(payOrderTime);

            timer = new CountDownTimer(l+3600000-System.currentTimeMillis(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (!ObjectUtils.isEmpty(activityCheckStandTime)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                        activityCheckStandTime.setText(sdf.format(new Date(millisUntilFinished)));
                    }
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }else {
            timer = new CountDownTimer(3599000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (!ObjectUtils.isEmpty(activityCheckStandTime)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                        activityCheckStandTime.setText(sdf.format(new Date(millisUntilFinished)));
                    }
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!ObjectUtils.isEmpty(timer)) {
            timer.cancel();
        }
    }

    @Override
    public void requestData() {

    }


    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {
        super.onRecevieMessage(bean);

        switch (bean.getCode()){
            case EventBusMessageBean.Order.cancelPay:
                gotoOrderDetail();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                gotoOrderDetail();
                break;
        }

        return super.onKeyDown(keyCode, event);

    }

    private void gotoOrderDetail() {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("id", payOrderSuccessBean.getOrderId());
        ActivityUtils.startActivity(intent);
        finish();
    }

    @OnClick({R.id.toolbar_layout_back, R.id.activity_check_stand_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                gotoOrderDetail();
                break;
            case R.id.activity_check_stand_confirm:

                if (payType == 1) {
                    weChatPay();
                } else {
                    ToastUtils.showShort("请选择付款方式");
                }

                break;
        }
    }

    private void weChatPay() {
        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                try {
                    IWXAPI api = WXAPIFactory.createWXAPI(CheckStandActivity.this, "wxa9974a0f587be201", false);
                    //填写自己的APPIDapi.registerApp("wxAPPID");//填写自己的APPID，注册本身
                    PayReq req = new PayReq();//PayReq就是订单信息对象
                    req.appId = "wxa9974a0f587be201";//你的微信appid
                    req.partnerId = payOrderSuccessBean.getPay().getPartnerid();//商户号
                    req.prepayId = payOrderSuccessBean.getPay().getPrepayid();//预支付交易会话ID
                    req.nonceStr = payOrderSuccessBean.getPay().getNoncestr();//随机字符串
                    req.timeStamp = payOrderSuccessBean.getPay().getTimestamp();//时间戳
                    req.packageValue = "Sign=WXPay";
                    req.sign = payOrderSuccessBean.getPay().getSignNew();//签名
                    api.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求
                }catch (Exception e){
                    e.printStackTrace();
                    ToastUtils.showShort("支付信息解析异常!");
                }
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
