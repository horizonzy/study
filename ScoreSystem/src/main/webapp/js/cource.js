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
		data : {  },
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