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
		<script src="js/PublicData.js"></script>
		<style type="text/css">

			#myModel {
				position: absolute;
				top: 0;
				left: 0;
				width: 100%;
				height: 100%;
				background-color: #CCCCCC88;
				display: none;
			}

			#model-content {
				width: 30%;
				height: 30%;
				margin: auto;
				position: relative;
				background-color: #fffffe;
				margin-top: 145px;
			}
		</style>
        <script>
            //重置密码
            function resetPwd() {
                var resetNum=$("#resetNum").val();
                $.ajax({
					type:"post",
					url:"resetPwd.do",
					dataType:"text",
					data:{
					    "resetNum":resetNum
					},
					success:function (inf) {
					    if(inf==SUCCESS) {
                            alert("你的密码已重置为：123456");
                        }else {
                            alert("重置失败");
						}
                    },
					erroe:function (error) {
						alert("重置失败");
                    }
				});
                hideModel();

            }
            function showModel() {
                $("#myModel").css("display", "block");
            }
            function hideModel() {
				$("#myModel").css("display","none");
            }
            function login() {
                var num=$("#num").val();
                var pwd=$("#pwd").val();
                var flag = $('input:radio:checked').val();
                if(flag=="学生"){
                    window.open("stuLogin.do?num="+num+"&pwd="+pwd, "_self");
				}else if(flag=="教师"){
                    window.open("teaLogin.do?num="+num+"&pwd="+pwd, "_self");
				}
            }
        </script>

	</head>

	<body style="background-color:#fdfbfe;">
		<div id="container" class="container">
			<div class=" row " style=" height: 97px; " id="header">
				<div class="logo " style="margin-top: 30px; margin-left: 2cm;">
					<img src="http://www.farsight.com.cn/templets/default/images/logo.png " alt=" " width="315 " height="42 ">
				</div>
			</div>
			<div style="text-align: center;" id="title">
				<h1>登录界面</h1>
				<hr>
			</div>
		</div>
		<div id="bodyer" style="margin-top: 30px;">

			<div id="login" style="width: 300px;height:300px; margin: auto;text-align: center;">
				账号：<input id="num" name="num" type="text" style="width: 255px;height: 30px;" placeholder="请输入账号"/>
				<br />
				<br />
				<br />
				密码：<input id="pwd" name="pwd" type="password" style="width: 255px;height: 30px;" placeholder="请输入密码"/>
					<br />	
					<br />
				<input type="radio" name="process" value="学生" checked/>&nbsp;&nbsp;学生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="process" value="教师" />&nbsp;&nbsp;教师
			
			    <br />
				<br />
				<button  class="btn btn-default btn-block" style="background-color: red;color: white;" onclick="login()">登录</button>
				<br />
			  <a href="modifyPwd.jsp">修改密码</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <a href="javascript:showModel();">忘记密码?</a>
				<br />
				<br />
				<div style="color: red;font-size: 20px;">${requestScope.inf}</div>
			</div>

			<div id="myModel">
				<div id="model-content" align="center" style="text-align: center;">
					<br />
					<br/>
					<br/>
					<br/>
					账号：<input type="text" placeholder="忘记的账号名" id="resetNum" name="resetNum"/>
					<br />
					<br/>
					<button onclick="resetPwd();" type="button"  style="background-color: red;color: white;width: 20%;">重置</button>

					<button onclick="hideModel();" type="button"  style="background-color: red;color: white;width: 20%;">取消</button>
				</div>
			</div>
		</div>
		<div class="row" style="height: 110px; margin-top: 50px; " id="footer">
			<p class="u-foot" style="color: #767074;
				text-align: center;">Copyright 2004-2017 华清远见教育集团 版权所有 ，京ICP备16055436号</p>
		</div>
	</body>

</html>