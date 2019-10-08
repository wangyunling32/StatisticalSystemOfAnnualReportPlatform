
var isPassRight = false;
var isSPassRight = false;
var isDateRight=false;

function load(){
	checkAll();
	if($("#username").val()!="")
		checkId();
	if($("#password").val()!="")
		checkPass();
	if($("#spassword").val()!="")
		checkSPass();
	if($("#nickname").val() != "")
		checkNickName();
	if($("#phone").val() != "")
		checkPhone();
	if($("#dormitory").val()!="")
		checkDormitory();
	if($("#room").val()!="")
		checkRoomnumber();
};

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

function checkDate()
{
	var str = $("#approve_date").val();
	if($("#approve_date").val()==null||$("#approve_date").val()=="")
	{
		$(".tip").eq(4).html("×");
		$(".tip").eq(4).css({"color":"red"});
		$(".tip").eq(4).attr("title",'显示名称不能为空');
		isDateRight = false;
	}
	else{
		$(".tip").eq(4).html("√");
		$(".tip").eq(4).css({"color":"green"});
		isDateRight = true;
	}
	checkAll(); 	
}

function checkAll(){
	var flag = isPassRight+isSPassRight+isDateRight
	console.log(flag);
	if(flag == 7){
		$("#regBtn").removeAttr("disabled");
		$("#regBtn").css({"background-color":"#a9a9a9"});
	}else{
		$("#regBtn").attr("disabled");
		$("#regBtn").css({"background-color":"#f7f7f7"});
	}
}