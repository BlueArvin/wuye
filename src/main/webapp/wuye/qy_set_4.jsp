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
    <title>城区设置</title>  
    <link rel="stylesheet" href="/wuye/css/pintuer.css">
    <link rel="stylesheet" href="/wuye/css/admin.css">
    <script src="/wuye/js/jquery.js"></script>   
    <script src="/wuye/js/pintuer.js"></script>  
</head>
<body>
    <form method="post" action="/manager/addArea.aspx">
      <ul class="search">
        <li>
        	<div class="form-group">
	          <label>片区名称：</label>
	          <select class="input w66" id="parentId" name="parentId" style="float:right" >
	              <option value=0>请选择片区</option>
	              <c:forEach  varStatus="i" var="areaBean" items="${parentList }" > 
	              	<option value=${areaBean.id }>${areaBean.name }</option>
	              </c:forEach>
	            </select>
	        </div>
	        <div class="form-group">
	          <label>胡同名称：</label>
	          <input type="hidden" class="input" id="type" name="type" value=4 style="float:right" />
	          <input type="hidden" class="input" id="id" name="id" value=0 style="float:right" />
	          <input type="text" class="input" id="name" name="name" style="float:right" />
	        </div>
	    </li>
	    <li><button type="submit"  class="button border-green"  style="float:right" id="addBtn"><span class="icon-check"></span> 确认添加</button></li>
	  </ul>
	  </form>
	  <table class="table table-hover text-center">
	      <tr>
	        <th width="120">序号</th>
	        <th>胡同名称</th>
	        <th>隶属于</th>       
	        <th>操作</th>       
	      </tr>      
	        <c:forEach  varStatus="i" var="areaBean" items="${list }" > 
	      <tr>
	          <td>${areaBean.id }</td>
	          <td>${areaBean.name }</td>
	          <td>${areaBean.parentName }</td>
	          <td>
	          	<div class="button-group">
	           		<a class="button border-red" href="javascript:void(0)" onclick="return update(${areaBean.id },${areaBean.parentId },'${areaBean.name }')"><span class="icon-trash-o"></span> 修改</a> 
	           		<a class="button border-red" href="javascript:void(0)" onclick="return del(${areaBean.id })"><span class="icon-trash-o"></span> 删除</a>
	           	</div>
	           </td>
	        </tr>
      		</c:forEach>
	        <tr>
		        <td colspan="4">
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
<script type="text/javascript">


$(function(){
	$("a[num=0],a[num=-1]").hide();
	var retMsg="${msg }";
	if(retMsg!=null&&retMsg!=""){
		alert(retMsg);
	}
})

function load(pageNum){
	var count = ${page.countPage };
	var now = ${page.page };
	if(pageNum<=0||pageNum>count){
		return;
	}
	location.href="/manager/toAreaSetChild.aspx?type=4&pageNum="+pageNum;
}

function del(id){
	if(confirm("您确定要删除吗?")){
		$.ajax({
			url : "/manager/deleteArea.aspx",
			data : {id:id,type:4},
			type : 'post',
			async : true,
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				alert(data.msg);
				location.href="/manager/toAreaSetChild.aspx?type=4&pageNum=1";
			},
			error : function() {
				alert("请求异常！");
			}
		});
	}
}


function update(id,parentId,name){
	$("#id").val(id);
	$("#parentId").val(parentId);
	$("#name").val(name);
	$("#addBtn").text("确认修改");
}

</script>
</body></html>