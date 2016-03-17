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
						<label for="tipoDeUsoViviendaId" class="control-label">
							<spring:message code="seguroViviendaBean.edit.tipoDeUsoViviendaId" />:
							<span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:select id="tipoDeUsoViviendaId" path="tipoDeUsoViviendaId" cssClass="form-control" >
							<form:option value=""></form:option>
    						<form:options items="${tiposUsosViviendas}" />
						</form:select>
					</div>			 
					<div class="form-group">
						<label for="numPersonasQueVivenEnLaVivienda" class="control-label">
							<spring:message code="seguroViviendaBean.edit.numPersonasQueVivenEnLaVivienda" />: <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="numPersonasQueVivenEnLaVivienda" path="numPersonasQueVivenEnLaVivienda" cssClass="form-control" />
					</div>			
				</div>	
			</div>
			
			<jsp:include page="../../commons/flowButtons.jsp" />
		</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>		
