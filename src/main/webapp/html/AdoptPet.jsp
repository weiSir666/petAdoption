<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.User" %>
<%
    String path = request.getContextPath();
    HttpSession session1 = request.getSession();
    String userName = (String) session1.getAttribute("RegisterPojoUserName");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/adoptPet.css">
    <title>宠物信息</title>
</head>
<body>
<div class="background">
    <div class="rectangle">
        <%if(userName != null){%>
      <a href="../html/mainPage2.jsp" class="mainindex">主页</a>
        <%}else {%>
      <a href="../html/mainPage.jsp" class="mainindex">主页</a>
        <%}%>
      <a href="<%=path%>/PetShowServlet?petPage=1" class="adoptindexcenter">领养中心</a>
        <div class="username textstyle">${sessionScope.RegisterPojoUserName}</div>
  </div>
    <img src="${sessionScope.ImgUrl}" alt="宠物图片" class="imgcat">
    <div class="id">编号</div>
    

    <img src="../images/birthcake.png" alt="生日蛋糕" class="birthcake">
  <div class="birth">生日</div>

    <img src="../images/R-Cd.jpg" alt="求收养" class="please">

    <img src="../images/R-CA.png" alt="名字" class="type">
  <div class="typetxt">名字</div>
    

    <img src="../images/sex.png" alt="性别" class="seximg">
  <div class="sextext">性别</div>


    <%
        String imgUrl = request.getParameter("imgUrl");
        int id = (int) session1.getAttribute("PetId");
    %>
    <form action="#">
        <input type="text" class="idtext" name="PetId" value="${sessionScope.PetId}" readonly>
        <input type="text" class="typetext" name="PetType" value="${sessionScope.PetType}" readonly>
        <input type="text" class="sex3" name="PetSex" value="${sessionScope.PetSex}" readonly>

        <%if(userName != null){%>
      <div class="adopt"><div class="adoptback"> <a href="adopApply.jsp?PetId=<%=id%>&imgUrl=<%=imgUrl%>" + class="adoptindex">想要领养</a></div> </div>
        <%}%>
    </form>


    
    <div class="back">
        <button class="backcenter textstyle" onclick="location.href='<%=path%>/PetShowServlet?petPage=1'" value="返回中心">返回中心</button>
    </div>


    <img src="../images/R-Ce.png" alt="评论图片" class="commentimg">
        <form action="../SubmitCommentServlet" method="post">
            <input type="text" class="commenttext" name="comment" id="comment" placeholder="谈谈你的看法吧!">

            <input type="hidden" name="user_id" value="${sessionScope.RegisterPojoUserId}">
            <input type="hidden" name="pet_id" value="${sessionScope.PetId}">
            <input type="hidden" name="user_name" value="${sessionScope.RegisterPojoUserName}">

            <%if(userName != null){%>
            <button class="comback textstyle" onclick="check()" style="" value="提交评论">提交评论</button>
            <%}%>
        </form>


</div>

</body>
<script type="text/javascript">
    function check() {
        var comment = document.getElementById("comment").value;
        if (comment==null) {
            alert("评论不能为空");
            return ;
        }
    }
</script>
</html>