package com.surhoo.sh.material.bean;

import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

public class MaterialBean {

    /**
     * materialId : 2
     * logo : https://wx.qlogo.cn/mmopen/vi_32/uGOeNuw7ln7BEUFDaNP5WuVGXoqxDRSeibGs1zCWFcHLcEH6hFY7Wn02k5ibictfOmbKGGMpeznEVfbcbficd058YQ/132
     * name : 素材名称
     * detail : 素材简单的介绍
     * labelIds : #16#,#17#
     * labelName : moon,cat
     * labelInfoList : [{"labelId":16,"name":"moon"},{"labelId":17,"name":"cat"}]
     * price : 100
     * isCollect : true
     * video : https://wx.qlogo.cn/mmopen/vi_32/uGOeNuw7ln7BEUFDaNP5WuVGXoqxDRSeibGs1zCWFcHLcEH6hFY7Wn02k5ibictfOmbKGGMpeznEVfbcbficd058YQ/132
     * richText : 啊啊啊啊啊啊
     */

    private Integer materialId;
    private String logo;
    private String name;
    private String detail;
    private String labelIds;
    private String labelName;
    private String price;
    private Boolean isCollect;
    private String video;
    private String richText;
    private Integer designerId;
    private List<LabelInfoListBean> labelInfoList;
    private boolean hasTagAdapter;
    private TagAdapter tagAdapter;

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public boolean isHasTagAdapter() {
        return hasTagAdapter;
    }

    public void setHasTagAdapter(boolean hasTagAdapter) {
        this.hasTagAdapter = hasTagAdapter;
    }

    public TagAdapter getTagAdapter() {
        return tagAdapter;
    }

    public void setTagAdapter(TagAdapter tagAdapter) {
        this.tagAdapter = tagAdapter;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getCollect() {
        return isCollect;
    }

    public void setCollect(Boolean collect) {
        isCollect = collect;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getRichText() {
        return richText;
    }

    public void setRichText(String richText) {
        this.richText = richText;
    }

    public List<LabelInfoListBean> getLabelInfoList() {
        return labelInfoList;
    }

    public void setLabelInfoList(List<LabelInfoListBean> labelInfoList) {
        this.labelInfoList = labelInfoList;
    }

    public static class LabelInfoListBean {
        /**
         * labelId : 16
         * name : moon
         */

        private Integer labelId;
        private String name;

        public Integer getLabelId() {
            return labelId;
        }

        public void setLabelId(Integer labelId) {
            this.labelId = labelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
