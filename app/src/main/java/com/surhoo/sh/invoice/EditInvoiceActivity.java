package com.surhoo.sh.invoice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.picker.SinglePickerUtil;
import com.surhoo.sh.invoice.bean.InvoiceBean;
import com.surhoo.sh.invoice.bean.RequestSaveInvocieBean;
import com.surhoo.sh.invoice.present.EditInvoicePresentImpl;
import com.surhoo.sh.invoice.present.IEditInvoicePresent;
import com.surhoo.sh.invoice.view.EditInvoiceView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;

public class EditInvoiceActivity extends BaseActivity implements EditInvoiceView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_edit_invoice_common_invoice_layout)
    ConstraintLayout activityEditInvoiceCommonInvoiceLayout;
    @BindView(R.id.activity_edit_invoice_common_invoice)
    TextView activityEditInvoiceCommonInvoice;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice)
    TextView activityEditInvoiceSpecialVatInvoice;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice_layout)
    ScrollView activityEditInvoiceSpecialVatInvoiceLayout;
    @BindView(R.id.activity_edit_invoice_common_invoice_title)
    EditText activityEditInvoiceCommonInvoiceTitle;
    @BindView(R.id.activity_edit_invoice_common_invoice_phone)
    EditText activityEditInvoiceCommonInvoicePhone;
    @BindView(R.id.activity_edit_invoice_common_invoice_default)
    Switch activityEditInvoiceCommonInvoiceDefault;
    @BindView(R.id.activity_edit_invoice_save)
    Button activityEditInvoiceSave;
    @BindView(R.id.activity_edit_invoice_common_invoice_taxCode_layout)
    ConstraintLayout activityEditInvoiceCommonInvoiceTaxCodeLayout;
    @BindView(R.id.activity_edit_invoice_common_invoice_type)
    TextView activityEditInvoiceCommonInvoiceType;

    @BindView(R.id.activity_edit_invoice_common_invoice_taxCode)
    EditText activityEditInvoiceCommonInvoiceTaxCode;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice_companyName)
    EditText activityEditInvoiceSpecialVatInvoiceCompanyName;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice_taxCode)
    EditText activityEditInvoiceSpecialVatInvoiceTaxCode;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice_company_address)
    EditText activityEditInvoiceSpecialVatInvoiceCompanyAddress;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice_phone)
    EditText activityEditInvoiceSpecialVatInvoicePhone;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice_bankName)
    EditText activityEditInvoiceSpecialVatInvoiceBankName;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice_bank_account)
    EditText activityEditInvoiceSpecialVatInvoiceBankAccount;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice_nickname)
    EditText activityEditInvoiceSpecialVatInvoiceNickname;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice_address)
    EditText activityEditInvoiceSpecialVatInvoiceAddress;
    @BindView(R.id.activity_edit_invoice_special_vat_invoice_default)
    Switch activityEditInvoiceSpecialVatInvoiceDefault;

    private IEditInvoicePresent editInvoicePresent;


    //1 个人 2 单位
    private int normalType = 1;

    //1 普通发票 2 增值税专用发票
    private int invoiceType = 1;

    //是否默认 0不是 1是
    private int isCommonInvoiceDefault = 1;

    private int isSpecialInvoiceDefault = 1;


    @Override
    public int getContentView() {
        return R.layout.activity_edit_invoice;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }


    //需要回显的发票信息
    private InvoiceBean invoiceBean;

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("新增/编辑发票");



        activityEditInvoiceCommonInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityEditInvoiceCommonInvoiceLayout.setVisibility(View.VISIBLE);
                activityEditInvoiceSpecialVatInvoiceLayout.setVisibility(View.GONE);
                activityEditInvoiceCommonInvoice.setTextColor(getResources().getColor(R.color.pageTitle));
                activityEditInvoiceSpecialVatInvoice.setTextColor(getResources().getColor(R.color.saleColor));
                invoiceType = 1;
            }
        });

        activityEditInvoiceSpecialVatInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityEditInvoiceCommonInvoiceLayout.setVisibility(View.GONE);
                activityEditInvoiceSpecialVatInvoiceLayout.setVisibility(View.VISIBLE);
                activityEditInvoiceCommonInvoice.setTextColor(getResources().getColor(R.color.saleColor));
                activityEditInvoiceSpecialVatInvoice.setTextColor(getResources().getColor(R.color.pageTitle));
                invoiceType = 2;
            }
        });

        activityEditInvoiceCommonInvoiceDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isCommonInvoiceDefault = 1;
                } else {
                    isCommonInvoiceDefault = 0;
                }
            }
        });

        activityEditInvoiceSpecialVatInvoiceDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isSpecialInvoiceDefault = 1;
                } else {
                    isSpecialInvoiceDefault = 0;
                }
            }
        });


        invoiceBean = getIntent().getParcelableExtra("invoiceBean");

        if (!ObjectUtils.isEmpty(invoiceBean)) {
            normalType = invoiceBean.getNormalType();
            invoiceType = invoiceBean.getInvoiceType();

            if (invoiceBean.getNormalType() == 1 && invoiceBean.getInvoiceType() == 1) {
                activityEditInvoiceCommonInvoiceTitle.setText(invoiceBean.getTitle());
                activityEditInvoiceCommonInvoicePhone.setText(invoiceBean.getMobile());
                activityEditInvoiceCommonInvoiceDefault.setChecked(invoiceBean.getDefaultStatus());
            } else if (invoiceBean.getNormalType() == 2 && invoiceBean.getInvoiceType() == 1) {
                activityEditInvoiceCommonInvoiceType.setText("企业");
                activityEditInvoiceCommonInvoiceTitle.setText(invoiceBean.getTitle());
                activityEditInvoiceCommonInvoicePhone.setText(invoiceBean.getMobile());
                activityEditInvoiceCommonInvoiceDefault.setChecked(invoiceBean.getDefaultStatus());
                activityEditInvoiceCommonInvoiceTaxCodeLayout.setVisibility(View.VISIBLE);
                activityEditInvoiceCommonInvoiceTaxCode.setText(invoiceBean.getTaxCode());

            } else if (invoiceBean.getInvoiceType() == 2) {
                activityEditInvoiceCommonInvoiceLayout.setVisibility(View.GONE);
                activityEditInvoiceSpecialVatInvoiceLayout.setVisibility(View.VISIBLE);
                activityEditInvoiceCommonInvoice.setTextColor(getResources().getColor(R.color.saleColor));
                activityEditInvoiceSpecialVatInvoice.setTextColor(getResources().getColor(R.color.pageTitle));
                activityEditInvoiceSpecialVatInvoiceCompanyName.setText(invoiceBean.getEnterpriseName());
                activityEditInvoiceSpecialVatInvoiceTaxCode.setText(invoiceBean.getTaxCode());
                activityEditInvoiceSpecialVatInvoiceCompanyAddress.setText(invoiceBean.getLoginAddress());
                activityEditInvoiceSpecialVatInvoicePhone.setText(invoiceBean.getMobile());
                activityEditInvoiceSpecialVatInvoiceBankName.setText(invoiceBean.getBankName());
                activityEditInvoiceSpecialVatInvoiceBankAccount.setText(invoiceBean.getAccount());
                activityEditInvoiceSpecialVatInvoiceNickname.setText(invoiceBean.getNickname());
                activityEditInvoiceSpecialVatInvoiceAddress.setText(invoiceBean.getAddress());
                activityEditInvoiceSpecialVatInvoiceDefault.setChecked(invoiceBean.getDefaultStatus());
            }
        }
    }

    @Override
    public void initData() {

        editInvoicePresent = new EditInvoicePresentImpl();
        editInvoicePresent.bindView(this, this);

    }

    @Override
    public void requestData() {

    }

    @OnClick({R.id.toolbar_layout_back, R.id.activity_edit_invoice_save, R.id.activity_edit_invoice_common_invoice_type})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                KeyboardUtils.hideSoftInput(this);
                finish();
                break;
            case R.id.activity_edit_invoice_save:
                //普通个人发票
                if (normalType == 1 && invoiceType == 1) {
                    if (checkNormalInvoceNull()) {
                        RequestSaveInvocieBean requestSaveInvocieBean = new RequestSaveInvocieBean();

                        if (StringUtils.isEmpty(activityEditInvoiceCommonInvoiceTitle.getText().toString())) {
                            requestSaveInvocieBean.setTitle("个人");
                        } else {
                            requestSaveInvocieBean.setTitle(activityEditInvoiceCommonInvoiceTitle.getText().toString());
                        }
                        requestSaveInvocieBean.setContent("商品明细");
                        requestSaveInvocieBean.setMobile(activityEditInvoiceCommonInvoicePhone.getText().toString());
                        requestSaveInvocieBean.setNormalType(normalType);
                        requestSaveInvocieBean.setDefaultStatus(isCommonInvoiceDefault);
                        requestSaveInvocieBean.setInvoiceType(invoiceType);
                        editInvoicePresent.saveInvocieInfo(requestSaveInvocieBean);

                    }

                } else if (normalType == 2 && invoiceType == 1) {
                    //企业普通发票
                    if (checkNormalCompanyInvoceNull()) {
                        RequestSaveInvocieBean requestSaveInvocieBean = new RequestSaveInvocieBean();

                        if (StringUtils.isEmpty(activityEditInvoiceCommonInvoiceTitle.getText().toString())) {
                            requestSaveInvocieBean.setTitle("个人");
                        } else {
                            requestSaveInvocieBean.setTitle(activityEditInvoiceCommonInvoiceTitle.getText().toString());
                        }
                        requestSaveInvocieBean.setContent("商品明细");
                        requestSaveInvocieBean.setMobile(activityEditInvoiceCommonInvoicePhone.getText().toString());
                        requestSaveInvocieBean.setTaxCode(activityEditInvoiceCommonInvoiceTaxCode.getText().toString());
                        requestSaveInvocieBean.setNormalType(normalType);
                        requestSaveInvocieBean.setDefaultStatus(isCommonInvoiceDefault);
                        requestSaveInvocieBean.setInvoiceType(invoiceType);
                        editInvoicePresent.saveInvocieInfo(requestSaveInvocieBean);

                    }

                } else if (invoiceType == 2) {
                    //增值税发票
                    if (checkSpecialValNull()) {
                        RequestSaveInvocieBean requestSaveInvocieBean = new RequestSaveInvocieBean();

                        requestSaveInvocieBean.setContent("商品明细");
                        requestSaveInvocieBean.setEnterpriseName(activityEditInvoiceSpecialVatInvoiceCompanyName.getText().toString());
                        requestSaveInvocieBean.setTaxCode(activityEditInvoiceSpecialVatInvoiceTaxCode.getText().toString());
                        requestSaveInvocieBean.setLoginAddress(activityEditInvoiceSpecialVatInvoiceCompanyAddress.getText().toString());
                        requestSaveInvocieBean.setMobile(activityEditInvoiceSpecialVatInvoicePhone.getText().toString());
                        requestSaveInvocieBean.setBankName(activityEditInvoiceSpecialVatInvoiceBankName.getText().toString());
                        requestSaveInvocieBean.setAccount(activityEditInvoiceSpecialVatInvoiceBankAccount.getText().toString());
                        requestSaveInvocieBean.setNickname(activityEditInvoiceSpecialVatInvoiceNickname.getText().toString());
                        requestSaveInvocieBean.setAddress(activityEditInvoiceSpecialVatInvoiceAddress.getText().toString());
                        requestSaveInvocieBean.setNormalType(normalType);
                        requestSaveInvocieBean.setDefaultStatus(isSpecialInvoiceDefault);
                        requestSaveInvocieBean.setInvoiceType(invoiceType);

                        editInvoicePresent.saveInvocieInfo(requestSaveInvocieBean);
                    }
                }


                break;
            case R.id.activity_edit_invoice_common_invoice_type:

                if (ObjectUtils.isEmpty(typeSinglePicker)) {
                    ArrayList<String> s = new ArrayList<>();
                    s.add("个人");
                    s.add("企业");
                    typeSinglePicker = SinglePickerUtil.getSinglePicker(this,s);
                    typeSinglePicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
                        @Override
                        public void onItemPicked(int index, String item) {
                            if (StringUtils.equals("个人", item)) {
                                normalType = 1;
                                activityEditInvoiceCommonInvoiceType.setText("个人");
                                activityEditInvoiceCommonInvoiceTaxCodeLayout.setVisibility(View.GONE);
                            } else {
                                normalType = 2;
                                activityEditInvoiceCommonInvoiceType.setText("企业");
                                activityEditInvoiceCommonInvoiceTaxCodeLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                } else {
                    if (typeSinglePicker.isShowing()) {
                        typeSinglePicker.dismiss();
                    } else {
                        typeSinglePicker.show();
                    }
                }

                break;
        }
    }

    private boolean checkSpecialValNull() {
        if (StringUtils.isEmpty(activityEditInvoiceSpecialVatInvoiceCompanyName.getText().toString())) {
            ToastUtils.showShort("请填写单位名称!");
            return false;
        }

        if (StringUtils.isEmpty(activityEditInvoiceSpecialVatInvoiceCompanyAddress.getText().toString())) {
            ToastUtils.showShort("请填写单位注册地址!");
            return false;
        }

        if (StringUtils.isEmpty(activityEditInvoiceSpecialVatInvoicePhone.getText().toString())) {
            ToastUtils.showShort("请填写注册电话!");
            return false;
        }

        if (!RegexUtils.isMobileSimple(activityEditInvoiceSpecialVatInvoicePhone.getText().toString())) {
            ToastUtils.showShort("请填写正确的注册电话!");
            return false;
        }

        if (StringUtils.isEmpty(activityEditInvoiceSpecialVatInvoiceBankName.getText().toString())) {
            ToastUtils.showShort("请填写开户银行!");
            return false;
        }

        if (StringUtils.isEmpty(activityEditInvoiceSpecialVatInvoiceBankAccount.getText().toString())) {
            ToastUtils.showShort("请填写银行账户!");
            return false;
        }

        if (StringUtils.isEmpty(activityEditInvoiceSpecialVatInvoiceNickname.getText().toString())) {
            ToastUtils.showShort("请填写昵称!");
            return false;
        }

        if (StringUtils.isEmpty(activityEditInvoiceSpecialVatInvoiceAddress.getText().toString())) {
            ToastUtils.showShort("请填写地址信息!");
            return false;
        }

        return true;

    }

    private boolean checkNormalCompanyInvoceNull() {

        if (StringUtils.isEmpty(activityEditInvoiceCommonInvoicePhone.getText().toString())) {
            ToastUtils.showShort("请填写手机号!");
            return false;
        }

        if (!RegexUtils.isMobileSimple(activityEditInvoiceCommonInvoicePhone.getText().toString())) {
            ToastUtils.showShort("请填写正确的手机号码!");
            return false;
        }

        if (StringUtils.isEmpty(activityEditInvoiceCommonInvoiceTaxCode.getText().toString())) {
            ToastUtils.showShort("请填写纳税人识别码!");
            return false;

        }
        return true;
    }

    private boolean checkNormalInvoceNull() {

        if (StringUtils.isEmpty(activityEditInvoiceCommonInvoicePhone.getText().toString())) {
            ToastUtils.showShort("请填写手机号!");
            return false;
        }

        if (!RegexUtils.isMobileSimple(activityEditInvoiceCommonInvoicePhone.getText().toString())) {
            ToastUtils.showShort("请填写正确的手机号码!");
            return false;
        }
        return true;

    }


    private SinglePicker<String> typeSinglePicker;

    @Override
    public void getResult() {
        EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.Invoice.addInvoiceSuccess));
        finish();

    }


}
