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
				<label class="control-label" for="tipoDeUsoViviendaId">Tipo de uso de la vivienda:</label>
				<html:select property="tipoDeUsoViviendaId" styleClass="form-control">
					<html:option value="" ></html:option>
					<html:options collection="tiposUsosViviendas" property="id" labelProperty="nombre" />
				</html:select>
			</div>			 
			<div class="form-group">
				<label class="control-label" for="numPersonasQueVivenEnLaVivienda">Número de personas que viven en la vivienda:</label>
				<html:text property="numPersonasQueVivenEnLaVivienda" styleClass="form-control"></html:text>
			</div>			
	
		</div>	
	</div>
	
</html:form>
 
<jsp:include page="../commons/flowButtons.jsp" />
 