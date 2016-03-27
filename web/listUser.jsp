<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
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

<script language="javascript" type="text/javascript">
    $(document).ready(function(){
            $("#validacion").show();
            setTimeout('$("#validacion").hide()',2000);
    });
</script>


<h1>Usuarios</h1>
<div id="validacion" style="display:none">Los cambios se realizaron corectamente.</div> 
<div class="btn-agregar"><a style="margin-right:40px;"  href="/user.jsp">Agregar pregunta</a></div> 

    <table>
	<tr>
		<th width="25%" class="center">Name</th>
                <th width="25%" class="center">Email</th>
		<th width="25%">Fecha de Creaci&oacute;n</th>
		<th colspan="4" align="center">Acciones</th> 
	</tr>
	<c:forEach items="${users}" var="User">  
            <tr>
                <td>${User.name}</td>   
                <td>${User.email}</td> 
                <td>${User.startDate}</td> 
                <td class="center"><a href="#?id=${User.userId}"><img src="img/img_editar.png" alt="EDITAR"></a></td>
                <td class="center"><a href="#" onclick="if(confirm('Realmente desea eliminar el usuario')){window.open('#?id=${User.userId}','_self');}"><img src="img/img_eliminar.png" alt="ELIMINAR"></a></td>
            </tr>
        </c:forEach> 
    </table>
</body>
</html>
