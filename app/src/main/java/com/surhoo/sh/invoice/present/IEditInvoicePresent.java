package com.surhoo.sh.invoice.present;

import com.alibaba.fastjson.JSONObject;
import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.invoice.view.EditInvoiceView;

public interface IEditInvoicePresent extends BasePresenter<EditInvoiceView> {

    void saveInvocieInfo(String s);

}
