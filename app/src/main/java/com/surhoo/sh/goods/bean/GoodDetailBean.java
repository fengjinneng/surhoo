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

    private Integer id;
    private Integer goodsType;
    private String goodsNo;
    private String goodsName;
    private Object goodsClassifyOneId;
    private Object goodsClassifyTwoId;
    private Object goodsClassifyThreeId;
    private String goodsMarketPrice;
    private String goodsPrice;
    private Integer weight;
    private Integer fakeSaleCount;
    private Integer saleCount;
    private Integer sort;
    private Integer freightTemplateId;
    private String logo;
    private String img;
    private String banner;
    private Integer richTextId;
    private String createdAt;
    private Object updatedAt;
    private Integer status;
    private boolean isDelete;
    private boolean isFreight;
    private boolean platformStatus;
    private boolean isPlatform;
    private boolean isRecommend;
    private Integer shopId;
    private Integer evaluateCount;
    private Object skuId;
    private Object goodsNum;
    private boolean isCollect;
    private String goodsDetail;
    private List<EvaluateListBean> evaluateList;
    private List<SkuListBean> skuList;
    private List<SpecListBean> specList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getFakeSaleCount() {
        return fakeSaleCount;
    }

    public void setFakeSaleCount(Integer fakeSaleCount) {
        this.fakeSaleCount = fakeSaleCount;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFreightTemplateId() {
        return freightTemplateId;
    }

    public void setFreightTemplateId(Integer freightTemplateId) {
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

    public Integer getRichTextId() {
        return richTextId;
    }

    public void setRichTextId(Integer richTextId) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getEvaluateCount() {
        return evaluateCount;
    }

    public void setEvaluateCount(Integer evaluateCount) {
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

        private Integer id;
        private Object orderId;
        private Object orderDataId;
        private Object goodsId;
        private Object materialId;
        private Object goodsType;
        private Object goodsName;
        private Integer userId;
        private Integer mark;
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
        private Integer type;
        private Object isAnonymous;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getMark() {
            return mark;
        }

        public void setMark(Integer mark) {
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

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
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

        private Integer id;
        private Integer goodsId;
        private String goodsSkuName;
        private String goodsSkuImg;
        private String goodsSkuNo;
        private String goodsSkuCostPrice;
        private String goodsSkuMarketPrices;
        private String goodsSkuRetailPrice;
        private Integer goodsSkuWeight;
        private Integer goodsSkuVolume;
        private Integer goodsSkuStockLock;
        private Integer goodsSkuStock;
        private String gmtCreate;
        private String gmtModified;
        private boolean disable;
        private Integer goodsSkuFictitiousSales;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Integer goodsId) {
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

        public Integer getGoodsSkuWeight() {
            return goodsSkuWeight;
        }

        public void setGoodsSkuWeight(Integer goodsSkuWeight) {
            this.goodsSkuWeight = goodsSkuWeight;
        }

        public Integer getGoodsSkuVolume() {
            return goodsSkuVolume;
        }

        public void setGoodsSkuVolume(Integer goodsSkuVolume) {
            this.goodsSkuVolume = goodsSkuVolume;
        }

        public Integer getGoodsSkuStockLock() {
            return goodsSkuStockLock;
        }

        public void setGoodsSkuStockLock(Integer goodsSkuStockLock) {
            this.goodsSkuStockLock = goodsSkuStockLock;
        }

        public Integer getGoodsSkuStock() {
            return goodsSkuStock;
        }

        public void setGoodsSkuStock(Integer goodsSkuStock) {
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

        public Integer getGoodsSkuFictitiousSales() {
            return goodsSkuFictitiousSales;
        }

        public void setGoodsSkuFictitiousSales(Integer goodsSkuFictitiousSales) {
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

        private Integer id;
        private Integer goodsId;
        private String goodsSpecName;
        private long gmtCreate;
        private Object skuNo;
        private List<GoodsSkuSpecValsBean> goodsSkuSpecVals;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Integer goodsId) {
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

            private Integer id;
            private Integer goodsId;
            private Integer goodsSkuSpecId;
            private String goodsSkuSpecValName;
            private long gmtCreate;
            private Boolean isChecked;

            public Boolean getChecked() {
                return isChecked;
            }

            public void setChecked(Boolean checked) {
                isChecked = checked;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(Integer goodsId) {
                this.goodsId = goodsId;
            }

            public Integer getGoodsSkuSpecId() {
                return goodsSkuSpecId;
            }

            public void setGoodsSkuSpecId(Integer goodsSkuSpecId) {
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
