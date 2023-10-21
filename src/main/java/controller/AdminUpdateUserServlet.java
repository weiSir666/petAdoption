package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.RegisterPojo;
import pojo.User;
import service.AdminManageUserService;

import java.io.IOException;

/**
 * 管理员更新用户信息Servlet(点击编辑按钮后提交到该页面)
 */
@WebServlet("/adminUpdateUserServlet")
public class AdminUpdateUserServlet extends HttpServlet {
    private AdminManageUserService adminManageUserService = new AdminManageUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        //System.out.println("userId = " + userId);
        //根据userId获取数据
        User user = adminManageUserService.getInformationByUserid(userId);
        //System.out.println(user);
        session.setAttribute("userUpdate_UserInformation", user);
        response.sendRedirect(path + "/html/userUpdate.jsp");
    }
}
