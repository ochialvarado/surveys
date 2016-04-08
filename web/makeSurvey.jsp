<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:registerLayout title="Realizar Encuesta">
    <h1>${Survey.title}</h1>

    <form name="formAddSurvey" method="post" action="/surveys/SurveyController" onSubmit="return valida(this);" class="formulario">
        <div id="validacion" style="display:none"></div> 
        <c:set var="currentQuestion" value="0" scope="page" />
        <c:set var="currentAnswerType" value="0" scope="page" />
        <c:set var="currentItem" value="1" scope="page" />
        
        <c:forEach items="${questions}" var="question"> 
            <c:choose>
                <c:when test="${question.questionId != currentQuestion}">
                    
                    <c:if test="${currentQuestion == 2 || currentQuestion == 1}"> </div></c:if>                   
                    <c:set var="currentQuestion" value="${question.questionId}" scope="page"/>
                   
                    <label class="label">${currentItem})  ${question.questionText};?></label>
                    
                    
                    <c:if test="${question.answerTypeId == 1}">
                        <div id="${question.questionId}_group" class='radio_group'>
                    </c:if>

                    <c:if test="${question.answerTypeId == 2}">
                        <div id="${question.questionId}_group" class='checkbox_group'>
                    </c:if>

                    <c:if test="${question.answerTypeId == 2}">
                        <textarea class="text_area" name="question_${question.questionId}"></textarea>
                    </c:if>
                        
                </c:when>    
                <c:otherwise> 
                    <c:if test="${question.answerTypeId == 1}">
                        <label style="cursor:pointer;width:100%;">
                                <input class="radio_btn" type="radio" value="<${question.questionOptionId}" name="question_${question.questionId};?>">
                                ${question.description}<br>
                        </label>
                    </c:if>
                    
                    <c:if test="${question.answerTypeId == 2}">
                        <label style="cursor:pointer;width:100%;" class="">
                                <input class="checkbox_btn" type="checkbox" value="${question.questionOptionId}" name="question_${question.questionId}[]">
                                ${question.description}<br>
                        </label> 
                    </c:if>  
                    
                </c:otherwise>
            </c:choose>
        </c:forEach>
            
        <input type="hidden" name="survey_id" value="${Survey.surveyId}" id="survey_id">  
        <input type="hidden" name="forBucle" value="${questionsId}" id="forBucle">  
        <div class="divisor">&nbsp;</div>
        <input type="submit" name="btnSubmit" id="btnSubmit" class="btn-aceptar" value="GUARDAR">
        <div class="clear">&nbsp;</div>
    </form>
    <script type="text/javascript" src="js/programacion/make_surveys.js"></script>
</custom:registerLayout>