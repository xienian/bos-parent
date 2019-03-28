<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>datagrid_edit</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/highcharts.js"></script>
<script src="${pageContext.request.contextPath }/js/highcharts/modules/exporting.js"></script>

<!-- 创建基本图标 -->
<script type="text/javascript">
	$(function () {
		$("#test").highcharts({
			title:{
				text:'各种浏览器份额'
			},
	        xAxis: {
	            categories: ['苹果', ' 橙']
	        },
			series:[{
			/* 	type:'line', */
				name:'浏览器占比',
				data:[
					['Firfox',45.0],
					['EI',	26.8],
					['Safari',	8.5],
					['Opera',	6.2],
					['Others',	7.1]
				]
			},
			{
				/* 	type:'line', */
					name:'资源占比',
					data:[
						['Firfox',66.0],
						['EI',	26.8],
						['Safari',	8.5],
						['Opera',	6.2],
						['Others',	7.1]
					]
				}
			]
			
		});
		
	Highcharts.chart('container',{
		title:{
			text:'各种浏览器份额'
		},
        xAxis: {
            categories: ['苹果', ' 橙', '梨', '香蕉', '李']
        },
		series:[{
		/* 	type:'line', */
			name:'浏览器占比',
			data:[
				['Firfox',45.0],
				['EI',	26.8],
				['Safari',	8.5],
				['Opera',	6.2],
				['Others',	7.1]
			]
		},
		{
			/* 	type:'line', */
				name:'资源占比',
				data:[
					['Firfox',66.0],
					['EI',	26.8],
					['Safari',	8.5],
					['Opera',	6.2],
					['Others',	7.1]
				]
			}
		]
		
	});
	});
</script>
</head>
<body>
	<div id="test">
	</div>
	<div id="container"></div>
</body>
</html>