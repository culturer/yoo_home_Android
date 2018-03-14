package com.culturer.yoo_home.bean;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class User {

    /**
     * Id : 12
     * Username : test8
     * Password : 123456
     * RealName : 宋志文
     * Sex : false
     * Uid : 429004199508105157
     * Birth : 2017-12-27T16:28:16+08:00
     * Tel : 18588263531
     * Email : 78901214@qq.com
     * Icon :
     * NMsg :
     * RelationId : 0
     * FamilyId : 6
     * FamilyName : YooHome~
     * CreatedTime : 2017-12-27T16:28:16+08:00
     * LoginTime : 2017-12-27T16:28:16+08:00
     * Msg :
     * Permission : 0
     */

    private int Id;
    private String Username;
    private String Password;
    private String RealName;
    private boolean Sex;
    private String Uid;
    private String Birth;
    private String Tel;
    private String Email;
    private String Icon;
    private String NMsg;
    private Long RelationId;
    private Long FamilyId;
    private String FamilyName;
    private String CreatedTime;
    private String LoginTime;
    private String Msg;
    private Long Permission;

    public User(int id, String username, String password, String realName, boolean sex, String uid, String birth, String tel, String email, String icon, String NMsg, Long relationId, Long familyId, String familyName, String createdTime, String loginTime, String msg, Long permission) {
        Id = id;
        Username = username;
        Password = password;
        RealName = realName;
        Sex = sex;
        Uid = uid;
        Birth = birth;
        Tel = tel;
        Email = email;
        Icon = icon;
        this.NMsg = NMsg;
        RelationId = relationId;
        FamilyId = familyId;
        FamilyName = familyName;
        CreatedTime = createdTime;
        LoginTime = loginTime;
        Msg = msg;
        Permission = permission;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public boolean isSex() {
        return Sex;
    }

    public void setSex(boolean sex) {
        Sex = sex;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String birth) {
        Birth = birth;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getNMsg() {
        return NMsg;
    }

    public void setNMsg(String NMsg) {
        this.NMsg = NMsg;
    }

    public Long getRelationId() {
        return RelationId;
    }

    public void setRelationId(Long relationId) {
        RelationId = relationId;
    }

    public Long getFamilyId() {
        return FamilyId;
    }

    public void setFamilyId(Long familyId) {
        FamilyId = familyId;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        FamilyName = familyName;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        CreatedTime = createdTime;
    }

    public String getLoginTime() {
        return LoginTime;
    }

    public void setLoginTime(String loginTime) {
        LoginTime = loginTime;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public Long getPermission() {
        return Permission;
    }

    public void setPermission(Long permission) {
        Permission = permission;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", RealName='" + RealName + '\'' +
                ", Sex=" + Sex +
                ", Uid='" + Uid + '\'' +
                ", Birth='" + Birth + '\'' +
                ", Tel='" + Tel + '\'' +
                ", Email='" + Email + '\'' +
                ", Icon='" + Icon + '\'' +
                ", NMsg='" + NMsg + '\'' +
                ", RelationId=" + RelationId +
                ", FamilyId=" + FamilyId +
                ", FamilyName='" + FamilyName + '\'' +
                ", CreatedTime='" + CreatedTime + '\'' +
                ", LoginTime='" + LoginTime + '\'' +
                ", Msg='" + Msg + '\'' +
                ", Permission=" + Permission +
                '}';
    }
}
