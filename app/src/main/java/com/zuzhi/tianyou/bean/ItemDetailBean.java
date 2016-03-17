package com.zuzhi.tianyou.bean;

import com.zuzhi.tianyou.entity.ShopCertificateEntity;

import java.util.List;

/**
 * Created by 超 on 2016/3/17.
 */
public class ItemDetailBean {

    /**
     * models : null
     * message : 查询成功
     * errorMessage :
     * pageCount : 0
     * nextPageNo : 0
     * pageStr :
     * totalCount : 0
     * pageNo : 1
     * value : {"itemShopPrice":100,"itemPromoteEndDate":null,"shopArea":"北京","itemPromoteStartDate":null,"expertId":1457677461009000,"expertName":"张律师","invFlag":null,"id":1458025271940007,"itemPromotePrice":90,"itemViewUrl":"/item/2016/3/15/1458025271940007.html","shopRemark":"本事务所。。。。。。。。","shopCertificate":[{"url":"/shop/2016/3/11/ec2ba76c583547f2981ef7c32c4d5469.JPG","name":"等级证书"},{"url":"/shop/2016/3/11/s_cf585ea8c50e4139b43333b61cb1793e.JPG","name":"入围证书"},{"url":"/shop/2016/3/11/8ce3bc0793494f5d9acef23b4c9ad509.JPG","name":"营业执照"}],"itemMarketPrice":null,"itemPromote":false,"shopName":"永大律师事务所","shopId":1457669285466002,"itemImg":"测试商品1","name":"测试商品1","comment":[],"expertWorkingHours":"10","itemThumbImg":"测试商品1","collectionFlag":0,"itemSaleNum":1}
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
    /**
     * itemShopPrice : 100
     * itemPromoteEndDate : null
     * shopArea : 北京
     * itemPromoteStartDate : null
     * expertId : 1457677461009000
     * expertName : 张律师
     * invFlag : null
     * id : 1458025271940007
     * itemPromotePrice : 90
     * itemViewUrl : /item/2016/3/15/1458025271940007.html
     * shopRemark : 本事务所。。。。。。。。
     * shopCertificate : [{"url":"/shop/2016/3/11/ec2ba76c583547f2981ef7c32c4d5469.JPG","name":"等级证书"},{"url":"/shop/2016/3/11/s_cf585ea8c50e4139b43333b61cb1793e.JPG","name":"入围证书"},{"url":"/shop/2016/3/11/8ce3bc0793494f5d9acef23b4c9ad509.JPG","name":"营业执照"}]
     * itemMarketPrice : null
     * itemPromote : false
     * shopName : 永大律师事务所
     * shopId : 1457669285466002
     * itemImg : 测试商品1
     * name : 测试商品1
     * comment : []
     * expertWorkingHours : 10
     * itemThumbImg : 测试商品1
     * collectionFlag : 0
     * itemSaleNum : 1
     */

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
        private int itemShopPrice;
        private Object itemPromoteEndDate;
        private String shopArea;
        private Object itemPromoteStartDate;
        private long expertId;
        private String expertName;
        private Object invFlag;
        private long id;
        private int itemPromotePrice;
        private String itemViewUrl;
        private String shopRemark;
        private int itemMarketPrice;
        private boolean itemPromote;
        private String shopName;
        private long shopId;
        private String itemImg;
        private String name;
        private String expertWorkingHours;
        private String itemThumbImg;
        private int collectionFlag;
        private int itemSaleNum;
        /**
         * url : /shop/2016/3/11/ec2ba76c583547f2981ef7c32c4d5469.JPG
         * name : 等级证书
         */

        private List<ShopCertificateEntity> shopCertificate;
        private List<?> comment;

        public void setItemShopPrice(int itemShopPrice) {
            this.itemShopPrice = itemShopPrice;
        }

        public void setItemPromoteEndDate(Object itemPromoteEndDate) {
            this.itemPromoteEndDate = itemPromoteEndDate;
        }

        public void setShopArea(String shopArea) {
            this.shopArea = shopArea;
        }

        public void setItemPromoteStartDate(Object itemPromoteStartDate) {
            this.itemPromoteStartDate = itemPromoteStartDate;
        }

        public void setExpertId(long expertId) {
            this.expertId = expertId;
        }

        public void setExpertName(String expertName) {
            this.expertName = expertName;
        }

        public void setInvFlag(Object invFlag) {
            this.invFlag = invFlag;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setItemPromotePrice(int itemPromotePrice) {
            this.itemPromotePrice = itemPromotePrice;
        }

        public void setItemViewUrl(String itemViewUrl) {
            this.itemViewUrl = itemViewUrl;
        }

        public void setShopRemark(String shopRemark) {
            this.shopRemark = shopRemark;
        }

        public void setItemMarketPrice(int itemMarketPrice) {
            this.itemMarketPrice = itemMarketPrice;
        }

        public void setItemPromote(boolean itemPromote) {
            this.itemPromote = itemPromote;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public void setShopId(long shopId) {
            this.shopId = shopId;
        }

        public void setItemImg(String itemImg) {
            this.itemImg = itemImg;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setExpertWorkingHours(String expertWorkingHours) {
            this.expertWorkingHours = expertWorkingHours;
        }

        public void setItemThumbImg(String itemThumbImg) {
            this.itemThumbImg = itemThumbImg;
        }

        public void setCollectionFlag(int collectionFlag) {
            this.collectionFlag = collectionFlag;
        }

        public void setItemSaleNum(int itemSaleNum) {
            this.itemSaleNum = itemSaleNum;
        }

        public void setShopCertificate(List<ShopCertificateEntity> shopCertificate) {
            this.shopCertificate = shopCertificate;
        }

        public void setComment(List<?> comment) {
            this.comment = comment;
        }

        public int getItemShopPrice() {
            return itemShopPrice;
        }

        public Object getItemPromoteEndDate() {
            return itemPromoteEndDate;
        }

        public String getShopArea() {
            return shopArea;
        }

        public Object getItemPromoteStartDate() {
            return itemPromoteStartDate;
        }

        public long getExpertId() {
            return expertId;
        }

        public String getExpertName() {
            return expertName;
        }

        public Object getInvFlag() {
            return invFlag;
        }

        public long getId() {
            return id;
        }

        public int getItemPromotePrice() {
            return itemPromotePrice;
        }

        public String getItemViewUrl() {
            return itemViewUrl;
        }

        public String getShopRemark() {
            return shopRemark;
        }

        public int getItemMarketPrice() {
            return itemMarketPrice;
        }

        public boolean isItemPromote() {
            return itemPromote;
        }

        public String getShopName() {
            return shopName;
        }

        public long getShopId() {
            return shopId;
        }

        public String getItemImg() {
            return itemImg;
        }

        public String getName() {
            return name;
        }

        public String getExpertWorkingHours() {
            return expertWorkingHours;
        }

        public String getItemThumbImg() {
            return itemThumbImg;
        }

        public int getCollectionFlag() {
            return collectionFlag;
        }

        public int getItemSaleNum() {
            return itemSaleNum;
        }

        public List<ShopCertificateEntity> getShopCertificate() {
            return shopCertificate;
        }

        public List<?> getComment() {
            return comment;
        }

    }
}
