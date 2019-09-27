package com.surhoo.sh.login.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.login.view.LoginView;

public interface LoginPresenter extends BasePresenter<LoginView> {


    void sendVerifycode(String phone);
    void login(String phone,String verifycode);

}
