package dao;

import common.DBConnection;
import pojo.Pet;
import pojo.User;

import java.sql.*;
import java.util.ArrayList;

/**
 *宠物信息管理方法
 * */
public class PetManageDao {
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private Connection con = null;
    private Statement statement = null;
    public static int PetPageSize=3;
    /**
     * 获取所有宠物信息
     * @return ArrayList集合
     */
    public ArrayList<Pet> getAllPetInformation( ) {
        ArrayList<Pet> list = new ArrayList<Pet>();
        con = DBConnection.getConnection();
        try {
            String sql = "SELECT * from t_pet;";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                //一条一条的封装数据
                Pet pet=new Pet();
                pet.setPetId(rs.getInt("pet_id"));
                pet.setPetType(rs.getString("pet_type"));
                pet.setPetId(rs.getInt("pic_id"));
                pet.setSex(rs.getString("sex"));
                list.add(pet);
            }
        }catch (Exception err){
            System.out.println("获取所有宠物信息失败");
            err.printStackTrace();
        }finally {
            try{
                //关闭数据库
                rs.close();
                pstm.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("获取所有宠物：数据库关闭错误");
            }
        }
        //System.out.println(list);
        return list;
    }

    /**
     * 查询宠物总个数
     * **/
    public int getPetCount() throws SQLException {
        con=DBConnection.getConnection();
        int count=0;
        try {

            String sql="select count(*) total from t_pet";
            pstm = con.prepareStatement(sql);
            rs=pstm.executeQuery();
            if(rs.next()){
                count=rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("宠物个数查询失败");
        }finally {
            DBConnection.closeConnection();
            pstm.close();
            rs.close();
        }
        return count;
    }

    /**
     * 根据隐藏页码查询数据，返回一个宠物集合
     * 前台3个宠物一次
     * pagesize一次展示的数据量
     * pagenow起始数据下标（当前页码-1）*pagesize
     * **/
    public ArrayList<Pet> GetInformationByPageNow(int pagenow) throws SQLException {
        con=DBConnection.getConnection();
        ArrayList<Pet> list=new ArrayList<>();
        int offset=(pagenow-1)*3;

        try {
            String sql="select * from t_pet join t_picture tp on tp.pic_id = t_pet.pic_id limit ?,?;";

            pstm=con.prepareStatement(sql);
            pstm.setInt(1,offset);
            pstm.setInt(2,PetPageSize);
            rs=pstm.executeQuery();
            while (rs.next()){
                Pet pet=new Pet();
                pet.setPetId(rs.getInt("pet_id"));
                pet.setPetType(rs.getString("pet_type"));
                pet.setPicId(rs.getInt("pic_id"));
                pet.setSex(rs.getString("sex"));
                pet.setImgurl(rs.getString("pic_url"));
                list.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("由页码封装宠物数据失败");
        }finally {
            try {
                pstm.close();
                rs.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }

    public ArrayList<Pet> AdminGetInformationByPageNow(int pagenow) throws SQLException {
        con=DBConnection.getConnection();
        ArrayList<Pet> list=new ArrayList<>();
        int offset=(pagenow-1)*20;

        try {
            String sql="SELECT *FROM t_pet LEFT OUTER JOIN t_adopt ON t_pet.pet_id = t_adopt.pet_id LIMIT ? , ?";
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,offset);
            pstm.setInt(2,20);
            rs=pstm.executeQuery();
            while (rs.next()){
                Pet pet=new Pet();
                pet.setPetId(rs.getInt("pet_id"));
                pet.setPetType(rs.getString("pet_type"));
                pet.setSex(rs.getString("sex"));
                pet.setRemark(rs.getString("remark"));
                pet.setAdoptstate(rs.getInt("state"));
                list.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("由页码封装宠物数据失败");
        }finally {
            try {
                pstm.close();
                rs.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }
    /**
     *根据宠物id删除数据并返回 1删除成功 0删除失败
     **/
    public int adminDeletePetInformantionById(int PetId) throws SQLException {
        con = DBConnection.getConnection();
        int flag1 = 0 , flag2 = 0, flag3 = 0;
        //解除外键约束
        String set0 = "SET FOREIGN_KEY_CHECKS = 0;";
        //恢复外键约束
        String set1 = "SET FOREIGN_KEY_CHECKS = 1;";
        //先删除相关领养
        String sql1 = "delete from t_adopt where pet_id= ? ;";
        //再删除pet
        String sql2 = "delete from t_pet where pet_id=?;";
        // 删除评论表
        String sql3 = "delete from t_comment where pet_id=?";
        try {
            con.prepareStatement(set0).execute(); // 执行SQL语句并忽略返回结果

            pstm = con.prepareStatement(sql3);
            pstm.setInt(1, PetId);
            flag3 = pstm.executeUpdate();

            pstm = con.prepareStatement(sql1);
            pstm.setInt(1, PetId);
            flag1 = pstm.executeUpdate();

            pstm = con.prepareStatement(sql2);
            pstm.setInt(1, PetId);
            flag2 = pstm.executeUpdate();

            con.prepareStatement(set1).execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("宠物信息删除失败");
        }finally {
            pstm.close();
            DBConnection.closeConnection();
        }
        return flag1 + flag2 + flag3;
    }

    public int adminUpdatePetInformation(int petid,String remark,String name,String sex){
        int count=0;

        try {
            con = DBConnection.getConnection();
            String sql = "update t_pet set remark = ? , pet_type = ? ,sex = ? where pet_id = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,remark);
            pstm.setString(2,name);
            pstm.setString(3,sex);
            pstm.setInt(4,petid);
            count = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("宠物更新失败");
        }finally {
            try {
                DBConnection.closeConnection();
                pstm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }

    public Pet getPetInformationByPetId(int petid) {
        con = DBConnection.getConnection();
        Pet pet = null;
        try {
            String sql = "SELECT * from t_pet where pet_id = ?;";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, petid);
            rs = pstm.executeQuery();
            if (rs.next()) {
                //一条一条的封装数据
                pet = new Pet();
                pet.setPetId(rs.getInt("pet_id"));
                pet.setPetType(rs.getString("pet_type"));
                pet.setSex(rs.getString("sex"));
            }
        } catch (Exception err) {
            System.out.println("获取所有宠物信息失败");
            err.printStackTrace();
        } finally {
            try {
                //关闭数据库
                rs.close();
                pstm.close();
                DBConnection.closeConnection();
            } catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("获取所有宠物：数据库关闭错误");
            }
        }
        //System.out.println(list);
        return pet;
    }
    /**
     * 提交宠物信息到数据库
     * **/
    public int submitPetInformation(String name,String sex){
        int count=0;
        con = DBConnection.getConnection();
        Pet pet = null;
        try {
            String sql = "INSERT INTO `db_petsys`.`t_pet`(`pet_type`, `sex`) VALUES (?, ?)";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2,sex);
            count = pstm.executeUpdate();

        } catch (Exception err) {
            System.out.println("插入宠物信息失败");
            err.printStackTrace();
        } finally {
            try {
                //关闭数据库
                rs.close();
                pstm.close();
                DBConnection.closeConnection();
            } catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("插入宠物信息：数据库关闭错误");
            }
        }

        return count;
    }
}















