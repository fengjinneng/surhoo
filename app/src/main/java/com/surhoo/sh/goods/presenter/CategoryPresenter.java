package com.surhoo.sh.goods.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.goods.view.CategoryListView;

public interface CategoryPresenter extends BasePresenter<CategoryListView> {



     void requestLevelOneCategory(String requestTag);

     //一级分类的id  一级分类的positon
     void requestLevelTwoCategory(String requestTag,int id);



}
