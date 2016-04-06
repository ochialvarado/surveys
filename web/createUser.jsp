<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:registerLayout title="Registro">
    <h1>Registro</h1>
    <div id="validacion" style="display: none;"></div>
    <form name="formAddUser" method="post" action="/surveys/UserController" onSubmit="return valida(this);" class="formulario">	
            <label class="label">Nombre Completo: <span class="nota"></span></label>
            <input name="name" type="text" id="name" maxlength="200" class="input">

            <label class="label">Email: <span class="nota"></span></label>
            <input name="email" type="text" id="email" maxlength="200" class="input">

            <label class="label">Password: <span class="nota"></span></label>
            <input name="password" type="text" id="password" maxlength="200" class="input">   
            <input type="hidden" name="action" value="registerUser"/>
          

            <input type="button" name="btnCancel" id="btnCancel" class="btn-cancelar" value="CANCELAR" onClick="window.open('index.jsp','_self');">
            <input type="submit" name="btnSubmit" id="btnSubmit" class="btn-aceptar" value="GUARDAR">
            <div class="clear">&nbsp;</div>
    </form>  
    <script type="text/javascript" src="js/programacion/register.js"></script>
</custom:registerLayout>