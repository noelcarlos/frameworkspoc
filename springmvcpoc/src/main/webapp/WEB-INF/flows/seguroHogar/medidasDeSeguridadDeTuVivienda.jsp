<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">

		<form:form id="dataForm" commandName="model" name="dataForm" action="${pageContext.request.contextPath}/web/${executionUrl}">
			<jsp:include page="../../commons/flowMenu.jsp" />
			<jsp:include page="../../commons/flowFormErrors.jsp"  />
			
			<input type="hidden" id="_eventId" name="_eventId" value=""/>	
	
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Caracteristicas</label>
						<div class="checkbox checkbox-primary">
							<form:checkbox id="rejasEnVentanasOSimilares" path="rejasEnVentanasOSimilares" />
							<label for="rejasEnVentanasOSimilares" class="control-label">
								<spring:message code="seguroViviendaBean.edit.rejasEnVentanasOSimilares" />
							</label>
						</div>
						<div class="checkbox checkbox-primary">
							<form:checkbox id="puertaDeSeguridad" path="puertaDeSeguridad" />
							<label for="puertaDeSeguridad" class="control-label">
								<spring:message code="seguroViviendaBean.edit.puertaDeSeguridad" />
							</label>
						</div>
						<div class="checkbox checkbox-primary">
							<form:checkbox id="alarmaConectada" path="alarmaConectada" />
							<label for="alarmaConectada" class="control-label">
								<spring:message code="seguroViviendaBean.edit.alarmaConectada" />
							</label>
						</div>
						<div class="checkbox checkbox-primary">
							<form:checkbox id="cajaFuerte" path="cajaFuerte" />
							<label for="cajaFuerte" class="control-label">
								<spring:message code="seguroViviendaBean.edit.cajaFuerte" />
							</label>
						</div>
					</div>			
				</div>	
			</div>
	
		<jsp:include page="../../commons/flowButtons.jsp" />
		</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>	