var userObj;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj){
	$.ajax({
		type:"GET",
		url:path+"/sys/user/deluser",
		data:{uid:obj.attr("userid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
				//删除成功重新加载该页面
				window.location.href=path+"/sys/user/userlist.htm";
			}else if(data.delResult == "false"){//删除失败
				changeDLGContent("对不起，删除用户【"+obj.attr("username")+"】失败");
			}else if(data.delResult == "notexist"){
				changeDLGContent("对不起，用户【"+obj.attr("username")+"】不存在");
			}
		},
		error:function(data){
			changeDLGContent("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	//查询视图
	$(".viewUser").on("click",function(){
		var obj = $(this);
		//使用ajax方式查询用户对象
		$.ajax({
			url: path+"/sys/user/userview",
			data: {id:obj.attr("userid")},
			type: "POST",
			dataType: "json",
			success : function(result){
				$("#userCode").val(result.userCode);
				$("#userName").val(result.userName);
				$("#userPassword").val(result.userPassword);
				if(result.gender == 1){
					$("#gender").val("男");
				}else{
					$("#gender").val("女");
				}
				$("#birthday").val(result.birthday);
				$("#phone").val(result.phone);
				$("#address").val(result.address);
				$("#creationDate").val(result.creationDate);
			}
		});
	});
	
	/*修改用户信息*/
	$(".modifyUser").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/sys/user/usermodify.htm?id="+ obj.attr("userid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteUser(userObj);
	});
	$(".deleteUser").on("click",function(){
		userObj = $(this);
		changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");
		openYesOrNoDLG();
	});
});