<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:mainLayout title="Dashboard">
    
<h1>Bienvenido ${User.name}</h1>
<p>En las diferentes áreas podrá añadir, editar y eliminar sus encuestas.</p>
<div id="principal">
        <div class="clear"> </div>
</div>       

</custom:mainLayout>