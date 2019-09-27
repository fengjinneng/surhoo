package com.surhoo.sh.invoice;

public class InvoiceBean {


    /**
     * id : 62
     * normalType : 2
     * invoiceType : 1
     * title :
     * content :
     * nickname :
     * userId : 10010085
     * enterpriseName :
     * taxCode :
     * address :
     * mobile : 15516965560
     * bankName :
     * loginAddress :
     * account :
     * gmtCreate : 2019-08-18 20:39:24
     * gmtModified : null
     * defaultStatus : false
     */

    private Integer id;
    private Integer normalType;
    private Integer invoiceType;
    private String title;
    private String content;
    private String nickname;
    private Integer userId;
    private String enterpriseName;
    private String taxCode;
    private String address;
    private String mobile;
    private String bankName;
    private String loginAddress;
    private String account;
    private String gmtCreate;
    private Object gmtModified;
    private Boolean defaultStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNormalType() {
        return normalType;
    }

    public void setNormalType(Integer normalType) {
        this.normalType = normalType;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Object getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Object gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Boolean defaultStatus) {
        this.defaultStatus = defaultStatus;
    }
}
