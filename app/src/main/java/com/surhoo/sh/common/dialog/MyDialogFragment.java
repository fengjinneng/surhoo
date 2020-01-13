package com.surhoo.sh.common.dialog;

import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.ClickUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {


    private onConfirmClickListener onConfirmClickListener;

    private onCancelClickListener onCancelClickListener;

    private String content;


    public void setOnConfirmClickListener(MyDialogFragment.onConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    public void setOnCancelClickListener(MyDialogFragment.onCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
    }

    public static MyDialogFragment newInstance(String content) {

        Bundle args = new Bundle();

        MyDialogFragment fragment = new MyDialogFragment();
        args.putString("content",content);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content = getArguments().getString("content");
        }
    }

    @Override
    public void onStart() {

        getDialog().getWindow().getAttributes().width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        super.onStart();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    private View mLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //添加这一行
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = getDialog().getWindow();

        window.setBackgroundDrawable(
                new BitmapDrawable());


        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);

        mLayout = inflater.inflate(R.layout.common_dialog, container);

        ((TextView) mLayout.findViewById(R.id.common_dialog_content)).setText(content);

        mLayout.findViewById(R.id.common_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    if (!ObjectUtils.isEmpty(onCancelClickListener)) {
                        onCancelClickListener.onCancelClick();
                    }
                }
            }
        });

        mLayout.findViewById(R.id.common_dialog_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    if (!ObjectUtils.isEmpty(onConfirmClickListener)) {
                        onConfirmClickListener.onConfirmClick();
                    }
                }
            }
        });

        return mLayout;
    }


    public interface onCancelClickListener{
        void onCancelClick();
    }

    public interface onConfirmClickListener{
        void onConfirmClick();
    }

}
