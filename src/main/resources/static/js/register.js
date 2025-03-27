$(function(){
	$("form").submit(check_data);
	$("#submitPwd").click(submitPwdClick);
	$("input").focus(clear_error);
});

function check_data() {
	var pwd1 = $("#password").val();
	var pwd2 = $("#confirm-password").val();
	if(pwd1 != pwd2) {
		$("#confirm-password").addClass("is-invalid");
		return false;
	}
	return true;
}

function clear_error() {
	$(this).removeClass("is-invalid");
}
function submitPwdClick() {
	var email = $('#yourEmail').val();
	var userName = $('#yourUserName').val();
	var password = $('#yourPassword').val();
	if(email == ''){
		alert('密码不能为空');
		return;
	}
	if(userName == ''){
		alert('用户名不能为空');
		return;
	}
	if(password == '' || password.length < 6){
		alert('密码长度必须大于等于6位');
		return;
	}
	var param = "email="+email+"&userName="+userName+"&password="+password;
	$.get('/login/forget?'+param, function (res) {
		if(res.code == 0){
			//重置密码成功
			alert(res.msg);
			location.href = CONTEXT_PATH + "/login";
		}else {
			$('#yourEmail').val("");
			$('#yourUserName').val("");
			alert(res.msg)
		}
	})
}
