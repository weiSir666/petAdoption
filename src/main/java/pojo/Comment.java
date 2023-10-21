package pojo;

import java.sql.Date;

/**
 * 评论实体类
 */
public class Comment {
    private int commentId;
    private String userName;
    private String petName;
    private String content;  //评论内容
    private Date commentTime; //评论时间

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
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

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userName='" + userName + '\'' +
                ", petName='" + petName + '\'' +
                ", content='" + content + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }
}
