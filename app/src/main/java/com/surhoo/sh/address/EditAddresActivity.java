package com.surhoo.sh.address;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditAddresActivity extends BaseActivity {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_edit_address_name)
    EditText activityEditAddressName;
    @BindView(R.id.activity_edit_address_phone)
    EditText activityEditAddressPhone;
    @BindView(R.id.activity_edit_address_distric)
    TextView activityEditAddressDistric;
    @BindView(R.id.activity_edit_address_detail)
    EditText activityEditAddressDetail;
    @BindView(R.id.activity_edit_address_default)
    Switch activityEditAddressDefault;
    @BindView(R.id.activity_edit_address_save)
    Button activityEditAddressSave;

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

    }

    @Override
    public void requestData() {

    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        KeyboardUtils.hideSoftInput(this);
        finish();
    }

    @OnClick(R.id.activity_edit_address_save)
    public void onSaveClicked() {


    }
}
