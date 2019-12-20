package com.surhoo.sh.invoice.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.invoice.view.InvoiceView;

public interface InvoicePresenter extends BasePresenter<InvoiceView> {


    void requestData();
    void deleteInvoice(int id,int position);

}
