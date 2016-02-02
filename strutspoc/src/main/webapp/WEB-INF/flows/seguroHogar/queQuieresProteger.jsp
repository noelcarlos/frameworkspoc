<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="col-md-3">
		<c:forEach var="result" items="${sessionScope.get('flow').selectNodes('//flow/step')}">
			<div class="row">
				<button type="button" class="btn ${ sessionScope.get('currentStep').equals(result.valueOf('@name')) ? 'btn-success' : 'btn-primary'} col-md-12">
					${result.valueOf("@description")}
				</button>
			</div>
		</c:forEach>
	</div>
		
	<div class="col-md-9">
		formulario
	</div>	
</div>
 
