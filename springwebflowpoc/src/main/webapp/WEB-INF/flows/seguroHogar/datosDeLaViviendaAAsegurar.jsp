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
						<div class="form-group col-md-2">
							<label for="tipoDeViaViviendaId" class="control-label">
								<spring:message code="seguroViviendaBean.edit.tipoDeViaViviendaId" />:
								<span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:select id="tipoDeViaViviendaId" path="tipoDeViaViviendaId" cssClass="form-control" >
								<form:option value=""></form:option>
	    						<form:options items="${tiposDeVias}" />
							</form:select>
						</div>
						<div class="form-group col-md-2">
							<label for="domicilioVivienda" class="control-label">
								<spring:message code="seguroViviendaBean.edit.domicilioVivienda" />: <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="domicilioVivienda" path="domicilioVivienda" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="numeroYPisoVivienda" class="control-label">
								<spring:message code="seguroViviendaBean.edit.numeroYPisoVivienda" />: <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="numeroYPisoVivienda" path="numeroYPisoVivienda" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="codigoPostalVivienda" class="control-label">
								<spring:message code="seguroViviendaBean.edit.codigoPostalVivienda" />: <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="codigoPostalVivienda" path="codigoPostalVivienda" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="localidadVivienda" class="control-label">
								<spring:message code="seguroViviendaBean.edit.localidadVivienda" />: <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="localidadVivienda" path="localidadVivienda" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="provinciaViviendaId" class="control-label">
								<spring:message code="seguroViviendaBean.edit.provinciaViviendaId" />:
								<span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:select id="provinciaViviendaId" path="provinciaViviendaId" cssClass="form-control" >
								<form:option value=""></form:option>
	    						<form:options items="${provincias}" />
							</form:select>
						</div>
					</div>
				</div>
			</div>
	
			<jsp:include page="../../commons/flowButtons.jsp" />
		</form:form>
	
	</tiles:putAttribute>
</tiles:insertDefinition>