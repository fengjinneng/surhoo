package com.surhoo.sh.shop;

import java.util.List;

public class ShopDetailBean {


    /**
     * shopId : 1
     * name : 第一家商家名称
     * shopLogo : http://yaochengkun-shanghu.f.wmeimob.com/M783259XJ926258MT75LXJW88KTTCJ63.png
     * isCollect : false
     * bannerList : ["http://yaochengkun-shanghu.f.wmeimob.com/6VQ6GPNT2PXK2MS2S2P53P6Q68NZ7MJW.png","http://yaochengkun-shanghu.f.wmeimob.com/J53RSLN6AR5P28KP6KMN6K8P6V3N6945.png"]
     * classifyList : [{"classifyId":1052,"name":"商品名称"},{"classifyId":1053,"name":"商家分类"},{"classifyId":1055,"name":"商家分类测试"}]
     */

    private Integer shopId;
    private String name;
    private String shopLogo;
    private boolean isCollect;
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

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
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
         * classifyId : 1052
         * name : 商品名称
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
