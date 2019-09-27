package com.surhoo.sh.shop;

public class ShopListBean {


    /**
     * shopId : 1
     * logo : http://yaochengkun-shanghu.f.wmeimob.com/5TQK5Q7G43LU2M239T28226NJ93KK572.png
     * name : 小店名称
     * labelNames : 虚拟店铺,实体店铺
     * viewNum : 0
     */

    private Integer shopId;
    private String logo;
    private String name;
    private String labelNames;
    private Integer viewNum;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(String labelNames) {
        this.labelNames = labelNames;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }
}
