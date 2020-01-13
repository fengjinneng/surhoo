package com.surhoo.sh.base;

import java.util.List;

public interface NoPageListBaseView<T> extends BaseView{


    void setNoPageEmptyView();

    void setNoPageErrorView();


    /**
     *
     * @param requestTag 请求的标记，多个请求会回调同一个方法时进行判断
     * @param list
     */

    void showNoPageList(String requestTag,List<T> list);

}
