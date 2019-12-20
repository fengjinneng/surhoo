package com.surhoo.sh.order.present;

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
import com.lzy.okgo.request.PostRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.DialogStringCallback;
import com.surhoo.sh.common.util.MyJsonUtil;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.order.bean.UpLoadEvaluationBean;
import com.surhoo.sh.order.view.IOrderEvaluationView;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OrderEvaluationPresentImpl implements IOrderEvaluationPresent {


    private Activity activity;


    private IOrderEvaluationView orderEvaluationView;


    @Override
    public void bindView(Activity activity, IOrderEvaluationView view) {

        this.activity = activity;
        this.orderEvaluationView = view;

    }

    @Override
    public void unBindView() {
        this.orderEvaluationView = null;
    }


    @Override
    public void saveEvaluation(ArrayList<UpLoadEvaluationBean> upLoadEvaluationBean) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String s = JSONObject.toJSONString(upLoadEvaluationBean);

        RequestBody requestBody = RequestBody.create(JSON,s );

        PostRequest<String> request = OkGo.<String>post(Api.addOrderEvaluate)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("addOrderEvaluate", response.body());
                try {
                    if (response.code() == 200) {

                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());

                            if(StringUtils.isEmpty(jsonObject.getString("msg"))){

                            }else {
                                orderEvaluationView.showToastMsg(jsonObject.getString("msg"));
                                return;
                            }
                        }

                        orderEvaluationView.getAddEvaluationResult();

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
                orderEvaluationView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);




    }
}
