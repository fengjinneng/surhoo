package com.surhoo.sh.designer.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.designer.bean.DesignerDetailBean;
import com.surhoo.sh.designer.fragment.MaterialFragment;
import com.surhoo.sh.designer.presenter.DesignerPresenter;
import com.surhoo.sh.designer.presenter.impl.DesignerPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DesignerActivity extends BaseActivity implements DesignerViewList {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_design_tabLayout)
    TabLayout activityDesignTabLayout;
    @BindView(R.id.activity_design_viewPager)
    ViewPager activityDesignViewPager;

    BaseViewpageAdapter baseViewpageAdapter;

    DesignerPresenter designerPresenter;
    @BindView(R.id.activity_designer_img)
    ImageView activityDesignerImg;
    @BindView(R.id.activity_designer_name)
    TextView activityDesignerName;
    @BindView(R.id.activity_designer_level)
    TextView activityDesignerLevel;
    @BindView(R.id.activity_designer_content)
    TextView activityDesignerContent;
    @BindView(R.id.activity_designer_avator)
    ImageView activityDesignerAvator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);
        ButterKnife.bind(this);

        designerPresenter = new DesignerPresenterImpl();

        designerPresenter.bindView(this, this);

        initView();

        requestData();
    }

    @Override
    public void initView() {

        toolbarLayoutTitle.setText("设计师首页");

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(MaterialFragment.newInstance("", ""));
        fragments.add(MaterialFragment.newInstance("", ""));
        fragments.add(MaterialFragment.newInstance("", ""));
        List<String> datas = new ArrayList<>();
        datas.add("素材");
        datas.add("成品");
        datas.add("动态");
        baseViewpageAdapter = new BaseViewpageAdapter(getSupportFragmentManager(), fragments, datas);
        activityDesignViewPager.setAdapter(baseViewpageAdapter);
        activityDesignTabLayout.setupWithViewPager(activityDesignViewPager);
        activityDesignTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void requestData() {
        designerPresenter.requestData(false, 10010085);
    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    public void showData(DesignerDetailBean designerDetailBean) {
        activityDesignerName.setText(designerDetailBean.getNickname());

        activityDesignerLevel.setText("lv"+designerDetailBean.getLevel());

        Glide.with(this).load(designerDetailBean.getHeadimgurl()).into(activityDesignerAvator);
        Glide.with(this).load(designerDetailBean.getImg()).into(activityDesignerImg);
        activityDesignerContent.setText(designerDetailBean.getDetail());

    }

}
