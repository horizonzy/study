<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>百分比统计表</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        var process=${requestScope.process};
        var tclass_id=${requestScope.tclass_id};
        var allcontent=new Array();		//所有的项目内容
        var allcountKindnum=new Array();  //每个类别包含的项目数量集合
        var allkind=new Array();		//所有的类别集合
        var allStat=new Array();   //接受人数统计的 二维数组！
        var idTmr;

        function  getExplorer() {
            var explorer = window.navigator.userAgent ;
            //ie
            if (explorer.indexOf("MSIE") >= 0) {
                return 'ie';
            }
            //firefox
            else if (explorer.indexOf("Firefox") >= 0) {
                return 'Firefox';
            }
            //Chrome
            else if(explorer.indexOf("Chrome") >= 0){
                return 'Chrome';
            }
            //Opera
            else if(explorer.indexOf("Opera") >= 0){
                return 'Opera';
            }
            //Safari
            else if(explorer.indexOf("Safari") >= 0){
                return 'Safari';
            }
        }
        function method1(tableid) {//整个表格拷贝到EXCEL中
            if(getExplorer()=='ie')
            {
                var curTbl = document.getElementById(tableid);
                var oXL = new ActiveXObject("Excel.Application");

                //创建AX对象excel
                var oWB = oXL.Workbooks.Add();
                //获取workbook对象
                var xlsheet = oWB.Worksheets(1);
                //激活当前sheet
                var sel = document.body.createTextRange();
                sel.moveToElementText(curTbl);
                //把表格中的内容移到TextRange中
                sel.select();
                //全选TextRange中内容
                sel.execCommand("Copy");
                //复制TextRange中内容
                xlsheet.Paste();
                //粘贴到活动的EXCEL中
                oXL.Visible = true;
                //设置excel可见属性

                try {
                    var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
                } catch (e) {
                    print("Nested catch caught " + e);
                } finally {
                    oWB.SaveAs(fname);

                    oWB.Close(savechanges = false);
                    //xls.visible = false;
                    oXL.Quit();
                    oXL = null;
                    //结束excel进程，退出完成
                    //window.setInterval("Cleanup();",1);
                    idTmr = window.setInterval("Cleanup();", 1);

                }
            }
            else
            {
                tableToExcel(tableid)
            }
        }
        function Cleanup() {
            window.clearInterval(idTmr);
            CollectGarbage();
        }
        var tableToExcel = (function() {
            var uri = 'data:application/vnd.ms-excel;base64,',
                template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><meta http-equiv="Content-Type" charset=utf-8"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
                base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
                format = function(s, c) {
                    return s.replace(/{(\w+)}/g,
                        function(m, p) { return c[p]; }) }
            return function(table, name) {
                if (!table.nodeType) table = document.getElementById(table)
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
                window.location.href = uri + base64(format(template, ctx))
            }
        })()

        $(document).ready(function(){

            $("#admin_bar").hide();
            $("#feedback_bar").hide();
            if (process == 2) {

                $("#feedback_bar").show();
            } else if (process == 3) {
                $("#admin_bar").show();
            }
            $.ajax({
                type:"post",
                url:"selectallmycontent.do",
                dataType:"json",
                data:{
                },
                success:function(contentList) {
                    for(var i=0;i<contentList.length;i++){
                        allcontent[i]=contentList[i];     //接收所有的项目内容
                    }
                },
                error:function(e) {
                    alert("项目内容显示失败");
                }
            });

            $.ajax({
                type:"post",
                url:"selectcountKindname.do",
                dataType:"json",
                data:{
                },
                success:function(IntegerList) {
                    for(var i=0;i<IntegerList.length;i++){
                        allcountKindnum[i]=IntegerList[i];    //每个类别包含的项目数量集合
                    }
                },
                error:function(e) {
                    alert("项目内容显示失败");
                }
            });

            $.ajax({
                type:"post",
                url:"selectallKind.do",
                dataType:"json",
                data:{
                },
                success:function(kindList) {
                    for(var i=0;i<kindList.length;i++){
                        allkind[i]=kindList[i];    //接收所有的类别集合
                    }
                },
                error:function(e) {
                    alert("项目内容显示失败");
                }
            });

            showAllCourse();   			//初始调用显示的方法
        });
        function toExcel() {
            $('#exportToExcel').tableExport({type:'Ms-Excel',escape:'false',tableName:'percent'});
        }
        function showContentAndKind(){
            var inf = "";
            var numbercontent=0;     //制作表格时的中间数
            var num1=0;
            var	summation=0;
            for(var k=0;k<allcountKindnum.length-1;k++){
                num1 += allcountKindnum[k];
            }
//	for(var m=0;m<num1;m++){
//		summation += ((allStat[m][4]+allStat[m][3])/(allStat[m][2]
//		+allStat[m][1]+allStat[m][0]));   //求和满意度
//	}
//	var average=summation/num1;		//平均满意度计算

            var num2=allcontent.length;
            var summation4=0, summation3=0, summation2=0, summation1=0, summation0=0;
            for(var m=0;m<allcontent.length;m++){
                summation4 += allStat[m][4];
                summation3 += allStat[m][3];
                summation2 += allStat[m][2];
                summation1 += allStat[m][1];
                summation0 += allStat[m][0];
            }
            var average=((((summation4+summation3)/(summation4+summation3+summation2+summation1+summation0)).toFixed(2))*100+"%");

            //allStat[i][5]
            for(var i=0;i<allkind.length;i++){
                for(var j=0;j<allcountKindnum[i];j++){
                    if(j==0){
                        inf += "<tr>";
                        inf += "<td rowspan="+allcountKindnum[i]+">"+allkind[i].name+"</td>";
                        inf += "<td>"+allcontent[numbercontent].name+"</td>";
                        inf += "<td>"+allStat[numbercontent][4]+"</td>";
                        inf += "<td>"+allStat[numbercontent][3]+"</td>";
                        inf += "<td>"+allStat[numbercontent][2]+"</td>";
                        inf += "<td>"+allStat[numbercontent][1]+"</td>";
                        inf += "<td>"+allStat[numbercontent][0]+"</td>";
                        if((allStat[numbercontent][2]+allStat[numbercontent][1]+allStat[numbercontent][0]+allStat[numbercontent][4]+allStat[numbercontent][3])==0){
                            inf += "<td>0%</td>";
                        }else{
                            inf += "<td>"+( ((allStat[numbercontent][4]+allStat[numbercontent][3])/(allStat[numbercontent][2]
                                +allStat[numbercontent][1]+allStat[numbercontent][0]+allStat[numbercontent][4]+allStat[numbercontent][3])).toFixed(2))*100+'%'+"</td>";
                        }
                        if(numbercontent==0){
                            inf += "<td rowspan="+num1+" style='height: 240px;line-height:450px; overflow: hidden;'>"+average+"</td>";        //此处需添加rows
                        }else if(numbercontent==num1){
                            inf += "<td >"+((allStat[num1][4]+allStat[num1][3])/(allStat[num1][2]
                                +allStat[num1][1]+allStat[num1][0]+allStat[num1][4]+allStat[num1][3]))*100+'%'+"</td>";
                        }
                        inf += "</tr>";

                        numbercontent ++;

                    }else if(j!=0){
                        inf += "<tr>";
                        inf += "<td>"+allcontent[numbercontent].name+"</td>";
                        inf += "<td>"+allStat[numbercontent][4]+"</td>";
                        inf += "<td>"+allStat[numbercontent][3]+"</td>";
                        inf += "<td>"+allStat[numbercontent][2]+"</td>";
                        inf += "<td>"+allStat[numbercontent][1]+"</td>";
                        inf += "<td>"+allStat[numbercontent][0]+"</td>";
                        if((allStat[numbercontent][2]+allStat[numbercontent][1]+allStat[numbercontent][0]+allStat[numbercontent][4]+allStat[numbercontent][3])==0){
                            inf += "<td>0%</td>";
                        }else{
                            inf += "<td>"+( ((allStat[numbercontent][4]+allStat[numbercontent][3])/(allStat[numbercontent][2]
                                +allStat[numbercontent][1]+allStat[numbercontent][0]+allStat[numbercontent][4]+allStat[numbercontent][3])).toFixed(2))*100+'%'+"</td>";
                        }
                        inf += "</tr>";

                        numbercontent ++;
                    }
                }
            }


            inf += "<tr style='background-color:burlywood;'>";
            inf += "<td rowspan='1'></td>";
            inf += "<td align='center'>总体满意度比例</td>";
            inf += "<td>"+summation4+"</td>";
            inf += "<td>"+summation3+"</td>";
            inf += "<td>"+summation2+"</td>";
            inf += "<td>"+summation1+"</td>";
            inf += "<td>"+summation0+"</td>";
            inf += "<td>"+average+"</td>";
            inf += "<td></td>";
            inf += "</tr>";

            $("#tbodyid1").html(inf);
        }


        function showAllCourse(){

            $.ajax({
                type:"post",
                url:"selectallCource.do",
                dataType:"json",
                data:{
                    "tclass_id":tclass_id
                },
                success:function(CourseList) {
                    var inf1="";
                    for(var i=0;i<CourseList.length;i++){
                        inf1 +=" <option value ="+CourseList[i].name+">"+CourseList[i].name+"</option>";
                    }
                    $("#courseselect").html(inf1);
                    showtheteacher();

                },
                error:function(e) {
                    alert("项目内容显示失败");
                }
            });

        }

        function showtheteacher(){

            var sel=$("#courseselect").val();

            $.ajax({
                type:"post",
                url:"seleceteacherByCource.do",
                dataType:"json",
                data:{
                    "name":sel
                },
                success:function(TeacherList) {
                    var inf2="";
                    for(var i=0;i<TeacherList.length;i++){
                        inf2 +=" <option value ="+TeacherList[i].name+">"+TeacherList[i].name+"</option>";
                    }
                    $("#showteacherBycourse").html(inf2);
                    showDate();
                },
                error:function(e) {
                    alert("项目内容显示失败了啦");
                }
            });


        }

        function showDate(){
            var sel1=$("#courseselect").val();
            var sel2=$("#showteacherBycourse").val();
            $.ajax({
                type:"post",
                url:"selectDateByCourceAndteacher.do",
                dataType:"json",
                data:{
                    "courcename":sel1,
                    "teachername":sel2
                },
                success:function(DateList) {
                    var inf3="";
                    for(var i=0;i<DateList.length;i++){
                        inf3 +=" <option value ="+DateList[i].date+">"+DateList[i].date+"</option>";
                    }
                    $("#selectDate").html(inf3);

                },
                error:function(e) {
                    alert("项目内容显示失败了啦date");
                }
            });

        }

        function getid(){
            var name1=$("#courseselect").val();
            var name2=$("#showteacherBycourse").val();
            var date=$("#selectDate").val();
            $("#select_button").attr("class","btn btn-default btn-block btn-primary disabled");
            $("#select_cource").html("<span>"+name1+"</span>");
            $("#select_date").html("<span>"+date+"</span>");
            $("#select_teacher").html("<span>"+name2+"</span>");

            $.ajax({
                type:"post",
                url:"selectCourceID.do",
                dataType:"text",
                data:{
                    "courcename":name1,
                    "teachername":name2,
                    "date":date
                },
                success:function(courceID){
                    showdescription(courceID);
                    getTclassID(courceID);
                    getStat(courceID);
                },
                error:function(e) {
                    alert("项目内容显示失败了啦ID");
                }
            });
        }
        function getTclassID(courceid){
            $.ajax({
                type:"post",
                url:"selectTclassid.do",
                dataType:"text",
                data:{
                    "courid":courceid
                },
                success:function(tclassId){

                    getStuCount(tclassId);
                },
                error:function(e) {
                    alert("得到tclassID失败");
                }
            });
        }

        function showdescription(courceid){
            $.ajax({
                type:"post",
                url:"selectdescription.do",
                dataType:"text",
                data:{
                    "id":courceid
                },
                success:function(description){
                    $("#DescriptionTextarea").html(description+"");
                },
                error:function(e) {
                    alert("项目内容显示失败了啦comment");
                }
            });
        }

        function resetPage() {
            window.open("toPercent.do?tclass_id=${requestScope.tclass_id}&process=${requestScope.process}","_self");
        }
        function getStuCount(tclassID){
            $.ajax({
                type:"post",
                url:"CountStudent.do",
                dataType:"text",
                data:{
                    "id":tclassID
                },
                success:function(countstu){
                    $("#countStu").text(countstu);
                },
                error:function(e) {
                    alert("当前参培人数获取失败");
                }
            });
        }


        function getStat(CourceID){
            $.ajax({
                type:"post",
                url:"getGrade.do",
                dataType:"json",
                data:{
                    "id":CourceID
                },
                success:function(gradeStat) {
                    for(var i=0;i<gradeStat.length;i++){
                        allStat[i]=gradeStat[i];   //new Array()接收分数统计的二维数组
                    }
                    showContentAndKind();
                },
                error:function(e) {
                    alert("项目内容显示失败");
                }
            });


        }
    </script>

    <script type="text/javascript" src="js/tableExport.js"></script>
    <script type="text/javascript" src="js/jquery.base64.js"></script>
    <script type="text/javascript" src="js/html2canvas.js"></script>
    <script type="text/javascript" src="js/sprintf.js"></script>
    <script type="text/javascript" src="js/jspdf.js"></script>
    <script type="text/javascript" src="js/base64.js"></script>


    <style>
        body {
            background-color: #fdfbfe;
        }

        #headerimg1 {
            float: left;
        }

        #container1 {
            display: block;
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

</head>


<body>

<div class="container" id="container1">
    <div class=" row " style=" height: 97px; " id="header1">
        <div class="logo " style="margin-top: 30px; margin-left: 2cm;" id="headerimg1">
            <img src="http://www.farsight.com.cn/templets/default/images/logo.png " alt=" " width="315 " height="42 ">
        </div>


    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-center">
                <strong>项目实战课程反馈表</strong>
            </h3>
        </div>
    </div>
    <div class="row clearfix" id="bodyer1">
        <div class="col-md-12 column">

            <table id='test' class="table table-condensed table-bordered table-hover">
                <thead>
                <tr>
                    <td>课程名称：</td>
                    <td id="select_cource">
                        <select style="width: 30%;" id="courseselect" onchange="showtheteacher(); ">
                        </select>

                    </td>
                    <td >
                        <div style="float:right">课程日期：</div>
                    </td>
                    <td colspan="3" id="select_date">
                        <select style="width: 100%;" id="selectDate">

                        </select>

                    </td>
                    <td >中心名称：</td>
                    <td colspan="2">成都中心</td>
                </tr>
                <tr>
                    <td>培训讲师：</td>
                    <td id="select_teacher"><select style="width: 30%;" id="showteacherBycourse" onchange="showDate();">

                    </select>

                    </td>
                    <td >
                        <div style="float:right" >参培人数：</div>
                    </td>
                    <td colspan="3"><p id="countStu"></p></td>
                    <td>
                        <button id="select_button"  type="button" class="btn btn-default btn-block btn-primary" onclick="getid()">查询
                        </button>
                    </td>
                    <td>
                        <button  type="button" class="btn btn-danger btn-block btn-primary" onclick="resetPage();">重置
                        </button>
                    </td>
                    <td colspan="1">
                        <button  type="button" class="btn btn-danger btn-block btn-primary" onclick="method1('test');">导出
                        </button>
                    </td>
                </tr>
                <tr>
                    <th rowspan="2" width="15%" align="center" style="height: 30px;line-height:50px; overflow: hidden;">
                        类别
                    </th>
                    <th width="40%">满意度</th>
                    <th rowspan="2" width="9%" style="height: 30px;line-height:50px; overflow: hidden;">很满意</th>
                    <th rowspan="2" width="5%" style="height: 30px;line-height:50px; overflow: hidden;">满意</th>
                    <th rowspan="2" width="5%" style="height: 30px;line-height:50px; overflow: hidden;">一般</th>
                    <th rowspan="2" width="6%" style="height: 30px;line-height:50px; overflow: hidden;">不满意</th>
                    <th rowspan="2" width="8%" style="height: 30px;line-height:50px; overflow: hidden;">很不满意</th>
                    <th rowspan="2" width="6%" style="height: 30px;line-height:50px; overflow: hidden;">满意率</th>
                    <th rowspan="2" width="6%">主讲教师满意度</th>
                </tr>
                <tr>
                    <th >项目内容</th>
                </tr>
                </thead>


                <tbody id="tbodyid1">

                </tbody>

            </table>
            <h3>
                部分学员评语/建议：
            </h3>
            <textarea style="width: 100%; " disabled="disabled" id="DescriptionTextarea">
						学员：相应评论
					</textarea>
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
    <div class="row" style="height: 110px; margin-top: 50px; " id="footer">
        <p class="u-foot" style="color: #767074;text-align: center;">Copyright 2004-2017 华清远见教育集团 版权所有
            ，京ICP备16055436号</p>
    </div>

</div>


</body>
</html>
