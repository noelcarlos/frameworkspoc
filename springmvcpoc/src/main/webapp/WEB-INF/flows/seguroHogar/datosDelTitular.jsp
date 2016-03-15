<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">

		<form:form id="dataForm" commandName="model" name="dataForm">
			<jsp:include page="../../commons/flowMenu.jsp" />
			<jsp:include page="../../commons/flowFormErrors.jsp"  />
			
			<form:errors path="*" cssClass="errorBlock" element="div" />
			<input type="hidden" id="_eventId" name="_eventId" value=""/>
				
			<div class="row">
				<div class="col-md-12">
					<div class="row clearfix">
						<div class="form-group col-md-4">
							<label for="nombre" class="control-label">
								<spring:message code="seguroViviendaBean.edit.nombre" /> <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="nombre" path="nombre" cssClass="form-control" />
						</div>			
						<div class="form-group col-md-3">
							<label for="apellido1" class="control-label">
								<spring:message code="seguroViviendaBean.edit.apellido1" /> <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="apellido1" path="apellido1" cssClass="form-control" />
						</div>	
						<div class="form-group col-md-3">
							<label for="apellido2" class="control-label">
								<spring:message code="seguroViviendaBean.edit.apellido2" /> 
							</label>
							<form:input id="apellido2" path="apellido2" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="sexoId" class="control-label">
								<spring:message code="seguroViviendaBean.edit.sexoId" />
								<span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:select id="sexoId" path="sexoId" cssClass="form-control" >
								<form:option value=""></form:option>
	    						<form:options items="${sexos}" />
							</form:select>
						</div>
					</div>			
					<div class="row clearfix">
						<div class="form-group col-md-2">
							<label for="tipoDeDocumentoDeIdentidadId" class="control-label">
								<spring:message code="seguroViviendaBean.edit.tipoDeDocumentoDeIdentidadId" />
								<span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:select id="tipoDeDocumentoDeIdentidadId" path="tipoDeDocumentoDeIdentidadId" cssClass="form-control" >
								<form:option value=""></form:option>
	    						<form:options items="${tiposDeDocumentosDeIndentidad}" />
							</form:select>
						</div>
						<div class="form-group col-md-3">
							<label for="documentoIdentidad" class="control-label">
								<spring:message code="seguroViviendaBean.edit.documentoIdentidad" /> <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="documentoIdentidad" path="documentoIdentidad" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="fechaDeNacimiento" class="control-label">
								<spring:message code="seguroViviendaBean.edit.fechaDeNacimiento" /> <span class="glyphicon glyphicon-mandatory" />
							</label>
							<fmt:formatDate value="${model.fechaDeNacimiento}" type="date" pattern="dd/MM/yyyy" var="theFormattedDate" />
							<form:input id="fechaDeNacimiento" path="fechaDeNacimiento" cssClass="form-control date-picker" value="${theFormattedDate}"/>
						</div>
						<div class="form-group col-md-2">
							<label for="telefonoMovil" class="control-label">
								<spring:message code="seguroViviendaBean.edit.telefonoMovil" /> <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="telefonoMovil" path="telefonoMovil" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="email" class="control-label">
								<spring:message code="seguroViviendaBean.edit.email" /> <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="email" path="email" cssClass="form-control" />
						</div>
					</div>
					<div class="row clearfix">
						<div class="form-group col-md-2">
							<label for="tipoDeViaTitularId" class="control-label">
								<spring:message code="seguroViviendaBean.edit.tipoDeViaTitularId" />
								<span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:select id="tipoDeViaTitularId" path="tipoDeViaTitularId" cssClass="form-control" >
								<form:option value=""></form:option>
	    						<form:options items="${tiposDeVias}" />
							</form:select>
						</div>
						<div class="form-group col-md-2">
							<label for="domicilioTitular" class="control-label">
								<spring:message code="seguroViviendaBean.edit.domicilioTitular" /> <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="domicilioTitular" path="domicilioTitular" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="numeroYPisoTitular" class="control-label">
								<spring:message code="seguroViviendaBean.edit.numeroYPisoTitular" /> <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="numeroYPisoTitular" path="numeroYPisoTitular" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="codigoPostalTitular" class="control-label">
								<spring:message code="seguroViviendaBean.edit.codigoPostalTitular" /> <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="codigoPostalTitular" path="codigoPostalTitular" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="localidadTitular" class="control-label">
								<spring:message code="seguroViviendaBean.edit.localidadTitular" /> <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="localidadTitular" path="localidadTitular" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="provinciaTitularId" class="control-label">
								<spring:message code="seguroViviendaBean.edit.provinciaTitularId" />
								<span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:select id="provinciaTitularId" path="provinciaTitularId" cssClass="form-control" >
								<form:option value=""></form:option>
	    						<form:options items="${provincias}" />
							</form:select>
						</div>
					</div>
				</div>	
				<div class="row col-md-12">
					<div class="form-group col-md-12">
						<div class="checkbox checkbox-primary">
							<form:checkbox id="laViviendaAsegurarCoincideConLaDelTitular" path="laViviendaAsegurarCoincideConLaDelTitular" />	
							<label for="laViviendaAsegurarCoincideConLaDelTitular" class="control-label">
								<spring:message code="seguroViviendaBean.edit.laViviendaAsegurarCoincideConLaDelTitular" />
							</label>
						</div>
					</div>
				</div>
			</div>
			
			<jsp:include page="../../commons/flowButtons.jsp" />
		</form:form>
	
	</tiles:putAttribute>
</tiles:insertDefinition>