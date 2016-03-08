package com.zuzhi.tianyou.entity;

/**
 * Created by 李超 on 2016/03/07.
 */
public class UserEntity {


    /**
     * id : 1457407576021000
     * phone : null
     * headImg : http://wx.qlogo.cn/mmopen/7Xp96QiaBrG0PSOib5CmlrkOMGL6verr39GAc3oAyZ9jQbocibW4AMqcNrr4CvcuaH6UkOb9Mvvf4ZibLlT4icu8tZg/0
     * name : 卐
     * hasPhone : 0
     * workId : null
     * companyName : null
     * type : 1
     */

    private long id;
    private String phone;
    private String headImg;
    private String name;
    private String hasPhone;
    private long workId;
    private String companyName;
    private int type;

    public void setId(long id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHasPhone(String hasPhone) {
        this.hasPhone = hasPhone;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getHeadImg() {
        return headImg;
    }

    public String getName() {
        return name;
    }

    public String getHasPhone() {
        return hasPhone;
    }

    public long getWorkId() {
        return workId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getType() {
        return type;
    }
}
