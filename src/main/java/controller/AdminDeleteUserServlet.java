package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.AdminManageUserService;

import java.io.IOException;



/**
 * 管理员实现删除用户Servlet
 */
@WebServlet("/adminDeleteUserServlet")
public class AdminDeleteUserServlet extends HttpServlet {
    private AdminManageUserService adminManageUserService = new AdminManageUserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从后台获取id
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String path = request.getContextPath();
        // 调用Service中的方法
        boolean deleteFlag = adminManageUserService.deleteUserInformationByUserId(userId);
        response.sendRedirect(path + "/adminManageUserServlet?deleteFlag=" + deleteFlag + "&pageNow=1");
    }
}
