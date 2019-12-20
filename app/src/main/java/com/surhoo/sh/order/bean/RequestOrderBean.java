package com.surhoo.sh.order.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class RequestOrderBean {


    /**
     * orderDataList : [{"id":1,"skuId":168,"goodsNum":1}]
     * orderRemarks : 订单备注
     * shipId : 279
     * isCarPay : false
     * orderAmount : 21
     * orderFreight : 10
     */

    private String orderRemarks;
    private int shipId;

    @JSONField(name = "isCarPay")
    private boolean isCarPay;
    private String orderAmount;
    private String orderFreight;
    private List<OrderDataListBean> orderDataList;
    private int isInvoice;
    private int invoiceId;

    @JSONField(name = "isBargain")
    private boolean isBargain;
    private int orderSource;



    public String getOrderRemarks() {
        return orderRemarks;
    }

    public void setOrderRemarks(String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public boolean isCarPay() {
        return isCarPay;
    }

    public void setCarPay(boolean carPay) {
        isCarPay = carPay;
    }

    public boolean isBargain() {
        return isBargain;
    }

    public void setBargain(boolean bargain) {
        isBargain = bargain;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderFreight() {
        return orderFreight;
    }

    public void setOrderFreight(String orderFreight) {
        this.orderFreight = orderFreight;
    }

    public List<OrderDataListBean> getOrderDataList() {
        return orderDataList;
    }

    public void setOrderDataList(List<OrderDataListBean> orderDataList) {
        this.orderDataList = orderDataList;
    }

    public int getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(int isInvoice) {
        this.isInvoice = isInvoice;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }



    public int getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(int orderSource) {
        this.orderSource = orderSource;
    }

    public static class OrderDataListBean {
        /**
         * id : 1
         * skuId : 168
         * goodsNum : 1
         */

        private int id;
        private int skuId;
        private int goodsNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSkuId() {
            return skuId;
        }

        public void setSkuId(int skuId) {
            this.skuId = skuId;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }


    }
}
