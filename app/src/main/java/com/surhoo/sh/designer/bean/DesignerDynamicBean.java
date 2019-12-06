package com.surhoo.sh.designer.bean;

import com.surhoo.sh.common.bean.PhotoInfo;

import java.util.ArrayList;
import java.util.List;

public class DesignerDynamicBean {


    /**
     * strDate : 2019/10/17
     * date : 1571315530000
     * trendsList : [{"year":null,"month":null,"day":null,"gmtCreate":1571315357000,"logo":"https://shanghusm-shanghai-picture.oss-cn-shanghai.aliyuncs.com/img/xcx/material/CNFx2BkxnkxbamkXamxjRftf4BH35K5X.png","isMaterial":true,"materialId":1107},{"year":null,"month":null,"day":null,"gmtCreate":1571315468000,"logo":"https://shanghusm-shanghai-picture.oss-cn-shanghai.aliyuncs.com/img/xcx/material/EdBfHx8hpxeeRiz6YtimX3H2BaFdmrn3.png","isMaterial":true,"materialId":1109},{"year":null,"month":null,"day":null,"gmtCreate":1571315530000,"logo":"https://shanghusm-shanghai-picture.oss-cn-shanghai.aliyuncs.com/img/xcx/material/neDaMEehNA6tmcdDezNZ4itCZSHGPwWs.png","isMaterial":true,"materialId":1111}]
     */

    private String strDate;
    private Long date;
    private List<TrendsListBean> trendsList;

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public List<TrendsListBean> getTrendsList() {
        return trendsList;
    }

    public void setTrendsList(List<TrendsListBean> trendsList) {
        this.trendsList = trendsList;
    }

    public static class TrendsListBean {
        /**
         * year : null
         * month : null
         * day : null
         * gmtCreate : 1571315357000
         * logo : https://shanghusm-shanghai-picture.oss-cn-shanghai.aliyuncs.com/img/xcx/material/CNFx2BkxnkxbamkXamxjRftf4BH35K5X.png
         * isMaterial : true
         * materialId : 1107
         */

        private Object year;
        private Object month;
        private Object day;
        private Long gmtCreate;
        private String logo;
        private Boolean isMaterial;
        private Integer materialId;

        public Object getYear() {
            return year;
        }

        public void setYear(Object year) {
            this.year = year;
        }

        public Object getMonth() {
            return month;
        }

        public void setMonth(Object month) {
            this.month = month;
        }

        public Object getDay() {
            return day;
        }

        public void setDay(Object day) {
            this.day = day;
        }

        public Long getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Long gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public Boolean isIsMaterial() {
            return isMaterial;
        }

        public void setIsMaterial(Boolean isMaterial) {
            this.isMaterial = isMaterial;
        }

        public Integer getMaterialId() {
            return materialId;
        }

        public void setMaterialId(Integer materialId) {
            this.materialId = materialId;
        }
    }
}
