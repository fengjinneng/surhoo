package com.surhoo.sh.shoppingcart;

import java.util.List;

public class SBBBB {


    /**
     * shopName : 平台
     * carGoodsList : [{"id":41,"userId":10010085,"shopId":null,"shopName":null,"goodsName":"预售款WASSUP 已售款 城街头系列","goodsImg":"http://yaochengkun-shanghu.f.wmeimob.com/8986QJTRQSJ323VP75KK85L47K4KKLKK.png","skuId":254,"skuName":"黄色_S","goodsNum":1,"goodsId":17,"goodsPrice":102,"createdAt":"2019-08-16 11:05:03","updatedAt":null,"status":0,"goodsMarketPrice":200,"idList":null},{"id":40,"userId":10010085,"shopId":null,"shopName":null,"goodsName":"预售款WASSUP 已售款 城街头系列","goodsImg":"http://yaochengkun-shanghu.f.wmeimob.com/V5S465J546N72MXM43Q434VTZ995L2S9.png","skuId":252,"skuName":"黄色_M","goodsNum":1,"goodsId":17,"goodsPrice":100,"createdAt":"2019-08-16 10:40:28","updatedAt":null,"status":0,"goodsMarketPrice":200,"idList":null},{"id":34,"userId":10010085,"shopId":null,"shopName":null,"goodsName":"商品名称","goodsImg":"https://s3.cn-northwest-1.amazonaws.com.cn/lego-s3/286be282d9394423abffa4785b06190a.png","skuId":166,"skuName":"green_L","goodsNum":1,"goodsId":1,"goodsPrice":23,"createdAt":"2019-08-14 22:42:24","updatedAt":null,"status":0,"goodsMarketPrice":100,"idList":null},{"id":33,"userId":10010085,"shopId":null,"shopName":null,"goodsName":"商品名称","goodsImg":"http://yaochengkun-shanghu.f.wmeimob.com/3D42F834Q8KS2QQP7MR6M85RWT7N566W.jpg","skuId":189,"skuName":"黄色_M_两对","goodsNum":1,"goodsId":4,"goodsPrice":10,"createdAt":"2019-08-14 22:41:56","updatedAt":null,"status":0,"goodsMarketPrice":10,"idList":null},{"id":32,"userId":10010085,"shopId":null,"shopName":null,"goodsName":"商品名称","goodsImg":"http://yaochengkun-shanghu.f.wmeimob.com/V52R59NM6N282JR25MGY3TYR6J59N9WL.jpg","skuId":192,"skuName":"绿色_M_一对","goodsNum":1,"goodsId":4,"goodsPrice":10,"createdAt":"2019-08-14 22:41:47","updatedAt":null,"status":0,"goodsMarketPrice":10,"idList":null},{"id":31,"userId":10010085,"shopId":null,"shopName":null,"goodsName":"商品名称","goodsImg":"http://yaochengkun-shanghu.f.wmeimob.com/X7L992Q49KN4294JUN4839Y46M2J2U2R.jpg","skuId":191,"skuName":"绿色_L_两对","goodsNum":1,"goodsId":4,"goodsPrice":10,"createdAt":"2019-08-14 22:41:40","updatedAt":null,"status":0,"goodsMarketPrice":10,"idList":null},{"id":30,"userId":10010085,"shopId":null,"shopName":null,"goodsName":"商品名称","goodsImg":"http://yaochengkun-shanghu.f.wmeimob.com/5Q3PM557L2YR2S7RT98C4N2648ZR7TNP.jpg","skuId":187,"skuName":"黄色_L_两对","goodsNum":2,"goodsId":4,"goodsPrice":1101,"createdAt":"2019-08-14 22:41:36","updatedAt":null,"status":0,"goodsMarketPrice":10,"idList":null},{"id":29,"userId":10010085,"shopId":null,"shopName":null,"goodsName":"商品名称","goodsImg":"http://yaochengkun-shanghu.f.wmeimob.com/ZMY98NN8WP572S49T3PJ43KJQL6PKLR4.jpg","skuId":188,"skuName":"黄色_M_一对","goodsNum":2,"goodsId":4,"goodsPrice":10,"createdAt":"2019-08-14 22:41:28","updatedAt":null,"status":0,"goodsMarketPrice":10,"idList":null},{"id":21,"userId":10010085,"shopId":null,"shopName":null,"goodsName":"商品名称","goodsImg":"http://yaochengkun-shanghu.f.wmeimob.com/8TT4R887N2Q52L7953PK7P64867S25P2.jpg","skuId":190,"skuName":"绿色_L_一对","goodsNum":3,"goodsId":4,"goodsPrice":10,"createdAt":"2019-08-12 16:53:27","updatedAt":null,"status":0,"goodsMarketPrice":10,"idList":null},{"id":14,"userId":10010085,"shopId":null,"shopName":null,"goodsName":"商品名称","goodsImg":"https://s3.cn-northwest-1.amazonaws.com.cn/lego-s3/79b96b4171a5415dbcfd83f243e94f47.gif","skuId":164,"skuName":"yellow_L","goodsNum":9,"goodsId":1,"goodsPrice":21,"createdAt":"2019-07-25 13:59:39","updatedAt":null,"status":0,"goodsMarketPrice":100,"idList":null}]
     */

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
         * goodsPrice : 102.0
         * createdAt : 2019-08-16 11:05:03
         * updatedAt : null
         * status : 0
         * goodsMarketPrice : 200.0
         * idList : null
         */

        private int id;
        private int userId;
        private Object shopId;
        private Object shopName;
        private String goodsName;
        private String goodsImg;
        private int skuId;
        private String skuName;
        private int goodsNum;
        private int goodsId;
        private String goodsPrice;
        private String createdAt;
        private Object updatedAt;
        private int status;
        private String goodsMarketPrice;
        private Object idList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getShopId() {
            return shopId;
        }

        public void setShopId(Object shopId) {
            this.shopId = shopId;
        }

        public Object getShopName() {
            return shopName;
        }

        public void setShopName(Object shopName) {
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

        public int getSkuId() {
            return skuId;
        }

        public void setSkuId(int skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
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
