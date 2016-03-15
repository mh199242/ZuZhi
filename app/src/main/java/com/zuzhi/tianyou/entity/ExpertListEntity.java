package com.zuzhi.tianyou.entity;

import java.util.List;

public class ExpertListEntity {
    private String myd;
    private int orderNum;
    private String workingHours;
    private String pj;
    private String name;
    private long seId;
    private List<?> scly;

    public void setMyd(String myd) {
        this.myd = myd;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public void setPj(String pj) {
        this.pj = pj;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeId(long seId) {
        this.seId = seId;
    }

    public void setScly(List<?> scly) {
        this.scly = scly;
    }

    public String getMyd() {
        return myd;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public String getPj() {
        return pj;
    }

    public String getName() {
        return name;
    }

    public long getSeId() {
        return seId;
    }

    public List<?> getScly() {
        return scly;
    }
}