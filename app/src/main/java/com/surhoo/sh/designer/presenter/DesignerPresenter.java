package com.surhoo.sh.designer.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.designer.view.DesignerViewList;

public interface DesignerPresenter extends BasePresenter<DesignerViewList> {

    void requestData(boolean isSelf,int designerId);


    void addCollect(String requestTag,int typeId);

    void cancelCollect(String requestTag,int typeId);
}
