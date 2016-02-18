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
		<div class="col-md-4">
			<div class="panel dark-green-border">
               <div class="panel-heading green-back white-color">
                   <h3 class="panel-title">PRECIO FINAL</h3>
               </div>
               <ul class="panel-body list-group">
                   <li class="list-group-item">
						<span class="price-xl text-success">
						 	<center><fmt:formatNumber type="number" maxFractionDigits="2" value="${model.precio}" ></fmt:formatNumber> €</center>
						</span>	
                   </li>
                   <li class="list-group-item">
                         <center>Lea atentamente su contrato</center>
                   </li>
               </ul>
           </div>
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
				<pre><code>${model.getContrato().substring(0, (model.getContrato().length() < 2000) ? model.getContrato().length(): 2000)} ...</code></pre>
			</div>
		</div>
	</div>
	
</html:form>
 
<jsp:include page="../commons/flowButtons.jsp" />
 