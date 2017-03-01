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
    <title>用户管理</title>  
    <link rel="stylesheet" href="/wuye/css/pintuer.css">
    <link rel="stylesheet" href="/wuye/css/admin.css">
    <script src="/wuye/js/jquery.js"></script>   
    <script src="/wuye/js/pintuer.js"></script>  
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 用户管理</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
	        <div style="float:left">
	          <label  style="float:left" >用户名：</label>
	          <input type="text" class="input" name="userName" value="" style="float:left" />
	          
	          <label  style="float:left" >&nbsp;&nbsp;&nbsp;&nbsp;账号：</label>
	          <input type="text" class="input" name="cn" value=""  style="float:left" />&nbsp;&nbsp;&nbsp;&nbsp;
	          <button type="button"  class="button border-green" onclick="queryList()" id="queryUser"><span class="icon-check"></span> 查询</button>
	        </div>
        </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">序号</th>
        <th>用户名</th>       
        <th>账号</th>
        <th>app权限</th>
        <th>web权限</th>
        <th>操作</th>       
      </tr>
      <c:forEach  varStatus="i" var="userBean" items="${list }" > 
	      <tr>
	          <td>${(page.page-1)*page.pageSize +i.index } </td>
	          <td>${userBean.userName }</td>
	          <td>${userBean.cn }</td>
	          <td>${userBean.roleName }</td>
	          <td>${userBean.webRoleName }</td>
	          <td>
	          	<div class="button-group">
	           		<a class="button border-red" href="javascript:void(0)" onclick="return toUpdateUser(${userBean.id })"><!-- <span class="icon-trash-o"></span> --> 修改</a>
	           		 
	           		 <c:if test="${userBean.status == 1}" >  
	           			<a class="button border-green" href="javascript:void(0)" id="${userBean.id }" status="0" name="statusBtn" ><!-- <span class="icon-trash-o"></span> --> 启用</a>
	           		 </c:if>
	           		 
	           		 <c:if test="${userBean.status == 0}" >
	           		 	<a class="button border-red" href="javascript:void(0)" id="${userBean.id }" status="1" name="statusBtn" ><!-- <span class="icon-trash-o"></span> --> 停用</a>  
	           		 </c:if>
	           	</div>
	           </td>
	        </tr>
      </c:forEach>
      
      <tr>
        <td colspan="6">
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
	
	$("a[name='statusBtn']").bind("click",function(){
		var scope = $(this);
		
		var status = scope.attr("status");
		var id = scope.attr("id");
		
		var className = "button border-green";
		var operName = "停用";
		if(status==0){
			className="button border-red";
			operName = "启用";
		}
		
		if(confirm("您确定要"+operName+"吗?")){
			$.ajax({
				url : "/manager/updateUser.aspx",
				data : {id:id,status:status},
				type : 'post',
				async : true,
				cache : false,
				dataType : 'json',
				success : function(data) {
					console.log(data);
					if(data.ret==0){
						scope.removeClass().addClass(className);
						scope.attr("status",status^1);
						scope.text(status==0?"停用":"启用");
						alert(operName+"成功");
					}else if(data.ret==-1){
						alert("操作失败请重新尝试");
					}
				},
				error : function() {
					alert("请求异常！");
				}
			});
		}
	})
	
})

function toUpdateUser(id){
	location.href="/manager/toUpdateUser.aspx?id="+id;
}

function queryList(){
	
	var userName = $("input[name='userName']").val();
	var cn = $("input[name='cn']").val();
	
	var url = "/manager/userList.aspx?pageNum=1"
	if(userName.trim() != ""){
		url+="&userName="+userName.trim();
	}
	
	if(cn.trim() != ""){
		url+="&cn="+cn.trim();
	}
	location.href=url;
}


function load(pageNum){
	var count = ${page.countPage };
	var now = ${page.page };
	if(pageNum<=0||pageNum>count){
		return;
	}
	location.href="/manager/userList.aspx?pageNum="+pageNum;
}

function del(id){
	if(confirm("您确定要删除吗?")){
		
	}
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