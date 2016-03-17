<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">

		<form:form id="dataForm" commandName="model" name="dataForm" action="${pageContext.request.contextPath}/web/${executionUrl}">
			<jsp:include page="../../commons/flowMenu.jsp" />
			<jsp:include page="../../commons/flowFormErrors.jsp"  />
			
			<input type="hidden" id="_eventId" name="_eventId" value=""/>
		
			<div class="row">
				<div class="col-md-4">
					<div class="panel dark-green-border">
		               <div class="panel-heading green-back white-color">
		                   <h3 class="panel-title">PRECIO FINAL</h3>
		               </div>
		               <ul class="panel-body list-group">
		                   <li class="list-group-item">
								<span class="price-xl text-success">
								 	<center>
										<fmt:formatNumber value="${model.precio}" type="currency" pattern="#,##0.##" var="theFormattedvalue" />
								 	    ${theFormattedvalue}
								 	</center>
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
							<fmt:formatNumber value="${model.getContrato().length()/1024}" type="number" pattern="#,##0" var="theFormattedvalue" />
					 		${theFormattedvalue} KB
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
	
			<jsp:include page="../../commons/flowButtons.jsp" />
		</form:form>
	
	</tiles:putAttribute>
</tiles:insertDefinition>