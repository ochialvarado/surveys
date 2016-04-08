<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:registerLayout title="Reporte de Encuesta">
    <h1>${Survey.title}</h1>
    <span>Total de entrevistados: ${totalInterviewed}</span><br><br>
    <span>
        <c:forEach items="${firstStepStatics}" var="firstStep"> 
            Provincia: ${firstStep.name} | Edad: ${firstStep.ageRange} | encuestados: ${firstStep.total}<br/><br/>
        </c:forEach>
    </span>

    <div>
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
                    <br/><br/>
                    
                    
                    <c:if test="${question.answerTypeId == 1}">
                        <div id="${question.questionId}_group" class='radio_group'>
                            <label style="cursor:pointer;width:100%;">
                                <span style="display:inline-block; width: 300px;">* ${question.description} </span> <span style="display:inline-block; margin-left: 20px;"> <b>Cantidad:</b> ${question.answerCount} </span><br>
                            </label>
                    </c:if>

                    <c:if test="${question.answerTypeId == 2}">
                        <div id="${question.questionId}_group" class='checkbox_group'>
                            <label style="cursor:pointer;width:100%;" class="">
                                <span style="display:inline-block; width: 300px;">* ${question.description} </span> <span style="display:inline-block; margin-left: 20px;"> <b>Cantidad:</b> ${question.answerCount} </span><br>
                            </label> 
                    </c:if>

                    <c:if test="${question.answerTypeId == 3}">
                        <div id="${question.questionId}_text">
                            <p>${question.textAnswer}</p>
                    </c:if>
                        
                </c:when>    
                <c:otherwise> 
                    <c:if test="${question.answerTypeId == 1}">
                        <label style="cursor:pointer;width:100%;">
                                <span style="display:inline-block; width: 300px;">* ${question.description} </span> <span style="display:inline-block; margin-left: 20px;"> <b>Cantidad:</b> ${question.answerCount} </span><br>
                        </label>
                    </c:if>
                    
                    <c:if test="${question.answerTypeId == 2}">
                        <label style="cursor:pointer;width:100%;" class="">
                                <span style="display:inline-block; width: 300px;">* ${question.description} </span> <span style="display:inline-block; margin-left: 20px;"> <b>Cantidad:</b> ${question.answerCount} </span><br>
                        </label> 
                    </c:if>  
                    
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentQuestion > 0}"> </div></c:if> 
            
        <div class="divisor">&nbsp;</div>
        
        <div class="clear">&nbsp;</div>
    </div>
</custom:registerLayout>