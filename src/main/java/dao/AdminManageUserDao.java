package dao;

import common.DBConnection;

import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 管理员管理用户信息Dao层,增删改查
 */
public class AdminManageUserDao {
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;
    private Connection con = null;
    private Statement statement = null;

    /**
     * 获取所有用户信息
     * @return ArrayList集合
     */
    public ArrayList<User> getAllInformationByName( ) {
        ArrayList<User> list = new ArrayList<User>();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * from t_user;";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                //一条一条的封装数据
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserPassword(rs.getString("password"));
                user.setUserName(rs.getString("uname"));
                user.setRealName(rs.getString("rname"));
                user.setUserAge(rs.getInt("age"));
                user.setUserPhone(rs.getString("telphone"));
                user.setPlace(rs.getString("place"));
                user.setUserSex(rs.getString("sex"));
                list.add(user);
            }
        }catch (Exception err){
            System.out.println("获取所有用户信息失败");
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                rs.close();
                pStmt.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("获取所有用户：数据库关闭错误");
            }
        }
        //System.out.println(list);
        return list;
    }

    /**
     * 统计用户总人数
     * @return count（用户总人数）
     */
    public int getUserCount() {
        int count = 0;
        String sql = "select COUNT(*) total from t_user;";
        con = DBConnection.getConnection();
        try {
            Statement statement = con.createStatement();
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("total");
            }
        }catch (Exception err) {
            err.printStackTrace();
            System.out.println("统计总用户人数");
        }finally {
            try{
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
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("统计总人数：数据库关闭错误");
            }
        }
        return count;
    }

    /**
     * 通过页码数查询数据
     * @param offset [偏移量 = （页码-1）*每页展示数据]
     * @param pageSize (每页展示数据)
     * @return
     */
    public ArrayList<User> getInformationByPage(int offset, int pageSize) {
        ArrayList<User> list = new ArrayList<>();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM t_user LIMIT ?, ?;";
            pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, offset);
            pStmt.setInt(2, pageSize);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                //一条一条的封装数据
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserPassword(rs.getString("password"));
                user.setUserName(rs.getString("uname"));
                user.setRealName(rs.getString("rname"));
                user.setUserAge(rs.getInt("age"));
                user.setUserPhone(rs.getString("telphone"));
                user.setPlace(rs.getString("place"));
                user.setUserSex(rs.getString("sex"));
                list.add(user);
            }
        }catch (Exception err){
            System.out.println("通过页码获取用户数据失败");
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                rs.close();
                pStmt.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("通过页码查询数据：数据库关闭错误");
            }
        }

        return list;
    }

    /**
     * 通过userId查询用户信息
     * @param userId
     * @return User对象
     */
    public User getInformationByUserid(int userId) {
        User user = new User();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM `t_user` WHERE user_id = ?;";
            pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, userId);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setUserPassword(rs.getString("password"));
                user.setUserName(rs.getString("uname"));
                user.setRealName(rs.getString("rname"));
                user.setUserAge(rs.getInt("age"));
                user.setUserPhone(rs.getString("telphone"));
                user.setPlace(rs.getString("place"));
                user.setUserSex(rs.getString("sex"));
            }
        }catch (Exception err){
            System.out.println("通过userId获取用户信息失败");
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                rs.close();
                pStmt.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("通过userId获取用户信息失败：数据库关闭错误");
            }
        }
        return user;
    }

    /**
     * 通过用户id更新用户数据（用户名不可更改）
     * @param userId,userRealName,userPassword,userAge,userPhone,userAddress,userSex
     * @return int(影响的行数)
     */
    public int updateUserInformationByUserId(int userId, String userRealName, String userPassword,
                                              int userAge, String userPhone, String userAddress, String userSex) {
        int count = 0;
        try{
            con = DBConnection.getConnection();
            pStmt = con.prepareStatement("UPDATE t_user set `password`=?,rname=?,age=?,place=?,sex=?,telphone=? where user_id=?;");
            pStmt.setString(1,userPassword);
            pStmt.setString(2,userRealName);
            pStmt.setInt(3,userAge);
            pStmt.setString(4,userAddress);
            pStmt.setString(5,userSex);
            pStmt.setString(6,userPhone);
            pStmt.setInt(7,userId);

            count = pStmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("通过userId更新用户信息失败");
        }finally {
            try{
                //关闭数据库
                pStmt.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("通过userId更新用户信息失败：数据库关闭错误");
            }
        }
        return count;
    }

    /**
     * 通过id删除用户数据（删除跟用户相关的所有数据, 用户表，领养表，评论表全部删除）
     * @param userId
     * @return int（受影响的行数）
     */
    public int deleteUserInformationByUserId(int userId) {
        con = DBConnection.getConnection();
        // 必须一次按顺序执行
        String sql1 = "delete from t_adopt where user_id=?;";
        String sql2 = "delete from t_comment where user_id=?;";
        String sql3 = "delete from t_user where user_id=?;";
        int sum1 = 0, sum2 = 0, sum3 = 0;
        try {
            PreparedStatement pStmt1 = con.prepareStatement(sql1);
            pStmt1.setInt(1,userId);
            sum1 = pStmt1.executeUpdate();

            PreparedStatement pStmt2 = con.prepareStatement(sql2);
            pStmt2.setInt(1,userId);
            sum2 = pStmt2.executeUpdate();

            PreparedStatement pStmt3 = con.prepareStatement(sql3);
            pStmt3.setInt(1,userId);
            sum3 = pStmt3.executeUpdate();

            pStmt1.close();
            pStmt2.close();
            pStmt3.close();
        }catch (Exception err) {
            System.out.println("通过userId删除用户信息失败");
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("通过userId删除用户信息失败：数据库关闭错误");
            }
        }
        return sum1 + sum2 + sum3;
    }

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return User
     */
    public ArrayList<User> findUserInformationByUserName(String userName) {
        ArrayList<User> list = new ArrayList<>();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM `t_user` WHERE uname like ?;";
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, "%" + userName + "%");
            rs = pStmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserPassword(rs.getString("password"));
                user.setUserName(rs.getString("uname"));
                user.setRealName(rs.getString("rname"));
                user.setUserAge(rs.getInt("age"));
                user.setUserPhone(rs.getString("telphone"));
                user.setPlace(rs.getString("place"));
                user.setUserSex(rs.getString("sex"));
                list.add(user);
            }
        }catch (Exception err){
            System.out.println("通过userName获取用户信息失败");
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                rs.close();
                pStmt.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("通过userName获取用户信息失败：数据库关闭错误");
            }
        }
        return list;
    }
}
