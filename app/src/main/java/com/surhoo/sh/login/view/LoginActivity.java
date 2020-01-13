package com.surhoo.sh.login.view;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.login.bean.UserDataBean;
import com.surhoo.sh.login.presenter.LoginPresenter;
import com.surhoo.sh.login.presenter.LoginPresenterImpl;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
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
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }


    @Override
    public void initView() {
        loginPresenter = new LoginPresenterImpl();

        loginPresenter.bindView(this, this);
        toolbarLayoutTitle.setText("登录");
    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {

    }

    @OnClick({R.id.activity_login_send_verifycode, R.id.activity_login_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_login_send_verifycode:

                if (StringUtils.isEmpty(activityLoginPhone.getText().toString())) {
                    showToastMsg("请填写手机号!");
                    return;
                }

                if (!RegexUtils.isMobileSimple(activityLoginPhone.getText().toString())) {
                    showToastMsg("您的手机号有误!");
                    return;
                }

                activityLoginVerifycode.requestFocus();

                loginPresenter.sendVerifycode(activityLoginPhone.getText().toString());
                break;
            case R.id.activity_login_login:
                if (StringUtils.isEmpty(activityLoginPhone.getText().toString())) {
                    showToastMsg("请填写手机号!");
                    return;
                }

                if (!RegexUtils.isMobileSimple(activityLoginPhone.getText().toString())) {
                    showToastMsg("您的手机号有误!");
                    return;
                }
                if (StringUtils.isEmpty(activityLoginVerifycode.getText().toString())) {
                    showToastMsg("验证码不能为空！");
                    return;
                }

                loginPresenter.login(activityLoginPhone.getText().toString(), activityLoginVerifycode.getText().toString());
                break;
        }
    }

    @Override
    public void showToastMsg(String msg) {

        ToastUtils.showShort(msg);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!ObjectUtils.isEmpty(timer)) {
            timer.cancel();
        }

    }

    private CountDownTimer timer;


    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        KeyboardUtils.hideSoftInput(this);
        finish();
    }


    @Override
    public void showStringData(String requestTag, String s) {
        activityLoginSendVerifycode.setEnabled(false);

        timer = new CountDownTimer(59000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!ObjectUtils.isEmpty(activityLoginSendVerifycode)) {
                    activityLoginSendVerifycode.setTextColor(getResources().getColor(R.color.saleColor));
                    SimpleDateFormat sdf = new SimpleDateFormat("ss");
                    activityLoginSendVerifycode.setText(sdf.format(new Date(millisUntilFinished)) + "s后重试");
                }
            }

            @Override
            public void onFinish() {
                if (!ObjectUtils.isEmpty(activityLoginSendVerifycode)) {
                    activityLoginSendVerifycode.setTextColor(getResources().getColor(R.color.themeColor));
                    activityLoginSendVerifycode.setEnabled(true);
                    activityLoginSendVerifycode.setText("发送验证码");
                }
            }
        }.start();
    }

    @Override
    public void showBeanData(UserDataBean userBean) {
        SPUtils.getInstance().put("token", userBean.getToken());
        SPUtils.getInstance().put("nickName", userBean.getUser().getNickname());
        SPUtils.getInstance().put("headImg", userBean.getUser().getHeadimgurl());

        EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.User.login));

        finish();

    }
}
