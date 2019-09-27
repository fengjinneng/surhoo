package com.surhoo.sh.shoppingcart;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ShoppingCartBean implements MultiItemEntity {


    public static final int head = 1;
    public static final int body = 2;
    public static final int foot = 3;

    private int shopId;

    private int itemType;


    private String shopName;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private CarGoodsListBean carGoodsList;

    public ShoppingCartBean(int itemType) {
        this.itemType = itemType;
    }

    public ShoppingCartBean(int itemType,int shopId, String shopName, CarGoodsListBean carGoodsList) {
        this.itemType = itemType;
        this.shopName = shopName;
        this.carGoodsList = carGoodsList;
        this.shopId = shopId;
    }

    public ShoppingCartBean() {
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public CarGoodsListBean getCarGoodsList() {
        return carGoodsList;
    }

    public void setCarGoodsList(CarGoodsListBean carGoodsList) {
        this.carGoodsList = carGoodsList;
    }

    @Override
    public int getItemType() {
        return this.itemType;
    }


    public static class CarGoodsListBean {
        /**
         * id : 41
         * userId : 10010085
         * shopId : null
         * shopName : null
         * goodsName : 预售款WASSUP 已售款 城街头系列
         * goodsImg : http://yaochengkun-shanghu.f.wmeimob.com/8986QJTRQSJ323VP75KK85L47K4KKLKK.png
         * skuId : 254
         * skuName : 黄色_S
         * goodsNum : 1
         * goodsId : 17
         * goodsPrice : 102
         * createdAt : 2019-08-16 11:05:03
         * updatedAt : null
         * status : 0
         * goodsMarketPrice : 200
         * idList : null
         */

        private Integer id;
        private Integer userId;
        private String goodsName;
        private String goodsImg;
        private Integer skuId;
        private String skuName;
        private Integer goodsNum;
        private Integer goodsId;
        private String goodsPrice;
        private String createdAt;
        private Integer status;
        private String goodsMarketPrice;

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

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsImg() {
            return goodsImg;
        }

        public void setGoodsImg(String goodsImg) {
            this.goodsImg = goodsImg;
        }

        public Integer getSkuId() {
            return skuId;
        }

        public void setSkuId(Integer skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public Integer getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(Integer goodsNum) {
            this.goodsNum = goodsNum;
        }

        public Integer getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Integer goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getGoodsMarketPrice() {
            return goodsMarketPrice;
        }

        public void setGoodsMarketPrice(String goodsMarketPrice) {
            this.goodsMarketPrice = goodsMarketPrice;
        }
    }
}
