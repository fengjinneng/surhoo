package com.surhoo.sh.goods.bean;

import java.util.List;

public class CommentBean {


    /**
     * total : 1
     * list : [{"id":1,"orderId":132,"orderDataId":132,"goodsId":1,"materialId":null,"goodsType":1,"goodsName":"撒大声地","userId":1001,"mark":5,"userName":"111","skuId":1,"skuName":"黄色_10*10","orderNo":"13213213","evaluateName":"这是一条评论","img":"https://wx.qlogo.cn/mmopen/vi_32/uGOeNuw7ln7BEUFDaNP5WuVGXoqxDRSeibGs1zCWFcHLcEH6hFY7Wn02k5ibictfOmbKGGMpeznEVfbcbficd058YQ/132","isUsed":0,"isShow":true,"returnDate":"2019-07-15 08:29:08","returnName":null,"returnDesc":"好的好的","gmtCreate":"2019-07-08 02:28:48","gmtModified":null,"headimgurl":"https://wx.qlogo.cn/mmopen/vi_32/uGOeNuw7ln7BEUFDaNP5WuVGXoqxDRSeibGs1zCWFcHLcEH6hFY7Wn02k5ibictfOmbKGGMpeznEVfbcbficd058YQ/132","mobile":"1313","sysId":null,"shopId":null,"isPlatform":true,"type":1,"isAnonymous":null}]
     * pageNum : 1
     * pageSize : 20
     * size : 1
     * startRow : 1
     * endRow : 1
     * pages : 1
     * prePage : 0
     * nextPage : 0
     * isFirstPage : true
     * isLastPage : true
     * hasPreviousPage : false
     * hasNextPage : false
     * navigatePages : 8
     * navigatepageNums : [1]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     */

    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
    private Integer size;
    private Integer startRow;
    private Integer endRow;
    private Integer pages;
    private Integer prePage;
    private Integer nextPage;
    private Boolean isFirstPage;
    private Boolean isLastPage;
    private Boolean hasPreviousPage;
    private Boolean hasNextPage;
    private Integer navigatePages;
    private Integer navigateFirstPage;
    private Integer navigateLastPage;
    private List<ListBean> list;
    private List<Integer> navigatepageNums;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Boolean getFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(Boolean firstPage) {
        isFirstPage = firstPage;
    }

    public Boolean getLastPage() {
        return isLastPage;
    }

    public void setLastPage(Boolean lastPage) {
        isLastPage = lastPage;
    }

    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(Boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public Integer getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(Integer navigatePages) {
        this.navigatePages = navigatePages;
    }

    public Integer getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(Integer navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public Integer getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(Integer navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {

        //是否已经添加过了图片信息
        private boolean isLoad;
        private Integer id;
        private Integer orderId;
        private Integer orderDataId;
        private Integer goodsId;
        private Object materialId;
        private Integer goodsType;
        private String goodsName;
        private Integer userId;
        private Integer mark;
        private String userName;
        private Integer skuId;
        private String skuName;
        private String orderNo;
        private String evaluateName;
        private String img;
        private Integer isUsed;
        private Boolean isShow;
        private String returnDate;
        private Object returnName;
        private String returnDesc;
        private String gmtCreate;
        private Object gmtModified;
        private String headimgurl;
        private String mobile;
        private Object sysId;
        private Object shopId;
        private Boolean isPlatform;
        private Integer type;
        private Object isAnonymous;

        public boolean isLoad() {
            return isLoad;
        }

        public void setLoad(boolean load) {
            isLoad = load;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public Integer getOrderDataId() {
            return orderDataId;
        }

        public void setOrderDataId(Integer orderDataId) {
            this.orderDataId = orderDataId;
        }

        public Integer getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Integer goodsId) {
            this.goodsId = goodsId;
        }

        public Object getMaterialId() {
            return materialId;
        }

        public void setMaterialId(Object materialId) {
            this.materialId = materialId;
        }

        public Integer getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(Integer goodsType) {
            this.goodsType = goodsType;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
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

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
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

        public Integer getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(Integer isUsed) {
            this.isUsed = isUsed;
        }

        public Boolean getShow() {
            return isShow;
        }

        public void setShow(Boolean show) {
            isShow = show;
        }

        public String getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(String returnDate) {
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

        public Boolean getPlatform() {
            return isPlatform;
        }

        public void setPlatform(Boolean platform) {
            isPlatform = platform;
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
}
