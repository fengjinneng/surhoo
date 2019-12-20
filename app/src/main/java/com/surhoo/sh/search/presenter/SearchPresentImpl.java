package com.surhoo.sh.search.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.DialogStringCallback;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.home.bean.HomePageBean;
import com.surhoo.sh.search.view.SearchView;

public class SearchPresentImpl implements ISearchPresent {


    private Activity activity;

    private SearchView searchView;


    @Override
    public void bindView(Activity activity, SearchView view) {

        this.activity = activity;
        this.searchView = view;
    }

    @Override
    public void unBindView() {
        this.searchView = null;
    }

    @Override
    public void requestData(String searchName) {
        GetRequest<String> request = OkGo.<String>get(Api.SEARCHALL)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .params("searchName",searchName);

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("SEARCHALL", response.body());
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = JSONObject.parseObject(response.body());
                        if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                            ToastUtils.showShort(jsonObject.getString("msg"));
                            return;
                        }

                        searchView.showData(JSON.parseObject(jsonObject.toString(), HomePageBean.class));

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
                searchView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);
    }
}
