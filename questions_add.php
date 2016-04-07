<?php $pagina = "surveys"; ?>
<?php 
	require_once('abre.php'); 
	$sql="SELECT * FROM surveys WHERE survey_id=".base64_decode($_GET['s']);
	$result=mysql_query($sql);
	$rowSurvey=mysql_fetch_assoc($result); 
?>

<script type="text/javascript" src="js/programacion/surveys.js"></script>
<script type="text/javascript" src="js/apariencia/jqueryui.js"></script> 
<h1>Agregar Pregunta a: <?php echo $rowSurvey['title'];?> </h1> 
<h2>AGREGAR</h2>
<div id="validacion" style="display:none"></div>

<form name="formAddUser" method="post" action="actions/questions.php" onSubmit="return validaQuestions(this);" class="formulario" enctype="multipart/form-data">	
	<div class="question_wrapper">  
		<label class="label">Pregunta 1: <span class="nota"></span></label>
		<input name="question_1" type="text" id="question_1" maxlength="200" class="input">
		
		<label class="label">Tipo de Respuesta: <span class="nota"></span></label>
		<select onchange="checkSelection()" style="font-size: 13px; height: 29px; padding-top: 4px; width: 100%;margin-bottom: 20px; cursor:pointer;" id="question_type"  name="question_type">
			<option value="0">Seleccione</option> 
			<?php
				$result=mysql_query("SELECT * FROM answer_types ORDER BY title");
				while ($row = mysql_fetch_assoc($result)) {	
				?>
					<option value="<?php echo $row['answer_type_id'];?>"><?php echo $row['title'];?></option>
				<?php
				}
			?>
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
	
	<input type="button" name="btnCancel" id="btnCancel" class="btn-cancelar" value="CANCELAR" onClick="window.open('questions.php?s=<?php echo $_GET['s'];?>','_self');">
	<input type="submit" name="btnSubmit" id="btnSubmit" class="btn-aceptar" value="GUARDAR">
	<div class="clear">&nbsp;</div>
</form> 
				
<?php 
	require_once('cierra.php'); 
?>