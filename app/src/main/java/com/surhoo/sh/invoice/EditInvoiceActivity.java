package com.surhoo.sh.invoice;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditInvoiceActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_invoice);
        ButterKnife.bind(this);

        initView();
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
    public void requestData() {

    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        KeyboardUtils.hideSoftInput(this);
        finish();
    }
}
