package com.surhoo.sh.invoice;

import android.app.Activity;
import android.content.Context;

import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;

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
