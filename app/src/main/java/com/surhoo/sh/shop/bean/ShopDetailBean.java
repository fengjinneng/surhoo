package com.surhoo.sh.shop.bean;

import java.util.List;

public class ShopDetailBean {


    /**
     * shopId : 48
     * name : 胡伟达艺术时尚工作室
     * shopLogo : https://shanghusm-shanghai-picture.oss-cn-shanghai.aliyuncs.com/img/xcx/material/hEnRGtJcQwpkFR3AfT76A5AfFtDWZZE8.jpg
     * isCollect : false
     * bannerList : ["https://shanghusm-shanghai-picture.oss-cn-shanghai.aliyuncs.com/img/xcx/material/7Xc6RGQkjjNMpSssFTsBHp7D5mm3Grh5.png","https://shanghusm-shanghai-picture.oss-cn-shanghai.aliyuncs.com/img/xcx/material/afybEj55FS4xAammiReTfwjdWnK6z7iK.jpg"]
     * classifyList : [{"classifyId":1084,"name":"T恤"},{"classifyId":1085,"name":"T恤啊"},{"classifyId":1089,"name":"卫衣"},{"classifyId":1090,"name":"丝巾"}]
     * isSynopsis : 1
     * isOriginal : 1
     * isGoods : 1
     * synopsisId : 3092
     * shopType : 1
     */

    private Integer shopId;
    private String name;
    private String shopLogo;
    private Boolean isCollect;
    private Integer isSynopsis;
    private Integer isOriginal;
    private Integer isGoods;
    private Integer synopsisId;
    private Integer shopType;
    private List<String> bannerList;
    private List<ClassifyListBean> classifyList;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public Boolean isIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Boolean isCollect) {
        this.isCollect = isCollect;
    }

    public Integer getIsSynopsis() {
        return isSynopsis;
    }

    public void setIsSynopsis(Integer isSynopsis) {
        this.isSynopsis = isSynopsis;
    }

    public Integer getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(Integer isOriginal) {
        this.isOriginal = isOriginal;
    }

    public Integer getIsGoods() {
        return isGoods;
    }

    public void setIsGoods(Integer isGoods) {
        this.isGoods = isGoods;
    }

    public Integer getSynopsisId() {
        return synopsisId;
    }

    public void setSynopsisId(Integer synopsisId) {
        this.synopsisId = synopsisId;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    public List<String> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<String> bannerList) {
        this.bannerList = bannerList;
    }

    public List<ClassifyListBean> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<ClassifyListBean> classifyList) {
        this.classifyList = classifyList;
    }

    public static class ClassifyListBean {
        /**
         * classifyId : 1084
         * name : T恤
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
    }
}
