package com.surhoo.sh.base;

import java.util.List;

public interface HavePageListBaseView extends BaseView {


    void setHavePageEmptyView();

    void setHavePageErrorView();

    void loadDataEnd();

    void firstLoadData(List list);

    void loadData(List list);

}
