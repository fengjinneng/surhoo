package com.surhoo.sh.home.bean;

import android.graphics.drawable.Drawable;

public class MineItemBean {

    private int type;
    private String name;

    private Drawable drawable;

    public MineItemBean() {
    }

    public MineItemBean(int type, String name, Drawable drawable) {
        this.type = type;
        this.name = name;
        this.drawable = drawable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
