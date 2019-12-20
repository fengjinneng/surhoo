package com.surhoo.sh.invoice.present;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
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
    public void requestData() {
        NetworkReturnUtil.requestList(invoiceView,activity,Api.INVOICELIST,null,InvoiceBean.class);
    }

    @Override
    public void deleteInvoice(int id,int position) {


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String s ="{\"id\":"+id+"}";

        RequestBody requestBody = RequestBody.create(JSON,s );

        PutRequest<String> request = OkGo.<String>put(Api.DELETEINVOICE)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("DELETEINVOICE", response.body());
                try {
                    if (response.code() == 200) {

                        invoiceView.getDeleteResult(position);

                    } else {
                        ToastUtils.showShort("啊哦，出现错误了！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                invoiceView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);
    }
}
