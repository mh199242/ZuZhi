package com.zuzhi.tianyou.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/29.
 */
public class AliPayChargeBean {


    /**
     * models : null
     * message : 返回成功
     * errorMessage :
     * pageCount : 0
     * nextPageNo : 0
     * pageStr :
     * totalCount : 0
     * pageNo : 1
     * value : {"body":"1","subject":"1","failureMsg":"","timeSettle":0,"object":"charge","currency":"cny","id":"ch_K8C0iLq5qn10aLinPGSWrrTK","amount":100,"paid":false,"refunded":false,"created":1459233839,"description":"","amountRefunded":0,"livemode":true,"failureCode":"","amountSettle":100,"metadata":{},"app":"app_b1G88K9Wnj5GmLO0","extra":{},"credential":{"alipay":{"orderInfo":"service=\"mobile.securitypay.pay\"&_input_charset=\"utf-8\"&notify_url=\"https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_K8C0iLq5qn10aLinPGSWrrTK\"&partner=\"2088221321146808\"&out_trade_no=\"1459137256747008\"&subject=\"1\"&body=\"1\"&total_fee=\"1.00\"&payment_type=\"1\"&seller_id=\"2088221321146808\"&it_b_pay=\"2016-03-30 14:43:59\"&sign=\"YcUQnzl%2BSQSI%2BBH3lj6zJOPm3bVjid%2FKYI%2BHszItj7dRTe%2BwuutBnmiLvFWXko%2BlWJdUDRZ0AUHw8pg4FW1akKPCodIgV37a5fJ4I5TRo8A583aZfF%2F38RBOI%2FVPzVVXD70jKqsU5rPBp0vxz3r25UqS7mD6EORSSI8H1VRdF%2Bs%3D\"&sign_type=\"RSA\""},"object":"credential"},"orderNo":"1459137256747008","timeExpire":1459320239,"refunds":{"hasMore":false,"data":[],"URL":"/v1/charges/ch_K8C0iLq5qn10aLinPGSWrrTK/refunds","object":"list"},"timePaid":0,"clientIp":"127.0.0.1","transactionNo":"","channel":"alipay"}
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
     * id : ch_K8C0iLq5qn10aLinPGSWrrTK
     * amount : 100
     * paid : false
     * refunded : false
     * created : 1459233839
     * description :
     * amountRefunded : 0
     * livemode : true
     * failureCode :
     * amountSettle : 100
     * metadata : {}
     * app : app_b1G88K9Wnj5GmLO0
     * extra : {}
     * credential : {"alipay":{"orderInfo":"service=\"mobile.securitypay.pay\"&_input_charset=\"utf-8\"&notify_url=\"https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_K8C0iLq5qn10aLinPGSWrrTK\"&partner=\"2088221321146808\"&out_trade_no=\"1459137256747008\"&subject=\"1\"&body=\"1\"&total_fee=\"1.00\"&payment_type=\"1\"&seller_id=\"2088221321146808\"&it_b_pay=\"2016-03-30 14:43:59\"&sign=\"YcUQnzl%2BSQSI%2BBH3lj6zJOPm3bVjid%2FKYI%2BHszItj7dRTe%2BwuutBnmiLvFWXko%2BlWJdUDRZ0AUHw8pg4FW1akKPCodIgV37a5fJ4I5TRo8A583aZfF%2F38RBOI%2FVPzVVXD70jKqsU5rPBp0vxz3r25UqS7mD6EORSSI8H1VRdF%2Bs%3D\"&sign_type=\"RSA\""},"object":"credential"}
     * orderNo : 1459137256747008
     * timeExpire : 1459320239
     * refunds : {"hasMore":false,"data":[],"URL":"/v1/charges/ch_K8C0iLq5qn10aLinPGSWrrTK/refunds","object":"list"}
     * timePaid : 0
     * clientIp : 127.0.0.1
     * transactionNo :
     * channel : alipay
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
         * alipay : {"orderInfo":"service=\"mobile.securitypay.pay\"&_input_charset=\"utf-8\"&notify_url=\"https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_K8C0iLq5qn10aLinPGSWrrTK\"&partner=\"2088221321146808\"&out_trade_no=\"1459137256747008\"&subject=\"1\"&body=\"1\"&total_fee=\"1.00\"&payment_type=\"1\"&seller_id=\"2088221321146808\"&it_b_pay=\"2016-03-30 14:43:59\"&sign=\"YcUQnzl%2BSQSI%2BBH3lj6zJOPm3bVjid%2FKYI%2BHszItj7dRTe%2BwuutBnmiLvFWXko%2BlWJdUDRZ0AUHw8pg4FW1akKPCodIgV37a5fJ4I5TRo8A583aZfF%2F38RBOI%2FVPzVVXD70jKqsU5rPBp0vxz3r25UqS7mD6EORSSI8H1VRdF%2Bs%3D\"&sign_type=\"RSA\""}
         * object : credential
         */

        private CredentialEntity credential;
        private String orderNo;
        private int timeExpire;
        /**
         * hasMore : false
         * data : []
         * URL : /v1/charges/ch_K8C0iLq5qn10aLinPGSWrrTK/refunds
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
             * orderInfo : service="mobile.securitypay.pay"&_input_charset="utf-8"&notify_url="https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_K8C0iLq5qn10aLinPGSWrrTK"&partner="2088221321146808"&out_trade_no="1459137256747008"&subject="1"&body="1"&total_fee="1.00"&payment_type="1"&seller_id="2088221321146808"&it_b_pay="2016-03-30 14:43:59"&sign="YcUQnzl%2BSQSI%2BBH3lj6zJOPm3bVjid%2FKYI%2BHszItj7dRTe%2BwuutBnmiLvFWXko%2BlWJdUDRZ0AUHw8pg4FW1akKPCodIgV37a5fJ4I5TRo8A583aZfF%2F38RBOI%2FVPzVVXD70jKqsU5rPBp0vxz3r25UqS7mD6EORSSI8H1VRdF%2Bs%3D"&sign_type="RSA"
             */

            private AlipayEntity alipay;
            private String object;

            public void setAlipay(AlipayEntity alipay) {
                this.alipay = alipay;
            }

            public void setObject(String object) {
                this.object = object;
            }

            public AlipayEntity getAlipay() {
                return alipay;
            }

            public String getObject() {
                return object;
            }

            public static class AlipayEntity {
                private String orderInfo;

                public void setOrderInfo(String orderInfo) {
                    this.orderInfo = orderInfo;
                }

                public String getOrderInfo() {
                    return orderInfo;
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
