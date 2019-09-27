package com.surhoo.sh.collect;

import android.content.Context;

public class CollectPresenterImpl implements CollectPresenter {


    private Context context;
    private CollectView collectView;

    @Override
    public void bindView(Context ctx, CollectView view) {
        this.context = ctx;
        this.collectView = view;
    }

    @Override
    public void unBindView() {
        collectView = null;
    }
}
