package com.surhoo.sh.base;

import java.util.List;

public interface NoPageListBaseView<T> extends BaseView{

    void showList(List<T> list);

}
