<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Gracias!</title>
<style>
/*GENERALES*/
body {
	font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
	color: #484848;
	margin: 0px;
	padding: 0px;
	background-color: #F2F2F2;
	background-image: url(img/bg_body.png);
	background-repeat: repeat-x;
	font-size: 12px;
}
p, form, ul, h1, h2, h3 {
	margin: 0px;
	padding: 0px;
}
object {
	outline:none;
}
img {
	border:none;
}
a {
	text-decoration:none;
	outline:none;
	cursor:pointer;
}
a:hover {
	text-decoration:none;
}
.clear {
	clear:both;
	height: 0px;
	font-size: 0px;
	line-height: 0px;
}
/*ESTRUCTURA*/
#container {
	width:500px;
	margin-left:auto;
	margin-right:auto;
}
#header {
	height:125px;
	margin-bottom: 10px;
}
#main {
	margin-top:25px;
}
#footer {
	height:50px;
	line-height:49px;
	font-size: 11px;
	color: #999;
	text-align:center;
}
/*CAJAS*/
#logo a {
	width:200px;
	height:120px;
	display:block;
	margin-right: auto;
	margin-left: auto;
	margin-top: 5px;
}
#index {
	width:500px;
	height:75px;
	
}
#login {
	width:400px;
	margin-left:auto;
	margin-right:auto;
	background-color:#FFF;
	border-top:1px solid #CCC;
	border-bottom:1px solid #CCC;
	padding-top:35px;
	padding-bottom:35px;
	position:relative;
}
/*FORMULARIO*/
.formulario {
	width:260px;
	margin-right:auto;
	margin-left:auto;
}
.formulario .label {
	float:left;
	display:block;
	width:85px;
	height:25px;
	line-height:24px;
	color: #666;
	font-size: 12px;
	font-weight: bold;
	text-align:right;
	padding-right:15px;
	margin-bottom:10px;
}
.formulario .input {
	float:left;
	width: 150px;
	height: 22px;
	margin-bottom:15px;
	font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #999;
	padding-top: 3px;
	border: 1px solid #999;
}
.formulario .btn-entrar {
	width:90px;
	height:30px;
	cursor:pointer;
	outline:none;
	border:1px solid #666;
	float: right;
	margin-left: 15px;
	padding-bottom: 3px;
	background-color: #333;
	margin-right: 10px;
	font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #FFF;
	font-weight: bolder;
	background-image: url(img/btn_entrar.png);
	background-repeat: repeat-x;
}
.formulario .btn-entrar:hover {
	background-position:bottom;
}
.validacion {
	text-align: center;
	color:#FFF;
	font-weight: bold;
	background-color:#C30;
	padding:10px;
	margin-top:10px;
	margin-left:10px;
	margin-right:10px;
	margin-bottom:25px;
	background-image: url(img/bg_validacion.png);
	background-repeat: repeat-x;
	border:1px solid #000;
}
#register{
	bottom: 10px;
    color: #666666;
    cursor: pointer;
    font-size: 16px;
    left: 46px;
    position: absolute;
    text-decoration: underline;
}
</style>
</head>
<body>
<div id="container">
	<div id="header">
		<div id="logo"><a href="index.jsp"></a></div>
		<div id="panel"></div>
	</div>
	<div id="index"></div>
	<div id="main">
		<div id="login">
			<form name="formLogin" class="formulario" method="post" action="login.php">
				Gracias por contestar nuestra encuesta!
				<div class="clear">&nbsp;</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>