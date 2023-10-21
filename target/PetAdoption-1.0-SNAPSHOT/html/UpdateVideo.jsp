<%--管理员管理视频界面--%>
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
  <title>管理员视频管理界面</title>
</head>
<style>
  form {
    position: relative;
    left: 500px;
    top: 100px;
  }
  input[type=submit]{
    position: relative;
    left: 180px;
  }
</style>
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
    <span class="userManagement15">视频管理</span>
  </div>
  <div class="userManagement16">
    <form method="post" action="../updateVideo" enctype="multipart/form-data">
      <span> 点击选择文件按钮，选择上传文件:</span>
      <input type="file" name="videoFile"><br><br>
      <input type="submit" value="提交">
    </form>
  </div>
</div>
</body>
<script>
  //用于判断管理员是否上传了文件，以免出现还未上传文件就删除了之前文件的情况。
  const fileInput = document.querySelector('input[type="file"]');
  const submitButton = document.querySelector('input[type="submit"]');

  submitButton.addEventListener('click', function(e) {
    if(fileInput.files.length === 0) {
      e.preventDefault(); // 阻止提交表单
      alert('请先选择要上传的文件！');
    }
  });
</script>
</html>