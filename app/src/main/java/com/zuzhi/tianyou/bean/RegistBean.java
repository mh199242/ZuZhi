package com.zuzhi.tianyou.bean;

import com.zuzhi.tianyou.entity.ProfessionEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/12/29.
 */
public class RegistBean {

    /**
     * models : null
     * message :
     * errorMessage :
     * pageCount : 1
     * nextPageNo : 0
     * pageStr :
     * totalCount : 6
     * pageNo : 1
     * value : [{"id":1456312895481002,"parentId":1456312895481002,"code":"kjcn","name":"会计出纳"},{"id":1456312895481003,"parentId":1456312895481003,"code":"qysyz","name":"企业所有者"},{"id":1456312895481004,"parentId":1456312895481004,"code":"glry","name":"管理人员"},{"id":1456312895481005,"parentId":1456312895481005,"code":"zy_sws","name":"税务师"},{"id":1456312895481006,"parentId":1456312895481006,"code":"zy_kjs","name":"会计师"},{"id":1456312895481007,"parentId":1456312895481007,"code":"zy_qt","name":"其他"}]
     * pageSize : 20
     * errorCode : 0
     * imgHost : http://101.200.160.210
     * success : true
     */

    private Object models;
    private String message;
    private String errorMessage;
    private int pageCount;
    private int nextPageNo;
    private String pageStr;
    private int totalCount;
    private int pageNo;
    private int pageSize;
    private int errorCode;
    private String imgHost;
    private boolean success;
    private List<ProfessionEntity> value;

    public List<ProfessionEntity> getValue() {
        return value;
    }

    public void setValue(List<ProfessionEntity> value) {
        this.value = value;
    }

    public void setModels(Object models) {
        this.models = models;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public void setPageStr(String pageStr) {
        this.pageStr = pageStr;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setImgHost(String imgHost) {
        this.imgHost = imgHost;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getModels() {
        return models;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public String getPageStr() {
        return pageStr;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getImgHost() {
        return imgHost;
    }

    public boolean isSuccess() {
        return success;
    }

}
