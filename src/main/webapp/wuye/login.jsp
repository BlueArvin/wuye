<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>后台管理中心</title>
<link rel="stylesheet" href="/wuye/css/pintuer.css">
<link rel="stylesheet" href="/wuye/css/admin.css">
<script src="/wuye/js/jquery.js"></script>
<script src="/wuye/js/pintuer.js"></script>
</head>
<body>
	<div class="bg"></div>
	<div class="container">
		<div class="line bouncein">
			<div class="xs6 xm4 xs3-move xm4-move">
				<div style="height: 150px;"></div>
				<div class="media media-y margin-big-bottom"></div>
				<!-- <form method="post"> -->
					<div class="panel loginbox">
						<div class="text-center margin-big padding-big-top">
							<h1>后台管理中心</h1>
						</div>
						<div class="panel-body"
							style="padding: 30px; padding-bottom: 10px; padding-top: 10px;">
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input input-big" name="userName"
										placeholder="登录账号" data-validate="required:" /> <span
										class="icon icon-user margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="password" class="input input-big" name="passwd"
										placeholder="登录密码" data-validate="required:" /> <span
										class="icon icon-key margin-small"></span>
								</div>
							</div>
							<p id ="errorTips" class="pagelist" style="color:red;display:none;overflow:right" >用户名或密码错误</p>
						</div>
						<div style="padding: 30px;">
							<input id="loginBtn" type="submit"
								class="button button-block bg-main text-big input-big"
								value="登录">
						</div>
					</div>
				<!-- </form> -->
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	 if(window.top!=window.self){//不存在父页面
		top.document.location.href='/wuye/login.jsp';
	 }
	
	$("#loginBtn").on("click",function(){
		var userName = $("input[name='userName']").val();
		var passwd = $("input[name='passwd']").val();
		if(userName==null||userName==""){
			alert("请输入用户名");
			return;
		}
		if(passwd==null||passwd==""){
			alert("请输入用密码");
			return;
		}
		
		
		$.ajax({
			url : "/manager/login.aspx",
			data : {loginName:userName,passwd:passwd},
			type : 'post',
			async : true,
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if(data.ret==0){
					window.location.href="/manager/index.aspx";
				}else if(data.ret==-1){
					$("#errorTips").show();
				}
			},
			error : function() {
				alert("请求异常！");
			}
		});
	})
})
</script>
</html>