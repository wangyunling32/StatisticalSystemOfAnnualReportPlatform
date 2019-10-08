<%@page import="java.sql.Array"%>
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
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<jsp:useBean id="user" class="com.indeed.model.User" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<script src="jquery-1.11.1.min.js">
	
</script>
<script type="text/javascript" src="requireForm_shu.js"></script>
<link href="requireForm_shu.css" type="text/css" rel="stylesheet" />
<meta charset="UTF-8">
<title>分类浏览</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="layui/css/layui.css" />
<script type="text/javascript"
	src="js/StandardIndustrialClassificationCodes.js"></script>
<script type="text/javascript" src="js/subjects.js"></script>
<script type="text/javascript" src="js/SimpleTree.js"></script>
<style>
body {
	background: url(img/index_backgro.jpg);
	background-attachment: fixed;
}

#content {
	background-color: rgba(238, 238, 238, 0.5);
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

<!--
#D3D3D3 #696969线条 #87CEFA #FFD700一层  black #9932CC字体 #ADD8E6 #FFB6C1二层  white #4B0082-->a:link,
	a:visited {
	text-decoration: none;
	color: black;
}

.list {
	width: 220px;
	border-bottom: solid 1px #D3D3D3;
	font-family: 宋体;
	float: left;
	margin-left: 10px
}

.list ul li {
	background-color: #87CEFA;
	border: solid 1px #D3D3D3;
	border-bottom: 0;
}

.list ul li a {
	padding-left: 10px;
	color: black;
	font-size: 12px;
	display: block;
	font-weight: bold;
	height: 25px;
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
	background-color: #ADD8E6;
	border-color: ##87CEFA;
}

.list ul li ul li ul {
	display: none;
}

.list ul li ul li a {
	padding-left: 20px;
}

.list ul li ul li ul li {
	background-color: white;
	border-color: black;
}

.last {
	background-color: white;
	border-color: black;
}

.list ul li ul li ul li a {
	color: black;
	padding-left: 40px;
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
<body background="img/123.jpg"
	style="background-repeat: no-repeat; background-size: 100%, 100%; background-attachment: fixed;">
	<div>
		<img src="img/index_01beifen.jpg" width="100%"><br>
		<br>
	</div>


	<br>
	<div>
		<a href="temp2.jsp"><input type="button"
			style="background-color: #87CEEB" value="后退"></a>

	</div>

	<form name="form1" action="requireForm_shu4.jsp" method="post">

		<td>
			<!-- 多条件查询 -->
			<div id="content"
				style="border-left: solid 1px #8a8a8a; margin-left: 0px; margin-top: 20px; float: right; width: 1050px">

				输入检索条件 ：<br />
				<ul>
					<div id="tiaojian">
						<li><input type="button" id="add" value="+" size="5" /> <input
							type="button" id="remove" value="-" size="5" /> ( <select
							name="type[0]">
								<option value="user_name" checked>填表人姓名</option>
								<option value="platfrom_name">平台名称</option>
								<option value="authorize_number">批准文号</option>
								<option value="support_name">依托单位名称</option>
								<option value="director_name">平台主任姓名</option>
								<option value="build_unit">共建单位</option>
						</select> <input type="text" name="content1[0]" size="20" /> <select
							name="way2[0]">
								<option value="and" checked>并含</option>
								<option value="or">或含</option>
								<option value="not">不含</option>
						</select> <input type="text" name="content2[0]" size="20" /> <select
							name="content3[0]">
								<option value="in" checked>精准</option>
								<option value="like">模糊</option>
						</select> )</li>
					</div>

				</ul>

				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					<input type="hidden" name="geshu" /> <input type="submit"
						id="submit" value="查询" style="background-color: #F4A460">
				</p>

			</div>
		<td>
		</tr>
	</form>


	<br>
	<form method="post" id="Search" style="">
		<div class="list">
			<ul class="yiji">
				<li><a href="#">分类查询</a></li>
				<script>
				$.each(SUBJECT[100], function(key,value) {
					document.write("<li><input type='checkbox' name='subjects' value='"+key+"' style=''><a href='#' class='inactive'>" + value + "</a>");
					document.write("<ul style='display:none'>");
					$.each(SUBJECT[key], function(key,value) {
						document.write("<li><input type='checkbox' name='subjects'  style='margin-left:10px' value='"+key+"'><a href='#' class='inactive active'>"+key+value+"</a>");
						document.write("<ul style='display:none'>");
						$.each(SUBJECT[key], function(key,value) {
							document.write("<li><input type='checkbox' name='subjects' value='"+key+"' style='margin-left:20px'><a href='#'>"+key+value+"</a></li>");
			
						});
						document.write("</ul>");
						document.write("</li>");
					});
					document.write("</ul>");
					document.write("</li>");
				});
			</script>

			</ul>
		</div>

		<%
	 PlatformDao platformdao = new PlatformDao();
	%>


		</div>
		<div
			style="border-left: solid 1px #8a8a8a; margin-left: 0px; margin-top: 20px; float: right; width: 1050px">

			<hr>
			<table style="width: 1000px;">
				<tr style="color: black; height: 30px;">

					<td>平台名称</td>
					<td>平台网站名称</td>
					<td>平台级别</td>
					<td>是否京冀共建</td>
					<td>批准年月</td>
					<td>审核状态</td>

				</tr>
				<tr style="color: black; height: 30px;">

					<td>spl</td>
					<td>spl</td>
					<td>省级</td>
					<td>否</td>
					<td>2018-06-13</td>
					<td>通过</td>
					<td><a href="content.jsp?Pid=<%=11%>"><input
							type="button" value="查看" style="float: right; color: #0000FF"></a></td>
				</tr>
				<tr style="color: black; height: 30px;">

					<td>mdzs</td>
					<td>博君一笑</td>
					<td>省级</td>
					<td>否</td>
					<td>2018-06-19</td>
					<td>未通过</td>
					<td><a href="content.jsp?Pid=<%=13%>"><input
							type="button" value="查看" style="float: right; color: #0000FF"></a></td>
				</tr>

			</table>


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