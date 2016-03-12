<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
		<div class="row">
			<div class="col-md-6">
				<h2>CONFIGURACIÓ“N INICIAL</h2>
			</div>
		</div>
		
		<div class="alert alert-success" role="alert">
			<div class="row">
				<div class="col-md-6">
					<h3>La configuración ha sido aplicada</h3>
				</div>
			</div>
		</div>			
	</tiles:putAttribute>
</tiles:insertDefinition>	
