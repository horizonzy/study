	var allcontent=new Array();		//所有的项目内容
	var allcountKindnum=new Array();  //每个类别包含的项目数量集合
	var allkind=new Array();		//所有的类别集合
	var allStat=new Array();   //接受人数统计的 二维数组！
$(document).ready(function(){
    $("#headerTeacher_bar").hide();
    $("#admin_bar").hide();
    $("#feedback_bar").hide();
    if (process == 2) {
        $("#headerTeacher_bar").show();
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

//查询ID，并自动调用其它查询显示的方法
function getid(){
	var name1=$("#courseselect").val();
	var name2=$("#showteacherBycourse").val();
	var date=$("#selectDate").val();
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