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
						<label for="provinciaId" cssClass="control-label">
							<spring:message code="seguroViviendaBean.edit.provinciaId" />:
							<span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:select id="provinciaId" path="provinciaId" cssClass="form-control" >
							<form:option value=""></form:option>
    						<form:options items="${provincias}" />
						</form:select>
					</div>	
					<div class="form-group">
						<label for="localizacionId" cssClass="control-label">
							<spring:message code="seguroViviendaBean.edit.localizacionId" />:
							<span class="glyphicon glyphicon-mandatory" />
						</label>
						<form:select id="localizacionId" path="localizacionId" cssClass="form-control" >
							<form:option value=""></form:option>
    						<form:options items="${localizacionesViviendas}" />
						</form:select>
					</div>		
				</div>	
			</div>
			
			<jsp:include page="../../commons/flowButtons.jsp" />
		</form:form>
	
	</tiles:putAttribute>
</tiles:insertDefinition>