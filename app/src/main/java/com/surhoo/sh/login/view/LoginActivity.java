package com.surhoo.sh.login.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.login.presenter.LoginPresenter;
import com.surhoo.sh.login.presenter.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity implements LoginView {


    LoginPresenter loginPresenter;
    @BindView(R.id.activity_login_phone)
    EditText activityLoginPhone;
    @BindView(R.id.activity_login_verifycode)
    EditText activityLoginVerifycode;
    @BindView(R.id.activity_login_login)
    Button activityLoginLogin;
    @BindView(R.id.activity_login_send_verifycode)
    public TextView activityLoginSendVerifycode;
    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        initView();

        loginPresenter = new LoginPresenterImpl();

        loginPresenter.bindView(this,this);
    }


    @Override
    public void initView() {
        toolbarLayoutTitle.setText("登录");
    }

    @Override
    public void requestData() {

    }

    @OnClick({R.id.activity_login_send_verifycode, R.id.activity_login_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_login_send_verifycode:
                loginPresenter.sendVerifycode(activityLoginPhone.getText().toString());
                break;
            case R.id.activity_login_login:
                loginPresenter.login(activityLoginPhone.getText().toString(), activityLoginPhone.getText().toString());
                break;
        }
    }

    @Override
    public void showToastMsg(String msg) {

        ToastUtils.showShort(msg);
    }


    @Override
    public void setCountDown(String text) {
        activityLoginSendVerifycode.setText(text);
    }

    @Override
    public void setSendVerifycodeEnable(boolean b) {
        activityLoginSendVerifycode.setEnabled(b);

    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        KeyboardUtils.hideSoftInput(this);
        finish();
    }
}
