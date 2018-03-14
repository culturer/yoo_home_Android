package com.culturer.yoo_home.bean;

/**
 * Created by Administrator on 2018/2/11 0011.
 */

public class Comment {
    private Long id;
    private Long articleId;
    private Long commentId;
    private Long userId;
    private String desc;
    private String createTime;

    public Comment(Long id, Long articleId, Long commentId, Long userId, String desc, String createTime) {
        this.id = id;
        this.articleId = articleId;
        this.commentId = commentId;
        this.userId = userId;
        this.desc = desc;
        this.createTime = createTime;
    }

    public Comment(Long articleId, Long commentId, Long userId, String desc, String createTime) {
        this.articleId = articleId;
        this.commentId = commentId;
        this.userId = userId;
        this.desc = desc;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
