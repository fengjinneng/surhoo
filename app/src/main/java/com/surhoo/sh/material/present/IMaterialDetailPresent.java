package com.surhoo.sh.material.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.material.view.MaterialDetailView;

public interface IMaterialDetailPresent extends BasePresenter<MaterialDetailView> {



    void requestData(int id);

    void cancelCollect(String requestTag,int typeId);
    void addCollect(String requestTag,int typeId);

}
