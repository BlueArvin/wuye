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
			<table class="table table-hover text-center">
				<tr>
					<th width="120">日期</th>
					<th>类别</th>
					<th>内容</th>
					<th>原因</th>
					<th>问题图片</th>
					<th>扣分值</th>
					<th>检查人</th>
					<th>操作</th>
				</tr>
				<c:forEach varStatus="i" var="assessBean" items="${list }">
					<tr>
						<td>${assessBean.timeStr }</td>
						<td>${assessBean.assessName }</td>
						<td>${assessBean.assesstopName }</td>
						<td>${assessBean.msg }</td>
						<td>
						<c:if test="${assessBean.img1!=null }">
							<img src="${assessBean.img1 }" width="150px" />
						</c:if>
						<c:if test="${assessBean.img2!=null }">
							<img src="${assessBean.img2 }" width="150px" />
						</c:if>
						<c:if test="${assessBean.img3!=null }">
							<img src="${assessBean.img3 }" width="150px" />
						</c:if>
						<c:if test="${assessBean.img4!=null }">
							<img src="${assessBean.img4 }" width="150px" />
						</c:if>
						</td>
						<td align="center"><span id="score_span_${assessBean.id}">${assessBean.score }</span><input
							type="number" class="input" style="width: 80px; display: none;"
							id="score_input_${assessBean.id }" value="${assessBean.score }" /></td>
						<td>${assessBean.userName }</td>
						<td>
							<div class="button-group">
								<a class="button border-red" href="javascript:void(0)"
									onclick="return update(${assessBean.id },this)"><span
									class="icon-trash-o"></span> 修改</a> <a class="button border-green"
									style="display: none;" href="javascript:void(0)"
									onclick="return save(${assessBean.id },this)"><span
									class="icon-trash-o"></span> 完成</a> <a class="button border-red"
									href="javascript:void(0)"
									onclick="return del(${assessBean.id })"><span
									class="icon-trash-o"></span> 删除</a>
							</div>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="8">
						<div class="pagelist">
							<a href="javascript:load(${page.page-1 })">上一页</a> <a
								num="${page.page-2 }" href="javascript:load(${page.page-2 })">${page.page-2 }</a>
							<a num="${page.page-1 }" href="javascript:load(${page.page-1 })">${page.page-1 }</a>
							<span class="current">${page.page }</span> <a
								num="${page.countPage - page.page }"
								href="javascript:load(${page.page+1 })">${page.page+1 }</a> <a
								num="${page.countPage - page.page - 1 }"
								href="javascript:load(${page.page+2 })">${page.page+2 }</a> <a
								href="javascript:load(${page.page+1 })">下一页</a> <a
								href="javascript:load(${page.countPage })">尾页</a>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<script type="text/javascript">

$(function(){
	$("a[num=0],a[num=-1],a[num=-2]").hide();
	var retMsg="${msg }";
	if(retMsg!=null&&retMsg!=""){
		alert(retMsg);
	}
	
	
	/* $("#streetid").change(function(){
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
	}); */
})

/* function queryAll(){
	var time = $("#time").val();
	var pianquid = $("#pianquid").val();
	var streetid = $("#streetid").val();
	var yeneiid = $("#yeneiid").val();
	var assessidtop = $("#assessidtop").val();
	
	var url = "/manager/toAssessInfo.aspx?pageNum=1";
	
	var param = "";
	if(time != null&&time!=""){
		param+="&timeStr="+time;
	}
	if(streetid != 0){
		param+="&streetid="+streetid;
	}
	if(pianquid != 0){
		param+="&pianquid="+pianquid;
	}
	
	if(yeneiid != 0){
		param+="&yeneiid="+yeneiid;
	}
	if(assessidtop != 0){
		param+="&assessidtop="+assessidtop;
	}
	
	//localStorage.setItem("questionParam",param);
	location.href=url+param;
} */

function load(pageNum){
	var count = ${page.countPage };
	var now = ${page.page };
	if(pageNum<=0||pageNum>count){
		return;
	}
	var param = localStorage.getItem("questionParam");
	location.href="/manager/toAssessInfo.aspx?pageNum="+pageNum+param;
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
				location.href="/manager/toAssessInfo.aspx?pageNum=1";
			},
			error : function() {
				alert("请求异常！");
			}
		});
	}
}

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
	
}


</script>
</body>
</html>