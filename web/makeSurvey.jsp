<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:registerLayout title="Realizar Encuesta">
    <h1>${Survey.title}</h1>

    <form name="formAddSurvey" method="post" action="/surveys/SurveyController" onSubmit="return valida(this);" class="formulario">
        <div id="validacion" style="display:none"></div> 
        <c:set var="currentQuestion" value="0" scope="page" />
        <c:set var="currentAnswerType" value="0" scope="page" />
        <c:set var="currentItem" value="0" scope="page" />
        
        <c:forEach items="${questions}" var="question"> 
            <c:choose>
                <c:when test="${question.questionId != currentQuestion}">
                    
                    <c:if test="${currentQuestion != 0}"> </div></c:if> 
                    
                    <c:set var="currentAnswerType" value="${question.answerTypeId}" scope="page"/>
                    <c:set var="currentQuestion" value="${question.questionId}" scope="page"/>
                    <c:set var="currentItem" value="${currentItem + 1}" scope="page"/>
                    
                    <br/>
                    <label class="label">${currentItem})  ${question.questionText}</label>
                    
                    
                    <c:if test="${question.answerTypeId == 1}">
                        <div id="${question.questionId}_group" class='radio_group'>
                            <label style="cursor:pointer;width:100%;">
                                <input class="radio_btn" type="radio" value="${question.questionOptionId}" name="question_${question.questionId}">
                                ${question.description}<br>
                            </label>
                    </c:if>

                    <c:if test="${question.answerTypeId == 2}">
                        <div id="${question.questionId}_group" class='checkbox_group'>
                            <label style="cursor:pointer;width:100%;" class="">
                                <input class="checkbox_btn" type="checkbox" value="${question.questionOptionId}" name="question_${question.questionId}[]">
                                ${question.description}<br>
                            </label> 
                    </c:if>

                    <c:if test="${question.answerTypeId == 3}">
                        <div id="${question.questionId}_text">
                            <textarea class="text_area" name="question_${question.questionId}"></textarea>
                    </c:if>
                        
                </c:when>    
                <c:otherwise> 
                    <c:if test="${question.answerTypeId == 1}">
                        <label style="cursor:pointer;width:100%;">
                                <input class="radio_btn" type="radio" value="${question.questionOptionId}" name="question_${question.questionId}">
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
        <c:if test="${currentQuestion > 0}"> </div></c:if> 
            
        <input type="hidden" name="survey_id" value="${saveSurveyId}" id="survey_id">  
        <input type="hidden" name="forBucle" value="${questionsId}" id="forBucle">  
        <input type="hidden" name="action" value="saveSurvey" id="forBucle2">  
        <div class="divisor">&nbsp;</div>
        <input type="submit" name="btnSubmit" id="btnSubmit" class="btn-aceptar" value="GUARDAR">
        <div class="clear">&nbsp;</div>
    </form>
    <script type="text/javascript" src="js/programacion/make_surveys.js"></script>
</custom:registerLayout>