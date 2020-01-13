package com.surhoo.sh.material;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.UserUtil;
import com.surhoo.sh.common.dialog.MyDialogFragment;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.designer.view.DesignerActivity;
import com.surhoo.sh.login.view.LoginActivity;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.material.present.IMaterialDetailPresent;
import com.surhoo.sh.material.present.MaterialDetailPresentImpl;
import com.surhoo.sh.material.view.MaterialDetailView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialDetailActivity extends BaseActivity implements MaterialDetailView {


    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_material_detail_img)
    ImageView activityMaterialDetailImg;
    @BindView(R.id.activity_material_detail_name)
    TextView activityMaterialDetailName;
    @BindView(R.id.activity_material_detail_price)
    TextView activityMaterialDetailPrice;
    @BindView(R.id.activity_material_detail_desc)
    TextView activityMaterialDetailDesc;
    @BindView(R.id.activity_material_detail_tagFlowLayout)
    TagFlowLayout activityMaterialDetailTagFlowLayout;
    @BindView(R.id.activity_material_detail_video)
    ImageView activityMaterialDetailVideo;
    @BindView(R.id.activity_material_detail_webview)
    WebView activityMaterialDetailWebview;
    @BindView(R.id.activity_material_detail_apply_material)
    TextView activityMaterialDetailApplyMaterial;
    @BindView(R.id.activity_material_detail_to_designer)
    TextView activityMaterialDetailToDesigner;
    @BindView(R.id.activity_material_detail_collect)
    ImageView activityMaterialDetailCollect;
    private int id;
    IMaterialDetailPresent iMaterialDetailPresent;

    @Override
    public int getContentView() {
        return R.layout.activity_material_detail;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        iMaterialDetailPresent = new MaterialDetailPresentImpl();
        iMaterialDetailPresent.bindView(this, this);
        id = getIntent().getIntExtra("id", 0);
        toolbarLayoutTitle.setText("素材详情");

    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {
        iMaterialDetailPresent.requestData(id);
    }

    private int designerId;

    @Override
    public void showBeanData(MaterialBean materialBean) {
        GlideUtil.loadDefaultImg(this, materialBean.getLogo(), activityMaterialDetailImg);
        activityMaterialDetailName.setText(materialBean.getName());
        activityMaterialDetailPrice.setText("￥" + materialBean.getPrice());
        activityMaterialDetailDesc.setText(materialBean.getDetail());

        String[] split = materialBean.getLabelName().split(",");

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < split.length; i++) {
            strings.add(split[i]);
        }

        activityMaterialDetailTagFlowLayout.setAdapter(new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.item_designer_tag, parent, false);
                tv.setText(s);
                return tv;
            }
        });


        if(materialBean.getCollect()){
            activityMaterialDetailCollect.setImageDrawable(getResources().getDrawable(R.mipmap.collect));
            isCollect =true;
        }

        if (!ObjectUtils.isEmpty(materialBean.getDesignerId())) {
            activityMaterialDetailToDesigner.setVisibility(View.VISIBLE);
            designerId = materialBean.getDesignerId();

        }

        if (!StringUtils.isEmpty(materialBean.getVideo())) {
            activityMaterialDetailVideo.setVisibility(View.VISIBLE);
        }

        if (!StringUtils.isEmpty(materialBean.getRichText())) {
            activityMaterialDetailWebview.setVisibility(View.VISIBLE);
            activityMaterialDetailWebview.loadData(materialBean.getRichText(), "text/html", "UTF-8");
        }

    }

    private boolean isCollect;
    private static final String CANCELCOLLECT = "cancelCollect";
    private static final String ADDCOLLECT = "addCollect";

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    @OnClick({R.id.toolbar_layout_back, R.id.activity_material_detail_apply_material, R.id.activity_material_detail_to_designer,R.id.activity_material_detail_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;
            case R.id.activity_material_detail_apply_material:
                break;
            case R.id.activity_material_detail_to_designer:
                Intent intent = new Intent(this, DesignerActivity.class);
                intent.putExtra("id", designerId);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.activity_material_detail_collect:
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
                                iMaterialDetailPresent.cancelCollect(CANCELCOLLECT,id);
                            }
                        });

                        myDialogFragment.show(getSupportFragmentManager(),"dialog");
                    }else {
                        iMaterialDetailPresent.addCollect(ADDCOLLECT,id);
                    }
                }else {
                    ActivityUtils.startActivity(LoginActivity.class);
                }
                break;
        }
    }

    @Override
    public void showStringData(String requestTag, String s) {
        if (StringUtils.equals(ADDCOLLECT, requestTag)) {
            isCollect = true;
            activityMaterialDetailCollect.setImageDrawable(getResources().getDrawable(R.mipmap.collect));
        }

        if (StringUtils.equals(CANCELCOLLECT, requestTag)) {
            isCollect = false;
            activityMaterialDetailCollect.setImageDrawable(getResources().getDrawable(R.mipmap.un_collect));
            EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Collect.cancelMaterialCollect));
        }
    }
}
