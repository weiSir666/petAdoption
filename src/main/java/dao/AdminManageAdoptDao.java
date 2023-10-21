package dao;

import common.DBConnection;
import pojo.Adopt;

import java.sql.*;
import java.util.ArrayList;

public class AdminManageAdoptDao {
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection con = null;
    private Statement statement = null;

    /**
     * 获取所有用户信息
     * @return ArrayList集合
     */
    public ArrayList<Adopt> getAllInformationByName( ) {
        ArrayList<Adopt> list = new ArrayList<Adopt>();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * from t_adopt;";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //一条一条的封装数据
                Adopt adopt = new Adopt();
                adopt.setAdoptId(rs.getInt("adopt_id"));
                adopt.setUserId(rs.getInt("user_id"));
                adopt.setPetId(rs.getInt("pet_id"));
                adopt.setApply_reason(rs.getString("apply_reason"));
                adopt.setState(rs.getInt("state"));
                adopt.setApplyTime(rs.getDate("apply_time"));
                list.add(adopt);
            }
        }catch (Exception err){
            System.out.println("获取所有用户信息失败");
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                rs.close();
                pstmt.close();
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
    public int getAdoptCount() {
        int count = 0;
        String sql = "select COUNT(*) total from t_adopt;";
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
    public ArrayList<Adopt> getInformationByPage(int offset, int pageSize) {
        ArrayList<Adopt> list = new ArrayList<>();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM t_adopt LIMIT ?, ?;";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, offset);
            pstmt.setInt(2, pageSize);
            //System.out.println(offset);
            //System.out.println(pageSize);.
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //一条一条的封装数据
                Adopt adopt = new Adopt();
                adopt.setAdoptId(rs.getInt("adopt_id"));
                adopt.setUserId(rs.getInt("user_id"));
                adopt.setPetId(rs.getInt("pet_id"));
                adopt.setApply_reason(rs.getString("apply_reason"));
                adopt.setState(rs.getInt("state"));
                adopt.setApplyTime(rs.getDate("apply_time"));
                System.out.println(adopt);
                list.add(adopt);
            }
        }catch (Exception err){
            System.out.println("通过页码获取用户数据失败");
            System.out.println(list.size());
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                rs.close();
                pstmt.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("通过页码查询数据：数据库关闭错误");
            }
        }
        //System.out.println(list);
        return list;
    }

    /**
     * 通过adoptId查询用户信息
     * @param adoptId
     * @return Adopt对象
     */
    public Adopt getInformationByAdoptid(int adoptId) {
        Adopt adopt = new Adopt();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM t_adopt WHERE adopt_id = ?;";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, adoptId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                adopt.setAdoptId(rs.getInt("adopt_id"));
                adopt.setUserId(rs.getInt("user_id"));
                adopt.setPetId(rs.getInt("pet_id"));
                adopt.setApply_reason(rs.getString("apply_reason"));
                adopt.setState(rs.getInt("state"));
                adopt.setApplyTime(rs.getDate("apply_time"));
            }
        }catch (Exception err){
            System.out.println("通过adoptId获取用户信息失败");
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                rs.close();
                pstmt.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("通过adoptId获取用户信息失败：数据库关闭错误");
            }
        }
        return adopt;
    }

    public boolean updateAdoptState(Adopt adopt) throws Exception {
        boolean result = false;
        try {
            con = DBConnection.getConnection();
            String sql = "UPDATE t_adopt SET state = ? WHERE adopt_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, adopt.getState());
            pstmt.setInt(2, adopt.getAdoptId());
            int count = pstmt.executeUpdate();
            if (count > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            con.close();
        }
        return result;
    }

    public ArrayList<Adopt> getInformationByUserid(int user_id) {
        ArrayList<Adopt> arrayList = new ArrayList<Adopt>();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM t_adopt Where user_id = ?;";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            //System.out.println(offset);
            //System.out.println(pageSize);.
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //一条一条的封装数据
                Adopt adopt = new Adopt();
                adopt.setAdoptId(rs.getInt("adopt_id"));
                adopt.setUserId(rs.getInt("user_id"));
                adopt.setPetId(rs.getInt("pet_id"));
                adopt.setApply_reason(rs.getString("apply_reason"));
                adopt.setState(rs.getInt("state"));
                adopt.setApplyTime(rs.getDate("apply_time"));
                System.out.println(adopt);
                arrayList.add(adopt);
            }
        }catch (Exception err){
            System.out.println("通过页码获取用户数据失败");
            System.out.println(arrayList.size());
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                rs.close();
                pstmt.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("通过页码查询数据：数据库关闭错误");
            }
        }
        //System.out.println(list);
        return arrayList;
    }
}
