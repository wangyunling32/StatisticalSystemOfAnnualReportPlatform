<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.indeed.dao.PlatformDao"%>
<%@page import="com.indeed.dao.OrganizationDao"%>
<%@page import="com.indeed.dao.DeanDao"%>
<%@page import="com.indeed.model.Platform"%>
<%@page import="com.indeed.model.Dean"%>
<%@page import="com.indeed.model.Organization"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/china.js"></script>
</head>
<body onload="load()" background="img/123.jpg"
	style="background-repeat: no-repeat; background-size: 100%, 100%; background-attachment: fixed;">

	<div>
		<img src="img/index_01beifen.jpg" width="100%"><br>
		<br>
	</div>
	<%
	PlatformDao platformdao = new PlatformDao();
	%>
	<div width="90%" align="center">
		<% 
          
           String id=request.getParameter("Pid"); 
           int pid=Integer.valueOf(id);
           
          //System.out.print("输出"+pid);
          %>
		<%
           Platform platform=platformdao.loadPlatform(pid);   
          
        %>

		<div class="layui-form-item">

			<form style="border: solid 1px blue; width: 1001px">
				<table style="width: 1000px" border="1">
					<thead>
						<tr>
							<td align="center" height="50"><font class="must">*</font>平台名称：</td>
							<td align="center"><%=platform.getUser().getPlatform_name() %></td>
							<td align="center"><font class="must">*</font>平台编号：</td>
							<td align="center"><%=platform.getUser().getPlatform_number()%></td>
						</tr>
						<tr>
							<td align="center" height="50"><font class="must">*</font>批准年月：</td>
							<td align="center"><%=platform.getUser().getApprove_date()%></td>
							<td align="center"><font class="must">*</font>批准文号：</td>
							<td align="center"><%=platform.getUser().getApprove_number()%></td>
						</tr>
						<tr>
							<td align="center" height="50" colspan="1"><font
								class="must">*</font>技术领域：</td>
							<td colspan="3" align="center"><%=platform.getUser().getTechnosphere()%></td>
						</tr>
						<tr>
							<td align="center" height="50"><font class="must">*</font>平台级别：</td>
							<td>
								<%
                  int jibie=platform.getGrade();
                  String s="";
                  if(jibie==1)
                  {
                	  s="国家级";
                  }
                  else
                	  s="省级";
               %>
								<p align="center">
									<%=s %></p>
							</td>
							<td align="center"><font class="must">*</font>所在市县：</td>
							<td>
								<%
                  String adress=platform.getDistrict();
                  
                  
               %>
								<p align="center">
									<%=adress %></p>
							</td>
						</tr>

						<tr>
							<td rowspan="2" height="100" align="center"><font
								class="must">*</font>平台组织形态</td>
							<td height="50">
								<%
                int x1=platform.getForm();
                String ae="";
                if(x1==1)
                {
                   ae="内设机构相对独立";
                }
                else if(x1==2)
                {
                	ae="企业法人";
                }
                else if(x1==3)
                {
                	ae="事业法人";
                }
                else
                {
                	ae="社团法人";
                }
                %>
								<p align="center"><%=ae %></p>
							</td>
							<td>
								<%
                   boolean b=platform.isUnite();
                   if(b)
                   {
                %>
								<p align="center">多单位联合共建</p> <%
                   }
                else
                {
                %>
								<p align="center">多单位联合共建</p> <%} %>
							</td>
						</tr>
						<tr>
							<td>
								<%
                   boolean c=platform.isJjjgj();
                   if(c)
                   {
                %>
								<p align="center">京津冀共建</p> <%
                   }
                   else
                   {
                %>
								<p align="center">不是京津冀共建</p> <%
                   }
                %>

							</td>
						</tr>
						<tr>
							<td height="50" align="center">服务的主要国民经济行业</td>
							<td colspan="3" align="center"><%=platform.getIndustry() %>
							</td>
						</tr>

						<tr>
							<td align="center" height="50">所属的主要学科</td>
							<td colspan="3" align="center"><%=platform.getSubject() %></td>
						</tr>
					</thead>
				</table>
				<div style="margin-top: 20px;">
					<label class="lab_title" style="margin-left: 10%;">依托单位信息：</label>
				</div>
				<table style="width: 1000px" border="1">
					<thead>
						<tr>
							<td align="right" height="50" width="20%"><font class="must">*</font>依托单位名称：</td>
							<td><p align="center"><%=platform.getOrganization().getName() %></p></td>
							<td align="right"><font class="must">*</font>依托单位组织机构代码(社会信用代码)：</td>
							<td><p align="center"><%=platform.getOrganization().getUsci() %></p></td>
						</tr>
						<tr>
							<td align="right" height="50"><font class="must">*</font>依托单位法人代表姓名：</td>
							<td><p align="center"><%=platform.getOrganization().getFarenName() %></p></td>
							<td align="right" height="50"><font class="must">*</font>办公电话：</td>
							<td><p align="center"><%=platform.getOrganization().getTelephone() %></p></td>
						</tr>
						<tr>
							<td align="right" height="50"><font class="must">*</font>依托单位类型：</td>
							<td colspan="3">
								<%
                  String type=platform.getOrganization().getType();
                  if(type.equals("enterprise"));
                  {
                %>
								<p align="center">企业</p> <%
                  }
                  if(type.equals("science"))
                {
                %>
								<p align="center">科研机构</p> <%
                  }
                  if(type.equals("university"))
                  {
                %>
								<p align="center">高等院校</p> <%
                  }
                  if(type.equals("delection"))
                {
                %>
								<p align="center">检测机构</p> <%
                  }
                  if(type.equals("medical"))
                {
                %>
								<p align="center">医疗机构</p> <%
                  }
                  if(type.equals("goverment"))
                {
                %>
								<p align="center">政府机构</p> <%
                  }
                  if(type.equals("association"))
                {
                %>
								<p align="center">社团组织</p> <%
                  }
                  if(type.equals("orther"))
                {
                %>
								<p align="center">其他</p> <%
                }
                %>
							</td>
						</tr>
						<tr>
							<td align="right" height="50">共建单位：</td>
							<td colspan="3">
								<%
               String[] gongjian=platform.getGjdw();
            %>
								<p align="center"><%=gongjian %></p>
							</td>
						</tr>
					</thead>
				</table>
				<div style="margin-top: 20px;">
					<label class="lab_title" style="margin-left: 10%;">平台主任信息：</label>
				</div>
				<table style="width: 1000px" border="1">
					<thead>
						<tr>
							<td rowspan="3" width="9%" align="center">平台主任(院长)</td>
							<td align="right" height="50" width="10%"><font class="must">*</font>姓名：</td>
							<td width="15%"><%=platform.getDean().getName() %></td>
							<td align="right" width="8%"><font class="must">*</font>性别：</td>
							<td width="9%">
								<%
                  String x=platform.getDean().getSex();
                  if(x.equals("male"))
                  {
                	  x="男";
                  }
                  else
                  {
                	  x="女";
                  }
                %> <%=x %>
							</td>
							<td align="right" width="10%"><font class="must">*</font>出生年月：</td>
							<td align="right" width="15%"><%=platform.getDean().getDate() %></td>
							<td align="right" width="8%"><font class="must">*</font>职称：</td>
							<td align="right" width="16%"><%=platform.getDean().getJobTitle() %></td>
						</tr>
						<tr>
							<td align="right" height="50"><font class="must">*</font>所学专业：</td>
							<td colspan="2"><%=platform.getDean().getMajor() %></td>
							<td align="right" height="50"><font class="must">*</font>学历：</td>
							<td colspan="2"><%=platform.getDean().getEducation() %></td>
							<td align="right" height="50"><font class="must">*</font>学位：</td>
							<td><%=platform.getDean().getDegree() %></td>
						</tr>
						<tr>
							<td align="right" height="50"><font class="must">*</font>办公电话：</td>
							<td colspan="2"><%=platform.getDean().getTelephone() %></td>
							<td align="right" height="50"><font class="must">*</font>手机：</td>
							<td colspan="2"><%=platform.getDean().getMobilephone() %></td>
							<td align="right" height="50"><font class="must">*</font>Email：</td>
							<td><%=platform.getDean().getEmail() %></td>
						</tr>
					</thead>
				</table>
				<div style="margin-top: 20px;">
					<label class="lab_title" style="margin-left: 10%;">平台联系信息：</label>
				</div>
				<table style="width: 1000px" border="1">
					<thead>
						<tr>
							<td align="right" height="50" width="14%"><font class="must">*</font>平台网站名称：</td>
							<td width="43%"><%=platform.getSitename() %></td>
							<td align="right" width="14%"><font class="must">*</font>平台网站地址：</td>
							<td><%=platform.getWebsite() %></td>
						</tr>
						<tr>
							<td align="right" height="50"><font class="must">*</font>平台通讯地址：</td>
							<td><%=platform.getAddr() %></td>
							<td align="right">邮编：</td>
							<td><%=platform.getPostcode() %></td>
						</tr>
					</thead>
				</table>
			</form>
		</div>
		<footer style="height: 20px"></footer>
		<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
		<script type="text/javascript" src="js/china.js"></script>
		<script type="text/javascript" src="js/subjects.js"></script>
		<script type="text/javascript" src="js/demo.js"></script>
</body>
</html>