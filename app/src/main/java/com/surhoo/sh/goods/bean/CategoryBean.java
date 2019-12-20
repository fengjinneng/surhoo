package com.surhoo.sh.goods.bean;

import java.util.List;

public class CategoryBean {


    private Integer id;
    private String name;
    private String img;
    private Integer type;
    private Integer pid;
    private Integer sort;

    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private List<CategoryBean> categoryBeans;

    public List<CategoryBean> getCategoryBeans() {
        return categoryBeans;
    }

    public void setCategoryBeans(List<CategoryBean> categoryBeans) {
        this.categoryBeans = categoryBeans;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}
