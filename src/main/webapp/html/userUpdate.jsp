<%--编辑用户信息界面--%>
<%@ page import="pojo.User" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../css/userUpdate.css">
  <title>用户信息</title>
</head>
<%
  HttpSession session1 = request.getSession();
  User user = (User) session1.getAttribute("userUpdate_UserInformation");
  String path = request.getContextPath();
  String userAge = null;
  String userPhone = null;
  //取int类型数据判断是否为空
  if (user.getUserAge() == 0) {
    userAge = "用户未填写";
  }
  else {
    userAge = String.valueOf(user.getUserAge());
  }
%>
<body class="background">
<!-- 顶部褐色框 -->
<div class="userUpdate01">
  <a href="<%=path%>/adminManageUserServlet?pageNow=1"  class="userUpdate02">返回</a>
  <div class="userUpdate03" style="left: 670px;">用户信息</div>
</div>
<form action="<%=path%>/adminUpdateUserServlet1" method="post" id="userUpdateForm">
  <!-- 用户信息 -->
  <input type="hidden" name="userUpdateUserId" id="userId" value="<%=user.getUserId()%>">
  <div class="userUpdate04">用户名：</div>
  <input type="text" class="userUpdate05" id="userUpdateUserName" name="userUpdateUserName" value="<%=user.getUserName()%>" readonly>
  <div class="userUpdate04" style="top: 170px;">真实名:</div>
  <input type="text" class="userUpdate05" style="top: 180px;" id="userUpdateRealName" name="userUpdateRealName" value="<%=user.getRealName()%>">
  <div class="userUpdate04" style="top: 240px;">密码：</div>
  <input type="text" class="userUpdate05" style="top: 250px;" id="userUpdateUserPassword" name="userUpdateUserPassword" value="<%=user.getUserPassword()%>">
  <div class="userUpdate04" style="top: 310px;">年龄：</div>
  <input type="text" class="userUpdate05" style="top: 320px;" id="userUpdateUserAge" name="userUpdateUserAge" value="<%=userAge%>">
  <div class="userUpdate04" style="top: 380px;">电话：</div>
  <input type="text" class="userUpdate05" style="top: 390px;" id="userUpdateUserPhone" name="userUpdateUserPhone" value="<%=user.getUserPhone()%>">
  <div class="userUpdate04" style="top: 450px;">地址：</div>
  <input type="text" class="userUpdate05" style="top: 460px;" id="userUpdateUserAddress" name="userUpdateUserAddress" value="<%=user.getPlace()%>">
  <div class="userUpdate04" style="top: 520px;">性别：</div>
  <input type="text" class="userUpdate05" style="top: 530px;" id="userUpdateUserSex" name="userUpdateUserSex" value="<%=user.getUserSex()%>">
  <input type="button" class="userUpdate06" value="更新" onclick="userUpdateCheck()" id="userUpdateButton01">
  <input type="button" class="userUpdate06" style="left: 650px;" onclick="location.href='<%=path%>/adminManageUserServlet?pageNow=1'" value="返回上一页">
</form>
</body>
<script language="JavaScript">
  /**
   * 该方法用于判断管理员更新用户信息的时候格式的正确性
   */
  function userUpdateCheck() {
    let pw = /^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{6,9}$/; //密码格式正则表达式，表示密码由6到9位数字和字母组成
    let phoneTest = /^(1\d{10}|\d)$/;    //电话规范，号码必须11位且第一个数字必须为1，或者不填
    let userPwd = document.getElementById("userUpdateUserPassword").value;
    let userPhone = document.getElementById("userUpdateUserPhone").value;
    let userAge = document.getElementById("userUpdateUserAge").value;
    let userSex = document.getElementById("userUpdateUserSex").value;
    let userRealName = document.getElementById("userUpdateRealName").value;
    let userAddress = document.getElementById("userUpdateUserAddress").value;

    let userUpdateForm = document.getElementById("userUpdateForm");
    // 判断密码格式
    if (!pw.test(userPwd)) {
      alert("密码格式错误（密码必须由6到9位数字和字母组成）");
      return ;
    }
    // 判断电话格式
    if (userPhone != "用户未填写" && userPhone != "") {
      if (!phoneTest.test(userPhone)) {
        alert("电话必须由首位数字位1的11位数字组成");
        return ;
      }
    }
    // 判断年龄格式
    if (userAge != "用户未填写" && userAge != "") {
      if (userAge > 130 || userAge < 1) {
        alert("用户年龄必须在1-130之间");
        return ;
      }
      else {
        let num = parseInt(userAge);
        if (!(num.toString() == userAge)) {
          alert("年龄必须为整数");
          return ;
        }
      }
    }
    // 判断性别格式
    if (userSex != "用户未填写" && userSex != "") {
      if (userSex != "男" && userSex != "女") {
        alert("性别必须为男或者女");
        return ;
      }
    }
    // 判断地址格式
    if (userAddress.length > 50) {
      alert("地址长度超出范围");
      return ;
    }
    // 判断真实姓名长度
    if (userRealName.length > 10) {
      alert("真实姓名长度超出范围");
      return ;
    }
    userUpdateForm.submit();
  }
</script>
</html>