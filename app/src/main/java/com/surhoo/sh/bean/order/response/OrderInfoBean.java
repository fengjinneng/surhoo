package com.surhoo.sh.bean.order.response;

import java.util.List;

public class OrderInfoBean {



    private AddressBean address;
    private List<GoodsListBeanX> goodsList;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public List<GoodsListBeanX> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBeanX> goodsList) {
        this.goodsList = goodsList;
    }

    public static class AddressBean {
        /**
         * id : 279
         * userId : 10010085
         * userMobile : 13381875761
         * name : 李四
         * phone : 13381875761
         * provinceId : null
         * cityId : null
         * districtId : null
         * address : 详细地址详细地址
         * defaultStatus : 1
         * createdAt : 2019-07-16 06:14:47
         * updatedAt : 2019-07-16 06:14:47
         * status : null
         * provinceName : 北京
         * cityName : 北京
         * districtName : 东城区
         */

        private Integer id;
        private Integer userId;
        private String userMobile;
        private String name;
        private String phone;
        private Object provinceId;
        private Object cityId;
        private Object districtId;
        private String address;
        private Integer defaultStatus;
        private String createdAt;
        private String updatedAt;
        private Object status;
        private String provinceName;
        private String cityName;
        private String districtName;

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

        public String getUserMobile() {
            return userMobile;
        }

        public void setUserMobile(String userMobile) {
            this.userMobile = userMobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(Object provinceId) {
            this.provinceId = provinceId;
        }

        public Object getCityId() {
            return cityId;
        }

        public void setCityId(Object cityId) {
            this.cityId = cityId;
        }

        public Object getDistrictId() {
            return districtId;
        }

        public void setDistrictId(Object districtId) {
            this.districtId = districtId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getDefaultStatus() {
            return defaultStatus;
        }

        public void setDefaultStatus(Integer defaultStatus) {
            this.defaultStatus = defaultStatus;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }
    }

    public static class GoodsListBeanX {
        /**
         * shopName : 平台
         * goodsList : [{"id":2,"skuId":168,"goodsNum":2,"goodsName":"另一个商品","logo":"https://s3.cn-northwest-1.amazonaws.com.cn/lego-s3/79b96b4171a5415dbcfd83f243e94f47.gif","skuName":"yellow_L","goodsMarketPrice":100,"goodsPrice":21,"shopLogo":null}]
         */

        private String shopName;
        private List<GoodsListBean> goodsList;

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
            /**
             * id : 2
             * skuId : 168
             * goodsNum : 2
             * goodsName : 另一个商品
             * logo : https://s3.cn-northwest-1.amazonaws.com.cn/lego-s3/79b96b4171a5415dbcfd83f243e94f47.gif
             * skuName : yellow_L
             * goodsMarketPrice : 100
             * goodsPrice : 21
             * shopLogo : null
             */

            private Integer id;
            private Integer skuId;
            private Integer goodsNum;
            private String goodsName;
            private String logo;
            private String skuName;
            private Double goodsMarketPrice;
            private Double goodsPrice;
            private Object shopLogo;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getSkuId() {
                return skuId;
            }

            public void setSkuId(Integer skuId) {
                this.skuId = skuId;
            }

            public Integer getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(Integer goodsNum) {
                this.goodsNum = goodsNum;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getSkuName() {
                return skuName;
            }

            public void setSkuName(String skuName) {
                this.skuName = skuName;
            }

            public Double getGoodsMarketPrice() {
                return goodsMarketPrice;
            }

            public void setGoodsMarketPrice(Double goodsMarketPrice) {
                this.goodsMarketPrice = goodsMarketPrice;
            }

            public Double getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(Double goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public Object getShopLogo() {
                return shopLogo;
            }

            public void setShopLogo(Object shopLogo) {
                this.shopLogo = shopLogo;
            }
        }
    }
}
