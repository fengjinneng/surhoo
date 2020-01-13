package com.surhoo.sh.login.bean;

public class UserDataBean {


    private UserBean user;
    private String token;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserBean {


        private Integer id;
        private String mobile;
        private String appid;
        private String openid;
        private String nickname;
        private String sex;
        private String province;
        private String city;
        private String country;
        private String headimgurl;
        private String createdAt;
        private Integer status;
        private Boolean isDesigner;
        private Boolean isMerchant;
        private Boolean isDistributor;
        private Double balance;
        private Double distributorBalance;
        private Double designerBalance;
        private Double shopBalance;
        private String isPerson;
        private String bankName;
        private Boolean enabled;
        private Boolean accountNonExpired;
        private Boolean accountNonLocked;
        private Boolean credentialsNonExpired;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
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

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public Double getDistributorBalance() {
            return distributorBalance;
        }

        public void setDistributorBalance(Double distributorBalance) {
            this.distributorBalance = distributorBalance;
        }

        public Double getDesignerBalance() {
            return designerBalance;
        }

        public void setDesignerBalance(Double designerBalance) {
            this.designerBalance = designerBalance;
        }

        public Double getShopBalance() {
            return shopBalance;
        }

        public void setShopBalance(Double shopBalance) {
            this.shopBalance = shopBalance;
        }

        public String getIsPerson() {
            return isPerson;
        }

        public void setIsPerson(String isPerson) {
            this.isPerson = isPerson;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getAccountNonExpired() {
            return accountNonExpired;
        }

        public void setAccountNonExpired(Boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
        }

        public Boolean getAccountNonLocked() {
            return accountNonLocked;
        }

        public void setAccountNonLocked(Boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
        }

        public Boolean getCredentialsNonExpired() {
            return credentialsNonExpired;
        }

        public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
        }
    }
}
