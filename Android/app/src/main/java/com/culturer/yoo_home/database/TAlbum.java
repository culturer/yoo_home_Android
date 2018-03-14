package com.culturer.yoo_home.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by Administrator on 2018/1/2 0002.
 */

@Entity
public class TAlbum {
    @Id
    private Long id;
    private Long userId;
    private Long familyId;
    private String name;
    private String icon;
    private String createTime;
    @Generated(hash = 1223709936)
    public TAlbum(Long id, Long userId, Long familyId, String name, String icon,
            String createTime) {
        this.id = id;
        this.userId = userId;
        this.familyId = familyId;
        this.name = name;
        this.icon = icon;
        this.createTime = createTime;
    }
    @Generated(hash = 462348040)
    public TAlbum() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getFamilyId() {
        return this.familyId;
    }
    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TAlbum{" +
                "id=" + id +
                ", userId=" + userId +
                ", familyId=" + familyId +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
