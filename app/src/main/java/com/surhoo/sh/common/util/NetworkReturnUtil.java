package com.surhoo.sh.common.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

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
import com.surhoo.sh.R;
import com.surhoo.sh.base.NoPageBaseView;
import com.surhoo.sh.base.NoPageListBaseView;
import com.surhoo.sh.base.PagerBaseView;

import java.util.List;

public class NetworkReturnUtil {

    public static final String TAG = "NetworkReturnUtil";

    //请求需要分页的数据
    public static void requestPage(PagerBaseView baseView, Activity activity, String url, HttpParams params, Class clz, int pageIndex) {

        GetRequest<String> request = OkGo.<String>get(url)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .params(params);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = JSONObject.parseObject(response.body());

                        if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                            ToastUtils.showShort(jsonObject.getString("msg"));
                            return;
                        }
                        if (jsonObject.getIntValue("total") == 0) {
                            return;
                        }
                        List beans = JSONObject.parseArray(jsonObject.getString("list"), clz);

                        boolean hasNextPage = jsonObject.getBooleanValue("hasNextPage");
                        if (pageIndex == 1) {
                            baseView.refresh(beans);
                            if (!hasNextPage) {
                                baseView.loadEnd();
                            }
                            return;
                        }
                        baseView.loadData(beans);
                        if (!hasNextPage) {
                            baseView.loadEnd();
                        }

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
                baseView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);

    }


    //请求没有分页的数据，如详情页，展示数据时封装成一个对象
    public static void requestOne(NoPageBaseView baseView, Activity activity, String url, HttpParams params, Class clz) {
        GetRequest<String> request = OkGo.<String>get(url)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .params(params);

        StringCallback stringCallback = new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = JSONObject.parseObject(response.body());
                        if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                            ToastUtils.showShort(jsonObject.getString("msg"));
                            return;
                        }

                        baseView.showData(JSON.parseObject(jsonObject.toString(), clz));

//                        JSON.parseObject(JSON.toJSONString(jsonObject.toString()), clz);

                        //属性值为大写的时候拿不到
//                       jsonObject.toJavaObject(clz);

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
                baseView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);

    }


    //请求没有分页的集合
    public static void requestList(NoPageListBaseView baseView, Activity activity, String url, HttpParams params, Class clz) {
        GetRequest<String> request = OkGo.<String>get(url)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .params(params);

        StringCallback stringCallback = new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if(StringUtils.isEmpty(response.body())){
                            return;
                        }

                        List beans = JSONObject.parseArray(response.body(), clz);
                        baseView.showList(beans);

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
                baseView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);

    }
}
