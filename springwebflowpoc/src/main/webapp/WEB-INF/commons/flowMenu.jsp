<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<div class="row">
	<div class="col-md-12">
		<div class="separator-sm"></div>
		<div class="btn-group" role="group" aria-label="..."> 
		
			<c:forEach var="result" items="${esmartpointWebFlowEngine.flow.selectNodes('//flow/step')}">
				<c:if test="${!(esmartpointWebFlowEngine.currentStep lt result.valueOf('@name'))}">
					<button type="button" class="btn ${esmartpointWebFlowEngine.currentStep.toString().equals(result.valueOf('@name')) ? 'btn-success' : 'btn-default'}
							${!config[result.valueOf('@view').concat('Externo')] ? ' external-step' : ' internal-step'}"
						onClick="sendEvent('gobackward-${ result.valueOf('@name')}');">
						${result.valueOf("@menu")}
					</button>
				</c:if>
				<c:if test="${esmartpointWebFlowEngine.currentStep lt result.valueOf('@name')}">
					<button type="button" class="btn ${esmartpointWebFlowEngine.currentStep.toString().equals(result.valueOf('@name')) ? 'btn-success' : 'btn-primary'}
							${!config[result.valueOf('@view').concat('Externo')] ? ' external-step' : ' internal-step'}"
						onClick="sendEvent('goforward-${ result.valueOf('@name')}');">
						${result.valueOf("@menu")}
					</button>
				</c:if>
			</c:forEach>
		</div>
		<div class="separator-sm"></div>
		<h2>${esmartpointWebFlowEngine.currentPageNumber} - ${esmartpointWebFlowEngine.currentPageTitle}</h2>
		<div class="separator-sm"></div>
	</div>
</div>

	    <SCRIPT type="text/javascript">
	    	function sendEvent(eventName) {
	    		$('#_eventId').val(eventName);
	    		dataForm.submit();
	    	}
	    </SCRIPT>

