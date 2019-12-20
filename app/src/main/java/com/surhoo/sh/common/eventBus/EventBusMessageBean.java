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

