package com.surhoo.sh.material.present;

import android.app.Activity;
import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.material.view.MaterialDetailView;

public class MaterialDetailPresentImpl implements IMaterialDetailPresent {




    MaterialDetailView materialDetailView;

    Activity activity;

    @Override
    public void bindView(Activity activity, MaterialDetailView view) {
        this.activity = activity;
        this.materialDetailView = view;
    }

    @Override
    public void unBindView() {
        materialDetailView = null;
    }

    @Override
    public void requestData(int id) {

        HttpParams httpParams = new HttpParams();

        NetworkReturnUtil.requestOne(materialDetailView,activity,Api.MATERIALDETAIL+"/"+id,httpParams,MaterialBean.class);

    }
}
