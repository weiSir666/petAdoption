<%--注册界面--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
  <link rel="stylesheet" href="../css/register.css">
  <title>登录\注册</title>
</head>

<body class="background">
<div>
  <div class="border">
    <div class="mask">
      <div class="login_01">
        <div class="login_02">
          <div class="login_03">注册</div>
        </div>
      </div>
      <div class="register_01">
        <div class="register_02">
          <a href="login.jsp" class="register_03">登录</a>
        </div>
      </div>
      <form name="form" id="form" class="form" action="../RegisterController" method="post">
        <input type="text" name="username" id="username" class="form_01" placeholder="用户名" onkeyup="checkname()" >
        <span id="name"></span>
        <br><br>
        <input type="password" name="userpassword" id="p1" class="form_02" placeholder="密码" onkeyup="password()">
        <span id="pw"></span>
        <br><br>
        <input type="password" name="userpassword2" id="p2" class="form_02" placeholder="重复输入一次密码" onkeyup="checkpassword()">
        <span id="tishi"></span>
        <br><br>
        <input type="button" name="注册" id="button" class="button" value="注册" onclick="check()">
      </form>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
  //1.判断输入用户名是否为空
  function checkname(){
    let username = /^\S{1,}$/;
    let name=document.getElementById("username").value;
    //判断是否满足用户名条件，不满足就禁用button
    if(!username.test(name)){
      document.getElementById("name").innerHTML="<br><font color='red'>用户名格式错误</font>";
      document.getElementById("button").disabled = true;
    }
    //满足就解除禁用
    else{
      document.getElementById("name").innerHTML="<br><font color='green'>用户名格式正确</font>";
      document.getElementById("button").disabled = false;
    }
  }
  //2.判断是否输入密码
  function password(){
    let pw = /^\S{1,}$/;
    let password = document.getElementById("p1").value;
    if(!pw.test(password)||password==null){
      document.getElementById("pw").innerHTML="<br><font color='red'>密码格式错误</font>";
      document.getElementById("button").disabled = true;
    }
    else{
      document.getElementById("pw").innerHTML="<br><font color='green'>密码格式正确</font>";
      document.getElementById("button").disabled = false;
    }
  }
  //3判断两次密码是否一致
  function checkpassword(){
    let userpassword = document.getElementById("p1").value;
    let userpassword2 = document.getElementById("p2").value;
    if(userpassword === userpassword2){
      document.getElementById("tishi").innerHTML="<br><font color='green'>两次输入密码一致</font>";
      document.getElementById("button").disabled = false;
    }
    else{
      document.getElementById("tishi").innerHTML="<br><font color='red'>两次输入密码不一致</font>";
      document.getElementById("button").disabled = true;
    }
  }
  function check(){
    let frm = document.getElementById("form");
    let re = /^\S{1,}$/;
    let uname = document.getElementById("username").value;
    let upassword = document.getElementById("p1").value;
    let upassword2 = document.getElementById("p2").value;
    if(!re.test(uname)){
      alert("用户名不能为空！");
      return ;
    }
    if(!re.test(upassword)){
      alert("密码不能为空！");
      return ;
    }
    if(!re.test(upassword2)){
      alert("请重复输入一遍密码！");
      return ;
    }
    else{
      frm.submit();
    }

  }
</script>
<script>
  <%
      String flag = request.getParameter("flag");
      if(flag==null){
          flag="";
      }
      if(flag.equals("1")){
          out.println("alert('用户"+session.getAttribute("username")+"已经存在')");
      }
  %>
</script>
</html>