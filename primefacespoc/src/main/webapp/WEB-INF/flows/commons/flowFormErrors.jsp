<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<input id="_flowEvent" type="hidden" name="_flowEvent" />

<div class="row">
	<div class="col-md-12">
		<c:if test="${requestScope.get('org.apache.struts.action.ERROR').size() > 0}">
			<div class="alert alert-danger">
				<div class="h3" style="margin-top:0px;">
					Han ocurrido errores al validar los datos de esta pantalla
				</div>
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


