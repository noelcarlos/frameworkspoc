<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">

		<form:form id="dataForm" commandName="model" name="dataForm">
			<jsp:include page="../../commons/flowMenu.jsp" />
			<jsp:include page="../../commons/flowFormErrors.jsp"  />
			
			<form:errors path="*" cssClass="errorBlock" element="div" />
			<input type="hidden" id="_eventId" name="_eventId" value=""/>
	
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="tipoDeConstruccionId" class="control-label">
							<spring:message code="seguroViviendaBean.edit.tipoDeConstruccionId" />:
							<span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:select id="tipoDeConstruccionId" path="tipoDeConstruccionId" cssClass="form-control" >
							<form:option value=""></form:option>
    						<form:options items="${tiposDeConstrucciones}" />
						</form:select>
					</div>	
					<div class="form-group">
						<label for="calidadDeLaConstruccionId" class="control-label">
							<spring:message code="seguroViviendaBean.edit.calidadDeLaConstruccionId" />:
							<span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:select id="calidadDeLaConstruccionId" path="calidadDeLaConstruccionId" cssClass="form-control" >
							<form:option value=""></form:option>
    						<form:options items="${calidadesDeLasConstrucciones}" />
						</form:select>
					</div>
					<div class="form-group">
						<label for="tipologiaDeLaViviendaId" class="control-label">
							<spring:message code="seguroViviendaBean.edit.tipologiaDeLaViviendaId" />:
							<span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:select id="tipologiaDeLaViviendaId" path="tipologiaDeLaViviendaId" cssClass="form-control" >
							<form:option value=""></form:option>
    						<form:options items="${tipologiasDeLasViviendas}" />
						</form:select>
					</div>		 			
				</div>	
			</div>
	
			<jsp:include page="../../commons/flowButtons.jsp" />
		</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>		

 