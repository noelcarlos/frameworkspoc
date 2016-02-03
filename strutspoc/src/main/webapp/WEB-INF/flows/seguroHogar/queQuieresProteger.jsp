<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="flowMenu.jsp" />

<div class="row">
	<div class="col-md-6">
		<html:form action="/seguroHogar?method=onSubmit">
			<html:errors />
			
			AHHAHA
			
			<logic:present name="org.apache.struts.action.ERROR" >
				<ul id="errors">
						%{requestScope['org.apache.struts.action.ERROR']}
					<logic:iterate id="error" name="org.apache.struts.action.ERROR" >
						<li>ASAS %{error}</li>
					</logic:iterate>
				</ul>
			</logic:present>
			
			<html:messages id="error" property="errors">
	            <li>ASAS<bean:write name="error"/></li>
	        </html:messages>
        
			<!-- errors coming from form validation -->
<logic:messagesPresent property="errors">
    <ul id="errors">
        <html:messages id="error" property="errors" >
            <li>ASAS<bean:write name="error"/></li>
        </html:messages>
    </ul>
</logic:messagesPresent>
<!-- errors coming from actions -->
<logic:messagesPresent message="true" property="errors">
    <ul id="errors">
        <html:messages id="error" message="true" property="errors">
            <li><bean:write name="error"/></li>
        </html:messages>
    </ul>
</logic:messagesPresent>
<!-- messages coming from actions -->
<logic:messagesPresent message="true" property="messages">
    <ul id="messages">
        <html:messages id="message" message="true" property="messages">
            <li><bean:write name="message"/></li>
        </html:messages>
    </ul>
</logic:messagesPresent>

			<logic:messagesPresent message="true">
			    <html:messages id="aMsg" message="true">
			        <logic:present name="aMsg">
			            <!-- Messages -->
			            <div class="messages">
			                <bean:write name="aMsg" filter="false" />
			            </div>
			        </logic:present>
			    </html:messages>
			</logic:messagesPresent>
			
			<logic:messagesPresent message="false">
			    <html:messages id="aMsg" message="false">
			        <logic:present name="aMsg">
			            <!-- Warnings-->
			            <div class="warnings">
			                <bean:write name="aMsg" filter="false" />
			            </div>
			        </logic:present>
			    </html:messages>
			</logic:messagesPresent>

			<input type="hidden" name="flowEvent" />
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
	
		</html:form>
	</div>	
</div>
 
<jsp:include page="flowButtons.jsp" />
 