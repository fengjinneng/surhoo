package com.surhoo.sh.base;

import android.content.Context;

import com.surhoo.sh.login.view.LoginView;

public interface BasePresenter<T> {

    void bindView(Context ctx,T view);

    void unBindView();

}
