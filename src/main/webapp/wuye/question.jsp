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
    <title>问题修改</title>  
    <link rel="stylesheet" href="/wuye/css/pintuer.css">
    <link rel="stylesheet" href="/wuye/css/admin.css">
    <script src="/wuye/js/jquery.js"></script>   
    <script src="/wuye/js/pintuer.js"></script>  
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 问题修改</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
	        <div style="float:left">
	          <label  style="float:left" >检查日期：</label>
	          <input type="date" class="input w66"  id="time" name="time" style="float:left" />
	          <label  style="float:left" >&nbsp;&nbsp;&nbsp;&nbsp;城区名称：</label>
	          <select name="areaid" id="areaid" class="input w66"  style="float:left" >
	              <option value=0>请选择城区</option>
	              <c:forEach varStatus="i" var="areaBean" items="${areaList }" > 
	              	<option value=${areaBean.id }>${areaBean.name }</option>
	              </c:forEach>
	            </select>
	        </div>
        </li>
        <li style="padding-top: 5px">
	        <div style="float:left">
	          <label  style="float:left" >街道名称：</label>
	          <select name="streetid" id="streetid" class="input w66"  style="float:left" >
	              <option value=0>请选择街道</option>
	            </select>
	          <label  style="float:left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;检查人：</label>
	          <input type="text" class="input w66"  id="userName" name="userName" style="float:left" />
	            &nbsp;&nbsp;&nbsp;
	          <button type="button"  class="button border-green" onclick="queryAll()" ><span class="icon-check"></span> 查询</button>
	        </div>
        </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">日期</th>
        <th>城区</th>       
        <th>街道</th>
        <th>片区</th>
        <th>胡同</th>
        <th>物业</th>
        <th>评分</th>
        <th>检查人</th>
        <th>操作</th>       
      </tr>     
      <c:forEach  varStatus="i" var="assessBean" items="${list }" > 
	      <tr>
	          <td>${assessBean.timeStr }</td>
	          <td>${assessBean.areaName }</td>
	          <td>${assessBean.streetName }</td>
	          <td>${assessBean.pianquName }</td>
	          <td>${assessBean.hutongName }</td>
	          <td>${assessBean.wuyeName }</td>
	          <td align="center"><span id="score_span_${assessBean.id}">${assessBean.score }</span><input type="number" class="input" style="width:80px;display:none;" id="score_input_${assessBean.id }" value="${assessBean.score }" /></td>
	          <td>${assessBean.userName }</td>
	          <td>
	          	<div class="button-group">
	           		<a class="button border-red" href="javascript:void(0)" onclick="return update(${assessBean.id },this)"><span class="icon-trash-o"></span> 修改</a>
	           		<a class="button border-green" style="display:none;" href="javascript:void(0)" onclick="return save(${assessBean.id },this)"><span class="icon-trash-o"></span> 完成</a> 
	           		<a class="button border-red" href="javascript:void(0)" onclick="return del(${assessBean.id })"><span class="icon-trash-o"></span> 删除</a>
	           	</div>
	           </td>
	        </tr>
      </c:forEach>
      <tr>
        <td colspan="8">
        	<div class="pagelist">
	        	<a href="javascript:load(${page.page-1 })">上一页</a>
	        	<a num="${page.page-2 }" href="javascript:load(${page.page-2 })">${page.page-2 }</a>
	        	<a num="${page.page-1 }" href="javascript:load(${page.page-1 })">${page.page-1 }</a>
	        	<span class="current">${page.page }</span>
	        	<a num="${page.countPage - page.page }" href="javascript:load(${page.page+1 })">${page.page+1 }</a>
	        	<a num="${page.countPage - page.page - 1 }" href="javascript:load(${page.page+2 })">${page.page+2 }</a>
	        	<a href="javascript:load(${page.page+1 })">下一页</a>
	        	<a href="javascript:load(${page.countPage })">尾页</a>
        	</div>
        </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

$(function(){
	$("a[num=0],a[num=-1]").hide();
	var retMsg="${msg }";
	if(retMsg!=null&&retMsg!=""){
		alert(retMsg);
	}
	
	
	$("#areaid").change(function(){
		var areaId = $(this).val();
		$("#streetid").val(0);
		$("#streetid option:not(:first)").remove();
		if(areaId==0){
			return;
		}
		$.ajax({
			url : "/manager/queryStreet.aspx",
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
					$("#streetid").append(options);
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
	var areaid = $("#areaid").val();
	var streetid = $("#streetid").val();
	var userName = $("#userName").val();
	var url = "/manager/toAssess.aspx?pageNum=1";
	
	var param = "";
	if(time != null&&time!=""){
		param+="&timeStr="+time;
	}
	if(areaid != 0){
		param+="&areaid="+areaid;
	}
	if(streetid != 0){
		param+="&streetid="+streetid;
	}
	if(userName.trim() != ""){
		param+="&userName="+userName.trim();
	}
	localStorage.setItem("questionParam",param);
	location.href=url+param;
}

function load(pageNum){
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
</body></html>