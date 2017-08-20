<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师课程反馈表</title>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/t.js"></script>
<style type="text/css">
		body {
			background-color: #fdfbfe;
		}
		
		#b1 {
			font-family: "微软雅黑";
			font-size: 20px;
			color: white;
			background-color: brown;
			text-align: center;
			width: 80%;
			
		}
		
		.table {
			width: 80%;
			margin: auto;
		}
		
		select {
			width: 40%;
		}
		
		.ss {
			background-color: #A52A2A;
			border: 1px solid #f10180;
			width: 90px;
			color: white;
			font-size: 18px;
			margin-top: 1cm;
			margin-left: 83%;
		}
		div.menu ul
		{
			list-style:none; /* 去掉ul前面的符号 */
			margin: 0px; /* 与外界元素的距离为0 */
			padding: 0px; /* 与内部元素的距离为0 */
			width: auto; /* 宽度根据元素内容调整 */
		}
		/* 所有class为menu的div中的ul中的li样式 */
		div.menu ul li
		{
			float:left; /* 向左漂移，将竖排变为横排 */
		}
		/* 所有class为menu的div中的ul中的a样式(包括尚未点击的和点击过的样式) */
		div.menu ul li a, div.menu ul li a:visited
		{
			background-color: #465c71; /* 背景色 */
			border: 1px #4e667d solid; /* 边框 */
			color: #dde4ec; /* 文字颜色 */
			display: block; /* 此元素将显示为块级元素，此元素前后会带有换行符 */
			line-height: 1.35em; /* 行高 */
			padding: 4px 20px; /* 内部填充的距离 */
			text-decoration: none; /* 不显示超链接下划线 */
			white-space: nowrap; /* 对于文本内的空白处，不会换行，文本会在在同一行上继续，直到遇到 <br> 标签为止。 */
		}
		/* 所有class为menu的div中的ul中的a样式(鼠标移动到元素中的样式) */
		div.menu ul li a:hover
		{
			background-color: #bfcbd6; /* 背景色 */
			color: #465c71; /* 文字颜色 */
			text-decoration: none; /* 不显示超链接下划线 */
		}
		/* 所有class为menu的div中的ul中的a样式(鼠标点击元素时的样式) */
		div.menu ul li a:active
		{
			background-color: #465c71; /* 背景色 */
			color: #cfdbe6; /* 文字颜色 */
			text-decoration: none; /* 不显示超链接下划线 */
		}
</style>
	<script>
        var process ="${requestScope.process}";

	</script>
</head>
<body>
<div class="container" id="container2">
			<div class=" row " style=" height: 97px; " id="header">
				<div class="logo " style="margin-top: 30px; margin-left: 2cm;">
					<img src="http://www.farsight.com.cn/templets/default/images/logo.png " alt=" " width="315 " height="42 ">
        		</div>
       		</div>
        	<div class="row "  id="bodyer">
        		<div class="feedback">
        			
        			<table class="table table-bordered " >
					  
					  <thead>
					    <tr>
					      <th id="b1" >课程反馈表</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					     
					      <td>课程日期：<select onchange="showDate()" id="ddd"  ></select></td>
					    </tr>
					    <tr>
					      <td>课程名称：<select onchange="showTeacher()" id="bbb"  ></select></td>
					    </tr>
					    <tr>
					      <td>讲师姓名：<select onchange="showTeacher()" id="ccc"  ></select></td> 
					    </tr>
					    <tr>
					      <td>学员姓名：</td> 
					    </tr>
					    <tr>
					    	<td>满意度说明：5分 - 很满意&nbsp;&nbsp;&nbsp;4分 - 满意 &nbsp;&nbsp;&nbsp;3分 - 一般&nbsp;&nbsp;&nbsp;2分 - 不满意&nbsp;&nbsp;&nbsp;1分 - 很不满意</td>
					    </tr>
					   
					  </tbody>
					</table>
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>类别</td>
								<th>项目内容</td>
								<th>满意度</td>
							</tr>
						</thead>
						<tbody id="content">
					</tbody>
					</table>
        		    <div ><button type="submit" class="ss" onclick="updateCource();">提交</button></div>
        		</div>
        	</div>
	<div  class="menu"  style="margin: auto;width: 46%;">
		<ul>
			<li>
				<a href="toFeedback.do?process=${requestScope.process}" id="feedback_bar">学生反馈</a>
			</li>
			<li>
				<a href="toAllgrade.do?process=${requestScope.process}">各项统计</a>
			</li>
			<li >
				<a href="toPercent.do?process=${requestScope.process}">百分比统计</a>
			</li>
			<li>
				<a href="toAdvice.do?process=${requestScope.process}">教师反馈</a>
			</li>
			<li >
				<a href="toHeader_manage.do?process=${requestScope.process}" id="headerTeacher_bar">班主任管理</a>
			</li>
			<li >
				<a href="toAdmin_manage.do?process=${requestScope.process}" id="admin_bar">管理员管理</a>
			</li>
		</ul>
	</div>
        	<div class="row" style="height: 110px; margin-top: 50px; " id="footer">
        		<p class="u-foot" style="color: #767074;
				text-align: center;">Copyright 2004-2017 华清远见教育集团 版权所有 ，京ICP备16055436号</p>
        	</div>
       </div>
</body>
</html>