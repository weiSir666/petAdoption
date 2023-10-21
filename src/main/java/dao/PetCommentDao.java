package dao;

import common.DBConnection;
import pojo.Comment;
import pojo.Comment1;

import java.sql.*;

public class PetCommentDao {
    private Connection con = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;



     public int SubmitComment(Comment1 com) throws SQLException {
         con = DBConnection.getConnection();

         int col = 0;
         try {
             String sql = "INSERT INTO t_comment (comment_time, content, user_id, pet_id) VALUES (?, ?, ?, ?); ";
             pstm = con.prepareStatement(sql);

             pstm.setDate(1, com.getCommentTime());
             pstm.setString(2, com.getContent());
             pstm.setInt(3, com.getUserId());
             pstm.setInt(4, com.getPetId());
             col = pstm.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println("评论插入失败");
         }finally {
             pstm.close();
             DBConnection.closeConnection();
         }

         return col;
     }
}
