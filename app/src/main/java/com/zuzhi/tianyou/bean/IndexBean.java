package com.zuzhi.tianyou.bean;

import com.zuzhi.tianyou.entity.AdEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 超 on 2016/3/11.
 */
public class IndexBean {

    /**
     * models : null
     * message : 查询成功
     * errorMessage :
     * pageCount : 1
     * nextPageNo : 0
     * pageStr :
     * totalCount : 1
     * pageNo : 1
     * value : {"content":[{"cssKey":"tile","sub":[{"imgUrl":"/ad/2016/2/29/f02f6a8ed85442269d4164b692645c69.png","id":1456722825493000,"objType":"category","remark":"测试简介111111","name":"测试数据1","objId":"1454037672918007","keyWord":"","targetType":"itemList"}],"name":"鉴证报告"},{"cssKey":"firstColumn","sub":[],"name":"顾问咨询"},{"cssKey":"tile","sub":[],"name":"上市服务"},{"cssKey":"firstColumn","sub":[],"name":"商务代理"},{"cssKey":"tile","sub":[],"name":"客服测试2"}],"customerService":{"id":"123","phone":"111111111111"},"ad":[{"imgUrl":"/ad/2016/2/29/ed5ac8f1026f44d3bc3a16cd535af078.jpg","id":1456722825493001,"objType":"category","remark":"他","name":"图1","objId":"1454037672918007","keyWord":"","targetType":"shopDetails"},{"imgUrl":"/ad/2016/2/29/85201f7ce5c9412382debf6533628aba.jpg","id":1456722825493002,"objType":"category","remark":"1","name":"图2","objId":"123445","keyWord":"","targetType":"itemList"},{"imgUrl":"/ad/2016/2/29/9acd5b122d3446cbaab56126f644cdf2.jpg","id":1456722825493003,"objType":"category","remark":"3","name":"图3","objId":null,"keyWord":"","targetType":"itemList"}],"category":[{"imgUrl":"/ad/2016/2/29/693a4a9137b5400387561d5f1f732a78.png","id":1456722825493004,"objType":"category","remark":"","name":"类目1","objId":"1454037672918007","keyWord":"","targetType":"itemList"},{"imgUrl":"/ad/2016/2/29/63845521b09848e5ad1df38c67ed635a.png","id":1456722825493005,"objType":"category","remark":"","name":"类目2","objId":"1454037672918008","keyWord":"","targetType":"itemList"},{"imgUrl":"/ad/2016/2/29/9dbbf0dd5c9e4724bee39165c2704e23.png","id":1456722825493006,"objType":"category","remark":"","name":"类目3","objId":"1454037672918009","keyWord":"","targetType":"itemList"}],"hotService":[{"itemShopPrice":66,"itemPromoteEndDate":null,"itemPromoteStartDate":null,"expertId":1455858419213000,"expertName":"专家4","id":1455878226635000,"itemPromotePrice":66,"itemMarketPrice":444,"itemPromote":false,"shopName":"测试事务所","shopId":1455854153489000,"itemImg":"/item/2016/2/19/1ebdeb40e90747fc9af35cbc38544a7d.jpg","name":"6678","expertWorkingHours":"10年","itemThumbImg":"/item/2016/2/19/s_1ebdeb40e90747fc9af35cbc38544a7d.jpg"}]}
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
     * content : [{"cssKey":"tile","sub":[{"imgUrl":"/ad/2016/2/29/f02f6a8ed85442269d4164b692645c69.png","id":1456722825493000,"objType":"category","remark":"测试简介111111","name":"测试数据1","objId":"1454037672918007","keyWord":"","targetType":"itemList"}],"name":"鉴证报告"},{"cssKey":"firstColumn","sub":[],"name":"顾问咨询"},{"cssKey":"tile","sub":[],"name":"上市服务"},{"cssKey":"firstColumn","sub":[],"name":"商务代理"},{"cssKey":"tile","sub":[],"name":"客服测试2"}]
     * customerService : {"id":"123","phone":"111111111111"}
     * ad : [{"imgUrl":"/ad/2016/2/29/ed5ac8f1026f44d3bc3a16cd535af078.jpg","id":1456722825493001,"objType":"category","remark":"他","name":"图1","objId":"1454037672918007","keyWord":"","targetType":"shopDetails"},{"imgUrl":"/ad/2016/2/29/85201f7ce5c9412382debf6533628aba.jpg","id":1456722825493002,"objType":"category","remark":"1","name":"图2","objId":"123445","keyWord":"","targetType":"itemList"},{"imgUrl":"/ad/2016/2/29/9acd5b122d3446cbaab56126f644cdf2.jpg","id":1456722825493003,"objType":"category","remark":"3","name":"图3","objId":null,"keyWord":"","targetType":"itemList"}]
     * category : [{"imgUrl":"/ad/2016/2/29/693a4a9137b5400387561d5f1f732a78.png","id":1456722825493004,"objType":"category","remark":"","name":"类目1","objId":"1454037672918007","keyWord":"","targetType":"itemList"},{"imgUrl":"/ad/2016/2/29/63845521b09848e5ad1df38c67ed635a.png","id":1456722825493005,"objType":"category","remark":"","name":"类目2","objId":"1454037672918008","keyWord":"","targetType":"itemList"},{"imgUrl":"/ad/2016/2/29/9dbbf0dd5c9e4724bee39165c2704e23.png","id":1456722825493006,"objType":"category","remark":"","name":"类目3","objId":"1454037672918009","keyWord":"","targetType":"itemList"}]
     * hotService : [{"itemShopPrice":66,"itemPromoteEndDate":null,"itemPromoteStartDate":null,"expertId":1455858419213000,"expertName":"专家4","id":1455878226635000,"itemPromotePrice":66,"itemMarketPrice":444,"itemPromote":false,"shopName":"测试事务所","shopId":1455854153489000,"itemImg":"/item/2016/2/19/1ebdeb40e90747fc9af35cbc38544a7d.jpg","name":"6678","expertWorkingHours":"10年","itemThumbImg":"/item/2016/2/19/s_1ebdeb40e90747fc9af35cbc38544a7d.jpg"}]
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
        /**
         * id : 123
         * phone : 111111111111
         */

        private CustomerServiceEntity customerService;
        /**
         * cssKey : tile
         * sub : [{"imgUrl":"/ad/2016/2/29/f02f6a8ed85442269d4164b692645c69.png","id":1456722825493000,"objType":"category","remark":"测试简介111111","name":"测试数据1","objId":"1454037672918007","keyWord":"","targetType":"itemList"}]
         * name : 鉴证报告
         */

        private List<ContentEntity> content;
        /**
         * imgUrl : /ad/2016/2/29/ed5ac8f1026f44d3bc3a16cd535af078.jpg
         * id : 1456722825493001
         * objType : category
         * remark : 他
         * name : 图1
         * objId : 1454037672918007
         * keyWord :
         * targetType : shopDetails
         */

        private List<AdEntity> ad;
        /**
         * imgUrl : /ad/2016/2/29/693a4a9137b5400387561d5f1f732a78.png
         * id : 1456722825493004
         * objType : category
         * remark :
         * name : 类目1
         * objId : 1454037672918007
         * keyWord :
         * targetType : itemList
         */

        private List<CategoryEntity> category;
        /**
         * itemShopPrice : 66
         * itemPromoteEndDate : null
         * itemPromoteStartDate : null
         * expertId : 1455858419213000
         * expertName : 专家4
         * id : 1455878226635000
         * itemPromotePrice : 66
         * itemMarketPrice : 444
         * itemPromote : false
         * shopName : 测试事务所
         * shopId : 1455854153489000
         * itemImg : /item/2016/2/19/1ebdeb40e90747fc9af35cbc38544a7d.jpg
         * name : 6678
         * expertWorkingHours : 10年
         * itemThumbImg : /item/2016/2/19/s_1ebdeb40e90747fc9af35cbc38544a7d.jpg
         */

        private List<HotServiceEntity> hotService;

        public void setCustomerService(CustomerServiceEntity customerService) {
            this.customerService = customerService;
        }

        public void setContent(List<ContentEntity> content) {
            this.content = content;
        }

        public void setAd(List<AdEntity> ad) {
            this.ad = ad;
        }

        public void setCategory(List<CategoryEntity> category) {
            this.category = category;
        }

        public void setHotService(List<HotServiceEntity> hotService) {
            this.hotService = hotService;
        }

        public CustomerServiceEntity getCustomerService() {
            return customerService;
        }

        public List<ContentEntity> getContent() {
            return content;
        }

        public List<AdEntity> getAd() {
            return ad;
        }

        public List<CategoryEntity> getCategory() {
            return category;
        }

        public List<HotServiceEntity> getHotService() {
            return hotService;
        }

        public static class CustomerServiceEntity {
            private String id;
            private String phone;

            public void setId(String id) {
                this.id = id;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getId() {
                return id;
            }

            public String getPhone() {
                return phone;
            }
        }

        public static class ContentEntity {
            private String cssKey;
            private String name;
            /**
             * imgUrl : /ad/2016/2/29/f02f6a8ed85442269d4164b692645c69.png
             * id : 1456722825493000
             * objType : category
             * remark : 测试简介111111
             * name : 测试数据1
             * objId : 1454037672918007
             * keyWord :
             * targetType : itemList
             */

            private List<SubEntity> sub;

            public void setCssKey(String cssKey) {
                this.cssKey = cssKey;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setSub(List<SubEntity> sub) {
                this.sub = sub;
            }

            public String getCssKey() {
                return cssKey;
            }

            public String getName() {
                return name;
            }

            public List<SubEntity> getSub() {
                return sub;
            }

            public static class SubEntity implements Serializable{
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
        }

        public static class CategoryEntity implements Serializable {
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

        public static class HotServiceEntity {
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
}
