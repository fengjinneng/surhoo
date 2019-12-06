package com.surhoo.sh.login.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.login.view.LoginView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginPresenterImpl implements LoginPresenter {


    LoginView loginView;
    Activity activity;



    @Override
    public void bindView(Activity activity, LoginView loginView) {

        this.activity = activity;
        this.loginView = loginView;
    }

    @Override
    public void unBindView() {
        loginView = null;
    }


    private CountDownTimer timer;

    @Override
    public void sendVerifycode(String phone) {
        if (StringUtils.isEmpty(phone)) {
            loginView.showToastMsg("请填写手机号!");
            return;
        }

        if (!RegexUtils.isMobileSimple(phone)) {
            loginView.showToastMsg("您的手机号有误!");
            return;
        }

        String url = Api.GETVERIFYCODE;
        GetRequest<String> request = OkGo.<String>get(url)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .params("mobile", phone);


        StringCallback stringCallback = new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("DSADASSADA", response.body());

                try {
                    if (response.code() == 200) {
                        timer = new CountDownTimer(59000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                SimpleDateFormat sdf = new SimpleDateFormat("ss");
                                loginView.setCountDown(sdf.format(new Date(millisUntilFinished)) + "S");
                                loginView.setSendVerifycodeEnable(false);
                            }

                            @Override
                            public void onFinish() {
                                loginView.setCountDown("重新获取验证码");
                                loginView.setSendVerifycodeEnable(true);
                            }
                        }.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                loginView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);

    }

    @Override
    public void login(String phone, String verifycode) {

        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(verifycode)) {
            loginView.showToastMsg("请填写完整信息!");
            return;
        }

        if (!RegexUtils.isMobileSimple(phone)) {
            loginView.showToastMsg("您的手机号有误!");
            return;
        }

        loginView.showToastMsg("登录成功!");

    }
}
