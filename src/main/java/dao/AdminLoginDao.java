package dao;

import common.DBConnection;
import pojo.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminLoginDao {
    private Connection con = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    /**
     *  管理员登录效验，查询账号密码是否一致，一致则登录
     *  返回值：Admin对象
     */
    public Admin adminLoginDao(String adminName) {
        con = DBConnection.getConnection();
        Admin admin = null;
        try {
            String sql = "SELECT * FROM t_admin where admin_name = ?;";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,adminName);
            rs = pstm.executeQuery();
            if(rs.next()) {
                admin = new Admin();
                admin.setAdminName(rs.getString("admin_name"));
                admin.setAdminPassword(rs.getString("password"));
            }
        }
        catch (Exception err) {
            err.printStackTrace();
            System.out.println("管理员查询出错");
        }finally {
            try{
                rs.close();
                pstm.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("数据库关闭错误");
            }
        }
        //这里用于测试数据，如果没查找到数据，admin为空对象，则以下两条数据会出错
        //System.out.println(admin.getAdminPassword());
        //System.out.println(admin.getAdminName());
        return admin;
    }

}
