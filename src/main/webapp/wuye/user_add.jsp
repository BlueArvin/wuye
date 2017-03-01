<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>添加用户</title>  
    <link rel="stylesheet" href="/wuye/css/pintuer.css">
    <link rel="stylesheet" href="/wuye/css/admin.css">
    <script src="/wuye/js/jquery.js"></script>
    <script src="/wuye/js/pintuer.js"></script>  
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 添加用户</strong></div>
  <div class="body-content">
    <form method="post" class="form-x">
      <div class="form-group">
        <div class="label">
          <label>账户：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="cn" value="${userBean.cn } " />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>用户名：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="userName" value="${userBean.userName }" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>初始密码：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="passwd" value="${userBean.passwd }" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>app权限分配：</label>
        </div>
        <div class="field" style="padding-top:8px;">
            <input id="xmcheck" name="xmcheck" value="1" type="checkbox" />项目检查&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="jlquery" name="jlquery" value="1" type="checkbox" />记录查询&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>web权限分配：</label>
        </div>
        <div class="field" style="padding-top:8px;">
                  <input id="yhgl" name="yhgl" value="100000"  type="checkbox" />用户管理&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="bzsz"  name="bzsz" value="200000" type="checkbox" />标准设置&nbsp;&nbsp;&nbsp;&nbsp;
                     <input id="khxg" name="khxg" value="300000" type="checkbox" />考核修改&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="sjfx" name="sjfx" value="400000" type="checkbox" />数据分析&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" onclick="subForm()" type="button"> 确认添加</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
<script type="text/javascript">
Array.prototype.contains = function(obj) {
  var i = this.length;
  while (i--) {
    if (this[i] === obj) {
      return true;
    }
  }
  return false;
}
$(function(){
	
	//初始化checkbox
	
	var role = "${userBean.role }";
	var webRole = "${userBean.webRole }";
	
	var xmcheck = false;
	var jlquery = false;
	if(role != ""){
		if(role&2!=0){
			xmcheck = true;
		}
		if(role&1!=0){
			jlquery = true;
		}
	}
	var cbArray = webRole.split(",");
	var yhgl = cbArray.contains("100000");
	var bzsz = cbArray.contains("200000");
	var khxg = cbArray.contains("300000");
	var sjfx = cbArray.contains("400000");
	
	var ckArray = ["xmcheck","jlquery","yhgl","bzsz","khxg","sjfx"];
	var ckArrayValue = [xmcheck,jlquery,yhgl,bzsz,khxg,sjfx]
	
	for(i=0;i<ckArray.length;i++){
		var name = ckArray[i];
		$("input[name='"+name+"']").attr("checked",ckArrayValue[i]);
	}
	
	//alert("xmcheck="+xmcheck+",jlquery="+jlquery+",yhgl="+yhgl+",bzsz="+bzsz+",khxg="+khxg+",sjfx="+sjfx);
	
})

function subForm(){
	var cn = $("input[name='cn']").val();
	var userName = $("input[name='userName']").val();
	var passwd = $("input[name='passwd']").val();
	
	if(cn==""||userName==""||passwd==""){
		alert("表单信息不全");
		return;
	}
	
	var postData = getFormField($("form"), "name");
	console.log(postData);
	
	var id = "${userBean.id }";
	if(id != ""){
		postData.id=id;
	}
	var url = "/manager/addUser.aspx";
	$.ajax({
		url : url,
		data : postData,
		type : 'post',
		async : true,
		cache : false,
		dataType : 'json',
		success : function(data) {
			console.log(data);
			alert(data.msg);
			if(data.ret==0){
				$("input[type='text']").val("");
				$("input[type='checkbox']").attr("checked",false);
				//window.location.href="/manager/index.aspx";
			}
		},
		error : function() {
			alert("请求异常！");
		}
	});
}
</script>


</html>