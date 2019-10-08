<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.indeed.dao.PlatformDao"%>
<%@page import="com.indeed.dao.OrganizationDao"%>
<%@page import="com.indeed.dao.DeanDao"%>
<%@page import="com.indeed.model.Platform"%>
<%@page import="com.indeed.model.Dean"%>
<%@page import="com.indeed.model.Organization"%>
<%@page import="java.util.List"%>
<jsp:useBean id="user" class="com.indeed.model.User" scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/china.js"></script>
</head>
<body onload="load()">
	<div style="border: solid 1px #ff8040;">
		<br>
		<h1
			style="text-align: center; margin-top: 50px; font-family: 微软雅黑; color: black">河北科技创新平台年报统计表</h1>
		<br> <br> <br>
	</div>
	<div style="width: 200px; float: left">
		<br> <br>
		<h2>
			<a href="fill_in.jsp" style="color: blue">填报</a>
		</h2>
		<br> <br>
		<h2>
			<a href="select.jsp" style="color: blue">综合查询</a>
		</h2>
		<br> <br>
		<h2>
			<a href="fenlei.jsp" style="color: blue">分类查询</a>
		</h2>
	</div>
	<%
    
    OrganizationDao organizationdao=new OrganizationDao();
    DeanDao deandao = new DeanDao();
    String key="";
    String catagory=request.getParameter("catagory");
	%>
	<form action="select.jsp" method="post" class="layui-form"
		style="margin-left: 200px;">
		<div>
			<label class="layui-form-label">关键字</label>
			<div class="layui-input-block">
				<input type="text" name="key" lay-verify="required"
					placeholder="请输入标题" autocomplete="off" class="layui-input"
					value="<%=key %>" style="margin-top: 20px; width: 30%;">
			</div>
		</div>
		<div>
			<label class="layui-form-label">分类</label>
			<div class="layui-input-block" style="width: 28%;">
				<select name="catagory" lay-verify="required">
					<option value="依托单位名称">依托单位名称</option>
					<option value="平台主任姓名">平台主任姓名</option>
				</select>
			</div>
		</div>
		<input type="submit"
			style="margin-left: 350px; width: 100px; height: 30px" value="搜索">
		<% 
 System.out.print(catagory);
 if(catagory==null)
 {
	 System.out.print("啦啦啦"); 
	 catagory="依托单位";
 }
 
 if(catagory.equals("依托单位"))
 {
   List<Organization> organizations = organizationdao.load(request.getParameter("key"),user.getId());        
   for(Organization organization:organizations)
  {       
        %>
		<div class="layui-form-item">
			<form class="layui-form" action="PlatformSubmit" method="post"
				style="width: 1400px">
				<div style="margin-top: 20px;">
					<label class="lab_title" style="margin-left: 10%;">依托单位信息：</label>
				</div>
				<table class="layui-table tab middle_self">
					<thead>
						<tr>
							<td align="right" height="50" width="20%"><font class="must">*</font>依托单位名称：</td>
							<td><input type="text" name="dep_organization_name"
								id="dep_organization_name" value="<%=organization.getName() %>"
								class="tb_input" lay-verify="required" disabled="disabled" /></td>
							<td align="right"><font class="must">*</font>依托单位组织机构代码(社会信用代码)：</td>
							<td><input type="text" name="dep_organization_number"
								id="dep_organization_number"
								value="<%=organization.getUsci() %>" class="tb_input"
								lay-verify="required|usci" style="text-transform: uppercase"
								disabled="disabled" /></td>
						</tr>
						<tr>
							<td align="right" height="50"><font class="must">*</font>依托单位法人代表姓名：</td>
							<td><input type="text" name="dep_organization_faren"
								id="dep_organization_faren"
								value="<%=organization.getFarenName() %>" class="tb_input"
								lay-verify="required" disabled="disabled" /></td>
							<td align="right" height="50"><font class="must">*</font>办公电话：</td>
							<td><input type="text" name="dep_organization_phone"
								id="dep_organization_phone"
								value="<%=organization.getTelephone() %>" class="tb_input"
								lay-verify="require|telephone" disabled="disabled" /></td>
						</tr>
						<tr>
							<td align="right" height="50"><font class="must">*</font>依托单位类型：</td>
							<td colspan="3">
								<%
                  String type=organization.getType();
                  if(type.equals("enterprise"));
                  {
                %> <input type="text" class="tb_input" value="企业"
								disabled="disabled" /> <%
                  }
                  if(type.equals("science"))
                {
                %> <input type="text" class="tb_input" value="科研机构"
								disabled="disabled" /> <%
                  }
                  if(type.equals("university"))
                  {
                %> <input type="text" class="tb_input" value="高等院校"
								disabled="disabled" /> <%
                  }
                  if(type.equals("delection"))
                {
                %> <input type="text" class="tb_input" value="检测机构"
								disabled="disabled" /> <%
                  }
                  if(type.equals("medical"))
                {
                %> <input type="text" class="tb_input" value="医疗机构"
								disabled="disabled" /> <%
                  }
                  if(type.equals("goverment"))
                {
                %> <input type="text" class="tb_input" value="政府机构"
								disabled="disabled" /> <%
                  }
                  if(type.equals("association"))
                {
                %> <input type="text" class="tb_input" value="社团组织"
								disabled="disabled" /> <%
                  }
                  if(type.equals("orther"))
                {
                %> <input type="text" class="tb_input" value="其他"
								disabled="disabled" /> <%
                }
                %>
							</td>
						</tr>
					</thead>
				</table>
			</form>
		</div>
		<%
           }
           if(organizations.size()==0)
           {
        	 %>
		<h2 style="margin-left: 50px;">很遗憾，您找的信息不存在！</h2>
		<% 
           }
 }
 else 
 {
	 List<Dean> deans = deandao.load(request.getParameter("key"),user.getId());
	 for(Dean dean:deans)
	 {
    %>
		<div class="layui-form-item">
			<form class="layui-form" action="PlatformSubmit" method="post"
				style="width: 1400px">
				<div style="margin-top: 20px;">
					<label class="lab_title" style="margin-left: 10%;">平台主任信息：</label>
				</div>
				<table class="layui-table tab middle_self">
					<thead>
						<tr>
							<td rowspan="3" width="9%" align="center">平台主任(院长)</td>
							<td align="right" height="50" width="10%"><font class="must">*</font>姓名：</td>
							<td width="15%"><input type="text" name="dean_name"
								id="dean_name" value="<%=dean.getName() %>" class="tb_input"
								lay-verify="required" disabled="disabled" /></td>
							<td align="right" width="8%"><font class="must">*</font>性别：</td>
							<td width="9%">
								<%
                  String x=dean.getSex();
                  if(x.equals("male"))
                  {
                	  x="男";
                  }
                  else
                  {
                	  x="女";
                  }
                %> <input type="text" value="<%=x %>"
								disabled="disabled" class="tb_input" />
							</td>
							<td align="right" width="10%"><font class="must">*</font>出生年月：</td>
							<td align="right" width="15%"><input type="text"
								name="birthday" id="birthday" value="<%=dean.getDate() %>"
								disabled="disabled" class="layui-input"
								lay-verify="required|date" /></td>
							<td align="right" width="8%"><font class="must">*</font>职称：</td>
							<td align="right" width="16%"><input type="text"
								name="job_title" id="job_title" value="<%=dean.getJobTitle() %>"
								disabled="disabled" class="tb_input" lay-verify="required" /></td>
						</tr>
						<tr>
							<td align="right" height="50"><font class="must">*</font>所学专业：</td>
							<td colspan="2"><input type="text" name="major" id="major"
								value="<%=dean.getMajor() %>" disabled="disabled"
								class="tb_input" lay-verify="required" /></td>
							<td align="right" height="50"><font class="must">*</font>学历：</td>
							<td colspan="2"><input type="text" name="education"
								id="education" value="<%=dean.getEducation() %>"
								disabled="disabled" class="tb_input" lay-verify="required" /></td>
							<td align="right" height="50"><font class="must">*</font>学位：</td>
							<td><input type="text" name="degree" id="degree"
								value="<%=dean.getDegree() %>" disabled="disabled"
								class="tb_input" lay-verify="required" /></td>
						</tr>
						<tr>
							<td align="right" height="50"><font class="must">*</font>办公电话：</td>
							<td colspan="2"><input type="text" name="dean_telephone"
								id="dean_telephone" value="<%=dean.getTelephone() %>"
								disabled="disabled" class="tb_input"
								lay-verify="required|telephone" /></td>
							<td align="right" height="50"><font class="must">*</font>手机：</td>
							<td colspan="2"><input type="text" name="dean_mobilephone"
								id="dean_mobilephone" value="<%=dean.getMobilephone() %>"
								disabled="disabled" class="tb_input" lay-verify="required|phone" /></td>
							<td align="right" height="50"><font class="must">*</font>Email：</td>
							<td><input type="text" name="dean_email" id="dean_email"
								value="<%=dean.getEmail() %>" disabled="disabled"
								class="tb_input" lay-verify="required|email" /></td>
						</tr>
					</thead>
				</table>
			</form>
		</div>
		<%
 }
           if(deans.size()==0)
           {
        	 %>
		<h2 style="margin-left: 50px;">很遗憾，您找的信息不存在！</h2>
		<% 
           }
 }
 %>
	</form>
	<footer style="height: 20px"></footer>
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="js/china.js"></script>
	<script type="text/javascript" src="js/subjects.js"></script>
	<script type="text/javascript" src="js/demo.js"></script>
</body>
</html>