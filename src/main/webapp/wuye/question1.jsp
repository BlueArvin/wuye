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
<title>问题修改</title>
<link rel="stylesheet" href="/wuye/css/pintuer.css">
<link rel="stylesheet" href="/wuye/css/admin.css">
<script src="/wuye/js/jquery.js"></script>
<script src="/wuye/js/pintuer.js"></script>
</head>
<body>
	<form method="post" action="">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder"> 问题修改</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search">
					<li style="padding-top: 5px">
						<div style="float: left">
							<label style="float: left">检查日期：</label> <input type="date"
								class="input w66" id="time" name="time" style="float: left" />
						</div>
					</li>
					<li style="padding-top: 5px">
						<div style="float: left">
							<label style="float: left">街道名称：</label> <select name="streetid"
								id="streetid" class="input w66" style="float: left">
								<option value=0>请选择街道</option>
								<c:forEach varStatus="i" var="areaBean" items="${areaList }">
									<option value=${areaBean.id }>${areaBean.name }</option>
								</c:forEach>
							</select> <label style="float: left">&nbsp;&nbsp;&nbsp;&nbsp;片区名称：</label>
							<select name="pianquid" id="pianquid" class="input w66"
								style="float: left">
								<option value=0>请选择片区</option>
							</select>
						</div>
					</li>
					<li style="padding-top: 5px">
						<div>
							<label style="float: left">考核业态：</label> <select name="yeneiid"
								id="yeneiid" class="input w66" style="float: left">
								<option value=0>请选择业态</option>
								<option value=1>内业</option>
								<option value=2>外业</option>
							</select> <label style="float: left">&nbsp;&nbsp;&nbsp;&nbsp;考核类别：</label>
							<select name="assessidtop" id="assessidtop" class="input w66"
								style="float: left">
								<option value=0>请选择类别</option>
							</select> &nbsp;&nbsp;&nbsp;
							<button type="button" class="button border-green"
								onclick="queryAll()">
								<span class="icon-check"></span> 查询
							</button>
						</div>
					</li>
				</ul>
			</div>
			<iframe name="Info1" id="Info1" src="/manager/toAssessInfo.aspx?pageNum=1" onload="this.height=Info1.document.body.scrollHeight" width="100%" scrolling="no" frameborder="0"></iframe>
		</div>
	</form>
	<script type="text/javascript">

$(function(){
	/* $("a[num=0],a[num=-1]").hide();
	var retMsg="${msg }";
	if(retMsg!=null&&retMsg!=""){
		alert(retMsg);
	} */
	
	
	$("#streetid").change(function(){
		var areaId = $(this).val();
		$("#pianquid").val(0);
		$("#pianquid option:not(:first)").remove();
		if(areaId==0){
			return;
		}
		$.ajax({
			url : "/manager/queryPianqu.aspx",
			data : {parentId:areaId},
			type : 'post',
			async : true,
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if(data.ret==0){
					var options = "";
					$.each(data.data, function (index, info) {
						var id = info.id;
						var name = info.name;
						options+="<option value="+id+">"+name+"</option>";
					 })
					$("#pianquid").append(options);
				}
			},
			error : function() {
				alert("请求异常！");
			}
		});
	});
	
	
	$("#yeneiid").change(function(){
		var yeneiid = $(this).val();
		$("#assessidtop").val(0);
		$("#assessidtop option:not(:first)").remove();
		if(yeneiid==0){
			return;
		}
		$.ajax({
			url : "/manager/queryKhCate.aspx",
			data : {type:yeneiid},
			type : 'post',
			async : true,
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if(data.ret==0){
					var options = "";
					$.each(data.data, function (index, info) {
						var id = info.categoryNo;
						var name = info.categoryName;
						options+="<option value="+id+">"+name+"</option>";
					 })
					$("#assessidtop").append(options);
				}
			},
			error : function() {
				alert("请求异常！");
			}
		});
	});
})

function queryAll(){
	var time = $("#time").val();
	var pianquid = $("#pianquid").val();
	var streetid = $("#streetid").val();
	var yeneiid = $("#yeneiid").val();
	var assessidtop = $("#assessidtop").val();
	
	var url = "/manager/toAssessInfo.aspx?pageNum=1";
	
	var param = "";
	if(time != null&&time!=""){
		param+="&timeStr="+time;
	}else{
		alert("请选择日期");
		return;
	}
	
	if(streetid != 0){
		param+="&streetid="+streetid;
	}else{
		alert("请选择街道");
		return;
	}
	if(pianquid != 0){
		param+="&pianquid="+pianquid;
	}else{
		alert("请选择片区");
		return;
	}
	
	if(yeneiid != 0){
		param+="&yeneiid="+yeneiid;
	}else{
		alert("请选择考核业内");
		return;
	}
	if(assessidtop != 0){
		param+="&assessidtop="+assessidtop;
	}else{
		alert("请选考核类别");
		return;
	}
	
	localStorage.setItem("questionParam",param);
	
	$('#Info1').attr('src', url+param);
	//$("#Info1").load(url+param);
	//document.getElementById("Info1").document.location=url+param;
	//document.frames("Info1").document.location=url+param;//ok
}
localStorage.setItem("questionParam","");
/* function load(pageNum){
	var count = ${page.countPage };
	var now = ${page.page };
	if(pageNum<=0||pageNum>count){
		return;
	}
	var param = localStorage.getItem("questionParam");
	location.href="/manager/toAssess.aspx?pageNum="+pageNum+param;
}

function del(id){
	if(confirm("您确定要删除吗?")){
		$.ajax({
			url : "/manager/delAssess.aspx",
			data : {id:id},
			type : 'post',
			async : true,
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				alert(data.msg);
				location.href="/manager/toAssess.aspx?pageNum=1";
			},
			error : function() {
				alert("请求异常！");
			}
		});
	}
} */
/* 
function update(id,obj){
	$(obj).hide();
	$(obj).next("a").show();
	$("#score_span_"+id).hide();
	$("#score_input_"+id).show();
}


function save(id,obj){
	$(obj).hide();
	$(obj).prev("a").show();
	var value = $("#score_input_"+id).val();
	$("#score_input_"+id).hide();
	$("#score_span_"+id).text(value).show();
	
	$.ajax({
		url : "/manager/updateAssess.aspx",
		data : {id:id,score:value},
		type : 'post',
		async : true,
		cache : false,
		dataType : 'json',
		success : function(data) {
			console.log(data);
			alert(data.msg);
		},
		error : function() {
			alert("请求异常！");
		}
	});
	
} */

</script>
</body>
</html>