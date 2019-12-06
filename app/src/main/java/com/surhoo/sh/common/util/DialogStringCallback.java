package com.surhoo.sh.common.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.base.Request;

public abstract class DialogStringCallback extends StringCallback
{

    private ProgressDialog dialog;


    private void initDialog(Activity activity){
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                ;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("网络请求中...");

    }

    public DialogStringCallback(Activity activity){

        super();

        if(!activity.isFinishing()){
            initDialog(activity);
        }
    }


    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        if(dialog!=null&&!dialog.isShowing()){
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
