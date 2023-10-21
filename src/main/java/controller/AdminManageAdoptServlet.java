package controller;

import dao.AdminManageAdoptDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.Adopt;
import service.AdminManageAdoptService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员审核领养
 */
@WebServlet("/adminManageAdoptServlet")
public class AdminManageAdoptServlet extends HttpServlet {
    private AdminManageAdoptDao adminManageAdoptDao = new AdminManageAdoptDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从后台获取所有数据
        AdminManageAdoptService adminManageAdoptService = new AdminManageAdoptService();
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        //获取当前页码数（管理员登录成功后默认为1）
        int pageNow = Integer.parseInt(request.getParameter("pageNow"));
        System.out.println("当前页码" + pageNow);

        //allPage总页数
        int allPage = adminManageAdoptService.getAllPage();
        System.out.println("总页码" + allPage);
        ArrayList<Adopt> list;
        //传递页码数，返回该页码的数据
        list = adminManageAdoptService.getInformationByPage(pageNow);
        System.out.println(list);

        //每页数据集合
        session.setAttribute("pageList1", list);
        //总页码
        //session.setAttribute("allPages", allPage);
        //当前页码
        session.setAttribute("pageNow", pageNow);
        response.sendRedirect(path + "/html/adoptManagement.jsp?pageNow=" + pageNow + "&allPage=" + allPage);
        //request.getRequestDispatcher("/html/adoptManagement.jsp?pageNow=" + pageNow).forward(request, response);
    }
}
