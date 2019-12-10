package com.surhoo.sh.invoice.present;

import android.app.Activity;

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
    public void saveInvocieInfo(String s) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON,s);

        PostRequest<String> request = OkGo.<String>post(Api.SAVEINVOICEINFO)
                .tag(activity)
                .upRequestBody(requestBody)
                .headers("Authorization", activity.getResources().getString(R.string.Auth));

        StringCallback stringCallback = new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("SAVEINVOICEINFO", response.body());
                try {
                    if (response.code() == 200) {

                        editInvoiceView.getResult();

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
            }
        };

        request.execute(stringCallback);

    }
}
