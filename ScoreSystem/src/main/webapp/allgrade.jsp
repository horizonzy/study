<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
		   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
		<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


		<link href="./css/allgrade.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="./js/cource.js"></script>
		<script type="text/javascript">

			     var process=${requestScope.process};


                $(document).ready(function(){
                    $("#headerTeacher_bar").hide();
                    $("#admin_bar").hide();
                    $("#feedback_bar").hide();
                    if(process==2){
                        $("#headerTeacher_bar").show();
                        $("#feedback_bar").show();
                    }else if(process==3){
                        $("#admin_bar").show();
                    }
                });
		</script>
		
		<style type="text/css">
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
</head>
<body>
		<div class="container">
			<div class=" row " style=" height: 97px; " id="header">
				<div class="logo " style="margin-top: 30px; margin-left: 2cm;">
					<img src="http://www.farsight.com.cn/templets/default/images/logo.png  " alt=" " width="315 " height="42 ">
				</div>
			</div>
			<div class=" row " id="body" align="center">
				<div class="row clearfix" style="width: 100%;" align="center">
					<h1 style="background-color:brown;color:#ffffff">项目实战课程反馈统计表</h1>
				</div>
				<div class="form-group" style="height: 100px;">
					<form role="form" style="align-content: ;">
						<div align="center" id="top">
							<table class="table table-striped">
								<tr>
                                    <td id="getCourceName">
										<span>课程名称：</span>
											<select style="width: 170px;" id="courceselect" 
											onchange="showtheteacher()">
											</select>
                                     </td>
									<td>
                                        <span>课程日期：</span>
											<select style="width: 170px;" id="dateByname">
											</select>
                                        
                                    </td>
									<td>中心名称：<input type="text" value="成都中心" /></td>
								</tr>
								<tr>
									<td > 
									    <span>培训讲师：</span>
									        <select style='width: 170px;' id="showteacherBycourse"
									        onchange="showDate()">
											
											</select>
									</td>
									<td id="stuNum">参培人数：<input type="text" value="" /></td>
									<td>说明：表格中数值表示人数</td>
								</tr>
							</table>
						</div>

					</form>
				</div>
				<div>
					<table class="table table-bordered table-striped">
					    <thead>
						<tr id="title">
							<td rowspan="2">项目</td>
							<td id="kind_bar" colspan="" align="center">授课过程</td>
						</tr>
						<tr style="color: ffffff;" id="kind">
						</tr>
						<tr id="content">
						<td>序号</td>
						</tr>
						</thead>
						<tbody id="score">
							<tr class="active" >
									
							</tr>
                        </tbody>
                        <tbody id="sum">
							
						</tbody>
						
							<tr class="warning" id="single">
								
							</tr>
						
							<tr class="active" id="avg">
								
				            </tr>
							<tr class="success" id="allAvg">
								
							</tr>
							<tr class="warning" id="allScore">
								

							</tr>
					</table>
				</div>
				 <div  class="menu"  style="margin: auto;width: 45%;">
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
		</div>

	</body>
</html>