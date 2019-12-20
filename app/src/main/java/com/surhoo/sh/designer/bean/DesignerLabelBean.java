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
    private boolean checked;



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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
