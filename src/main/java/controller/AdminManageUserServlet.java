package controller;

import dao.AdminManageUserDao;
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
import java.util.List;

/**
 * 管理员登录成功后跳转到用户管理界面，实现分页加载用户数据
 */
@WebServlet("/adminManageUserServlet")
public class AdminManageUserServlet extends HttpServlet {
    private AdminManageUserDao adminManageUserDao = new AdminManageUserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从后台获取所有数据
        AdminManageUserService adminManageUserService = new AdminManageUserService();
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        //获取当前页码数（管理员登录成功后默认为1）
        int pageNow = Integer.parseInt(request.getParameter("pageNow"));


        String flag = request.getParameter("flag");
        String deleteFlag = request.getParameter("deleteFlag");

        //System.out.println("当前页码" + pageNow);

        //allPage总页数
        int allPage = adminManageUserService.getAllPage();
        //System.out.println("总页码" + allPage);
        ArrayList<User> list = new ArrayList<>();
        //传递页码数，返回该页码的数据
        list = adminManageUserService.getInformationByPage(pageNow);
        //System.out.println(list);

        //每页数据集合
        session.setAttribute("pageList", list);
        //总页码
        //session.setAttribute("allPages", allPage);
        //当前页码
        session.setAttribute("pageNow", pageNow);
        response.sendRedirect(path + "/html/userManagement.jsp?pageNow=" + pageNow + "&allPage=" + allPage + "&flag=" + flag +"&deleteFlag=" + deleteFlag);
    }
}
