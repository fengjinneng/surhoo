package com.surhoo.sh.common.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.PutRequest;
import com.surhoo.sh.App;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.base.OneResultBaseView;
import com.surhoo.sh.base.NoPageListBaseView;
import com.surhoo.sh.base.HavePageListBaseView;
import com.surhoo.sh.base.StringResultBaseView;
import com.surhoo.sh.common.Api;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class NetworkReturnUtil {

    public static final String TAG = "NetworkReturnUtil";



    public static void requestStringResultUseGet(String requestTag, StringResultBaseView baseView, Activity activity, String url, HttpParams httpParams) {


        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("当前网络未连接！");
            return;
        }

        GetRequest<String> request = OkGo.<String>get(url)
                .tag(activity)
                .headers("Authorization", SPUtils.getInstance().getString("token"))
                .params(httpParams);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());

                            if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                                ToastUtils.showShort(jsonObject.getString("msg"));
                                return;
                            }
                        }

                        baseView.showStringData(requestTag, response.body());

                    } else {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            baseView.showToastMsg(jsonObject.getString("msg"));
                        }
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


    public static void requestStringResultUsePost(String requestTag, StringResultBaseView baseView, Activity activity, String url, String upData) {


        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("当前网络未连接！");
            return;
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(JSON, upData);

        PostRequest<String> request = OkGo.<String>post(url)
                .tag(activity)
                .headers("Authorization", SPUtils.getInstance().getString("token"))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());

                            if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                                ToastUtils.showShort(jsonObject.getString("msg"));
                                return;
                            }
                        }

                        baseView.showStringData(requestTag, response.body());

                    } else {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            baseView.showToastMsg(jsonObject.getString("msg"));
                        }
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


    public static void requestStringResultUsePut(String requestTag, StringResultBaseView baseView, Activity activity, String url, String upData) {

        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("当前网络未连接！");
            return;
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(JSON, upData);

        PutRequest<String> request = OkGo.<String>put(url)
                .tag(activity)
                .headers("Authorization", SPUtils.getInstance().getString("token"))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());

                            if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                                ToastUtils.showShort(jsonObject.getString("msg"));
                                return;
                            }
                        }

                        baseView.showStringData(requestTag, response.body());

                    } else {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            baseView.showToastMsg(jsonObject.getString("msg"));
                        }
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

    //请求需要分页的数据
    public static void requestHavePageList(HavePageListBaseView baseView, Activity activity, String url,
                                           HttpParams params, Class clz, int pageIndex) {

        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("当前网络未连接！");
            baseView.setHavePageErrorView();
            return;
        }

        GetRequest<String> request = OkGo.<String>get(url)
                .tag(activity)
                .headers("Authorization", SPUtils.getInstance().getString("token"))
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
                        if (jsonObject.getIntValue("total") == 0) {
                            baseView.setHavePageEmptyView();
                            return;
                        }
                        List beans = JSONObject.parseArray(jsonObject.getString("list"), clz);

                        boolean hasNextPage = jsonObject.getBooleanValue("hasNextPage");
                        if (pageIndex == 1) {
                            baseView.firstLoadData(beans);
                            if (!hasNextPage) {
                                baseView.loadDataEnd();
                            }
                            return;
                        }
                        baseView.loadData(beans);
                        if (!hasNextPage) {
                            baseView.loadDataEnd();
                        }

                    } else {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            baseView.showToastMsg(jsonObject.getString("msg"));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                baseView.setHavePageErrorView();

            }
        };

        request.execute(stringCallback);

    }


    public static void requestBeanResultUsePut(OneResultBaseView baseView, Activity activity, String url, String upData, Class clz) {


        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("当前网络未连接！");
            return;
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(JSON, upData);

        PutRequest<String> request = OkGo.<String>put(url)
                .tag(activity)
                .headers("Authorization", SPUtils.getInstance().getString("token"))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());

                            if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                                ToastUtils.showShort(jsonObject.getString("msg"));
                                return;
                            }
                        }

                        baseView.showBeanData(JSONObject.parseObject(response.body(), clz));
//                        JSON.parseObject(JSON.toJSONString(jsonObject.toString()), clz);
                        //属性值为大写的时候拿不到
//                       jsonObject.toJavaObject(clz);

                    } else {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            baseView.showToastMsg(jsonObject.getString("msg"));
                        }
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

    //请求没有分页的数据，如详情页，展示数据时封装成一个对象
    public static void requestBeanResultUsePost(OneResultBaseView baseView, Activity activity, String url, String upData, Class clz) {


        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("当前网络未连接！");
            return;
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(JSON, upData);

        PostRequest<String> request = OkGo.<String>post(url)
                .tag(activity)
                .headers("Authorization", SPUtils.getInstance().getString("token"))
                .upRequestBody(requestBody);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());

                            if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                                ToastUtils.showShort(jsonObject.getString("msg"));
                                return;
                            }
                        }

                        baseView.showBeanData(JSONObject.parseObject(response.body(), clz));
//                        JSON.parseObject(JSON.toJSONString(jsonObject.toString()), clz);
                        //属性值为大写的时候拿不到
//                       jsonObject.toJavaObject(clz);

                    } else {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            baseView.showToastMsg(jsonObject.getString("msg"));
                        }
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

    //请求没有分页的数据，如详情页，展示数据时封装成一个对象
    public static void requestBeanResultUsePostNoBody(OneResultBaseView baseView, Activity activity, String url, HttpParams httpParams, Class clz) {


        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("当前网络未连接！");
            return;
        }



        PostRequest<String> request = OkGo.<String>post(url)
                .tag(activity)
                .headers("Authorization", SPUtils.getInstance().getString("token"))
                .params(httpParams);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());

                            if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                                ToastUtils.showShort(jsonObject.getString("msg"));
                                return;
                            }
                        }

                        baseView.showBeanData(JSONObject.parseObject(response.body(), clz));
//                        JSON.parseObject(JSON.toJSONString(jsonObject.toString()), clz);
                        //属性值为大写的时候拿不到
//                       jsonObject.toJavaObject(clz);

                    } else {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            baseView.showToastMsg(jsonObject.getString("msg"));
                        }
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


    //请求没有分页的数据，如详情页，展示数据时封装成一个对象
    public static void requestBeanResultUseGet(OneResultBaseView baseView, Activity activity, String url, HttpParams params, Class clz) {

        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("当前网络未连接！");
            return;
        }

        GetRequest<String> request = OkGo.<String>get(url)
                .tag(activity)
                .headers("Authorization", SPUtils.getInstance().getString("token"))
                .params(params);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());

                            if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                                ToastUtils.showShort(jsonObject.getString("msg"));
                                return;
                            }
                        }

                        baseView.showBeanData(JSONObject.parseObject(response.body(), clz));
//                        JSON.parseObject(JSON.toJSONString(jsonObject.toString()), clz);
                        //属性值为大写的时候拿不到
//                       jsonObject.toJavaObject(clz);

                    } else {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            baseView.showToastMsg(jsonObject.getString("msg"));
                        }
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


    //请求没有分页的集合
    public static void requestNoPageList(String requestTag, NoPageListBaseView baseView, Activity activity, String url, HttpParams params, Class clz) {

        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("当前网络未连接！");
            baseView.setNoPageErrorView();
            return;
        }

        GetRequest<String> request = OkGo.<String>get(url)
                .tag(activity)
                .headers("Authorization",SPUtils.getInstance().getString("token"))
                .params(params);

        StringCallback stringCallback = new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if (MyJsonUtil.isJsonArray(response.body())) {
                            List beans = JSONObject.parseArray(response.body(), clz);
                            if (beans.size() == 0) {
                                baseView.setNoPageEmptyView();
                                return;
                            }
                            baseView.showNoPageList(requestTag, beans);
                        } else {
                            if (MyJsonUtil.isJson(response.body())) {
                                JSONObject jsonObject = JSONObject.parseObject(response.body());
                                baseView.showToastMsg(jsonObject.getString("msg"));
                            }
                        }

                    } else {
                        if (MyJsonUtil.isJson(response.body())) {
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            baseView.showToastMsg(jsonObject.getString("msg"));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                baseView.setNoPageErrorView();
            }
        };
        request.execute(stringCallback);

    }
}
