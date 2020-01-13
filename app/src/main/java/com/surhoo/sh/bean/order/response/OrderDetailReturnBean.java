package com.surhoo.sh.bean.order.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

public class OrderDetailReturnBean implements Parcelable {


    private BaseQuickAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private View.OnClickListener onClickListener1;
    private View.OnClickListener onClickListener2;




    public View.OnClickListener getOnClickListener1() {
        return onClickListener1;
    }

    public void setOnClickListener1(View.OnClickListener onClickListener1) {
        this.onClickListener1 = onClickListener1;
    }

    public View.OnClickListener getOnClickListener2() {
        return onClickListener2;
    }

    public void setOnClickListener2(View.OnClickListener onClickListener2) {
        this.onClickListener2 = onClickListener2;
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    public BaseQuickAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseQuickAdapter adapter) {
        this.adapter = adapter;
    }



    private Integer id;
    private Integer userId;
    private String userMobile;
    private Integer goodsId;
    private String formId;
    private Integer goodsType;
    private String orderNo;
    private String orderTime;
    private String payTime;
    private Integer payType;
    private String orderAmount;
    private String payAmount;
    private String goodsAmount;
    private String orderFreight;
    private String transactionId;
    private Boolean isExpressAmount;
    private String receiptPerson;
    private String receiptPhone;
    private String receiptProvince;
    private String receiptCity;
    private String receiptArea;
    private String receiptAddr;
    private Integer orderStatus;
    private Integer isRefund;
    private Boolean refundStatus;
    private String orderRemarks;
    private String platformRemarks;
    private String operator;
    private String expressName;
    private String expressNumber;
    private String expressCode;
    private String deliverAt;
    private String confirmAt;
    private String cancelAt;
    private String cancelDesc;
    private String gmtCreate;
    private String gmtModified;
    private Boolean status;
    private String orderPerson;
    private Integer couponId;
    private Integer Integeregral;
    private Integer sysId;
    private String actualAmount;
    private Integer shopId;
    private String shopName;
    private String searchName;
    private Integer normalType;
    private Integer isInvoice;
    private Integer invoiceType;
    private String invoiceTitle;
    private String invoiceContent;
    private String invoiceNickname;
    private String invoiceEnterpriseName;
    private String invoiceTaxCode;
    private String invoiceAddress;
    private String invoiceMobile;
    private String invoiceBankName;
    private String invoiceAccount;
    private Long orderStopTime;
    private String totalGoodsNum;
    private List<OrderDataListBean> orderDataList;

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getOrderFreight() {
        return orderFreight;
    }

    public void setOrderFreight(String orderFreight) {
        this.orderFreight = orderFreight;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Boolean getExpressAmount() {
        return isExpressAmount;
    }

    public void setExpressAmount(Boolean expressAmount) {
        isExpressAmount = expressAmount;
    }

    public String getReceiptPerson() {
        return receiptPerson;
    }

    public void setReceiptPerson(String receiptPerson) {
        this.receiptPerson = receiptPerson;
    }

    public String getReceiptPhone() {
        return receiptPhone;
    }

    public void setReceiptPhone(String receiptPhone) {
        this.receiptPhone = receiptPhone;
    }

    public String getReceiptProvince() {
        return receiptProvince;
    }

    public void setReceiptProvince(String receiptProvince) {
        this.receiptProvince = receiptProvince;
    }

    public String getReceiptCity() {
        return receiptCity;
    }

    public void setReceiptCity(String receiptCity) {
        this.receiptCity = receiptCity;
    }

    public String getReceiptArea() {
        return receiptArea;
    }

    public void setReceiptArea(String receiptArea) {
        this.receiptArea = receiptArea;
    }

    public String getReceiptAddr() {
        return receiptAddr;
    }

    public void setReceiptAddr(String receiptAddr) {
        this.receiptAddr = receiptAddr;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }

    public Boolean getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Boolean refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getOrderRemarks() {
        return orderRemarks;
    }

    public void setOrderRemarks(String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }

    public String getPlatformRemarks() {
        return platformRemarks;
    }

    public void setPlatformRemarks(String platformRemarks) {
        this.platformRemarks = platformRemarks;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(String deliverAt) {
        this.deliverAt = deliverAt;
    }

    public String getConfirmAt() {
        return confirmAt;
    }

    public void setConfirmAt(String confirmAt) {
        this.confirmAt = confirmAt;
    }

    public String getCancelAt() {
        return cancelAt;
    }

    public void setCancelAt(String cancelAt) {
        this.cancelAt = cancelAt;
    }

    public String getCancelDesc() {
        return cancelDesc;
    }

    public void setCancelDesc(String cancelDesc) {
        this.cancelDesc = cancelDesc;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getOrderPerson() {
        return orderPerson;
    }

    public void setOrderPerson(String orderPerson) {
        this.orderPerson = orderPerson;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getIntegeregral() {
        return Integeregral;
    }

    public void setIntegeregral(Integer integeregral) {
        Integeregral = integeregral;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Integer getNormalType() {
        return normalType;
    }

    public void setNormalType(Integer normalType) {
        this.normalType = normalType;
    }

    public Integer getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Integer isInvoice) {
        this.isInvoice = isInvoice;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getInvoiceNickname() {
        return invoiceNickname;
    }

    public void setInvoiceNickname(String invoiceNickname) {
        this.invoiceNickname = invoiceNickname;
    }

    public String getInvoiceEnterpriseName() {
        return invoiceEnterpriseName;
    }

    public void setInvoiceEnterpriseName(String invoiceEnterpriseName) {
        this.invoiceEnterpriseName = invoiceEnterpriseName;
    }

    public String getInvoiceTaxCode() {
        return invoiceTaxCode;
    }

    public void setInvoiceTaxCode(String invoiceTaxCode) {
        this.invoiceTaxCode = invoiceTaxCode;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getInvoiceMobile() {
        return invoiceMobile;
    }

    public void setInvoiceMobile(String invoiceMobile) {
        this.invoiceMobile = invoiceMobile;
    }

    public String getInvoiceBankName() {
        return invoiceBankName;
    }

    public void setInvoiceBankName(String invoiceBankName) {
        this.invoiceBankName = invoiceBankName;
    }

    public String getInvoiceAccount() {
        return invoiceAccount;
    }

    public void setInvoiceAccount(String invoiceAccount) {
        this.invoiceAccount = invoiceAccount;
    }

    public Long getOrderStopTime() {
        return orderStopTime;
    }

    public void setOrderStopTime(Long orderStopTime) {
        this.orderStopTime = orderStopTime;
    }

    public String getTotalGoodsNum() {
        return totalGoodsNum;
    }

    public void setTotalGoodsNum(String totalGoodsNum) {
        this.totalGoodsNum = totalGoodsNum;
    }

    public List<OrderDataListBean> getOrderDataList() {
        return orderDataList;
    }

    public void setOrderDataList(List<OrderDataListBean> orderDataList) {
        this.orderDataList = orderDataList;
    }

    public static class OrderDataListBean implements Parcelable {
        /**
         * id : 1
         * orderId : 1
         * sysId : 1
         * orderNo : 1321321
         * goodsId : 12
         * goodsNo : 13
         * goodsName : 商品名称
         * goodsImg : https://essilor.oss-cn-shanghai.aliyuncs.com/images/JEnm2GPaKQDCQHp5Wc6imk4ZM4jCctpz.gif
         * goodsNum : 1
         * goodsPrice : 200
         * goodsWeight : 2
         * goodsCostPrice : 20
         * goodsMarketPrice : 20
         * userId : 10010085
         * skuId : 1
         * skuName : 规格名称
         * refundNumber : 12132
         * refundStatus : true
         * refundType : 1
         * refundAmount : 100
         * refundTime : 2019-07-05 10:22:32
         * orderAmount : 200
         * actualAmount : 200
         * gmtCreate : 2019-07-05 10:22:38
         * gmtModified : null
         * shopId : 1
         * shopLogo : null
         */

        private Integer id;
        private Integer orderId;
        private Integer sysId;
        private String orderNo;
        private Integer goodsId;
        private String goodsNo;
        private String goodsName;
        private String goodsImg;
        private Integer goodsNum;
        private Integer goodsPrice;
        private Integer goodsWeight;
        private Integer goodsCostPrice;
        private Integer goodsMarketPrice;
        private Integer userId;
        private Integer skuId;
        private String skuName;
        private String refundNumber;
        private Boolean refundStatus;
        private Integer refundType;
        private Integer refundAmount;
        private String refundTime;
        private Integer orderAmount;
        private Integer actualAmount;
        private String gmtCreate;
        private String gmtModified;
        private Integer shopId;
        private String shopLogo;



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

        public Integer getSysId() {
            return sysId;
        }

        public void setSysId(Integer sysId) {
            this.sysId = sysId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public Integer getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Integer goodsId) {
            this.goodsId = goodsId;
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

        public String getGoodsImg() {
            return goodsImg;
        }

        public void setGoodsImg(String goodsImg) {
            this.goodsImg = goodsImg;
        }

        public Integer getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(Integer goodsNum) {
            this.goodsNum = goodsNum;
        }

        public Integer getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(Integer goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public Integer getGoodsWeight() {
            return goodsWeight;
        }

        public void setGoodsWeight(Integer goodsWeight) {
            this.goodsWeight = goodsWeight;
        }

        public Integer getGoodsCostPrice() {
            return goodsCostPrice;
        }

        public void setGoodsCostPrice(Integer goodsCostPrice) {
            this.goodsCostPrice = goodsCostPrice;
        }

        public Integer getGoodsMarketPrice() {
            return goodsMarketPrice;
        }

        public void setGoodsMarketPrice(Integer goodsMarketPrice) {
            this.goodsMarketPrice = goodsMarketPrice;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
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

        public String getRefundNumber() {
            return refundNumber;
        }

        public void setRefundNumber(String refundNumber) {
            this.refundNumber = refundNumber;
        }

        public Boolean getRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(Boolean refundStatus) {
            this.refundStatus = refundStatus;
        }

        public Integer getRefundType() {
            return refundType;
        }

        public void setRefundType(Integer refundType) {
            this.refundType = refundType;
        }

        public Integer getRefundAmount() {
            return refundAmount;
        }

        public void setRefundAmount(Integer refundAmount) {
            this.refundAmount = refundAmount;
        }

        public String getRefundTime() {
            return refundTime;
        }

        public void setRefundTime(String refundTime) {
            this.refundTime = refundTime;
        }

        public Integer getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(Integer orderAmount) {
            this.orderAmount = orderAmount;
        }

        public Integer getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(Integer actualAmount) {
            this.actualAmount = actualAmount;
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

        public Integer getShopId() {
            return shopId;
        }

        public void setShopId(Integer shopId) {
            this.shopId = shopId;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }


        public OrderDataListBean() {
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
            dest.writeValue(this.orderId);
            dest.writeValue(this.sysId);
            dest.writeString(this.orderNo);
            dest.writeValue(this.goodsId);
            dest.writeString(this.goodsNo);
            dest.writeString(this.goodsName);
            dest.writeString(this.goodsImg);
            dest.writeValue(this.goodsNum);
            dest.writeValue(this.goodsPrice);
            dest.writeValue(this.goodsWeight);
            dest.writeValue(this.goodsCostPrice);
            dest.writeValue(this.goodsMarketPrice);
            dest.writeValue(this.userId);
            dest.writeValue(this.skuId);
            dest.writeString(this.skuName);
            dest.writeString(this.refundNumber);
            dest.writeValue(this.refundStatus);
            dest.writeValue(this.refundType);
            dest.writeValue(this.refundAmount);
            dest.writeString(this.refundTime);
            dest.writeValue(this.orderAmount);
            dest.writeValue(this.actualAmount);
            dest.writeString(this.gmtCreate);
            dest.writeString(this.gmtModified);
            dest.writeValue(this.shopId);
            dest.writeString(this.shopLogo);
        }

        protected OrderDataListBean(Parcel in) {
            this.id = (Integer) in.readValue(Integer.class.getClassLoader());
            this.orderId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.sysId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.orderNo = in.readString();
            this.goodsId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.goodsNo = in.readString();
            this.goodsName = in.readString();
            this.goodsImg = in.readString();
            this.goodsNum = (Integer) in.readValue(Integer.class.getClassLoader());
            this.goodsPrice = (Integer) in.readValue(Integer.class.getClassLoader());
            this.goodsWeight = (Integer) in.readValue(Integer.class.getClassLoader());
            this.goodsCostPrice = (Integer) in.readValue(Integer.class.getClassLoader());
            this.goodsMarketPrice = (Integer) in.readValue(Integer.class.getClassLoader());
            this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.skuId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.skuName = in.readString();
            this.refundNumber = in.readString();
            this.refundStatus = (Boolean) in.readValue(Boolean.class.getClassLoader());
            this.refundType = (Integer) in.readValue(Integer.class.getClassLoader());
            this.refundAmount = (Integer) in.readValue(Integer.class.getClassLoader());
            this.refundTime = in.readString();
            this.orderAmount = (Integer) in.readValue(Integer.class.getClassLoader());
            this.actualAmount = (Integer) in.readValue(Integer.class.getClassLoader());
            this.gmtCreate = in.readString();
            this.gmtModified = in.readString();
            this.shopId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.shopLogo = in.readString();
        }

        public static final Creator<OrderDataListBean> CREATOR = new Creator<OrderDataListBean>() {
            @Override
            public OrderDataListBean createFromParcel(Parcel source) {
                return new OrderDataListBean(source);
            }

            @Override
            public OrderDataListBean[] newArray(int size) {
                return new OrderDataListBean[size];
            }
        };
    }

    public OrderDetailReturnBean() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.userId);
        dest.writeString(this.userMobile);
        dest.writeValue(this.goodsId);
        dest.writeString(this.formId);
        dest.writeValue(this.goodsType);
        dest.writeString(this.orderNo);
        dest.writeString(this.orderTime);
        dest.writeString(this.payTime);
        dest.writeValue(this.payType);
        dest.writeString(this.orderAmount);
        dest.writeString(this.payAmount);
        dest.writeString(this.goodsAmount);
        dest.writeString(this.orderFreight);
        dest.writeString(this.transactionId);
        dest.writeValue(this.isExpressAmount);
        dest.writeString(this.receiptPerson);
        dest.writeString(this.receiptPhone);
        dest.writeString(this.receiptProvince);
        dest.writeString(this.receiptCity);
        dest.writeString(this.receiptArea);
        dest.writeString(this.receiptAddr);
        dest.writeValue(this.orderStatus);
        dest.writeValue(this.isRefund);
        dest.writeValue(this.refundStatus);
        dest.writeString(this.orderRemarks);
        dest.writeString(this.platformRemarks);
        dest.writeString(this.operator);
        dest.writeString(this.expressName);
        dest.writeString(this.expressNumber);
        dest.writeString(this.expressCode);
        dest.writeString(this.deliverAt);
        dest.writeString(this.confirmAt);
        dest.writeString(this.cancelAt);
        dest.writeString(this.cancelDesc);
        dest.writeString(this.gmtCreate);
        dest.writeString(this.gmtModified);
        dest.writeValue(this.status);
        dest.writeString(this.orderPerson);
        dest.writeValue(this.couponId);
        dest.writeValue(this.Integeregral);
        dest.writeValue(this.sysId);
        dest.writeString(this.actualAmount);
        dest.writeValue(this.shopId);
        dest.writeString(this.shopName);
        dest.writeString(this.searchName);
        dest.writeValue(this.normalType);
        dest.writeValue(this.isInvoice);
        dest.writeValue(this.invoiceType);
        dest.writeString(this.invoiceTitle);
        dest.writeString(this.invoiceContent);
        dest.writeString(this.invoiceNickname);
        dest.writeString(this.invoiceEnterpriseName);
        dest.writeString(this.invoiceTaxCode);
        dest.writeString(this.invoiceAddress);
        dest.writeString(this.invoiceMobile);
        dest.writeString(this.invoiceBankName);
        dest.writeString(this.invoiceAccount);
        dest.writeValue(this.orderStopTime);
        dest.writeString(this.totalGoodsNum);
        dest.writeTypedList(this.orderDataList);
    }

    protected OrderDetailReturnBean(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userMobile = in.readString();
        this.goodsId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.formId = in.readString();
        this.goodsType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.orderNo = in.readString();
        this.orderTime = in.readString();
        this.payTime = in.readString();
        this.payType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.orderAmount = in.readString();
        this.payAmount = in.readString();
        this.goodsAmount = in.readString();
        this.orderFreight = in.readString();
        this.transactionId = in.readString();
        this.isExpressAmount = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.receiptPerson = in.readString();
        this.receiptPhone = in.readString();
        this.receiptProvince = in.readString();
        this.receiptCity = in.readString();
        this.receiptArea = in.readString();
        this.receiptAddr = in.readString();
        this.orderStatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isRefund = (Integer) in.readValue(Integer.class.getClassLoader());
        this.refundStatus = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.orderRemarks = in.readString();
        this.platformRemarks = in.readString();
        this.operator = in.readString();
        this.expressName = in.readString();
        this.expressNumber = in.readString();
        this.expressCode = in.readString();
        this.deliverAt = in.readString();
        this.confirmAt = in.readString();
        this.cancelAt = in.readString();
        this.cancelDesc = in.readString();
        this.gmtCreate = in.readString();
        this.gmtModified = in.readString();
        this.status = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.orderPerson = in.readString();
        this.couponId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.Integeregral = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sysId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.actualAmount = in.readString();
        this.shopId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shopName = in.readString();
        this.searchName = in.readString();
        this.normalType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isInvoice = (Integer) in.readValue(Integer.class.getClassLoader());
        this.invoiceType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.invoiceTitle = in.readString();
        this.invoiceContent = in.readString();
        this.invoiceNickname = in.readString();
        this.invoiceEnterpriseName = in.readString();
        this.invoiceTaxCode = in.readString();
        this.invoiceAddress = in.readString();
        this.invoiceMobile = in.readString();
        this.invoiceBankName = in.readString();
        this.invoiceAccount = in.readString();
        this.orderStopTime = (Long) in.readValue(Long.class.getClassLoader());
        this.totalGoodsNum = in.readString();
        this.orderDataList = in.createTypedArrayList(OrderDataListBean.CREATOR);
    }

    public static final Creator<OrderDetailReturnBean> CREATOR = new Creator<OrderDetailReturnBean>() {
        @Override
        public OrderDetailReturnBean createFromParcel(Parcel source) {
            return new OrderDetailReturnBean(source);
        }

        @Override
        public OrderDetailReturnBean[] newArray(int size) {
            return new OrderDetailReturnBean[size];
        }
    };
}
