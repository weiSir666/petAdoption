<%--登陆界面--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
  <link rel="stylesheet" href="../css/login.css">
  <title>登录\注册</title>
</head>
<body class="background">
<div>
  <div class="border">
    <div class="mask">
      <div class="login_01">
        <div class="login_02">
          <div class="login_03">登录</div>
        </div>
      </div>
      <div class="register_01">
        <div class="register_02">
          <a href="register.jsp" class="register_03">注册</a>
        </div>
      </div>
      <form class="form" action="../userLoginServlet" name="frm01" id="frm01" method="post">
        <input type="text" class="form_01" name="username" id="username" placeholder="用户名">
        <br><br>
        <input type="text" class="form_02" name="password" id="password" placeholder="密码">
        <br><br>
<!--        <button class="button" id="btn" onclick="check()">登录</button>-->
        <input type="button" class="button" onclick="check()" value="登录">
        <br><br>
        <a href="adminLogin.jsp" class="button1">管理员登录</a>
        <div style="color:red; font-size: 20px">${msg1}</div>
      </form>

    </div>
  </div>
</div>
</body>
<script type="text/javascript">
  function check() {
    var name = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    let test = /^\S{1,}$/;
    if (!test.test(name)) {
      alert("用户名不能为空");
      return ;
    }
    else if (!test.test(password)) {
      alert("密码不能为空");
      return ;
    }
    else {
      document.getElementById("frm01").submit();
    }
  }
</script>
</html>