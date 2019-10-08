<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:useBean id="user" class="com.indeed.model.User" scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript"
	src="js/StandardIndustrialClassificationCodes.js"></script>
</head>
<body onload="load()" background="img/123.jpg"
	style="background-repeat: no-repeat; background-size: 100%, 100%; background-attachment: fixed;">
	<div>
		<img src="img/index_01beifen.jpg" width="100%"><br>
		<br>
	</div>
	<div
		style="width: 150px; border: solid 1px #316a91; float: left; height: 1000px">
		<br> <br> <br> <a href="fill_in.jsp"><input
			type="button"
			style="background-color: #87CEFA; border-bottom: solid 1px #316a91; height: 35px; width: 150px; font-size: 20px"
			value="填表"></a> <a href="tijiao.jsp"><input type="button"
			style="background-color: #87CEFA; border-bottom: solid 1px #316a91; height: 35px; width: 150px; font-size: 20px"
			value="审核结果"></a> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
	</div>
	<div style="margin-left: 150px;">
		<form id="plat_form" class="layui-form" action="PlatformSubmit"
			method="post" style="width: 1400px">
			<table class="layui-table tab middle_self">
				<thead>
					<tr>
						<td align="right" height="50"><font class="must">*</font>平台名称：</td>
						<td><input type="text" name="platform_name"
							id="platform_name" value="<%=user.getPlatform_name()%>"
							class="tb_input1" disabled="disabled" /></td>
						<td align="right"><font class="must">*</font>平台编号：</td>
						<td><input type="text" name="platform_number"
							id="platform_number" value="<%=user.getPlatform_number()%>"
							class="tb_input1" disabled="disabled" /></td>
					</tr>
					<tr>
						<td align="right" height="50"><font class="must">*</font>批准年月：</td>
						<td><input type="text" name="approval_date"
							id="approval_date" value="<%=user.getApprove_date()%>"
							class="tb_input1" disabled="disabled" /></td>
						<td align="right"><font class="must">*</font>批准文号：</td>
						<td><input type="text" name="approval_number"
							id="approval_number" value="<%=user.getApprove_number()%>"
							class="tb_input1" disabled="disabled" /></td>
					</tr>
					<tr>
						<td align="right" height="50" colspan="1"><font class="must">*</font>技术领域：</td>
						<td colspan="3"><input type="text" name="field" id="field"
							value="<%=user.getTechnosphere()%>" disabled="disabled"
							class="tb_input1" /></td>
					</tr>
					<tr>
						<td align="right" height="50"><font class="must">*</font>平台级别：</td>
						<td><input type="checkbox" name="platform_grade"
							id="country_grade" value="1" lay-skin="primary" />&nbsp;&nbsp;国家级&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="platform_grade" id="province_grade"
							value="2" lay-skin="primary" />&nbsp;&nbsp;省级</td>
						<td align="right"><font class="must">*</font>所在市县：</td>
						<td>
							<div style="float: left; width: 120px;">
								<select name="province" style="width: 10px; float: left;"
									lay-verify="required">
									<option value="">请选择省份</option>
								</select>
							</div>
							<div style="float: left; width: 120px; margin-left: 10px;">
								<select name="city" lay-verify="required">
									<option value="">请选择市</option>
								</select>
							</div>
							<div style="float: left; width: 150px; margin-left: 10px;">
								<select name="district" lay-verify="required">
									<option value="">请选择县/镇/区</option>
								</select>
							</div>
						</td>
					</tr>

					<tr>
						<td rowspan="2" height="50" align="right"><font class="must">*</font>平台组织形态</td>
						<td height="25"><input type="radio" name="platform_form"
							id="rela_independent" value="1" title="内设机构相对独立" /></td>
						<td rowspan="2">
							<div id="faren_type">
								<input type="radio" name="faren_type" id="faren_type1" value="2"
									title="企业法人" disabled="disabled" /><br /> <input type="radio"
									name="faren_type" id="faren_type2" value="3" title="事业法人"
									disabled="disabled" /><br /> <input type="radio"
									name="faren_type" id="faren_type3" value="4" title="社团法人"
									disabled="disabled" /><br />
							</div>
						</td>
						<td><input type="radio" name="unite" id="unite"
							value="united" title="多单位联合共建" /> <input type="radio"
							name="unite" id="nounite" value="nounited" title="依托单位独自建设" /></td>
					</tr>
					<tr>
						<td height="25"><input style="height: 25px;" type="radio"
							name="platform_form" id="independent" value="0" title="独立法人" /></td>
						<td>京津冀共建： <input type="radio" name="jjjgj" id="jjjgj"
							value="yes" title="是" /> <input type="radio" name="jjjgj"
							id="nojjjgj" value="no" title="否" />
						</td>
					</tr>
					<tr>
						<td align="right"><font class="must">*</font>服务的主要国民经济行业</td>
						<td colspan="3">
							<div style="float: left; width: 120px;">
								门类：<select id="category" lay-filter="category" name="category"
									class="layui-select"></select>
							</div>
							<div style="float: left; width: 120px; margin-left: 10px;">
								大类：<select id="division" lay-filter="division" name="division"
									class="layui-select"></select>
							</div>
							<div style="float: left; width: 150px; margin-left: 10px;">
								中类：<select id="group" lay-filter="group" name="group"
									class="layui-select"></select>
							</div>
							<div style="float: left; width: 150px; margin-left: 10px;">
								小类：<select id="section" lay-filter="section" name="section"
									class="layui-select"></select>
							</div>
						</td>
					</tr>

					<tr>
						<td><font class="must">*</font>所属的主要学科</td>
						<td colspan="3">
							<div style="float: left; width: 180px;">
								<select name="subject1" style="width: 10px; float: left;"
									lay-verify="required">
									<option value="">请选择一级学科分类</option>
								</select>
							</div>
							<div style="float: left; width: 180px; margin-left: 10px;">
								<select name="subject2" lay-verify="required">
									<option value="">请选择二级学科分类</option>
								</select>
							</div>
							<div style="float: left; width: 180px; margin-left: 10px;">
								<select name="subject3" lay-verify="required">
									<option value="">请选择三级学科分类</option>
								</select>
							</div>
						</td>
					</tr>
				</thead>
			</table>
			<div style="margin-top: 20px;">
				<label class="lab_title" style="margin-left: 10%;">依托单位信息：</label>
			</div>
			<table class="layui-table tab middle_self">
				<thead>
					<tr>
						<td align="right" height="50" width="20%"><font class="must">*</font>依托单位名称：</td>
						<td><input type="text" name="dep_organization_name"
							id="dep_organization_name" placeholder="如：百度" value=""
							class="tb_input" lay-verify="required" /></td>
						<td align="right"><font class="must">*</font>依托单位组织机构代码(社会信用代码)：</td>
						<td><input type="text" name="dep_organization_number"
							id="dep_organization_number" placeholder="如：91430111MA4L16JQ9B"
							value="" class="tb_input" lay-verify="required|usci"
							style="text-transform: uppercase" /></td>
					</tr>
					<tr>
						<td align="right" height="50"><font class="must">*</font>依托单位法人代表姓名：</td>
						<td><input type="text" name="dep_organization_faren"
							id="dep_organization_faren" placeholder="如：齐志刚" value=""
							class="tb_input" lay-verify="required" /></td>
						<td align="right" height="50"><font class="must">*</font>办公电话：</td>
						<td><input type="text" name="dep_organization_phone"
							id="dep_organization_phone" placeholder="如：03105705554" value=""
							class="tb_input" lay-verify="require|telephone" /></td>
					</tr>
					<tr>
						<td align="right" height="50"><font class="must">*</font>依托单位类型：</td>
						<td colspan="3"><input type="radio"
							name="dep_organization_type" id="enterprise_type"
							value="enterprise" title="企业" /> <input type="radio"
							name="dep_organization_type" id="science_type" value="science"
							title="科研机构" /> <input type="radio" name="dep_organization_type"
							id="university_type" value="university" title="高等院校" /> <input
							type="radio" name="dep_organization_type" id="delection_type"
							value="delection" title="检测机构" /> <input type="radio"
							name="dep_organization_type" id="medical_type" value="medical"
							title="医疗机构" /> <input type="radio" name="dep_organization_type"
							id="government_type" value="government" title="政府机构" /> <input
							type="radio" name="dep_organization_type" id="association_type"
							value="association" title="社团组织" /> <input type="radio"
							name="dep_organization_type" id="orther_type" value="orther"
							title="其它" /></td>
					</tr>
					<tr>
						<td align="right">共建单位：</td>
						<td colspan="3"><input type="text" name="gjdw" value=""
							class="tb_input" style="width: 50%;" placeholder="请输入共建单位"
							lay-verify="required" />
							<button type="button" id="delete"
								class="layui-btn layui-btn-sm layui-btn-normal"
								style="margin-left: 10px;">
								<i class="layui-icon">&#xe640;</i>
							</button>
							<button type="button" id="add"
								class="layui-btn layui-btn-sm layui-btn-normal">
								<i class="layui-icon">&#xe654;</i>
							</button></td>
					</tr>
				</thead>
			</table>
			<div style="margin-top: 20px;">
				<label class="lab_title" style="margin-left: 10%;">平台主任信息：</label>
			</div>
			<table class="layui-table tab middle_self">
				<%
        
     %>
				<thead>
					<tr>
						<td rowspan="3" width="9%" align="center">平台主任(院长)</td>
						<td align="right" height="50" width="10%"><font class="must">*</font>姓名：</td>
						<td width="15%"><input type="text" name="dean_name"
							id="dean_name" placeholder="如：郑国庆" value="" class="tb_input"
							lay-verify="required" /></td>
						<td align="right" width="8%"><font class="must">*</font>性别：</td>
						<td width="9%"><input type="radio" name="sex" id="male"
							value="male" title="男" class="layui-input-inline" /> <input
							type="radio" name="sex" id="female" value="female" title="女"
							class="layui-input-inline" /></td>
						<td align="right" width="10%"><font class="must">*</font>出生年月：</td>
						<td align="right" width="15%"><input type="text"
							name="birthday" id="birthday" placeholder="如：1987-05-01" value=""
							class="layui-input" lay-verify="required|date" /></td>
						<td align="right" width="8%"><font class="must">*</font>职称：</td>
						<td align="right" width="16%"><input type="text"
							name="job_title" id="job_title" placeholder="如：会计师" value=""
							class="tb_input" lay-verify="required" /></td>
					</tr>
					<tr>
						<td align="right" height="50"><font class="must">*</font>所学专业：</td>
						<td colspan="2"><input type="text" name="major" id="major"
							placeholder="如：通信工程" value="" class="tb_input"
							lay-verify="required" /></td>
						<td align="right" height="50"><font class="must">*</font>学历：</td>
						<td colspan="2"><input type="text" name="education"
							id="education" placeholder="如：本科" value="" class="tb_input"
							lay-verify="required" /></td>
						<td align="right" height="50"><font class="must">*</font>学位：</td>
						<td><input type="text" name="degree" id="degree"
							placeholder="如：硕士" value="" class="tb_input"
							lay-verify="required" /></td>
					</tr>
					<tr>
						<td align="right" height="50"><font class="must">*</font>办公电话：</td>
						<td colspan="2"><input type="text" name="dean_telephone"
							id="dean_telephone" placeholder="如：031110105777" value=""
							class="tb_input" lay-verify="required|telephone" /></td>
						<td align="right" height="50"><font class="must">*</font>手机：</td>
						<td colspan="2"><input type="text" name="dean_mobilephone"
							id="dean_mobilephone" placeholder="如：15127045632" value=""
							class="tb_input" lay-verify="required|phone" /></td>
						<td align="right" height="50"><font class="must">*</font>Email：</td>
						<td><input type="text" name="dean_email" id="dean_email"
							placeholder="如：iv20@163.com" value="" class="tb_input"
							lay-verify="required|email" /></td>
					</tr>
				</thead>
			</table>
			<div style="margin-top: 20px;">
				<label class="lab_title" style="margin-left: 10%;">平台联系信息：</label>
			</div>
			<table class="layui-table tab middle_self">
				<thead>
					<tr>
						<td align="right" height="50" width="14%"><font class="must">*</font>平台网站名称：</td>
						<td width="43%"><input type="text" name="platform_site_name"
							id="site_name" placeholder="如：创新平台" value="" class="tb_input"
							lay-verify="required" /></td>
						<td align="right" width="14%"><font class="must">*</font>平台网站地址：</td>
						<td><input type="text" name="platform_website"
							id="platform_website" placeholder="如：http://www.baidu.com"
							value="" class="tb_input" lay-verify="required|url" /></td>
					</tr>
					<tr>
						<td align="right" height="50"><font class="must">*</font>平台通讯地址：</td>
						<td><input type="text" name="platform_addr"
							id="platform_addr" placeholder="如：广东省广州市天河区先烈东路沙河西街32号2梯403"
							value="" class="tb_input" lay-verify="required" /></td>
						<td align="right">邮编：</td>
						<td><input type="text" name="platform_postcode"
							id="platform_postcode" placeholder="如：056151" value=""
							class="tb_input" lay-verify="required|postcode" /></td>
					</tr>
				</thead>
			</table>
			<div style="text-align: center;">
				<button class="layui-btn layui-btn-lg layui-btn-normal"
					lay-submit="PlatformSave" lay-filter="btn_save">保存</button>
				<button class="layui-btn layui-btn-lg layui-btn-normal"
					lay-submit="PlatformSubmit" lay_filter="btn_submit">提交</button>
			</div>
		</form>
	</div>
	<footer style="height: 20px"></footer>
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="js/china.js"></script>
	<script type="text/javascript" src="js/subjects.js"></script>
	<script type="text/javascript">

    layui.use("form")
    addressInit('category','division','group','section','A 农、林、牧、渔业', '01 农业', '011 谷物种植', '0111 稻谷种植');
</script>

	<script type="text/javascript" src="js/demo.js"></script>
</body>
</html>