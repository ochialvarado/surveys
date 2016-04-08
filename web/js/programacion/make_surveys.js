function IsVoid(value){
	for (i=0; i < value.length; i++){
            if (value.charAt(i) != " ") {
                return true;
            }
	}
	return false;
}

function valida(form){ 
	var value= "";
	$(":text, textarea").each(function() {
            if($(this).val() === ""){
                value="error";
            }    
	});
	$('.checkbox_group').each(function() {  
            var thisId= $(this).attr('id');
            var checkBoxes = $('#'+thisId+' .checkbox_btn'); 
            var oneChecked = false;
            checkBoxes.each(function() {
                if ($(this).is(':checked') ) {
                    oneChecked = true;
                } 
            }); 
            if(!oneChecked){
                value="error";
            }
	});
	$('.radio_group').each(function() {  
		var thisId= $(this).attr('id');
		var radios = $('#'+thisId+' .radio_btn'); 
		var oneChecked = false;
		radios.each(function() {   
		    if ($(this).is(':checked') ) {
		    	oneChecked = true;
		    }   
		}); 
		if(!oneChecked){
                    value="error";
		}
	});

	if (value != ""){
            value= "Debe contestar todas la preguntas.";
            document.getElementById("validacion").innerHTML = '<p><strong>LOS SIGUIENTES ERRORES HAN OCURRIDO:</strong></p>'+value;
            $("#validacion").slideDown('slow');  
            jQuery("html, body").animate({ scrollTop:0 }, 1000);
            return false;
	}  
}//valida