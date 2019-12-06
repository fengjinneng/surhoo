package com.surhoo.sh.designer.bean;

import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

public class DesignerListBean {

    /**
     * designerId : 10010085
     * designerName : 陈
     * headimgurl : https://wx.qlogo.cn/mmopen/vi_32/tw1LOSJbOXzhXO8DS9icj6AuxQESDjHlYMn8vIhlC4iasD3kNySE2bS6UF99OaMltBwsQ98wBeKZZm8Y71B1TjsQ/132
     * labelNames : 闪亮,设计师标签,(♥∀♥)
     * level : 1
     * detail : 链接
     * materialList : ["http://yaochengkun-shanghu.f.wmeimob.com/C97NB7A7R95G288L9Q2QS9763J2Q6948.png","http://yaochengkun-shanghu.f.wmeimob.com/L25ZSTP43P6425JJ55K9NU2Q7JGLHRPF.jpg","http://yaochengkun-shanghu.f.wmeimob.com/6P4SHM3U5Y5R25925D558637M96U32SL.jpg","http://yaochengkun-shanghu.f.wmeimob.com/37R3UP87S7KM2T2Q9N9R73J86K84SL52.png","http://yaochengkun-shanghu.f.wmeimob.com/TC8R2M2MT34K26PM9PKMS3S74WMS3932.png","http://yaochengkun-shanghu.f.wmeimob.com/4T73K85WNTK222UN6JJ5F4MN4972KL3P.png"]
     */

    private Integer designerId;
    private String designerName;
    private String headimgurl;
    private String labelNames;
    private Integer level;
    private String detail;
    private List<String> materialList;

    private boolean hasTagAdapter;
    private TagAdapter tagAdapter;

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

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(String labelNames) {
        this.labelNames = labelNames;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<String> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<String> materialList) {
        this.materialList = materialList;
    }
}
