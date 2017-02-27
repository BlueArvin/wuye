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
    <title>项目设置</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>   
    <script src="js/pintuer.js"></script>  
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 考核项目管理</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li style="width:280px">
        	<div class="form-group">
	          <label>考核类别名称：</label>
	          <select name="type" class="input w66"  style="float:right" >
	              <option value="">请选择考核类型</option>
	              <option value="">内业</option>
	              <option value="">外业</option>
	            </select>
	        </div>
	        <div class="form-group">
	          <label>考核项目序号：</label>
	          <input type="text" class="input" name="userName" style="float:right" />
            </div>
            <div class="form-group">
	          <label>考核项目内容：</label>
	          <input type="text" class="input" name="cn" value=""  style="float:right" />
	        </div>
	    </li>
	    <li style="width:380px">    
	        <div class="form-group">
	          <label style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;考核&nbsp;&nbsp;&nbsp;&nbsp;</label>
	          <table>
	          	<tr>
	          		<td><label style="float:left">重点：</label><input type="text" class="input w55" name="userName" /></td>
	          		<td><label style="float:left">一般：</label><input type="text" class="input w55" name="userName" /></td>
	          		<td><label style="float:left">普通：</label><input type="text" class="input w55" name="userName" /></td>
	          	</tr>
	          </table>
	        </div>
        </li>
        <li>
        <button type="button"  class="button border-green"  style="float:right" id="addkhLevel"><span class="icon-check"></span> 确认添加</button>
        </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
      <th>考核类别</th>
        <th width="120">序号</th>
        <th>项目内容</th>   
        <th>考核分值</th>       
        <th>操作</th>       
      </tr>      
        <tr>
        <td>环卫保洁</td>
          <td>1</td>
          <td>建筑物</td>
          <td>重点0.5 一般0.5 普通0.5</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr>
        <tr>
          <td>环卫保洁</td>
          <td>1</td>
          <td>建筑物</td>
          <td>重点0.5 一般0.5 普通0.5</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr>
        <tr>
          <td>环卫保洁</td>
          <td>1</td>
          <td>建筑物</td>
          <td>重点0.5 一般0.5 普通0.5</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr>
        <tr>
          <td>环卫保洁</td>
          <td>1</td>
          <td>建筑物</td>
          <td>重点0.5 一般0.5 普通0.5</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr>
        <tr>
          <td>环卫保洁</td>
          <td>1</td>
          <td>建筑物</td>
          <td>重点0.5 一般0.5 普通0.5</td>
          <td>
          	<div class="button-group">
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 修改</a> 
           		<a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
           	</div>
           </td>
        </tr>
        <tr>
          <td>环卫保洁</td>
          <td>1</td>
          <td>建筑物</td>
          <td>重点0.5 一般0.5 普通0.5</td>
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