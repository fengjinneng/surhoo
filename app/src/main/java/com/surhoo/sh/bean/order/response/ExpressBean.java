package com.surhoo.sh.bean.order.response;

import java.util.List;

public class ExpressBean {


    /**
     * LogisticCode : 803606830189712542
     * ShipperCode : 圆通速递
     * Traces : [{"AcceptStation":"【上海市嘉定区江桥公司】  已收件","AcceptTime":"2018-12-23 16:33:06"},{"AcceptStation":"【上海市嘉定区江桥公司】 已收件","AcceptTime":"2018-12-23 20:15:38"},{"AcceptStation":"【上海转运中心】 已收入","AcceptTime":"2018-12-23 21:53:54"},{"AcceptStation":"【上海转运中心】 已发出 下一站 【上海市松江区九亭公司】","AcceptTime":"2018-12-23 21:56:33"},{"AcceptStation":"【上海市松江区九亭公司】 已收入","AcceptTime":"2018-12-24 08:50:51"},{"AcceptStation":"【上海市松江区九亭公司】 派件人 :周云龙 派件中 派件员电话13381701091","AcceptTime":"2018-12-24 08:59:27"},{"AcceptStation":"客户 签收人 :施 已签收 感谢使用圆通速递，期待再次为您服务","AcceptTime":"2018-12-24 17:19:41"}]
     * State : 3
     * EBusinessID : 1380404
     * Success : true
     */

    private String LogisticCode;
    private String ShipperCode;
    private String State;
    private String EBusinessID;
    private Boolean Success;
    private List<TracesBean> Traces;

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String LogisticCode) {
        this.LogisticCode = LogisticCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String ShipperCode) {
        this.ShipperCode = ShipperCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public Boolean isSuccess() {
        return Success;
    }

    public void setSuccess(Boolean Success) {
        this.Success = Success;
    }

    public List<TracesBean> getTraces() {
        return Traces;
    }

    public void setTraces(List<TracesBean> Traces) {
        this.Traces = Traces;
    }

    public static class TracesBean {
        /**
         * AcceptStation : 【上海市嘉定区江桥公司】  已收件
         * AcceptTime : 2018-12-23 16:33:06
         */

        private String AcceptStation;
        private String AcceptTime;

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String AcceptStation) {
            this.AcceptStation = AcceptStation;
        }

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String AcceptTime) {
            this.AcceptTime = AcceptTime;
        }
    }
}
