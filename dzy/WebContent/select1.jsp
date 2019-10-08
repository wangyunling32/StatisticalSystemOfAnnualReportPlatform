
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta charset="UTF-8">
<title>分类浏览</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="layui/css/layui.css" />
</head>
<style>
body {
	background: url(img/index_backgro.jpg);
	background-attachment: fixed;
}

#content {
	background-color: rgba(238, 238, 238, 0.5);
}

#content, #head {
	width: 90%;
	margin-left: auto;
	margin-right: auto;
	margin-top: 30px;
}

#catagory li {
	font-size: 16px;
	margin-left: 10px;
}
</style>

<style type="text/css">
.stepright1 {
	padding-left: 5px;
}

.stepright2 {
	padding-left: 15px;
}

.stepright3 {
	padding-left: 25px;
}

.stepright4 {
	padding-left: 35px;
}

.stepright5 {
	padding-left: 45px;
}

.stepright6 {
	padding-left: 55px;
}

.stepright7 {
	padding-left: 65px;
}

div.Leaf {
	font: bold;
}

body {
	background-color: #FFFFFF;
}
</style>
<style type="text/css">
.stepright1 {
	padding-left: 5px;
}

.stepright2 {
	padding-left: 15px;
}

.stepright3 {
	padding-left: 25px;
}

.stepright4 {
	padding-left: 35px;
}

.stepright5 {
	padding-left: 45px;
}

.stepright6 {
	padding-left: 55px;
}

.stepright7 {
	padding-left: 65px;
}

div.Leaf {
	font: bold;
}

body {
	background-color: #FFFFFF;
}
</style>
<body>
	<script language="JavaScript" type="text/javascript">
    function find(s1,s2)
      {
    	var str1=s1;
    	var str2=s2;
    	var len1=str1.length;
    	var len2=str2.length;
    	var i;
    	if(len1<len2)
    		{return 0;}
    	else
    		{
    		  for(var i=0;i<len2;i++)
    			  {
    			     if(str1.charAt(i)!=str2.charAt(i))
    			    	 {
    			    	   return 0;
    			    	 }
    			  }
    		}
    	return 1;
      }
    function test(obj)
    {
    	var objname=obj.id;
    	var name;
    	var s="";
    	var imgnode=document.getElementById("img_"+objname);
    	var showvalue="";
    	if(imgnode.alt=="展开")
    		{
    		  imgnode.src="img/缩回.png";
    		  imgnode.alt="缩回";
    		  showvalue="none";
    		}
    	else
    		{
    		  imgnode.src = "img/展开.png";
			  imgnode.alt = "展开";
			  showvalue="block"; 
			  
    		}
    	   for (var i=0; i<document.all.length; i++)
		  {
			name = document.all.item(i).id; 
			if (!((name == "")||(name == null)||(name == objname)))
			{    
				s = name;
				if (find(s,objname))
				{  
					document.all.item(i).style.display = showvalue;
				}
			}
		   }  
    }
    
    
    
    Ext.onReady(  
    		 function(){  
    		    var  tree = new Ext.tree.TreePanel({  
    		       height: 300,  
    		       width: 400,  
    		       animate:true,  
    		       enableDD:true,  
    		       containerScroll: true,  
    		       rootVisible: false,  
    		       frame: true,  
    		       // getBranch.do请求服务器返回多级树形结构的JSON字符串  
    		     loader: new Ext.tree.TreeLoader({dataUrl:'getBranch.do'}),   
    		       root : new Ext.tree.AsyncTreeNode({id:'0',text:'根结点'})    
    		      });        
    		      tree.expandAll();  
    		  }  
    		);
   
</script>
	<div style="border: solid 1px #ff8040;">
		<br>
		<h1
			style="text-align: center; margin-top: 50px; font-family: 微软雅黑; color: black">河北科技创新平台年报统计表</h1>
		<br> <br> <br>
	</div>
	<br>
	<div>
		<a href="fill_in.jsp"><input type="button"
			style="background-color: white" value="返回上一级"></a>
	</div>
	<div id="content">

		<div
			style="width: 23%; margin-top: 20px; float: left; maigin-left: 30px">
			<a href="A"></a>
			<div style="display: block;" class="stepright1" id="nodeA"
				name="nodeA" onClick="test(this)">
				<img border="0" src="img/缩回.png" title="缩回" id="img_nodeA"
					name="img_nodeA" /> A 农、林、牧、渔业
			</div>

			<div style="display: none;" class="stepright2" id="nodeA1"
				name="nodeA1" onClick="test(this)">
				<a href="javascript:expandTree('A1',2)"><img border="0"
					src="img/缩回.png" title="展开" id="img_nodeA1" name="img_nodeA1" /></a> <a
					title="" style="cursor: hand;" target="main"
					onclick="searchF('A1','%26%23x9a6c%3B%26%23x514b%3B%26%23x601d%3B%26%23x3001%3B%26%23x6069%3B%26%23x683c%3B%26%23x65af%3B%26%23x8457%3B%26%23x4f5c%3B')">J
					金融业</a>
			</div>

		</div>
		<div
			style="width: 74%; border-left: solid 1px #8a8a8a; margin-left: 200px; margin-top: 20px; float: right">
			<form id="search" action="Select" class="layui-form">
				多条件查询：
				<div style="border: solid 1px #8a8a8a80; height: 39px;"
					class="mutiselect_box">
					<div class="layui-input-inline">
						平台级别： <input type="checkbox" name="platform_grade"
							id="country_grade" value="1" lay-skin="primary" />&nbsp;&nbsp;国家级&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" name="platform_grade" id="province_grade"
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
			</form>
			<div style="margin-top: 26px;">
				<p class="title">最新平台报表</p>
			</div>
			<div>
				<ul class="table_list" style="margin: 20px 30px;">
					<li>撒地方撒地方深蓝撒地方重大科技创新平台</li>
					<li>人工智能APP撒地方吧撒地方科技创新平台</li>
					<li>见就就叫阿三地方垃圾啊收到了房价 烦烦烦</li>
				</ul>
			</div>
			<div style="margin-top: 26px;">
				<p class="title">最新平台报表</p>
			</div>
			<div>
				<ul class="table_list" style="margin: 20px 30px;">
					<li>撒地方撒地方深蓝撒地方重大科技创新平台</li>
					<li>人工智能APP撒地方吧撒地方科技创新平台</li>
					<li>见就就叫阿三地方垃圾啊收到了房价 烦烦烦</li>
				</ul>
			</div>
			<div style="margin-top: 26px;">
				<p class="title">最新平台报表</p>
			</div>
			<div>
				<ul class="table_list" style="margin: 20px 30px;">
					<li>撒地方撒地方深蓝撒地方重大科技创新平台</li>
					<li>人工智能APP撒地方吧撒地方科技创新平台</li>
					<li>见就就叫阿三地方垃圾啊收到了房价 烦烦烦</li>
				</ul>
			</div>
		</div>
	</div>
	<footer style="text-align: center; padding: 20px;">河北科技©Copyright版权所有</footer>
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