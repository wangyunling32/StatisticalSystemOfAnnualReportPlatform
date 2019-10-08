<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录界面</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="css/login.css" rel="stylesheet" type="text/css">
    <link href="layui/css/layui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="js/demo.js" ></script>
    <style type="text/css">
body{
	background-image: url(images/index_03.jpg);
	background-repeat:no-repeat;
	background-size:cover;
}
</style>
</head>
<body>
<div><img  src="img/index_01beifen.jpg" width="100%"><br><br></div>
<!--  <h1 style="font-family:微软雅黑;margin-top:9px;color:black;text-align:center;">河北省科技创新平台年报系统</h1>  -->
<div id="loginbox">
    <div>
        <form action="Login" style="text-align: center;" method="post">
            <input type="text" class="logininput" name="username" style="margin-top:90px;background-image:url('image/person.jpg')" placeholder="用户名" required><br />
            <input type="password" class="logininput" name="password" style="margin-top:10px;background-image:url('image/password.jpg')" placeholder="密码" required><br />
            <div class="logininput" style="margin-left:auto;margin-right:auto;margin-top:20px;">
                <input id="pass" type="checkbox" class="checkbox" name="remberpass" style="float:left">
                
                <label for="pass" style="position: relative;right: 65px;">记住密码</label>
                <br>
                <br>
                <input type="submit" class="loginbutton" value="登录" style="float:center">
                <br>
                <br>
                <p class="tip">还没有账号？点击<a style="text-decoration: none;" href='register.jsp' onclick='showRegist()'>注册</a></p>
            </div><br />
        </form>
    </div>
</div>
</body>
</html>