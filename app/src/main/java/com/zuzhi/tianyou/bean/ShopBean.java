package com.zuzhi.tianyou.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/14.
 */
public class ShopBean {

    /**
     * models : null
     * message : 查询成功
     * errorMessage :
     * pageCount : 0
     * nextPageNo : 0
     * pageStr :
     * totalCount : 0
     * pageNo : 1
     * value : {"remark":"本事务所。。。。。。。。","gzsd":4,"orderNum":0,"feedbackRate":"98%","wczl":5,"fwtd":3,"id":1457669285466002,"shopCertificate":[{"url":"/shop/2016/3/11/ec2ba76c583547f2981ef7c32c4d5469.JPG","name":"等级证书"},{"url":"/shop/2016/3/11/s_cf585ea8c50e4139b43333b61cb1793e.JPG","name":"入围证书"},{"url":"/shop/2016/3/11/8ce3bc0793494f5d9acef23b4c9ad509.JPG","name":"营业执照"}],"itemList":[{"itemShopPrice":1000,"itemPromoteEndDate":null,"itemPromoteStartDate":null,"expertId":1457677461009000,"expertName":"张律师","id":1457677461009001,"itemPromotePrice":900,"itemMarketPrice":null,"itemPromote":false,"shopName":"永大律师事务所","shopId":1457669285466002,"itemImg":"/item/2016/3/11/c7a26c53878a465db29e626ef21ab81a.JPG","name":"税务相关书籍","expertWorkingHours":"10","itemThumbImg":"/item/2016/3/11/s_c7a26c53878a465db29e626ef21ab81a.JPG"},{"itemShopPrice":111,"itemPromoteEndDate":null,"itemPromoteStartDate":null,"expertId":1457677461009002,"expertName":"赵工程师","id":1457939402098000,"itemPromotePrice":111,"itemMarketPrice":null,"itemPromote":false,"shopName":"永大律师事务所","shopId":1457669285466002,"itemImg":null,"name":"shadowYU","expertWorkingHours":"15","itemThumbImg":null}],"name":"永大律师事务所","year":"","expertList":[{"myd":"98%","orderNum":0,"scly":[],"workingHours":"10年","pj":"4.9","name":"shadowYU","seId":1457939402098002},{"myd":"98%","orderNum":0,"scly":[],"workingHours":"10年","pj":"4.9","name":"shadowYU","seId":1457939402098001},{"myd":"98%","orderNum":0,"scly":[],"workingHours":"15","pj":"4.9","name":"赵工程师","seId":1457677461009002},{"myd":"98%","orderNum":0,"scly":[],"workingHours":"10","pj":"4.9","name":"张律师","seId":1457677461009000}],"comScore":"4.9","shopLogo":"/shop/2016/3/11/70110eca8d9240999e39ddd9ebb636d4.JPG"}
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
     * remark : 本事务所。。。。。。。。
     * gzsd : 4
     * orderNum : 0
     * feedbackRate : 98%
     * wczl : 5
     * fwtd : 3
     * id : 1457669285466002
     * shopCertificate : [{"url":"/shop/2016/3/11/ec2ba76c583547f2981ef7c32c4d5469.JPG","name":"等级证书"},{"url":"/shop/2016/3/11/s_cf585ea8c50e4139b43333b61cb1793e.JPG","name":"入围证书"},{"url":"/shop/2016/3/11/8ce3bc0793494f5d9acef23b4c9ad509.JPG","name":"营业执照"}]
     * itemList : [{"itemShopPrice":1000,"itemPromoteEndDate":null,"itemPromoteStartDate":null,"expertId":1457677461009000,"expertName":"张律师","id":1457677461009001,"itemPromotePrice":900,"itemMarketPrice":null,"itemPromote":false,"shopName":"永大律师事务所","shopId":1457669285466002,"itemImg":"/item/2016/3/11/c7a26c53878a465db29e626ef21ab81a.JPG","name":"税务相关书籍","expertWorkingHours":"10","itemThumbImg":"/item/2016/3/11/s_c7a26c53878a465db29e626ef21ab81a.JPG"},{"itemShopPrice":111,"itemPromoteEndDate":null,"itemPromoteStartDate":null,"expertId":1457677461009002,"expertName":"赵工程师","id":1457939402098000,"itemPromotePrice":111,"itemMarketPrice":null,"itemPromote":false,"shopName":"永大律师事务所","shopId":1457669285466002,"itemImg":null,"name":"shadowYU","expertWorkingHours":"15","itemThumbImg":null}]
     * name : 永大律师事务所
     * year :
     * expertList : [{"myd":"98%","orderNum":0,"scly":[],"workingHours":"10年","pj":"4.9","name":"shadowYU","seId":1457939402098002},{"myd":"98%","orderNum":0,"scly":[],"workingHours":"10年","pj":"4.9","name":"shadowYU","seId":1457939402098001},{"myd":"98%","orderNum":0,"scly":[],"workingHours":"15","pj":"4.9","name":"赵工程师","seId":1457677461009002},{"myd":"98%","orderNum":0,"scly":[],"workingHours":"10","pj":"4.9","name":"张律师","seId":1457677461009000}]
     * comScore : 4.9
     * shopLogo : /shop/2016/3/11/70110eca8d9240999e39ddd9ebb636d4.JPG
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
        private String remark;
        private int gzsd;
        private int orderNum;
        private String feedbackRate;
        private int wczl;
        private int fwtd;
        private long id;
        private String name;
        private String year;
        private String comScore;
        private String shopLogo;
        /**
         * url : /shop/2016/3/11/ec2ba76c583547f2981ef7c32c4d5469.JPG
         * name : 等级证书
         */

        private List<ShopCertificateEntity> shopCertificate;
        /**
         * itemShopPrice : 1000
         * itemPromoteEndDate : null
         * itemPromoteStartDate : null
         * expertId : 1457677461009000
         * expertName : 张律师
         * id : 1457677461009001
         * itemPromotePrice : 900
         * itemMarketPrice : null
         * itemPromote : false
         * shopName : 永大律师事务所
         * shopId : 1457669285466002
         * itemImg : /item/2016/3/11/c7a26c53878a465db29e626ef21ab81a.JPG
         * name : 税务相关书籍
         * expertWorkingHours : 10
         * itemThumbImg : /item/2016/3/11/s_c7a26c53878a465db29e626ef21ab81a.JPG
         */

        private List<ItemListEntity> itemList;
        /**
         * myd : 98%
         * orderNum : 0
         * scly : []
         * workingHours : 10年
         * pj : 4.9
         * name : shadowYU
         * seId : 1457939402098002
         */

        private List<ExpertListEntity> expertList;

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setGzsd(int gzsd) {
            this.gzsd = gzsd;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public void setFeedbackRate(String feedbackRate) {
            this.feedbackRate = feedbackRate;
        }

        public void setWczl(int wczl) {
            this.wczl = wczl;
        }

        public void setFwtd(int fwtd) {
            this.fwtd = fwtd;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public void setComScore(String comScore) {
            this.comScore = comScore;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }

        public void setShopCertificate(List<ShopCertificateEntity> shopCertificate) {
            this.shopCertificate = shopCertificate;
        }

        public void setItemList(List<ItemListEntity> itemList) {
            this.itemList = itemList;
        }

        public void setExpertList(List<ExpertListEntity> expertList) {
            this.expertList = expertList;
        }

        public String getRemark() {
            return remark;
        }

        public int getGzsd() {
            return gzsd;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public String getFeedbackRate() {
            return feedbackRate;
        }

        public int getWczl() {
            return wczl;
        }

        public int getFwtd() {
            return fwtd;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getYear() {
            return year;
        }

        public String getComScore() {
            return comScore;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public List<ShopCertificateEntity> getShopCertificate() {
            return shopCertificate;
        }

        public List<ItemListEntity> getItemList() {
            return itemList;
        }

        public List<ExpertListEntity> getExpertList() {
            return expertList;
        }

        public static class ShopCertificateEntity {
            private String url;
            private String name;

            public void setUrl(String url) {
                this.url = url;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public String getName() {
                return name;
            }
        }

        public static class ItemListEntity {
            private int itemShopPrice;
            private Object itemPromoteEndDate;
            private Object itemPromoteStartDate;
            private long expertId;
            private String expertName;
            private long id;
            private int itemPromotePrice;
            private Object itemMarketPrice;
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

            public void setItemMarketPrice(Object itemMarketPrice) {
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

            public Object getItemMarketPrice() {
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

        public static class ExpertListEntity {
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
    }
}
