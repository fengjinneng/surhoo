package com.surhoo.sh.login.presenter;

import android.app.Activity;
import android.os.CountDownTimer;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.login.bean.UserDataBean;
import com.surhoo.sh.login.view.LoginView;

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

        HttpParams httpParams = new HttpParams();

        httpParams.put("mobile",phone);

        NetworkReturnUtil.requestStringResultUseGet("",loginView,activity,Api.GETVERIFYCODE,httpParams);

    }

    @Override
    public void login(String phone, String verifycode) {

        String s = "{\"mobile\":\""+phone+"\",\"verifyCode\":\""+verifycode+"\"}";


        NetworkReturnUtil.requestBeanResultUsePost(loginView,activity,Api.login,s, UserDataBean.class);


    }
}
