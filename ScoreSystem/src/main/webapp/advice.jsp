<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>调查反馈</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js "></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js "></script>
    <script src="./js/Advice.js" type="text/javascript"></script>
    <script src="./js/PublicData.js" type="text/javascript"></script>

    <style type="text/css">
        body {
            background-color: #fdfbfe;

        }

        #top2 {
            float: right;
            margin-right: 20px;
        }

        #tp {
            float: right;
            margin-right: 20px;
        }

        div.menu ul {
            list-style: none; /* 去掉ul前面的符号 */
            margin: 0px; /* 与外界元素的距离为0 */
            padding: 0px; /* 与内部元素的距离为0 */
            width: auto; /* 宽度根据元素内容调整 */
        }

        /* 所有class为menu的div中的ul中的li样式 */
        div.menu ul li {
            float: left; /* 向左漂移，将竖排变为横排 */
        }

        /* 所有class为menu的div中的ul中的a样式(包括尚未点击的和点击过的样式) */
        div.menu ul li a, div.menu ul li a:visited {
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
        div.menu ul li a:hover {
            background-color: #bfcbd6; /* 背景色 */
            color: #465c71; /* 文字颜色 */
            text-decoration: none; /* 不显示超链接下划线 */
        }

        /* 所有class为menu的div中的ul中的a样式(鼠标点击元素时的样式) */
        div.menu ul li a:active {
            background-color: #465c71; /* 背景色 */
            color: #cfdbe6; /* 文字颜色 */
            text-decoration: none; /* 不显示超链接下划线 */
        }
    </style>
    <script type="text/javascript">
        var process =${requestScope.process};
        var tciass_id=${requestScope.tclass_id};
        $(document).ready(function () {
            $("#admin_bar").hide();
            $("#feedback_bar").hide();
            $("#new").hide();
            if (process == 2) {
                $("#new").show();

                $("#feedback_bar").show();
            } else if (process == 1) {
                $("#new").hide();
            } else if (process == 3) {
                $("#new").hide();
                $("#admin_bar").show();
            }
            $("#container").hide();
            $("#container2").show();
            showclass();
        });

        function show (flag){
            $("#container").show();
            $("#container2").hide();
            showCource();
        }
        function showCource(){
            $.ajax({
                type:"post",
                url:"getAllCourceMessage.do",
                dataType:"json",
                data:{
                    "tclass_id":tciass_id
                },
                success:function(courceList){
                    var cource;
                    var courceInf="";
                    for(var i=0;i<courceList.length;i++){
                        cource=courceList[i];
                        courceInf+="<option value='"+cource.name+"'>"+cource.name+"</option>";

                    }
                    $("#showcource").html(courceInf);
                    showTeacher();
                },
                error:function(e){

                }
            });

        }
        function showTeacher(){
            var abc=$("#showcource").val();

            $.ajax({
                type:"post",
                url:"getAllTeacherMessage.do",
                dataType:"json",
                data:{
                    "name":abc
                },
                success:function(teacherList){
                    var teacher;
                    var teacherInf="";
                    for(var i=0;i<teacherList.length;i++){

                        teacher=teacherList[i];
                        teacherInf+="<option value='"+teacher.name+"'>"+teacher.name+"</option>";
                    }
                    $("#showteacher").html(teacherInf);
                    showTime();
                },
                error:function(e){

                }
            });
        }
        function showTime(){
            var ac=$("#showcource").val();
            var bc=$("#showteacher").val();
            $.ajax({
                type:"post",
                url:"getAllTimeMessage.do",
                dataType:"json",
                data:{
                    "courcename":ac,
                    "teachername":bc
                },
                success:function(dateList){

                    var date;
                    var dateInf="";
                    for(var i=0;i<dateList.length;i++){
                        date=dateList[i];
                        dateInf+="<option value='"+date.date+"'>"+date.date+"</option>";
                    }
                    $("#showtime").html(dateInf);
                    getStu();
                },
                error:function(){

                }
            });
        }
        function getStu(){
            var qw=$("#showcource").val();
            var er=$("#showteacher").val();
            var ty=$("#showtime").val();
            $.ajax({
                type:"post",
                url:"getStuMessage.do",
                dataType:"text",
                data:{
                    "courcename1":qw,
                    "teachername1":er,
                    "date":ty
                },
                success:function (stu){
                    var num = parseInt(stu);
                    $("#get").html("<p onchange='getStu()'>"+num+"</p>");

                },
                error:function(){

                }
            });
        }
        function getId(){
            var qw=$("#showcource").val();
            var er=$("#showteacher").val();
            var ty=$("#showtime").val();
            $.ajax({
                type:"post",
                url:"getIdMessage.do",
                dataType:"text",
                data:{
                    "courcename2":qw,
                    "teachername2":er,
                    "date2":ty
                },
                success:function (courceId){
                    add(courceId);
                }
            });
        }
        function add(courceId){
            var sum =$("#summary").val();
            var adv=$("#advice").val();
            $.ajax({
                type:"post",
                url:"insertAdvice.do",
                dataType:"text",
                data:{
                    "summary":sum,
                    "advice":adv,
                    "cource_id":courceId
                },
                success:function (inf){
                    if(inf==SUCCESS){
                        alert("提交成功");

                    }else {
                        alert("提交失败");

                    }

                },
                error:function(e){

                }
            });
        }
        function show1(){
            $("#container").hide();
            $("#container2").show();
        }
        function showclass(){
            $.ajax({
                type:"post",
                url:"getAllCourceMessage.do",
                dataType:"json",
                data:{
                    "tclass_id":tciass_id
                },
                success:function(courceList){
                    var cource;
                    var courceInf="";
                    for(var i=0;i<courceList.length;i++){
                        cource=courceList[i];
                        courceInf+="<option value='"+cource.name+"'>"+cource.name+"</option>";

                    }
                    $("#p1").html(courceInf);
                    showteachername();
                },
                error:function(e){

                }
            });

        }
        function showteachername(){
            var abc=$("#p1").val();

            $.ajax({
                type:"post",
                url:"getAllTeacherMessage.do",
                dataType:"json",
                data:{
                    "name":abc
                },
                success:function(teacherList){
                    var teacher;
                    var teacherInf="";
                    for(var i=0;i<teacherList.length;i++){

                        teacher=teacherList[i];
                        teacherInf+="<option value='"+teacher.name+"'>"+teacher.name+"</option>";
                    }
                    $("#p2").html(teacherInf);
                    showdate();
                },
                error:function(e){

                }
            });
        }
        function showdate(){
            var ac=$("#p1").val();
            var bc=$("#p2").val();
            $.ajax({
                type:"post",
                url:"getAllTimeMessage.do",
                dataType:"json",
                data:{
                    "courcename":ac,
                    "teachername":bc
                },
                success:function(dateList){

                    var date;
                    var dateInf="";
                    for(var i=0;i<dateList.length;i++){
                        date=dateList[i];
                        dateInf+="<option value='"+date.date+"'>"+date.date+"</option>";
                    }
                    $("#p3").html(dateInf);
                    showstu();
                },
                error:function(){

                }
            });
        }
        function showstu(){
            var qw=$("#p1").val();
            var er=$("#p2").val();
            var ty=$("#p3").val();
            $.ajax({
                type:"post",
                url:"getStuMessage.do",
                dataType:"text",
                data:{
                    "courcename1":qw,
                    "teachername1":er,
                    "date":ty
                },
                success:function (stu){
                    var num = parseInt(stu);
                    $("#p4").html("<p onchange='getStu()'>"+num+"</p>");
                    showsummary();
                },
                error:function(){

                }
            });
        }
        function showsummary(){
            var cource=$("#p1").val();
            $.ajax({
                type:"post",
                url:"showAllMessage.do",
                dataType:"json",
                data:{
                    "name":cource
                },
                success:function (adviceList){
                    var summaryInf="";
                    var summary;
                    for(var i=0;i<adviceList.length;i++){
                        summary=adviceList[i];
                        summaryInf+=""+summary.summary+"";
                    }
                    $("#getsummary").html(summaryInf);
                    showAdvice();
                },
                error:function(){

                }
            });
        }

        function showAdvice(){
            var cource=$("#p1").val();
            $.ajax({
                type:"post",
                url:"showAdvice.do",
                dataType:"json",
                data:{
                    "courcename":cource
                },

                success:function (adviceList){
                    var summaryInf="";
                    var summary;
                    for(var i=0;i<adviceList.length;i++){
                        summary=adviceList[i];
                        summaryInf+=""+summary.advice+"";
                    }
                    $("#getadvice").html(summaryInf);
                },
                error:function(){

                }
            });
        }



    </script>

</head>
<body>
<div id="container" class="container">
    <div id="head" class=" row " style=" height: 97px; " id="header">
        <div class="logo " style="margin-top: 30px; margin-left: 2cm;">
            <img src="http://www.farsight.com.cn/templets/default/images/logo.png  " alt=" " width="315 " height="42 ">
        </div>
        <div id="tp">
            <button id="new1" type="button" class="btn btn-default" onclick="javascript:show1();"
                    onchange="showteachername();">返回
            </button>
        </div>
    </div>
    <div id="body">
        <div id="top" style="background-color: aliceblue;">
            <h3 class="text-center">项目实战课程反馈表</h3>
        </div>
        <div id="mid1">
            <table class="table table-condensed table-bordered">
                <tbody>
                <tr>
                    <td width="20%">
                        课程名称：
                    </td>
                    <td width="30%">
                        <select style="width: 100%;" id="showcource" onchange="showTeacher()">

                        </select>
                    </td>
                    <td width="20%">
                        课程日期：
                    </td>
                    <td width="30%">
                        <select style="width: 100%;" id="showtime" onchange="showstu()">

                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%">
                        培训讲师：
                    </td>
                    <td width="30%">
                        <select style="width: 100%;" id="showteacher" onchange="showTime()">

                        </select>
                    </td>
                    <td width="20%">
                        参培人数：
                    </td>
                    <td width="30%">
                        <div id="get" onchange="showsummary();">

                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div id="mid2">
            <h4 class="text-left" style="background-color: azure;">
                班主任调查总结：
            </h4>
        </div>
        <div id="mid3">
            <textarea name="textarea" rows="3" cols="" style="width: 100%; height: 200px;" id="summary"
                      onchange="showAdvice();"></textarea>
        </div>
        <div id="mid4">
            <h4 class="text-left" style="background-color: azure;">
                班主任个人建议及解决措施 :
            </h4>
        </div>
        <div id="mid5">
            <textarea name="textarea" rows="3" cols="" style="width: 100%; height: 200px;" id="advice"></textarea>
        </div>
        <div id="mid6" style="margin: auto;">
            <button type="button" class="btn btn-default btn-block" onclick="getId()">提交</button>
        </div>
    </div>

</div>
<div id="container2" class="container">


    <div id="head" class=" row " style=" height: 97px; " id="header">
        <div class="logo " style="margin-top: 30px; margin-left: 2cm;">
            <img src="http://www.farsight.com.cn/templets/default/images/logo.png  " alt=" " width="315 " height="42 ">
        </div>
        <div id="top2">
            <button id="new" type="button" class="btn btn-default" onclick="javascript:show();">新建</button>
        </div>
    </div>
    <div id="body">
        <div id="top" style="background-color: aliceblue;">
            <h3 class="text-center">项目实战课程反馈表</h3>
        </div>
        <div id="mid1">
            <table class="table table-condensed table-bordered">
                <tbody>
                <tr id="t1">
                    <td width="20%">
                        课程名称：
                    </td>
                    <td width="30%">
                        <select style="width: 100%;" id="p1" onchange="showteachername()">

                        </select>
                    </td>
                    <td width="20%">
                        课程日期：
                    </td>
                    <td width="30%">
                        <select style="width: 100%;" id="p3">

                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%">
                        培训讲师：
                    </td>
                    <td width="30%">
                        <select style="width: 100%;" id="p2" onchange="showdate();">

                        </select>
                    </td>
                    <td width="20%">
                        参培人数：
                    </td>
                    <td width="30%">
                        <div id="p4">

                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div id="mid2">
            <h4 class="text-left" style="background-color: azure;">
                班主任调查总结：
            </h4>
        </div>
        <div id="mid3">
            <textarea id="getsummary" style="width: 100%; height: 200px; background-color:#fdfbfe;" disabled="disabled"
                      onchange=" showAdvice()"></textarea>
        </div>
        <div id="mid4">
            <h4 class="text-left" style="background-color: azure;">
                班主任个人建议及解决措施 :
            </h4>
        </div>
        <div id="mid5">
            <textarea id="getadvice" style="width: 100%; height: 200px; background-color:#fdfbfe;"
                      disabled="disabled"></textarea>
        </div>
    </div>
    <div class="menu" style="margin: auto;width: 46%;">
        <ul>
            <li>
                <a href='toFeedback.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}' id='feedback_bar'>学生反馈</a>
            </li>
            <li>
                <a href='toAllgrade.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}'>各项统计</a>
            </li>
            <li >
                <a href='toPercent.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}'>百分比统计</a>
            </li>
            <li>
                <a href='toAdvice.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}'>教师反馈</a>
            </li>
            <li >
                <a href='toAdmin_manage.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}' id='admin_bar'>管理员管理</a>
            </li>
        </ul>
    </div>
</div>
<div id="feet" style="height: 110px; margin-top: 50px; " id="footer">
    <p class="u-foot" style="color: #767074;
				text-align: center;">Copyright 2004-2017 华清远见教育集团 版权所有 ，京ICP备16055436号</p>
</div>
</body>
</html>
