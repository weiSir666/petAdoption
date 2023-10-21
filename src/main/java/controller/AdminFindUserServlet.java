package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.User;
import service.AdminManageUserService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 查询用户信息Servlet
 */
@WebServlet("/adminFindUserServlet")
public class AdminFindUserServlet extends HttpServlet {
    private AdminManageUserService adminManageUserService = new AdminManageUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        // 从前台获取数据
        String userName = request.getParameter("findByName");

        // 调用Service层方法
        ArrayList<User> list = adminManageUserService.findUserInformationByUserName(userName);
        // 未查询到数据
        if (list.size() == 0) {
            response.sendRedirect(path + "/html/userManagement.jsp?pageNow=1&allPage=1&findFlag=false");
        }
        // 查询到数据
        else {
            session.setAttribute("pageList", list);
            response.sendRedirect(path + "/html/userManagement.jsp?pageNow=1&allPage=1");
        }
    }
}