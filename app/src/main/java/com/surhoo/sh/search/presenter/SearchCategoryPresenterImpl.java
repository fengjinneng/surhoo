package com.surhoo.sh.search.presenter;

import android.app.Activity;
import android.content.Intent;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
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
import com.surhoo.sh.common.util.MyJsonUtil;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.designer.bean.DesignerLabelBean;
import com.surhoo.sh.designer.bean.DesignerListBean;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.view.GoodsListActivity;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.material.bean.MaterialLabelBean;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.search.view.SearchCategoryView;
import com.surhoo.sh.shop.ShopListBean;

import java.util.List;

public class SearchCategoryPresenterImpl implements SearchCategoryPresenter {

    public static final String TAG = "SearchCategoryPresenterImpl";

    private SearchCategoryView searchCategoryView;
    private Activity activity;

    @Override
    public void bindView(Activity activity, SearchCategoryView view) {
        this.searchCategoryView = view;
        this.activity = activity;
    }

    @Override
    public void unBindView() {
        searchCategoryView = null;
    }

    @Override
    public void requestData(int type, String searchName, int pageSize, int pageIndex,  String idList) {

        HttpParams httpParams = new HttpParams();

        httpParams.put("type", type);
        httpParams.put("searchName", searchName);
        httpParams.put("pageSize", pageSize);
        httpParams.put("pageIndex", pageIndex);
//        httpParams.put("sortType", sortType);
        httpParams.put("idList", idList);

        //1 商品 2 场景 3 店铺 4 设计师 5素材
        switch (type) {
            case 1:
                Intent intent = new Intent(activity, GoodsListActivity.class);
                intent.putExtra("from",1);
                intent.putExtra("searchName",searchName);
                ActivityUtils.startActivity(intent);
                break;
            case 2:
                NetworkReturnUtil.requestPage(searchCategoryView, activity, Api.SEARCHCATEGORY, httpParams, ScenarioBean.class, pageIndex);
                break;
            case 3:
                NetworkReturnUtil.requestPage(searchCategoryView, activity, Api.SEARCHCATEGORY, httpParams, ShopListBean.class, pageIndex);
                break;
            case 4:
                NetworkReturnUtil.requestPage(searchCategoryView, activity, Api.SEARCHCATEGORY, httpParams, DesignerListBean.class, pageIndex);
                break;
            case 5:
                NetworkReturnUtil.requestPage(searchCategoryView, activity, Api.SEARCHCATEGORY, httpParams, MaterialBean.class, pageIndex);
                break;
        }

    }

    @Override
    public void requestDesignerLabel() {
        GetRequest<String> request = OkGo.<String>get(Api.designerLabel)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth));

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("designerLabel", response.body());
                try {
                    if (response.code() == 200) {

                        if(StringUtils.isEmpty(response.body())){
                            return;
                        }

                        List beans = JSONObject.parseArray(response.body(), DesignerLabelBean.class);
                        searchCategoryView.showDesignerCategory(beans);

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
                searchCategoryView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);
    }

    @Override
    public void requestMaterialLabel() {
        GetRequest<String> request = OkGo.<String>get(Api.materialLabel)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth));

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("designerLabel", response.body());
                try {
                    if (response.code() == 200) {

                        if(StringUtils.isEmpty(response.body())){
                            return;
                        }

                        List beans = JSONObject.parseArray(response.body(), MaterialLabelBean.class);
                        searchCategoryView.showMaterialLabel(beans);

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
                searchCategoryView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);
    }

}
