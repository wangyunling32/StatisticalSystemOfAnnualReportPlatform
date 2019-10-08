var isPassRight = false;
var isUserRight = false;
function checkPass(){
	var pass = $("#pass1").val();
	var pass2 = $("#pass2").val();
	if(pass.length <= 5 || pass.length >= 11)
	{
		$(".tip").eq(2).html("×");
		$(".tip").eq(2).css({"color":"red"});
		$(".tip").eq(2).attr("title",'请设置6-10位字符的密码');
		isPassRight = false;
	}
	else {
		$(".tip").eq(2).html("√");
		$(".tip").eq(2).css({"color":"green"});
		$(".tip").eq(2).attr("title",'');
		isPassRight = true;
	}
	checkAll();
}

function checkSPass(){
	if($(".tip").eq(2).html() == "√"){
		var pass = $("#pass1").val();
		var pass2 = $("#pass2").val();
		if(pass == pass2){
			$(".tip").eq(3).html("√");
			$(".tip").eq(3).css({"color":"green"});
			$(".tip").eq(3).attr("title",'');
			isSPassRight = true;
		}
		else
		{
			$(".tip").eq(3).html("×");
			$(".tip").eq(3).css({"color":"red"});
			$(".tip").eq(3).attr("title",'两次输入密码不一致');
			isSPassRight = false;
		}
	}
	checkAll();
}
//显示注册层
function showRegist(){
	var cov = document.getElementById("cover");
	var reg = document.getElementById("regist");
	cov.style.display = "block";
	reg.style.display = "block";
	
	/****加载切换按钮***/
	var user = document.getElementById("userRegist");
	var owner = document.getElementById("ownerRegist");
	user.style.color="red";
	user.onclick = (function(){
		user.style.color="red";
		owner.style.color="#BBBBBB"
	});
	owner.onclick = (function(){
		user.style.color="#BBBBBB";
		owner.style.color="red";
	});
}
//关闭注册层
function closeRegist(){
	var cov = document.getElementById("cover");
	var reg = document.getElementById("regist");
	cov.style.display = "none";
	reg.style.display = "none";
}
function load(){

	$("input[type='text']").attr("onkeyup","this.value=this.value.replace(/\\s+/g,'')");		//禁止input框输入空格
	
	//选择地区三个下拉框
	var pro_select = $("select[name='province']");
	var city_select = $("select[name='city']");
	var dist_select = $("select[name='district']");
	var sub1_select = $("select[name='subject1']");
	var sub2_select = $("select[name='subject2']");
	var sub3_select = $("select[name='subject3']");
	
	//初始化省份
	$.each(DISTRICTS[100000],function(key,value){
		pro_select.append("<option value='" + key + "'>" + value + "</option>");
	})
	//初始化学科分类
	$.each(SUBJECT[100],function(key,value){
		sub1_select.append("<option value='" + key + "'>" + value + "</option>");
	})

    //加载form模块
    var form;

	layui.use('form',function () {
		form = layui.form;
		
		//监听提交
		form.on('submit(*)',function(data){
			layer.msg(JSON.stringify(data.field));
			return false;
		});
		
		//监听选择下拉框
		form.on('select',function(data){
			switch(data.elem.name){
				//选择省
				case 'province':
					city_select.html("<option value=''>请选择市</option>");			//清空市
					dist_select.html("<option value=''>请选择县/镇/区</option>");	//清空区
					var pro_JSON = DISTRICTS[parseInt(data.elem.value)];			//根据当前省加载城市列表
					$.each(pro_JSON,function(key,value){							//循环添加城市
						city_select.append("<option value='" + key + "'>" + value + "</option>");
					})
					break;
				//选择市
				case 'city':
					dist_select.html("<option value=''>请选择县/镇/区</option>");	//清空区
					console.log(DISTRICTS[parseInt(data.elem.value)]);
					var pro_JSON = DISTRICTS[parseInt(data.elem.value)];			//根据当前市加载区列表
					$.each(pro_JSON,function(key,value){							//循环添加区
						console.log("key" + ":" + value);
						dist_select.append("<option value='" + key + "'>" + value + "</option>");
					})
					break;
				case 'district':
					break;
				//选择一级学科分类
				case 'subject1':
					sub2_select.html("<option value=''>请选择二级学科分类</option>");		//清空二级学科分类
					sub3_select.html("<option value=''>请选择三级学科分类</option>");		//清空三级学科分类
					$.each(SUBJECT[parseInt(data.elem.value)], function(key,value) {
						sub2_select.append("<option value='" + key + "'>" + value + "</option>");	//遍历对象添加子类
					});
					break;
					
				//选择二级学科分类
				case 'subject2':
					var s_subject = SUBJECT[parseInt(data.elem.value)];			//获取二级学科对象
					if($.isEmptyObject(s_subject)){			//如果对象为空，说明该类不存在三级子类
						sub3_select.html("<option value=''>该分类没有三级子类</option>");
						sub3_select.removeAttr("lay-verify");
						sub3_select.attr("disabled","disabled");
					}else{		//否则，存在三级子类
						sub3_select.removeAttr("disabled");
						sub3_select.attr("lay-verify","required");
						sub3_select.html("<option value=''>请选择三级学科分类</option>");
					}
					$.each(s_subject, function(key,value) {
						sub3_select.append("<option value='" + key + "'>" + value + "</option>");
					});
					break;
				default:break;
			}
			form.render("select");
		})



		//监听单选钮
		form.on("radio",function(data){
//			console.log(data);
			switch(data.elem.value){
				case '1':
					console.log("相对独立");
					$("input[name='faren_type']").attr("disabled","disabled");
					break;
				case '0':
					console.log("独立法人");
					$("input[name='faren_type']").removeAttr("disabled");
					break;
				default:;
			}
			form.render("radio");
		})

		form.verify({
			date:function (value,item) {
				var reg_date = /^((((19\d{2}|((200[0-9]|201[0-7]))))-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\d|30))|((19\d{2}|((200[0-9]|201[0-7])))-(0?[13578]|1[02])-31)|((19\d{2}|((200[0-9]|201[0-7])))-0?2-(0?[1-9]|1\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$/;
				if(!reg_date.test(value))
					return '日期(格式)错误，请检查！';
            },
			usci:function (value,item) {
				var reg_usci = /^[^_IOZSVa-z\W]{2}\d{6}[^_IOZSVa-z\W]{10}$/;
				if(!reg_usci.test(value))
					return '统一社会信用代码格式错误！';
            },
			postcode:function(value,item){
				var reg_postcode = /^[0-9]\d{5}(?!\d)$/
				if(!reg_postcode.test(value))
					return '邮编错误，请检查！';
			},
			telephone:function (value,item) {
				var reg_telephone = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/
            	if(!reg_telephone.test(value))
            		return '电话号码格式错误，请检查！';
			}
		})
	})

	//加载日期模块
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#birthday' //指定元素
        });
    });
	
	//添加删除共建单位
    $("#add").click(function(e){
    	$("#delete").before('<input name="gjdw" value="" class="tb_input" style="width: 50%;margin-top:10px" onkeyup="this.value=this.value.replace(/\\s+/g,\'\')" type="text" placeholder="请输入共建单位" lay-verify="required">')
    })
    $("#delete").click(function(e){
    	if($("input[name='gjdw']").length > 1)
    		$("input[name='gjdw']:last").remove();
    })
}
