<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:mainLayout title="Dashboard">
    <h1>Mis Encuestas</h1>
    <div id="validacion" style="display:none">Los cambios se realizaron corectamente.</div>
    <div class="btn-agregar"><a href="/surveys/SurveyController?action=add">Agregar encuesta</a></div>
    <table>  
	<tr>
		<th width="30%" class="center">T&iacute;tulo</th>
		<th width="40%">Link</th>
		<th colspan="4" align="center">Acciones</th> 
        </tr>
        <c:forEach items="${surveys}" var="Survey">  
            <tr>
                <td>${Survey.title}</td>   
                <td class="center"><a target="_blank" href="/surveys/SurveyController?action=firstStep&id=${Survey.surveyId}">http://localhost/surveys/SurveyController?action=firstStep&id=${Survey.surveyId}</a></td>
                <td class="center"><a style="cursor:pointer; text-decoration:underline;" href="/surveys/SurveyController?action=listQuestion&surveyId=${Survey.surveyId}">Ir a Preguntas</a></td>
                <td class="center"><a target="_blank" style="cursor:pointer; text-decoration:underline;" href="/surveys/SurveyController?action=report&id=${Survey.surveyId}">Reportes</a></td> 
                <td class="center"><a href="/surveys/SurveyController?action=edit&surveyId=${Survey.surveyId}"><img src="img/img_editar.png" alt="EDITAR"></a></td> 
                <td class="center"><a href="#" onclick="if(confirm('Realmente desea eliminar: ${Survey.title}')){window.open('/surveys/SurveyController?action=delete&surveyId=${Survey.surveyId}','_self');}"><img src="img/img_eliminar.png" alt="ELIMINAR"></a></td>
            </tr>			
        </c:forEach>
    </table>
</custom:mainLayout>
