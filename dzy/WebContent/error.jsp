<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:useBean id="exception" class="java.lang.Exception" scope="request"></jsp:useBean>
<jsp:useBean id="err_msg" class="java.lang.String" scope="request"></jsp:useBean>
<html>
<head>
<title>错误</title>
</head>
<body>
	<h1 style="color: red"><%=err_msg%></h1>
	<article>
		<%if (exception!=null && exception.getMessage() != null){%>
		<%=exception.getMessage()%>
		<%}%>
	</article>
</body>
</html>
