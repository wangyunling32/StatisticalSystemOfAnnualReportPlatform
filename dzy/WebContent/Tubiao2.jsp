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

	<div>
		<img src="img/index_01beifen.jpg" width="100%"><br>
		<br>
	</div>

	<div
		style="width: 150px; border: solid 1px #316a91; height: 800px; float: left">
		<div>
			<a href="temp2.jsp"><input type="button"
				style="background-color: #87CEEB" value="后退"></a>
		</div>
		<br> <br> <br>
		<!--  <a href="Tubiao.jsp"><input type="button" style="background-color:#467ca2;border-bottom:solid 1px #316a91;height:35px;width:150px;font-size:20px" value="审核饼状图"></a>
      <a href="Tubiao2.jsp"><input type="button" style="background-color:#467ca2;border-bottom:solid 1px #316a91;height:35px;width:150px;font-size:20px" value="已审核的"></a>  -->
		<select name="dgLink2" id="dgLink2" style="WIDTH: 150px"
			onchange="MM_jumpMenu('parent',this,0)">
			<option value="#">－－－饼状图－－－</option>
			<option value="Tubiao.jsp">组织形态</option>
			<option value="Tubiao2.jsp">京津冀共建</option>
		</select> <br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br>
	</div>

	<%
        PlatformDao platformDao=new PlatformDao();
        List<Platform> platforms=platformDao.loadAll();
        int weishenhe=0;
        int tongguo=0;
        int butongguo=0;
        int jjjgj=0;
        int unjjjgj=0;		
        for(Platform platform:platforms)
        {
        	if(platform.getStatus()==1)
        	{
        		tongguo++;
        	}
        	if(platform.getStatus()==2)
        	{
        		butongguo++;
        	}
        	if(platform.getStatus()==0)
        	{
        		weishenhe++;
        	}
        	if(platform.isJjjgj())
        	{
        		jjjgj++;
        	}
        	if(platform.isJjjgj()==false)
        	{
        		unjjjgj++;
        	}
        }
        double a=(double)tongguo;
        double c=(double)weishenhe;
        double b=(double)butongguo;
        double all=a+b+c;
        double a1=a/all;
        double b1=b/all;
        double c1=c/all;
        double jjjgj1=(double)jjjgj;
        double unjjjgj1=(double)unjjjgj;
        double jjjgj2=jjjgj1/all;
        double unjjjgj2=unjjjgj1/all;
        System.out.println(a1);
     %>
	<div
		style="margin-left: 0px; margin-top: 20px; float: right; width: 1050px">
		<input type="hidden" id="jjjgj" value="<%=jjjgj2 %>" /> <input
			type="hidden" id="unjjjgj" value="<%=unjjjgj2 %>" />
		<div>
			<canvas id="canvas_circle" width="600" height="400"> </canvas>
			<p style="margin-left: 140px">京津冀共建饼状图</p>
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
                var a = document.getElementById('jjjgj').value;
                var b = document.getElementById("unjjjgj").value;
                var a1=parseFloat(a);
                var b1=parseFloat(b);
                var data_arr = [a1,b1];    
                var color_arr = ["GREEN",'RED'];    
                var text_arr = ["是", "否"];    
    
                drawCircle("canvas_circle", data_arr, color_arr, text_arr);    
            }    
    
            //页面加载时执行init()函数    
            window.onload = init;    
        </script>
	</div>
</body>
</html>