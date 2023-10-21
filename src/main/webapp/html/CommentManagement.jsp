<%--管理员管理用户信息界面--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.User" %>
<%@ page import="pojo.Comment" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
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
    <span class="userManagement15">评论管理</span>
  </div>
  <div class="userManagement16">
    <span class="userManagement17">这里是所有评论:</span>
    <div class="userManagement2-01">
      <table class="userManagement2-02">
        <thead>
        <th>评论Id</th>
        <th>评论用户</th>
        <th>被评论宠物</th>
        <th>评论内容</th>
        <th>评论时间</th>
        </thead>
        <tbody>
        <%
          //当前页码数
          int commentPage = Integer.parseInt(request.getParameter("commentPage"));
          //总页码
          int commentAllPage = Integer.parseInt(request.getParameter("commentAllPage"));
          //取数据
          ArrayList<Comment> list = (ArrayList<Comment>)session.getAttribute("commentList");
          for (int i = 0;i < list.size();i++) {
        %>
        <tr class="userManagement2-03">
          <td><%=list.get(i).getCommentId()%></td>
          <td><%=list.get(i).getUserName()%></td>
          <td><%=list.get(i).getPetName()%></td>
          <td><%=list.get(i).getContent()%></td>
          <td><%=list.get(i).getCommentTime()%></td>
          <td>
            <%--删除按钮--%>
            <a style="text-align: center; text-decoration: none; color: blue"  href="<%=path%>/deleteComment?comment_id=<%=list.get(i).getCommentId()%>">删除</a>
          </td>
          <%
            }
          %>
        </tr>
        <tr>
          <td>当前页码数:<%=commentPage%></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td style="text-align: center">
          <% if (commentAllPage != 1) { %>
            <% if (commentPage == 1) { %>
              <a style="text-decoration: none; color: blue" href="<%=path%>/commentManagementServlet?commentPage=<%=commentPage + 1%>">下一页</a>
            <% } %>
            <% if (commentPage == commentAllPage) { %>
              <a style="text-decoration: none; color: blue" href="<%=path%>/commentManagementServlet?commentPage=<%=commentPage - 1%>">上一页</a>
            <% } %>
            <% if (commentPage > 1 && commentPage < commentAllPage) { %>
              <a style="text-decoration: none; color: blue" href="<%=path%>/commentManagementServlet?commentPage=<%=commentPage - 1%>">上一页</a>
              <a style="text-decoration: none; color: blue" href="<%=path%>/commentManagementServlet?commentPage=<%=commentPage + 1%>">下一页</a>
            <% } %>
            <% } %>
          </td>
          <a href="<%=path%>/commentManagementServlet?commentPage=1" style="text-decoration: none; color: blue">刷新数据</a>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
<script>
  <%
    String deleteCommentFlag = request.getParameter("deleteCommentFlag");
    if (deleteCommentFlag != null) {
      if (deleteCommentFlag.equals("true")) {
        out.println("alert('删除评论成功')");
      }
      if (deleteCommentFlag.equals("false")) {
        out.println("alert('删除评论失败')");
      }
    }
  %>
</script>
</html>