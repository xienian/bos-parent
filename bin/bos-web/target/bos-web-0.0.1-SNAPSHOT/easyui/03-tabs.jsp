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
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
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
			<!-- 在面板2中，添加ztree
				使用简单json构建
			 -->
			<div title="面板二">
			<ul id="ztree" class="ztree"></ul>
			<script type="text/javascript">
				$(function(){
					var setting2 = {
							data: {
								simpleData: {
									enable: true//使用简单json数据构造ztree节点
								}
							}
					};
					
					var zNodes2 = [{"id":1, "pId":0, "name":"test1"},
					                {"id":11, "pId":1, "name":"test11"},
					                {"id":12, "pId":1, "name":"test12"},
					                {"id":111, "pId":11, "name":"test111"}];
					
					$.fn.zTree.init($("#ztree"), setting2, zNodes2);
					
				});
			</script>
			</div>
			<div title="面板三">3333</div>
			<div title="面板四">
			<ul id="ztree4" class="ztree"></ul>
			<script type="text/javascript">
				$(function(){
					var setting2 = {
							data: {
								simpleData: {
									enable: true//使用简单json数据构造ztree节点
								}
								},
							callback: {
									onClick: function(event, treeId, treeNode){
										if(treeNode.page != undefined){
											var e = $("#mytabs").tabs("exists",treeNode.name);
											if(e){
												$("#mytabs").tabs("select",treeNode.name);
											}else{
												$("#mytabs").tabs("add",
												{
													title:treeNode.name,    
												    content:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>',    
												    closable:true
												}		
												);
											}
										}
									}
								}		
					};
					
					//使用jQuery的ajax请求，获取json数据构造ztree
					var url = "${pageContext.request.contextPath }/json/menu.json";
					$.post(url, {}, function(data){
						$.fn.zTree.init($("#ztree4"), setting2, data);
					}, 'json');
				});
			</script>
			</div>
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