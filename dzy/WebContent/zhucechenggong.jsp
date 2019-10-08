<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:useBean id="zhuce" class="java.lang.String" scope="request"></jsp:useBean>
<html>
<head>
<link rel="stylesheet" href="layui/css/layui.css" />
<script type="text/javascript" src="layui/layui.js"></script>
<title>提示信息</title>
</head>
<body>
	<script>
        layui.use("layer",function(){
            var layer = layui.layer;
            layer.open(<%=zhuce%>);
        })
    </script>
	<h1 style="align: center; font-family: 微软雅黑; color: yellow">
		注册成功！去<a style="text-decoration: none;" href='login.jsp'
			onclick='showRegist()'>登录</a>
	</h1>
</body>
</html>
