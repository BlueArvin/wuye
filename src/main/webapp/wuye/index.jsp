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
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="/wuye/css/pintuer.css">
    <link rel="stylesheet" href="/wuye/css/admin.css">
    <script src="/wuye/js/jquery.js"></script>   
</head>

<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="/wuye/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="/wuye/index.jsp" target="_self">
  <span class="icon-home"></span> 返回首页</a> &nbsp;&nbsp;
  <a href="/manager/toUpdatePw.aspx" target="right" class="button button-little bg-blue"><span class="icon-wrench"></span> 修改密码</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="/manager/logout.aspx"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  
  
  
  
  <c:forEach var="menu" items="${menuList }" > 
  	<c:choose>
	    <c:when test="${menu.menuCode == '100000'}"><!-- 如果 -->  
	        <h2><span class="icon-user"></span>${menu.menuName } </h2>
	    </c:when>
    	<c:otherwise>
    		<h2><span class="icon-pencil-square-o""></span>${menu.menuName } </h2>
    	</c:otherwise>
	</c:choose>
  	<ul>
	  	<c:forEach var="menuChild" items="${menu.child }" > 
	  		<li><a href="${menuChild.url }" target="right"><span class="icon-caret-right"></span>${menuChild.menuName }</a></li>
	  	</c:forEach>
  	</ul>
  </c:forEach>
  <!-- 
  <h2><span class="icon-user"></span>用户管理</h2>
  <ul>
    <li><a href="user_add.jsp" target="right"><span class="icon-caret-right"></span>用户添加</a></li>
    <li><a href="user_list.jsp" target="right"><span class="icon-caret-right"></span>用户管理</a></li>
  </ul>
  <h2><span class="icon-pencil-square-o"></span>标准设置</h2>
  <ul>
    <li><a href="kh_level.jsp" target="right"><span class="icon-caret-right"></span>考核级别</a></li>
    <li><a href="kh_type.jsp" target="right"><span class="icon-caret-right"></span>考核类别</a></li>
    <li><a href="xm_set.jsp" target="right"><span class="icon-caret-right"></span>项目设置</a></li>  
    <li><a href="qy_set.jsp" target="right"><span class="icon-caret-right"></span>区域设置</a></li>   
  </ul>
  <h2><span class="icon-pencil-square-o"></span>考评修改</h2>
  <ul>
  <li><a href="question.jsp" target="right"><span class="icon-caret-right"></span>问题修改</a></li>
  </ul>
  <h2><span class="icon-pencil-square-o"></span>数据分析</h2>
  <ul>
  <li><a href="add.html" target="right"><span class="icon-caret-right"></span>问题地图</a></li>
  <li><a href="cate.html" target="right"><span class="icon-caret-right"></span>报表导出</a></li> 
  </ul> -->
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="main.jsp" target="right" class="icon-home"> 首页</a></li>
  <li><a href="javascript:void(0)" id="a_leader_txt">网站信息</a></li>
  <li><b>当前语言：</b><span style="color:red;">中文</span></li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="/wuye/main.jsp" name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>