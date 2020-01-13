package com.surhoo.sh.invoice.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.invoice.view.InvoiceView;

public interface InvoicePresenter extends BasePresenter<InvoiceView> {


    void requestInvoiceList(String requestTag);
    void deleteInvoice(String requestTag,int id);

}
