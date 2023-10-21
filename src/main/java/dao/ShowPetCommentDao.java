package dao;

import common.DBConnection;
import pojo.Comment;
import pojo.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 关于某个宠物的所有评论Dao层
 */
public class ShowPetCommentDao {
    private Connection con = null;
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;

    /**
     * 根据宠物id查询跟该宠物所有相关的评论
     * @param petId
     * @return list
     */
    public ArrayList<Comment> getPetComment(int petId) {
        ArrayList<Comment> list = new ArrayList<>();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM t_pet JOIN t_comment ON t_comment.pet_id = t_pet.pet_id JOIN t_user ON t_comment.user_id = t_user.user_id where t_pet.pet_id=?;";
            pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, petId);
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
            System.out.println("查询宠物有关评论失败");
            err.printStackTrace();
        } finally {
            try {
                //关闭数据库
                rs.close();
                pStmt.close();
                DBConnection.closeConnection();
            } catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("查询宠物有关评论失败：数据库关闭错误");
            }
        }

        return list;
    }
}
