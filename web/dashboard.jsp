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
        <title>Surveys Dashboard</title>
        <link href="css/surveys.css" rel="stylesheet" type="text/css"/>
        <link href="css/ui-lightness/jqueryui.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="js/apariencia/jquery.js"></script>
        <script type="text/javascript" src="js/apariencia/ddaccordion.js"></script>
    </head>
    <body>
       <div id="container"> 
            <div id="header"> 
                <div id="logo"><a href="principal.xhtml"></a></div>
                <div id="panel">Sistema de encuestas</div>
                <div class="btn-salir"><a href="logout.jsp">CERRAR SESIÓN</a></div>
            </div>
            <div id="main">
                <div class="left">
                    <div id="menu">
                        <div class="head">MENU</div>
                        <ul> 
                            <li><a href="createUser.jsp">>Mi Perfil</a></li>
                            <li><a href="surveys.xhtml">Mis Encuestas</a></li> 
                        </ul>
                    </div>
                </div> 
                <div class="right"> 
                    <div id="content"> 
                        <h1>Bienvenido <%= request.getParameter("usuario") %></h1>
                        <p>En las diferentes áreas podrá añadir, editar y eliminar sus encuestas.</p>
                        <div id="principal">
                                <div class="clear"> </div>
                        </div>
                    </div>
                    <div id="footer"><strong>ochialvarado.com</strong> | Email: info@encuestas.com | 2016 </div>
                </div>
                <div class="clear"> </div>
            </div>
        </div>

    </body>
</html>
