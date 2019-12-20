package com.surhoo.sh.common.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.base.Request;
import com.surhoo.sh.R;

import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class DialogStringCallback extends StringCallback
{

    private Dialog dialog;


    private void initDialog(Activity activity){
        dialog = new Dialog(activity,R.style.loading_dialog);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
//        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        dialog.setContentView(R.layout.load_dialog_view);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


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
