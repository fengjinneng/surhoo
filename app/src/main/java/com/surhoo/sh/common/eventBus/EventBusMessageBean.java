package com.surhoo.sh.common.eventBus;

public class EventBusMessageBean {


    private int code;
    private int param;
    private String message;
    private Object obj;





    public static class Invoice{

        public static final int choiceInvoice  = 1001;

        public static final int addInvoiceSuccess = 1002;

    }


    public static class Address{
        public static final int addAddressSuccess  = 2001;

        public static final int choiceAddress  = 2002;

        public static final int updateAddressSuccess  = 2003;
    }

    public static class Order{
        public static final int cancleOrderSuccess  =3001;

        public static final int payOrderSuccess  =3002;

        public static final int cancelPay  =3003;

        public static final int confirmOrder  =3004;

        public static final int deleteOrder  =3005;

    }


    public static class Img{
        public static final int closeImg  =4001;

    }

    public static class User{
        public static final int login  =5001;
        public static final int updateNickNameSuccess  =5002;

    }

    public static class Collect{
        public static final int cancelGoodsCollect  =6001;
        public static final int cancelMaterialCollect  =6002;
        public static final int cancelDesignerCollect  =6003;

    }
















    public int getParam() {
        return param;
    }

    public String getMessage() {
        return message;
    }

    public Object getObj() {
        return obj;
    }

    public int getCode() {
        return code;
    }

    public EventBusMessageBean(int code) {
        this.code = code;
    }

    public EventBusMessageBean(int code, int param) {
        this.code = code;
        this.param = param;
    }

    public EventBusMessageBean(int code, int param, String message) {
        this.code = code;
        this.param = param;
        this.message = message;
    }


    public EventBusMessageBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public EventBusMessageBean(int code, Object obj) {
        this.code = code;
        this.obj = obj;
    }

    public EventBusMessageBean(int code, String message, Object obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
    }

    public EventBusMessageBean(int code, int param, String message, Object obj) {
        this.code = code;
        this.param = param;
        this.message = message;
        this.obj = obj;
    }

    public EventBusMessageBean(int code, int param, Object obj) {
        this.code = code;
        this.param = param;
        this.obj = obj;
    }


}

