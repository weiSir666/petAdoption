package controller;

import dao.UserLoginDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.RegisterPojo;
import service.UserLoginService;

import java.io.IOException;


/**
 * 用户登录Servlet
 *  ：根据传递name属性先到数据库的管理员表进行效验，如果不是管理员再去用户表效验
 */
@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private UserLoginService userLoginService = new UserLoginService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getContextPath();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        //System.out.println(username + " "+ password);

        //调用service层方法,true为登陆成功,false为登陆失败
        boolean flag = userLoginService.userLogin(username, password);
        System.out.println(flag);
        if (flag == true) {
            //登陆成功后调用dao层方法查询某个用户数据
            UserLoginDao userLoginDao = new UserLoginDao();
            RegisterPojo registerPojo = userLoginDao.RegisterPojoGetInformationByName(username);
            //把数据封装到session，便于在jsp页面取数据
            session.setAttribute("RegisterPojoUserId", registerPojo.getUserId());
            session.setAttribute("RegisterPojoUserPassword", registerPojo.getUserPassword());
            session.setAttribute("RegisterPojoUserName", registerPojo.getUserName());
            session.setAttribute("RegisterPojoRealName", registerPojo.getRealName());
            session.setAttribute("RegisterPojoUserSex", registerPojo.getUserSex());
            session.setAttribute("RegisterPojoUserPlace", registerPojo.getPlace());
            session.setAttribute("RegisterPojoUserPhone", registerPojo.getUserPhone());
            //数据库没数据，age默认为0
            if (registerPojo.getUserAge() == 0) {
                session.setAttribute("RegisterPojoUserAge", null);
            }
            else {
                session.setAttribute("RegisterPojoUserAge", registerPojo.getUserAge());
            }
            //重定向到个人中心
            response.sendRedirect(path + "/html/PersonalCenter.jsp");
        }
        else {
            session.setAttribute("msg1","账号或者密码错误");
            response.sendRedirect(path + "/html/login.jsp");
        }
    }
}
