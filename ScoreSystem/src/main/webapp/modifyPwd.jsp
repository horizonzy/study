<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<!-- 新 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
			function modifyPwd() {
				$.ajax({
					type:"post",
					dataType:"text",
					url:"modifyPwd.do",
					data:{
					    "num":$("#num").val(),
						"oldPwd":$("#oldPwd").val(),
						"newPwd":$("#newPwd").val()
					},
					success:function (inf) {
                            window.open("login.jsp", "_self");
                            alert("修改成功");
                    },
					error:function (error) {
                        alert("修改失败");
                    }
				});
            }
        </script>
	</head>

	<body style="background-color:#fdfbfe;" >
		<div id="container" class="container">
			<div class=" row " style=" height: 97px; " id="header">
				<div class="logo " style="margin-top: 30px; margin-left: 2cm;">
					<img src="http://www.farsight.com.cn/templets/default/images/logo.png " alt=" " width="315 " height="42 ">
				</div>
			</div>
			<div style="text-align: center;" id="title">
				<h1>修改密码</h1>
				<hr>
			</div>
		</div>
		<div id="bodyer" style="margin-top: 30px;">
			<div id="login" style="width: 300px;height:300px; margin: auto;text-align: center;">
				账号：<input type="text" style="width: 255px;height: 30px;" placeholder="请输入账号" id="num" name="num"/>
				<br />
				<br />
				<br />
				原密码：<input type="password" style="width: 242px;height: 30px;" placeholder="请输入密码" id="oldPwd" name="oldPwd"/>
				<br />
				<br />
				<br />
				新密码：<input type="password" style="width: 242px;height: 30px;" placeholder="请输入密码" id="newPwd" name="newPwd"/>
				<br />
				<br />
				<br />
				
				<button onclick="modifyPwd();" type="button" class="btn btn-default btn-block" style="background-color: red;color: white;">确认修改</button>
				<br />
			</div>
		</div>
		<div class="row" style="height: 110px; margin-top: 50px; " id="footer">
			<p class="u-foot" style="color: #767074;
				text-align: center;">Copyright 2004-2017 华清远见教育集团 版权所有 ，京ICP备16055436号</p>
		</div>
	</body>

</html>