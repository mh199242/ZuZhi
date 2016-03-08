package com.zuzhi.tianyou.bean;

import com.zuzhi.tianyou.entity.UserEntity;

/**
 * Created by Administrator on 2015/12/29.
 */
public class LoginBean {


    /**
     * models : null
     * message : 登录成功
     * errorMessage :
     * pageCount : 0
     * nextPageNo : 0
     * pageStr :
     * totalCount : 0
     * pageNo : 1
     * value : {"id":1457407576021000,"phone":null,"headImg":"http://wx.qlogo.cn/mmopen/7Xp96QiaBrG0PSOib5CmlrkOMGL6verr39GAc3oAyZ9jQbocibW4AMqcNrr4CvcuaH6UkOb9Mvvf4ZibLlT4icu8tZg/0","name":"卐","hasPhone":"0","workId":null,"companyName":null,"type":1}
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
    public UserEntity value;

    public UserEntity getValue() {
        return value;
    }

    public void setValue(UserEntity value) {
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
