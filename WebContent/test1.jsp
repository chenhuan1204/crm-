<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body >
    <h2 class="titlelog"><br></h2><h2 class="titlelog"><br></h2><h2 class="titlelog">学乐购</h2>
    <p class="advertisement">专门为学生打造的网站</p>
    <div id="panelname" style="margin-left: 500px;width: 800px;">
    	<div id="registername"><p class="userregister">用户注册</p></div>
    	<form action="register_add.action" οnsubmit="return clickregister()">
		<div style="margin-bottom:20px">
			<div>手机号码:</div>
			<input class="easyui-textbox" id="telephonename" name="loginName" data-options="prompt:'请输入手机号',validType:'mobile'" style="width: 300px; height: 30px;"">
			<input class="code" type="button" id="btnSendCode" οnclick="sendMessage()" value="点击获取手机验证码" />
			<span id="telephonenameTip"></span>
		</div>
		<div style="margin-bottom:20px">
			<div>验证码:</div>
			<input class="easyui-textbox" id="codename" style="width: 300px; height: 30px;">
			<span id="codenameTip"></span>
		</div>
		<div style="margin-bottom:20px">
			<div>密码:</div>
			<input class="easyui-textbox" id="password" name="loginPassword" style="width: 300px; height: 30px;">
			<span id="passwordTip"></span>
		</div>
		<div style="margin-bottom:20px">
			<div>确认密码:</div>
			<input class="easyui-textbox" id="passwordRepeat" style="width: 300px; height: 30px;"  >
			<span id="passwordRepeatTip"></span>
		</div>
		
		<div class="readname">
			<input id="checked" type="checkbox" οnclick="arrgement()" checked="checked">
			<span >我已阅读并同意<span class="servicename" id="aree">《学乐购网站服务协议》</span></span>
		</div>
		<div id="zhuce" class="loginname">
				<input class="loginregister" id="loginregister" type="submit" value="注册 "  />
				<a class="login">已有账号？去登陆</a>
		</div>
		</form>
	</div>
  </body>
</html>