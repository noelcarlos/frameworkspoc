<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="col-md-12">
		<div class="separator-sm"></div>
		<div class="btn-group" role="group" aria-label="..."> 
			<c:forEach var="result" items="${sessionScope.get('flow').selectNodes('//flow/step')}">
				<c:if test="${sessionScope.get('currentStep') > (result.valueOf('@name')+0)}">
					<button type="button" class="btn ${sessionScope.get('currentStep').toString().equals(result.valueOf('@name')) ? 'btn-success' : 'btn-default'}
							${sessionScope.config[result.valueOf('@view').concat('Externo')] ? 'external-step' : 'internal-step'}"
						onClick="sendEvent('go-${ result.valueOf('@name')}');">
						${result.valueOf("@menu")}
					</button>
				</c:if>
				<c:if test="${!(sessionScope.get('currentStep') > (result.valueOf('@name') + 0))}">
					<button type="button" class="btn ${sessionScope.get('currentStep').toString().equals(result.valueOf('@name')) ? 'btn-success' : 'btn-primary'}
							${sessionScope.config[result.valueOf('@view').concat('Externo')] ? 'external-step' : 'internal-step'}"
						onClick="sendEvent('go-${ result.valueOf('@name')}');">
						${result.valueOf("@menu")}
					</button>
				</c:if>
			</c:forEach>
		</div>
		<div class="separator-sm"></div>
		<h2>${currentPageNumber} - ${currentPageTitle}</h2>
		<div class="separator-sm"></div>
	</div>
</div>

	    <SCRIPT type="text/javascript">
	    	function sendEvent(eventName) {
	    		$('#_flowEvent').val(eventName);
	    		flowForm.submit();
	    	}
	    </SCRIPT>