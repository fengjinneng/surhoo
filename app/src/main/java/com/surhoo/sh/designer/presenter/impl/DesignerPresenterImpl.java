package com.surhoo.sh.designer.presenter.impl;

import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.designer.bean.DesignerDetailBean;
import com.surhoo.sh.designer.presenter.DesignerPresenter;
import com.surhoo.sh.designer.view.DesignerViewList;

public class DesignerPresenterImpl implements DesignerPresenter {


    private DesignerViewList designerView;
    private Context context;


    @Override
    public void bindView(Context ctx, DesignerViewList view) {
        context =ctx;
        designerView = view;

    }

    @Override
    public void unBindView() {
        designerView = null;
    }


    @Override
    public void requestData(boolean isSelf, int designerId) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("isSelf",isSelf);
        httpParams.put("designerId",designerId);
        NetworkReturnUtil.requestOne(designerView,context,Api.DESIGNERINFO,httpParams,DesignerDetailBean.class);

    }
}
