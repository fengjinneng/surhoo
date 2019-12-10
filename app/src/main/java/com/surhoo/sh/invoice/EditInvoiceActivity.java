package com.surhoo.sh.invoice;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.invoice.bean.RequestSaveInvocieBean;
import com.surhoo.sh.invoice.present.EditInvoicePresentImpl;
import com.surhoo.sh.invoice.present.IEditInvoicePresent;
import com.surhoo.sh.invoice.view.EditInvoiceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.activity_edit_invoice_common_invoice_content)
    EditText activityEditInvoiceCommonInvoiceContent;
    @BindView(R.id.activity_edit_invoice_common_invoice_phone)
    EditText activityEditInvoiceCommonInvoicePhone;
    @BindView(R.id.activity_edit_invoice_common_invoice_default)
    Switch activityEditInvoiceCommonInvoiceDefault;
    @BindView(R.id.activity_edit_invoice_save)
    Button activityEditInvoiceSave;

    private IEditInvoicePresent editInvoicePresent;


    // 1 为个人的普通发票  2为企业的普通发票  3为企业的增值税发票
    private int invoiceType = 1;


    @Override
    public int getContentView() {
        return R.layout.activity_edit_invoice;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("新增/编辑发票");

        activityEditInvoiceCommonInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityEditInvoiceCommonInvoiceLayout.setVisibility(View.VISIBLE);
                activityEditInvoiceSpecialVatInvoiceLayout.setVisibility(View.GONE);
            }
        });

        activityEditInvoiceSpecialVatInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityEditInvoiceCommonInvoiceLayout.setVisibility(View.GONE);
                activityEditInvoiceSpecialVatInvoiceLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void initData() {

        editInvoicePresent = new EditInvoicePresentImpl();
        editInvoicePresent.bindView(this, this);

    }

    @Override
    public void requestData() {

    }

    @OnClick({R.id.toolbar_layout_back, R.id.activity_edit_invoice_save})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                KeyboardUtils.hideSoftInput(this);
                finish();
                break;
            case R.id.activity_edit_invoice_save:
                RequestSaveInvocieBean requestSaveInvocieBean = new RequestSaveInvocieBean();

                requestSaveInvocieBean.setTitle(activityEditInvoiceCommonInvoiceTitle.getText().toString());
                requestSaveInvocieBean.setContent(activityEditInvoiceCommonInvoiceContent.getText().toString());
                requestSaveInvocieBean.setMobile(activityEditInvoiceCommonInvoicePhone.getText().toString());
                requestSaveInvocieBean.setNormalType(1);
                requestSaveInvocieBean.setDefaultStatus(1);
                requestSaveInvocieBean.setInvoiceType(1);

                editInvoicePresent.saveInvocieInfo(requestSaveInvocieBean.beCommonPersonal());
                break;

        }

    }

    @Override
    public void getResult() {

        ToastUtils.showShort("擦汗如成功");

    }


}
