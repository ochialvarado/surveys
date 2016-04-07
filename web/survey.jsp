<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:mainLayout title="Registro">
    <h1>Crear Encuesta</h1>
    <h2>AGREGAR</h2>
    <div id="validacion" style="display:none"></div>
    <form name="formAddUser" method="POST" action="/surveys/SurveyController" onSubmit="return valida(this);" class="formulario">
        <label class="label">T&iacute;tulo de encuesta: <span class="nota"></span></label>
            <input name="title" type="text" id="title" maxlength="1000" class="input" value="${Survey.title}">
            

            <label class="label">Descripci&oacute;n: <span class="nota"></span></label>
            <textarea  rows="5" cols="121" name="description" id="description">${Survey.description}</textarea>  
            <input type="hidden" name="action" value="addSurvey"/>
            
            <c:choose>
                <c:when test="${Survey.surveyId > 0}">
                    <input type="hidden" name="surveyId" value="${Survey.surveyId}"/>
                </c:when>    
                <c:otherwise> 
                    
                </c:otherwise>
            </c:choose>
            
            <div class="divisor">&nbsp;</div>

            <input type="button" name="btnCancel" id="btnCancel" class="btn-cancelar" value="CANCELAR" onClick="window.open('/surveys/SurveyController?action=listSurvey','_self');">
            <input type="submit" name="btnSubmit" id="btnSubmit" class="btn-aceptar" value="GUARDAR">
            <div class="clear">&nbsp;</div>
    </form> 
    <script type="text/javascript" src="js/programacion/surveys.js"></script>
</custom:mainLayout>