<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="cn">
<head>
  <!-- 主页内容 -->
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>宠物领养系统-主页(已登录)</title>
  <link rel="stylesheet" href="../css/mainPage.css">
</head>
<style>
  @keyframes carousel {
    0%,20%{
      background: url("../images/R-C.jpg") center / 100% 100% no-repeat;
    }
    30%,50%{
      background: url("../images/3.webp") center / 100% 100% no-repeat;
    }
    60%,80%{
      background: url("../images/4.jpg") center / 100% 100% no-repeat;
    }
    90%,100%{
      background: url("../images/R-C.jpg") center / 100% 100% no-repeat;
    }
  }
</style>
<body class="Backgroundimage01">
<div class="mainPageRectangle01">
  <div class="mainPageFont01">主页</div>
  <a href="../html/petKnowledge2.html" class="mainPageFont02">宠物知识</a>
  <a href="<%=path%>/PetShowServlet?petPage=1" class="mainPageFont03">领养中心</a>
  <a href="PersonalCenter.jsp" class="mainPageFont04">个人中心</a>
  <div class="mainPageFont05">
    <!-- 查询宠物表单 -->
    <form action="../PetFindServlet2" name = "form01" id = "form01">
      宠物查找:<input type="text" id="petType" name="petType" placeholder="输入宠物类型名称" style="width: 200px; height: 50px;" value="">
      <input type="submit" value="查找" style="height: 50px;">
    </form>
  </div>
  <div class="mainPageLogin01">
    <div class="mainPageLogin02">
      <a href="<%=path%>/userExit" class="mainPageLogin03">退出登录</a>
    </div>
  </div>
  <div class="mainPageFont06"> <span style="color: aquamarine;">欢迎</span>来到宠物领养平台</div>
  <div class="buttonSp">
    <input type="button" class="buttonSp_02" value="宠物公益视频" onclick="openVideo()">
  </div>
</div>
<div id="video-popup" style="position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); display: none;">
  <video width="640" height="360" controls>
    <source src="../video/1.mp4" type="video/mp4">
  </video>
  <button onclick="closeVideo()" class="close-btn">X</button>
</div>
</body>
<script type="text/javascript">
  function openVideo() {
    let popup = document.getElementById("video-popup");
    popup.style.display = "block";
  }
  function closeVideo() {
    let popup = document.getElementById("video-popup");
    popup.style.display = "none";
  }
</script>
<script>
  <%
  String petId = request.getParameter("pet_Id");
  if(petId == null){
      //正常访问登录页面
      petId = "";
  }
  if(petId.equals("0")){
      out.println("alert('未查找到该宠物')");
  }
  %>
</script>
<html>