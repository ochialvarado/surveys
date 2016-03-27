function IsVoid(value){
	for (i=0; i < value.length; i++){
		if (value.charAt(i) != " ")
			return true
	}
	return false
}

function valida(form){
	var value= "";
	if (IsVoid(form.name.value) == false){
		form.name.className='input'; 
		value += "<p>&bull; Debe digitar su nombre.</p>";
	}
	else form.name.className='input';
	
	if (IsVoid(form.password.value) == false){
		form.password.className='input'; 
		value += "<p>&bull; Debe digitar su password.</p>";
	}
	else form.name.className='input';
	
	if (IsVoid(form.email.value) == false || !(/[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(form.email.value))){
		form.email.className='input'; 
		value += "<p>&bull; Debe digitar un email correcto.</p>"; 
	}
	else form.email.className='input';
	
	if (value != ""){
		document.getElementById("validacion").innerHTML = '<p><strong>LOS SIGUIENTES ERRORES HAN OCURRIDO:</strong></p>'+value;
		$("#validacion").slideDown('slow');
		return false;
	}
}//valida
function validaEdit(form){
	var value= "";
	if (IsVoid(form.name.value) == false){
		form.name.className='input'; 
		value += "<p>&bull; Debe digitar su nombre.</p>";
	}
	else form.name.className='input';

	if (value != ""){
		document.getElementById("validacion").innerHTML = '<p><strong>LOS SIGUIENTES ERRORES HAN OCURRIDO:</strong></p>'+value;
		$("#validacion").slideDown('slow');
		return false;
	}
}//valida