<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>报表导出</title>
<link rel="stylesheet" href="/wuye/css/pintuer.css">
<link rel="stylesheet" href="/wuye/css/admin.css">
<script src="/wuye/js/jquery.js"></script>
<script src="/wuye/js/pintuer.js"></script>
<script type="text/javascript">
</script>
<style>
li {line-height:35px;}
</style>
</head>
<body>
    <div class="panel-head"><strong class="icon-reorder"> 文件下载：</strong></div>
<div id="filesDic" style="height:500px;width:50%;overflow-y:auto;border:0.5px solid ">
	<ul>
		<c:forEach varStatus="i" var="fileBean" items="${fileList }">
		<c:if test="${fileBean.type==1}">
			<li><a href="/${fileBean.name }" target="_blank">${fileBean.name }</a></li> 
		</c:if>
	</c:forEach>
</ul>
</div>
</body>
</html>