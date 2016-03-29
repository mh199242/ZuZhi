package com.zuzhi.tianyou.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/29.
 */
public class WXPayChargeBean {


    /**
     * models : null
     * message : 返回成功
     * errorMessage :
     * pageCount : 0
     * nextPageNo : 0
     * pageStr :
     * totalCount : 0
     * pageNo : 1
     * value : {"body":"1","subject":"1","failureMsg":"","timeSettle":0,"object":"charge","currency":"cny","id":"ch_anjLCCrTO008mrj1qLOi5uP8","amount":100,"paid":false,"refunded":false,"created":1459231996,"description":"","amountRefunded":0,"livemode":true,"failureCode":"","amountSettle":100,"metadata":{},"app":"app_b1G88K9Wnj5GmLO0","extra":{},"credential":{"wx":{"sign":"62E6278992F51596E20AA546938EC9C2","packageValue":"Sign=WXPay","partnerId":"1316868201","nonceStr":"5ef4f03b74662d3a43c24c6c12ce9819","appId":"wx9a7ee3b009a54abe","timeStamp":"1459231996","prepayId":"wx2016032914131652488174ed0433008045"},"object":"credential"},"orderNo":"1459137256747008","timeExpire":1459239196,"refunds":{"hasMore":false,"data":[],"URL":"/v1/charges/ch_anjLCCrTO008mrj1qLOi5uP8/refunds","object":"list"},"timePaid":0,"clientIp":"127.0.0.1","transactionNo":"","channel":"wx"}
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
     * body : 1
     * subject : 1
     * failureMsg :
     * timeSettle : 0
     * object : charge
     * currency : cny
     * id : ch_anjLCCrTO008mrj1qLOi5uP8
     * amount : 100
     * paid : false
     * refunded : false
     * created : 1459231996
     * description :
     * amountRefunded : 0
     * livemode : true
     * failureCode :
     * amountSettle : 100
     * metadata : {}
     * app : app_b1G88K9Wnj5GmLO0
     * extra : {}
     * credential : {"wx":{"sign":"62E6278992F51596E20AA546938EC9C2","packageValue":"Sign=WXPay","partnerId":"1316868201","nonceStr":"5ef4f03b74662d3a43c24c6c12ce9819","appId":"wx9a7ee3b009a54abe","timeStamp":"1459231996","prepayId":"wx2016032914131652488174ed0433008045"},"object":"credential"}
     * orderNo : 1459137256747008
     * timeExpire : 1459239196
     * refunds : {"hasMore":false,"data":[],"URL":"/v1/charges/ch_anjLCCrTO008mrj1qLOi5uP8/refunds","object":"list"}
     * timePaid : 0
     * clientIp : 127.0.0.1
     * transactionNo :
     * channel : wx
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
        private String body;
        private String subject;
        private String failureMsg;
        private int timeSettle;
        private String object;
        private String currency;
        private String id;
        private int amount;
        private boolean paid;
        private boolean refunded;
        private int created;
        private String description;
        private int amountRefunded;
        private boolean livemode;
        private String failureCode;
        private int amountSettle;
        private MetadataEntity metadata;
        private String app;
        private ExtraEntity extra;
        /**
         * wx : {"sign":"62E6278992F51596E20AA546938EC9C2","packageValue":"Sign=WXPay","partnerId":"1316868201","nonceStr":"5ef4f03b74662d3a43c24c6c12ce9819","appId":"wx9a7ee3b009a54abe","timeStamp":"1459231996","prepayId":"wx2016032914131652488174ed0433008045"}
         * object : credential
         */

        private CredentialEntity credential;
        private String orderNo;
        private int timeExpire;
        /**
         * hasMore : false
         * data : []
         * URL : /v1/charges/ch_anjLCCrTO008mrj1qLOi5uP8/refunds
         * object : list
         */

        private RefundsEntity refunds;
        private int timePaid;
        private String clientIp;
        private String transactionNo;
        private String channel;

        public void setBody(String body) {
            this.body = body;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void setFailureMsg(String failureMsg) {
            this.failureMsg = failureMsg;
        }

        public void setTimeSettle(int timeSettle) {
            this.timeSettle = timeSettle;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
        }

        public void setRefunded(boolean refunded) {
            this.refunded = refunded;
        }

        public void setCreated(int created) {
            this.created = created;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setAmountRefunded(int amountRefunded) {
            this.amountRefunded = amountRefunded;
        }

        public void setLivemode(boolean livemode) {
            this.livemode = livemode;
        }

        public void setFailureCode(String failureCode) {
            this.failureCode = failureCode;
        }

        public void setAmountSettle(int amountSettle) {
            this.amountSettle = amountSettle;
        }

        public void setMetadata(MetadataEntity metadata) {
            this.metadata = metadata;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public void setExtra(ExtraEntity extra) {
            this.extra = extra;
        }

        public void setCredential(CredentialEntity credential) {
            this.credential = credential;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public void setTimeExpire(int timeExpire) {
            this.timeExpire = timeExpire;
        }

        public void setRefunds(RefundsEntity refunds) {
            this.refunds = refunds;
        }

        public void setTimePaid(int timePaid) {
            this.timePaid = timePaid;
        }

        public void setClientIp(String clientIp) {
            this.clientIp = clientIp;
        }

        public void setTransactionNo(String transactionNo) {
            this.transactionNo = transactionNo;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getBody() {
            return body;
        }

        public String getSubject() {
            return subject;
        }

        public String getFailureMsg() {
            return failureMsg;
        }

        public int getTimeSettle() {
            return timeSettle;
        }

        public String getObject() {
            return object;
        }

        public String getCurrency() {
            return currency;
        }

        public String getId() {
            return id;
        }

        public int getAmount() {
            return amount;
        }

        public boolean isPaid() {
            return paid;
        }

        public boolean isRefunded() {
            return refunded;
        }

        public int getCreated() {
            return created;
        }

        public String getDescription() {
            return description;
        }

        public int getAmountRefunded() {
            return amountRefunded;
        }

        public boolean isLivemode() {
            return livemode;
        }

        public String getFailureCode() {
            return failureCode;
        }

        public int getAmountSettle() {
            return amountSettle;
        }

        public MetadataEntity getMetadata() {
            return metadata;
        }

        public String getApp() {
            return app;
        }

        public ExtraEntity getExtra() {
            return extra;
        }

        public CredentialEntity getCredential() {
            return credential;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public int getTimeExpire() {
            return timeExpire;
        }

        public RefundsEntity getRefunds() {
            return refunds;
        }

        public int getTimePaid() {
            return timePaid;
        }

        public String getClientIp() {
            return clientIp;
        }

        public String getTransactionNo() {
            return transactionNo;
        }

        public String getChannel() {
            return channel;
        }

        public static class MetadataEntity {
        }

        public static class ExtraEntity {
        }

        public static class CredentialEntity {
            /**
             * sign : 62E6278992F51596E20AA546938EC9C2
             * packageValue : Sign=WXPay
             * partnerId : 1316868201
             * nonceStr : 5ef4f03b74662d3a43c24c6c12ce9819
             * appId : wx9a7ee3b009a54abe
             * timeStamp : 1459231996
             * prepayId : wx2016032914131652488174ed0433008045
             */

            private WxEntity wx;
            private String object;

            public void setWx(WxEntity wx) {
                this.wx = wx;
            }

            public void setObject(String object) {
                this.object = object;
            }

            public WxEntity getWx() {
                return wx;
            }

            public String getObject() {
                return object;
            }

            public static class WxEntity {
                private String sign;
                private String packageValue;
                private String partnerId;
                private String nonceStr;
                private String appId;
                private String timeStamp;
                private String prepayId;

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public void setPackageValue(String packageValue) {
                    this.packageValue = packageValue;
                }

                public void setPartnerId(String partnerId) {
                    this.partnerId = partnerId;
                }

                public void setNonceStr(String nonceStr) {
                    this.nonceStr = nonceStr;
                }

                public void setAppId(String appId) {
                    this.appId = appId;
                }

                public void setTimeStamp(String timeStamp) {
                    this.timeStamp = timeStamp;
                }

                public void setPrepayId(String prepayId) {
                    this.prepayId = prepayId;
                }

                public String getSign() {
                    return sign;
                }

                public String getPackageValue() {
                    return packageValue;
                }

                public String getPartnerId() {
                    return partnerId;
                }

                public String getNonceStr() {
                    return nonceStr;
                }

                public String getAppId() {
                    return appId;
                }

                public String getTimeStamp() {
                    return timeStamp;
                }

                public String getPrepayId() {
                    return prepayId;
                }
            }
        }

        public static class RefundsEntity {
            private boolean hasMore;
            private String URL;
            private String object;
            private List<?> data;

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public void setURL(String URL) {
                this.URL = URL;
            }

            public void setObject(String object) {
                this.object = object;
            }

            public void setData(List<?> data) {
                this.data = data;
            }

            public boolean isHasMore() {
                return hasMore;
            }

            public String getURL() {
                return URL;
            }

            public String getObject() {
                return object;
            }

            public List<?> getData() {
                return data;
            }
        }
    }
}
