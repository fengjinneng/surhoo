package com.surhoo.sh.login.view;

import com.surhoo.sh.base.BaseView;
import com.surhoo.sh.base.NoPageBaseView;

public interface LoginView extends NoPageBaseView {


    void setCountDown(String text);

    void setSendVerifycodeEnable(boolean b);

}
