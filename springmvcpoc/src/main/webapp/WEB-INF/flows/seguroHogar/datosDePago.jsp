<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">

		<form:form id="dataForm" commandName="model" name="dataForm" action="${pageContext.request.contextPath}/web/${executionUrl}">
			<jsp:include page="../../commons/flowMenu.jsp" />
			<jsp:include page="../../commons/flowFormErrors.jsp"  />
			
			<input type="hidden" id="_eventId" name="_eventId" value=""/>
	
			<div class="row">
				<div class="col-md-7">
					<div class="row clearfix">
						<div class="form-group col-md-2">
							<label for="cuentaIBAN" class="control-label">
								<spring:message code="seguroViviendaBean.edit.cuentaIBAN" />: <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="cuentaIBAN" path="cuentaIBAN" cssClass="form-control" />
						</div>	
						<div class="form-group col-md-2">
							<label for="cuentaEntidad" class="control-label">
								<spring:message code="seguroViviendaBean.edit.cuentaEntidad" />: <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="cuentaEntidad" path="cuentaEntidad" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="cuentaOficina" class="control-label">
								<spring:message code="seguroViviendaBean.edit.cuentaOficina" />: <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="cuentaOficina" path="cuentaOficina" cssClass="form-control" />
						</div>
						<div class="form-group col-md-2">
							<label for="cuentaDigitoControl" class="control-label">
								<spring:message code="seguroViviendaBean.edit.cuentaDigitoControl" />: <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="cuentaDigitoControl" path="cuentaDigitoControl" cssClass="form-control" />
						</div>
						<div class="form-group col-md-3">
							<label for="cuentaNumero" class="control-label">
								<spring:message code="seguroViviendaBean.edit.cuentaNumero" />: <span class="glyphicon glyphicon-mandatory" />
							</label>
							<form:input id="cuentaNumero" path="cuentaNumero" cssClass="form-control" />
						</div>
					</div>			
					
					<div class="row clearfix">
						<div class="form-group col-md-4">
							<label for="fechaDeEfecto" class="control-label">
								<spring:message code="seguroViviendaBean.edit.fechaDeEfecto" />: <span class="glyphicon glyphicon-mandatory" />
							</label>
							<fmt:formatDate value="${model.fechaDeEfecto}" type="date" pattern="dd/MM/yyyy" var="theFormattedDate" />
							<form:input id="fechaDeEfecto" path="fechaDeEfecto" cssClass="form-control datePicker" value="${theFormattedDate}"/>
						</div>			
					</div>
					
				</div>	
			</div>
	
			<jsp:include page="../../commons/flowButtons.jsp" />
		</form:form>
	
	</tiles:putAttribute>
</tiles:insertDefinition>