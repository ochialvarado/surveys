<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:mainLayout title="Agregar Pregunta">
    <h1>Agregar Pregunta a: ${Survey.title} </h1> 
    <h2>AGREGAR</h2>
    <div id="validacion" style="display:none"></div>
    
    <h1>Registro</h1>
    <div id="validacion" style="display: none;"></div>
    
    <form name="formAddUser" method="post" action="/surveys/UserController/" onSubmit="return validaQuestions(this);" class="formulario">	
        <div class="question_wrapper">  
            <label class="label">Pregunta 1: <span class="nota"></span></label>
            <input name="question_1" type="text" id="question_1" maxlength="200" class="input">

            <label class="label">Tipo de Respuesta: <span class="nota"></span></label>
            <select onchange="checkSelection()" style="font-size: 13px; height: 29px; padding-top: 4px; width: 100%;margin-bottom: 20px; cursor:pointer;" id="question_type"  name="question_type">
                <option value="0">Seleccione</option> 
                
                <c:forEach items="${answerTypes}" var="AnswerType">  
                     <option value="${AnswerType.answerTypeId}">${AnswerType.title}</option>
                </c:forEach>
                     
            </select>
            <input type="hidden" value="<?php echo $rowSurvey['survey_id'];?>" name="survey_id">    
            
            <div id="answers_wrapper" style="display:none;"> 
                <label class="label">Respuesta 1: <span class="nota"></span></label>
                <input name="answer_1" type="text" id="answer_1" maxlength="200" class="input"> 

                <label class="label">Respuesta 2: <span class="nota"></span></label>
                <input name="answer_2" type="text" id="answer_2" maxlength="200" class="input"> 

                <label class="label">Respuesta 3: <span class="nota"></span></label>
                <input name="answer_3" type="text" id="answer_3" maxlength="200" class="input"> 

                <label class="label">Respuesta 4: <span class="nota"></span></label>
                <input name="answer_4" type="text" id="answer_4" maxlength="200" class="input"> 

                <label class="label">Respuesta 5: <span class="nota"></span></label>
                <input name="answer_5" type="text" id="answer_5" maxlength="200" class="input">
            </div> 
        </div>            
            <input type="hidden" name="action" value="addQuestion"/>
          
            <input type="button" name="btnCancel" id="btnCancel" class="btn-cancelar" value="CANCELAR" onClick="window.open('/surveys/SurveyController?action=listQuestion&surveyId=${Survey.surveyId}','_self');">
            <input type="submit" name="btnSubmit" id="btnSubmit" class="btn-aceptar" value="GUARDAR">
            <div class="clear">&nbsp;</div>
    </form>  
    <script type="text/javascript" src="js/programacion/surveys.js"></script>
</custom:mainLayout>