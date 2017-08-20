
$(document).ready(function() {
    $("#headerTeacher_bar").hide();
    $("#admin_bar").hide();
    $("#feedback_bar").hide();
    if (process == 2) {
        $("#headerTeacher_bar").show();
        $("#feedback_bar").show();
    } else if (process == 3) {
        $("#admin_bar").show();
    }
	showCource();
	showKind();

});
function showCource() {
	$.ajax({
		type : "post",
		url : "tea/showCource.do",
		dataType : "json",
		data : {
			
		},
		success : function(courceList) {
			var inf;
             
          
			for (var i = 0; i < courceList.length; i++) {
				cource = courceList[i];
				inf += "<option  value='"+ cource.cource_name+"'>"+ cource.cource_name+"</option>";
			}
			$("#bbb").html(inf);
			showTeacher();
		},
		error : function(e) {
			alert("失败")

		}
	});
}
function showTeacher() {
	var abc=$("#bbb").val();
	//alert(abc);
	$.ajax({
		type : "post",
		url : "tea/showTeacher.do",
		dataType : "json",
		data : {
			"name":abc
		},
		success : function(teacherList) {
			
			var ninf;

			for (var i = 0; i < teacherList.length; i++) {
				var teacher = teacherList[i];
				//alert(teacher.name);
				ninf += "<option  value='"+ teacher.name+"'>"+ teacher.name+"</option>";
				
			}
			$("#ccc").html(ninf);
			showDate();
		},
		error : function(e) {
			alert("失败")

		}
	});
}
function showDate() {
	//alert("hahzsd");
	var a = $("#bbb").val();
	var c = $("#ccc").val();
	//alert(a);
	//alert(c);
	$.ajax({
		type : "post",
		url : "tea/showDate.do",
		dataType : "json",
		data : {
			"courcename":a,
	        "teachername":c
		},
		success : function(courceList) {
			
			var tinf;

			for (var i = 0; i < courceList.length; i++) {
				var cource = courceList[i];
				//alert(cource.date);
				tinf += "<option  value='"+cource.date+"'>"+ cource.date+"</option>";
			
			}
			$("#ddd").html(tinf);
			
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
						//alert(list[i].k_id);
						$.ajax({
							
						            async:false,
									type : "post",
									url : "showContent.do",
									dataType : "json",
									data : {
										"kind_id" :list[i].k_id
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
												contentStr += "<td></td>";
												contentStr += "</tr>";
											} else {
												contentStr += "<tr>";
												contentStr += "<td>"
														+ ContentList[j].c_name
														+ "</td>";
												contentStr += "<td></td>";
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
function updateCource(){
	
	var updatedate = $("#ddd").val();
	var updatecource = $("#bbb ").val();
	var updateteacher = $("#ccc" ).val();

	
	$.ajax({
		type:"post",
		url:"tea/updateCource.do",
		dataType:"text",
		data:{
			"cource_name":updatecource,	
			"cource_date":updatedate,
			"teacher_name":updateteacher
		},
		success:function(inf) {
			
			if("ok"==inf) {
				//刷新显示
				alert("提交成功");
				showCource();
				
			}else {
				alert("增加失败");
			}						
		},
		error:function(e) {
			alert("ajax请求失败");
		}
	});
}