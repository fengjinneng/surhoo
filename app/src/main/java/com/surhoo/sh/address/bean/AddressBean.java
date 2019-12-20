package com.surhoo.sh.address.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AddressBean implements Parcelable {

    private Integer id;
    private Integer userId;
    private String userMobile;
    private String name;
    private String phone;
    private Integer provinceId;
    private Integer cityId;
    private Integer districtId;
    private String address;
    private Integer defaultStatus;
    private String createdAt;
    private String updatedAt;
    private Integer status;
    private String provinceName;
    private String cityName;
    private String districtName;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Integer defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeValue(this.provinceId);
        dest.writeValue(this.cityId);
        dest.writeValue(this.districtId);
        dest.writeString(this.address);
        dest.writeValue(this.defaultStatus);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeValue(this.status);
        dest.writeString(this.provinceName);
        dest.writeString(this.cityName);
        dest.writeString(this.districtName);
    }

    public AddressBean() {
    }

    protected AddressBean(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userMobile = in.readString();
        this.name = in.readString();
        this.phone = in.readString();
        this.provinceId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cityId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.districtId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.address = in.readString();
        this.defaultStatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.provinceName = in.readString();
        this.cityName = in.readString();
        this.districtName = in.readString();
    }

    public static final Parcelable.Creator<AddressBean> CREATOR = new Parcelable.Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel source) {
            return new AddressBean(source);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };
}
