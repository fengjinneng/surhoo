package com.surhoo.sh.shoppingcart;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class ShoppingCartBean implements Parcelable {



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


    public ShoppingCartBean() {
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }


    public static class CarGoodsListBean implements MultiItemEntity, Parcelable {


        public static final int head = 1;
        public static final int body = 2;
        public static final int foot = 3;
        private int itemType;
        private boolean checked;

        //标记每三个部分同样的flag 便于操作
        private int flag;

        public CarGoodsListBean() {
        }


        public CarGoodsListBean(int itemType, String shopName) {
            this.itemType = itemType;
            this.shopName = shopName;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
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


        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        @Override
        public int getItemType() {
            return this.itemType;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.itemType);
            dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
            dest.writeInt(this.flag);
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
            this.itemType = in.readInt();
            this.checked = in.readByte() != 0;
            this.flag = in.readInt();
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

        public static final Parcelable.Creator<CarGoodsListBean> CREATOR = new Parcelable.Creator<CarGoodsListBean>() {
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

    protected ShoppingCartBean(Parcel in) {
        this.shopId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shopName = in.readString();
        this.carGoodsList = in.createTypedArrayList(CarGoodsListBean.CREATOR);
    }

    public static final Parcelable.Creator<ShoppingCartBean> CREATOR = new Parcelable.Creator<ShoppingCartBean>() {
        @Override
        public ShoppingCartBean createFromParcel(Parcel source) {
            return new ShoppingCartBean(source);
        }

        @Override
        public ShoppingCartBean[] newArray(int size) {
            return new ShoppingCartBean[size];
        }
    };
}
