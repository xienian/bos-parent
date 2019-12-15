<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tabs</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="../../../css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#btn1").click(
		function(){
			//对于添加选项卡，一般先判断是否已存在，存在则用选中方法，不存在则创建
			var condition = $("#mytabs").tabs("exists","添加选项卡面板");
			if(condition){
				$("#mytabs").tabs("select","添加选项卡面板");
			}else{
				$("#mytabs").tabs("add",{    
				    title:"添加选项卡面板",    
				    content:"Tab Body",
				    closable:true   
				}); 
			} 
		});

	});

</script>
</head>
<body class="easyui-layout">
	<!-- 使用div元素描述每个区域 -->
	<div title="XXX管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
	<div title="系统菜单" style="width: 200px" data-options="region:'west'">
		<!-- 制作accordion折叠面板 
			fit:true----自适应(填充父容器)
		-->
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-cut'" title="面板一">
				<!-- 添加一个按钮 -->
				<a id="btn1" class="easyui-linkbutton">添加一个选项卡</a>
			</div>
			<!-- 在面板2中，添加ztree -->
			<div title="面板二">
			
			</div>
			<div title="面板三">3333</div>
		</div>
	</div>
	<div   data-options="region:'center'">
		<!-- 制作一个tabs选项卡面板 -->
		<div   id="mytabs" class="easyui-tabs" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-cut'" title="面板一">1111</div>
			<div data-options="closable:true" title="面板二">2222</div>
			<div title="面板三">3333</div>
		</div>
	</div>
	<div style="width: 100px" data-options="region:'east'">东部区域</div>
	<div style="height: 50px" data-options="region:'south'">南部区域</div>
</body>
</html>