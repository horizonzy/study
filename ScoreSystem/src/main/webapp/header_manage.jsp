<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
	<title>内容列表页面</title>
	<link href="css/all.css" rel="stylesheet" type="text/css" />
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
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
		#myModel1 {
			position: absolute;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			background-color: #CCCCCC88;
			display: none;
		}
		#model-content1 {
			width: 30%;
			height: 30%;
			margin: auto;
			position: relative;
			background-color: #fffffe;
			margin-top: 145px;
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
	<script type="text/javascript">
		var process=${requestScope.process};


		var currentPage=1;
		var allPage=0;
		var allStuCount=0;
        $(document).ready(function () {

            $("#headerTeacher_bar").hide();
            $("#admin_bar").hide();
            $("#feedback_bar").hide();
            if(process==2){
                $("#headerTeacher_bar").show();
                $("#feedback_bar").show();
            }else if(process==3){
                $("#admin_bar").show();
            }
            $.ajax({
                type:"post",
                dataType:"json",
                url:"getAllTclass.do",
                success:function (tclassList) {
                    var str="";
                    for(var i=0;i<tclassList.length;i++){
                        var element=document.getElementById("tclass1");
                        var tclass=tclassList[i];
                        var param=document.createElement("option");
                        param.value=tclass.id;
                        param.innerHTML=tclass.name;
                        element.appendChild(param);
                        var element1=document.getElementById("tclass2");
                        var tclass1=tclassList[i];
                        var param1=document.createElement("option");
                        param1.value=tclass.id;
                        param1.innerHTML=tclass.name;
                        element1.appendChild(param1);
//                        str+="<option value='"+tclass.id+"'>"+tclass.name+"</option>";
                    }
                },
                error:function (error) {

                }
            });
            getAllStu();

        });
		function hideModel() {
			$("#myModel").css("display","none");
        }
        function showModel(stuid,name,sex,num,pwd,tclassid) {
            $("#tclass1").val(tclassid);
			$("#myModel").css("display","block");
            $("#stuid").val(stuid);
            $("#name1").val(name);
            $("#num1").val(num);
            $("#pwd1").val(pwd);
            $(":radio[name='sex1'][value='" + sex + "']").prop("checked", "checked");
        }
        function hideModel1() {
            $("#myModel1").css("display","none");
        }
        function showModel1(stuid,name,sex,num,pwd,tclassid) {
            $("#myModel1").css("display","block");
        }

		function getAllStu(flag) {
			var tclass_name=$("#tclass_name").val();
			var name=$("#name").val();
            if(flag=="next"){
                if(currentPage<allPage){
                currentPage++;
                }
            }else if(flag=="pre"){
                if(currentPage>=2){
                currentPage--;
                }
            }else if(flag=="first"){
                currentPage=1;
            }else if(flag=="last"){
                currentPage=allPage;
            }else if(flag=="go"){
				var goPage = $("#goPage").val();
				if(goPage<=allPage&&goPage>=1){
				    currentPage=goPage;
				}else if(goPage>allPage){
				    currentPage=allPage;
                    $("#goPage").val(allPage);
				}else if(goPage<1){
				    currentPage=1;
                    $("#goPage").val(1);
				}
			}
			$.ajax({
				type:"post",
				dataType:"json",
				url:"getAllStu.do",
				data:{
				    "tclass_name":tclass_name,
					"name":name,
					"currentPage":currentPage
				},
				success:function (stuList) {
					var str="";
					str+="<tr> <th><input type=\"checkbox\" id=\"all\" onclick=\"#\"/></th>";
					str+="<th>编号</th>";
					str+=" <th>姓名</th>";
					str+="<th>性别</th>";
					str+="<th>学号</th>";
					str+="<th>班级</th>";
					str+="<th>密码</th>";
					str+="<th>操作</th></tr>";
					for(var i=0;i<stuList.length;i++){
					    var stu=stuList[i];
					    if(i%2!=0){
                            str+="<tr style='background-color:#ECF6EE;'>";
                        }else {
                            str+="<tr>";
                        }
                        str+="<td><input type=\"checkbox\" name=\"id\" value='"+stu.id+"'/></td>";
					    str+="<td>"+(i+1)+"</td>";
						str+="<td>"+stu.name+"</td>";
                        str+="<td>"+stu.sex+"</td>"
                        str+="<td>"+stu.num+"</td>"
                        str+="<td>"+stu.tclass.name+"</td>"
                        str+="<td>"+stu.pwd+"</td>"
                        str+=" <td> <a href='javascript:showModel("+stu.id+",\""+stu.name+"\",\""+stu.sex+"\",\""+stu.num+"\",\""+stu.pwd+"\",\""+stu.tclass.id+"\")'>修改</a>&nbsp;&nbsp;&nbsp;<a href='javascript:deleteOne("+stu.id+")'>删除</a> </td> </tr>";

					}
					$("#t_body1").html(str);
                },
				error:function (error) {
					alert("查找失败");
                }
            });
            //获取总页面
            $.ajax({
                type:"post",
                dataType:"text",
                url:"getAllPage.do",
                data:{
                    "tclass_name":tclass_name,
                    "name":name
                },
                success:function (all_Page) {
					 allPage=parseInt(all_Page);
                    document.getElementById("prompt").innerHTML=currentPage+"/"+allPage;
                },
                error:function (error) {

                }
            });
            //获取学生数量
            $.ajax({
                type:"post",
                dataType:"text",
                url:"getAllStuCount.do",
                data:{
                    "tclass_name":tclass_name,
                    "name":name
                },
                success:function (all_StuCount) {
					allStuCount=parseInt(all_StuCount);
					document.getElementById("stu_num").innerHTML=all_StuCount;
                },
                error:function (error) {

                }
            });
        }
        function deleteOne(id) {
			$.ajax({
				type:"post",
				dataType:"text",
				url:"deleteOne.do",
				data:{
				  "id":  id
				},
				success:function (inf) {
				    if(inf==SUCCESS){
					alert("删除成功");
                    }else {
                        alert("删除失败");
					}
					getAllStu();
                },
				error:function (error) {
					alert("删除失败");
					getAllStu();
                }
            });
        }
        function updateStu() {
            var id=$("#stuid").val();
            var name=$("#name1").val();
            var sex = $('input[id="sex1"]').filter(':checked').val();
            var num=$("#num1").val();
			var pwd=$("#pwd1").val();
            var tclass_id1 = $("#tclass1 option:selected").val();
			hideModel();
            $.ajax({
                type:"post",
                dataType:"text",
                url:"updateStu.do",
                data:{
                    "id":  id,
					"name":name,
					"sex":sex,
					"num":num,
					"pwd":pwd,
					"tclass_id":tclass_id1
                },
                success:function (inf) {
                    if(inf==SUCCESS){
                        alert("修改成功");
					}else{
                        alert("删除失败");
					}
                    getAllStu();
                },
                error:function (error) {
                    alert("修改失败");
                    getAllStu();
                }
            });

        }
        function deleteBatch() {

            var stusCheckBox = $("input[type='checkbox']:checked");
            var stusid = [];
            for(var i=0;i<stusCheckBox.length;i++){
                //使用[]取得元素是是一个domElement元素，取值需要使用.value，
                //如果使用stusCheckBox.eq(i) 则是一个Obkject元素，就可以使用val()取值
                //alert(stusCheckBox[i].value);
                mysendstu_id = {};
                mysendstu_id['stu_id'] = stusCheckBox[i].value;
                stusid[i] = mysendstu_id;
            }
            //alert(booksid);
            var confirmdel= confirm('确认要删除吗?');
            if(confirmdel){
                //开始请求删除
                $.ajax({
                    url:'deleteBatch.do',
                    data:JSON.stringify(stusid),
                    type:'post',
                    success:function(res){
                        alert("删除成功");
                        getAllStu();
                    },
                    error:function () {
                        alert("删除失败");
                        getAllStu();
                    }
                });
            }
        }
        function insertStu() {
            var name=$("#name2").val();
            var sex = $('input[id="sex2"]').filter(':checked').val();
            var num=$("#num2").val();
            var pwd=$("#pwd2").val();
            var tclass_id1 = $("#tclass2 option:selected").val();
            hideModel1();
			$.ajax({
				type:"post",
				datatype:"text",
				url:"insertStu.do",
				data:{
				    "name":name,
					"sex":sex,
					"num":num,
					"pwd":pwd,
					"tclass_id":tclass_id1
				},
				success:function (inf) {
					if(inf==SUCCESS){
					    alert("增加成功");
					}else {
                        alert("增加失败");
					}
					getAllStu();
                },
				error:function () {
					alert("增加失败");
                    getAllStu();
                }
			});
        }
	</script>
</head>
<body style="background: #fdfbfe;">
<div class=" row " style=" height: 97px; " id="header">
	<div class="logo " style="margin-top: 30px; margin-left: 2cm;">
		<img src="http://www.farsight.com.cn/templets/default/images/logo.png " alt=" " width="315 " height="42 ">
	</div>
</div>
<form action="" id="mainForm" method="post">
	<div class="right">
		<div class="rightCont">
			<p class="g_title fix"><a class="btn03" href="javascript:showModel1()">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03" href="javascript:deleteBatch()">删 除</a></p>
			<table class="tab1">
				<tbody>
				<tr>
					<td width="90" align="right">班级：</td>
					<td>
						<input id="tclass_name" name="tclass_name" type="text" class="allInput" value=""/>
					</td>
					<td width="90" align="right">姓名：</td>
					<td>
						<input id="name" name="name" type="text" class="allInput" value=""/>
					</td>
					<td width="85" align="right"><input type="button" class="tabSub" value="查 询"  onclick="getAllStu()"/></td>
				</tr>
				</tbody>
			</table>
			<div class="zixun fix">
				<table class="tab2" width="100%">
					<tbody id="t_body1">
					<tr>
						<th><input type="checkbox" id="all" onclick="#"/></th>
						<th>编号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>学号</th>
						<th>班级</th>
						<th>密码</th>
						<th>操作</th>
					</tr>
					<tr>
						<td><input type="checkbox" /></td>
						<td>1</td>
						<td>小明</td>
						<td>男</td>
						<td>201413060219</td>
						<td>javaEE</td>
						<td>123456</td>

						<td>
							<a href="#">修改</a>&nbsp;&nbsp;&nbsp;
							<a href="#">删除</a>
						</td>
					</tr>
					<tr style="background-color:#ECF6EE;">
						<td><input type="checkbox" /></td>
						<td>2</td>
						<td>小华</td>
						<td>男</td>
						<td>201413061291</td>
						<td>嵌入式</td>
						<td>xiaohua</td>
						<td>
							<a href="#">修改</a>&nbsp;&nbsp;&nbsp;
							<a href="#">删除</a>
						</td>
					</tr>
					<tr>
						<td><input type="checkbox" /></td>
						<td>3</td>
						<td>小霞</td>
						<td>女</td>
						<td>2014121024</td>
						<td>HTML</td>
						<td>xiaoxia</td>

						<td>
							<a href="#">修改</a>&nbsp;&nbsp;&nbsp;
							<a href="#">删除</a>
						</td>
					</tr>
					<tr style="background-color:#ECF6EE;">
						<td><input type="checkbox" /></td>
						<td>2</td>
						<td>李翔</td>
						<td>男</td>
						<td>20140217212</td>
						<td>JaveEE</td>
						<td>lixiang</td>
						<td>
							<a href="#">修改</a>&nbsp;&nbsp;&nbsp;
							<a href="#">删除</a>
						</td>
					</tr>
					<tr>
						<td><input type="checkbox" /></td>
						<td>3</td>
						<td>小霞</td>
						<td>女</td>
						<td>2014121024</td>
						<td>HTML</td>
						<td>xiaoxia</td>

						<td>
							<a href="#">修改</a>&nbsp;&nbsp;&nbsp;
							<a href="#">删除</a>
						</td>
					</tr>

					</tbody>
				</table>
				<div class='page fix'>
					共 <b id="stu_num">4</b> 条
					<a href='javascript:getAllStu("first")' class='first'>首页</a>
					<a href='javascript:getAllStu("pre")' class='pre'>上一页</a>
					当前第<span id="prompt">1/1</span>页
					<a href='javascript:getAllStu("next")' class='next'>下一页</a>
					<a href='javascript:getAllStu("last")' class='last'>末页</a>
					跳至&nbsp;<input type='text' value='1' id="goPage" class='allInput w28' />&nbsp;页&nbsp;
					<a href='javascript:getAllStu("go")' class='go'>GO</a>
				</div>
			</div>
		</div>
	</div>
</form>
<div id="myModel">
	<div id="model-content" align="center" style="text-align: center;">
		<table  border="1" align="center" style="width: 70%;height: 70%">
			<input type="hidden" name="stuid" id="stuid" />
			<tr>
				<td><label for="name1">姓名:</label></td>
				<td><input style="width: 40%;" type="text" name="name1" id="name1" placeholder="请输入姓名"/></td>
			</tr>
			<tr>
				<td><label for="sex1">性别:</label></td>
				<td>男<input  style="width: 20%" type="radio" name="sex1" id="sex1" value="男" checked="checked"/> 女<input
						style="width: 20%" type="radio" name="sex1" id="sex1" value="女" />
				</td>
			</tr>
			<tr>
				<td><label for="num1">学号:</label></td>
				<td><input style="width: 40%;" type="text" name="num1" id="num1" placeholder="请输入学号"/></td>
			</tr>
			<tr>
				<td><label for="pwd1">密码:</label></td>
				<td><input style="width: 40%;" type="text" name="pwd1" id="pwd1" placeholder="请输入密码"/></td>
			</tr>
			<tr>
				<td><label for="tclass1">班级:</label></td>
				<td><select name="tclass1" id="tclass1" style="width: 40%;">

					<!--<option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option> -->
				</select></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="button" name="button" id="button"
									   value="修改" onclick="updateStu()" />
					<input type="button" name="button" id="button"
						   value="取消" onclick="hideModel();" />
				</td>
			</tr>
		</table>
	</div>
</div>
<div id="myModel1">
	<div id="model-content1" align="center" style="text-align: center;">
		<table class="table"  border="1" align="center" style="width: 100%;height: 100%">
			<input type="hidden" name="stuid1" id="stuid1" />
			<tr>
				<td><label for="name2">姓名:</label></td>
				<td><input style="width: 40%;" type="text" name="name2" id="name2" placeholder="请输入姓名"/></td>
			</tr>
			<tr>
				<td><label for="sex2">性别:</label></td>
				<td>男<input   style="width: 20%" type="radio" name="sex2" id="sex2" value="男" checked="checked"/> 女<input
						style="width: 20%"	type="radio" name="sex2" id="sex2" value="女" />
				</td>
			</tr>
			<tr>
				<td><label for="num2">学号:</label></td>
				<td><input style="width: 40%;" type="text" name="num2" id="num2" placeholder="请输入学号"/></td>
			</tr>
			<tr>
				<td><label for="pwd2">密码:</label></td>
				<td><input style="width: 40%;" type="text" name="pwd2" id="pwd2" placeholder="请输入密码"/></td>
			</tr>
			<tr>
				<td><label for="tclass2">班级:</label></td>
				<td><select name="tclass2" id="tclass2" style="width: 40%;">

					<!--<option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option> -->
				</select></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="button" name="button" id="button"
									   value="修改" onclick="insertStu()" />
					<input type="button" name="button" id="button"
						   value="取消" onclick="hideModel1();" />
				</td>
			</tr>
		</table>
	</div>
</div>
<div  class="menu"  style="margin: auto;width: 38%;">
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
<div class="row" style="height: 110px; margin-top: 50px;" id="footer">
	<p class="u-foot" style="color: #767074; text-align: center;">Copyright
		2004-2017 华清远见教育集团 版权所有 ，京ICP备16055436号</p>
</div>
</body>
</html>
