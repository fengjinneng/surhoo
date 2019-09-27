package com.surhoo.sh.search.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.search.view.SearchCategoryView;

public interface SearchCategoryPresenter extends BasePresenter<SearchCategoryView> {

    void requestData(int type, String searchName,
                     int pageSize, int pageIndex, int sortType, String idList);


    void requestDesignerLable(int type);

}
