package com.surhoo.sh.bean.order.response;

import android.os.Parcel;
import android.os.Parcelable;

public class PayOrderSuccessBean implements Parcelable {


    private String orderNo;
    private int orderId;
    private PayBean pay;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }



    public static class PayBean implements Parcelable {

        private String appid;
        private String partnerid;
        private String prepayid;
        private String signNew;
        private String noncestr;
        private String timestamp;


        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSignNew() {
            return signNew;
        }

        public void setSignNew(String signNew) {
            this.signNew = signNew;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.appid);
            dest.writeString(this.partnerid);
            dest.writeString(this.prepayid);
            dest.writeString(this.signNew);
            dest.writeString(this.noncestr);
            dest.writeString(this.timestamp);
        }

        public PayBean() {
        }

        protected PayBean(Parcel in) {
            this.appid = in.readString();
            this.partnerid = in.readString();
            this.prepayid = in.readString();
            this.signNew = in.readString();
            this.noncestr = in.readString();
            this.timestamp = in.readString();
        }

        public static final Parcelable.Creator<PayBean> CREATOR = new Parcelable.Creator<PayBean>() {
            @Override
            public PayBean createFromParcel(Parcel source) {
                return new PayBean(source);
            }

            @Override
            public PayBean[] newArray(int size) {
                return new PayBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderNo);
        dest.writeInt(this.orderId);
        dest.writeParcelable(this.pay, flags);
    }

    public PayOrderSuccessBean() {
    }

    protected PayOrderSuccessBean(Parcel in) {
        this.orderNo = in.readString();
        this.orderId = in.readInt();
        this.pay = in.readParcelable(PayBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PayOrderSuccessBean> CREATOR = new Parcelable.Creator<PayOrderSuccessBean>() {
        @Override
        public PayOrderSuccessBean createFromParcel(Parcel source) {
            return new PayOrderSuccessBean(source);
        }

        @Override
        public PayOrderSuccessBean[] newArray(int size) {
            return new PayOrderSuccessBean[size];
        }
    };
}
