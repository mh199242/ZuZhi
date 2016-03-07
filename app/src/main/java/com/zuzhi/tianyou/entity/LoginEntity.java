package com.zuzhi.tianyou.entity;

/**
 * Created by 李超 on 2016/03/07.
 */
public class LoginEntity {


    /**
     * id : 用户ID（long）
     * name : 用户名称（string）
     * phone : 电话(string)
     * type : 用户类型(Integer)
     * workId : 职业(long)
     * hasPhone : 是否绑定手机 1已绑定 0未绑定(String)
     * headImg : 头像(String)
     * companyName : 公司名称(String)
     */

    private String id;
    private String name;
    private String phone;
    private String type;
    private String workId;
    private String hasPhone;
    private String headImg;
    private String companyName;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public void setHasPhone(String hasPhone) {
        this.hasPhone = hasPhone;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public String getWorkId() {
        return workId;
    }

    public String getHasPhone() {
        return hasPhone;
    }

    public String getHeadImg() {
        return headImg;
    }

    public String getCompanyName() {
        return companyName;
    }
}
