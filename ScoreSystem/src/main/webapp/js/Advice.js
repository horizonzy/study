$(document).ready(function(){
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

