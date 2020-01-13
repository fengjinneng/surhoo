package com.surhoo.sh.user.bean;

public class UserInfoBean {


    /**
     * id : 10025392
     * nickname : lun
     * headimgurl : https://wx.qlogo.cn/mmopen/vi_32/gJlnj3aobc05SjQda4vF9QyDpyib7upGiaKo0zCnOSkCdqj7ApfSFArPRQyGWBfKdCdr6StZma6ydR9ru3HBMCBw/132
     * isDesigner : false
     * isMerchant : false
     * isDistributor : false
     * distributorStatus : null
     * isLogin : true
     * isFirstDistributor : false
     * province : null
     * sex : null
     * city : null
     */

    private Integer id;
    private String nickname;
    private String headimgurl;
    private Boolean isDesigner;
    private Boolean isMerchant;
    private Boolean isDistributor;
    private Object distributorStatus;
    private Boolean isLogin;
    private Boolean isFirstDistributor;
    private String province;
    private Boolean sex;
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Boolean getDesigner() {
        return isDesigner;
    }

    public void setDesigner(Boolean designer) {
        isDesigner = designer;
    }

    public Boolean getMerchant() {
        return isMerchant;
    }

    public void setMerchant(Boolean merchant) {
        isMerchant = merchant;
    }

    public Boolean getDistributor() {
        return isDistributor;
    }

    public void setDistributor(Boolean distributor) {
        isDistributor = distributor;
    }

    public Object getDistributorStatus() {
        return distributorStatus;
    }

    public void setDistributorStatus(Object distributorStatus) {
        this.distributorStatus = distributorStatus;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public Boolean getFirstDistributor() {
        return isFirstDistributor;
    }

    public void setFirstDistributor(Boolean firstDistributor) {
        isFirstDistributor = firstDistributor;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
