package service;

import dao.UserLoginDao;
import pojo.Admin;
import pojo.RegisterPojo;

/**
 * 用户登录service层
 * 返回值：true普通用户登录成功，false失败
 */
public class UserLoginService {
    private UserLoginDao userLoginDao = new UserLoginDao();
    public boolean userLogin(String name, String password) {
        RegisterPojo registerPojo = userLoginDao.RegisterPojoLoginDao(name);
        if (registerPojo == null) {
            return false;
        }
        else {
            if (password.equals(registerPojo.getUserPassword())) { //密码正确
                return true;
            }
            else {
                return false;
            }
        }
    }
}
