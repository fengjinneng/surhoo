package com.surhoo.sh.base;

import java.util.List;

public interface PagerBaseView extends BaseView {


    void firstInEmpty();

    void loadEnd();

    void refresh(List list);

    void loadData(List list);


}
