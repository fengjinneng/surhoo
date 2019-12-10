package com.surhoo.sh.invoice.bean;

public class RequestSaveInvocieBean {


    private Integer normalType;
    private Integer invoiceType;
    private String title;
    private String content;
    private String enterpriseName;
    private String mobile;
    private Integer defaultStatus;
    private String taxCode;
    private String address;
    private String bankName;
    private String account;
    private String nickname;
    private String loginAddress;


    public String beCommonPersonal() {
        return "{" +
                "\"normalType\":" + normalType +
                ", \"invoiceType\":" + invoiceType +
                ", \"title\":" +"\""+ title +"\"" +
                ", \"content\":" +"\""+ content +"\""  +
                ", \"mobile\":" + mobile  +
                ", \"defaultStatus\":" + defaultStatus +
                '}';
    }

    public String beCommonCompany() {
        return "{" +
                "\"normalType\":" + normalType +
                ", \"invoiceType\":" + invoiceType +
                ", \"title\":"+"\"" + title +"\"" +
                ", \"content\":"+"\"" + content +"\"" +
                ", \"mobile\":" +"\""+ mobile +"\"" +
                ", \"taxCode\":" +"\""+ taxCode +"\"" +
                ", \"defaultStatus\":" + defaultStatus +
                '}';
    }

    public String beSpecialVatInvoice() {
        return "{" +
                ", \"invoiceType\":" + invoiceType +
                ", \"content\":"+"\"" + content +"\"" +
                ", \"enterpriseName\":" +"\""+ enterpriseName+"\""  +
                ", \"mobile\":"+"\"" + mobile+"\""  +
                ", \"defaultStatus\":" + defaultStatus +
                ", \"taxCode\":"+"\"" + taxCode +"\""+
                ", \"address\":"+"\"" + address +"\"" +
                ", \"bankName\":"+"\"" + bankName +"\""+
                ", \"account\":" +"\""+ account+"\"" +
                ", \"nickname\":" +"\""+ nickname +"\""+
                ", \"loginAddress\":"+"\"" + loginAddress +"\""+
                '}';
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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Integer defaultStatus) {
        this.defaultStatus = defaultStatus;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }
}
