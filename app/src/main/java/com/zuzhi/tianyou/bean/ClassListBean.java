package com.zuzhi.tianyou.bean;

import java.util.List;

/**
 * Created by 超 on 2016/3/16.
 */
public class ClassListBean {
    /**
     * models : null
     * message : 查询成功
     * errorMessage :
     * pageCount : 0
     * nextPageNo : 0
     * pageStr :
     * totalCount : 0
     * pageNo : 1
     * value : {"ad":[],"category":[{"id":1454037672918009,"name":"注销代理"},{"id":1454037672918008,"name":"变更"},{"id":1454037672918007,"name":"公司设立"}],"item":[]}
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
    private ValueEntity value;
    private int pageSize;
    private int errorCode;
    private String imgHost;
    private boolean success;

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

    public void setValue(ValueEntity value) {
        this.value = value;
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

    public ValueEntity getValue() {
        return value;
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

    public static class ValueEntity {
        private List<?> ad;
        /**
         * id : 1454037672918009
         * name : 注销代理
         */

        private List<CategoryEntity> category;
        private List<?> item;

        public void setAd(List<?> ad) {
            this.ad = ad;
        }

        public void setCategory(List<CategoryEntity> category) {
            this.category = category;
        }

        public void setItem(List<?> item) {
            this.item = item;
        }

        public List<?> getAd() {
            return ad;
        }

        public List<CategoryEntity> getCategory() {
            return category;
        }

        public List<?> getItem() {
            return item;
        }

        public static class CategoryEntity {
            private long id;
            private String name;

            public void setId(long id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }
    }
}
