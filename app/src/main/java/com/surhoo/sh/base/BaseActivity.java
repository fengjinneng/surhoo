package com.surhoo.sh.base;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.githang.statusbar.StatusBarCompat;
import com.lzy.okgo.OkGo;
import com.surhoo.sh.R;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.NetworkUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {


    protected Unbinder mBinder;

    public Activity activity;

    private AlertDialog dialog = null;
    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //锁定屏幕。禁止旋转屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        activity = this;

        EventBus.getDefault().register(this);

        if (android.os.Build.VERSION.SDK_INT > 19) {
            StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        }

//        getWindow().setBackgroundDrawable(null);

        setContentView(getContentView());

        mBinder = ButterKnife.bind(this);

        initView();

        initData();

//        if (NetworkUtils.isConnected()) {
            if (isFirstInLoadData()) {
                requestData();
//            }
//        } else {
//            showPop();
//
        }
    }


    /**
     * 显示无网弹框
     *
     * @param
     */
    private void showPop() {
        if (builder == null) {

            builder = new AlertDialog.Builder(activity);
            View view = View.inflate(activity, R.layout.toast_newwork_setting_layout, null);
            view.setPadding(10, 0, 10, 200);
            builder.setView(view);
            builder.setCancelable(true);//点击返回是否取消
            LinearLayout rlParent = view.findViewById(R.id.rl_parent);//


            rlParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (dialog.isShowing()) {
//                    dialog.dismiss();
                        NetworkUtil.toSetting(activity);
                    }
                }
            });
        }

        //取消或确定按钮监听事件处理
        if (dialog == null) {
            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCanceledOnTouchOutside(true);
            Window window = dialog.getWindow();
            window.setDimAmount(0);//设置昏暗度为0
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setGravity(Gravity.BOTTOM);

        }

        if (!dialog.isShowing()) {
            dialog.show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
        mBinder.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecevieMessage(EventBusMessageBean bean) {
    }


    //得到布局
    public abstract int getContentView();


    //第一次进入是否需要加载数据
    public abstract boolean isFirstInLoadData();


    //初始化视图
    public abstract void initView();

    //初始化数据
    public abstract void initData();


    //请求数据
    public abstract void requestData();


}
