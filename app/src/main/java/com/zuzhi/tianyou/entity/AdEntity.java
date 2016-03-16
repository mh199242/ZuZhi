package com.zuzhi.tianyou.entity;

import java.io.Serializable;

public class AdEntity implements Serializable {
    private String imgUrl;
    private long id;
    private String objType;
    private String remark;
    private String name;
    private String objId;
    private String keyWord;
    private String targetType;

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public long getId() {
        return id;
    }

    public String getObjType() {
        return objType;
    }

    public String getRemark() {
        return remark;
    }

    public String getName() {
        return name;
    }

    public String getObjId() {
        return objId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public String getTargetType() {
        return targetType;
    }
}