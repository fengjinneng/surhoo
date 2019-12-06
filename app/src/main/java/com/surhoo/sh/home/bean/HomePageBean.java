package com.surhoo.sh.home.bean;

import java.util.List;

public class HomePageBean {


    private List<DESIGNERBean> DESIGNER;
    private List<BANNERBean> BANNER;
    private List<FIRSTSCENEBean> FIRSTSCENE;
    private List<GOODSBean> GOODS;
    private List<SCENEBean> SCENE;
    private List<BARGAINGOODSBean> BARGAINGOODS;

    public List<DESIGNERBean> getDESIGNER() {
        return DESIGNER;
    }

    public void setDESIGNER(List<DESIGNERBean> DESIGNER) {
        this.DESIGNER = DESIGNER;
    }

    public List<BANNERBean> getBANNER() {
        return BANNER;
    }

    public void setBANNER(List<BANNERBean> BANNER) {
        this.BANNER = BANNER;
    }

    public List<FIRSTSCENEBean> getFIRSTSCENE() {
        return FIRSTSCENE;
    }

    public void setFIRSTSCENE(List<FIRSTSCENEBean> FIRSTSCENE) {
        this.FIRSTSCENE = FIRSTSCENE;
    }

    public List<GOODSBean> getGOODS() {
        return GOODS;
    }

    public void setGOODS(List<GOODSBean> GOODS) {
        this.GOODS = GOODS;
    }

    public List<SCENEBean> getSCENE() {
        return SCENE;
    }

    public void setSCENE(List<SCENEBean> SCENE) {
        this.SCENE = SCENE;
    }

    public List<BARGAINGOODSBean> getBARGAINGOODS() {
        return BARGAINGOODS;
    }

    public void setBARGAINGOODS(List<BARGAINGOODSBean> BARGAINGOODS) {
        this.BARGAINGOODS = BARGAINGOODS;
    }

    public static class DESIGNERBean  {
        /**
         * designerId : 10010120
         * designerName : 阿森纳
         * headimgurl : https://wx.qlogo.cn/mmopen/vi_32/vxAofGNMybZ72X5ywt2sIOzVQobhX46xMTgY0lmUCIibEGsaSGmYWlmC3413Vv1tRPRiavvIgvKibKtQMIweNosRw/132
         * labelNames : null
         * level : 1
         * detail : null
         * materialList : null
         */

        private Integer designerId;
        private String designerName;
        private String headimgurl;
        private String labelNames;
        private Integer level;
        private String detail;



        public Integer getDesignerId() {
            return designerId;
        }

        public void setDesignerId(Integer designerId) {
            this.designerId = designerId;
        }

        public String getDesignerName() {
            return designerName;
        }

        public void setDesignerName(String designerName) {
            this.designerName = designerName;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getLabelNames() {
            return labelNames;
        }

        public void setLabelNames(String labelNames) {
            this.labelNames = labelNames;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }



    }

    public static class BANNERBean {
        /**
         * id : 83
         * banner : https://essilor.oss-cn-shanghai.aliyuncs.com/images/mzDbKTSehFhsZ4xfWMYx5dAWfkxGa2ZR.jpg
         * type : 2
         * sort : 10
         * status : 1
         * createdAt : null
         * updatedAt : null
         * typeId : 10
         * sysId : null
         */

        private Integer id;
        private String banner;
        private Integer type;
        private Integer sort;
        private Integer status;
        private Integer typeId;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getTypeId() {
            return typeId;
        }

        public void setTypeId(Integer typeId) {
            this.typeId = typeId;
        }


    }

    public static class FIRSTSCENEBean  {
        /**
         * sceneId : 2
         * icon : https://essilor.oss-cn-shanghai.aliyuncs.com/images/KtzREs4neykh86NYrhXTjJRm28Cakm4G.jpg
         * name : 场景名称2
         * logo : null
         * detail : null
         */

        private Integer sceneId;
        private String icon;
        private String name;
        private String logo;
        private String detail;



        public Integer getSceneId() {
            return sceneId;
        }

        public void setSceneId(Integer sceneId) {
            this.sceneId = sceneId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }


    }

    public static class GOODSBean {
        /**
         * goodsId : 1
         * logo : https://wx.qlogo.cn/mmopen/vi_32/uGOeNuw7ln7BEUFDaNP5WuVGXoqxDRSeibGs1zCWFcHLcEH6hFY7Wn02k5ibictfOmbKGGMpeznEVfbcbficd058YQ/132
         * saleCount : 50
         * goodsName : 商品名称
         * goodsPrice : 100
         * shopLogo : null
         */

        private Integer goodsId;
        private String logo;
        private Integer saleCount;
        private String goodsName;
        private String goodsPrice;
        private String shopLogo;



        public Integer getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Integer goodsId) {
            this.goodsId = goodsId;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public Integer getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(Integer saleCount) {
            this.saleCount = saleCount;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }


    }

    public static class SCENEBean   {
        /**
         * sceneId : 2
         * icon : https://essilor.oss-cn-shanghai.aliyuncs.com/images/KtzREs4neykh86NYrhXTjJRm28Cakm4G.jpg
         * name : 场景名称2
         * logo : https://essilor.oss-cn-shanghai.aliyuncs.com/images/KtzREs4neykh86NYrhXTjJRm28Cakm4G.jpg
         * detail : 场景描叙
         */

        private Integer sceneId;
        private String icon;
        private String name;
        private String logo;
        private String detail;



        public Integer getSceneId() {
            return sceneId;
        }

        public void setSceneId(Integer sceneId) {
            this.sceneId = sceneId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }


    }

    public static class BARGAINGOODSBean   {
        /**
         * sceneId : 2
         * icon : https://essilor.oss-cn-shanghai.aliyuncs.com/images/KtzREs4neykh86NYrhXTjJRm28Cakm4G.jpg
         * name : 场景名称2
         * logo : https://essilor.oss-cn-shanghai.aliyuncs.com/images/KtzREs4neykh86NYrhXTjJRm28Cakm4G.jpg
         * detail : 场景描叙
         */

        private Integer goodsId;
        private String logo;
        private Integer saleCount;
        private String goodsName;
        private String marketPrice;
        private String bottomPrice;


        public Integer getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Integer goodsId) {
            this.goodsId = goodsId;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public Integer getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(Integer saleCount) {
            this.saleCount = saleCount;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getBottomPrice() {
            return bottomPrice;
        }

        public void setBottomPrice(String bottomPrice) {
            this.bottomPrice = bottomPrice;
        }
    }

}
