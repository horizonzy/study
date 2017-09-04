<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
		   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
		<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


		<link href="./css/allgrade.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="./js/cource.js"></script>
		<script type="text/javascript">
			     var process=${requestScope.process};
                 var tlcass_id = ${requestScope.tclass_id};

                $(document).ready(function(){
					alert(tclass_id);
                    $("#admin_bar").hide();
                    $("#feedback_bar").hide();
                    if(process==2){

                        $("#feedback_bar").show();
                    }else if(process==3){
                        $("#admin_bar").show();
                    }
                });
                 var kinds=new Array();
                 var contents=new Array();
                 var zzz=0;
                 var index=0;
                 <!-- 显示班级名字-->
                 $(document).ready(function(){
                     getKindSize();
                     showCourceName();
                 });
                 function showCourceName(){
                     $.ajax({
                         type : "post",
                         url: "cource/showAllCource.do",
                         dataType : "json",
                         data : {
                             "tclass_id":tlcass_id
                         },
                         success:function(courceList){
                             var courceInf="";
                             var cource;
                             for(var i=0;i<courceList.length;i++){
                                 cource=courceList[i];
                                 courceInf+="<option  value='"+cource.name+"'>"+cource.name+"</option>";
                             }
                             $("#courceselect").html(courceInf);
                             showtheteacher();
                         },
                         error : function(msg)
                         {
                         }
                     });

                 }
                 function showtheteacher(){
                     var sel=$("#courceselect").val();
                     //alert(name);
                     $.ajax({
                         type:"post",
                         url:"cource/seleceteacherBycourse.do",
                         dataType:"json",
                         data:{
                             "name":sel
                         },
                         success:function(TeacherList) {
                             var teacherInf="";
                             var teacher;
                             for(var i=0;i<TeacherList.length;i++){
                                 teacher=TeacherList[i];
                                 teacherInf+="<option value="+teacher.name+">"+teacher.name+"</option>";
                             }
                             $("#showteacherBycourse").html(teacherInf);
                             showDate();


                         },
                         error:function(e) {
                             alert("项目内容显示失败了啦");
                         }
                     });

                 }
                 function showDate(){
                     var sel=$("#courceselect").val();
                     var sel2=$("#showteacherBycourse").val();

                     $.ajax({
                         type:"post",
                         url:"cource/selectDateBycourseAndteacher.do",
                         dataType:"json",
                         data:{
                             "coursename":sel,
                             "teachername":sel2
                         },
                         success:function(DateList){
                             var dateInf="";
                             var Date;
                             for(var i=0;i<DateList.length;i++){
                                 Date=DateList[i];
                                 dateInf+="<option value='"+Date.date+"'>"+Date.date+"</option>" ;
                             }
                             $("#dateByname").html(dateInf);
                             showStuNum();
                             showScore();


                         },
                         error:function(e) {
                             alert("项目内容显示失败了啦");
                         }
                     });
                 }
                 function showStuNum(){
                     var sel=$("#courceselect").val();
                     var sel2=$("#showteacherBycourse").val();
                     var sel3=$("#dateByname").val();
                     $.ajax({
                         type:"post",
                         url:"cource/showStuNum.do",
                         dataType:"json",
                         data:{
                             "name":sel,
                             "teacher_name":sel2,
                             "date":sel3
                         },
                         success:function(stuNum){

                             var stuInf="";
                             stuInf+="参培人数：";
                             stuInf+="<input type='text' value='"+stuNum+"'/>";
                             $("#stuNum").html(stuInf);

                         },
                         error:function(e) {
                             alert("失败了啦");
                         }
                     });
                 }
                 function showKind(){

                     $.ajax({
                         type:"post",
                         url:"content/showKind.do",
                         dataType:"json",
                         data:{},
                         success:function(kindList){
                             var kindInf="";
                             var kind;
                             for(var i=0;i<kindList.length;i++){
                                 kind=kindList[i];
                                 kindInf+="<td colspan='"+kinds[i]+"'>"+kind.nick_name+"</td>";
                             }
                             $("#kind").html(kindInf);

                             for(var i=0;i<kinds.length;i++){
                                 contentByKindId(i+1);
                                 for(var j=0;j<5000000;j++){

                                 }
                             }
                         },
                         error:function(e){
                             alert("项目内容显示失败了啦");
                         }
                     });
                 }
                 function getKindSize(){
                     $.ajax({
                         type:"post",
                         url:"content/showKindNum.do",
                         dataType:"json",
                         data:{
                         },
                         success:function(kindSizeList){
                             for(var i=0;i<kindSizeList.length;i++){
                                 kinds[i]=kindSizeList[i];
                             }
                             showKind();
                             var allkind=0;
                             for(var i=0;i<kinds.length;i++){
                                 allkind+=kinds[i];
                             }
                             zzz=allkind;
                             $("#kind_bar").attr("colspan",allkind);
                         },
                         error:function(e){
                         }
                     });

                 }
                 function contentByKindId(id){

                     $.ajax({
                         type:"post",
                         url:"content/showContentByKindId.do",
                         dataType:"json",
                         data:{
                             "id":id
                         },
                         success:function(contentList){

                             var element=document.getElementById("content");
//			var param=document.createElement("td");
//			param.innerHTML="序号";
//			element.appendChild(param);
                             for(var i=0;i<contentList.length;i++){
                                 var param = document.createElement("td");
                                 param.innerHTML=contentList[i];
                                 element.appendChild(param);
                             }




//				var contentInf="";
//				var content;
//				contentInf+="<td>序号</td>";
//				for(var i=0;i<contents.length;i++){
//					content=contents[i];
//					contentInf+="<td>"+content+"</td>";
//				}
//				$("#content").html(contentInf);







                         },
                         error:function(e){

                         }
                     });
                 }
                 function showScore(){
                     var sel=$("#courceselect").val();
                     var sel2=$("#showteacherBycourse").val();
                     var sel3=$("#dateByname").val();
                     $.ajax({
                         type:"post",
                         url:"score/showScore.do",
                         dataType:"json",
                         data:{
                             "name":sel,
                             "teacher_name":sel2,
                             "date":sel3
                         },
                         success:function(scoreList){
                             var scoreInf="";
                             var k=0;
                             for(var i=0;i<(scoreList.length)/zzz;i++){
                                 scoreInf+="<tr class='active'>";
                                 scoreInf+="<td>"+(i+1)+"</td>";
                                 for(var j=1;j<=zzz;j++){
                                     scoreInf+="<td>"+scoreList[k].grade+"</td>";
                                     k++;
                                     if(j==zzz){
                                         scoreInf+="</tr>";
                                     }
                                 }
                             }
                             $("#score").html(scoreInf);
                             showSum();
                         },
                         error:function(e){
                         }
                     });
                 }
                 function showSum(){
                     var sel=$("#courceselect").val();
                     var sel2=$("#showteacherBycourse").val();
                     var sel3=$("#dateByname").val();
                     $.ajax({
                         type:"post",
                         url:"score/showScore.do",
                         dataType:"json",
                         data:{
                             "name":sel,
                             "teacher_name":sel2,
                             "date":sel3
                         },
                         success:function(scoreList){

                             var score;
                             var k=0;
                             var tArray=new Array;
                             for(var k=0;k<5;k++){
                                 tArray[k]=new Array();
                                 for(var j=0;j<zzz;j++){
                                     tArray[k][j]=0;
                                 }
                             }
                             for(var i=0;i<(scoreList.length)/zzz;i++){
                                 for(var j=0;j<zzz;j++){
                                     score=scoreList[i*zzz+j];

                                     if(score.grade==5){
                                         tArray[4][j]++;
                                     }else if(score.grade==4){
                                         tArray[3][j]++;
                                     }else if( score.grade==3){
                                         tArray[2][j]++;
                                     }else if(score.grade==2){
                                         tArray[1][j]++;
                                     }else if(score.grade==1){
                                         tArray[0][j]++;
                                     }
                                 }
                             }
                             var sumInf="";
                             for(var i=0;i<5;i++){
                                 sumInf+="<tr class='active'>";
                                 sumInf+="<td>"+(i+1)+"</td>";
                                 for(var j=0;j<zzz;j++){
                                     sumInf+="<td class='count'>"+tArray[i][j]+"</td>";
                                 }
                             }
                             $("#sum").html(sumInf);
                             <!--单项总分-->
                             var singleSum=new Array;
                             for(var i=0;i<zzz;i++){
                                 singleSum[i]=(tArray[0][i]*1)+(tArray[1][i]*2)+(tArray[2][i]*3)+(tArray[3][i]*4)+(tArray[4][i]*5);
                             }
                             var singleInf="";
                             singleInf+="<td>单项总分</td>";
                             for(var i=0;i<zzz;i++){
                                 singleInf+="<td>"+singleSum[i]+"</td>";
                             }
                             $("#single").html(singleInf);
                             <!--单项平均-->
                             var singleAvg=new Array;
                             for(var i=0;i<zzz;i++){
                                 singleAvg[i]=singleSum[i]/((scoreList.length)/zzz);
                             }
                             var avgInf="";
                             avgInf+="<td>单项平均</td>";
                             for(var i=0;i<zzz;i++){
                                 avgInf+="<td>"+singleAvg[i]+"</td>";
                             }
                             $("#avg").html(avgInf);
                             <!--整体平均-->
                             var allAvg=new Array;
                             var midAvg=new Array;
                             for(var i=0;i<kinds.length;i++){
                                 midAvg[i]=0;
                             }
                             var m=0;
                             for(var i=0;i<kinds.length;i++){
                                 for(var j=0;j<kinds[i];j++){
                                     midAvg[i]=midAvg[i]+singleAvg[m+j]
                                 }
                                 allAvg[i]=(midAvg[i]/kinds[i]);
                                 m=m+kinds[i];
                             }
                             var allAvgInf="";
                             allAvgInf+="<td>整体平均</td>";
                             for(var i=0;i<allAvg.length;i++){
                                 allAvgInf+="<td colspan='"+kinds[i]+"'>"+allAvg[i].toFixed(2)+"</td>";
                             }
                             $("#allAvg").html(allAvgInf);
                             <!--统计总分-->
                             var allSum=0;
                             for(var i=0;i<allAvg.length;i++){
                                 allSum=allSum+allAvg[i];
                             }
                             var allSumInf="";
                             allSumInf+="<td>统计总分</td>";
                             allSumInf+="<td colspan='"+zzz+"'>"+allSum.toFixed(2)+"</td>";
                             $("#allScore").html(allSumInf);
                         },
                         error:function(e){

                         }
                     });
                 }
		</script>
		
		<style type="text/css">
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
</head>
<body>
		<div class="container">
			<div class=" row " style=" height: 97px; " id="header">
				<div class="logo " style="margin-top: 30px; margin-left: 2cm;">
					<img src="http://www.farsight.com.cn/templets/default/images/logo.png  " alt=" " width="315 " height="42 ">
				</div>
			</div>
			<div class=" row " id="body" align="center">
				<div class="row clearfix" style="width: 100%;" align="center">
					<h1 style="background-color:brown;color:#ffffff">项目实战课程反馈统计表</h1>
				</div>
				<div class="form-group" style="height: 100px;">
					<form role="form" style="align-content: ;">
						<div align="center" id="top">
							<table class="table table-striped">
								<tr>
                                    <td id="getCourceName">
										<span>课程名称：</span>
											<select style="width: 170px;" id="courceselect" 
											onchange="showtheteacher()">
											</select>
                                     </td>
									<td>
                                        <span>课程日期：</span>
											<select style="width: 170px;" id="dateByname">
											</select>
                                        
                                    </td>
									<td>中心名称：<input type="text" value="成都中心" /></td>
								</tr>
								<tr>
									<td > 
									    <span>培训讲师：</span>
									        <select style='width: 170px;' id="showteacherBycourse"
									        onchange="showDate()">
											
											</select>
									</td>
									<td id="stuNum">参培人数：<input type="text" value="" /></td>
									<td>说明：表格中数值表示人数</td>
								</tr>
							</table>
						</div>

					</form>
				</div>
				<div>
					<table class="table table-bordered table-striped">
					    <thead>
						<tr id="title">
							<td rowspan="2">项目</td>
							<td id="kind_bar" colspan="" align="center">授课过程</td>
						</tr>
						<tr style="color: ffffff;" id="kind">
						</tr>
						<tr id="content">
						<td>序号</td>
						</tr>
						</thead>
						<tbody id="score">
							<tr class="active" >
									
							</tr>
                        </tbody>
                        <tbody id="sum">
							
						</tbody>
						
							<tr class="warning" id="single">
								
							</tr>
						
							<tr class="active" id="avg">
								
				            </tr>
							<tr class="success" id="allAvg">
								
							</tr>
							<tr class="warning" id="allScore">
								

							</tr>
					</table>
				</div>
				 <div  class="menu"  style="margin: auto;width: 45%;">
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
					<p class="u-foot" style="color: #767074;
						text-align: center;">Copyright 2004-2017 华清远见教育集团 版权所有 ，京ICP备16055436号</p>
				</div>
		</div>
		</div>

	</body>
</html>