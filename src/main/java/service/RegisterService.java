package service;

import dao.RegisterDao;

import java.sql.SQLException;

public class RegisterService {
    private RegisterDao registerdao = new RegisterDao();

    //验证用户名是否存在
    public int Register(String username)throws SQLException {
        return registerdao.GetUserByName(username);
    }
}