package com.surhoo.sh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.util.ClickUtil;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutSurhooActivity extends BaseActivity {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_about_surhoo_user_agreement)
    ConstraintLayout activityAboutSurhooUserAgreement;
    @BindView(R.id.activity_about_surhoo_versionName)
    TextView activityAboutSurhooVersionName;
    @BindView(R.id.activity_about_surhoo_shop_agreement)
    ConstraintLayout activityAboutSurhooShopAgreement;

    @Override
    public int getContentView() {
        return R.layout.activity_about_surhoo;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {

        toolbarLayoutTitle.setText("关于尚乎");

    }

    @Override
    public void initData() {
        activityAboutSurhooVersionName.setText("v  "+ AppUtils.getAppVersionName());
    }

    @Override
    public void requestData() {

    }

    @OnClick({R.id.toolbar_layout_back, R.id.activity_about_surhoo_user_agreement
            , R.id.activity_about_surhoo_shop_agreement})
    public void onViewClicked(View view) {
        if (ClickUtil.isFastClick()) {
            switch (view.getId()) {
                case R.id.toolbar_layout_back:
                    finish();
                    break;
                case R.id.activity_about_surhoo_user_agreement:
                    Intent intent = new Intent(this, WebViewActivity.class);
                    intent.putExtra("webUrl", "http://static.shanghusm.com/personalPrivate.html");
                    ActivityUtils.startActivity(intent);
                    break;
                case R.id.activity_about_surhoo_shop_agreement:

                    Intent i = new Intent(this, WebViewActivity.class);
                    i.putExtra("webUrl", "http://static.shanghusm.com/userService.html");
                    ActivityUtils.startActivity(i);
                    break;
            }
        }
    }

}
