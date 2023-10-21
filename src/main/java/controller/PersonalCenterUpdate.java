package controller;

import common.MyTools;
import dao.PersonalCenterUpdateDao;
import dao.UserLoginDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.PersonalCenterPojo;
import pojo.RegisterPojo;

import java.io.IOException;

@WebServlet("/PersonalCenterUpdate")
public class PersonalCenterUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getContextPath();
        String realname = request.getParameter("realname");                 //用户姓名
        String password = request.getParameter("userpassword");             //用户密码
        String sex = request.getParameter("sex");                           //性别
        int age = MyTools.strToint(request.getParameter("age"));            //年龄
        String telphone = request.getParameter("telphone");                 //电话号码
        String place = request.getParameter("address");                     //地址
        //将得到的用户信息全部封装到pojo
        PersonalCenterPojo personalcenterpojo = new PersonalCenterPojo();
        personalcenterpojo.setUsername(request.getParameter("username"));
        personalcenterpojo.setRealname(realname);
        personalcenterpojo.setUserpassword(password);
        personalcenterpojo.setSex(sex);
        personalcenterpojo.setAge(age);
        personalcenterpojo.setTelphone(telphone);
        personalcenterpojo.setPlace(place);
        //调用dao层方法将personalcenterpojo对象更新到数据库中
        PersonalCenterUpdateDao personalcenterupdatedao = new PersonalCenterUpdateDao();
        int result = personalcenterupdatedao.userUpdate(personalcenterpojo);
        if(result == 0){
            //更新失败
            System.out.println("用户信息更新失败");
        }
        if(result == 1){
            //更新成功
            //把数据封装到session，便于在jsp页面取数据
            HttpSession session = request.getSession();
            session.setAttribute("RegisterPojoUserPassword", password);                                  //用户密码
            session.setAttribute("RegisterPojoUserName", request.getParameter("username"));           //用户名
            session.setAttribute("RegisterPojoRealName", realname);                                      //用户姓名
            session.setAttribute("RegisterPojoUserSex", sex);                                            //用户性别
            session.setAttribute("RegisterPojoUserPlace", place);                                        //用户地址
            session.setAttribute("RegisterPojoUserPhone", telphone);                                     //用户手机号
            if(age == 0){
                session.setAttribute("RegisterPojoUserAge", null);                                    //用户年龄
            }
            else {
                session.setAttribute("RegisterPojoUserAge", age);                                        //用户年龄
            }
            //重定向到个人中心界面
            int flag = 1;
            response.sendRedirect(path + "/html/PersonalCenter.jsp?updateflag="+flag);
            System.out.println("更新成功");
        }

    }
}
