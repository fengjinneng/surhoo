package com.surhoo.sh.bean.order.request;

import java.util.List;

public class GetPostageBean {


    private int shipId;
    private List<GoodsListBean> goodsList;

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
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

        private int id;
        private int goodsNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }
    }
}
