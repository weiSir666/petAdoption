<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="cn">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
  <link rel="stylesheet" href="../css/PersonalCenter.css">
  <title>个人中心</title>
</head>
<body>
<div class="background">
  <div class="bangs">
    <a href="mainPage2.jsp" class="LinkToMain">主页</a>
    <div class="personal">个人中心</div>
  </div>
  <img src="../images/head.webp" alt="头像" class="head">
  <img src="../images/2.webp" alt="照片" class="photo">
  <form class="form" action="../PersonalCenterUpdate" method="post" name="frm" id="frm">
    <div class="front " style="left: 0;right: 1602px;top: 20px;bottom: 789px;">用户名:</div>
    <input type="text" class="text" style="top: 20px;" name="username" id="username" value = "${sessionScope.RegisterPojoUserName}" readonly>

    <div class="front " style="left: 25px;right: 1577px;top: 100px;bottom: 714px;">姓名:</div>
    <input type="text" class="text" style="top: 100px;" name="realname" id="realname" value = "${sessionScope.RegisterPojoRealName}">

    <div class="front " style="left: 25px;right: 1577px;top: 180px;bottom: 647px;">密码:</div>
    <input type="text" class="text" style="top: 180px;" name="userpassword" id="userpassword" value = "${sessionScope.RegisterPojoUserPassword}">

    <div class="front " style="left: 25px;right: 1577px;top: 260px;bottom: 572px;">性别:</div>
    <input type="text" class="text" style="top: 260px;" name="sex" id="sex" placeholder="   男/女" value = "${sessionScope.RegisterPojoUserSex}">

    <div class="front " style="left: 25px;right: 1602px;top: 340px;bottom: 496.23px;">年龄:</div>
    <input type="number" class="text" style="top: 340px;" name="age" id="age" value = "${sessionScope.RegisterPojoUserAge}">

    <div class="front " style="left: 0;right: 1602px;top: 420px;bottom: 429px;">手机号:</div>
    <input type="number" class="text" style="top: 420px;" name="telphone" id="telphone" value = "${sessionScope.RegisterPojoUserPhone}">
    <span id="phone-number-hint" style="position: absolute; top: 440px; left: 770px; white-space: nowrap;"></span>

    <div class="front " style="left: 25px;right: 1577px;top: 500px;bottom: 365px;">地址:</div>
    <input type="text" class="text" style="top: 500px;" name="address" id="address" value = "${sessionScope.RegisterPojoUserPlace}">
    <input type="button" class="button_01" style="left: 40%;right: 75%;" value="更新信息" onclick="check()">
  </form>
  <button class="button_02" onclick="location.href='PersonAdoptInfo.jsp'" style="left: 5%;right: 87.29%;">查看领养申请</button>
  <button class="button_02" onclick="location.href='<%=path%>/userExit'" style="left: 33%;right: 62.71%;">退出登录</button>
</div>
</body>
<script>
  <%String updateflag = request.getParameter("updateflag");
  if(updateflag == null){
      //正常个人中心界面
      updateflag="";
  }
  if(updateflag.equals("1")){
      //显示更新成功
      out.println("alert('更新成功！')");
  }
  %>
</script>
</html>
<script type="text/javascript">
  // 获取 phone-number 元素和 phone-number-hint 元素
  var phoneNumberInput = document.getElementById("telphone");
  var phoneNumberHint = document.getElementById("phone-number-hint");

  // 添加事件监听器
  phoneNumberInput.addEventListener("input", function() {
    // 验证手机号是否正确
    var phoneNumber = phoneNumberInput.value;
    if (/^1[3456789]\d{9}$/.test(phoneNumber)) {
      // 输入正确时显示提示信息
      phoneNumberHint.textContent = "+86中国大陆";
      phoneNumberHint.style.color = "green";
    } else {
      // 输入错误时清空提示信息
      phoneNumberHint.textContent = "号码格式错误";
      phoneNumberHint.style.color = "red";
    }
  });
  function check(){
    //密码格式正则表达式，表示密码由6到9位数字和字母组成
    let pattern = /^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{6,9}$/;
    let age = /^\d+$/;
    let frm = document.getElementById("frm");
    let password = document.getElementById("userpassword").value;
    let sex = document.getElementById("sex").value;
    let userAge = document.getElementById("age").value;
    let userPhone = document.getElementById("telphone").value;
    if(!pattern.test(password)){
      alert("密码格式错误，密码由至少6位数字和字母组成！");
      return ;
    }
    if(!(sex === '男' || sex === '女')){
      alert("性别填 ‘男’/‘女’");
      return ;
    }
    if (!age.test(userAge)){
      alert("请输入整数年龄");
      return ;
    }
    if (!/^1[3456789]\d{9}$/.test(userPhone)){
      alert("电话格式错误，请输入正确的电话号码");
    }
    else{
      frm.submit();
    }
  }
</script>