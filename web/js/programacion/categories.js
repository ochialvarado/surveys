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
		value += "<p>&bull; Debe digitar el t&iacute;tulo</p>";
	}
	else form.title.className='input';
	
	if (value != ""){
		document.getElementById("validacion").innerHTML = '<p><strong>LOS SIGUIENTES ERRORES HAN OCURRIDO:</strong></p>'+value;
		$("#validacion").slideDown('slow');
		return false;
	}
}//valida