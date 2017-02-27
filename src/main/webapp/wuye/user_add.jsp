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
    <title>添加用户</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>  
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 添加用户</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">
      <div class="form-group">
        <div class="label">
          <label>账户：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="cn" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>用户名：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="userName" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>初始密码：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="passwd" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>app权限分配：</label>
        </div>
        <div class="field" style="padding-top:8px;">
            <input id="xmcheck"  type="checkbox" />项目检查&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="jlquery"  type="checkbox" />记录查询&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>web权限分配：</label>
        </div>
        <div class="field" style="padding-top:8px;">
                  <input id="yhgl"  type="checkbox" />用户管理&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="bzsz"  type="checkbox" />标准设置&nbsp;&nbsp;&nbsp;&nbsp;
                     <input id="khxg"  type="checkbox" />考核修改&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="sjfx"  type="checkbox" />数据分析&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 确认添加</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body></html>