<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="flowMenu.jsp" />

<html:form action="/seguroHogar?method=onSubmit">
	<input type="hidden" name="flowEvent" />

	<div class="row">
		<div class="col-md-12">
			<c:if test="${requestScope.get('org.apache.struts.action.ERROR').size() > 0}">
				<div class="alert alert-danger">
					<div class="h3" style="margin-top:0px;">Han ocurrido errores al validar los datos de esta pantalla</div>
			    	<html:messages id="aMsg" message="false">
			            <div class="messages">
			                <bean:write name="aMsg" filter="true" />
			            </div>
			    	</html:messages>
			    </div>
			</c:if>
			
			<logic:messagesPresent message="true">
				<div class="alert alert-warning">
					<div class="h3" style="margin-top:0px;">Mensajes</div>
			    	<html:messages id="aError" message="true">
				        <logic:present name="aError">
				            <div class="messages">
				                <bean:write name="aError" filter="false" />
				            </div>
				        </logic:present>
			    	</html:messages>
			    </div>
			</logic:messagesPresent>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label" for="tipoDeUsoViviendaId">Tipo de uso de la vivienda:</label>
				<html:select property="tipoDeUsoViviendaId" styleClass="form-control">
					<html:option value="" ></html:option>
					<html:options collection="tiposUsosViviendas" property="id" labelProperty="nombre" />
				</html:select>
			</div>			 
			<div class="form-group">
				<label class="control-label" for="numPersonasQueVivenEnLaVivienda">Número de personas que viven en la vivienda:</label>
				<html:text property="numPersonasQueVivenEnLaVivienda" styleClass="form-control"></html:text>
			</div>			
	
		</div>	
	</div>
</html:form>
 
<jsp:include page="flowButtons.jsp" />
 