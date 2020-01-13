package com.surhoo.sh.material.present;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.Api;
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

        NetworkReturnUtil.requestBeanResultUseGet(materialDetailView,activity,Api.MATERIALDETAIL+"/"+id,httpParams,MaterialBean.class);

    }

    @Override
    public void cancelCollect(String requestTag, int typeId) {
        String s = "{\"type\":2,\"typeId\":"+typeId+"}";
        NetworkReturnUtil.requestStringResultUsePut(requestTag,materialDetailView,activity,Api.collect,s);
    }

    @Override
    public void addCollect(String requestTag, int typeId) {
        String s = "{\"type\":2,\"typeId\":"+typeId+"}";
        NetworkReturnUtil.requestStringResultUsePost(requestTag,materialDetailView,activity,Api.collect,s);
    }
}
