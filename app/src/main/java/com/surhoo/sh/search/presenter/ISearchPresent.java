package com.surhoo.sh.search.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.search.view.SearchView;

public interface ISearchPresent extends BasePresenter<SearchView> {


    void requestData(String searchName);

}
