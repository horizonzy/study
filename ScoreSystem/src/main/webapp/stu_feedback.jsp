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
	<script>

        var stu_id=${requestScope.stuid}
		var tclass_id = ${requestScope.tclass_id};
        $(document).ready(function() {
            showCource();
            getNumById();
            showKind();
        });
        function getNumById() {
            $.ajax({
                type : "post",
                url : "getNumById.do",
                dataType : "text",
                data : {
                    "id" : stu_id
                },
                success : function(num) {

                    showName(num);
                },
                error : function(e) {
                    alert("失败")

                }
            });

        }
        function showCource() {

            $.ajax({
                async : false,
                type : "post",
                url : "showCource.do",
                dataType : "json",
                data : {
                    "tclass_id" : tclass_id
                },
                success : function(List) {
                    var inf;

                    for (var i = 0; i < List.length; i++) {
                        var mid = List[i];
                        inf += "<tr>";      //+ +
                        inf += "<td >课程日期：<span id='date'> " +mid.cource_date +"</span></td>"
                        inf += "</tr>";
                        inf += "<tr>";
                        inf += "<td>课程名称： <span id='cname'>" + mid.cource_name + "</span></td>";
                        inf += "</tr>";
                        inf += "<tr>";
                        inf += "<td>讲师姓名：<span id='tname'>"  + mid.teacher_name + "</span></td>";
                        inf += "</tr>";
                    }

                    $("#aaa").html(inf);
                },
                error : function(e) {
                    alert("失败")

                }
            });
        }
        function showName(stu_num) {

            $.ajax({
                type : "post",
                url : "showName.do",
                dataType : "json",
                data : {
                    "num" :stu_num
                },
                success : function(stuList) {
                    var inf;
                    for (var i = 0; i < stuList.length; i++) {
                        var stu = stuList[i];
                        inf += "<tr>";
                        inf += "<td>学员姓名：" + stu.name + "</td>"
                        inf += "</tr>";
                    }
                    $("#name").html(inf);
                },
                error : function(e) {
                    alert("失败")
                }
            });
        }

        function showKind() {
            $.ajax({
                type : "post",
                url : "showkind.do ",
                dataType : "json",
                data : {

                },
                success : function(list) {

                    for (var i = 0; i < list.length; i++) {
                        // alert(list[i].k_id);
                        $.ajax({
                            async : false,
                            type : "post",
                            url : "showContent.do",
                            dataType : "json",
                            data : {
                                "kind_id" : list[i].k_id
                            },
                            success : function(ContentList) {
                                var contentStr = "";
                                for (var j = 0; j < ContentList.length; j++) {

                                    if (j == 0) {
                                        contentStr += "<tr>";
                                        contentStr += "<td rowspan='"
                                            + ContentList.length
                                            + "'>" + list[i].name
                                            + "</td>";
                                        contentStr += "<td>"
                                            + ContentList[j].c_name
                                            + "</td>";
                                        contentStr += "<td><select id='"+ContentList[j].c_id+"'><option value='5'>5</option><option value='4'>4</option><option value='3'>3</option><option value='2'>2</option><option value='1'>1</option></select></td>";
                                        contentStr += "</tr>";
                                    } else {
                                        contentStr += "<tr>";
                                        contentStr += "<td>"
                                            + ContentList[j].c_name
                                            + "</td>";
                                        contentStr += "<td><select id='"+ContentList[j].c_id+"'><option value='5'>5</option><option value='4'>4</option><option value='3'>3</option><option value='2'>2</option><option value='1'>1</option></select></td>";
                                        contentStr += "</tr>";
                                    }
                                }
                                ;
                                $("#content").append(contentStr);

                            },

                            error : function(e) {
                                alert(1);
                            }
                        });
                    }

                },
                error : function(e) {
                    alert(1);
                }

            });

        }

        function getcource_id(){
            var cname = $("#cname").text();
            var tname = $("#tname").text();
            var date = $("#date").text();
            $.ajax({
                type : "post",
                url : "getcource_id.do",
                dataType : "text",
                data : {
                    "courcename" : cname,
                    "teachername": tname,
                    "date":date
                },
                success : function(cource_id) {
                    getContent_id(parseInt(cource_id));
                    addAdvice(cource_id);
                },
                error : function(e) {
                    alert("得到id失败");
                }
            });
        }
        function getContent_id(cource_id) {

            $.ajax({
                type : "post",
                url : "showAllContent.do",
                dataType : "json",

                success : function(ContentList) {
                    var data=[];
                    for (var i = 0; i < ContentList.length; i++) {
                        var id=ContentList[i].c_id;
                        var grade =$("#"+id+"").val();
                        var object={};
                        object['content_id']=id;
                        object['grade']=grade;
                        object['cource_id']=cource_id;
                        object['stu_id']=stu_id;
                        data[i]=object;
                    }
                    goPost(data);
                },
                error : function(e) {
                    alert("得到id失败");
                }
            });
        }
        function goPost(data) {

            $.ajax({
                //async:false,
                type : "post",
                url : "addGrade.do",
                dataType : "text",
                data : JSON.stringify(data),
                success : function() {

                },
                error : function(e) {

                }
            });
        }
        function addAdvice(cource_id) {
            var description = $("#advice").val();

            $.ajax({
                type : "post",
                url : "addAdvice.do ",
                dataType : "text",
                data : {
                    "description":description,
                    "cource_id":cource_id
                },
                success: function() {
                    alert("提交成功");
                },
                error : function(e) {
                    alert("提交失败");
                }
            });
        }

	</script>
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