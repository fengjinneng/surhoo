package com.surhoo.sh.designer.bean;

public class DesignerLabelBean {


    /**
     * id : 8
     * name : 设计师标签
     * type : 1
     * sort : 1
     * isDelete : null
     * gmtGreate : null
     * gmtModified : null
     * selected : null
     * value : null
     */

    private Integer id;
    private String name;
    private Integer type;
    private Integer sort;
    private Object isDelete;
    private Object gmtGreate;
    private Object gmtModified;
    private Object selected;
    private Object value;
    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Object getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Object isDelete) {
        this.isDelete = isDelete;
    }

    public Object getGmtGreate() {
        return gmtGreate;
    }

    public void setGmtGreate(Object gmtGreate) {
        this.gmtGreate = gmtGreate;
    }

    public Object getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Object gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Object getSelected() {
        return selected;
    }

    public void setSelected(Object selected) {
        this.selected = selected;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
