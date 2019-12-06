package com.surhoo.sh.material.present;

import android.content.Context;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.material.view.MaterialDetailView;

public interface IMaterialDetailPresent extends BasePresenter<MaterialDetailView> {



    void requestData(int id);

}
