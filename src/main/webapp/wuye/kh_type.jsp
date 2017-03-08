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
    <title>考核类型</title>  
    <link rel="stylesheet" href="/wuye/css/pintuer.css">
    <link rel="stylesheet" href="/wuye/css/admin.css">
    <script src="/wuye/js/jquery.js"></script>   
    <script src="/wuye/js/pintuer.js"></script>  
</head>
<body>
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 考核类型设置</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
        	<form method="post" action="/manager/addKhCate.aspx">
	        <div class="form-group">
	        <input type="hidden" id="categoryNo" name="categoryNo" value=0 />
	          <label style="float:left">考核类别名称：</label>
	          <input type="text" class="input"  id="categoryName" name="categoryName" data-validate="required:" style="float:left" />
	          
	          <label style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;业务设置：</label>
	          <select class="input w66" id="business" name="business" style="float:left" >
	              <!-- <option value="">请选择业务类型</option> -->
	              <option value="1">内业</option>
	              <option value="2">外业</option>
	            </select>
	            &nbsp;&nbsp;&nbsp;&nbsp;
	            <button type="submit"  class="button border-green"  style="float:right" id="addBtn"><span class="icon-check"></span> 确认添加</button>
	          </div>
	          <!-- <div class="form-group">
	          <label>类型序号：</label>
	          <input type="number" class="input" name="categoryNo" value="" data-validate="required:"  style="float:right" />
	        </div> -->
	        </form>
        </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">序号</th>
        <th>名称</th>
        <th>业务</th>       
        <th>操作</th>       
      </tr>     
      <c:forEach  varStatus="i" var="normCateBean" items="${list }" > 
	      <tr>
	          <td>${normCateBean.categoryNo }</td>
	          <td>${normCateBean.categoryName }</td>
	          <td>${normCateBean.businessName }</td>
	          <td>
	          	<div class="button-group">
	           		<a class="button border-red" href="javascript:void(0)" onclick="return update(${normCateBean.categoryNo },'${normCateBean.categoryName }',${normCateBean.business })"><span class="icon-trash-o"></span> 修改</a> 
	           		<a class="button border-red" href="javascript:void(0)" onclick="return del(${normCateBean.categoryNo })"><span class="icon-trash-o"></span> 删除</a>
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
  </div>
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
	location.href="/manager/toKhType.aspx?pageNum="+pageNum;
}

function del(id){
	if(confirm("您确定要删除吗?")){
		$.ajax({
			url : "/manager/delKhCate.aspx",
			data : {id:id},
			type : 'post',
			async : true,
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				alert(data.msg);
				location.href="/manager/toKhType.aspx";
			},
			error : function() {
				alert("请求异常！");
			}
		});
	}
}

function update(no,name,business){
	$("#categoryNo").val(no);
	$("#categoryName").val(name);
	$("#business").val(business);
	$("#addBtn").text("确认修改");
}

$("#checkall").click(function(){
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false; 		
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
}

</script>
</body></html>