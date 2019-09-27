package com.surhoo.sh.common.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.surhoo.sh.R;

public class MineItem extends ConstraintLayout {


    private TextView name;
    private ImageView img;
    private ConstraintLayout constraintLayout;


    private String text;
    private Drawable image;

    public MineItem(Context context) {
        super(context);
        initView(context);
    }

    public MineItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeValue(context, attrs);
        initView(context);

    }

    private void initTypeValue(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MineItem);
        image = a.getDrawable(R.styleable.MineItem_img);
        text = a.getString(R.styleable.MineItem_text);
        a.recycle();


    }

    public MineItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeValue(context, attrs);
        initView(context);

    }


    public void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.template_mine_item, this, true);

        name = findViewById(R.id.template_mine_item_name);
        img = findViewById(R.id.template_mine_item_img);
        constraintLayout = findViewById(R.id.template_mine_item_layout);

        name.setText(text);
        img.setImageDrawable(image);
        constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
    }

}
