package com.surhoo.sh.order.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.File;
import java.util.List;

public class UpLoadEvaluationBean {


    private int maxCount = 6;

    private int requestCode;

    private boolean havaContent;

    public boolean isHavaContent() {
        return havaContent;
    }

    public void setHavaContent(boolean havaContent) {
        this.havaContent = havaContent;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int orderDataId;
    private int orderId;
    private String evaluateName;
    @JSONField(name = "isAnonymous")
    private boolean isAnonymous;
    private int mark;
    private String img;

    private List<File> imgFiles;

    public List<File> getImgFiles() {
        return imgFiles;
    }

    public void setImgFiles(List<File> imgFiles) {
        this.imgFiles = imgFiles;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public int getOrderDataId() {
        return orderDataId;
    }

    public void setOrderDataId(int orderDataId) {
        this.orderDataId = orderDataId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getEvaluateName() {
        return evaluateName;
    }

    public void setEvaluateName(String evaluateName) {
        this.evaluateName = evaluateName;
    }

    public boolean isIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Override
    public String toString() {
        return "UpLoadEvaluationBean{" +
                "maxCount=" + maxCount +
                ", requestCode=" + requestCode +
                ", havaContent=" + havaContent +
                ", position=" + position +
                ", orderDataId=" + orderDataId +
                ", orderId=" + orderId +
                ", evaluateName='" + evaluateName + '\'' +
                ", isAnonymous=" + isAnonymous +
                ", mark=" + mark +
                ", img='" + img + '\'' +
                ", imgFiles=" + imgFiles +
                '}';
    }
}
