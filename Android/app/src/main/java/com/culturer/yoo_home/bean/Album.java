package com.culturer.yoo_home.bean;

import com.culturer.yoo_home.database.TAlbum;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class Album {

    /**
     * Id : 4
     * UserId : 12
     * FamilyId : 0
     * Name : 是的发
     * Icon :
     * CreateTime : 2017-12-30T14:32:01+08:00
     */

    private Long Id;
    private Long UserId;
    private Long FamilyId;
    private String Name;
    private String Icon;
    private String CreateTime;

    public Album() {
    }

    public Album(Long id, Long userId, Long familyId, String name, String icon, String createTime) {
        Id = id;
        UserId = userId;
        FamilyId = familyId;
        Name = name;
        Icon = icon;
        CreateTime = createTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getFamilyId() {
        return FamilyId;
    }

    public void setFamilyId(Long familyId) {
        FamilyId = familyId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public TAlbum str2TAlbum(){
        return new TAlbum(Id,UserId,FamilyId,Name,Icon,CreateTime);
    }

    @Override
    public String toString() {
        return "Album{" +
                "Id=" + Id +
                ", UserId=" + UserId +
                ", FamilyId=" + FamilyId +
                ", Name='" + Name + '\'' +
                ", Icon='" + Icon + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}
