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
    <title>区域设置</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>   
    <script src="js/pintuer.js"></script>  
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 城区设置</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
        	<div class="form-group">
	          <label>城区名称：</label>
	          <input type="text" class="input" name="userName" style="float:right" />
	        </div>
	    </li>
	    <li><button type="button"  class="button border-green"  style="float:right" id="addkhLevel"><span class="icon-check"></span> 确认添加</button></li>
	  </ul>
	</div> 
	<div class="panel-head"><strong class="icon-reorder"> 街道设置</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
        	<div class="form-group">
	          <label>城区名称：</label>
	          <select name="type" class="input w66"  style="float:right" >
	              <option value="">请选择城区</option>
	              <option value="">内业</option>
	              <option value="">外业</option>
	            </select>
	        </div>
	        <div class="form-group">
	          <label>街道名称：</label>
	          <input type="text" class="input" name="userName" style="float:right" />
	        </div>
	    </li>
	    <li><button type="button"  class="button border-green"  style="float:right" id="addkhLevel"><span class="icon-check"></span> 确认添加</button></li>
	  </ul>
	</div>
	<div class="panel-head"><strong class="icon-reorder"> 片区设置</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
        	<div class="form-group">
	          <label>街道名称：</label>
	          <select name="type" class="input w66"  style="float:right" >
	              <option value="">请选择街道</option>
	              <option value="">内业</option>
	              <option value="">外业</option>
	            </select>
	        </div>
	        <div class="form-group">
	          <label>片区名称：</label>
	          <input type="text" class="input" name="userName" style="float:right" />
	        </div>
	    </li>
	    <li><button type="button"  class="button border-green"  style="float:right" id="addkhLevel"><span class="icon-check"></span> 确认添加</button></li>
	  </ul>
	</div>
	<div class="panel-head"><strong class="icon-reorder"> 物业设置</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
        	<div class="form-group">
	          <label>物业名称：</label>
	          <input type="text" class="input" name="userName" style="float:right" />
	        </div>
	    </li>
	    <li><button type="button"  class="button border-green"  style="float:right" id="addkhLevel"><span class="icon-check"></span> 确认添加</button></li>
	  </ul>
	</div>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">序号</th>
        <th>考核名称</th>       
        <th>操作</th>       
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
        <td colspan="3"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

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