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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<title>Insert title here</title>
<script type="text/JavaScript">
function MM_jumpMenu(targ,selObj,restore){ 
 eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
 if (restore) selObj.selectedIndex=0;
}
</script>
</head>
<body background="img/123.jpg"
	style="background-repeat: no-repeat; background-size: 100%, 100%; background-attachment: fixed;">

	<!--  <div style="background-image:url(img/index_01beifen.jpg); background-size:cover;height:130px;width:100%">
  <br>
  <br>
  </div>  -->
	<div>
		<img src="img/index_01beifen.jpg" width="100%"><br>
		<br>
	</div>
	<div>
		<a href="temp2.jsp"><input type="button"
			style="background-color: #87CEEB" value="后退"></a>

	</div>
	<div
		style="width: 150px; border: solid 1px #316a91; height: 800px; float: left">

		<br> <br> <br>
		<!--  <a href="Tubiao.jsp"><input type="button" style="background-color:#467ca2;border-bottom:solid 1px #316a91;height:35px;width:150px;font-size:20px" value="组织形态饼状图"></a> -->
		<select name="dgLink2" id="dgLink2" style="WIDTH: 150px"
			onchange="MM_jumpMenu('parent',this,0)">
			<option value="#">－－－饼状图－－－</option>
			<option value="Tubiao.jsp">组织形态</option>
			<option value="Tubiao2.jsp">京津冀共建</option>
		</select> <br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br>
	</div>

	<%
        PlatformDao platformDao=new PlatformDao();
        List<Platform> platforms=platformDao.loadAll();
        int neishe=0;
        int qiye=0;
        int shiye=0;
        int shetuan=0;		
        for(Platform platform:platforms)
        {
        	if(platform.getForm()==1)
        	{
        		neishe++;
        	}
        	if(platform.getForm()==2)
        	{
        		qiye++;
        	}
        	if(platform.getForm()==3)
        	{
        		shiye++;
        	}
        	if(platform.getForm()==4)
        	{
        		shetuan++;
        	}
        	
        }
        double a=(double)neishe;
        double c=(double)qiye;
        double b=(double)shiye;
        double e=(double)shetuan;
        double all=a+b+c+e;
        double a1=a/all;
        double b1=b/all;
        double c1=c/all;
        double e1=e/all;

     %>
	<div
		style="margin-left: 0px; margin-top: 20px; float: right; width: 1050px">
		<input type="hidden" name="pass1" id="pass1" value="<%=a1 %>" /> <input
			type="hidden" name="pass2" id="pass2" value="<%=b1 %>" /> <input
			type="hidden" name="pass3" id="pass3" value="<%=c1 %>" /> <input
			type="hidden" name="pass4" id="pass4" value="<%=e1 %>" />
		<div>
			<canvas id="canvas_circle" width="600" height="400"> </canvas>
			<p style="margin-left: 140px">组织形态比例图</p>
		</div>
		<script type="text/javascript">    
            //绘制饼图    
            function drawCircle(canvasId, data_arr, color_arr, text_arr)    
            {    
                var c = document.getElementById(canvasId);    
                var ctx = c.getContext("2d");    
    
                var radius = c.height / 2 - 20; //半径    
                var ox = radius + 20, oy = radius + 20; //圆心    
    
                var width = 30, height = 10; //图例宽和高    
                var posX = ox * 2 + 20, posY = 30;   //    
                var textX = posX + width + 5, textY = posY + 10;    
    
                var startAngle = 0; //起始弧度    
                var endAngle = 0;   //结束弧度    
                for (var i = 0; i < data_arr.length; i++)    
                {    
                    //绘制饼图    
                    endAngle = endAngle + data_arr[i] * Math.PI * 2; //结束弧度    
                    ctx.fillStyle = color_arr[i];    
                    ctx.beginPath();    
                    ctx.moveTo(ox, oy); //移动到到圆心    
                    ctx.arc(ox, oy, radius, startAngle, endAngle, false);    
                    ctx.closePath();    
                    ctx.fill();    
                    startAngle = endAngle; //设置起始弧度    
    
                    //绘制比例图及文字    
                    ctx.fillStyle = color_arr[i];    
                    ctx.fillRect(posX, posY + 20 * i, width, height);    
                    ctx.moveTo(posX, posY + 20 * i);    
                    ctx.font = 'bold 12px 微软雅黑';    //斜体 30像素 微软雅黑字体    
                    ctx.fillStyle = color_arr[i]; //"#000000";    
                    var percent = text_arr[i] + "：" + 100 * data_arr[i] + "%";    
                    ctx.fillText(percent, textX, textY + 20 * i);    
                }    
            }    
    
            function init() {    
                //绘制饼图    
                //比例数据和颜色   
                var a = document.getElementById('pass1').value;
                var b = document.getElementById("pass2").value;
                var c = document.getElementById("pass3").value;
                var e = document.getElementById("pass4").value;
                var a1=parseFloat(a);
                var b1=parseFloat(b);
                var c1=parseFloat(c);
                var e1=parseFloat(e);
                var data_arr = [a1,b1,c1,e1];    
                var color_arr = ["GREEN",'RED','yellow','blue'];    
                var text_arr = ["内设机构独立", "企业法人", "事业法人","社团法人"];    
    
                drawCircle("canvas_circle", data_arr, color_arr, text_arr);    
            }    
    
            //页面加载时执行init()函数    
            window.onload = init;    
        </script>
</body>
</html>