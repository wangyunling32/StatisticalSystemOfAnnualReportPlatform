<%@page import="com.indeed.dao.UserDaoImpl"%>
<%@page import="com.indeed.dao.PlatformDao"%>
<%@page import="com.indeed.dao.OrganizationDao"%>
<%@page import="com.indeed.dao.DeanDao"%>
<%@page import="com.indeed.model.Platform"%>
<%@page import="com.indeed.model.Dean"%>
<%@page import="com.indeed.model.Organization"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.indeed.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员界面</title>
</head>
<body background="img/123.jpg"
	style="background-repeat: no-repeat; background-size: 100%, 100%; background-attachment: fixed;">
	<div>
		<img src="img/index_01beifen.jpg" width="100%"><br>
		<br>
	</div>
	<div>
		<a href="yishenhe.jsp"><input type="button"
			style="background-color: #87CEEB" value="后退"></a>

	</div>
	<div
		style="width: 150px; border: solid 1px #316a91; height: 800px; float: left">
		<br> <br> <br> <a href="admin.jsp"><input
			type="button"
			style="background-color: #87CEFA; border-bottom: solid 1px #316a91; height: 35px; width: 150px; font-size: 20px"
			value="未审核"></a> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
	</div>
	<div
		style="border-left: solid 1px #8a8a8a; margin-left: 0px; margin-top: 20px; float: right; width: 1050px">

		<hr>
		<table style="width: 800px">

			<tr style="color: black; height: 20px;">
				<td>平台名称</td>
				<td>平台网站名称</td>
				<td>平台级别</td>
				<td>是否京冀共建</td>
				<!--  <td>审核状态</td>  -->
			</tr>
			<%
          PlatformDao platformDao=new PlatformDao();
          List<Platform> platforms=platformDao.loadAll();
          UserDaoImpl userDaoImpl=new UserDaoImpl();
          for(Platform platform:platforms)
          {
        	  if(platform.getStatus()==0)
        	  {
        	  if(platform.getGrade()==1)
        	  {
        		  if(platform.isJjjgj())
        		  {
        	%>
			<tr>
				<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><%=platform.getUser().getPlatform_name() %></a></td>
				<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><%=platform.getSitename() %></a></td>
				<td>国家级</td>
				<td>是</td>
				<td style="color: blue">待审核</td>
			</tr>

			<% 
        		  }
        		  else
        		  {
        			  %>
			<tr>
				<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><%=platform.getUser().getPlatform_name() %></a></td>
				<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><%=platform.getSitename() %></a></td>
				<td>国家级</td>
				<td>否</td>
				<td style="color: blue">待审核</td>
			</tr>

			<%
        		  }
        	  }
        	  else
        	  {
        		  if(platform.isJjjgj())
        		  {
        	%>
			<tr>
				<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><%=platform.getUser().getPlatform_name() %></a></td>
				<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><%=platform.getSitename() %></a></td>
				<td>省级</td>
				<td>是</td>
				<td style="color: blue">待审核</td>
			</tr>

			<% 
        		  }
        		  else
        		  {
        			  %>
			<tr>
				<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><%=platform.getUser().getPlatform_name() %></a></td>
				<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><%=platform.getSitename() %></a></td>
				<td>省级</td>
				<td>否</td>
				<td style="color: blue">待审核</td>
			</tr>

			<%
        		  } 
        	  }
          }
          }
          if(platforms.size()==0)
          {
       	 %>
			<h2 style="margin-left: 50px;">暂无待审核的信息</h2>
			<% 
          }
       %>
		</table>
	</div>
</body>
</html>