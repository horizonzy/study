
$(document).ready(function() {
    showCource();
    getNumById();
    showKind();
});
function getNumById() {

    $.ajax({
        type : "post",
        url : "getNumById.do",
        dataType : "text",
        data : {
            "id" : stu_id
        },
        success : function(num) {

            showName(num);
        },
        error : function(e) {
            alert("失败")

        }
    });

}
function showCource() {

    $.ajax({
        async : false,
        type : "post",
        url : "showCource.do",
        dataType : "json",
        data : {
            "id" : 1
        },
        success : function(List) {
            var inf;

            for (var i = 0; i < List.length; i++) {
                var mid = List[i];
                inf += "<tr>";      //+ +
                inf += "<td >课程日期：<span id='date'> " +mid.cource_date +"</span></td>"
                inf += "</tr>";
                inf += "<tr>";
                inf += "<td>课程名称： <span id='cname'>" + mid.cource_name + "</span></td>";
                inf += "</tr>";
                inf += "<tr>";
                inf += "<td>讲师姓名：<span id='tname'>"  + mid.teacher_name + "</span></td>";
                inf += "</tr>";
            }

            $("#aaa").html(inf);
        },
        error : function(e) {
            alert("失败")

        }
    });
}
function showName(stu_num) {

    $.ajax({
        type : "post",
        url : "showName.do",
        dataType : "json",
        data : {
            "num" :stu_num
        },
        success : function(stuList) {
            var inf;
            for (var i = 0; i < stuList.length; i++) {
                var stu = stuList[i];
                inf += "<tr>";
                inf += "<td>学员姓名：" + stu.name + "</td>"
                inf += "</tr>";
            }
            $("#name").html(inf);
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
                // alert(list[i].k_id);
                $.ajax({
                    async : false,
                    type : "post",
                    url : "showContent.do",
                    dataType : "json",
                    data : {
                        "kind_id" : list[i].k_id
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
                                contentStr += "<td><select id='"+ContentList[j].c_id+"'><option value='5'>5</option><option value='4'>4</option><option value='3'>3</option><option value='2'>2</option><option value='1'>1</option></select></td>";
                                contentStr += "</tr>";
                            } else {
                                contentStr += "<tr>";
                                contentStr += "<td>"
                                    + ContentList[j].c_name
                                    + "</td>";
                                contentStr += "<td><select id='"+ContentList[j].c_id+"'><option value='5'>5</option><option value='4'>4</option><option value='3'>3</option><option value='2'>2</option><option value='1'>1</option></select></td>";
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

function getcource_id(){
    var cname = $("#cname").text();
    var tname = $("#tname").text();
    var date = $("#date").text();
    $.ajax({
        type : "post",
        url : "getcource_id.do",
        dataType : "text",
        data : {
            "courcename" : cname,
            "teachername": tname,
            "date":date
        },
        success : function(cource_id) {
            getContent_id(parseInt(cource_id));
            addAdvice(cource_id);
        },
        error : function(e) {
            alert("得到id失败");
        }
    });
}
function getContent_id(cource_id) {

    $.ajax({
        type : "post",
        url : "showAllContent.do",
        dataType : "json",

        success : function(ContentList) {
            var data=[];
            for (var i = 0; i < ContentList.length; i++) {
                var id=ContentList[i].c_id;
                var grade =$("#"+id+"").val();
                var object={};
                object['content_id']=id;
                object['grade']=grade;
                object['cource_id']=cource_id;
                object['stu_id']=stu_id;
                data[i]=object;
            }
            goPost(data);
        },
        error : function(e) {
            alert("得到id失败");
        }
    });
}
function goPost(data) {

    $.ajax({
        //async:false,
        type : "post",
        url : "addGrade.do",
        dataType : "text",
        data : JSON.stringify(data),
        success : function() {

        },
        error : function(e) {

        }
    });
}
function addAdvice(cource_id) {
    var description = $("#advice").val();

    $.ajax({
        type : "post",
        url : "addAdvice.do ",
        dataType : "text",
        data : {
            "description":description,
            "cource_id":cource_id
        },
        success: function() {
            alert("提交成功");
        },
        error : function(e) {
            alert("提交失败");
        }
    });
}
