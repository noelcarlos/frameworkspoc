<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../commons/flowMenu.jsp" />

<html:form action="/seguroHogar?method=onSubmit">
	<jsp:include page="../commons/flowFormErrors.jsp" />
	
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label" for="metrosCuadradosConstruidos">Metros cuadrados construidos:</label>
				<html:text property="metrosCuadradosConstruidos" styleClass="form-control"></html:text>
			</div>			
			<div class="form-group">
				<label class="control-label" for="numeroDeDormitorios">Número de dormitorios:</label>
				<html:text property="numeroDeDormitorios" styleClass="form-control"></html:text>
			</div>	
			<div class="form-group">
				<label class="control-label" for="anyoDeConstruccion">Año de construcción:</label>
				<html:text property="anyoDeConstruccion" styleClass="form-control"></html:text>
			</div>			
			<div class="form-group">
				<label class="control-label" for="anyoDeLaUltimaRehabilitacion">Año de la ultima rehabilitación:</label>
				<html:text property="anyoDeLaUltimaRehabilitacion" styleClass="form-control"></html:text>
			</div>
		</div>	
	</div>
	
</html:form>
 
<jsp:include page="../commons/flowButtons.jsp" />
 