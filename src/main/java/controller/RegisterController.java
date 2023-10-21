package controller;

import dao.RegisterDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.PersonalCenterPojo;
import service.RegisterService;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户注册Servlet
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public RegisterController(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();//获取站点的根路径
        HttpSession session = request.getSession();

        int num = 0;//用来判断用户是否存在的标志,num为0表示没有该用户，则可以进行注册操作，num为1的话，说明已经存在该用户，则不能添加;
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");

        try{
            RegisterService registerservice = new RegisterService();
            num = registerservice.Register(username);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("判断用户是否存在失败！");
        }

        if(num==0) {
            RegisterDao registerdao = new RegisterDao();

            PersonalCenterPojo personalcenterpojo = new PersonalCenterPojo();
            //客户端获取数据，并进行封装
            personalcenterpojo.setUsername(request.getParameter("username"));
            personalcenterpojo.setUserpassword(request.getParameter("userpassword"));

            int flag = 0;
            flag = registerdao.insertUser(personalcenterpojo);

            //向客户端做出回应
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            if (flag != 0) {
                response.sendRedirect(path + "/html/login.jsp?loginflag=" + flag);
            }
        }
        if(num==1){
            int flag = 1;
            session.setAttribute("username",username);
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect(path + "/html/register.jsp?flag="+flag);
        }
    }
}