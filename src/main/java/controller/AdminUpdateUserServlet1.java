package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AdminManageUserService;

import java.io.IOException;

/**
 * 用户编辑界面点击更新用户信息后跳转到该Sevlet
 */
@WebServlet("/adminUpdateUserServlet1")
public class AdminUpdateUserServlet1 extends HttpServlet {
    private AdminManageUserService adminManageUserService = new AdminManageUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getContextPath();
        // 从前台获取数据(用户名不可更改)
        int userAge;
        int userId = Integer.parseInt(request.getParameter("userUpdateUserId"));
        String userRealName = request.getParameter("userUpdateRealName");
        String userPassword = request.getParameter("userUpdateUserPassword");
        String userAge1 = request.getParameter("userUpdateUserAge");
        //年龄为空的时候数据库更新int年龄数据应该为0
        if (userAge1.equals("用户未填写") || userAge1.isEmpty()) {
            userAge = 0;
        }
        else {
            userAge = Integer.parseInt(request.getParameter("userUpdateUserAge"));
        }
        String userPhone = request.getParameter("userUpdateUserPhone");
        String userAddress = request.getParameter("userUpdateUserAddress");
        String userSex = request.getParameter("userUpdateUserSex");

        // 调用service中的方法
        boolean flag = adminManageUserService.updateUserInformationByUserId(userId, userRealName, userPassword,
                userAge, userPhone, userAddress, userSex);
        int allPage = adminManageUserService.getAllPage();
        int pageNow = 1;
        response.sendRedirect(path + "/adminManageUserServlet?flag=" + flag + "&pageNow=" + pageNow + "&allPage=" +allPage);
    }
}