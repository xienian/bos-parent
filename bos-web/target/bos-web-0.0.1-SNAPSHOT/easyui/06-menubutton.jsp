<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>layout</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function(){
		
	})
</script>
</head>
<!-- <body class="easyui-layout">他妈的，加了布局，上面的消息框就不能出现了 -->
<body>
<a href="javascript:void(0)" id="mb" class="easyui-menubutton"     
        data-options="menu:'#mm',iconCls:'icon-edit'">Edit</a>   
<div id="mm" style="width:150px;">   
    <div data-options="iconCls:'icon-undo'">Undo</div>   
    <div data-options="iconCls:'icon-redo'">Redo</div>   
    <!-- 菜单分隔符 -->
    <div class="menu-sep"></div>   
    <div>Cut</div>   
    <div>Copy</div>   
    <div>Paste</div>   
    <div class="menu-sep"></div>   
    <div data-options="iconCls:'icon-remove'">Delete</div>   
    <div>Select All</div>   
</div>  

</body>
</html>