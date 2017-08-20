<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师课程反馈表</title>
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script type="text/javascript">

			 var stu_id=${requestScope.stuid}


	</script>
     <script src="js/s.js"></script>

<style type="text/css">
		body {
			background-color: #fdfbfe;
		}
		
		h3 {
			font-family: "微软雅黑";
			font-size: 32px;
			color: white;
			background-color: brown;
			text-align: center;
			width: 80%;
			margin: auto
			
		}
		
		.table {
			width: 80%;
			margin: auto;
		}
		
		select {
			width: 100%;
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
</style>

</head>
<body>
	<div class="container" id="container3">
		<div class=" row " style="height: 97px;" id="header">
			<div class="logo " style="margin-top: 30px; margin-left: 2cm;">
				<img
					src="http://www.farsight.com.cn/templets/default/images/logo.png "
					alt=" " width="315 " height="42 ">
			</div>
		</div>
		<div class="row " id="bodyer">
			<div class="feedback">
				<h3>课程反馈表</h3>
				<table class="table table-bordered ">

					<thead id="aaa">

					</thead>
					<tbody id="name">
					</tbody>
					<tr>
						<td>满意度说明：5分 - 很满意&nbsp;&nbsp;&nbsp;4分 - 满意
							&nbsp;&nbsp;&nbsp;3分 - 一般&nbsp;&nbsp;&nbsp;2分 -
							不满意&nbsp;&nbsp;&nbsp;1分 - 很不满意</td>
					</tr>

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
					        <tr>
								<td >评语</td>
								<td colspan="2"><textarea id="advice"  name="yoursuggest" cols ="50" rows = "3" style="width: 100%;"></textarea>
								</td>				
							</tr>
				</table>
				<div>
					<button type="submit" class="ss" onclick="getcource_id();">提交</button>
				</div>
			</div>

		</div>
		<div class="row" style="height: 110px; margin-top: 50px;" id="footer">
			<p class="u-foot" style="color: #767074; text-align: center;">Copyright
				2004-2017 华清远见教育集团 版权所有 ，京ICP备16055436号</p>
		</div>
	</div>

</body>
</html>