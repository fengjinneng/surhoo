package com.surhoo.sh.search.presenter;

import android.app.Activity;
import android.content.Intent;

import com.blankj.utilcode.util.ActivityUtils;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.designer.bean.DesignerLabelBean;
import com.surhoo.sh.designer.bean.DesignerListBean;
import com.surhoo.sh.goods.view.GoodsListActivity;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.material.bean.MaterialLabelBean;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.search.view.SearchCategoryView;
import com.surhoo.sh.shop.bean.ShopListBean;

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
                NetworkReturnUtil.requestHavePageList(searchCategoryView, activity, Api.SEARCHCATEGORY, httpParams, ScenarioBean.class, pageIndex);
                break;
            case 3:
                //0为普通店铺
                httpParams.put("shopType", 0);
                NetworkReturnUtil.requestHavePageList(searchCategoryView, activity, Api.SEARCHCATEGORY, httpParams, ShopListBean.class, pageIndex);
                break;
            case 4:
                NetworkReturnUtil.requestHavePageList(searchCategoryView, activity, Api.SEARCHCATEGORY, httpParams, DesignerListBean.class, pageIndex);
                break;
            case 5:
                NetworkReturnUtil.requestHavePageList(searchCategoryView, activity, Api.SEARCHCATEGORY, httpParams, MaterialBean.class, pageIndex);
                break;
        }

    }

    @Override
    public void requestDesignerLabel(String requestTag) {


        NetworkReturnUtil.requestNoPageList(requestTag,searchCategoryView, activity, Api.designerLabel, null,DesignerLabelBean.class);

    }

    @Override
    public void requestMaterialLabel(String requestTag) {

        NetworkReturnUtil.requestNoPageList(requestTag,searchCategoryView, activity, Api.materialLabel, null,MaterialLabelBean.class);

    }

}
