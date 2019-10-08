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
<jsp:useBean id="user" class="com.indeed.model.User" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tijiao</title>
</head>
<body background="img/123.jpg"
	style="background-repeat: no-repeat; background-size: 100%, 100%; background-attachment: fixed;">
	<div>
		<img src="img/index_01beifen.jpg" width="100%"><br>
		<br>
	</div>
	<div
		style="width: 150px; border: solid 1px #316a91; height: 800px; float: left">
		<br> <br> <br> <a href="fill_in.jsp"><input
			type="button"
			style="background-color: #467ca2; border-bottom: solid 1px #316a91; height: 35px; width: 150px; font-size: 20px"
			value="填表"></a> <a href="tijiao.jsp"><input type="button"
			style="background-color: #467ca2; border-bottom: solid 1px #316a91; height: 35px; width: 150px; font-size: 20px"
			value="待审核的"></a> <a href="User_yishenhe.jsp"><input
			type="button"
			style="background-color: #467ca2; border-bottom: solid 1px #316a91; height: 35px; width: 150px; font-size: 20px"
			value="已审核的"></a> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
	</div>
	<div style="margin-left: 200px">
		<table style="width: 1000px">
			<hr>
			<tr style="color: blue;">
				<td>平台名称</td>
				<td>平台网站名称</td>
				<td>平台级别</td>
				<td>是否京冀共建</td>
				<td>审核状态</td>
			</tr>
			<%
          PlatformDao platformDao=new PlatformDao();
          List<Platform> platforms=platformDao.load(user.getId());
          for(Platform platform:platforms)
          {
        	  if(platform.getStatus()!=0) 
        	  {
        	  if(platform.getGrade()==1)
        	  {
        		  if(platform.isJjjgj())
        		  {
        			  
        	%>
			<tr>
				<td><a href="content2.jsp?Pid=<%=platform.getId() %>"><%=user.getPlatform_name() %></a></td>
				<td><a href="content2.jsp?Pid=<%=platform.getId() %>"><%=platform.getSitename() %></a></td>
				<td>国家级</td>
				<td>是</td>
				<%
                                                                  if(platform.getStatus()==1)
                                                                  {
                                                                	  %>
				<td style="color: green">通过</td>
				<%
                                                                  }
                                                                  if(platform.getStatus()==2)
                                                                  {
                                                                	  %>
				<td style="color: red">未通过</td>
				<%
                                                                  }
                                                                %>
			</tr>

			<% 
        		  }
        		  else
        		  {
        			  %>
			<tr>
				<td><a href="content.jsp?Pid=<%=platform.getId() %>"><%=platform.getUser().getPlatform_name() %></a></td>
				<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><%=platform.getSitename() %></a></td>
				<td>国家级</td>
				<td>否</td>
				<%
                                                                  if(platform.getStatus()==1)
                                                                  {
                                                                	  %>
				<td style="color: green">通过</td>
				<%
                                                                  }
                                                                  if(platform.getStatus()==2)
                                                                  {
                                                                	  %>
				<td style="color: red">未通过</td>
				<%
                                                                  }
                                                                %>
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
				<td><a href="content2.jsp?Pid=<%=platform.getId() %>"><%=platform.getUser().getPlatform_name() %></a></td>
				<td><a href="content2.jsp?Pid=<%=platform.getId() %>"><%=platform.getSitename() %></a></td>
				<td>省级</td>
				<td>是</td>
				<%
                                                                  if(platform.getStatus()==1)
                                                                  {
                                                                	  %>
				<td style="color: green">通过</td>
				<%
                                                                  }
                                                                  if(platform.getStatus()==2)
                                                                  {
                                                                	  %>
				<td style="color: red">未通过</td>
				<%
                                                                  }
                                                                %>
			</tr>

			<% 
        		  }
        		  else
        		  {
        			  %>
			<tr>
				<td><a href="content2.jsp?Pid=<%=platform.getId() %>"><%=platform.getUser().getPlatform_name() %></a></td>
				<td><a href="content2.jsp?Pid=<%=platform.getId() %>"><%=platform.getSitename() %></a></td>
				<td>省级</td>
				<td>否</td>
				<%
                                                                  if(platform.getStatus()==1)
                                                                  {
                                                                	  %>
				<td style="color: green">通过</td>
				<%
                                                                  }
                                                                  if(platform.getStatus()==2)
                                                                  {
                                                                	  %>
				<td style="color: red">未通过</td>
				<%
                                                                  }
                                                                %>
			</tr>

			<%
        		  } 
        	  }
          }
          }
       %>
		</table>
	</div>
</body>
</html>