<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
    <title>管理列表页面</title>
    <link href="css/allty.css" rel="stylesheet" type="text/css"/>
    <style>
        #kind {
            position: absolute;
        }

        #content {
            position: absolute;
        }

        #teacher {
            position: absolute;
        }

        #class {
            position: absolute;
        }

        #cource {
            position: absolute;
        }
    </style>
    <script src="js/jquery-1.8.0.min.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="js/PublicData.js" type="text/javascript"></script>

    <script type="text/javascript">
        var process =${requestScope.process};
        var tciass_id =${requestScope.tclass_id};
        $(document).ready(function () {
            $("#kind").show();
            $("#content").hide();
            $("#teacher").hide();
            $("#class").hide();
            $("#cource").hide();

            $("#admin_bar").hide();
            $("#feedback_bar").hide();
            if (process == 2) {

                $("#feedback_bar").show();
            } else if (process == 3) {
                $("#admin_bar").show();
            }
            $.ajax({
                type: "post",
                url: "Kind/allKindNickName.do",
                dataType: "json",
                success: function (nickNameList) {
                    for (var i = 0; i < nickNameList.length; i++) {
                        var element = document.getElementById("kind_id");
                        var param = document.createElement("option");
                        param.value=nickNameList[i];
                        param.innerHTML=nickNameList[i];
                        element.appendChild(param);

                        var element1 = document.getElementById("contentKind_id");
                        var param1 = document.createElement("option");
                        param1.value=nickNameList[i];
                        param1.innerHTML=nickNameList[i];
                        element1.appendChild(param1);
                    }
                },
                error: function () {

                }
            });

        });

        function show(flag) {
            if (flag == 0) {
                $("#kind").show();
                $("#content").hide();
                $("#teacher").hide();
                $("#class").hide();
                $("#cource").hide();
            } else if (flag == 1) {
                $("#kind").hide();
                $("#content").show();
                $("#teacher").hide();
                $("#class").hide();
                $("#cource").hide();
            } else if (flag == 2) {
                $("#kind").hide();
                $("#content").hide();
                $("#teacher").show();
                $("#class").hide();
                $("#cource").hide();
            } else if (flag == 3) {
                $("#kind").hide();
                $("#content").hide();
                $("#teacher").hide();
                $("#class").show();
                $("#cource").hide();
            } else if (flag == 4) {
                $("#kind").hide();
                $("#content").hide();
                $("#teacher").hide();
                $("#class").hide();
                $("#cource").show();
            }
        }
    </script>
    <script type="text/javascript">
        var currentPage2 = 1;
        var totalPage2 = 0;

        function showAllKind(flag) {
            if (flag == "add") {
                currentPage2++;
            } else if (flag == "reduce") {
                currentPage2--;
            } else if (flag == "last") {
                currentPage2 = totalPage2;
            } else if (flag == "first") {
                currentPage2 = 1;
            }
            $.ajax({
                type: "post",
                url: "Kind/showAllKind.do",
                dataType: "json",
                data: {
                    "currentPage2": currentPage2
                },
                success: function (kindList) {
                    var kindInf;
                    kindInf;
                    kindInf += "<tr>";
                    kindInf += "<th><input type='checkbox'  onclick='#'/></th>";
                    kindInf += "<th>序号</th>";
                    kindInf += "<th>类别名</th>";
                    kindInf += "<th>别名</th>";
                    kindInf += "<th>操作</th>";
                    kindInf += "</tr>";
                    var kind;
                    for (var i = 0; i < kindList.length; i++) {
                        kind = kindList[i];
                        kindInf += "<tr>";
                        kindInf += "<td><input type='checkbox' value='" + kind.id + "' onclick='#'/></td>";
                        kindInf += "<td>" + kind.id + "</td>";
                        kindInf += "<td>" + kind.name + "</td>";
                        kindInf += "<td>" + kind.nick_name + "</td>";
                        kindInf += "<td><a href='javascript:showMyModel1(" + kind.id + ",\"" + kind.name + "\",\"" + kind.nick_name + "\");'>修改</a>&nbsp;&nbsp;&nbsp;<a href='javascript:deleteKindById(" + kind.id + ");'>删除</a></td>";
                        kindInf += "</tr>";
                    }

                    $("#kind1").html(kindInf);
                    showButton2();
                },
                error: function (e) {
                    alert("失败");
                }
            });
        }

        $(document).ready(function () {
            showAllKind();
        });

        function showButton2() {

            $.ajax({
                type: "post",
                url: "Kind/getTotalPage2.do",
                dataType: "text",
                data:
                    {},
                success: function (total_Page2) {
                    totalPage2 = parseInt(total_Page2);//总页数
                    var str = "";
                    if (currentPage2 == 0) {
                        currentPage2 = 1;
                    }
                    if (currentPage2 == 1 && currentPage2 < totalPage2) {//前面不可点,后面可点
                        str += "tr"
                        str += "<td><a href='#' >第一页&nbsp </a></td>";
                        str += "<td> <a href='#' > &nbsp上一页&nbsp </a></li>";
                        str += "<td> <a href='#' >&nbsp" + currentPage2 + "&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllKind(\"add\")'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllKind(\"last\")'>&nbsp最后一页&nbsp</a></td>";
                        str += "tr"
                    } else if (currentPage2 == 1 && currentPage2 == totalPage2) {//前后都不可点
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage2 + "&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp最后一页&nbsp</a></td>";
                    } else if (currentPage2 > 1 && currentPage2 == totalPage2) {//前面可点,后面不可点
                        str += "<td><a href='javascript:showAllKind(\"first\")' class='btn btn-default btn-link'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='javascript:showAllKind(\"reduce\")' class='btn btn-default btn-link'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage2 + "&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp最后一页&nbsp</a></td>";
                    } else if (currentPage2 > 1 && currentPage2 < totalPage2) {//前后都可点
                        str += "<td><a href='javascript:showAllKind(\"first\")' class='btn btn-default btn-link'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='javascript:showAllKind(\"reduce\")' class='btn btn-default btn-link'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage2 + "&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllKind(\"add\")'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllKind(\"last\")'>&nbsp最后一页&nbsp</a></td>";
                    }
                    $("#mypageul2").html(str);
                },
                error: function (msg) {

                }
            });
        }
    </script>
    <script type="text/javascript">
        function deleteKindById(kindId) {
            $.ajax({
                type: "post",
                url: "Kind/deleteKindById.do",
                dataType: "text",
                data: {
                    "kindId": kindId
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("删除成功")
                        showAllKind();//重新显示
                    } else {
                        alert("删除失败")
                        showAllKind();
                    }
                },
                error: function (e) {
                    alert("删除失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function showMyModel() {
            //显示蒙板

            $("#myModel").css("display", "block");
        }

        function addKind() {
            // 隐藏蒙板
            var name = $("#name").val();
            var nick_name = $("#nick_name").val();

            $.ajax({
                type: "post",
                url: "Kind/saveKind.do",
                dataType: "text",
                data: {
                    "name": name,
                    "nick_name": nick_name
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("增加成功")
                        $("#myModel").css("display", "none");
                        showAllKind();
                    } else {
                        alert("增加失败")
                        showAllKind();
                    }
                },
                error: function (e) {
                    alert("增加失败")
                }
            });
        }
    </script>
    <%--增加的id是nick_name--%>
    <script type="text/javascript">
        function showMyModel1(id, name, nick_name) {
            //显示蒙板
            $("#kindId").val(id);
            $("#kindName").val(name);
            $("#kind_nick_name").val(nick_name);
            $("#myModel1").css("display", "block");
        }

        function updateKind() {
            // 隐藏蒙板
            $.ajax({
                type: "post",
                url: "Kind/updateMyKind.do",
                dataType: "text",
                data: {
                    "id": $("#kindId").val(),
                    "name": $("#kindName").val(),
                    "nick_name": $("#kind_nick_name").val()
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("修改成功")
                        $("#myModel1").css("display", "none");
                        showAllKind();
                    } else {
                        alert("修改失败")
                        showAllKind();
                    }
                },
                error: function (e) {
                    alert("修改失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function deleteBatch() {
            var kindsCheckBox = $("input[type='checkbox']:checked");
            var kindsid = [];
            for (var i = 0; i < kindsCheckBox.length; i++) {
                //使用[]取得元素是是一个domElement元素，取值需要使用.value，
                //如果使用stusCheckBox.eq(i) 则是一个Obkject元素，就可以使用val()取值
                //alert(stusCheckBox[i].value);
                mysendkind_id = {};
                mysendkind_id['kind_id'] = kindsCheckBox[i].value;
                kindsid[i] = mysendkind_id;

            }
            //alert(booksid);
            var confirmdel = confirm('确认要删除吗?');
            if (confirmdel) {
                //开始请求删除
                $.ajax({
                    url: "Kind/deleteBatch.do",
                    data: JSON.stringify(kindsid),
                    type: 'post',
                    success: function (res) {
                        alert("删除成功");
                        showAllKind();
                    },
                    error: function () {
                        alert("删除失败");
                        showAllKind();
                    }
                });
            }
        }
    </script>


    <script type="text/javascript">

        var currentPage = 1;
        var totalPage = 0;

        function showAllContent(flag) {
            if (flag == "add") {
                currentPage++;
            } else if (flag == "reduce") {
                currentPage--;
            } else if (flag == "last") {
                currentPage = totalPage;
            } else if (flag == "first") {
                currentPage = 1;
            }
            $.ajax({
                type: "post",
                url: "Content/showAllContent.do",
                dataType: "json",
                data: {
                    "currentPage": currentPage
                },
                success: function (contentList) {
                    var contentInf;
                    contentInf;
                    contentInf += "<tr>";
                    contentInf += "<th><input type='checkbox'  onclick='#'/></th>";
                    contentInf += "<th>序号</th>";
                    contentInf += "<th>项目</th>";
                    contentInf += "<th>别名</th>";
                    contentInf += "<th>类别</th>";
                    contentInf += "<th>操作</th>";
                    contentInf += "</tr>";
                    var content;
                    for (var i = 0; i < contentList.length; i++) {
                        content = contentList[i];
                        contentInf += "<tr>";
                        contentInf += "<td><input type='checkbox' value='" + content.id + "' onclick='#'/></td>";
                        contentInf += "<td>" + content.id + "</td>";
                        contentInf += "<td>" + content.name + "</td>";
                        contentInf += "<td>" + content.c_name + "</td>";
                        contentInf += "<td>" + content.k_name + "</td>";
                        contentInf += "<td><a href='javascript:showMyModel1con(" + content.id + ",\"" + content.name + "\",\"" + content.c_name + "\",\"" + content.k_name + "\");'>修改</a>&nbsp;&nbsp;&nbsp;<a href='javascript:deleteContentById(" + content.id + ");'>删除</a></td>";
                        contentInf += "</tr>";
                    }

                    $("#content1").html(contentInf);
                    showButton();
                },
                error: function (e) {
                    alert("失败");
                }
            });
        }

        //        c_nick_name是增加的

        function showButton() {

            $.ajax({
                type: "post",
                url: "Content/getTotalPage.do",
                dataType: "text",
                data:
                    {},
                success: function (total_Page) {
                    totalPage = parseInt(total_Page);//总页数
                    var str = "";
                    if (currentPage == 0) {
                        currentPage = 1;
                    }
                    if (currentPage == 1 && currentPage < totalPage) {//前面不可点,后面可点
                        str += "tr"
                        str += "<td><a href='#' >第一页&nbsp </a></td>";
                        str += "<td> <a href='#' > &nbsp上一页&nbsp </a></li>";
                        str += "<td> <a href='#' >&nbsp" + currentPage + "&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllContent(\"add\")'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllContent(\"last\")'>&nbsp最后一页&nbsp</a></td>";
                        str += "tr"
                    } else if (currentPage == 1 && currentPage == totalPage) {//前后都不可点
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage + "&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp最后一页&nbsp</a></td>";
                    } else if (currentPage > 1 && currentPage == totalPage) {//前面可点,后面不可点
                        str += "<td><a href='javascript:showAllContent(\"first\")' class='btn btn-default btn-link'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='javascript:showAllContent(\"reduce\")' class='btn btn-default btn-link'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage + "&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp最后一页&nbsp</a></td>";
                    } else if (currentPage > 1 && currentPage < totalPage) {//前后都可点
                        str += "<td><a href='javascript:showAllContent(\"first\")' class='btn btn-default btn-link'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='javascript:showAllContent(\"reduce\")' class='btn btn-default btn-link'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage + "&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllContent(\"add\")'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllContent(\"last\")'>&nbsp最后一页&nbsp</a></td>";
                    }
                    $("#mypageul").html(str);
                },
                error: function (msg) {

                }
            });
        }

    </script>

    <script type="text/javascript">
        function deleteContentById(contentId) {
            $.ajax({
                type: "post",
                url: "Content/deleteContentById.do",
                dataType: "text",
                data: {
                    "contentId": contentId
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("删除成功")
                        showAllContent();//重新显示
                    } else {
                        alert("删除失败")
                        showAllContent();
                    }
                },
                error: function (e) {
                    alert("删除失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function showMyModelcon() {
            //显示蒙板
            $("#myModelcon").css("display", "block");
        }

        function addContent() {
            // 隐藏蒙板
            var name = $("#cname").val();
            var nick_name = $("#c_nick_name").val();
            var k_name=$("#kind_id option:selected").val();
            $.ajax({
                type: "post",
                url: "Content/saveContent.do",
                dataType: "text",
                data: {
                    "name": name,
                    "nick_name": nick_name,
                    "k_name": k_name
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("增加成功")
                        $("#myModelcon").css("display", "none");
                        showAllContent();
                    } else {
                        alert("增加失败")
                        showAllContent();
                    }
                },
                error: function (e) {
                    alert("增加失败");
                }
            });
        }
    </script>
    <script type="text/javascript">
        function showMyModel1con(id, name, nick_name, k_name) {
            //显示蒙板
            $("#c_nick_name1").val(nick_name);
            $("#contentId").val(id);
            $("#contentName").val(name);
            $("#contentKind_id").val(k_name);
            $("#myModel1con").css("display", "block");
        }

        function updateContent() {
            // 隐藏蒙板
            var k_name = $("#contentKind_id option:selected").val();
            $("#myModel1con").css("display", "none");
            $.ajax({
                type: "post",
                url: "Content/updateMyContent.do",
                dataType: "text",
                data: {
                    "id": $("#contentId").val(),
                    "name": $("#contentName").val(),
                    "nick_name": $("#c_nick_name1").val(),
                    "k_name": $("#contentKind_id option:selected").val()

                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("修改成功")
                        showAllContent();
                    } else {
                        alert("修改失败")
                        showAllContent();
                    }
                },
                error: function (e) {
                    alert("修改失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function deleteBatch2() {
            var contentsCheckBox = $("input[type='checkbox']:checked");
            var contentsid = [];
            for (var i = 0; i < contentsCheckBox.length; i++) {
                //使用[]取得元素是是一个domElement元素，取值需要使用.value，
                //如果使用stusCheckBox.eq(i) 则是一个Obkject元素，就可以使用val()取值
                //alert(stusCheckBox[i].value);
                mysendcontent_id = {};
                mysendcontent_id['content_id'] = contentsCheckBox[i].value;
                contentsid[i] = mysendcontent_id;

            }
            //alert(booksid);
            var confirmdel = confirm('确认要删除吗?');
            if (confirmdel) {
                //开始请求删除
                $.ajax({
                    url: "Content/deleteBatch2.do",
                    data: JSON.stringify(contentsid),
                    type: 'post',
                    success: function (res) {
                        alert("删除成功");
                        showAllContent();
                    },
                    error: function () {
                        alert("删除失败");
                        showAllContent();
                    }
                });
            }
        }
    </script>


    <script type="text/javascript">
        function deleteTeacherById(teacherId) {
            $.ajax({
                type: "post",
                url: "Teacher/deleteTeacherById.do",
                dataType: "text",
                data: {
                    "teacherId": teacherId
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("删除成功")
                        showAllTeacher();//重新显示
                    } else {
                        alert("删除失败")
                        showAllTeacher();
                    }
                },
                error: function (e) {
                    alert("删除失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        var currentPage3 = 1;
        var totalPage3 = 0;

        function showAllTeacher(flag) {
            if (flag == "add") {
                currentPage3++;
            } else if (flag == "reduce") {
                currentPage3--;
            } else if (flag == "last") {
                currentPage3 = totalPage3;
            } else if (flag == "first") {
                currentPage3 = 1;
            }
            $.ajax({
                type: "post",
                url: "Teacher/showAllTeacher.do",
                dataType: "json",
                data: {
                    "currentPage3": currentPage3
                },
                success: function (teacherList) {
                    var teacherInf;
                    teacherInf;
                    teacherInf += "<tr>";
                    teacherInf += "<th><input type='checkbox'  onclick='#'/></th>";
                    teacherInf += "<th>序号</th>";
                    teacherInf += "<th>姓名</th>";
                    teacherInf += "<th>级别</th>";
                    teacherInf += "<th>账号</th>";
                    teacherInf += "<th>密码</th>";
                    teacherInf += "<th>操作</th>";
                    teacherInf += "</tr>";
                    var teacher;
                    for (var i = 0; i < teacherList.length; i++) {
                        teacher = teacherList[i];
                        teacherInf += "<tr>";
                        teacherInf += "<td><input type='checkbox' value='" + teacher.id + "' onclick='#'/></td>";
                        teacherInf += "<td>" + teacher.id + "</td>";
                        teacherInf += "<td>" + teacher.name + "</td>";
                        teacherInf += "<td>" + teacher.process + "</td>";
                        teacherInf += "<td>" + teacher.num + "</td>";
                        teacherInf += "<td>" + teacher.pwd + "</td>";
                        teacherInf += "<td><a href='javascript:showMyModel1tea(" + teacher.id + ", \"" + teacher.name + "\",\"" + teacher.process + "\",\"" + teacher.num + "\",\"" + teacher.pwd + "\");'>修改</a>&nbsp;&nbsp;&nbsp;<a href='javascript:deleteTeacherById(" + teacher.id + ");'>删除</a></td>";
                        teacherInf += "</tr>";
                    }

                    $("#teacher1").html(teacherInf);
                    showButton3();
                },
                error: function (e) {
                    alert("失败");
                }
            });
        }

        function showButton3() {

            $.ajax({
                type: "post",
                url: "Teacher/getTotalPage3.do",
                dataType: "text",
                data:
                    {},
                success: function (total_Page3) {
                    totalPage3 = parseInt(total_Page3);//总页数
                    var str = "";
                    if (currentPage3 == 0) {
                        currentPage3 = 1;
                    }
                    if (currentPage3 == 1 && currentPage3 < totalPage3) {//前面不可点,后面可点
                        str += "tr"
                        str += "<td><a href='#' >第一页&nbsp </a></td>";
                        str += "<td> <a href='#' > &nbsp上一页&nbsp </a></li>";
                        str += "<td> <a href='#' >&nbsp" + currentPage3 + "&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllTeacher(\"add\")'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllTeacher(\"last\")'>&nbsp最后一页&nbsp</a></td>";
                        str += "tr"
                    } else if (currentPage3 == 1 && currentPage3 == totalPage3) {//前后都不可点
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage3 + "&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp最后一页&nbsp</a></td>";
                    } else if (currentPage3 > 1 && currentPage3 == totalPage3) {//前面可点,后面不可点
                        str += "<td><a href='javascript:showAllTeacher(\"first\")' class='btn btn-default btn-link'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='javascript:showAllTeacher(\"reduce\")' class='btn btn-default btn-link'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage3 + "&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp最后一页&nbsp</a></td>";
                    } else if (currentPage3 > 1 && currentPage3 < totalPage3) {//前后都可点
                        str += "<td><a href='javascript:showAllTeacher(\"first\")' class='btn btn-default btn-link'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='javascript:showAllTeacher(\"reduce\")' class='btn btn-default btn-link'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage3 + "&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllTeacher(\"add\")'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllTeacher(\"last\")'>&nbsp最后一页&nbsp</a></td>";
                    }
                    $("#mypageul3").html(str);
                },
                error: function (msg) {

                }
            });
        }
    </script>
    <script type="text/javascript">
        function showMyModeltea() {
            //显示蒙板

            $("#myModeltea").css("display", "block");
        }

        function addTeacher() {
            // 隐藏蒙板
            var name = $("#tname").val();
            var process = $("#process").val();
            var num = $("#num").val();
            var pwd = $("#pwd").val();

            $.ajax({
                type: "post",
                url: "Teacher/saveTeacher.do",
                dataType: "text",
                data: {
                    "name": name,
                    "process": process,
                    "num": num,
                    "pwd": pwd
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("增加成功")
                        $("#myModeltea").css("display", "none");
                        showAllTeacher();
                    } else {
                        alert("增加失败")
                        showAllTeacher();
                    }
                },
                error: function (e) {
                    alert("增加失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function showMyModel1tea(id, name, process, num, pwd) {
            //显示蒙板

            $("#teacherId").val(id);
            $("#teacherName").val(name);
            $("#teacherprocess").val(process);
            $("#teachernum").val(num);
            $("#teacherpwd").val(pwd);
            $("#myModel1tea").css("display", "block");
        }

        function updateTeacher() {
            // 隐藏蒙板

            $.ajax({
                type: "post",
                url: "Teacher/updateMyTeacher.do",
                dataType: "text",
                data: {
                    "id": $("#teacherId").val(),
                    "name": $("#teacherName").val(),
                    "process": $("#teacherprocess").val(),
                    "num": $("#teachernum").val(),
                    "pwd": $("#teacherpwd").val()
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("修改成功")
                        $("#myModel1tea").css("display", "none");
                        showAllTeacher();
                    } else {
                        alert("修改失败")
                        showAllTeacher();
                    }
                },
                error: function (e) {
                    alert("修改失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function deleteBatch3() {
            var teachersCheckBox = $("input[type='checkbox']:checked");
            var teachersid = [];
            for (var i = 0; i < teachersCheckBox.length; i++) {
                //使用[]取得元素是是一个domElement元素，取值需要使用.value，
                //如果使用stusCheckBox.eq(i) 则是一个Obkject元素，就可以使用val()取值
                //alert(stusCheckBox[i].value);
                mysendteacher_id = {};
                mysendteacher_id['teacher_id'] = teachersCheckBox[i].value;
                teachersid[i] = mysendteacher_id;

            }
            //alert(booksid);
            var confirmdel = confirm('确认要删除吗?');
            if (confirmdel) {
                //开始请求删除
                $.ajax({
                    url: "Teacher/deleteBatch3.do",
                    data: JSON.stringify(teachersid),
                    type: 'post',
                    success: function (res) {
                        alert("删除成功");
                        showAllTeacher();
                    },
                    error: function () {
                        alert("删除失败");
                        showAllTeacher();
                    }
                });
            }
        }
    </script>


    <script type="text/javascript">
        var currentPage4 = 1;
        var totalPage4 = 0;

        function showAllTclass(flag) {
            if (flag == "add") {
                currentPage4++;
            } else if (flag == "reduce") {
                currentPage4--;
            } else if (flag == "last") {
                currentPage4 = totalPage4;
            } else if (flag == "first") {
                currentPage4 = 1;
            }
            $.ajax({
                type: "post",
                url: "Tclass/showAllTclass.do",
                dataType: "json",
                data: {
                    "currentPage4": currentPage4
                },
                success: function (tclassList) {
                    var tclassInf;
                    tclassInf;
                    tclassInf += "<tr>";
                    tclassInf += "<th><input type='checkbox'  onclick='#'/></th>";
                    tclassInf += "<th>序号</th>";
                    tclassInf += "<th>班级</th>";
                    tclassInf += "<th>课程</th>";
                    tclassInf += "<th>操作</th>";
                    tclassInf += "</tr>";
                    var tclass;
                    for (var i = 0; i < tclassList.length; i++) {
                        tclass = tclassList[i];
                        tclassInf += "<tr>";
                        tclassInf += "<td><input type='checkbox' value='" + tclass.id + "' onclick='#'/></td>";
                        tclassInf += "<td>" + tclass.id + "</td>";
                        tclassInf += "<td>" + tclass.name + "</td>";
                        tclassInf += "<td>" + tclass.major + "</td>";
                        tclassInf += "<td><a href='javascript:showMyModel1class(" + tclass.id + ", \"" + tclass.name + "\",\"" + tclass.major + "\");'>修改</a>&nbsp;&nbsp;&nbsp;<a href='javascript:deleteTclassById(" + tclass.id + ");'>删除</a></td>";
                        tclassInf += "</tr>";
                    }

                    $("#tclass1").html(tclassInf);
                    showButton4();
                },
                error: function (e) {
                    alert("失败");
                }
            });
        }

        function showButton4() {
            $.ajax({
                type: "post",
                url: "Tclass/getTotalPage4.do",
                dataType: "text",
                data:
                    {},
                success: function (total_Page4) {
                    totalPage4 = parseInt(total_Page4);//总页数
                    var str = "";
                    if (currentPage4 == 0) {
                        currentPage4 = 1;
                    }
                    if (currentPage4 == 1 && currentPage4 < totalPage4) {//前面不可点,后面可点
                        str += "tr"
                        str += "<td><a href='#' >第一页&nbsp </a></td>";
                        str += "<td> <a href='#' > &nbsp上一页&nbsp </a></li>";
                        str += "<td> <a href='#' >&nbsp" + currentPage4 + "&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllTclass(\"add\")'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllTclass(\"last\")'>&nbsp最后一页&nbsp</a></td>";
                        str += "tr"
                    } else if (currentPage4 == 1 && currentPage4 == totalPage4) {//前后都不可点
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage4 + "&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp最后一页&nbsp</a></td>";
                    } else if (currentPage4 > 1 && currentPage4 == totalPage4) {//前面可点,后面不可点
                        str += "<td><a href='javascript:showAllTclass(\"first\")' class='btn btn-default btn-link'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='javascript:showAllTclass(\"reduce\")' class='btn btn-default btn-link'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage4 + "&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp最后一页&nbsp</a></td>";
                    } else if (currentPage4 > 1 && currentPage4 < totalPage4) {//前后都可点
                        str += "<td><a href='javascript:showAllTclass(\"first\")' class='btn btn-default btn-link'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='javascript:showAllTclass(\"reduce\")' class='btn btn-default btn-link'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage4 + "&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllTclass(\"add\")'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllTclass(\"last\")'>&nbsp最后一页&nbsp</a></td>";
                    }
                    $("#mypageul4").html(str);
                },
                error: function (msg) {

                }
            });
        }
    </script>
    <script type="text/javascript">
        function deleteTclassById(tclassId) {
            $.ajax({
                type: "post",
                url: "Tclass/deleteTclassById.do",
                dataType: "text",
                data: {
                    "tclassId": tclassId
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("删除成功")
                        showAllTclass();//重新显示
                    } else {
                        alert("删除失败")
                        showAllTclass();
                    }
                },
                error: function (e) {
                    alert("删除失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function showMyModelclass() {
            //显示蒙板

            $("#myModelclass").css("display", "block");
        }

        function addTclass() {
            // 隐藏蒙板
            var name = $("#classname").val();
            var major = $("#classmajor").val();
            $.ajax({
                type: "post",
                url: "Tclass/saveTclass.do",
                dataType: "text",
                data: {
                    "name": name,
                    "major": major
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("增加成功")
                        $("#myModelclass").css("display", "none");
                        showAllTclass();
                    } else {
                        alert("增加失败")
                        showAllTclass();
                    }
                },
                error: function (e) {
                    alert("增加失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function showMyModel1class(id, name, major) {
            //显示蒙板

            $("#tclassId").val(id);
            $("#tclassName").val(name);
            $("#tclassmajor").val(major);
            $("#myModel1class").css("display", "block");
        }

        function updateTclass() {
            // 隐藏蒙板

            $.ajax({
                type: "post",
                url: "Tclass/updateMyTclass.do",
                dataType: "text",
                data: {
                    "id": $("#tclassId").val(),
                    "name": $("#tclassName").val(),
                    "major": $("#tclassmajor").val()
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("修改成功")
                        $("#myModel1class").css("display", "none");
                        showAllTclass();
                    } else {
                        alert("修改失败")
                        showAllTclass();
                    }
                },
                error: function (e) {
                    alert("修改失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function deleteBatch4() {
            var tclasssCheckBox = $("input[type='checkbox']:checked");
            var tclasssid = [];
            for (var i = 0; i < tclasssCheckBox.length; i++) {
                //使用[]取得元素是是一个domElement元素，取值需要使用.value，
                //如果使用stusCheckBox.eq(i) 则是一个Obkject元素，就可以使用val()取值
                //alert(stusCheckBox[i].value);
                mysendtclass_id = {};
                mysendtclass_id['tclass_id'] = tclasssCheckBox[i].value;
                tclasssid[i] = mysendtclass_id;

            }
            //alert(booksid);
            var confirmdel = confirm('确认要删除吗?');
            if (confirmdel) {
                //开始请求删除
                $.ajax({
                    url: "Tclass/deleteBatch4.do",
                    data: JSON.stringify(tclasssid),
                    type: 'post',
                    success: function (res) {
                        alert("删除成功");
                        showAllTclass();
                    },
                    error: function () {
                        alert("删除失败");
                        showAllTclass();
                    }
                });
            }
        }
    </script>


    <script type="text/javascript">
        var currentPage5 = 1;
        var totalPage5 = 0;

        function showAllCource(flag) {
            if (flag == "add") {
                currentPage5++;
            } else if (flag == "reduce") {
                currentPage5--;
            } else if (flag == "last") {
                currentPage5 = totalPage5;
            } else if (flag == "first") {
                currentPage5 = 1;
            }
            $.ajax({
                type: "post",
                url: "Cource/showAllCource.do",
                dataType: "json",
                data: {
                    "currentPage5": currentPage5
                },
                success: function (courceList) {
                    var courceInf;
                    courceInf;
                    courceInf += "<tr>";
                    courceInf += "<th><input type='checkbox'  onclick='#'/></th>";
                    courceInf += "<th>序号</th>";
                    courceInf += "<th>课程</th>";
                    courceInf += "<th>班级</th>";
                    courceInf += "<th>老师</th>";
                    courceInf += "<th>日期</th>";
                    courceInf += "<th>操作</th>";
                    courceInf += "</tr>";
                    var cource;
                    for (var i = 0; i < courceList.length; i++) {
                        cource = courceList[i];
                        courceInf += "<tr>";
                        courceInf += "<td><input type='checkbox' value='" + cource.id + "' onclick='#'/></td>";
                        courceInf += "<td>" + cource.id + "</td>";
                        courceInf += "<td>" + cource.name + "</td>";
                        courceInf += "<td>" + cource.tc_name + "</td>";
                        courceInf += "<td>" + cource.t_name + "</td>";
                        courceInf += "<td>" + cource.date + "</td>";
                        courceInf += "<td><a href='javascript:showMyModel1cource(" + cource.id + ", \"" + cource.name + "\",\"" + cource.tc_name + "\",\"" + cource.t_name + "\",\"" + cource.date + "\");'>修改</a>&nbsp;&nbsp;&nbsp;<a href='javascript:deleteCourceById(" + cource.id + ");'>删除</a></td>";
                        courceInf += "</tr>";
                    }

                    $("#cource1").html(courceInf);
                    showButton5();
                },
                error: function (e) {
                    alert("失败");
                }
            });
        }

        function showButton5() {
            $.ajax({
                type: "post",
                url: "Cource/getTotalPage5.do",
                dataType: "text",
                data:
                    {},
                success: function (total_Page5) {
                    totalPage5 = parseInt(total_Page5);//总页数
                    var str = "";
                    if (currentPage5 == 0) {
                        currentPage5 = 1;
                    }
                    if (currentPage5 == 1 && currentPage5 < totalPage5) {//前面不可点,后面可点
                        str += "tr"
                        str += "<td><a href='#' >第一页&nbsp </a></td>";
                        str += "<td> <a href='#' > &nbsp上一页&nbsp </a></li>";
                        str += "<td> <a href='#' >&nbsp" + currentPage5 + "&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllCource(\"add\")'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllCource(\"last\")'>&nbsp最后一页&nbsp</a></td>";
                        str += "tr"
                    } else if (currentPage5 == 1 && currentPage5 == totalPage5) {//前后都不可点
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage5 + "&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp最后一页&nbsp</a></td>";
                    } else if (currentPage5 > 1 && currentPage5 == totalPage5) {//前面可点,后面不可点
                        str += "<td><a href='javascript:showAllCource(\"first\")' class='btn btn-default btn-link'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='javascript:showAllCource(\"reduce\")' class='btn btn-default btn-link'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage5 + "&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='#' class='btn btn-default btn-link disabled'>&nbsp最后一页&nbsp</a></td>";
                    } else if (currentPage5 > 1 && currentPage5 < totalPage5) {//前后都可点
                        str += "<td><a href='javascript:showAllCource(\"first\")' class='btn btn-default btn-link'>&nbsp第一页&nbsp</a></td>";
                        str += "<td> <a href='javascript:showAllTclass(\"reduce\")' class='btn btn-default btn-link'>&nbsp上一页&nbsp</a></td>";
                        str += "<td> <a href='#' class='btn btn-default btn-link disabled'>&nbsp" + currentPage5 + "&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllCource(\"add\")'>&nbsp下一页&nbsp</a></td>";
                        str += "<td><a href='javascript:showAllCource(\"last\")'>&nbsp最后一页&nbsp</a></td>";
                    }
                    $("#mypageul5").html(str);
                },
                error: function (msg) {

                }
            });
        }
    </script>
    <script type="text/javascript">
        function deleteCourceById(courceId) {
            $.ajax({
                type: "post",
                url: "Cource/deleteCourceById.do",
                dataType: "text",
                data: {
                    "courceId": courceId
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("删除成功")
                        showAllCource();//重新显示
                    } else {
                        alert("删除失败")
                        showAllCource();
                    }
                },
                error: function (e) {
                    alert("删除失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function showMyModelcource() {
            //显示蒙板
            $("#myModelcource").css("display", "block");
        }

        function addCource() {
            // 隐藏蒙板
            var name = $("#courcename").val();
            var tc_name = $("#tc_name2").val();
            var t_name = $("#t_name2").val();
            var date = $("#date").val();
            $.ajax({
                type: "post",
                url: "Cource/saveCource.do",
                dataType: "text",
                data: {
                    "name": name,
                    "tc_name": tc_name,
                    "t_name": t_name,
                    "date": date
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("增加成功")
                        $("#myModelcource").css("display", "none");
                        showAllCource();
                    } else {
                        alert("增加失败")
                        showAllCource();
                    }
                },
                error: function (e) {
                    alert("增加失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function showMyModel1cource(id, name, tc_name, t_name, date) {
            //显示蒙板

            $("#courceId").val(id);
            $("#courceName").val(name);
            $("#tc_name1").val(tc_name);
            $("#t_name1").val(t_name);
            $("#courcedate").val(date);
            $("#myModel1cource").css("display", "block");
        }

        function updateCource() {
            // 隐藏蒙板
            $.ajax({
                type: "post",
                url: "Cource/updateMyCource.do",
                dataType: "text",
                data: {
                    "id": $("#courceId").val(),
                    "name": $("#courceName").val(),
                    "tc_name": $("#tc_name1").val(),
                    "t_name": $("#t_name1").val(),
                    "date": $("#courcedate").val()
                },
                success: function (inf) {
                    if (inf == SUCCESS) {
                        alert("修改成功")
                        $("#myModel1cource").css("display", "none");
                        showAllCource();
                    } else {
                        alert("修改失败")
                        showAllCource();
                    }
                },
                error: function (e) {
                    alert("修改失败")
                }
            });
        }
    </script>
    <script type="text/javascript">
        function deleteBatch5() {
            var courcesCheckBox = $("input[type='checkbox']:checked");
            var courcesid = [];
            for (var i = 0; i < courcesCheckBox.length; i++) {

                mysendcource_id = {};
                mysendcource_id['cource_id'] = courcesCheckBox[i].value;
                courcesid[i] = mysendcource_id;

            }
            //alert(booksid);
            var confirmdel = confirm('确认要删除吗?');
            if (confirmdel) {
                //开始请求删除
                $.ajax({
                    url: "Cource/deleteBatch5.do",
                    data: JSON.stringify(courcesid),
                    type: 'post',
                    success: function (res) {
                        alert("删除成功");
                        showAllCource();
                    },
                    error: function () {
                        alert("删除失败");
                        showAllCource();
                    }
                });
            }
        }
    </script>

    <script type="text/javascript">
        function hideModel() {
            $("#myModel").css("display", "none");
            $("#myModel1").css("display", "none");
            $("#myModelcon").css("display", "none");
            $("#myModel1con").css("display", "none");
            $("#myModeltea").css("display", "none");
            $("#myModel1tea").css("display", "none");
            $("#myModelclass").css("display", "none");
            $("#myModel1class").css("display", "none");
            $("#myModelcource").css("display", "none");
            $("#myModel1cource").css("display", "none");
        }
    </script>


</head>
<body style="background: #fdfbfe;">

<div class=" row " style="height: 97px; width: 80%;" id="header">
    <div class="logo " style="margin-top: 30px; margin-left: 2cm;">
        <img
                src="http://www.farsight.com.cn/templets/default/images/logo.png "
                alt=" " width="315 " height="42 ">
    </div>
</div>
<div class="right" style="margin: auto; width: 80%;" id="bodyer">

    <div class="rightCont">
        <div id="aaa" class="a" style="height: 60px;">
            <ul>
                <li></li>
                <li><a class="aa" href="javascript:show(0);">类别 </a></li>
                <li><a class="aa" href="javascript:show(1); javascript:showAllContent();">项目 </a></li>
                <li><a class="aa" href="javascript:show(2);javascript:showAllTeacher();">老师 </a></li>
                <li><a class="aa" href="javascript:show(3);javascript:showAllTclass();">班级 </a></li>
                <li><a class="aa" href="javascript:show(4);javascript:showAllCource();">课程 </a></li>

            </ul>
        </div>
        <br> <br>

        <div id="kind" style="width: 75%;height:auto;margin-top: 30px">
            <p class="g_title fix">
                类别 <a class="btn03" href="javascript:showMyModel();">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                    class="btn03" href="javascript:deleteBatch();">删 除</a>
            </p>

            <table id=kind1 class="tab2" width="100%"></table>
            <table class='pagination' id="mypageul2" border="0.5" style="margin: auto ;margin-top: 10px">
            </table>

            <div id="myModel" align="center" style="text-align: center;">
                <div id="model-content" style="width: 70%;height: 70%;padding-top: 50px">
                    <input type="hidden" name="id" id="id"/>

                    <table border="1" align="center" style="width: 50%;height: 50%">
                        <tr>
                            <td>类别:</td>
                            <td><input type="text" value="" id="name" placeholder="请输入类别"/></td>
                        </tr>
                        <tr>
                            <td>别名:</td>
                            <td><input type="text" value="" id="nick_name" placeholder="请输入别名"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="button" name="button" id="button"
                                                   value="增加" onclick="addKind()"/>&nbsp;&nbsp;&nbsp;&nbsp;

                                <input type="button" name="button" id="button"
                                       value="取消" onclick="hideModel()"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="myModel1">
                <div id="model-content" align="center" style="text-align: center; padding-top: 50px">
                    <table border="1" align="center" style="width: 70%;height: 70%">
                        <tr>
                            <td>序号</td>
                            <td><input type="text" value="" id="kindId" readonly/><br/></td>
                        </tr>
                        <tr>
                            <td>类别</td>
                            <td><input type="text" value="" id="kindName"/><br/></td>
                        </tr>
                        <tr>
                            <td>别名</td>
                            <td><input type="text" value="" id="kind_nick_name"/><br/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="button" name="button" id="button"
                                                                  value="修改" onclick="updateKind()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input
                                    type="button" name="button" id="button"
                                    value="取消" onclick="hideModel()"/></td>
                        </tr>
                    </table>

                </div>
            </div>
        </div>

        <div id="content" style="width: 75%;height:auto;margin-top: 30px">
            <p class="g_title fix">
                项目内容 <a class="btn03" href="javascript:showMyModelcon();">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="btn03" href="javascript:deleteBatch2();">删 除</a>
            </p>


            <table id="content1" class="tab2" width="100%">
            </table>


            <table class='pagination' id="mypageul" border="0.5" style="margin: auto ;margin-top: 10px">
            </table>

            <div id="myModelcon" align="center" style="text-align: center;">
                <div id="model-content" style="text-align: center; padding-top: 50px">
                    <table border="1" align="center" style="width: 70%;height: 70%">
                        <tr>
                            <td>项目</td>
                            <td><input type="text" value="" id="cname" placeholder="请输入项目"/></td>
                        </tr>
                        <tr>
                            <td>别名</td>
                            <td><input type="text" value="" id="c_nick_name" placeholder="请输入别名"/></td>
                        </tr>
                        <tr>
                            <td><label for="kind_id">类别:</label></td>
                            <td><select name="kind_id" id="kind_id" style="width: 45%;">
                                <!--<option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option> -->
                            </select></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="button" name="button" id="button"
                                                   value="增加" onclick="addContent()"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" name="button" id="button"
                                       value="取消" onclick="hideModel()"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="myModel1con">
                <div id="model-content" style="text-align: center; padding-top: 50px">
                    <table border="1" align="center" style="width: 70%;height: 70%">
                        <tr>
                            <td>序号</td>
                            <td><input type="text" value="" id="contentId" readonly/></td>
                        </tr>
                        <tr>
                            <td>项目</td>
                            <td><input type="text" value="" id="contentName"/></td>
                        </tr>
                        <tr>
                            <td>别名</td>
                            <td><input type="text" value="" id="c_nick_name1" placeholder="请输入别名"/></td>
                        </tr>
                        <tr>
                            <td><label for="contentKind_id">类别:</label></td>
                            <td><select name="contentKind_id" id="contentKind_id" style="width: 45%;">
                                <!--<option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option> -->
                            </select></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="button" name="button" id="button"
                                                                  value="修改" onclick="updateContent()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input
                                    type="button" name="button" id="button"
                                    value="取消" onclick="hideModel()"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <div id="teacher" style="width: 75%;margin-top: 30px">

            <p class="g_title fix">
                老师<a class="btn03" href="javascript:showMyModeltea();">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                    class="btn03" href="javascript:deleteBatch3();">删 除</a>
            </p>

            <table class="tab2" width="100%" id="teacher1">
            </table>

            <table class='pagination' id="mypageul3" border="0.5" style="margin: auto ;margin-top: 10px">
            </table>
            <div id="myModeltea" align="center" style="text-align: center;">
                <div id="model-content" style="text-align: center; padding-top: 50px;height:100%">
                    <table border="1" align="center" style="width: 70%;height: 70%">
                        <tr>
                            <td>姓名</td>
                            <td><input type="text" value="" id="tname" placeholder="请输入姓名"/></td>
                        </tr>
                        <tr>
                            <td>级别</td>
                            <td><input type="text" value="" id="process" placeholder="请输入级别"/></td>
                        </tr>
                        <tr>
                            <td>账号</td>
                            <td><input type="text" value="" id="num" placeholder="请输入账号"/></td>
                        </tr>
                        <tr>
                            <td>密码</td>
                            <td><input type="text" value="" id="pwd" placeholder="请输入密码"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="button" name="button" id="button"
                                                   value="增加" onclick="addTeacher()"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" name="button" id="button"
                                       value="取消" onclick="hideModel()"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="myModel1tea" align="center" style="text-align: center;">
                <div id="model-content" style="text-align: center; padding-top: 50px;height:100%">
                    <table border="1" align="center" style="width: 70%;height: 70%">
                        <tr>
                            <td>序号</td>
                            <td><input type="text" value="" id="teacherId" readonly/></td>
                        </tr>
                        <tr>
                            <td>姓名</td>
                            <td><input type="text" value="" id="teacherName"/></td>
                        </tr>
                        <tr>
                            <td>级别</td>
                            <td><input type="text" value="" id="teacherprocess"/></td>
                        </tr>
                        <tr>
                            <td>账号</td>
                            <td><input type="text" value="" id="teachernum"/></td>
                        </tr>
                        <tr>
                            <td>密码</td>
                            <td><input type="text" value="" id="teacherpwd"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="button" name="button" id="button"
                                                                  value="修改" onclick="updateTeacher()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input
                                    type="button" name="button" id="button"
                                    value="取消" onclick="hideModel()"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <div id="class" style="width: 75%;margin-top: 30px">

            <p class="g_title fix">
                班级<a class="btn03" href="javascript:showMyModelclass();">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                    class="btn03" href="javascript:deleteBatch4();">删 除</a>
            </p>

            <table class="tab2" width="100%" id="tclass1">
            </table>
            <table class='pagination' id="mypageul4" style="margin: auto ;margin-top: 10px">
            </table>
            <div id="myModelclass" align="center" style="text-align: center;">
                <div id="model-content" style="text-align: center; padding-top: 50px;height:100%">
                    <table border="1" align="center" style="width: 70%;height: 70%">
                        <tr>
                            <td>班级</td>
                            <td><input type="text" value="" id="classname" placeholder="请输入班级"/></td>
                        </tr>
                        <tr>
                            <td>课程</td>
                            <td><input type="text" value="" id="classmajor" placeholder="请输入课程"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="button" name="button" id="button"
                                                   value="增加" onclick="addTclass()"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" name="button" id="button"
                                       value="取消" onclick="hideModel()"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="myModel1class" align="center" style="text-align: center;">
                <div id="model-content" style="text-align: center; padding-top: 50px;height:100%">
                    <table border="1" align="center" style="width: 70%;height: 70%">
                        <tr>
                            <td>序号</td>
                            <td><input type="text" value="" id="tclassId" readonly/></td>
                        </tr>
                        <tr>
                            <td>班级</td>
                            <td><input type="text" value="" id="tclassName"/></td>
                        </tr>
                        <tr>
                            <td>课程</td>
                            <td><input type="text" value="" id="tclassmajor"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="button" name="button" id="button"
                                                                  value="修改" onclick="updateTclass()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input
                                    type="button" name="button" id="button"
                                    value="取消" onclick="hideModel()"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <div id="cource" style="width: 75%;margin-top: 30px">

            <p class="g_title fix">
                课程<a class="btn03" href="javascript:showMyModelcource();">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                    class="btn03" href="javascript:deleteBatch5();">删 除</a>
            </p>

            <table class="tab2" width="100%" id="cource1">
            </table>
            <table class='pagination' id="mypageul5" style="margin: auto ;margin-top: 10px">
            </table>
            <div id="myModelcource" align="center" style="text-align: center;">
                <div id="model-content" style="text-align: center; padding-top: 50px;height:100%">
                    <table border="1" align="center" style="width: 70%;height: 70%">
                        <tr>
                            <td>课程</td>
                            <td><input type="text" value="" id="courcename" placeholder="请输入课程"/></td>
                        </tr>
                        <tr>
                            <td>班级</td>
                            <td><input type="text" value="" id="tc_name2" placeholder="请输入班级"/></td>
                        </tr>
                        <tr>
                            <td>老师</td>
                            <td><input type="text" value="" id="t_name2" placeholder="请输入老师"/></td>
                        </tr>
                        <tr>
                            <td>日期</td>
                            <td><input type="text" value="" id="date" placeholder="请输入日期"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="button" name="button" id="button"
                                                   value="增加" onclick="addCource()"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" name="button" id="button"
                                       value="取消" onclick="hideModel()"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="myModel1cource" align="center" style="text-align: center;">
                <div id="model-content" style="text-align: center; padding-top: 50px;height:100%">
                    <table border="1" align="center" style="width: 70%;height: 70%">
                        <tr>
                            <td>序号</td>
                            <td><input type="text" value="" id="courceId" readonly/></td>
                        </tr>
                        <tr>
                            <td>课程</td>
                            <td><input type="text" value="" id="courceName"/></td>
                        </tr>
                        <tr>
                            <td>班级</td>
                            <td><input type="text" value="" id="tc_name1"/></td>
                        </tr>
                        <tr>
                            <td>老师</td>
                            <td><input type="text" value="" id="t_name1"/></td>
                        </tr>
                        <tr>
                            <td>日期</td>
                            <td><input type="text" value="" id="courcedate"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="button" name="button" id="button"
                                                                  value="修改" onclick="updateCource()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input
                                    type="button" name="button" id="button"
                                    value="取消" onclick="hideModel()"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="menu" style="margin: auto; width: 30%;padding-top: 10px">
    <ul>
        <li>
            <a href='toFeedback.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}'
               id='feedback_bar'>学生反馈</a>
        </li>
        <li>
            <a href='toAllgrade.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}'>各项统计</a>
        </li>
        <li>
            <a href='toPercent.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}'>百分比统计</a>
        </li>
        <li>
            <a href='toAdvice.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}'>教师反馈</a>
        </li>
        <li>
            <a href='toAdmin_manage.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}'
               id='admin_bar'>管理员管理</a>
        </li>
    </ul>
</div>


<div class="row" style="height: 110px; margin-top: 50px;" id="footer">
    <p class="u-foot" style="color: #767074; text-align: center;">Copyright
        2004-2017 华清远见教育集团 版权所有 ，京ICP备16055436号</p>
</div>


</body>
</html>
