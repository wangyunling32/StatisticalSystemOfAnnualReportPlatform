/**
 * 
 */
/* 多条件查询 */
var i = 0;
$(document)
		.ready(
				function() {
					form1.geshu.value = i + 1;

					$("#remove").click(function() {
						$("#div" + i).empty();
						if (i > 0)
							i--;
						form1.geshu.value = i + 1;
					});

					$("#add")
							.click(
									function() {
										i++;

										$("#tiaojian")
												.append(
														'<div id=div'
																+ i
																+ '><li>'
																+ ' <select name="way1['
																+ i
																+ ']">'
																+ '<option value="and" checked>并且</option>'
																+ '<option value="or">或者</option>'
																+ '<option value="not">不含</option>'
																+

																'</select>&nbsp;'
																+ '( <select name="type['
																+ i
																+ ']">'
																+ '<option value="user_name" checked>填表人姓名</option>'
																+ '<option value="platfrom_name">平台名称</option>'
																+ '<option value="authorize_number">批准文号</option>'
																+ '<option value="support_name">依托单位名称</option>'
																+ '<option value="director_name">平台主任姓名</option>'
																+ '<option value="build_unit">共建单位</option>'
																+ '</select>&nbsp;'
																+ '<input type="text" name="content1['
																+ i
																+ ']" size="20" />&nbsp;'
																+ '<select name="way2['
																+ i
																+ ']">'
																+ '<option value="and" checked>并含</option>'
																+ '<option value="or">或含</option>'
																+ '<option value="not">不含</option>'
																+ '</select>&nbsp;'
																+ '<input type="text" name="content2['
																+ i
																+ ']" size="20" />&nbsp;'
																+ ' <select name="content3['
																+ i
																+ ']">'
																+ '<option value="in" checked>精准</option>'
																+ '<option value="like">模糊</option>'
																+ '</select>&nbsp;'
																+ ')'
																+ '</li></div>');

										form1.geshu.value = i + 1;
									});

				});
/* 树状图 */
$(document)
.ready(
		function() {
			$('.inactive')
					.click(
							function() {
								if ($(this).siblings('ul').css(
										'display') == 'none') {
									$(this).parent('li').siblings(
											'li').removeClass(
											'inactives');
									$(this).addClass('inactives');
									$(this).siblings('ul')
											.slideDown(100)
											.children('li');
									if ($(this).parents('li')
											.siblings('li')
											.children('ul').css(
													'display') == 'block') {
										$(this)
												.parents('li')
												.siblings('li')
												.children('ul')
												.parent('li')
												.children('a')
												.removeClass(
														'inactives');
										$(this).parents('li')
												.siblings('li')
												.children('ul')
												.slideUp(100);

									}
								} else {
									//控制自身变成+号
									$(this)
											.removeClass(
													'inactives');
									//控制自身菜单下子菜单隐藏
									$(this).siblings('ul').slideUp(
											100);
									//控制自身子菜单变成+号
									$(this).siblings('ul')
											.children('li')
											.children('ul').parent(
													'li').children(
													'a').addClass(
													'inactives');
									//控制自身菜单下子菜单隐藏
									$(this).siblings('ul')
											.children('li')
											.children('ul')
											.slideUp(100);

									//控制同级菜单只保持一个是展开的（-号显示）
									$(this).siblings('ul')
											.children('li')
											.children('a')
											.removeClass(
													'inactives');
								}
							})
		});
function fuzhi()// 树状图向framework_1赋值
{
	var a = document.getElementById('t1');

	alert("a:" + a);
}
/* 表头超链接 */
// 显示tab（tabHeadId：tab头中当前的超链接；tabContentId要显示的层ID）
function showTab(tabHeadId, tabContentId) {
	// tab层
	var tabDiv = document.getElementById("tabDiv");
	// 将tab层中所有的内容层设为不可见
	// 遍历tab层下的所有子节点
	var taContents = tabDiv.childNodes;
	for (i = 0; i < taContents.length; i++) {
		// 将所有内容层都设为不可见
		if (taContents[i].id != null && taContents[i].id != 'tabsHead') {
			taContents[i].style.display = 'none';
		}
	}
	// 将要显示的层设为可见
	document.getElementById(tabContentId).style.display = 'block';
	// 遍历tab头中所有的超链接
	var tabHeads = document.getElementById('tabsHead')
			.getElementsByTagName('a');
	for (i = 0; i < tabHeads.length; i++) {
		// 将超链接的样式设为未选的tab头样式
		tabHeads[i].className = 'tabs';
	}
	// 将当前超链接的样式设为已选tab头样式
	document.getElementById(tabHeadId).className = 'curtab';
	document.getElementById(tabHeadId).blur();
}
