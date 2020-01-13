package com.surhoo.sh.invoice.bean;

public class InvoiceDetailItem {

    private String key;
    private String value;
    private boolean needHead;
    private String title;



    public InvoiceDetailItem(boolean needHead) {
        this.needHead = needHead;
    }

    public boolean isNeedHead() {
        return needHead;
    }

    public void setNeedHead(boolean needHead) {
        this.needHead = needHead;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
