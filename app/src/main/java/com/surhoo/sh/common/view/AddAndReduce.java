package com.surhoo.sh.common.view;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.surhoo.sh.R;

public class AddAndReduce extends ConstraintLayout {


    public AddAndReduce(Context context) {
        super(context);
        initView(context);
    }

    public AddAndReduce(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AddAndReduce(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private ConstraintLayout add;
    private ConstraintLayout reduce;


    private void initView(Context context) {
        /**
         * XML文件就是上面提到的那个
         */
        LayoutInflater.from(context).inflate(R.layout.add_and_reduce, this);


        add = findViewById(R.id.pop_goods_spec_reduceNum);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClickListener.onAddClick();
            }
        });

        reduce.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onReduceClickListener.onReduceClick();
            }
        });
    }

    private onAddClickListener onAddClickListener;
    private onReduceClickListener onReduceClickListener;


    public interface onAddClickListener {
        void onAddClick();
    }

    public interface onReduceClickListener {
        void onReduceClick();
    }



}
