package service;

import dao.AdminManageUserDao;
import pojo.User;

import java.util.ArrayList;

public class AdminManageUserService {
    private AdminManageUserDao adminManageUserDao = new AdminManageUserDao();
    //设置每页展示的数据量
    public static int pageSize = 20;

    /**
     * 获取所有用户信息
     * @return ArrayList<User>
     */
    public ArrayList<User> getAllInformationByName() {
        return adminManageUserDao.getAllInformationByName();
    }

    /**
     * 统计总页码数
     * @return count(总页码数)
     */
    public int getAllPage() {
        int sum = adminManageUserDao.getUserCount();
        int count = (sum + pageSize - 1) / pageSize;

        return count;
    }

    /**
     * 通过传递的页码数展示该页码数的数据
     * @param page(页码数)
     * @return ArrayList<User>
     */
    public ArrayList<User> getInformationByPage(int page) {
        //偏移量offset
        int offset = (page - 1) * pageSize;
        return adminManageUserDao.getInformationByPage(offset, pageSize);
    }

    /**
     * 通过用户id查询用户信息（如果String类型数据为null，则将数据改为 用户未填写）
     * @param userId
     * @return User
     */
    public User getInformationByUserid(int userId) {
        User user = adminManageUserDao.getInformationByUserid(userId);
        if (user.getRealName() == null) {
            user.setRealName("用户未填写");
        }
        if (user.getUserSex() == null) {
            user.setUserSex("用户未填写");
        }
        if (user.getPlace() == null) {
            user.setPlace("用户未填写");
        }
        if (user.getUserPhone() == null) {
            user.setUserPhone("用户未填写");
        }
        return user;
    }

    /**
     * 通过用户id更新用户数据（用户名不可更改）
     * @param userId,userRealName,userPassword,userAge,userPhone,userAddress,userSex
     * @return User
     */
    public boolean updateUserInformationByUserId(int userId, String userRealName, String userPassword,
                                                 int userAge, String userPhone, String userAddress, String userSex) {
        User user = new User();
        int flag;
        if (userRealName.equals("用户未填写") || userRealName.isEmpty()) {
            userRealName = null;
        }
        if (userPhone.equals("用户未填写") || userPhone.isEmpty()) {
            userPhone = null;
        }
        if (userAddress.equals("用户未填写") || userAddress.isEmpty()) {
            userAddress = null;
        }
        if (userSex.equals("用户未填写") || userSex.isEmpty()) {
            userSex = null;
        }
        flag = adminManageUserDao.updateUserInformationByUserId(userId, userRealName, userPassword,
                userAge, userPhone, userAddress, userSex);
        if (flag == 1) {
            System.out.println("更新数据成功");
            return true;
        } else {
            System.out.println("更新数据失败");
            return false;
        }
    }

    /**
     * 通过用户id删除用户信息
     * @param userId
     * @return true or flase（true为删除成功，false为删除失败）
     */
    public boolean deleteUserInformationByUserId(int userId) {
        int deleteFlag = adminManageUserDao.deleteUserInformationByUserId(userId);
        System.out.println(deleteFlag);
        if (deleteFlag >= 1 && deleteFlag <=3) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return User
     */
    public ArrayList<User> findUserInformationByUserName(String userName) {
        return adminManageUserDao.findUserInformationByUserName(userName);
    }
}
