function checkSelection(){
	if($("#question_type").val()=="1" || $("#question_type").val()=="2"){
		$("#answers_wrapper").fadeIn(); 
		 jQuery("html, body").animate({ scrollTop: $("label1").scrollTop()+200 }, 1000);
	}else{
		jQuery("html, body").animate({ scrollTop: $("label1").scrollTop()-200 }, 1000);
		$("#answers_wrapper").fadeOut();
	}
}
function IsVoid(value){
	for (i=0; i < value.length; i++){
		if (value.charAt(i) != " ")
			return true
	}
	return false
}

function valida(form){ 
	var value= "";
	if (IsVoid(form.title.value) == false){
		form.title.className='input'; 
		value += "<p>&bull; Debe digitar el titulo de la encuesta.</p>";
	}
	else form.title.className='input';
	
	if (value !== ""){
		document.getElementById("validacion").innerHTML = '<p><strong>LOS SIGUIENTES ERRORES HAN OCURRIDO:</strong></p>'+value;
		$("#validacion").slideDown('slow');  
		return false;
	} else {
            return true;
        }
}//valida
function validaQuestions(form){
	var value= "";
	
	if (IsVoid(form.question_1.value) == false){
		form.question_1.className='input'; 
		value += "<p>&bull; Debe digitar la pregunta.</p>";
	}
	else form.question_1.className='input';
	
	if ($("#question_type").val() == "0"){
		value += "<p>&bull; Debe seleccionar el tipo de respuesta.</p>";
	}
	else{ 
		if($("#question_type").val() != "3"){
			if (IsVoid(form.answer_1.value) == false || IsVoid(form.answer_2.value) == false){
				value += "<p>&bull; Debe digitar al menos las dos primeras respuestas.</p>";
				jQuery("html, body").animate({ scrollTop: 100}, 1000);
			}  
			else form.question_1.className='input'; 
		}
	}
	
	if (value != ""){
		document.getElementById("validacion").innerHTML = '<p><strong>LOS SIGUIENTES ERRORES HAN OCURRIDO:</strong></p>'+value;
		$("#validacion").slideDown('slow');
		return false;
	}
}