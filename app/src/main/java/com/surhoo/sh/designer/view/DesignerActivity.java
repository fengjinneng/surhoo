package com.surhoo.sh.designer.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.tabs.TabLayout;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.UserUtil;
import com.surhoo.sh.common.dialog.MyDialogFragment;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.common.util.EmptyUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.designer.bean.DesignerDetailBean;
import com.surhoo.sh.designer.fragment.DynamicFragment;
import com.surhoo.sh.designer.fragment.FinishProducsFragment;
import com.surhoo.sh.designer.fragment.MaterialFragment;
import com.surhoo.sh.designer.presenter.DesignerPresenter;
import com.surhoo.sh.designer.presenter.impl.DesignerPresenterImpl;
import com.surhoo.sh.login.view.LoginActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
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
    @BindView(R.id.activity_designer_video)
    ImageView activityDesignerVideo;
    @BindView(R.id.activity_desinger_tagFlowLayout)
    TagFlowLayout activityDesingerTagFlowLayout;
    @BindView(R.id.activity_design_collect)
    ImageView activityDesignCollect;

    private BaseViewpageAdapter baseViewpageAdapter;

    private DesignerPresenter designerPresenter;

    private Integer designerId;


    @Override
    public int getContentView() {
        return R.layout.activity_designer;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        designerId = getIntent().getIntExtra("id", 0);

        designerPresenter = new DesignerPresenterImpl();

        designerPresenter.bindView(this, this);

        toolbarLayoutTitle.setText("设计师首页");

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(MaterialFragment.newInstance("designer", designerId));
        fragments.add(FinishProducsFragment.newInstance(designerId));
        fragments.add(DynamicFragment.newInstance(designerId));
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
    public void initData() {

    }

    @Override
    public void requestData() {
        designerPresenter.requestData(false, designerId);
    }

    @OnClick({R.id.toolbar_layout_back,R.id.activity_design_collect})
    public void onViewClicked(View view ) {
        switch (view.getId()){
            case R.id.toolbar_layout_back:
                finish();
                break;

            case R.id.activity_design_collect:
                if(UserUtil.isLogin()){
                    if(isCollect){
                        MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("确定取消收藏？");
                        myDialogFragment.setOnCancelClickListener(new MyDialogFragment.onCancelClickListener() {
                            @Override
                            public void onCancelClick() {
                                myDialogFragment.dismiss();

                            }
                        });
                        myDialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                            @Override
                            public void onConfirmClick() {
                                myDialogFragment.dismiss();
                                designerPresenter.cancelCollect(CANCELCOLLECT,designerId);
                            }
                        });

                        myDialogFragment.show(getSupportFragmentManager(),"dialog");
                    }else {
                        designerPresenter.addCollect(ADDCOLLECT,designerId);
                    }
                }else {
                    ActivityUtils.startActivity(LoginActivity.class);
                }

                break;
        }
    }

    private boolean isCollect;


    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    public void showBeanData(DesignerDetailBean designerDetailBean) {

        if (ObjectUtils.isEmpty(designerDetailBean)) {
            return;
        }

        activityDesignerName.setText(EmptyUtil.isStringEmpty(designerDetailBean.getNickname()));
        activityDesignerContent.setText(EmptyUtil.isStringEmpty(designerDetailBean.getDetail()));

        activityDesignerLevel.setText("lv" + EmptyUtil.isInterEmpty(designerDetailBean.getLevel()));

        GlideUtil.loadCircleImage(this, designerDetailBean.getHeadimgurl(), activityDesignerAvator);

        GlideUtil.loadDefaultImg(this, designerDetailBean.getImg(), activityDesignerImg);

        ArrayList<String> list = new ArrayList<>();

        String[] split = designerDetailBean.getLabelNames().split(",");

        for (int i = 0; i < split.length; i++) {
            if (i == 6) {
                break;
            }
            list.add(split[i]);
        }

        if(designerDetailBean.getCollect()){
            activityDesignCollect.setImageDrawable(getResources().getDrawable(R.mipmap.collect));
            isCollect =true;
        }


        activityDesingerTagFlowLayout.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.item_designer_tag,
                        activityDesingerTagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    private static final String ADDCOLLECT = "addCollect";
    private static final String CANCELCOLLECT = "cancelCollect";


    @Override
    public void showStringData(String requestTag, String s) {
        if (StringUtils.equals(ADDCOLLECT, requestTag)) {
            isCollect = true;
            activityDesignCollect.setImageDrawable(getResources().getDrawable(R.mipmap.collect));
        }

        if (StringUtils.equals(CANCELCOLLECT, requestTag)) {
            isCollect = false;
            activityDesignCollect.setImageDrawable(getResources().getDrawable(R.mipmap.un_collect));
            EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Collect.cancelDesignerCollect));
        }
    }
}
