package com.surhoo.sh.home.view;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.githang.statusbar.StatusBarCompat;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.UserUtil;
import com.surhoo.sh.home.fragment.HomeFragment;
import com.surhoo.sh.home.fragment.MineFragment;
import com.surhoo.sh.login.view.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.activity_main_home_tv)
    TextView activityMainHomeTv;
    @BindView(R.id.activity_main_home_img)
    ImageView activityMainHomeImg;
    @BindView(R.id.activity_main_home_layout)
    ConstraintLayout activityMainHomeLayout;
    @BindView(R.id.activity_main_mine_tv)
    TextView activityMainMineTv;
    @BindView(R.id.activity_main_mine_img)
    ImageView activityMainMineImg;
    @BindView(R.id.activity_main_mine_layout)
    ConstraintLayout activityMainMineLayout;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;


    @Override
    public int getContentView() {

        if (android.os.Build.VERSION.SDK_INT > 19) {
            StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.topColor), false);
        }

        return R.layout.activity_main;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    FragmentManager fragmentManager ;
    FragmentTransaction fragmentTransaction ;

    @Override
    public void initView() {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        fragmentTransaction.add(R.id.activity_main_container, homeFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {

        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }

    }


    @OnClick({R.id.activity_main_home_layout, R.id.activity_main_mine_layout})
    public void onViewClicked(View view) {

        fragmentTransaction = fragmentManager.beginTransaction();

        switch (view.getId()) {
            case R.id.activity_main_home_layout:

                activityMainHomeImg.setImageDrawable(getResources().getDrawable(R.mipmap.home_select));
                activityMainHomeTv.setTextColor(getResources().getColor(R.color.themeColor));

                activityMainMineImg.setImageDrawable(getResources().getDrawable(R.mipmap.mine_unselect));
                activityMainMineTv.setTextColor(getResources().getColor(R.color.pageTitle));

                if (homeFragment == null) {

                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.activity_main_container, homeFragment);
                }
                hideFragment(fragmentTransaction);
                fragmentTransaction.show(homeFragment);
                break;
            case R.id.activity_main_mine_layout:

                activityMainHomeImg.setImageDrawable(getResources().getDrawable(R.mipmap.home_unselect));
                activityMainHomeTv.setTextColor(getResources().getColor(R.color.pageTitle));

                activityMainMineImg.setImageDrawable(getResources().getDrawable(R.mipmap.mine_select));
                activityMainMineTv.setTextColor(getResources().getColor(R.color.themeColor));

                if (mineFragment == null) {

                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.activity_main_container, mineFragment);
                }
                hideFragment(fragmentTransaction);
                fragmentTransaction.show(mineFragment);
                break;
        }
        fragmentTransaction.commit();
    }
}
