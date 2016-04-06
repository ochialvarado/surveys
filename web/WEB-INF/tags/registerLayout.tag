<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:directive.attribute name="title" required="true" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Surveys | <c:out value="${title}" escapeXml="true" /></title>
        <link href="css/surveys.css" rel="stylesheet" type="text/css"/>
        <link href="css/ui-lightness/jqueryui.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="js/apariencia/jquery.js"></script>
        <script type="text/javascript" src="js/apariencia/ddaccordion.js"></script>
    </head>
    <body>
       <div id="container"> 
            <div id="header"> 
                <div id="logo"><a href="dashboard.jsp"></a></div>
                <div id="panel">Sistema de encuestas</div>
                <div class="btn-salir"><a href="/surveys/UserController?action=logout   ">CERRAR SESIÓN</a></div>
            </div>
            <div id="main">
                <div class="right" style="width:100%;"> 
                    <div id="content"> 
                        <jsp:doBody />
                    </div>
                    <div id="footer"><strong>ochialvarado.com</strong> | Email: info@encuestas.com | 2016 </div>
                </div>
                <div class="clear"> </div>
            </div>
        </div>

    </body>
</html>
