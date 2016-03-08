package com.zuzhi.tianyou.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/03/07.
 */
public class WXLoginBean implements Serializable {
    /**
     * code:微信授权码
     * openid:微信开放id,
     * access_token:微信token
     * headimgurl:微信头像地址,
     * nickname:微信昵称,
     */
    private String code;
    private String openid;
    private String headimgurl;
    private String nickname;
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}
