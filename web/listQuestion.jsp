<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:mainLayout title="Dashboard">

    <h1>Preguntas de : ${Survey.title}</h1>
     <c:choose>
        <c:when test="${imposibleToChange > 0}">
            <div id="validacion" style="display:block">Esta encuesta no se puede modificar porque al menos una persona la ha contestado.</div> 
        </c:when>    
        <c:otherwise> 
            <div id="validacion" style="display:none;">Los cambios se realizaron corectamente.</div> 
            <div class="btn-agregar"><a style="margin-right:40px;"  href="/surveys/SurveyController?action=addQuestion&surveyId=${Survey.surveyId}">Agregar pregunta</a></div> 
            <div class="btn-agregar"><a style="background:#ccc;padding-left:20px;" href="/surveys/SurveyController?action=listSurvey">Regresar</a></div> 
        </c:otherwise>
    </c:choose>
            
        <table>
            <tr>
		<th width="30%" class="center">Pregunta</th>
		<th width="30%">Fecha de Creaci&oacute;n</th>
		<th colspan="4" align="center">Acciones</th> 
            </tr>
            <c:forEach items="${questions}" var="Question">  
                <tr>
                    <td>${Question.title}</td>   
                    <td>${Question.answerTypeText}</td> 
                    
                    <c:choose>
                        <c:when test="${imposibleToChange > 0}">
                            <td></td><td></td>
                        </c:when>    
                        <c:otherwise> 
                            <td class="center"><a href="/surveys/SurveyController?action=questionMod?id=${Question.questionId}"><img src="img/img_editar.png" alt="EDITAR"></a></td>
                            <td class="center"><a href="#" onclick="if(confirm('Realmente desea eliminar la pregunta:')){window.open('/surveys/SurveyController?action=deleteQuestion?id=${Question.questionId}','_self');}"><img src="img/img_eliminar.png" alt="ELIMINAR"></a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach> 
        </table>
</custom:mainLayout>