package com.zuzhi.tianyou.bean;

import java.util.List;

/**
 * Created by 超 on 2016/3/16.
 */
public class ItemListBean {

    /**
     * models : null
     * message : 查询成功
     * errorMessage :
     * pageCount : 1
     * nextPageNo : 0
     * pageStr :
     * totalCount : 3
     * pageNo : 1
     * value : [{"itemShopPrice":100,"itemPromoteEndDate":null,"itemPromoteStartDate":null,"expertId":1457677461009000,"expertName":"张律师","id":1458025271940007,"itemPromotePrice":90,"itemMarketPrice":null,"itemPromote":false,"shopName":"永大律师事务所","shopId":1457669285466002,"itemImg":"/item/2016/3/15/b15039527a5f4ee7913ff8fa4bba85a3.jpg","name":"测试商品1","expertWorkingHours":"10","itemThumbImg":"/item/2016/3/15/s_b15039527a5f4ee7913ff8fa4bba85a3.jpg"},{"itemShopPrice":111,"itemPromoteEndDate":null,"itemPromoteStartDate":null,"expertId":1457677461009002,"expertName":"赵工程师","id":1457939402098000,"itemPromotePrice":111,"itemMarketPrice":null,"itemPromote":false,"shopName":"永大律师事务所","shopId":1457669285466002,"itemImg":null,"name":"shadowYU","expertWorkingHours":"15","itemThumbImg":null},{"itemShopPrice":1000,"itemPromoteEndDate":null,"itemPromoteStartDate":null,"expertId":1457677461009000,"expertName":"张律师","id":1457677461009001,"itemPromotePrice":900,"itemMarketPrice":null,"itemPromote":false,"shopName":"永大律师事务所","shopId":1457669285466002,"itemImg":"/item/2016/3/11/c7a26c53878a465db29e626ef21ab81a.JPG","name":"税务相关书籍","expertWorkingHours":"10","itemThumbImg":"/item/2016/3/11/s_c7a26c53878a465db29e626ef21ab81a.JPG"}]
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
    /**
     * itemShopPrice : 100
     * itemPromoteEndDate : null
     * itemPromoteStartDate : null
     * expertId : 1457677461009000
     * expertName : 张律师
     * id : 1458025271940007
     * itemPromotePrice : 90
     * itemMarketPrice : null
     * itemPromote : false
     * shopName : 永大律师事务所
     * shopId : 1457669285466002
     * itemImg : /item/2016/3/15/b15039527a5f4ee7913ff8fa4bba85a3.jpg
     * name : 测试商品1
     * expertWorkingHours : 10
     * itemThumbImg : /item/2016/3/15/s_b15039527a5f4ee7913ff8fa4bba85a3.jpg
     */

    private List<ValueEntity> value;

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

    public void setValue(List<ValueEntity> value) {
        this.value = value;
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

    public List<ValueEntity> getValue() {
        return value;
    }

    public static class ValueEntity {
        private int itemShopPrice;
        private Object itemPromoteEndDate;
        private Object itemPromoteStartDate;
        private long expertId;
        private String expertName;
        private long id;
        private int itemPromotePrice;
        private int itemMarketPrice;
        private boolean itemPromote;
        private String shopName;
        private long shopId;
        private String itemImg;
        private String name;
        private String expertWorkingHours;
        private String itemThumbImg;

        public void setItemShopPrice(int itemShopPrice) {
            this.itemShopPrice = itemShopPrice;
        }

        public void setItemPromoteEndDate(Object itemPromoteEndDate) {
            this.itemPromoteEndDate = itemPromoteEndDate;
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

        public void setId(long id) {
            this.id = id;
        }

        public void setItemPromotePrice(int itemPromotePrice) {
            this.itemPromotePrice = itemPromotePrice;
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

        public int getItemShopPrice() {
            return itemShopPrice;
        }

        public Object getItemPromoteEndDate() {
            return itemPromoteEndDate;
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

        public long getId() {
            return id;
        }

        public int getItemPromotePrice() {
            return itemPromotePrice;
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
    }
}
