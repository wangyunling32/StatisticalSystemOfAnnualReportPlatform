<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:useBean id="msg" class="java.lang.String" scope="request"></jsp:useBean>
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
            layer.open(<%=msg%>);
        })
    </script>
</body>
</html>
