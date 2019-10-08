$(document).ready(function(){

    layui.use("laydate",function(){
        var laydate = layui.laydate;
        laydate.render({
            elem:'#approve_date'
        })
    })

    layui.use("form",function(){
        var form = layui.form;
        form.verify({
            pass:function(value,item){
                if($("#pass1").val() != $("#pass2").val())
                    return "两次输入密码不一致";
            }
        })
    })
})

/*function verify(option){
	switch(option){
		case 'username':;
		case 'pass':return true;break;
		case 'pass2':return true;break;

	}
}*/