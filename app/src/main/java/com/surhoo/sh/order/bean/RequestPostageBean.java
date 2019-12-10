package com.surhoo.sh.order.bean;

import java.util.List;

public class RequestPostageBean {

    /**
     * goodsList : [{"id":1,"goodsNum":1}]
     * shipId : 279
     */

    private Integer shipId;
    private List<GoodsListBean> goodsList;

    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class GoodsListBean {
        /**
         * id : 1
         * goodsNum : 1
         */

        private Integer id;
        private Integer goodsNum;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(Integer goodsNum) {
            this.goodsNum = goodsNum;
        }


        @Override
        public String toString() {
            return "{" +
                    "\"id\":" + id +
                    ", \"goodsNum\":" + goodsNum +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "{" +
                "\"shipId\":" + shipId +
                ", \"goodsList\":" + goodsList +
                '}';
    }
}
