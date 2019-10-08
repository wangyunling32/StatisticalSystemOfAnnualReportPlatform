
<%@page import="com.indeed.dao.UserDaoImpl"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.indeed.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.indeed.dao.PlatformDao"%>
<%@page import="com.indeed.dao.OrganizationDao"%>
<%@page import="com.indeed.dao.DeanDao"%>
<%@page import="com.indeed.model.Platform"%>
<%@page import="com.indeed.model.Dean"%>
<%@page import="com.indeed.model.Organization"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<jsp:useBean id="user" class="com.indeed.model.User" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>分类浏览</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="layui/css/layui.css" />

<style>
body {
	background: url(img/index_backgro.jpg);
	background-attachment: fixed;
}

#content {
	
}

#content, #head {
	width: 1100px;
	margin-right: 0px;
	float: left;
}

#catagory li {
	font-size: 16px;
	margin-left: 10px;
}
</style>

<style type="text/css">
* {
	margin: 0;
	padding: 0
}

ul, li {
	list-style: none;
}

a:link, a:visited {
	text-decoration: none;
	color: #fff;
}

.list {
	width: 200px;
	border-bottom: solid 1px #316a91;
	font-family: 宋体;
	float: left;
	margin-left: 10px
}

.list ul li {
	background-color: #467ca2;
	border: solid 1px #316a91;
	border-bottom: 0;
}

.list ul li a {
	padding-left: 10px;
	color: #fff;
	font-size: 12px;
	display: block;
	font-weight: bold;
	height: 36px;
	line-height: 36px;
	position: relative;
}

.list ul li .inactive {
	background: url(images/off.png) no-repeat 184px center;
}

.list ul li .inactives {
	background: url(images/on.png) no-repeat 184px center;
}

.list ul li ul {
	display: none;
}

.list ul li ul li {
	border-left: 0;
	border-right: 0;
	background-color: #6196bb;
	border-color: #467ca2;
}

.list ul li ul li ul {
	display: none;
}

.list ul li ul li a {
	padding-left: 20px;
}

.list ul li ul li ul li {
	background-color: #d6e6f1;
	border-color: #6196bb;
}

.last {
	background-color: #d6e6f1;
	border-color: #6196bb;
}

.list ul li ul li ul li a {
	color: #316a91;
	padding-left: 30px;
}
</style>
<script type="text/javascript" src="jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.inactive').click(function(){
			if($(this).siblings('ul').css('display')=='none'){
				$(this).parent('li').siblings('li').removeClass('inactives');
				$(this).addClass('inactives');
				$(this).siblings('ul').slideDown(100).children('li');
				if($(this).parents('li').siblings('li').children('ul').css('display')=='block'){
					$(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
					$(this).parents('li').siblings('li').children('ul').slideUp(100);

				}
			}else{
				//控制自身变成+号
				$(this).removeClass('inactives');
				//控制自身菜单下子菜单隐藏
				$(this).siblings('ul').slideUp(100);
				//控制自身子菜单变成+号
				$(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
				//控制自身菜单下子菜单隐藏
				$(this).siblings('ul').children('li').children('ul').slideUp(100);

				//控制同级菜单只保持一个是展开的（-号显示）
				$(this).siblings('ul').children('li').children('a').removeClass('inactives');
			}
		})
	});
	</script>
</head>
<body onload="load()" style="">
	<div
		style="background-image: url(img/123.jpg); height: 130px; width: 100%">
		<br> <br>
		<p style="font-family: 楷体; font-size: 60px; margin-top: 0px"
			align="center">河北省创新年报平台</p>
	</div>
	<br>
	<div>
		<a href="admin.jsp"><input type="button"
			style="background-color: white" value="返回上一级"></a>
	</div>
	<br>
	<br>
	<div class="list">
		<ul class="yiji">
			<li><a href="#">分类查询</a></li>
			<li><a href="#" class="inactive">A 农、林、牧、渔业</a>
				<ul style="display: none">
					<li><a href="#" class="inactive active">01 农业</a>
						<ul>
							<li><a href="jieguo.jsp?hangye=011" value="011">011 谷物种植</a></li>
							<li><a href="jieguo.jsp?hangye=012">012 豆类、油料和薯类种植</a></li>
							<li><a href="jieguo.jsp?hangye=013">013 棉、麻、糖、烟草种植</a></li>
							<li><a href="jieguo.jsp?hangye=014">014 蔬菜、食用菌及园艺作物种植</a></li>
							<li><a href="jieguo.jsp?hangye=015">015 水果种植</a></li>
							<li><a href="jieguo.jsp?hangye=016">016 坚果、含油果、香料和饮料作物种</a></li>
							<li><a href="jieguo.jsp?hangye=017">017 中药材种植</a></li>
							<li><a href="jieguo.jsp?hangye=019">019 其他农业</a></li>
						</ul></li>
					<li><a href="#" class="inactive active">02 林业</a>
						<ul>
							<li><a href="jieguo.jsp?hangye=021">021 林木育种和育苗</a></li>
							<li><a href="jieguo.jsp?hangye=022">022 造林和更新</a></li>
							<li><a href="jieguo.jsp?hangye=023">023 森林经营和管护</a></li>
							<li><a href="jieguo.jsp?hangye=024">024 木材和竹材采运</a></li>
							<li><a href="jieguo.jsp?hangye=025">025 林产品采集</a></li>
						</ul></li>
					<li><a href="#" class="inactive active">03 畜牧业</a>
						<ul>
							<li><a href="jieguo.jsp?hangye=031">031 牲畜饲养</a></li>
							<li><a href="jieguo.jsp?hangye=032">032 家禽饲养</a></li>
							<li><a href="jieguo.jsp?hangye=033">033 狩猎和捕捉动物</a></li>
							<li><a href="jieguo.jsp?hangye=039">039 其他畜牧业</a></li>
						</ul></li>
					<li><a href="#" class="inactive active">04 渔业</a>
						<ul>
							<li><a href="jieguo.jsp?hangye=041">041 水产养殖</a></li>
							<li><a href="jieguo.jsp?hangye=042">042 水产捕捞</a></li>
						</ul></li>
					<li><a href="#" class="inactive active">05 农、林、牧、渔服务业</a>
						<ul>
							<li><a href="jieguo.jsp?hangye=051">051 农业服务业</a></li>
							<li><a href="jieguo.jsp?hangye=052">052 林业服务业</a></li>
							<li><a href="jieguo.jsp?hangye=053">053 畜牧服务业</a></li>
							<li><a href="jieguo.jsp?hangye=054">054 渔业服务业</a></li>
						</ul></li>
				</ul></li>
			<li><a href="#" class="inactive">B 采矿业</a>
				<ul style="display: none">
					<li><a href="#" class="inactive active">06 煤炭开采和洗选业</a>
						<ul>
							<li><a href="#">061 烟煤和无烟煤开采洗选</a></li>
							<li><a href="#">062 褐煤开采洗选</a></li>
							<li><a href="#">069 其他煤炭采选</a></li>
						</ul></li>
					<li><a href="#" class="inactive active">07 石油和天然气开采业</a>
						<ul>
							<li><a href="#">071 石油开采</a></li>
							<li><a href="#">072 天然气开采</a></li>
						</ul></li>
				</ul></li>
		</ul>
	</div>
	<div id="content">
		<div
			style="border-left: solid 1px #8a8a8a; margin-left: 0px; margin-top: 20px; float: right">
			<form id="search" class="layui-form" method="post">
				多条件查询：
				<div style="border: solid 1px #8a8a8a80; height: 39px;"
					class="mutiselect_box">
					<div class="layui-input-inline">
						平台级别： <input type="radio" name="platform_grade" id="country_grade"
							value="1" lay-skin="primary" />&nbsp;&nbsp;国家级&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="platform_grade" id="province_grade"
							value="2" lay-skin="primary" />&nbsp;&nbsp;省级
					</div>
					<div class="layui-input-inline">
						<input type="checkbox" name="jjjgj" id="country_grade" value="1"
							lay-skin="primary" />&nbsp;&nbsp;京津冀共建&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					批准年月：
					<div class="layui-input-inline">
						<input type="text" name="approval_date" id="approval_date"
							placeholder="如：1987-05-01" value="" class="layui-input"
							lay-verify="required|date" />
					</div>
					<div>
						<div class="layui-input-inline">
							<input type="text" name="name" class="layui-input" />
						</div>
						<div class="layui-input-inline">
							<input type="submit" value="查询" class="layui-btn" />
						</div>
					</div>
				</div>
				<div>
					<br>
					<table style="width: 1000px">
						<tr style="color: blue;">
							<td>平台名称</td>
							<td>平台网站名称</td>
							<td>平台级别</td>
							<td>批准年月</td>
							<td>是否京冀共建</td>
							<td>审核状态</td>
						</tr>
						<%
        String  jibie=request.getParameter("platform_grade");
        //System.out.print(jibie);
        int jibie1=0;
        if(jibie!=null)
        {
        	jibie1=Integer.valueOf(jibie);
        }
        
       String jjgj=request.getParameter("jjjgj");
       //System.out.print(jjgj);
       boolean jj=false;
       if(jjgj!=null)
       {
          jj=Boolean.valueOf("jjgj");
       }
       String date=request.getParameter("approval_date");
       System.out.println(date);
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       System.out.println(sdf);
       //Date date1 = new Date(sdf.parse("1900-01-01").getTime());
       
       //if(date!="0000-00-00")
       //{
        //  date1 = new Date(sdf.parse(date).getTime());
       //}
       String name=request.getParameter("name");
       String platform_name="";
       if(name!=null)
       {
    	  platform_name=name; 
       }
       String hangye=request.getParameter("hangye");
       //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       //System.out.print(sdf);
       //Timestamp time = new Timestamp(sdf.parse(date).getTime());
	    PlatformDao platformdao = new PlatformDao();  
	    UserDaoImpl userDaoImpl=new UserDaoImpl();
	    
	    if(jibie==null&&jjgj==null&&date==null&&name==null)
	    {
	    	List<Platform> platforms=platformdao.loadHangye(hangye);
	    	for(Platform platform:platforms)
	    	{
	    		 if(platform.isJjjgj())
	        	   {
	        		   if(platform.getGrade()==1){
	        			   
	        		   %>

						<tr>
							<td><%=platform.getUser().getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>国家级</td>
							<td><%=platform.getUser().getApprove_date() %></td>
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
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%}
	        	                else
	        	           {
	        		   %>

						<tr>
							<td><%=platform.getUser().getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>省级</td>
							<td><%=platform.getUser().getApprove_date() %></td>
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
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%}
	        		   //System.out.print(platform.getId());
	        	   }
	        	   else
	        	   { 
	 		           if(platform.getGrade()==1){
	        		   %>

						<tr>
							<td><%=platform.getUser().getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>国家级</td>
							<td><%=platform.getUser().getApprove_date() %></td>
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
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%}
	        	         else
	        	        {
	        		   %>

						<tr>
							<td><%=platform.getUser().getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>省级</td>
							<td><%=platform.getUser().getApprove_date() %></td>
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
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%
	        	       }
	 		       }
	       
	    }
	    }
	    if(jibie!=null&&jjgj!=null||date!=null||name!=null)
	    {
	    	List<Platform> platforms = platformdao.loadTiaojian(hangye, jibie1, jj, date, name);
	    	for(Platform platform:platforms)
	    	{
	    		if(platform.isJjjgj())
	        	   {
	        		   if(platform.getGrade()==1){
	        			   
	        		   %>

						<tr>
							<td><%=user.getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>国家级</td>
							<td><%=user.getApprove_date() %></td>
							<td>是</td>
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%}
	        	                else
	        	           {
	        		   %>

						<tr>
							<td><%=user.getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>省级</td>
							<td><%=user.getApprove_date() %></td>
							<td>是</td>
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%}
	        		   //System.out.print(platform.getId());
	        	   }
	        	   else
	        	   { 
	 		           if(platform.getGrade()==1){
	        		   %>

						<tr>
							<td><%=user.getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>国家级</td>
							<td><%=user.getApprove_date() %></td>
							<td>否</td>
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%}
	        	         else
	        	        {
	        		   %>

						<tr>
							<td><%=user.getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>省级</td>
							<td><%=user.getApprove_date() %></td>
							<td>否</td>
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%
	        	       }
	 		       }
	    	}
	    }
	    else
	    {
	    	System.out.println("yaoyao");
	    	List<Platform> platforms = platformdao.loadTiaojian1(hangye, jibie1, date, name);
	    	System.out.println("yaoyao");
	    	for(Platform platform:platforms)
	    	{
	    		if(platform.isJjjgj())
	        	   {
	        		   if(platform.getGrade()==1){
	        			   
	        		   %>

						<tr>
							<td><%=user.getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>国家级</td>
							<td><%=user.getApprove_date() %></td>
							<td>是</td>
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%}
	        	                else
	        	           {
	        		   %>

						<tr>
							<td><%=user.getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>省级</td>
							<td><%=user.getApprove_date() %></td>
							<td>是</td>
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%}
	        		   //System.out.print(platform.getId());
	        	   }
	        	   else
	        	   { 
	 		           if(platform.getGrade()==1){
	        		   %>

						<tr>
							<td><%=user.getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>国家级</td>
							<td><%=user.getApprove_date() %></td>
							<td>否</td>
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%}
	        	         else
	        	        {
	        		   %>

						<tr>
							<td><%=user.getPlatform_name() %></td>
							<td><%=platform.getSitename() %></td>
							<td>省级</td>
							<td><%=user.getApprove_date() %></td>
							<td>否</td>
							<td><a href="Look_up.jsp?Pid=<%=platform.getId() %>"><input
									type="button" value="查看详细内容"
									style="font-family: 楷体; float: right; color: green"></a></td>
						</tr>
						<%
	        	       }
	 		       }
	    	}
	    }
       %>
					</table>

				</div>
			</form>
		</div>






		<script type="text/javascript" src="layui/layui.js"></script>
		<script type="text/javascript">
	//加载form模块
	var form;
	
	layui.use('form',function () {
		form = layui.form;
	})
	//加载日期模块
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#approval_date' //指定元素
        });
    });
</script>


		<script type="text/javascript">

var clsNo;
var cslNameCurrent;

function expandTree(cls,lvl)
{
    var url = "cls_browsing_tree.php?s_doctype=" + document.getElementById('s_doctype').value + "&cls=" + cls + "&lvl=" + lvl+ "#node" + cls;
	top.window.tree.location.href= url;
}

function searchF(cls,clsName)
{
    clsNo = cls;
    clsNameCurrent = clsName;
    
	var url = "cls_browsing_book.php?s_doctype=" + document.getElementById('s_doctype').value + "&cls=" + cls + "&clsname=" + clsName ;
	top.window.main.location.href = url;
}

function changeClick()
{
    if(clsNo && clsNameCurrent)
    {
        var url = "cls_browsing_book.php?s_doctype=" + document.getElementById('s_doctype').value+ "&cls=" + clsNo + "&clsname=" + clsNameCurrent;
    	top.window.main.location.href = url;
    }
}
</script>
</body>
</html>