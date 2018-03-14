package com.culturer.yoo_home.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/2/27 0027.
 */
@Entity
public class TChatMsg {
    @Id
    private Long id;
    private int userId;
    private String username;
    private String userIcon;
    private String msg;
    private String url;
    private String users;
    @Generated(hash = 1587548432)
    public TChatMsg(Long id, int userId, String username, String userIcon,
            String msg, String url, String users) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.userIcon = userIcon;
        this.msg = msg;
        this.url = url;
        this.users = users;
    }
    @Generated(hash = 553444634)
    public TChatMsg() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserIcon() {
        return this.userIcon;
    }
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsers() {
        return this.users;
    }
    public void setUsers(String users) {
        this.users = users;
    }
}
