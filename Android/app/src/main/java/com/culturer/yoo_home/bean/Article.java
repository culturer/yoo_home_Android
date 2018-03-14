package com.culturer.yoo_home.bean;

/**
 * Created by Administrator on 2018/2/11 0011.
 */

public class Article {
    private Long id;
    private Long userId;
    private Long addressId;
    private String createTime;
    private String desc;

    public Article(Long id, Long userId, Long addressId, String createTime, String desc) {
        this.id = id;
        this.userId = userId;
        this.addressId = addressId;
        this.createTime = createTime;
        this.desc = desc;
    }

    public Article(Long userId, Long addressId, String createTime, String desc) {
        this.userId = userId;
        this.addressId = addressId;
        this.createTime = createTime;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
