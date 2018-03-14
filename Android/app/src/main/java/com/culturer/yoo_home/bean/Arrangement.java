package com.culturer.yoo_home.bean;

import com.culturer.yoo_home.database.TArrangement;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class Arrangement {

    /**
     * Id : 7
     * UserId : 12
     * Desc : Hello Yoo+ ! Hellow 宋志文 !
     * CreateTime : 2017-12-28T00:28:16+08:00
     */

    private Long Id;
    private Long UserId;
    private String Desc;
    private String CreateTime;

    public Arrangement(Long id, Long userId, String desc, String createTime) {
        Id = id;
        UserId = userId;
        Desc = desc;
        CreateTime = createTime;
    }

    public Arrangement() {
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

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public TArrangement str2TArrangement(){
        return new TArrangement(Id,UserId,Desc,CreateTime);
    }

    @Override
    public String toString() {
        return "Arrangement{" +
                "Id=" + Id +
                ", UserId=" + UserId +
                ", Desc='" + Desc + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}
