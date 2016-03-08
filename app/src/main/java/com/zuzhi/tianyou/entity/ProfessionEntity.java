package com.zuzhi.tianyou.entity;

/**
 * Created by 超 on 2016/3/8.
 */
public class ProfessionEntity {

    /**
     * id : 1456312895481002
     * parentId : 1456312895481002
     * code : kjcn
     * name : 会计出纳
     */

    private long id;
    private long parentId;
    private String code;
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public long getParentId() {
        return parentId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
