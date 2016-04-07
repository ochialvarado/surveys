<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:mainLayout title="Registro">
    <h2>Por razones estad&iacute;sticas primero debe responder estas tres preguntas: </h2>
    <form name="formAddSurvey" method="post" action="/surveys/SurveyController" onSubmit="return valida(this);" class="formulario">
	<div id="validacion" style="display:none">${Survey.description}</div>
        <label class="label">1) G&eacute;nero:</label>
        
        <div id="genero" class='radio_group'>
            <label style="cursor:pointer;width:100%;">
                    <input class="radio_btn" type="radio" value="0" name="genero"/>
                    Femenino<br>
            </label>
            <label style="cursor:pointer;width:100%;">
                    <input class="radio_btn" type="radio" value="1" name="genero"/>
                    Masculino</br>
            </label>
        </div>
        
        </br> 
        <label class="label">2) Provincia:</label>
        <div id="provincia" class='radio_group'>
       
            <label style="cursor:pointer;width:100%;">
                <c:forEach items="${provincias}" var="provincia">  
                    <input class="radio_btn" type="radio" value="${provincia.provinceId}" name="provincia"/>
                    ${provincia.name}</br> 
                </c:forEach>
            </label>    
        </div>
	</br> 	
        <label class="label">3) Edad:</label>	
        <div id="edad" class='radio_group'>
            <label style="cursor:pointer;width:100%;">
                <c:forEach items="${edades}" var="edad">  
                    <input class="radio_btn" type="radio" value="${edad.ageID}" name="edad"/>
                    ${edad.ageRange}</br> 
                </c:forEach>
            </label>   
        </div>
		
        <input type="hidden" name="survey_id" value="${Survey.surveyId}" id="survey_id"/>  
        <input type="hidden" name="action" value="saveFirstSurvey"/>  
        <div class="divisor">&nbsp;</div>
        <input type="submit" name="btnSubmit" style="width:163px" id="btnSubmit" class="btn-aceptar" value="Continuar con la encuesta"/>
        <div class="clear">&nbsp;</div>
    </form> 
    <script type="text/javascript" src="js/programacion/make_surveys.js"></script>
</custom:mainLayout>