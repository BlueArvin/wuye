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
    <title>考核级别管理</title>  
    <link rel="stylesheet" href="/wuye/css/pintuer.css">
    <link rel="stylesheet" href="/wuye/css/admin.css">
    <script src="/wuye/js/jquery.js"></script>   
    <script src="/wuye/js/pintuer.js"></script>  
</head>
<body>
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 考核级别管理</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li style="widht:100%">
        <form action="/manager/addKhLevel.aspx" method="post" >
	        	<div class="form-group">
		        	<input type="hidden" id="levelNo" name="levelNo" value=0 />
		          	<label  style="float:left" >考核级别名称：</label>
		          	<input type="text" class="input" id="levelName" data-validate="required:" name="levelName" style="float:left" />
		          	&nbsp;&nbsp;&nbsp;&nbsp;
		          	<button type="submit"  class="button border-green"  style="float:right" id="addkhLevel"><span class="icon-check"></span> <span id="addBtn">确认添加</span></button>
	          	</div>
	          	<!-- <div class="form-group">
		          	<label>级别序号：</label>
		          	<input type="text" class="input" id="levelNo"  data-validate="required:" name="levelNo" value=""  style="float:right" />
	        	</div> -->
	        	<div class="form-group">
	        		
	        	</div>
	        </form>
        </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">序号</th>
        <th>考核名称</th>       
        <th>操作</th>       
      </tr>
      <c:forEach  varStatus="i" var="normLevelBean" items="${list }" > 
	      <tr>
	          <td>${normLevelBean.levelNo }</td>
	          <td>${normLevelBean.levelName }</td>
	          <td>
	          	<div class="button-group">
	           		<a class="button border-red" href="javascript:void(0)" onclick="return update(${normLevelBean.levelNo },'${normLevelBean.levelName }')"><span class="icon-trash-o"></span> 修改</a> 
	           		<a class="button border-red" href="javascript:void(0)" onclick="return del(${normLevelBean.levelNo })"><span class="icon-trash-o"></span> 删除</a>
	           	</div>
	           </td>
	        </tr>
      </c:forEach>
      <!-- 
        <tr>
          <td>1</td>
          <td>神夜</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr>
        <tr>
          <td>1</td>
          <td>神夜</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr>
        <tr>
          <td>1</td>
          <td>神夜</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr>
        <tr>
          <td>1</td>
          <td>神夜</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr>
        <tr>
          <td>1</td>
          <td>神夜</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr>
        <tr>
          <td>1</td>
          <td>神夜</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return update(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr> -->
      <!-- <tr>
        <td colspan="3"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>
      </tr> -->
    </table>
  </div>
<script type="text/javascript">
$(function(){
	
	var retMsg="${msg }";
	if(retMsg!=null&&retMsg!=""){
		alert(retMsg);
	}
})

function del(id){
	if(confirm("您确定要删除吗?")){
		$.ajax({
			url : "/manager/delKhLevel.aspx",
			data : {id:id},
			type : 'post',
			async : true,
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if(data.ret==0){
					alert(data.msg);
					location.href="/manager/toKhLevel.aspx";
				}
			},
			error : function() {
				alert("请求异常！");
			}
		});
	}
}

function update(no,name){
	$("#levelNo").val(no);
	$("#levelName").val(name);
	
	$("#addBtn").text("确认修改");
}

/* $("#addkhLevel").on("click",function(){
	var id = $("#id").val();
	var levelNo = $("#levelNo").val().trim();
	var levelName = $("#levelName").val().trim();
	
	if(levelNo==""){
	}
	
	if(levelName==""){
	}
	
}) */

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