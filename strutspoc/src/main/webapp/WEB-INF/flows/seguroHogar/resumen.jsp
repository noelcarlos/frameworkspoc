<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="../commons/flowMenu.jsp" />

<html:form action="/seguroHogar?method=onSubmit">
	<jsp:include page="../commons/flowFormErrors.jsp" />
	
	<div class="row">
		<div class="col-md-12">
			<span class="h2">PRECIO FINAL</span>
			<span class="price-xl text-success">
			 	<fmt:formatNumber type="number" maxFractionDigits="2" value="${model.precio}" ></fmt:formatNumber>
			</span>	
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div class="h2">Contrato:  
				<strong>
			 		<fmt:formatNumber type="number" maxFractionDigits="0" value="${model.getContrato().length()/1024}" ></fmt:formatNumber> KB	
				</strong>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="code">
				<pre><code>${model.getContrato().substring(0, 2000)} ...</code></pre>
			</div>
		</div>
	</div>
	
</html:form>
 
<jsp:include page="../commons/flowButtons.jsp" />
 