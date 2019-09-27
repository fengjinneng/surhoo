package com.surhoo.sh.invoice;

import android.content.Context;

import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;

public class InvoicePresenterImpl implements InvoicePresenter {

    private Context context;
    private InvoiceView invoiceView;

    @Override
    public void bindView(Context ctx, InvoiceView view) {
        this.context = ctx;
        this.invoiceView = view;
    }

    @Override
    public void unBindView() {
        invoiceView = null;
    }

    @Override
    public void requestData() {
        NetworkReturnUtil.requestList(invoiceView,context,Api.INVOICELIST,null,InvoiceBean.class);
    }
}
