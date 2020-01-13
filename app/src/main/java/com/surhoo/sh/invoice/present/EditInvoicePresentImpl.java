package com.surhoo.sh.invoice.present;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.PutRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.invoice.bean.RequestSaveInvocieBean;
import com.surhoo.sh.invoice.view.EditInvoiceView;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EditInvoicePresentImpl implements IEditInvoicePresent {


    private Activity activity;
    private EditInvoiceView editInvoiceView;


    @Override
    public void bindView(Activity activity, EditInvoiceView view) {

        this.activity = activity;

        this.editInvoiceView = view;

    }

    @Override
    public void unBindView() {
        editInvoiceView = null;
    }

    @Override
    public void addInvoice(String requestTag,RequestSaveInvocieBean bean) {
        String s = JSONObject.toJSONString(bean);
        NetworkReturnUtil.requestStringResultUsePost(requestTag,editInvoiceView,activity,Api.SAVEINVOICEINFO,s);


    }
}
