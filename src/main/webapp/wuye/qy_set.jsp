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
    <link rel="stylesheet" href="/wuye/css/pintuer.css">
    <link rel="stylesheet" href="/wuye/css/admin.css">
    <script src="/wuye/js/jquery.js"></script>   
    <script src="/wuye/js/pintuer.js"></script>  
</head>
<body>
  <div class="panel admin-panel">
    <div class="panel-head" onclick="showDiv('stateDiv')"><strong class="icon-reorder"> 城区设置</strong></div>
    <div id="stateDiv" name="form" class="padding border-bottom">
    	<iframe scrolling="auto" rameborder="0" presrc="/manager/toAreaSetChild.aspx?pageNum=1&type=1" src="/manager/toAreaSetChild.aspx?pageNum=1&type=1" name="right" width="100%" id="info1" onload="changeFrameHeight('info1')" ></iframe>
    </div>
	<div class="panel-head" onclick="showDiv('streeDiv')"><strong class="icon-reorder"> 街道设置</strong></div>
    <div id="streeDiv" name="form" class="padding border-bottom hidden">
    	<iframe scrolling="auto" rameborder="0" presrc="/manager/toAreaSetChild.aspx?pageNum=1&type=2" name="right" width="100%" id="info2" onload="changeFrameHeight('info2')" ></iframe>
	</div>
	<div class="panel-head" onclick="showDiv('pianquDiv')"><strong class="icon-reorder"> 片区设置</strong></div>
    <div id="pianquDiv" name="form" class="padding border-bottom hidden">
    	<iframe scrolling="auto" rameborder="0" presrc="/manager/toAreaSetChild.aspx?pageNum=1&type=3" name="right" width="100%" id="info3" onload="changeFrameHeight('info3')" ></iframe>
	</div>
	<div class="panel-head" onclick="showDiv('hutongDiv')"><strong class="icon-reorder"> 胡同设置</strong></div>
    <div id="hutongDiv" name="form" class="padding border-bottom hidden">
	    <iframe scrolling="auto" rameborder="0" presrc="/manager/toAreaSetChild.aspx?pageNum=1&type=4" name="right" width="100%" id="info4" onload="changeFrameHeight('info4')" ></iframe>
	</div>
	<div class="panel-head" onclick="showDiv('companyDiv')"><strong class="icon-reorder"> 物业设置</strong></div>
    <div id="companyDiv" name="form" class="padding border-bottom hidden">
    	<iframe scrolling="auto" rameborder="0" presrc="/manager/toAreaSetChild.aspx?pageNum=1&type=5" name="right" width="100%" id="info5" onload="changeFrameHeight('info5')" ></iframe>
	</div>
  </div>
<script type="text/javascript">

function changeFrameHeight(id){
    var ifm= $("#"+id);
    var height = ifm.contents().find("body").height();
    ifm.attr("height",height);
}


function showDiv(showDivId){
	$("div[name='form']").hide();
	var src = $("#"+showDivId+" iframe").attr("presrc");
	$("#"+showDivId+" iframe").attr("src",src);
	$("#"+showDivId).removeClass("hidden").show();
}

</script>
</body></html>