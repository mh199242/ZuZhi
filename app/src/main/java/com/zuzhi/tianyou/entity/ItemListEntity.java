package com.zuzhi.tianyou.entity;

public class ItemListEntity {
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