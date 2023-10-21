package pojo;

import java.sql.Date;


/**
 * 评论实体类1
 */
public class Comment1 {
    private int commentId;
    private int userId;
    private int petId;
    private String content;  //评论内容
    private Date commentTime; //评论时间

    @Override
    public String toString() {
        return "Comment1{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", petId=" + petId +
                ", content='" + content + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
