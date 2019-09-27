package com.surhoo.sh.goods.bean;

import java.util.List;

public class GoodDetailBean {


    /**
     * id : 1
     * goodsType : 1
     * goodsNo : spsad
     * goodsName : 商品名称
     * goodsClassifyOneId : null
     * goodsClassifyTwoId : null
     * goodsClassifyThreeId : null
     * goodsMarketPrice : 10
     * goodsPrice : 100
     * weight : 1
     * fakeSaleCount : 0
     * saleCount : 50
     * sort : 10
     * freightTemplateId : 11
     * logo : https://wx.qlogo.cn/mmopen/vi_32/uGOeNuw7ln7BEUFDaNP5WuVGXoqxDRSeibGs1zCWFcHLcEH6hFY7Wn02k5ibictfOmbKGGMpeznEVfbcbficd058YQ/132
     * img : https://essilor.oss-cn-shanghai.aliyuncs.com/images/CecjTNxAnisKK5Bim4NHXS3KTsH7eAGP.png
     * banner : https://essilor.oss-cn-shanghai.aliyuncs.com/images/Ndx27QYjk2RjcCpNeaGywJEcD7NTBbnY.png
     * richTextId : 1
     * createdAt : 2019-07-15 08:09:24
     * updatedAt : null
     * status : 1
     * isDelete : false
     * isFreight : false
     * platformStatus : true
     * isPlatform : true
     * isRecommend : true
     * shopId : 0
     * evaluateList : [{"id":1,"orderId":null,"orderDataId":null,"goodsId":null,"materialId":null,"goodsType":null,"goodsName":null,"userId":1001,"mark":5,"userName":"111","skuId":null,"skuName":"黄色_10*10","orderNo":null,"evaluateName":"这是一条评论","img":"https://wx.qlogo.cn/mmopen/vi_32/uGOeNuw7ln7BEUFDaNP5WuVGXoqxDRSeibGs1zCWFcHLcEH6hFY7Wn02k5ibictfOmbKGGMpeznEVfbcbficd058YQ/132","isUsed":null,"isShow":null,"returnDate":null,"returnName":null,"returnDesc":"好的好的","gmtCreate":"2019-07-08 02:28:48","gmtModified":null,"headimgurl":"https://wx.qlogo.cn/mmopen/vi_32/uGOeNuw7ln7BEUFDaNP5WuVGXoqxDRSeibGs1zCWFcHLcEH6hFY7Wn02k5ibictfOmbKGGMpeznEVfbcbficd058YQ/132","mobile":"1313","sysId":null,"shopId":null,"isPlatform":null,"type":1,"isAnonymous":null}]
     * evaluateCount : 1
     * skuList : [{"id":112,"goodsId":1,"goodsSkuName":"黄色_10*10","goodsSkuImg":"http://meile.oss-cn-beijing.aliyuncs.com/img/3NaTMnQAbQGrP8NcxWPXmNBY8tdBsBDW.jpg","goodsSkuNo":"sadas","goodsSkuCostPrice":100,"goodsSkuMarketPrices":200,"goodsSkuRetailPrice":20,"goodsSkuWeight":1,"goodsSkuVolume":1,"goodsSkuStockLock":200,"goodsSkuStock":100,"gmtCreate":"2019-07-15 08:15:14","gmtModified":"2019-04-29 03:28:21","disable":true,"goodsSkuFictitiousSales":40},{"id":113,"goodsId":1,"goodsSkuName":"绿色_10*10","goodsSkuImg":"http://meile.oss-cn-beijing.aliyuncs.com/img/3NaTMnQAbQGrP8NcxWPXmNBY8tdBsBDW.jpg","goodsSkuNo":"sadas2","goodsSkuCostPrice":100,"goodsSkuMarketPrices":200,"goodsSkuRetailPrice":20,"goodsSkuWeight":1,"goodsSkuVolume":1,"goodsSkuStockLock":200,"goodsSkuStock":100,"gmtCreate":"2019-07-15 08:15:14","gmtModified":"2019-04-29 03:28:21","disable":true,"goodsSkuFictitiousSales":40}]
     * skuId : null
     * goodsNum : null
     * isCollect : true
     * specList : [{"id":63,"goodsId":1,"goodsSpecName":"颜色","gmtCreate":1556508501000,"skuNo":null,"goodsSkuSpecVals":[{"id":78,"goodsId":1,"goodsSkuSpecId":63,"goodsSkuSpecValName":"黄色","gmtCreate":1556508501000},{"id":79,"goodsId":1,"goodsSkuSpecId":63,"goodsSkuSpecValName":"绿色","gmtCreate":1556508501000}]},{"id":64,"goodsId":1,"goodsSpecName":"尺寸","gmtCreate":1563178928000,"skuNo":null,"goodsSkuSpecVals":[{"id":80,"goodsId":1,"goodsSkuSpecId":64,"goodsSkuSpecValName":"10*10","gmtCreate":1556508501000}]}]
     * goodsDetail : 啊啊啊啊啊啊
     */

    private int id;
    private int goodsType;
    private String goodsNo;
    private String goodsName;
    private Object goodsClassifyOneId;
    private Object goodsClassifyTwoId;
    private Object goodsClassifyThreeId;
    private String goodsMarketPrice;
    private String goodsPrice;
    private int weight;
    private int fakeSaleCount;
    private int saleCount;
    private int sort;
    private int freightTemplateId;
    private String logo;
    private String img;
    private String banner;
    private int richTextId;
    private String createdAt;
    private Object updatedAt;
    private int status;
    private boolean isDelete;
    private boolean isFreight;
    private boolean platformStatus;
    private boolean isPlatform;
    private boolean isRecommend;
    private int shopId;
    private int evaluateCount;
    private Object skuId;
    private Object goodsNum;
    private boolean isCollect;
    private String goodsDetail;
    private List<EvaluateListBean> evaluateList;
    private List<SkuListBean> skuList;
    private List<SpecListBean> specList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Object getGoodsClassifyOneId() {
        return goodsClassifyOneId;
    }

    public void setGoodsClassifyOneId(Object goodsClassifyOneId) {
        this.goodsClassifyOneId = goodsClassifyOneId;
    }

    public Object getGoodsClassifyTwoId() {
        return goodsClassifyTwoId;
    }

    public void setGoodsClassifyTwoId(Object goodsClassifyTwoId) {
        this.goodsClassifyTwoId = goodsClassifyTwoId;
    }

    public Object getGoodsClassifyThreeId() {
        return goodsClassifyThreeId;
    }

    public void setGoodsClassifyThreeId(Object goodsClassifyThreeId) {
        this.goodsClassifyThreeId = goodsClassifyThreeId;
    }

    public String getGoodsMarketPrice() {
        return goodsMarketPrice;
    }

    public void setGoodsMarketPrice(String goodsMarketPrice) {
        this.goodsMarketPrice = goodsMarketPrice;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFakeSaleCount() {
        return fakeSaleCount;
    }

    public void setFakeSaleCount(int fakeSaleCount) {
        this.fakeSaleCount = fakeSaleCount;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getFreightTemplateId() {
        return freightTemplateId;
    }

    public void setFreightTemplateId(int freightTemplateId) {
        this.freightTemplateId = freightTemplateId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getRichTextId() {
        return richTextId;
    }

    public void setRichTextId(int richTextId) {
        this.richTextId = richTextId;
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

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isIsFreight() {
        return isFreight;
    }

    public void setIsFreight(boolean isFreight) {
        this.isFreight = isFreight;
    }

    public boolean isPlatformStatus() {
        return platformStatus;
    }

    public void setPlatformStatus(boolean platformStatus) {
        this.platformStatus = platformStatus;
    }

    public boolean isIsPlatform() {
        return isPlatform;
    }

    public void setIsPlatform(boolean isPlatform) {
        this.isPlatform = isPlatform;
    }

    public boolean isIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getEvaluateCount() {
        return evaluateCount;
    }

    public void setEvaluateCount(int evaluateCount) {
        this.evaluateCount = evaluateCount;
    }

    public Object getSkuId() {
        return skuId;
    }

    public void setSkuId(Object skuId) {
        this.skuId = skuId;
    }

    public Object getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Object goodsNum) {
        this.goodsNum = goodsNum;
    }

    public boolean isIsCollect() {
        return isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public List<EvaluateListBean> getEvaluateList() {
        return evaluateList;
    }

    public void setEvaluateList(List<EvaluateListBean> evaluateList) {
        this.evaluateList = evaluateList;
    }

    public List<SkuListBean> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuListBean> skuList) {
        this.skuList = skuList;
    }

    public List<SpecListBean> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecListBean> specList) {
        this.specList = specList;
    }

    public static class EvaluateListBean {
        /**
         * id : 1
         * orderId : null
         * orderDataId : null
         * goodsId : null
         * materialId : null
         * goodsType : null
         * goodsName : null
         * userId : 1001
         * mark : 5
         * userName : 111
         * skuId : null
         * skuName : 黄色_10*10
         * orderNo : null
         * evaluateName : 这是一条评论
         * img : https://wx.qlogo.cn/mmopen/vi_32/uGOeNuw7ln7BEUFDaNP5WuVGXoqxDRSeibGs1zCWFcHLcEH6hFY7Wn02k5ibictfOmbKGGMpeznEVfbcbficd058YQ/132
         * isUsed : null
         * isShow : null
         * returnDate : null
         * returnName : null
         * returnDesc : 好的好的
         * gmtCreate : 2019-07-08 02:28:48
         * gmtModified : null
         * headimgurl : https://wx.qlogo.cn/mmopen/vi_32/uGOeNuw7ln7BEUFDaNP5WuVGXoqxDRSeibGs1zCWFcHLcEH6hFY7Wn02k5ibictfOmbKGGMpeznEVfbcbficd058YQ/132
         * mobile : 1313
         * sysId : null
         * shopId : null
         * isPlatform : null
         * type : 1
         * isAnonymous : null
         */

        private int id;
        private Object orderId;
        private Object orderDataId;
        private Object goodsId;
        private Object materialId;
        private Object goodsType;
        private Object goodsName;
        private int userId;
        private int mark;
        private String userName;
        private Object skuId;
        private String skuName;
        private Object orderNo;
        private String evaluateName;
        private String img;
        private Object isUsed;
        private Object isShow;
        private Object returnDate;
        private Object returnName;
        private String returnDesc;
        private String gmtCreate;
        private Object gmtModified;
        private String headimgurl;
        private String mobile;
        private Object sysId;
        private Object shopId;
        private Object isPlatform;
        private int type;
        private Object isAnonymous;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getOrderId() {
            return orderId;
        }

        public void setOrderId(Object orderId) {
            this.orderId = orderId;
        }

        public Object getOrderDataId() {
            return orderDataId;
        }

        public void setOrderDataId(Object orderDataId) {
            this.orderDataId = orderDataId;
        }

        public Object getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Object goodsId) {
            this.goodsId = goodsId;
        }

        public Object getMaterialId() {
            return materialId;
        }

        public void setMaterialId(Object materialId) {
            this.materialId = materialId;
        }

        public Object getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(Object goodsType) {
            this.goodsType = goodsType;
        }

        public Object getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(Object goodsName) {
            this.goodsName = goodsName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getSkuId() {
            return skuId;
        }

        public void setSkuId(Object skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public Object getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Object orderNo) {
            this.orderNo = orderNo;
        }

        public String getEvaluateName() {
            return evaluateName;
        }

        public void setEvaluateName(String evaluateName) {
            this.evaluateName = evaluateName;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public Object getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(Object isUsed) {
            this.isUsed = isUsed;
        }

        public Object getIsShow() {
            return isShow;
        }

        public void setIsShow(Object isShow) {
            this.isShow = isShow;
        }

        public Object getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(Object returnDate) {
            this.returnDate = returnDate;
        }

        public Object getReturnName() {
            return returnName;
        }

        public void setReturnName(Object returnName) {
            this.returnName = returnName;
        }

        public String getReturnDesc() {
            return returnDesc;
        }

        public void setReturnDesc(String returnDesc) {
            this.returnDesc = returnDesc;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Object gmtModified) {
            this.gmtModified = gmtModified;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getSysId() {
            return sysId;
        }

        public void setSysId(Object sysId) {
            this.sysId = sysId;
        }

        public Object getShopId() {
            return shopId;
        }

        public void setShopId(Object shopId) {
            this.shopId = shopId;
        }

        public Object getIsPlatform() {
            return isPlatform;
        }

        public void setIsPlatform(Object isPlatform) {
            this.isPlatform = isPlatform;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getIsAnonymous() {
            return isAnonymous;
        }

        public void setIsAnonymous(Object isAnonymous) {
            this.isAnonymous = isAnonymous;
        }
    }

    public static class SkuListBean {
        /**
         * id : 112
         * goodsId : 1
         * goodsSkuName : 黄色_10*10
         * goodsSkuImg : http://meile.oss-cn-beijing.aliyuncs.com/img/3NaTMnQAbQGrP8NcxWPXmNBY8tdBsBDW.jpg
         * goodsSkuNo : sadas
         * goodsSkuCostPrice : 100
         * goodsSkuMarketPrices : 200
         * goodsSkuRetailPrice : 20
         * goodsSkuWeight : 1
         * goodsSkuVolume : 1
         * goodsSkuStockLock : 200
         * goodsSkuStock : 100
         * gmtCreate : 2019-07-15 08:15:14
         * gmtModified : 2019-04-29 03:28:21
         * disable : true
         * goodsSkuFictitiousSales : 40
         */

        private int id;
        private int goodsId;
        private String goodsSkuName;
        private String goodsSkuImg;
        private String goodsSkuNo;
        private String goodsSkuCostPrice;
        private String goodsSkuMarketPrices;
        private String goodsSkuRetailPrice;
        private int goodsSkuWeight;
        private int goodsSkuVolume;
        private int goodsSkuStockLock;
        private int goodsSkuStock;
        private String gmtCreate;
        private String gmtModified;
        private boolean disable;
        private int goodsSkuFictitiousSales;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsSkuName() {
            return goodsSkuName;
        }

        public void setGoodsSkuName(String goodsSkuName) {
            this.goodsSkuName = goodsSkuName;
        }

        public String getGoodsSkuImg() {
            return goodsSkuImg;
        }

        public void setGoodsSkuImg(String goodsSkuImg) {
            this.goodsSkuImg = goodsSkuImg;
        }

        public String getGoodsSkuNo() {
            return goodsSkuNo;
        }

        public void setGoodsSkuNo(String goodsSkuNo) {
            this.goodsSkuNo = goodsSkuNo;
        }

        public String getGoodsSkuCostPrice() {
            return goodsSkuCostPrice;
        }

        public void setGoodsSkuCostPrice(String goodsSkuCostPrice) {
            this.goodsSkuCostPrice = goodsSkuCostPrice;
        }

        public String getGoodsSkuMarketPrices() {
            return goodsSkuMarketPrices;
        }

        public void setGoodsSkuMarketPrices(String goodsSkuMarketPrices) {
            this.goodsSkuMarketPrices = goodsSkuMarketPrices;
        }

        public String getGoodsSkuRetailPrice() {
            return goodsSkuRetailPrice;
        }

        public void setGoodsSkuRetailPrice(String goodsSkuRetailPrice) {
            this.goodsSkuRetailPrice = goodsSkuRetailPrice;
        }

        public int getGoodsSkuWeight() {
            return goodsSkuWeight;
        }

        public void setGoodsSkuWeight(int goodsSkuWeight) {
            this.goodsSkuWeight = goodsSkuWeight;
        }

        public int getGoodsSkuVolume() {
            return goodsSkuVolume;
        }

        public void setGoodsSkuVolume(int goodsSkuVolume) {
            this.goodsSkuVolume = goodsSkuVolume;
        }

        public int getGoodsSkuStockLock() {
            return goodsSkuStockLock;
        }

        public void setGoodsSkuStockLock(int goodsSkuStockLock) {
            this.goodsSkuStockLock = goodsSkuStockLock;
        }

        public int getGoodsSkuStock() {
            return goodsSkuStock;
        }

        public void setGoodsSkuStock(int goodsSkuStock) {
            this.goodsSkuStock = goodsSkuStock;
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

        public boolean isDisable() {
            return disable;
        }

        public void setDisable(boolean disable) {
            this.disable = disable;
        }

        public int getGoodsSkuFictitiousSales() {
            return goodsSkuFictitiousSales;
        }

        public void setGoodsSkuFictitiousSales(int goodsSkuFictitiousSales) {
            this.goodsSkuFictitiousSales = goodsSkuFictitiousSales;
        }
    }

    public static class SpecListBean {
        /**
         * id : 63
         * goodsId : 1
         * goodsSpecName : 颜色
         * gmtCreate : 1556508501000
         * skuNo : null
         * goodsSkuSpecVals : [{"id":78,"goodsId":1,"goodsSkuSpecId":63,"goodsSkuSpecValName":"黄色","gmtCreate":1556508501000},{"id":79,"goodsId":1,"goodsSkuSpecId":63,"goodsSkuSpecValName":"绿色","gmtCreate":1556508501000}]
         */

        private int id;
        private int goodsId;
        private String goodsSpecName;
        private long gmtCreate;
        private Object skuNo;
        private List<GoodsSkuSpecValsBean> goodsSkuSpecVals;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsSpecName() {
            return goodsSpecName;
        }

        public void setGoodsSpecName(String goodsSpecName) {
            this.goodsSpecName = goodsSpecName;
        }

        public long getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(long gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getSkuNo() {
            return skuNo;
        }

        public void setSkuNo(Object skuNo) {
            this.skuNo = skuNo;
        }

        public List<GoodsSkuSpecValsBean> getGoodsSkuSpecVals() {
            return goodsSkuSpecVals;
        }

        public void setGoodsSkuSpecVals(List<GoodsSkuSpecValsBean> goodsSkuSpecVals) {
            this.goodsSkuSpecVals = goodsSkuSpecVals;
        }

        public static class GoodsSkuSpecValsBean {
            /**
             * id : 78
             * goodsId : 1
             * goodsSkuSpecId : 63
             * goodsSkuSpecValName : 黄色
             * gmtCreate : 1556508501000
             */

            private int id;
            private int goodsId;
            private int goodsSkuSpecId;
            private String goodsSkuSpecValName;
            private long gmtCreate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getGoodsSkuSpecId() {
                return goodsSkuSpecId;
            }

            public void setGoodsSkuSpecId(int goodsSkuSpecId) {
                this.goodsSkuSpecId = goodsSkuSpecId;
            }

            public String getGoodsSkuSpecValName() {
                return goodsSkuSpecValName;
            }

            public void setGoodsSkuSpecValName(String goodsSkuSpecValName) {
                this.goodsSkuSpecValName = goodsSkuSpecValName;
            }

            public long getGmtCreate() {
                return gmtCreate;
            }

            public void setGmtCreate(long gmtCreate) {
                this.gmtCreate = gmtCreate;
            }
        }
    }
}
