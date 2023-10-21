package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.AdminLoginService;
import service.UserLoginService;

import java.io.IOException;


/**
 * 管理员登录Servlet
 *  ：根据传递name属性到数据库进行效验。登陆成转到AdminManageUserServlet
 */
@WebServlet("/adminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private AdminLoginService adminLoginService = new AdminLoginService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //System.out.println(username + " "+ password);

        //调用service层方法,true为登陆成功,false为登陆失败
        boolean flag = adminLoginService.adminLogin(username, password);
        System.out.println(flag + "  true登陆成功   false登陆失败");
        if (flag == true) {
            //跳转到管理员界面

            //管理员登陆成功后页码为第1页
            int pageNow = 1;
            response.sendRedirect(path + "/adminManageUserServlet?pageNow=" + pageNow);
        }
        else {
            session.setAttribute("msg","账号或者密码错误");
            response.sendRedirect(path + "/html/adminLogin.jsp");
        }
    }
}
