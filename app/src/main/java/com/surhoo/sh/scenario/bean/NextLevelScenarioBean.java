package com.surhoo.sh.scenario.bean;


public class NextLevelScenarioBean  {


    /**
     * goodsId : 18
     * logo : http://yaochengkun-shanghu.f.wmeimob.com/9N95999P68S42253UZ7V358FKY37RMTN.png
     * saleCount : 0
     * goodsName : 测试商品测试商品测试商品
     * goodsPrice : 10.0
     * shopLogo : null
     */

    private int goodsId;
    private String logo;
    private int saleCount;
    private String goodsName;
    private double goodsPrice;
    private Object shopLogo;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Object getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(Object shopLogo) {
        this.shopLogo = shopLogo;
    }

    @Override
    public String toString() {
        return "ScenarioBean{" +
                "goodsId=" + goodsId +
                ", logo='" + logo + '\'' +
                ", saleCount=" + saleCount +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", shopLogo=" + shopLogo +
                '}';
    }
}
