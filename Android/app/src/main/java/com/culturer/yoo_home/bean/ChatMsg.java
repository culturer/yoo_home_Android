package com.culturer.yoo_home.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/31 0031.
 */

public class ChatMsg {
    /**
     * userId : 2
     * username : song
     * userIcon : 127.0.0.1:8888/sdfdsaf.png
     * msg : 按时下班
     * url : 127.0.0.1:8888/dhgfai/af
     * users : [1,2,3,4,5,6,7,8,9]
     */

    private int userId;
    private String username;
    private String userIcon;
    private String msg;
    private String url;
    private List<Integer> users;

    public ChatMsg(int userId, String username, String userIcon, String msg, String url, List<Integer> users) {
        this.userId = userId;
        this.username = username;
        this.userIcon = userIcon;
        this.msg = msg;
        this.url = url;
        this.users = users;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "ChatMsg{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                ", users=" + users +
                '}';
    }
}
