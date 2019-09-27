package com.surhoo.sh.home.view;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.home.fragment.HomeFragment;
import com.surhoo.sh.home.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
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
    public void requestData() {

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
