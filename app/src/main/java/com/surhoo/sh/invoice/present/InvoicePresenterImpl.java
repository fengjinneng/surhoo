package com.surhoo.sh.invoice.present;

import android.app.Activity;

import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.invoice.view.InvoiceView;
import com.surhoo.sh.invoice.bean.InvoiceBean;

public class InvoicePresenterImpl implements InvoicePresenter {

    private Activity activity;
    private InvoiceView invoiceView;

    @Override
    public void bindView(Activity activity, InvoiceView view) {
        this.activity = activity;
        this.invoiceView = view;
    }

    @Override
    public void unBindView() {
        invoiceView = null;
    }

    @Override
    public void requestData() {
        NetworkReturnUtil.requestList(invoiceView,activity,Api.INVOICELIST,null,InvoiceBean.class);
    }
}
