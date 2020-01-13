package com.surhoo.sh.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.order.CheckStandActivity;
import com.surhoo.sh.order.PaySuccessActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;


    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.pay_result);


        api = WXAPIFactory.createWXAPI(this, "wxa9974a0f587be201");

        api.handleIntent(getIntent(), this);

    }


    @Override

    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);

        setIntent(intent);

        api.handleIntent(intent, this);

    }


    @Override

    public void onReq(BaseReq req) {

    }


    @Override

    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            if (resp.errCode == 0) {

                EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Order.payOrderSuccess));

                ActivityUtils.finishActivity(CheckStandActivity.class);
                //成功
                ActivityUtils.startActivity(PaySuccessActivity.class);

            } else if (resp.errCode == -1) {
                //错误  可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
            } else if (resp.errCode == -2) {
                //错误  无需处理。发生场景：用户不支付了，点击取消，返回APP。
                EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Order.cancelPay));
            }

            finish();
        }
    }
}
