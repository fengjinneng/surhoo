package com.surhoo.sh.shop.bean;

public class ArtistProductBean {


    /**
     * id : 1
     * userId : 10024065
     * gmtCreate : 2019-11-05 19:26:09
     * gmtModified : 2019-12-06 18:32:53
     * sort : 2
     * sysId : 155
     * name : 素描风格和很丰富
     * isDelete : 0
     * shopId : 48
     */

    private Integer id;
    private Integer userId;
    private String gmtCreate;
    private String gmtModified;
    private Integer sort;
    private Integer sysId;
    private String name;
    private Integer isDelete;
    private Integer shopId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
