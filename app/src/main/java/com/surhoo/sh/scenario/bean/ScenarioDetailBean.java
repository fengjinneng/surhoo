package com.surhoo.sh.scenario.bean;

import java.util.List;

public class ScenarioDetailBean {


    /**
     * sceneId : 3
     * icon : https://essilor.oss-cn-shanghai.aliyuncs.com/images/KtzREs4neykh86NYrhXTjJRm28Cakm4G.jpg
     * name : 场景名称3
     * logo : https://essilor.oss-cn-shanghai.aliyuncs.com/images/KtzREs4neykh86NYrhXTjJRm28Cakm4G.jpg
     * detail : 场景描叙
     * infoList : [{"sceneId":4,"icon":"https://essilor.oss-cn-shanghai.aliyuncs.com/images/KtzREs4neykh86NYrhXTjJRm28Cakm4G.jpg","name":"二级场景1","logo":null,"detail":null,"infoList":null}]
     */

    private int sceneId;
    private String icon;
    private String name;
    private String logo;
    private String detail;
    private List<InfoListBean> infoList;

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<InfoListBean> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<InfoListBean> infoList) {
        this.infoList = infoList;
    }

    public static class InfoListBean {
        /**
         * sceneId : 4
         * icon : https://essilor.oss-cn-shanghai.aliyuncs.com/images/KtzREs4neykh86NYrhXTjJRm28Cakm4G.jpg
         * name : 二级场景1
         * logo : null
         * detail : null
         * infoList : null
         */

        private int sceneId;
        private String icon;
        private String name;
        private String logo;
        private String detail;
        private Object infoList;

        public int getSceneId() {
            return sceneId;
        }

        public void setSceneId(int sceneId) {
            this.sceneId = sceneId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public Object getInfoList() {
            return infoList;
        }

        public void setInfoList(Object infoList) {
            this.infoList = infoList;
        }
    }
}
