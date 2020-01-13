package com.surhoo.sh.user;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateHeadImgActivity extends BaseActivity {

    @BindView(R.id.activity_update_head_img_img)
    ImageView activityUpdateHeadImgImg;
    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;

    @Override
    public int getContentView() {
        return R.layout.activity_update_head_img;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {

        toolbarLayoutTitle.setText("头像");
    }

    @Override
    public void initData() {

        Glide.with(this).load(SPUtils.getInstance().getString("headImg")).into(activityUpdateHeadImgImg);

    }

    @Override
    public void requestData() {

    }


    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }
}
