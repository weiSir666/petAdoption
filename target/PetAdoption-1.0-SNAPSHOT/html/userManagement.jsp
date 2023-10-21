<%--管理员管理用户信息界面--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="cn">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
  <link rel="stylesheet" href="../css/userManagement.css">
  <link rel="stylesheet" href="../css/userManagement2.css">
  <title>管理员界面</title>
</head>
<body>
<!-- 左侧部分 -->
<div class="userManagement00">
  <div class="userManagement01"><a href="<%=path%>/html/mainPage.jsp?exitAdmin=1" class="userManagement02">宠物领养系统</a></div>
  <div class="userManagement03">
    <span class="userManagement04">用户管理</span>
  </div>
  <div class="userManagement05">
    <a href="<%=path%>/adminManageUserServlet?pageNow=1" class="userManagement06">用户信息</a>
  </div>
  <div class="userManagement07">
    <a href="<%=path%>/AdminManagePetServlet?petPage=1" class="userManagement08">宠物管理</a>
  </div>
  <div class="userManagement09">
    <a href="<%=path%>/commentManagementServlet?commentPage=1" class="userManagement10">评论管理</a>
  </div>
  <div class="userManagement11">
    <a href="<%=path%>/adminManageAdoptServlet?pageNow=1" class="userManagement12">领养审核管理</a>
  </div>
  <div class="userManagement20">
    <a href="../html/UpdateVideo.jsp" class="userManagement21">视频管理</a>
  </div>
</div>
<!-- 右上侧部分 -->
<div class="userManagement13">
  <div class="userManagement14">
    <span class="userManagement15">用户管理</span>
  </div>
  <div class="userManagement16">
    <span class="userManagement17">用户名称查询:
      <form action="<%=path%>/adminFindUserServlet" name="findByNameForm" id="findByNameForm">
        <input type="text" name="findByName" id="findByName" class="userManagement18" placeholder="根据用户名查询">
        <input type="submit" class="userManagement19" value="查询">
      </form>
    </span>



    <div class="userManagement2-01">
      <table class="userManagement2-02">
        <thead>
        <th>用户id</th>
        <th>用户名</th>
        <th>用户真实姓名</th>
        <th>用户年龄</th>
        <th>用户电话</th>
        <th>用户住址</th>
        <th>用户性别</th>
        <th>管理员操作</th>
        </thead>
        <tbody>
        <%
          //当前页码数
          int pageNow = Integer.parseInt(request.getParameter("pageNow"));
          //总页码
          int allPage = Integer.parseInt(request.getParameter("allPage"));
          System.out.println("jsp页面页码" + pageNow);
          //取数据
          ArrayList<User> list = (ArrayList<User>)session.getAttribute("pageList");
          int user_id = 0;
          String realName = null;
          String userAge = null;
          String userPhone = null;
          String place = null;
          String userSex = null;
          for (int i = 0;i < list.size();i++) {
            //需要进行判断是否为空，如果为空则不显示内容
            user_id = list.get(i).getUserId();
            if (list.get(i).getRealName() == null) {
              realName = "用户未填写";
            }
            else {
              realName = list.get(i).getRealName();
            }
            //年龄为0说明用户未填写
            if (list.get(i).getUserAge() == 0) {
              userAge = "用户未填写";
            }
            else {
              userAge = String.valueOf(list.get(i).getUserAge());
            }
            if (list.get(i).getUserPhone() == null) {
              userPhone = "用户未填写";
            }
            else {
              userPhone = String.valueOf(list.get(i).getUserPhone());
            }
            if (list.get(i).getPlace() == null) {
              place = "用户未填写";
            }
            else {
              place = list.get(i).getPlace();
            }
            if (list.get(i).getUserSex() == null) {
              userSex = "用户未填写";
            }
            else {
              userSex = list.get(i).getUserSex();
            }
        %>
        <tr class="userManagement2-03">
          <td><%=list.get(i).getUserId()%></td>
          <td><%=list.get(i).getUserName()%></td>
          <td><%=realName%></td>
          <td><%=userAge%></td>
          <td><%=userPhone%></td>
          <td><%=place%></td>
          <td><%=userSex%></td>
          <td>
            <%--编辑和删除按钮--%>
            <a style="text-align: center; text-decoration: none; color: blue"  href="<%=path%>/adminUpdateUserServlet?user_id=<%=user_id%>">编辑</a>
            <a style="text-align: center; text-decoration: none; color: blue"  href="<%=path%>/adminDeleteUserServlet?user_id=<%=user_id%>">删除</a>
          </td>
          <%
            }
          %>
        </tr>
        <tr>
          <td>当前页码数:<%=pageNow%></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td style="text-align: center">
            <% if (allPage != 1) {%>
            <% if(pageNow == 1) {%>
            <a style="text-decoration: none; color: blue" href="<%=path%>/adminManageUserServlet?pageNow=<%=pageNow + 1%>">下一页</a>
            <%}%>
            <% if(pageNow == allPage) {%>
            <a style="text-decoration: none; color: blue" href="<%=path%>/adminManageUserServlet?pageNow=<%=pageNow - 1%>">上一页</a>
            <%}%>
            <% if(pageNow > 1 && pageNow < allPage) {%>
            <a style="text-decoration: none; color: blue" href="<%=path%>/adminManageUserServlet?pageNow=<%=pageNow - 1%>">上一页</a>
            <a style="text-decoration: none; color: blue" href="<%=path%>/adminManageUserServlet?pageNow=<%=pageNow + 1%>">下一页</a>
            <%}%>
            <% }%>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
<script>
  <%
    String flag = request.getParameter("flag");
    String deleteFlag = request.getParameter("deleteFlag");
    String findFlag = request.getParameter("findFlag");
    if (flag != null) {
      if (flag.equals("true")) {
        out.println("alert('更新用户数据成功')");
      }
      if (flag.equals("false")){
        out.println("alert('更新用户数据失败')");
      }
    }
    if (deleteFlag != null) {
      if (deleteFlag.equals("true")) {
        out.println("alert('删除用户数据成功')");
      }
      if (deleteFlag.equals("false")) {
        out.println("alert('删除用户数据失败')");
      }
    }
    if (findFlag != null) {
      if (findFlag.equals("false")) {
        out.println("alert('未查找到该用户的数据')");
      }
    }
  %>
</script>
</html>