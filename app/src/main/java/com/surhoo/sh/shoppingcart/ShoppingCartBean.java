package com.surhoo.sh.shoppingcart;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class ShoppingCartBean {



    private Integer shopId;
    private String shopName;
    private List<CarGoodsListBean> carGoodsList;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<CarGoodsListBean> getCarGoodsList() {
        return carGoodsList;
    }

    public void setCarGoodsList(List<CarGoodsListBean> carGoodsList) {
        this.carGoodsList = carGoodsList;
    }


    public ShoppingCartBean() {
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }


    public static class CarGoodsListBean implements MultiItemEntity {

        public static final int head = 1;
        public static final int body = 2;
        public static final int foot = 3;
        private int itemType;
        private boolean checked;

        //标记每三个部分同样的flag 便于操作
        private int flag;

        public CarGoodsListBean() {
        }


        public CarGoodsListBean(int itemType, String shopName) {
            this.itemType = itemType;
            this.shopName = shopName;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        private Integer id;
        private Integer userId;
        private Object shopId;
        private String shopName;
        private String goodsName;
        private String goodsImg;
        private Integer skuId;
        private String skuName;
        private Integer goodsNum;
        private Integer goodsId;
        private String goodsPrice;
        private String createdAt;
        private Object updatedAt;
        private Integer status;
        private String goodsMarketPrice;
        private Object idList;


        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        @Override
        public int getItemType() {
            return this.itemType;
        }

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

        public Object getShopId() {
            return shopId;
        }

        public void setShopId(Object shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
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

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
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

        public Object getIdList() {
            return idList;
        }

        public void setIdList(Object idList) {
            this.idList = idList;
        }
    }
}
