package com.surhoo.sh.invoice.present;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PutRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.DialogStringCallback;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.invoice.view.InvoiceView;
import com.surhoo.sh.invoice.bean.InvoiceBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;

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
    public void requestInvoiceList(String requestTag) {
        NetworkReturnUtil.requestNoPageList(requestTag,invoiceView,activity,Api.INVOICELIST,null,InvoiceBean.class);
    }

    @Override
    public void deleteInvoice(String requestTag,int id) {

        String s ="{\"id\":"+id+"}";

        NetworkReturnUtil.requestStringResultUsePut(requestTag,invoiceView,activity,Api.DELETEINVOICE,s);
    }

}
