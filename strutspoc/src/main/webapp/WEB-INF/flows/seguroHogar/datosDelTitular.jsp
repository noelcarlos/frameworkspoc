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
				<div class="form-group col-md-4">
					<label class="control-label" for="nombre">Nombre:</label>
					<html:text property="nombre" styleClass="form-control"></html:text>
				</div>			
				<div class="form-group col-md-4">
					<label class="control-label" for="apellido1">Apellido 1:</label>
					<html:text property="apellido1" styleClass="form-control"></html:text>
				</div>	
				<div class="form-group col-md-4">
					<label class="control-label" for="apellido2">Apellido 2:</label>
					<html:text property="apellido2" styleClass="form-control"></html:text>
				</div>
			</div>			
			<div class="row clearfix">
				<div class="form-group col-md-2">
					<label class="control-label" for="tipoDeDocumentoDeIdentidadId">Tipo de documento:</label>
					<html:select property="tipoDeDocumentoDeIdentidadId" styleClass="form-control">
						<html:option value="" ></html:option>
						<html:options collection="tiposDeDocumentosDeIndentidad" property="id" labelProperty="nombre" />
					</html:select>
				</div>
				<div class="form-group col-md-3">
					<label class="control-label" for="documentoIdentidad">Documento de identidad:</label>
					<html:text property="documentoIdentidad" styleClass="form-control"></html:text>
				</div>
				<div class="form-group col-md-4">
					<label class="control-label" for="fechaDeNacimiento">Fecha de nacimiento:</label>
					<html:text property="fechaDeNacimiento" styleClass="form-control date-picker"></html:text>
				</div>
				<div class="form-group col-md-3">
					<label class="control-label" for="sexoId">Sexo:</label>
					<html:select property="sexoId" styleClass="form-control">
						<html:option value="" ></html:option>
						<html:options collection="sexos" property="id" labelProperty="nombre" />
					</html:select>
				</div>
			</div>
			<div class="row clearfix">
				<div class="form-group col-md-3">
					<label class="control-label" for="telefonoMovil">Teléfono móvil:</label>
					<html:text property="telefonoMovil" styleClass="form-control"></html:text>
				</div>
				<div class="form-group col-md-3">
					<label class="control-label" for="email">Email:</label>
					<html:text property="email" styleClass="form-control"></html:text>
				</div>
			</div>
			<div class="row clearfix">
				<div class="form-group col-md-2">
					<label class="control-label" for="tipoDeViaTitularId">Tipo de via:</label>
					<html:select property="tipoDeViaTitularId" styleClass="form-control">
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
		<div class="row col-md-12">
			<div class="form-group col-md-12">
				<div class="checkbox checkbox-primary">
					<html:checkbox property="laViviendaAsegurarCoincideConLaDelTitular" styleId="laViviendaAsegurarCoincideConLaDelTitular"></html:checkbox>
					<label for="laViviendaAsegurarCoincideConLaDelTitular">La vivienda a asegurar coincide con la del titular</label>
				</div>
			</div>
		</div>
		
	</div>
	
</html:form>
 
<jsp:include page="../commons/flowButtons.jsp" />
 