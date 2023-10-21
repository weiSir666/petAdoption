package service;

import dao.AdminLoginDao;
import dao.UserLoginDao;
import pojo.Admin;

/**
 * 管理员登录service层
 * 返回值：true管理员登陆成功，false失败
 */
public class AdminLoginService {
    private AdminLoginDao adminLoginDao = new AdminLoginDao();
    public boolean adminLogin(String name, String password) {
        Admin admin = adminLoginDao.adminLoginDao(name);
        if (admin == null) {
            return false;
        }
        else {
            if (password.equals(admin.getAdminPassword())) {
                return true;   //成功
            }
            else {
                return false;   //失败
            }
        }
    }
}
