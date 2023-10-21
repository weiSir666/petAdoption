package dao;

import common.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PetFindDao {
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;
    private Connection con = null;
    public int find_petId_by_petType(String petType){
        int id = 0;
        try{
            con = DBConnection.getConnection();
            pStmt = con.prepareStatement("SELECT pet_id FROM t_pet WHERE pet_type=?");
            pStmt.setString(1,petType);
            rs = pStmt.executeQuery();
            while(rs.next()){
                id = rs.getInt("pet_id");
            }
            pStmt.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查找宠物id失败");
        }
        finally {
            DBConnection.closeConnection();
        }
        return id;
    }
}