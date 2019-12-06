package com.surhoo.sh.base;

import android.app.Activity;
import android.content.Context;

import com.surhoo.sh.login.view.LoginView;

public interface BasePresenter<T> {

    void bindView(Activity activity, T view);

    void unBindView();

}
