<%-- 
    Document   : dashboard
    Created on : Jan 21, 2016, 1:51:25 PM
    Author     : omarchia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Usuario</title>
        <link href="css/surveys.css" rel="stylesheet" type="text/css"/>
        <link href="css/ui-lightness/jqueryui.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="js/apariencia/jquery.js"></script>
        <script type="text/javascript" src="js/apariencia/ddaccordion.js"></script>
        <script type="text/javascript" src="js/programacion/register.js"></script>
    </head>
    <body>
       <div id="container"> 
            <div id="header">
                <div id="logo"><a href="principal.php"></a></div>
                <div id="panel" style="left: 298px;">Usuario Nuevo</div>
            </div>
            <div id="main">
                <div class="right" style="width:826px; margin:0 auto; float:none;"> 
                    <div id="content"> 
                        <h1>Registro</h1>
                        <form name="formAddUser" method="post" action="actions/add_user.php" onSubmit="return valida(this);" class="formulario" enctype="multipart/form-data">	
                                <label class="label">Nombre Completo: <span class="nota"></span></label>
                                <input name="name" type="text" id="name" maxlength="200" class="input">

                                <label class="label">Email: <span class="nota"></span></label>
                                <input name="email" type="text" id="email" maxlength="200" class="input">

                                <label class="label">Password: <span class="nota"></span></label>
                                <input name="password" type="text" id="password" maxlength="200" class="input">   

                                <input type="button" name="btnCancel" id="btnCancel" class="btn-cancelar" value="CANCELAR" onClick="window.open('','_self');">
                                <input type="submit" name="btnSubmit" id="btnSubmit" class="btn-aceptar" value="GUARDAR">
                                <div class="clear">&nbsp;</div>
                        </form>  

                    </div>
                    <div id="footer"><strong>ochialvarado.com</strong> | Email: info@encuestas.com | 2016 </div>
                </div>
                <div class="clear">&nbsp;</div>
            </div>
        </div>
    </body>
</html>
