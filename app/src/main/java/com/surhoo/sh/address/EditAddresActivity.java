package com.surhoo.sh.address;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.address.bean.AddressBean;
import com.surhoo.sh.address.present.EditAddressPresent;
import com.surhoo.sh.address.present.EditAddressPresentImpl;
import com.surhoo.sh.address.view.EditAddressView;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.picker.AddressPickTask;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;

public class EditAddresActivity extends BaseActivity implements EditAddressView {

    private static final String ADDADDRESS = "addAddress";
    private static final String UPDATEADDRESS = "updateAddress";

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_edit_address_name)
    EditText activityEditAddressName;
    @BindView(R.id.activity_edit_address_phone)
    EditText activityEditAddressPhone;
    @BindView(R.id.activity_edit_address_province_city_district)
    TextView activityEditAddressProvinceCityDistrict;
    @BindView(R.id.activity_edit_address_detail)
    EditText activityEditAddressDetail;
    @BindView(R.id.activity_edit_address_default)
    Switch activityEditAddressDefault;
    @BindView(R.id.activity_edit_address_save)
    Button activityEditAddressSave;


    private EditAddressPresent editAddressPresent;

    private int isDefaultAddress = 1;

    //回显的bean
    private AddressBean addressBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_edit_addres;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("新增/编辑收货地址");
    }

    @Override
    public void initData() {

        editAddressPresent = new EditAddressPresentImpl();
        editAddressPresent.bindView(this, this);

        activityEditAddressDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isDefaultAddress = 1;
                } else {
                    isDefaultAddress = 0;
                }
            }
        });

        addressBean = getIntent().getParcelableExtra("addressBean");

        if (!ObjectUtils.isEmpty(addressBean)) {
            activityEditAddressName.setText(addressBean.getName());
            activityEditAddressPhone.setText(addressBean.getPhone());
            activityEditAddressProvinceCityDistrict.setText(addressBean.getProvinceName() + " " +
                    addressBean.getCityName() + " " + addressBean.getDistrictName());
            activityEditAddressDetail.setText(addressBean.getAddress());

            uploadProvice = addressBean.getProvinceName();
            uploadCity = addressBean.getCityName();
            uploadDistrict = addressBean.getDistrictName();

            if (addressBean.getDefaultStatus() == 1) {
                activityEditAddressDefault.setChecked(true);
            } else {
                activityEditAddressDefault.setChecked(false);
            }
        }
    }

    @Override
    public void requestData() {

    }


    @OnClick({R.id.toolbar_layout_back, R.id.activity_edit_address_save, R.id.activity_edit_address_province_city_district})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                KeyboardUtils.hideSoftInput(this);
                finish();
                break;
            case R.id.activity_edit_address_save:

                saveAddressInfo();

                break;
            case R.id.activity_edit_address_province_city_district:

                AddressPickTask task = new AddressPickTask(this);

                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUtils.showShort("初始化失败！");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        activityEditAddressProvinceCityDistrict.setText(province.getAreaName() + " " + city.getAreaName() + " " + county.getAreaName());
                        uploadProvice = province.getAreaName();
                        uploadCity = city.getAreaName();
                        uploadDistrict = county.getAreaName();
                    }
                });
                task.execute();

                break;
        }
    }

    private String uploadProvice;
    private String uploadCity;
    private String uploadDistrict;


    private void saveAddressInfo() {
        if (checkNull()) {
            AddressBean address = new AddressBean();
            address.setName(activityEditAddressName.getText().toString());
            address.setPhone(activityEditAddressPhone.getText().toString());
            address.setDefaultStatus(isDefaultAddress);
            address.setProvinceName(uploadProvice);
            address.setCityName(uploadCity);
            address.setDistrictName(uploadDistrict);
            address.setAddress(activityEditAddressDetail.getText().toString());

            if (ObjectUtils.isEmpty(addressBean)) {
                //保存地址
                editAddressPresent.addAddress(ADDADDRESS,address);
            } else {
                //修改地址
                address.setId(addressBean.getId());
                editAddressPresent.updateAddress(UPDATEADDRESS,address);
            }
        }
    }


    private boolean checkNull() {

        if (StringUtils.isEmpty(activityEditAddressName.getText().toString())) {
            ToastUtils.showShort("请填写收件人");
            return false;
        }

        if (StringUtils.isEmpty(activityEditAddressPhone.getText().toString())) {
            ToastUtils.showShort("请填写联系电话");
            return false;
        }

        if (!RegexUtils.isMobileSimple(activityEditAddressPhone.getText().toString())) {
            ToastUtils.showShort("请填写正确的手机号码");
            return false;
        }

        if (StringUtils.isEmpty(activityEditAddressProvinceCityDistrict.getText().toString())) {
            ToastUtils.showShort("请选择省市区信息");
            return false;
        }

        if (StringUtils.isEmpty(activityEditAddressDetail.getText().toString())) {
            ToastUtils.showShort("请填写您的详细地址");
            return false;
        }

        return true;

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);

    }

    @Override
    public void showStringData(String requestTag, String s) {
        if(StringUtils.equals(ADDADDRESS,requestTag)){
            EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Address.addAddressSuccess));

        }
        if(StringUtils.equals(UPDATEADDRESS,requestTag)){
            EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Address.updateAddressSuccess));
        }
        finish();
    }
}
