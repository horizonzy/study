<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>内容列表页面</title>
    <link href="css/all.css" rel="stylesheet" type="text/css"/>
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
    <script>
        var process = ${requestScope.process};
        var currentPage=1;
        var allPage=0;
        var allTclassCount=0;

        $(document).ready(function () {
           showAllTclass();
           $.ajax({
               type:"post",
               url:"getTeacherByProcess.do",
               dataType:"json",
               success:function (teacherList) {

                   for(var i=0;i<teacherList.length;i++){
                       var teacher = teacherList[i];
                       var element = document.getElementById("teacher");
                       var param = document.createElement("option");
                       param.value=teacher.name;
                       param.text=teacher.name;
                       element.appendChild(param);
                       var element1 = document.getElementById("teacher1");
                       var param1 = document.createElement("option");
                       param1.value=teacher.name;
                       param1.text=teacher.name;
                       element1.appendChild(param1);
                   }
               },
               erroe:function () {

               }
           });

        });

        function showAllTclass(flag) {
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
                url: "getAllTclass.do",
                type: "post",
                dataType: "json",
                data:{
                  "currentPage":currentPage
                },
                success: function (tclassList) {
                    var str = "";
                    str += "<tr> <th><input type=\"checkbox\" id=\"all\" onclick=\"#\"/></th>";
                    str += " <th>编号</th>";
                    str += " <th>班级</th>";
                    str += " <th>专业</th>";
                    str += " <th>班主任</th>";
                    str += "  <th>操作</th> </tr>";
                    for (var i = 0; i < tclassList.length; i++) {
                        var tclass = tclassList[i];
                        if(i%2!=0){
                            str+="<tr style='background-color:#ECF6EE;'>";
                        }else {
                            str+="<tr>";
                        }
                        str+="<td><input value='"+tclass.id+"' type=\"checkbox\"/></td>";
                        str+="<td>"+(i+1)+"</td>";
                        str+="<td><a href='toStuPage.do?process="+process+"&tclass_id="+tclass.id+"'>"+tclass.name+"</a></td>";
                        str+="<td>"+tclass.major+"</td>";
                        str+="<td>"+tclass.t_name+"</td>";
                        str+="<td> <a href='javascript:showModel("+tclass.id+",\""+tclass.name+"\",\""+tclass.major+"\",\""+tclass.t_name+"\");'>修改</a>&nbsp;&nbsp;&nbsp; <a href='javascript:deleteTclass("+tclass.id+")'>删除</a> </td> </tr>";
                    }
                    $("#t_body1").html(str);
                },

                error: function () {

                }
            });
            //获取总页面
            $.ajax({
                type:"post",
                dataType:"text",
                url:"getAllTclassPage.do",
                success:function (all_Page) {
                    allPage=parseInt(all_Page);
                    document.getElementById("prompt").innerHTML=currentPage+"/"+allPage;
                },
                error:function (error) {

                }
            });
            //获取班级数量
            $.ajax({
                type:"post",
                dataType:"text",
                url:"getAllTclassCount.do",
                success:function (all_TclassCount) {
                    allTclassCount=parseInt(all_TclassCount);
                    document.getElementById("stu_num").innerHTML=all_TclassCount;
                },
                error:function (error) {

                }
            });
        }
        function updateTclass() {
            hideModel();
            var id = $("#tclass_id").val();
            var name = $("#tclassName").val();
            var major = $("#tclassMajor").val();
            var teacher_name = $("#teacher option:selected").val();
            $.ajax({
                type:"post",
                url:"updateTclass.do",
                dataType:"text",
                data:{
                  "id":id,
                  "name":name,
                  "major":major,
                  "teacher_name":teacher_name
                },
                success:function (inf) {
                    if(inf=="success"){
                        alert("修改成功");
                    }else{
                        alert("修改失败");
                    }
                    showAllTclass();
                },
                error:function () {
                    alert("修改失败");
                    showAllTclass();
                }
            });

        }
        function deleteTclass(id) {
            $.ajax({
                type:"post",
                url:"deleteTclass.do",
                data:{
                  "id":id
                },
                dataType:"text",
                success:function (inf) {
                    if(inf=="success"){
                        alert("删除成功");
                    }else {
                        alert("删除失败");
                    }
                    showAllTclass();
                },
                error:function () {
                    alert("删除失败");
                    showAllTclass();
                }

            });
        }
        function deleteBatch() {
            var arr = $("input[type='checkbox']:checked");
            var tlcassIds = [];
            for (var i=0;i<arr.length;i++){
                var object = {};
                object['tclass_id']=arr[i].value;
                tlcassIds[i]=object;
            }

            var confirmdel= confirm('确认要删除吗?');
            if(confirmdel){
                //开始请求删除
                $.ajax({
                    url:'deleteBatch1.do',
                    data:JSON.stringify(tlcassIds),
                    type:'post',
                    dataType:"text",
                    success:function(res){
                        alert("删除成功");
                        showAllTclass();
                    },
                    error:function () {
                        alert("删除失败");
                        showAllTclass();
                    }
                });
            }

        }
        function insertTclass() {
            hideModel1();
            var name=$("#tclassName1").val();
            var major=$("#tclassMajor1").val();
            var teacher_name=$("#teacher1 option:selected").val();
            $.ajax({
                type:"post",
                url:"insertTclass.do",
                dataType:"text",
                data:{
                    "name":name,
                    "major":major,
                    "teacher_name":teacher_name
                },
                success:function (inf) {
                    if(inf=="seccess"){
                        alert("增加成功")
                    }else {
                        alert("增加失败");
                    }
                    showAllTclass();
                },
                error:function () {
                    alert("增加失败");
                    showAllTclass();
                }
            });
            var name=$("#tclassName1").val("");
            var major=$("#tclassMajor1").val("");
        }
        function showModel(tclass_id,tclassName,tclassMajor,t_name) {
            $("#myModel").css("display","block");
            $("#tclassName").val(tclassName);
            $("#tclassMajor").val(tclassMajor);
            $("#tclass_id").val(tclass_id);
            $("#teacher").val(t_name);
        }
        function hideModel() {
            $("#myModel").css("display","none");
        }
        function hideModel1() {
            $("#myModel1").css("display","none");
        }
        function showModel1() {
            $("#myModel1").css("display","block");
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
            <p class="g_title fix"><a class="btn03" href="javascript:showModel1()">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                    class="btn03" href="javascript:deleteBatch()">删 除</a></p>

            <div class="zixun fix">
                <table class="tab2" width="100%">
                    <tbody id="t_body1">
                    <tr>
                        <th><input type="checkbox" id="all" onclick="#"/></th>
                        <th>编号</th>
                        <th>班级</th>
                        <th>专业</th>
                        <th>班主任</th>
                        <th>操作</th>
                    </tr>
                    <tr>
                        <td><input type="checkbox"/></td>
                        <td>1</td>
                        <td><a href='toStuPage.do?'>一班</a></td>
                        <td>JaveEE</td>
                        <td>张玉</td>
                        <td>
                            <a href="#">修改</a>&nbsp;&nbsp;&nbsp;
                            <a href="#">删除</a>
                        </td>
                    </tr>

                    <tr>
                        <td><input type="checkbox"/></td>
                        <td>2</td>
                        <td>二班</td>
                        <td>HTMl</td>
                        <td>周老师</td>
                        <td>
                            <a href="#">修改</a>&nbsp;&nbsp;&nbsp;
                            <a href="#">删除</a>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="checkbox"/></td>
                        <td>3</td>
                        <td>三班</td>
                        <td>嵌入式</td>
                        <td>班主任</td>
                        <td>
                            <a href="#">修改</a>&nbsp;&nbsp;&nbsp;
                            <a href="#">删除</a>
                        </td>
                    </tr>

                    </tbody>
                </table>
                <div class='page fix'>
                    共 <b id="stu_num">4</b> 条
                    <a href='javascript:showAllTclass("first")' class='first'>首页</a>
                    <a href='javascript:showAllTclass("pre")' class='pre'>上一页</a>
                    当前第<span id="prompt">1/1</span>页
                    <a href='javascript:showAllTclass("next")' class='next'>下一页</a>
                    <a href='javascript:showAllTclass("last")' class='last'>末页</a>
                    跳至&nbsp;<input type='text' value='1' id="goPage" class='allInput w28' />&nbsp;页&nbsp;
                    <a href='javascript:getAllStu("go")' class='go'>GO</a>
                </div>
            </div>
        </div>
    </div>
</form>
<%--修改--%>
<div id="myModel">
    <div id="model-content" align="center" style="text-align: center;">
        <table border="1" align="center" style="width: 70%;height: 70%">
            <input type="hidden" name="tclass_id" id="tclass_id"/>
            <tr>
                <td><label for="tclassName">班级:</label></td>
                <td><input style="width: 40%;" type="text" name="tclassName" id="tclassName" placeholder="请输入班级" /></td>
            </tr>

            <tr>
                <td><label for="tclassMajor">专业:</label></td>
                <td><input style="width: 40%;" type="text" name="tclassMajor" id="tclassMajor" placeholder="请输入专业"/></td>
            </tr>

            <tr>
                <td><label for="teacher">班主任:</label></td>
                <td><select name="teacher" id="teacher" style="width: 40%;">

                </select></td>
            </tr>
            <tr align="center">
                <td colspan="2"><input type="button" name="button" id="button"
                                       value="修改" onclick="updateTclass()"/>
                    <input type="button" name="button" id="button"
                           value="取消" onclick="hideModel();"/>
                </td>
            </tr>
        </table>
    </div>
</div>

<%--新增--%>
<div id="myModel1">
    <div id="model-content1" align="center" style="text-align: center;">
        <table class="table" border="1" align="center" style="width: 100%;height: 100%">
            <input type="hidden" name="tclass_id1" id="tclass_id1"/>
            <tr>
                <td><label for="tclassName1">班级:</label></td>
                <td><input style="width: 40%;" type="text" name="tclassName1" id="tclassName1" placeholder="请输入班级" /></td>
            </tr>

            <tr>
                <td><label for="tclassMajor">专业:</label></td>
                <td><input style="width: 40%;" type="text" name="tclassMajor1" id="tclassMajor1" placeholder="请输入专业"/></td>
            </tr>

            <tr>
                <td><label for="teacher1">班主任:</label></td>
                <td><select name="teacher1" id="teacher1" style="width: 40%;">

                </select></td>
            </tr>

            <tr align="center">
                <td colspan="2"><input type="button" name="button" id="button"
                                       value="增加" onclick="insertTclass()"/>
                    <input type="button" name="button" id="button"
                           value="取消" onclick="hideModel1();"/>
                </td>
            </tr>
        </table>
    </div>
</div>

<div class="row" style="height: 110px; margin-top: 50px;" id="footer">
    <p class="u-foot" style="color: #767074; text-align: center;">Copyright
        2004-2017 华清远见教育集团 版权所有 ，京ICP备16055436号</p>
</div>
</body>
</html>
