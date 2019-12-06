package com.surhoo.sh.common.bean;

public class PhotoInfo {

    public String url;
    public String w;
    public String h;

    public PhotoInfo() {
    }

    public PhotoInfo(String url) {
        this.url = url;
    }

    public PhotoInfo(String url, String w, String h) {
        this.url = url;
        this.w = w;
        this.h = h;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

}
