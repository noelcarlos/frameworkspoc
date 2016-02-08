<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../commons/flowMenu.jsp" />

<html:form action="/seguroHogar?method=onSubmit">
	<jsp:include page="../commons/flowFormErrors.jsp" />
	
	<div class="row">
		<div class="col-md-12">
			<div class="row clearfix">
				<div class="form-group col-md-2">
					<label class="control-label" for="tipoDeVíaTitularId">Tipo de via:</label>
					<html:select property="tipoDeVíaTitularId" styleClass="form-control">
						<html:option value="" ></html:option>
						<html:options collection="tiposDeVias" property="id" labelProperty="nombre" />
					</html:select>
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="domicilioTitular">Domicilio:</label>
					<html:text property="domicilioTitular" styleClass="form-control"></html:text>
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="numeroYPisoTitular">Número y piso:</label>
					<html:text property="numeroYPisoTitular" styleClass="form-control"></html:text>
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="codigoPostalTitular">Código postal:</label>
					<html:text property="codigoPostalTitular" styleClass="form-control"></html:text>
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="localidadTitular">Localidad:</label>
					<html:text property="localidadTitular" styleClass="form-control"></html:text>
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="provinciaTitularId">Provincia:</label>
					<html:select property="provinciaTitularId" styleClass="form-control">
						<html:option value="" ></html:option>
						<html:options collection="provincias" property="id" labelProperty="nombre" />
					</html:select>
				</div>
			</div>
		</div>
	</div>
	
</html:form>
 
<jsp:include page="../commons/flowButtons.jsp" />
 