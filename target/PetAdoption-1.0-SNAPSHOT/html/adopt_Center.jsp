<%@ page import="pojo.Pet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.PetShowService" %>
<%@ page import="pojo.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  HttpSession session1 = request.getSession();
  String userName = (String) session1.getAttribute("RegisterPojoUserName");
%>
<html>
<head>
  <title>领养中心</title>
  <link rel="stylesheet" href="../css/adopt_center.css">
</head>
<body>
<div>
  <div class="rectangle">
    <%if(userName != null){%>
    <a href="../html/mainPage2.jsp" class="indexmain">主页</a>
    <%}else {%>
    <a href="../html/mainPage.jsp" class="indexmain">主页</a>
    <%}%>
    <%if(userName != null){%>
    <a href="../html/PersonalCenter.jsp" class="index2">个人中心</a>
    <%}%>
  </div>
  <div class="adoptcenter">领养中心</div>
    <%
    //当前页码数
    int petPage = Integer.parseInt(request.getParameter("petPage"));
    //总页码
    int petAllPage = Integer.parseInt(request.getParameter("petAllPage"));
    System.out.println("jsp页面页码" + petPage);
    //取宠物数据集合
    ArrayList<Pet> list = (ArrayList<Pet>)session.getAttribute("petList");
    int pet_Id=0;
    String petType =null;
    String imgUrl=null;

    for(int i=0;i< list.size();i++) {
      pet_Id=list.get(i).getPetId();
      petType=list.get(i).getPetType();
      imgUrl=list.get(i).getImgurl();
  %>
  <img src="<%=imgUrl%>" class="petimglocation" style="left:<%=277+i*464%>px;right:<%=1284-i*464%>px;">
  <div class="petnamelocation petnamestyle" style="left:<%=371+i*484%>px;right:<%=1379-i*484%>px;"><%=petType%></div>
  <%--  查看宠物详情--%>
  <a href="<%=path%>/Adopt_CenterServlet?pet_id=<%=pet_Id%>"  class="petbuttonlocation petbuttontextstyle" style="left:<%=353+i*482%>;right:<%=1360-i*482%>px">查看详情</a>
    <% } %>



  <%--  放评论--%>
    <%
    // 获取评论内容
    ArrayList<ArrayList<Comment>> commentsList = (ArrayList<ArrayList<Comment>>) session.getAttribute("commentsList");
    for (int m = 0;m < commentsList.size();m++) {
  %>
  <div class="tableForComments" style="left:<%=277 + m*464%>px;">
    <table border="1">
      <tr>
        <th>评论人</th>
        <th>评论内容</th>
      </tr>
      <% for (int n = 0;n < commentsList.get(m).size();n++) { %>
      <tr>
        <td><%=commentsList.get(m).get(n).getUserName()%></td>
        <td><%=commentsList.get(m).get(n).getContent()%></td>
      </tr>
      <% } %>
    </table>
  </div>
<% } %>
  <% if (petAllPage != 1) {%>
  <% if(petPage == 1) {%>
  <a  href="<%=path%>/PetShowServlet?petPage=<%=petPage + 1%>">
    <img src="../images/RightArrowHead.jpg" alt="" class="righthead">
  </a>
  <% } %>
  <% if(petPage == petAllPage) {%>
  <a  href="<%=path%>/PetShowServlet?petPage=<%=petPage - 1%>">
    <img src="../images/LeftArrowHead.png" alt="" class="letfhead">
  </a>
  <% } %>
  <% if(petPage > 1 && petPage < petAllPage) {%>
  <a  href="<%=path%>/PetShowServlet?petPage=<%=petPage - 1%>">
    <img src="../images/LeftArrowHead.png" alt="" class="letfhead">
  </a>
  <a  href="<%=path%>/PetShowServlet?petPage=<%=petPage + 1%>">
    <img src="../images/RightArrowHead.jpg" alt="" class="righthead">
  </a>
  <%}%>
  <% }%>
</div>
</body>
<html>