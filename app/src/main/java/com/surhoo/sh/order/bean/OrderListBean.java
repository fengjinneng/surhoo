package com.surhoo.sh.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

public class OrderListBean implements Parcelable {


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
//    private Object userMobile;
    private Integer goodsId;
//    private Object formId;
//    private Object goodsType;
    private String orderNo;
    private String shopName;
    private String orderTime;
    private String payTime;
    private Integer payType;
    private Integer orderAmount;
    private Integer payAmount;
//    private Object goodsAmount;
    private Integer orderFreight;
//    private Object transactionId;
    private Boolean isExpressAmount;
//    private Object receiptPerson;
//    private Object receiptPhone;
//    private Object receiptProvince;
//    private Object receiptCity;
//    private Object receiptArea;
//    private Object receiptAddr;
    private Integer orderStatus;
    private String expressName;
    private String expressNumber;
    private String deliverAt;
    private String confirmAt;
    private String cancelAt;
//    private Object cancelDesc;
    private String gmtCreate;
    private String gmtModified;
//    private Object status;
    private String orderPerson;
    private Integer totalGoodsNum;
    private List<OrderDataListBean> orderDataList;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getOrderFreight() {
        return orderFreight;
    }

    public void setOrderFreight(Integer orderFreight) {
        this.orderFreight = orderFreight;
    }

    public Boolean getExpressAmount() {
        return isExpressAmount;
    }

    public void setExpressAmount(Boolean expressAmount) {
        isExpressAmount = expressAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getOrderPerson() {
        return orderPerson;
    }

    public void setOrderPerson(String orderPerson) {
        this.orderPerson = orderPerson;
    }

    public Integer getTotalGoodsNum() {
        return totalGoodsNum;
    }

    public void setTotalGoodsNum(Integer totalGoodsNum) {
        this.totalGoodsNum = totalGoodsNum;
    }

    public List<OrderDataListBean> getOrderDataList() {
        return orderDataList;
    }

    public void setOrderDataList(List<OrderDataListBean> orderDataList) {
        this.orderDataList = orderDataList;
    }

    public static class OrderDataListBean implements Parcelable {



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
        private Boolean refundType;
        private Integer refundAmount;
        private String refundTime;
        private Integer orderAmount;
        private Integer actualAmount;
        private String gmtCreate;
//        private Object gmtModified;
        private Integer shopId;

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

        public Boolean isRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(Boolean refundStatus) {
            this.refundStatus = refundStatus;
        }

        public Boolean isRefundType() {
            return refundType;
        }

        public void setRefundType(Boolean refundType) {
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


        public Integer getShopId() {
            return shopId;
        }

        public void setShopId(Integer shopId) {
            this.shopId = shopId;
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
            dest.writeValue(this.shopId);
        }

        public OrderDataListBean() {
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
            this.refundType = (Boolean) in.readValue(Boolean.class.getClassLoader());
            this.refundAmount = (Integer) in.readValue(Integer.class.getClassLoader());
            this.refundTime = in.readString();
            this.orderAmount = (Integer) in.readValue(Integer.class.getClassLoader());
            this.actualAmount = (Integer) in.readValue(Integer.class.getClassLoader());
            this.gmtCreate = in.readString();
            this.shopId = (Integer) in.readValue(Integer.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.userId);
        dest.writeValue(this.goodsId);
        dest.writeString(this.orderNo);
        dest.writeString(this.shopName);
        dest.writeString(this.orderTime);
        dest.writeString(this.payTime);
        dest.writeValue(this.payType);
        dest.writeValue(this.orderAmount);
        dest.writeValue(this.payAmount);
        dest.writeValue(this.orderFreight);
        dest.writeValue(this.isExpressAmount);
        dest.writeValue(this.orderStatus);
        dest.writeString(this.expressName);
        dest.writeString(this.expressNumber);
        dest.writeString(this.deliverAt);
        dest.writeString(this.confirmAt);
        dest.writeString(this.cancelAt);
        dest.writeString(this.gmtCreate);
        dest.writeString(this.gmtModified);
        dest.writeString(this.orderPerson);
        dest.writeValue(this.totalGoodsNum);
        dest.writeList(this.orderDataList);
    }

    public OrderListBean() {
    }

    protected OrderListBean(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.goodsId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.orderNo = in.readString();
        this.shopName = in.readString();
        this.orderTime = in.readString();
        this.payTime = in.readString();
        this.payType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.orderAmount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.payAmount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.orderFreight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isExpressAmount = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.orderStatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.expressName = in.readString();
        this.expressNumber = in.readString();
        this.deliverAt = in.readString();
        this.confirmAt = in.readString();
        this.cancelAt = in.readString();
        this.gmtCreate = in.readString();
        this.gmtModified = in.readString();
        this.orderPerson = in.readString();
        this.totalGoodsNum = (Integer) in.readValue(Integer.class.getClassLoader());
        this.orderDataList = new ArrayList<OrderDataListBean>();
        in.readList(this.orderDataList, OrderDataListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<OrderListBean> CREATOR = new Parcelable.Creator<OrderListBean>() {
        @Override
        public OrderListBean createFromParcel(Parcel source) {
            return new OrderListBean(source);
        }

        @Override
        public OrderListBean[] newArray(int size) {
            return new OrderListBean[size];
        }
    };
}
