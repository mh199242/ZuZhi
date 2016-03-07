package com.zuzhi.tianyou.bean;

import com.zuzhi.tianyou.entity.LoginEntity;

/**
 * Created by Administrator on 2015/12/29.
 */
public class LoginBean {
    /**
     * isSuccess:方法是否成功  true 成功 false失败,
     * errorMessage:错误信息,
     * message:成功返回值,
     * errorCode:错误代码 同步一个枚举类ErrorCode,
     * totalCount:返回值中的总记录数,
     * pageSize:每页多少数据,默认20条,
     * pageNo:当前第几页,
     * pageCount:总页数,
     * nextPageNo:下一页页数，如果为0则表示无下一页,
     * imgHost:图片服务器地址
     * value:LoginEntity
     */
    private boolean isSuccess;
    private String errorMessage;
    private String message;
    private String errorCode;
    private String totalCount;
    private String pageSize;
    private String pageNo;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(String nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public String getImgHost() {
        return imgHost;
    }

    public void setImgHost(String imgHost) {
        this.imgHost = imgHost;
    }

    public LoginEntity getValue() {
        return value;
    }

    public void setValue(LoginEntity value) {
        this.value = value;
    }

    public String pageCount;
    public String nextPageNo;
    public String imgHost;
    public LoginEntity value;
}
