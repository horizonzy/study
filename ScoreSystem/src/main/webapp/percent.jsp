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
    <script src="./js/percent.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        var process=${requestScope.process};

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
            <table id="test" class="table table-condensed">
                <tr>
                    <td width="15%">课程名称：</td>
                    <td width="15%">
                        <select style="width: 100%;" id="courseselect" onchange="showtheteacher(); ">

                        </select>
                    </td>
                    <td width="15%">
                        <div style="float:right">课程日期：</div>
                    </td>
                    <td width="25%">
                        <select style="width: 100%;" id="selectDate">

                        </select>
                    </td>
                    <td width="10%">中心名称：</td>
                    <td width="20%">成都中心</td>
                </tr>
                <tr>
                    <td>培训讲师：</td>
                    <td><select style="width: 100%;" id="showteacherBycourse" onchange="showDate();">

                    </select>
                    </td>
                    <td>
                        <div style="float:right">参培人数：</div>
                    </td>
                    <td><p id="countStu"></p></td>
                    <td>
                        <button type="button" class="btn btn-default btn-block btn-primary" onclick="getid()">查询
                        </button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger btn-block btn-primary" onclick="method1('test');">导出
                        </button>

                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="row clearfix" id="bodyer1">
        <div class="col-md-12 column">

            <table class="table table-condensed table-bordered table-hover">
                <thead>
                <tr>
                    <th rowspan="2" width="15%" align="center" style="height: 30px;line-height:50px; overflow: hidden;">
                        类别
                    </th>
                    <th width="43%">满意度</th>
                    <th rowspan="2" width="6%" style="height: 30px;line-height:50px; overflow: hidden;">很满意</th>
                    <th rowspan="2" width="5%" style="height: 30px;line-height:50px; overflow: hidden;">满意</th>
                    <th rowspan="2" width="5%" style="height: 30px;line-height:50px; overflow: hidden;">一般</th>
                    <th rowspan="2" width="6%" style="height: 30px;line-height:50px; overflow: hidden;">不满意</th>
                    <th rowspan="2" width="8%" style="height: 30px;line-height:50px; overflow: hidden;">很不满意</th>
                    <th rowspan="2" width="6%" style="height: 30px;line-height:50px; overflow: hidden;">满意率</th>
                    <th rowspan="2" width="6%">主讲教师满意度</th>
                </tr>
                <tr>
                    <th rowspan="2">项目内容</th>
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
        <p class="u-foot" style="color: #767074;text-align: center;">Copyright 2004-2017 华清远见教育集团 版权所有
            ，京ICP备16055436号</p>
    </div>

</div>


</body>
</html>
