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
			<div class="row clearfix">
				<div class="form-group col-md-2">
					<label class="control-label" for="cuentaIBAN">IBAN:</label>
					<html:text property="cuentaIBAN" styleClass="form-control"></html:text>
				</div>	
				<div class="form-group col-md-2">
					<label class="control-label" for="cuentaEntidad">Entidad:</label>
					<html:text property="cuentaEntidad" styleClass="form-control"></html:text>
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="cuentaOficina">Oficina:</label>
					<html:text property="cuentaOficina" styleClass="form-control"></html:text>
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="cuentaDigitoControl">DC:</label>
					<html:text property="cuentaDigitoControl" styleClass="form-control"></html:text>
				</div>
				<div class="form-group col-md-4">
					<label class="control-label" for="cuentaNumero">Número:</label>
					<html:text property="cuentaNumero" styleClass="form-control"></html:text>
				</div>
			</div>			
			
			<div class="row clearfix">
				<div class="form-group col-md-4">
					<label class="control-label" for="fechaDeEfecto">Fecha de efecto:</label>
					<html:text property="fechaDeEfecto" styleClass="form-control"></html:text>
				</div>			
			</div>
			
		</div>	
	</div>
	
</html:form>
 
<jsp:include page="../commons/flowButtons.jsp" />
 