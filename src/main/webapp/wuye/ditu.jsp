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
<title>问题地图</title>
<style type="text/css">
	body, html,#container {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>
<link rel="stylesheet" href="/wuye/css/pintuer.css">
<link rel="stylesheet" href="/wuye/css/admin.css">
<script src="/wuye/js/jquery.js"></script>
<script src="/wuye/js/pintuer.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=PqqHAjd2pR9Xlu0Q1ppiTGM6nLWGqpRG" ></script>
</head>
<body>
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder"> 问题地图</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search">
					<li>
						<div style="float: left">
							<label style="float: left">街道名称：</label>
							<select name="streetid" id="streetid" class="input w66"
								style="float: left">
								<option value=0>请选择城区</option>
								<c:forEach varStatus="i" var="areaBean" items="${areaList }">
									<option value=${areaBean.id }>${areaBean.name }</option>
								</c:forEach>
							</select>
							<label style="float: left">&nbsp;&nbsp;&nbsp;&nbsp;片区名称：</label> <select name="pianquid"
								id="pianquid" class="input w66" style="float: left">
								<option value=0>请选择街道</option>
							</select> 
						</div>
					</li>
					<li style="padding-top: 5px">
						<div style="float: left">
							<label style="float: left">检查日期：</label> <input type="date"
								class="input w66" id="time" name="time" style="float: left" />
						&nbsp;&nbsp;&nbsp;
							<button id="queryBtn" type="button" class="button border-green">
								<span class="icon-check"></span> 查询
							</button>
						</div>
					</li>
				</ul>
			</div>
		</div>
		
		<c:forEach varStatus="i" var="cateBean" items="${cateList }">
			<span><image style="height:30px" src="/wuye/images/map/${cateBean.categoryNo%9 }.png" />${cateBean.categoryName }&nbsp;&nbsp;&nbsp;&nbsp;</span>
		</c:forEach>
		<div id="container"></div>
</body>
</html>
<script type="text/javascript">
var map;
$(document).ready(function(){
	map = new BMap.Map("container");          // 创建地图实例  
	var point = new BMap.Point(116.404, 39.915);  // 创建点坐标  
	map.centerAndZoom(point, 13);                 // 初始化地图，设置中心点坐标和地图级别  
	map.enableDragging();
	map.enableScrollWheelZoom();
	map.enableDoubleClickZoom();
});

$(function(){
	
	$("#streetid").change(function(){
		var areaId = $(this).val();
		$("#pianquid").val(0);
		$("#pianquid option:not(:first)").remove();
		if(areaId==0){
			return;
		}
		$.ajax({
			url : "/manager/queryPianqu.aspx",
			data : {parentId:areaId},
			type : 'post',
			async : true,
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if(data.ret==0){
					var options = "";
					$.each(data.data, function (index, info) {
						var id = info.id;
						var name = info.name;
						options+="<option value="+id+">"+name+"</option>";
					 })
					$("#pianquid").append(options);
				}
			},
			error : function() {
				alert("请求异常！");
			}
		});
	});
	
	$("#queryBtn").click(function(){
		
		var street = $("#streetid").val();
		var pianqu = $("#pianquid").val();
		var time = $("#time").val();
		
		if(street==0||pianqu==0||time==""){
			alert("请选择查看条件！！");
			return;
		}
		var url = "/api/Assess/point?street="+street+"&pianqu="+pianqu+"&time="+time;
		$.ajax({
			url : url,
			data : null,
			type : 'get',
			async : true,
			cache : false,
			dataType : 'html',
			success : function(data) {
				// console.log(data);
				// if(data.ret==0){
				// 	alert(data.msg);
				// 	location.href="/manager/toKhLevel.aspx";
				// }
				//clearAll();
				map.clearOverlays();    //清除地图上所有覆盖物
				display(data);
				// alert(data);
			},
			error : function() {
				alert("请求异常！");
			}
		});
	})

	function display(data) {
	// data = "[\"116.304,39.915\",\"116.404,39.915\"]";
		var obj = eval("("+data+")");
		console.log(data);
		for(var key in obj){ //第一层循环取到各个list 
			var List = obj[key]; 
			if(List==null){
				continue;
			}
			var s = List.split(",");
			dot(s[0], s[1], s[2]);
		}
	}

	function dot(v1, v2, iconId) {
		var point = new window.BMap.Point(v1, v2); //循环生成新的地图点
		var icon = "/wuye/images/map/"+iconId+".png";
		var myIcon = new BMap.Icon(icon,
				new BMap.Size(39, 56), {
			       // 指定定位位置。     
			       // 当标注显示在地图上时，其所指向的地理位置距离图标左上      
			       // 角各偏移7像素和25像素。您可以看到在本例中该位置即是     
			       // 图标中央下端的尖角位置。      
			       anchor: new BMap.Size(19.5, 58),        
			      }); 
		
		var marker = new BMap.Marker(point,{icon: myIcon});  // (point, {icon: myIcon})
		map.addOverlay(marker);
	}
	
	function clearAll() {
        //for (var i = 0; i < map.getOverlays().length; i++) {
        //    map.removeOverlay(map.getOverlays()[i]);
        //}
        //overlays.length = 0; 
        for (var i = 0; i < map.getOverlays().length; i++) {
            if (map.getOverlays()[i].GO == "Marker")
                map.removeOverlay(map.getOverlays()[i]);
        }
        overlays.length = 0; 
    }
	
	
	function initTime(){
		var ddd = new Date();
		var day =ddd.getDate();

		if(ddd.getMonth()<10){
		var month = "0"+(ddd.getMonth()+1); 
		}

		if(ddd.getDate()<10){
		 day = "0"+ddd.getDate(); 
		}

		var datew = ddd.getFullYear()+"-"+month+"-"+day;
		var datew = datew.toString();
		$("#time").val(datew);
	}
	initTime()
})
</script>