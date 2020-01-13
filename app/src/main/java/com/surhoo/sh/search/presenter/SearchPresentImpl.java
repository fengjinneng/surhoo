package com.surhoo.sh.search.presenter;

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
        HttpParams httpParams = new HttpParams();
        httpParams.put("searchName",searchName);

        NetworkReturnUtil.requestBeanResultUseGet(searchView,activity,Api.SEARCHALL,httpParams,HomePageBean.class);

    }
}
