package com.surhoo.sh.designer.view;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.common.util.EmptyUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.designer.bean.DesignerDetailBean;
import com.surhoo.sh.designer.fragment.DynamicFragment;
import com.surhoo.sh.designer.fragment.FinishProducsFragment;
import com.surhoo.sh.designer.fragment.MaterialFragment;
import com.surhoo.sh.designer.presenter.DesignerPresenter;
import com.surhoo.sh.designer.presenter.impl.DesignerPresenterImpl;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
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
        fragments.add(MaterialFragment.newInstance(designerId));
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

        if(ObjectUtils.isEmpty(designerDetailBean)){
            return;
        }

        activityDesignerName.setText(EmptyUtil.isStringEmpty(designerDetailBean.getNickname()));
        activityDesignerContent.setText(EmptyUtil.isStringEmpty(designerDetailBean.getDetail()));

        activityDesignerLevel.setText("lv" + EmptyUtil.isInterEmpty(designerDetailBean.getLevel()));

        GlideUtil.loadCircleImage(this, designerDetailBean.getHeadimgurl(), activityDesignerAvator);

        Glide.with(this).load(designerDetailBean.getImg()).into(activityDesignerImg);
        Glide.with(this).load(designerDetailBean.getImg()).into(activityDesignerVideo);

        ArrayList<String> list = new ArrayList<>();

        String[] split = designerDetailBean.getLabelNames().split(",");

        for (int i = 0; i < split.length; i++) {
            if(i==6){
                break;
            }
            list.add(split[i]);
        }


        activityDesingerTagFlowLayout.setAdapter(new TagAdapter<String>(list)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.item_designer_tag,
                        activityDesingerTagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }

}
