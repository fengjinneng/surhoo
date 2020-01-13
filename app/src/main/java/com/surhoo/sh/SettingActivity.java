package com.surhoo.sh;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.UserUtil;
import com.surhoo.sh.common.dialog.MyDialogFragment;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.home.view.MainActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.activity_setting_loginOut)
    TextView activitySettingLoginOut;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_setting_about_surhoo)
    ConstraintLayout aboutSurhoo;

    @Override
    public int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("设置");

        if (UserUtil.isLogin()) {
            activitySettingLoginOut.setVisibility(View.VISIBLE);
        } else {
            activitySettingLoginOut.setVisibility(View.GONE);
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {

    }

    @OnClick({R.id.toolbar_layout_back, R.id.activity_setting_loginOut, R.id.activity_setting_about_surhoo})
    public void onViewClicked(View view) {

        if (ClickUtil.isFastClick()) {
            switch (view.getId()) {
                case R.id.toolbar_layout_back:
                    finish();
                    break;
                case R.id.activity_setting_loginOut:
                    MyDialogFragment dialogFragment = MyDialogFragment.newInstance("确定要退出帐号吗？");

                    dialogFragment.setOnCancelClickListener(new MyDialogFragment.onCancelClickListener() {
                        @Override
                        public void onCancelClick() {
                            dialogFragment.dismiss();
                        }
                    });

                    dialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                        @Override
                        public void onConfirmClick() {
                            SPUtils.getInstance().clear();
                            ActivityUtils.finishAllActivities();
                            ActivityUtils.startActivity(MainActivity.class);
                        }
                    });
                    dialogFragment.show(getSupportFragmentManager(), "dialog");
                    break;
                case R.id.activity_setting_about_surhoo:
                    ActivityUtils.startActivity(AboutSurhooActivity.class);
                    break;
            }
        }
    }
}
