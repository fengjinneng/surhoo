package com.surhoo.sh.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.base.OneResultBaseView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.matisse.MatisseImageUtil;
import com.surhoo.sh.common.picker.AddressPickTask;
import com.surhoo.sh.common.picker.SinglePickerUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.user.bean.UserInfoBean;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.SinglePicker;
import cn.qqtheme.framework.picker.TimePicker;

public class MineInformationActivity extends BaseActivity implements OneResultBaseView<UserInfoBean> {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_mine_information_img)
    ImageView activityMineInformationImg;
    @BindView(R.id.activity_mine_information_imgLayout)
    ConstraintLayout activityMineInformationImgLayout;
    @BindView(R.id.activity_mine_information_nickName)
    TextView activityMineInformationNickName;
    @BindView(R.id.activity_mine_information_nickNameLayout)
    ConstraintLayout activityMineInformationNickNameLayout;
    @BindView(R.id.activity_mine_information_sex)
    TextView activityMineInformationSex;
    @BindView(R.id.activity_mine_information_sexLayout)
    ConstraintLayout activityMineInformationSexLayout;
    @BindView(R.id.activity_mine_information_address)
    TextView activityMineInformationAddress;
    @BindView(R.id.activity_mine_information_addressLayout)
    ConstraintLayout activityMineInformationAddressLayout;
    private String uploadProvice;
    private String uploadCity;


    @Override
    public int getContentView() {
        return R.layout.activity_mine_information;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        toolbarLayoutTitle.setText("我的资料");
    }

    @Override
    public void initData() {

    }

    private int CHOOSE_PICTURE_REQUEST_CODE = 2;

    @Override
    public void requestData() {

        NetworkReturnUtil.requestBeanResultUseGet(this, this, Api.userInfo, null, UserInfoBean.class);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            activityMineInformationImg.setImageURI(resultUri);

        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }

        if (resultCode == RESULT_OK && requestCode == CHOOSE_PICTURE_REQUEST_CODE) {
            List<Uri> mSelected = Matisse.obtainResult(data);
            String realFilePath = MatisseImageUtil.getRealFilePath(this, mSelected.get(0));
            startUCrop(this, realFilePath, UCrop.REQUEST_CROP, 1, 1);

        }
    }

    /**
     * 启动裁剪
     *
     * @param activity       上下文
     * @param sourceFilePath 需要裁剪图片的绝对路径
     * @param requestCode    比如：UCrop.REQUEST_CROP
     * @param aspectRatioX   裁剪图片宽高比
     * @param aspectRatioY   裁剪图片宽高比
     * @return
     */
    public static String startUCrop(Activity activity, String sourceFilePath,
                                    int requestCode, float aspectRatioX, float aspectRatioY) {
        Uri sourceUri = Uri.fromFile(new File(sourceFilePath));
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        //裁剪后图片的绝对路径
        String cameraScalePath = outFile.getAbsolutePath();
        Uri destinationUri = Uri.fromFile(outFile);
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(activity, R.color.white));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(activity, R.color.white));
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(true);

        //标题字的颜色以及按钮颜色
        options.setToolbarWidgetColor(ActivityCompat.getColor(activity, R.color.themeColor));//标题字的颜色以及按钮颜色

        //UCrop配置
        uCrop.withOptions(options);
        //设置裁剪图片的宽高比，比如16：9
        uCrop.withAspectRatio(aspectRatioX, aspectRatioY);
        //uCrop.useSourceImageAspectRatio();
        //跳转裁剪页面
        uCrop.start(activity, requestCode);
        return cameraScalePath;
    }


    private boolean sex;

    @OnClick({R.id.toolbar_layout_back, R.id.activity_mine_information_imgLayout,
            R.id.activity_mine_information_nickNameLayout, R.id.activity_mine_information_sexLayout,
            R.id.activity_mine_information_addressLayout, R.id.activity_mine_information_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;
            case R.id.activity_mine_information_imgLayout:
                MatisseImageUtil.chooseOnlyOnePhoto(this, CHOOSE_PICTURE_REQUEST_CODE);
                break;

            case R.id.activity_mine_information_img:
                ActivityUtils.startActivity(UpdateHeadImgActivity.class);

                break;
            case R.id.activity_mine_information_nickNameLayout:
                Intent nickNameIntent = new Intent(this, UpdateNickNameActivity.class);
                nickNameIntent.putExtra("nickName", activityMineInformationNickName.getText().toString());
                ActivityUtils.startActivity(nickNameIntent);

                break;
            case R.id.activity_mine_information_sexLayout:
                ArrayList<String> datas = new ArrayList<>();
                datas.add("男");
                datas.add("女");
                SinglePicker<String> singlePicker = SinglePickerUtil.getSinglePicker(this, datas);
                singlePicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        if (StringUtils.equals("男", item)) {
                            sex = true;
                        } else if (StringUtils.equals("女", item)) {
                            sex = false;
                        }
                        UpdateSexInfo(sex);
                    }
                });
                singlePicker.show();
                break;

            case R.id.activity_mine_information_addressLayout:

                AddressPickTask task = new AddressPickTask(this);
                task.setHideCounty(true);

                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUtils.showShort("初始化失败！");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        uploadProvice = province.getAreaName();
                        uploadCity = city.getAreaName();

                        updateAddressInfo(uploadProvice, uploadCity);
                    }
                });
                task.execute();
                break;
        }
    }

    private String requestTag;

    private void UpdateSexInfo(boolean sex) {

        requestTag = "UpdateSexInfo";
        HttpParams httpParams = new HttpParams();
        httpParams.put("sex", sex);
        NetworkReturnUtil.requestBeanResultUsePostNoBody(this, this, Api.updateuserInfo, httpParams, UserInfoBean.class);

    }

    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {
        super.onRecevieMessage(bean);

        switch (bean.getCode()) {
            case EventBusMessageBean.User.updateNickNameSuccess:
                requestData();
                break;
        }
    }

    private void updateAddressInfo(String province, String city) {

        requestTag = "updateAddressInfo";
        HttpParams httpParams = new HttpParams();
        httpParams.put("province", province);
        httpParams.put("city", city);
        NetworkReturnUtil.requestBeanResultUsePostNoBody(this, this, Api.updateuserInfo, httpParams, UserInfoBean.class);

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showBeanData(UserInfoBean userInfoBean) {

        if (StringUtils.isEmpty(requestTag)) {
            GlideUtil.loadCircleImage(this, userInfoBean.getHeadimgurl(), activityMineInformationImg);
            activityMineInformationNickName.setText(userInfoBean.getNickname());

            if (!StringUtils.isEmpty(userInfoBean.getProvince()) && !StringUtils.isEmpty(userInfoBean.getCity())) {
                activityMineInformationAddress.setText(userInfoBean.getProvince() + " " + userInfoBean.getCity());
            }

            if (!ObjectUtils.isEmpty(userInfoBean.getSex())) {
                if (userInfoBean.getSex()) {
                    activityMineInformationSex.setText("男");
                } else {
                    activityMineInformationSex.setText("女");
                }
            }
        } else {
            if (StringUtils.equals("UpdateSexInfo", requestTag)) {
                if (!ObjectUtils.isEmpty(userInfoBean.getSex())) {
                    if (userInfoBean.getSex()) {
                        activityMineInformationSex.setText("男");
                    } else {
                        activityMineInformationSex.setText("女");
                    }
                }
            } else if (StringUtils.equals("updateAddressInfo", requestTag)) {
                if (!StringUtils.isEmpty(userInfoBean.getProvince()) && !StringUtils.isEmpty(userInfoBean.getCity())) {
                    activityMineInformationAddress.setText(userInfoBean.getProvince() + " " + userInfoBean.getCity());
                }
            }
        }
    }
}
