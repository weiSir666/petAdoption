package dao;

import common.DBConnection;
import pojo.Comment;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 评论管理Dao层
 */
public class CommentManagementDao {
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;
    private Connection con = null;
    private Statement statement = null;

    /**
     * 获取所有评论
     *
     * @return ArrayList集合
     */
    public ArrayList<Comment> getAllCommentInformation() {
        ArrayList<Comment> list = new ArrayList<>();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM t_comment JOIN t_user ON t_user.user_id = t_comment.user_id JOIN t_pet ON t_comment.pet_id = t_pet.pet_id;";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                // 封装数据
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setCommentTime(rs.getDate("comment_time"));
                comment.setContent(rs.getString("content"));
                comment.setUserName(rs.getString("uname"));
                comment.setPetName(rs.getString("pet_type"));
                list.add(comment);
            }
        } catch (Exception err) {
            System.out.println("获取所有评论信息失败");
            err.printStackTrace();
        } finally {
            try {
                //关闭数据库
                rs.close();
                pStmt.close();
                DBConnection.closeConnection();
            } catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("获取所有评论信息失败：数据库关闭错误");
            }
        }
        return list;
    }

    /**
     * 统计评论个数
     *
     * @return count（总评论数）
     */
    public int getAllComment() {
        int count = 0;
        String sql = "select COUNT(*) total from t_comment;";
        con = DBConnection.getConnection();
        try {
            Statement statement = con.createStatement();
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (Exception err) {
            err.printStackTrace();
            System.out.println("统计总评论数");
        } finally {
            try {
                //关闭数据库
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    DBConnection.closeConnection();
                }
            } catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("统计总人数：数据库关闭错误");
            }
        }
        return count;
    }

    /**
     * 通过页码数查询数据
     *
     * @param offset          [偏移量 = （页码-1）*每页展示数据]
     * @param commentPageSize (每页展示数据)
     * @return
     */
    public ArrayList<Comment> getCommentInformationByPage(int offset, int commentPageSize) {
        ArrayList<Comment> list = new ArrayList<>();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM t_comment JOIN t_user ON t_user.user_id = t_comment.user_id JOIN t_pet ON t_comment.pet_id = t_pet.pet_id limit ?,?;";
            pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, offset);
            pStmt.setInt(2, commentPageSize);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                // 封装数据
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setCommentTime(rs.getDate("comment_time"));
                comment.setContent(rs.getString("content"));
                comment.setUserName(rs.getString("uname"));
                comment.setPetName(rs.getString("pet_type"));
                list.add(comment);
            }
        } catch (Exception err) {
            System.out.println("通过页码获取评论信息失败");
            err.printStackTrace();
        } finally {
            try {
                //关闭数据库
                rs.close();
                pStmt.close();
                DBConnection.closeConnection();
            } catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("通过页码获取评论信息失败：数据库关闭错误");
            }
        }

        return list;
    }

    /**
     * 通过评论Id删除评论信息
     * @param commentId
     * @return count(受影响的行数)
     */
    public int deleteCommentByCommentId(int commentId) {
        int count = 0;
        con = DBConnection.getConnection();
        String sql1 = "delete from t_comment where comment_id=?;";
        try {
            PreparedStatement pStmt1 = con.prepareStatement(sql1);
            pStmt1.setInt(1,commentId);
            count = pStmt1.executeUpdate();
        }catch (Exception err) {
            System.out.println("通过commentId删除评论信息失败");
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("通过commentId删除评论信息失败：数据库关闭错误");
            }
        }

        return count;
    }
}
