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
	        <h1>${model.precio}</h1>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="capitalAseguradoVivienda" class="control-label">
							<spring:message code="seguroViviendaBean.edit.capitalAseguradoVivienda" /> <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="capitalAseguradoVivienda" path="capitalAseguradoVivienda" cssClass="form-control" />
					</div>			
					<div class="form-group">
						<label for="capitalAseguradoEnseres" class="control-label">
							<spring:message code="seguroViviendaBean.edit.capitalAseguradoEnseres" /> <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="capitalAseguradoEnseres" path="capitalAseguradoEnseres" cssClass="form-control" />
					</div>			
					<div class="form-group">
						<label for="valorResponsabilidadCivil" class="control-label">
							<spring:message code="seguroViviendaBean.edit.valorResponsabilidadCivil" /> <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="valorResponsabilidadCivil" path="valorResponsabilidadCivil" cssClass="form-control" />
					</div>
							
					<div class="form-group">
						<label class="control-label">Opciones</label>
						<div class="checkbox checkbox-primary">
							<form:checkbox id="opcionRotura" path="opcionRotura" />
							<label for="opcionRotura" class="control-label">
								<spring:message code="seguroViviendaBean.edit.opcionRotura" />
							</label>
						</div>
						<div class="checkbox checkbox-primary">
							<form:checkbox id="opcionRobo" path="opcionRobo" />
							<label for="opcionRobo" class="control-label">
								<spring:message code="seguroViviendaBean.edit.opcionRobo" />
							</label>
						</div>
						<div class="checkbox checkbox-primary">
							<form:checkbox id="opcionDefensaJuridica" path="opcionDefensaJuridica" />
							<label for="opcionDefensaJuridica" class="control-label">
								<spring:message code="seguroViviendaBean.edit.opcionDefensaJuridica" />
							</label>
						</div>
						<div class="checkbox checkbox-primary">
							<form:checkbox id="opcionInhabilidad" path="opcionInhabilidad" />
							<label for="opcionInhabilidad" class="control-label">
								<spring:message code="seguroViviendaBean.edit.opcionInhabilidad" />
							</label>
						</div>
						<div class="checkbox checkbox-primary">
							<form:checkbox id="opcionAsistenciaInformatica" path="opcionAsistenciaInformatica" />
							<label for="opcionAsistenciaInformatica" class="control-label">
								<spring:message code="seguroViviendaBean.edit.opcionAsistenciaInformatica" />
							</label>
						</div>
					</div>	
					
					<div class="form-group">
						<label for="capitalAseguradoJoyas" class="control-label">
							<spring:message code="seguroViviendaBean.edit.capitalAseguradoJoyas" /> <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="capitalAseguradoJoyas" path="capitalAseguradoJoyas" cssClass="form-control" />
					</div>
					<div class="form-group">
						<label for="capitalAseguradoObjetosDeValor" class="control-label">
							<spring:message code="seguroViviendaBean.edit.capitalAseguradoObjetosDeValor" /> <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="capitalAseguradoObjetosDeValor" path="capitalAseguradoObjetosDeValor" cssClass="form-control" />
					</div>
					<div class="form-group">
						<label for="capitalAseguradoRecomposicionEstetica" class="control-label">
							<spring:message code="seguroViviendaBean.edit.capitalAseguradoRecomposicionEstetica" /> <span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:input id="capitalAseguradoRecomposicionEstetica" path="capitalAseguradoRecomposicionEstetica" cssClass="form-control" />
					</div>
							
				</div>	
			</div>
	
			<jsp:include page="../../commons/flowButtons.jsp" />
		</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>		
	