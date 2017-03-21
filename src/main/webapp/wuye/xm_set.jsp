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
    <link rel="stylesheet" href="/wuye/css/pintuer.css">
    <link rel="stylesheet" href="/wuye/css/admin.css">
    <script src="/wuye/js/jquery.js"></script>   
    <script src="/wuye/js/pintuer.js"></script>  
</head>
		
<body>
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 考核项目管理</strong></div>
    <div class="padding border-bottom">
    <form method="post" action="/manager/addKhItem.aspx">
      <ul class="search">
        <li style="width:280px">
        	<div class="form-group">
        	<input type="hidden" id="itemNo" name="itemNo" value=0 />
	          	<label>考核类别名称：</label>
	          	<select id="categoryNo" name="categoryNo" class="input w66"  style="float:right" >
	          		<c:forEach  varStatus="i" var="normCateBean" items="${cateList }" >
	          			<option value="${normCateBean.categoryNo }">${normCateBean.categoryName }</option>
	          		</c:forEach>
	          	<!-- 
	              <option value="1">内业</option>
	              <option value="2">外业</option> -->
	            </select>
	        </div>
	        <!-- <div class="form-group">
	          <label>考核项目序号：</label>
	          <input type="text" class="input" name="userName" style="float:right" />
            </div> -->
            <div class="form-group">
	          <label>考核项目内容：</label>
	          <input type="textarea" class="input" id="itemContent" name="itemContent" data-validate="required:"  value=""  style="float:right" />
	        </div>
	        <div class="form-group">
	          <label>项目总分：</label>
	          <input type="number" class="input" id="scoreCount" name="scoreCount" data-validate="required:"   step="0.1" min="0" value=0 style="float:right" />
	        </div>
	    </li>
	    <li style="width:100%;padding-top:8px">
	        <div class="form-group">
	          <label style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;考核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	          <table>
	          	<tr>
	          	<c:forEach  varStatus="i" var="normLevelBean" items="${levelList }" >
	          		<td><label style="float:left">${normLevelBean.levelName }：</label>
	          		<input type="number" class="input w55" data-validate="required:"  step="0.1" min="0" value=0 name="scoreValue" levelId="${normLevelBean.levelNo }" />
	          		<input type="hidden" class="input w55" name="scoreId" value="${normLevelBean.levelNo }" /></td>
	          	</c:forEach>
	          	</tr>
	          </table>
	        </div>
        </li>
        <li>
        <button type="submit" class="button border-green"  style="float:right" id="addBtn"><span class="icon-check"></span> 确认添加</button>
        </li>
      </ul>
      </form>
    </div>
    <table class="table table-hover text-center">
      <tr>
      <th>考核类别</th>
        <th width="120">序号</th>
        <th>项目内容</th>   
        <th>考核分值</th>
        <th>项目总分</th>          
        <th>操作</th>       
      </tr>
      
      <c:forEach  varStatus="i" var="normItemBean" items="${list }" > 
	      <tr>
	      	  <td>${normItemBean.categoryName }</td>
	          <td>${normItemBean.itemNo }</td>
	          <td>${normItemBean.itemContent }</td>
	          <td>${normItemBean.scoreName }</td>
	          <td>${normItemBean.scoreCount }</td>
	          <c:forEach  varStatus="z" var="normScoreBean" items="${normItemBean.scoreList }" > 
					<span style="display:none" name="score_${normItemBean.itemNo }" levelId="${normScoreBean.levelNo }" />${normScoreBean.score }</span>
	          </c:forEach>
	          
	          <td>
	          	<div class="button-group">
	           		<a class="button border-red" href="javascript:void(0)" onclick="return update(${normItemBean.itemNo },'${normItemBean.itemContent }','${normItemBean.categoryNo }',${normItemBean.scoreCount })"><span class="icon-trash-o"></span> 修改</a> 
	           		<a class="button border-red" href="javascript:void(0)" onclick="return del(${normItemBean.itemNo })"><span class="icon-trash-o"></span> 删除</a>
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
	location.href="/manager/toItemSet.aspx?pageNum="+pageNum;
}

function update(no,content,categoryNo,scoreCount){
	alert(no);
	$("#itemNo").val(no);
	$("#categoryNo").val(categoryNo);
	$("#itemContent").val(content);
	$("#scoreCount").val(scoreCount);
	$("#addBtn").text("确认修改");
	var levels = $("span[name='score_"+no+"']");
	for(i=0;i<levels.length;i++){
		var levelid = $(levels[i]).attr("levelId");
		var levelvalue = $(levels[i]).text();
		$("input[levelId='"+levelid+"']").val(Number(levelvalue));
	}

}

function del(id){
	if(confirm("您确定要删除吗?")){
		$.ajax({
			url : "/manager/delKhItem.aspx",
			data : {id:id},
			type : 'post',
			async : true,
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				alert(data.msg);
				location.href="/manager/toItemSet.aspx";
			},
			error : function() {
				alert("请求异常！");
			}
		});
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