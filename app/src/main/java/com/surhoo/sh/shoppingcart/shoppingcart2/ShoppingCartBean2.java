package com.surhoo.sh.shoppingcart.shoppingcart2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ShoppingCartBean2 implements Parcelable {



    private Integer shopId;
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }


    public static class CarGoodsListBean implements Parcelable {


        private int tag;


        private boolean isHead;

        private boolean isBody;

        private boolean isFoot;

        private boolean check;

        private boolean headCheck;

        private boolean isDelete;

        private int position = -1;


        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public boolean isDelete() {
            return isDelete;
        }

        public void setDelete(boolean delete) {
            isDelete = delete;
        }

        public boolean isHeadCheck() {
            return headCheck;
        }

        public void setHeadCheck(boolean headCheck) {
            this.headCheck = headCheck;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public boolean isHead() {
            return isHead;
        }

        public void setHead(boolean head) {
            isHead = head;
        }

        public boolean isBody() {
            return isBody;
        }

        public void setBody(boolean body) {
            isBody = body;
        }

        public boolean isFoot() {
            return isFoot;
        }

        public void setFoot(boolean foot) {
            isFoot = foot;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        private Integer id;
        private Integer userId;
        private String shopName;
        private String goodsName;
        private String goodsImg;
        private Integer skuId;
        private String skuName;
        private Integer goodsNum;
        private Integer goodsId;
        private String goodsPrice;
        private String createdAt;
        private Integer status;
        private String goodsMarketPrice;

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

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
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

        public Integer getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(Integer goodsNum) {
            this.goodsNum = goodsNum;
        }

        public Integer getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Integer goodsId) {
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

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getGoodsMarketPrice() {
            return goodsMarketPrice;
        }

        public void setGoodsMarketPrice(String goodsMarketPrice) {
            this.goodsMarketPrice = goodsMarketPrice;
        }

        public CarGoodsListBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.tag);
            dest.writeByte(this.isHead ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isBody ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isFoot ? (byte) 1 : (byte) 0);
            dest.writeByte(this.check ? (byte) 1 : (byte) 0);
            dest.writeByte(this.headCheck ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isDelete ? (byte) 1 : (byte) 0);
            dest.writeInt(this.position);
            dest.writeValue(this.id);
            dest.writeValue(this.userId);
            dest.writeString(this.shopName);
            dest.writeString(this.goodsName);
            dest.writeString(this.goodsImg);
            dest.writeValue(this.skuId);
            dest.writeString(this.skuName);
            dest.writeValue(this.goodsNum);
            dest.writeValue(this.goodsId);
            dest.writeString(this.goodsPrice);
            dest.writeString(this.createdAt);
            dest.writeValue(this.status);
            dest.writeString(this.goodsMarketPrice);
        }

        protected CarGoodsListBean(Parcel in) {
            this.tag = in.readInt();
            this.isHead = in.readByte() != 0;
            this.isBody = in.readByte() != 0;
            this.isFoot = in.readByte() != 0;
            this.check = in.readByte() != 0;
            this.headCheck = in.readByte() != 0;
            this.isDelete = in.readByte() != 0;
            this.position = in.readInt();
            this.id = (Integer) in.readValue(Integer.class.getClassLoader());
            this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.shopName = in.readString();
            this.goodsName = in.readString();
            this.goodsImg = in.readString();
            this.skuId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.skuName = in.readString();
            this.goodsNum = (Integer) in.readValue(Integer.class.getClassLoader());
            this.goodsId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.goodsPrice = in.readString();
            this.createdAt = in.readString();
            this.status = (Integer) in.readValue(Integer.class.getClassLoader());
            this.goodsMarketPrice = in.readString();
        }

        public static final Creator<CarGoodsListBean> CREATOR = new Creator<CarGoodsListBean>() {
            @Override
            public CarGoodsListBean createFromParcel(Parcel source) {
                return new CarGoodsListBean(source);
            }

            @Override
            public CarGoodsListBean[] newArray(int size) {
                return new CarGoodsListBean[size];
            }
        };
    }


    public ShoppingCartBean2() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.shopId);
        dest.writeString(this.shopName);
        dest.writeTypedList(this.carGoodsList);
    }

    protected ShoppingCartBean2(Parcel in) {
        this.shopId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shopName = in.readString();
        this.carGoodsList = in.createTypedArrayList(CarGoodsListBean.CREATOR);
    }

    public static final Creator<ShoppingCartBean2> CREATOR = new Creator<ShoppingCartBean2>() {
        @Override
        public ShoppingCartBean2 createFromParcel(Parcel source) {
            return new ShoppingCartBean2(source);
        }

        @Override
        public ShoppingCartBean2[] newArray(int size) {
            return new ShoppingCartBean2[size];
        }
    };
}
