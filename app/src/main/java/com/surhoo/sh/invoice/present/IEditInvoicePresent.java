package com.surhoo.sh.invoice.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.invoice.bean.RequestSaveInvocieBean;
import com.surhoo.sh.invoice.view.EditInvoiceView;

public interface IEditInvoicePresent extends BasePresenter<EditInvoiceView> {

    void addInvoice(String requestTag,RequestSaveInvocieBean bean);

}
