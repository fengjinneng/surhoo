package com.surhoo.sh.shop.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ClassifyListBean implements Parcelable {


    /**
     * classifyId : 1098
     * name : 丝巾
     */

    private Integer classifyId;
    private String name;

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.classifyId);
        dest.writeString(this.name);
    }

    public ClassifyListBean() {
    }

    protected ClassifyListBean(Parcel in) {
        this.classifyId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Parcelable.Creator<ClassifyListBean> CREATOR = new Parcelable.Creator<ClassifyListBean>() {
        @Override
        public ClassifyListBean createFromParcel(Parcel source) {
            return new ClassifyListBean(source);
        }

        @Override
        public ClassifyListBean[] newArray(int size) {
            return new ClassifyListBean[size];
        }
    };
}
