package com.surhoo.sh.collect;

import android.app.Activity;
import android.content.Context;

public class CollectPresenterImpl implements CollectPresenter {


    private Activity activity;
    private CollectView collectView;

    @Override
    public void bindView(Activity activity, CollectView view) {
        this.activity = activity;
        this.collectView = view;
    }

    @Override
    public void unBindView() {
        collectView = null;
    }
}
