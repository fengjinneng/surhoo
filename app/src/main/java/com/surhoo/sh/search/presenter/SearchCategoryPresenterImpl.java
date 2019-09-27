package com.surhoo.sh.search.presenter;

import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.designer.bean.DesignerLabelBean;
import com.surhoo.sh.designer.bean.DesignerListBean;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.material.MaterialBean;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.search.view.SearchCategoryView;
import com.surhoo.sh.shop.ShopListBean;

public class SearchCategoryPresenterImpl implements SearchCategoryPresenter {

    public static final String TAG = "SearchCategoryPresenterImpl";

    private SearchCategoryView searchCategoryView;
    private Context context;

    @Override
    public void bindView(Context ctx, SearchCategoryView view) {
        searchCategoryView = view;
        context = ctx;
    }

    @Override
    public void unBindView() {
        searchCategoryView = null;
    }

    @Override
    public void requestData(int type, String searchName, int pageSize, int pageIndex, int sortType, String idList) {


        HttpParams httpParams = new HttpParams();

        httpParams.put("type", type);
        httpParams.put("searchName", searchName);
        httpParams.put("pageSize", pageSize);
        httpParams.put("pageIndex", pageIndex);
        httpParams.put("sortType", sortType);
        httpParams.put("idList", idList);

        //1 商品 2 场景 3 店铺 4 设计师 5素材
        switch (type) {
            case 1:
                NetworkReturnUtil.requestPage(searchCategoryView, context, Api.SEARCHCATEGORY, httpParams, GoodsBean.class, pageIndex);
                break;
            case 2:
                NetworkReturnUtil.requestPage(searchCategoryView, context, Api.SEARCHCATEGORY, httpParams, ScenarioBean.class, pageIndex);
                break;

            case 3:
                NetworkReturnUtil.requestPage(searchCategoryView, context, Api.SEARCHCATEGORY, httpParams, ShopListBean.class, pageIndex);
                break;
            case 4:
                NetworkReturnUtil.requestPage(searchCategoryView, context, Api.SEARCHCATEGORY, httpParams, DesignerListBean.class, pageIndex);
                break;
            case 5:
                NetworkReturnUtil.requestPage(searchCategoryView, context, Api.SEARCHCATEGORY, httpParams, MaterialBean.class, pageIndex);
                break;
        }

    }

    @Override
    public void requestDesignerLable(int type) {

        //1 商品 2 场景 3 店铺 4 设计师 5素材
        switch (type) {
            case 1:
                break;
            case 2:
                break;

            case 3:
                break;
            case 4:
                NetworkReturnUtil.requestList(searchCategoryView, context, Api.DESIGNERLABEL, null, DesignerLabelBean.class);
                break;
            case 5:
                break;
        }
    }
}