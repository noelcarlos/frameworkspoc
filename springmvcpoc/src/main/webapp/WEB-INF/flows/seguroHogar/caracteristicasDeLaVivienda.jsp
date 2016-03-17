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
						<label for="metrosCuadradosConstruidos" class="control-label">
							<spring:message code="seguroViviendaBean.edit.metrosCuadradosConstruidos" />: <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="metrosCuadradosConstruidos" path="metrosCuadradosConstruidos" cssClass="form-control" />		
					</div>			
					<div class="form-group">
						<label for="numeroDeDormitorios" class="control-label">
							<spring:message code="seguroViviendaBean.edit.numeroDeDormitorios" />: <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="numeroDeDormitorios" path="numeroDeDormitorios" cssClass="form-control" />		
					</div>	
					<div class="form-group">
						<label for="anyoDeConstruccion" class="control-label">
							<spring:message code="seguroViviendaBean.edit.anyoDeConstruccion" />: <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="anyoDeConstruccion" path="anyoDeConstruccion" cssClass="form-control" />		
					</div>			
					<div class="form-group">
						<label for="anyoDeLaUltimaRehabilitacion" class="control-label">
							<spring:message code="seguroViviendaBean.edit.anyoDeLaUltimaRehabilitacion" />: <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="anyoDeLaUltimaRehabilitacion" path="anyoDeLaUltimaRehabilitacion" cssClass="form-control" />		
					</div>
				</div>	
			</div>
	
			<jsp:include page="../../commons/flowButtons.jsp" />
		</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>	
