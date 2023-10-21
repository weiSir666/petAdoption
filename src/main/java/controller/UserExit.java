package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserLoginService;

import java.io.IOException;


/**
 * 用户退出登录Servlet
 *  用户退出登录后跳转到主页
 */
@WebServlet("/userExit")
public class UserExit extends HttpServlet {
    private UserLoginService userLoginService = new UserLoginService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path = request.getContextPath();

        // 销毁session
        session.removeAttribute("RegisterPojoUserId");
        session.removeAttribute("RegisterPojoUserName");
        session.removeAttribute("RegisterPojoRealName");
        session.removeAttribute("RegisterPojoUserSex");
        session.removeAttribute("RegisterPojoUserPlace");
        session.removeAttribute("RegisterPojoUserPhone");

        response.sendRedirect(path + "/html/mainPage.jsp?exitFlag=1");
    }
}
